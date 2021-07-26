package services;

import data_access.DataAccessException;
import data_generation.DataGeneration;
import model.*;
import data_access.*;
import request.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;

public class RegisterService extends Service {

    /**
     * Plain old data objects created when the request is made
     */
    private User userToRegister;
    private Person personToAdd;
    private AuthToken authtokenToAdd;

    /**
     * Data access objects for the purpose of registration
     */
    private UserDao userDataAcccess;
    private PersonDao personDataAccess;
    private EventDao eventDataAccess;
    private AuthTokenDao authTokenDataAccess;
    private DataGeneration dataGeneration;

    /**
     * Constructs RegisterService initializing database access objects through database connection
     */
    public RegisterService() {
        userDataAcccess = new UserDao(connection);
        personDataAccess = new PersonDao(connection);
        eventDataAccess = new EventDao(connection);
        authTokenDataAccess = new AuthTokenDao(connection);
        dataGeneration = new DataGeneration();
    }

    /**
     * Registers a new user
     * @param request - RegisterRequest from user data input
     * @return <code>RegisterResult</code>
     */
    public RegisterResult register(RegisterRequest request) throws DataAccessException {
        RegisterResult result = new RegisterResult();
        try {
            if (!userDataAcccess.userExists(request.getUsername())) {

                String personID = generator.getRandomID();
                String username = request.getUsername();
                String password = request.getPassword();
                String email = request.getEmail();
                String firstName = request.getFirstName();
                String lastName = request.getLastName();
                String gender = request.getGender();

                userToRegister = new User(personID, username, password, email, firstName, lastName, gender);
                userDataAcccess.insert(userToRegister);

                personToAdd = new Person(personID, username, firstName, lastName, gender);

                dataGeneration.generateData(personToAdd, 4);

                addPersonsToDatabase(personDataAccess, dataGeneration.getPersons());

                addEventsToDataBase(eventDataAccess, dataGeneration.getEvents());

                result.setSuccess(true);
                result.setAuthtoken(generator.getRandomID());
                result.setPersonID(personID);
                result.setUsername(request.getUsername());

                authtokenToAdd = new AuthToken(userToRegister.getUsername(), result.getAuthtoken());
                authTokenDataAccess.insert(authtokenToAdd);

                database.closeConnection(true);

            }
            else {
                result.setMessage("Error: User already registered");
                result.setSuccess(false);
                database.closeConnection(false);
            }

            return result;

        } catch (DataAccessException e) {
            e.printStackTrace();
            database.closeConnection(false);
            return null;
        }
    }

    /**
     * Gets the value of userToRegister
     *
     * @return userToRegister
     */
    public User getUserToRegister() {
        return userToRegister;
    }

    /**
     * Sets the userToRegister - You can use getUserToRegister() to get the value of userToRegister
     *
     * @param userToRegister variable to be set
     */
    public void setUserToRegister(User userToRegister) {
        this.userToRegister = userToRegister;
    }

    /**
     * Gets the value of personToAdd
     *
     * @return personToAdd
     */
    public Person getPersonToAdd() {
        return personToAdd;
    }

    /**
     * Sets the personToAdd - You can use getPersonToAdd() to get the value of personToAdd
     *
     * @param personToAdd variable to be set
     */
    public void setPersonToAdd(Person personToAdd) {
        this.personToAdd = personToAdd;
    }

    /**
     * Gets the value of authtokenToAdd
     *
     * @return authtokenToAdd
     */
    public AuthToken getAuthtokenToAdd() {
        return authtokenToAdd;
    }

    /**
     * Sets the authtokenToAdd - You can use getAuthtokenToAdd() to get the value of authtokenToAdd
     *
     * @param authtokenToAdd variable to be set
     */
    public void setAuthtokenToAdd(AuthToken authtokenToAdd) {
        this.authtokenToAdd = authtokenToAdd;
    }
}
