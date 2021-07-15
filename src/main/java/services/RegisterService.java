package services;

import data_access.DataAccessException;
import model.*;
import data_access.UserDao;
import data_access.AuthTokenDao;
import data_access.PersonDao;
import services.request.RegisterRequest;
import services.results.RegisterResult;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisterService extends Service {

    /**
     * Plain old data objects created when the request is made
     */
    private User userToRegister;
    private Person personToAdd;
    private AuthToken authtokenToAdd;

    /**
     * Data access objects for the purpose of registration
     */
    private UserDao userDataAcccess;
    private PersonDao personDataAccess;
    private AuthTokenDao authTokenDataAccess;
    private Connection conn;

    /**
     * Constructs RegisterService initializing database access objects through database connection
     */
    public RegisterService() throws DataAccessException {

        conn = database.openConnection();
        userDataAcccess = new UserDao(conn);
        personDataAccess = new PersonDao(conn);
        authTokenDataAccess = new AuthTokenDao(conn);
    }

    /**
     * Registers a new user
     * @param request - RegisterRequest from user data input
     * @return <code>RegisterResult</code>
     */
    public RegisterResult register(RegisterRequest request) throws DataAccessException {


        return null;
    }

}
