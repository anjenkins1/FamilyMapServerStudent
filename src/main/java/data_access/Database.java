package data_access;

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
        return databaseCon;
    }

    /**
     * If the data connection object is null, call open connection
     * @return <code>Connection</code> private object
     * @throws DataAccessException - for SQL errors
     */
    public Connection getConnection() throws DataAccessException {
        return databaseCon;
    }

    /**
     * Commits any changes if there where no errors. If there were errors, calls <code>.rollback</code> on the database
     * then closes the database connection
     * @param commit - states whether the changes were successful or not.
     */
    public void closeConnection(boolean commit) {

    }

    /**
     * Creates a new SQL statement that clears all the data in all the tables
     * i.e. - <code>DELETE FROM User, Person, Event, Auth_Token</code>
     * @throws DataAccessException - catches SQL errors
     */
    public void clearAllTables() throws DataAccessException {

    }
}
