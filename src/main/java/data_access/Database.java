package data_access;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import java.sql.*;
import model.*;

public class Database {

    /**
     * <code>Connection</code> object for instances of database access
     */
    private Connection databaseCon;

    /**
     * Opens connection to database by passing through the path to the database and starting the transaction
     * @return the <code>Connection</code> object stored in this class
     * @throws DataAccessException - for SQL errors and nonexistent database errors
     */
    public Connection openConnection() throws DataAccessException {
        try {
            final String CONNECTION_URL = "jdbc:sqlite:familyServerDB.db";

            databaseCon = DriverManager.getConnection(CONNECTION_URL);

            databaseCon.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open the connection");
        }
        return databaseCon;
    }

    /**
     * If the data connection object is null, call open connection
     * @return <code>Connection</code> private object
     * @throws DataAccessException - for SQL errors
     */
    public Connection getConnection() throws DataAccessException {
        if (databaseCon == null) {
            return openConnection();
        }
        return databaseCon;
    }

    /**
     * Commits any changes if there where no errors. If there were errors, calls <code>.rollback</code> on the database
     * then closes the database connection
     * @param commit - states whether the changes were successful or not.
     * @throws DataAccessException - for SQL error in closing the connection
     */
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                databaseCon.commit();
            }
            else {
                databaseCon.rollback();
            }
            databaseCon.close();
            databaseCon = null;
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close the connection");
        }
    }

    /**
     * Creates a new SQL statement that clears all the data in all the tables
     * i.e. - <code>DELETE FROM User, Person, Event, Auth_Token</code>
     * @throws DataAccessException - catches SQL errors
     */
    public void clearAllTables() throws DataAccessException {
        try (Statement stmt = databaseCon.createStatement()) {
            String sql = "DELETE FROM User";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Person";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM AuthToken";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Event";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("SQL Error while clearing tables");
        }
    }
}
