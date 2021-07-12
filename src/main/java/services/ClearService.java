package services;

import data_access.*;
import services.results.ClearResult;

public class ClearService extends Service {

    /**
     * Constructs ClearService class with default Service database connection
     */
    public ClearService() {
        super();
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data
     * @return <code>ClearResult</code>
     * @throws DataAccessException - catches SQl errors
     */
    public ClearResult clear() throws DataAccessException {
        database.clearAllTables();
        return null;
    }
}
