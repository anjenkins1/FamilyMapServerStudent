package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.User;

import javax.xml.crypto.Data;

public class UserDao {

    private Connection databaseConn;

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

        String sql = "INSERT INTO User(person_id, username, password, email, first_name, last_name, gender)" +
                    "VALUES(?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            stmt.setString(1, userToAdd.getPersonID());
            stmt.setString(2, userToAdd.getUsername());
            stmt.setString(3, userToAdd.getPassword());
            stmt.setString(4, userToAdd.getEmail());
            stmt.setString(5, userToAdd.getFirstName());
            stmt.setString(6, userToAdd.getLastName());
            stmt.setString(7, userToAdd.getGender());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to register user");
        }
    }

    /**
     * Finds the given user and returns their data if they exist within the database
     * @param username - Username of user being found
     * @param password - Associated password
     * @return - <code>User</code>
     * @throws DataAccessException - catches SQL errors
     */
    public User find(String username, String password) throws DataAccessException {
        User returnUser;
        ResultSet rs = null;

        String sql = "SELECT * FROM User WHERE (username = '" + username + "' AND password = '" + password + "')";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnUser = new User(rs.getString("person_id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("gender"));
                return returnUser;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified user");
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
     * Clears all data from user table
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM User";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to delete all users");
        }
    }

}
