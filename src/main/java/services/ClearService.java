package services;

import data_access.*;
import results.ClearResult;
import results.Result;

public class ClearService extends Service {

    /**
     * Constructs ClearService class with default Service database connection
     */
    public ClearService() {
        super();
    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and generated person and event data
     * @return <code>Result</code>
     * @throws DataAccessException - catches SQl errors
     */
    public Result clear() {
        boolean success;
        String message;
        Result result = new Result();
        try {
            database.clearAllTables();
        } catch(DataAccessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("Error: Unable to clear tables");
            closeDataStream(false, result);
            return result;
        }
        result.setSuccess(true);
        result.setMessage("Clear succeeded");
        closeDataStream(true, result);
        return result;
    }
}
