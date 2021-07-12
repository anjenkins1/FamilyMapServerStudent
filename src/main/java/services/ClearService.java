package services;

import data_access.*;

public class ClearService {

    private Database databaseAccess;

    /**
     * Instantiates a new <code>Database</code> object
     */
    public ClearService() {
        databaseAccess = new Database();
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data
     * @throws DataAccessException - catches SQl errors
     */
    public void clearTables() throws DataAccessException {
        databaseAccess.clearAllTables();
    }
}
