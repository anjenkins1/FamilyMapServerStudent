package services;

import data_access.DataAccessException;
import model.*;
import data_access.UserDao;
import data_access.AuthTokenDao;
import data_access.PersonDao;
import services.request.RegisterRequest;
import services.results.LoginResult;
import services.results.RegisterResult;

import java.sql.Connection;
import java.sql.SQLException;

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
    private AuthTokenDao authTokenDataAccess;
    private Connection conn;

    /**
     * Constructs RegisterService initializing database access objects through database connection
     */
    public RegisterService() {
        try {
            conn = database.openConnection();
            userDataAcccess = new UserDao(conn);
            personDataAccess = new PersonDao(conn);
            authTokenDataAccess = new AuthTokenDao(conn);
        } catch(DataAccessException e) {
            e.printStackTrace();
            success = false;
            message = "Error: Unable to open database connection";
        }
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

                User user = new User(personID, username, password, email, firstName, lastName, gender);
                userDataAcccess.insert(user);



                result.setSuccess(true);
                result.setAuthtoken(generator.getRandomID());
                result.setPersonID(personID);
                result.setUsername(request.getUsername());

                AuthToken token = new AuthToken(user.getUsername(), result.getAuthtoken());
                authTokenDataAccess.insert(token);

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

}
