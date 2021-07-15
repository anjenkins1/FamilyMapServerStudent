package services;

import com.sun.net.httpserver.Authenticator;
import data_access.*;
import services.results.ClearResult;
import services.results.Result;

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
        Result result;
        try {
            database.openConnection();
            database.clearAllTables();
            database.closeConnection(true);
        } catch(DataAccessException e) {
            e.printStackTrace();
            success = false;
            message = "Error: Unable to clear tables";
            result = new Result(message, success);
            return result;
        }
        success = true;
        message = "Clear succeeded";
        result = new Result(message, success);
        return result;
    }
}
