package data_access;

import java.sql.Connection;
import java.util.List;

import model.Person;

public class PersonDao {

    private static Connection databaseConn;

    /**
     * <code>PersonDao</code> must be constructed with an opened database connection
     * @param conn
     */
    public PersonDao(Connection conn) {
        databaseConn = conn;
    }

    /**
     * Adds new person to the database
     * @param person - event to be added
     * @throws DataAccessException - catches SQL errors
     */
    public void insert (Person person) throws DataAccessException {
    }

    /**
     * Return the single Person object with the specified ID
     * @param personID - <code>String</code> specified ID
     * @return - <code>Person</code>
     */
    public Person getSinglePerson (String personID) {
        return null;
    }

    /**
     * Returns ALL family members of the current user.
     * @param auth_token - user <code>String</code> auth token to get the current user
     * @return <code>List<Person></code>
     */
    public  List<Person> getAllPeople (String auth_token) {
        return null;
    }


}
