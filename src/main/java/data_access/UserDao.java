package data_access;

import java.sql.Connection;
import java.util.List;

import model.User;

public class UserDao {

    private static Connection databaseConn;

    /**
     * <code>UserDao</code> must be constructed with an opened database connection
     * @param conn
     */
    public UserDao(Connection conn) {
        databaseConn = conn;
    }

    /**
     * Adds the designated user by creating the correct SQL statement with the given user's data
     * @param userToAdd - <code>User</code> object containing the information given by the new user for registration
     * @throws DataAccessException - catches SQL errors
     */
    public void insert(User userToAdd) throws DataAccessException {

    }

    /**
     * Finds the given user and returns their data if they exist within the database
     * @param username - Username of user being found
     * @param password - Associated password
     * @return - True if the user exists, False if otherwise
     * @throws DataAccessException - catches SQL errors
     */
    public boolean find(String username, String password) throws DataAccessException {
        return false;
    }


}
