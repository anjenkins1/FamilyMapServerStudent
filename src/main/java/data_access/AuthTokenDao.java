package data_access;

import model.AuthToken;

import java.sql.Connection;

public class AuthTokenDao {

    private static Connection databaseConn;

    /**
     * <code>AuthTokenDao</code> must be constructed with an opened database connection
     * @param conn
     */
    public AuthTokenDao(Connection conn) {
        databaseConn = conn;
    }

    /**
     * Adds a generated authToken with username to the Auth_Token database
     * @param token - <code>AuthToken</code> object to be added
     */
    public void insert(AuthToken token) {
    }

    /**
     * Get a token from the logged in user
     * @param username - <code>String</code> logged in user
     * @return - associated auth token
     */
    public AuthToken getAuthToken (String username) {
        return null;
    }
}
