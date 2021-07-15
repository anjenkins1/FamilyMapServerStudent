package services;

import data_access.DataAccessException;
import data_access.EventDao;
import data_access.PersonDao;
import data_access.UserDao;
import services.request.LoadRequest;
import services.request.LoginRequest;
import services.results.LoadResult;

public class LoadService extends Service{

    /**
     * numUsersAdded = number of users added to the database
     * numPersonsAdded = number of persons added to the database
     * numEventsAdded = number of events added to the database
     */
    private int numUsersAdded;
    private int numPersonsAdded;
    private int numEventsAdded;

    /**
     * Data access objects
     */
    private UserDao userDataAccess;
    private PersonDao personDataAccess;
    private EventDao eventsDataAccess;

    /**
     * Default LoadService Constructor
     */
    public LoadService() {

    }

    /**
     * Clears all data from the database then
     * Loads the specified ArrayList's of objects from <code>LoginRequest</code> into the database
     * @return <code>LoadResult</code>
     * @throws DataAccessException
     */
    public LoadResult load(LoadRequest request) throws DataAccessException {
        return null;
    }

    /**
     * Gets the value of numUsersAdded
     *
     * @return numUsersAdded
     */
    public int getNumUsersAdded() {
        return numUsersAdded;
    }

    /**
     * Sets the numUsersAdded - You can use getNumUsersAdded() to get the value of numUsersAdded
     *
     * @param numUsersAdded variable to be set
     */
    public void setNumUsersAdded(int numUsersAdded) {
        this.numUsersAdded = numUsersAdded;
    }

    /**
     * Gets the value of numPersonsAdded
     *
     * @return numPersonsAdded
     */
    public int getNumPersonsAdded() {
        return numPersonsAdded;
    }

    /**
     * Sets the numPersonsAdded - You can use getNumPersonsAdded() to get the value of numPersonsAdded
     *
     * @param numPersonsAdded variable to be set
     */
    public void setNumPersonsAdded(int numPersonsAdded) {
        this.numPersonsAdded = numPersonsAdded;
    }

    /**
     * Gets the value of numEventsAdded
     *
     * @return numEventsAdded
     */
    public int getNumEventsAdded() {
        return numEventsAdded;
    }

    /**
     * Sets the numEventsAdded - You can use getNumEventsAdded() to get the value of numEventsAdded
     *
     * @param numEventsAdded variable to be set
     */
    public void setNumEventsAdded(int numEventsAdded) {
        this.numEventsAdded = numEventsAdded;
    }

    /**
     * Gets the value of userDataAccess
     *
     * @return userDataAccess
     */
    public UserDao getUserDataAccess() {
        return userDataAccess;
    }

    /**
     * Sets the userDataAccess - You can use getUserDataAccess() to get the value of userDataAccess
     *
     * @param userDataAccess variable to be set
     */
    public void setUserDataAccess(UserDao userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    /**
     * Gets the value of personDataAccess
     *
     * @return personDataAccess
     */
    public PersonDao getPersonDataAccess() {
        return personDataAccess;
    }

    /**
     * Sets the personDataAccess - You can use getPersonDataAccess() to get the value of personDataAccess
     *
     * @param personDataAccess variable to be set
     */
    public void setPersonDataAccess(PersonDao personDataAccess) {
        this.personDataAccess = personDataAccess;
    }

    /**
     * Gets the value of eventsDataAccess
     *
     * @return eventsDataAccess
     */
    public EventDao getEventsDataAccess() {
        return eventsDataAccess;
    }

    /**
     * Sets the eventsDataAccess - You can use getEventsDataAccess() to get the value of eventsDataAccess
     *
     * @param eventsDataAccess variable to be set
     */
    public void setEventsDataAccess(EventDao eventsDataAccess) {
        this.eventsDataAccess = eventsDataAccess;
    }
}
