package data_access;

import model.AuthToken;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void insert(AuthToken token) throws DataAccessException {
        String sql = "INSERT INTO AuthToken (associated_username, auth_token)" +
                "VALUES(?,?)";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            stmt.setString(1, token.getAssociatedUsername());
            stmt.setString(2, token.getAuthtoken());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to add Authentication Token");
        }
    }

    /**
     * Get a token from the logged in user
     * @param username - <code>String</code> logged in user
     * @return - associated auth token
     */
    public AuthToken getAuthToken (String username) throws DataAccessException {
        AuthToken returnAuthToken;
        ResultSet rs = null;

        String sql = "SELECT * FROM AuthToken WHERE associated_username = '" + username + "'";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnAuthToken = new AuthToken(rs.getString("associated_username"), rs.getString("auth_token"));
                return returnAuthToken;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified Authorization Token");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Gets the username of a specified authToken
     * @param authToken - authToken to be searched
     * @return - <code>AuthToken</code>
     * @throws DataAccessException
     */
    public AuthToken getUserName (String authToken) throws DataAccessException {
        AuthToken returnAuthToken;
        ResultSet rs = null;

        String sql = "SELECT * FROM AuthToken WHERE auth_token = '" + authToken + "'";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnAuthToken = new AuthToken(rs.getString("associated_username"), rs.getString("auth_token"));
                return returnAuthToken;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified Authorization Token");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}


