package services;

import data_access.AuthTokenDao;
import data_access.EventDao;
import data_access.PersonDao;
import services.results.FillResult;


public class FillService extends Service {

    /**
     * numGenerations - how many generations need to be added
     * username - user to add these generations too
     * numPersonsAdded - count of how many persons are added
     * numEventsAdded - count of how many events are added
     */
    private int numGenerations;
    private String username;
    private int numPersonsAdded;
    private int numEventsAdded;

    /**
     * Data access objects
     */
    private PersonDao personDataAccess;
    private EventDao eventDataAccess;
    private AuthTokenDao authTokenDataAccess;

    /**
     * Constructor with default numGenerations set at 4
     * @param username - user to fill
     */
    public FillService (String username) {
        this.username = username;
        this.numGenerations = 4;
    }

    /**
     * Constructor with user defined generations
     * @param username - user to fill
     * @param numGenerations - user defined numGenerations
     */
    public FillService(String username, int numGenerations)  {
        this.username = username;
        this.numGenerations = numGenerations;
    }

    /**
     * Fill the database with number of persons and events as provided
     * @return <code>FillResult</code>
     */
    public FillResult fill() {
        return null;
    }

    /**
     * Gets the value of numGenerations
     *
     * @return numGenerations
     */
    public int getNumGenerations() {
        return numGenerations;
    }

    /**
     * Sets the numGenerations - You can use getNumGenerations() to get the value of numGenerations
     *
     * @param numGenerations variable to be set
     */
    public void setNumGenerations(int numGenerations) {
        this.numGenerations = numGenerations;
    }

    /**
     * Gets the value of username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username - You can use getUsername() to get the value of username
     *
     * @param username variable to be set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Gets the value of eventDataAccess
     *
     * @return eventDataAccess
     */
    public EventDao getEventDataAccess() {
        return eventDataAccess;
    }

    /**
     * Sets the eventDataAccess - You can use getEventDataAccess() to get the value of eventDataAccess
     *
     * @param eventDataAccess variable to be set
     */
    public void setEventDataAccess(EventDao eventDataAccess) {
        this.eventDataAccess = eventDataAccess;
    }

    /**
     * Gets the value of authTokenDataAccess
     *
     * @return authTokenDataAccess
     */
    public AuthTokenDao getAuthTokenDataAccess() {
        return authTokenDataAccess;
    }

    /**
     * Sets the authTokenDataAccess - You can use getAuthTokenDataAccess() to get the value of authTokenDataAccess
     *
     * @param authTokenDataAccess variable to be set
     */
    public void setAuthTokenDataAccess(AuthTokenDao authTokenDataAccess) {
        this.authTokenDataAccess = authTokenDataAccess;
    }
}
