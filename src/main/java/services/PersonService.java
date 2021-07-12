package services;

import data_access.AuthTokenDao;
import data_access.PersonDao;
import services.results.AllPersonsResult;
import services.results.SinglePersonResult;

public class PersonService extends Service {

    /**
     * Data access objects
     */
    private PersonDao personDataAccess;
    private AuthTokenDao authTokenDataAccess;

    public PersonService() {};

    /**
     * Finds specified person based on given ID
     * @param personID
     * @return <code>SinglePersonResult</code>
     */
    public SinglePersonResult getOnePerson(String personID) {
        return null;
    }

    /**
     * Gives a list of all people in the database
     * @return <code>AllPersonsResult</code>
     */
    public AllPersonsResult getAllPeople() {
        return null;
    }
}
