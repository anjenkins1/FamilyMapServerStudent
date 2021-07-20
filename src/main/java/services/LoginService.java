package services;

import data_access.*;
import data_generation.RandomItemGenerator;
import model.AuthToken;
import model.User;
import services.request.LoginRequest;
import services.results.LoginResult;

import java.sql.Connection;

public class LoginService extends Service {

    /**
     * Data access objects for user authentication
     */
    private UserDao userDataAccess;
    private AuthTokenDao authTokenDataAccess;


    public LoginService() {
        super();
        userDataAccess = new UserDao(connection);
        authTokenDataAccess = new AuthTokenDao(connection);
    }

    /**
     * Takes in a login request, checks if user exists and has proper credentials for login
     * @param request - provided username and password
     * @return <code>LoginResult</code>
     */
    public LoginResult login(LoginRequest request) throws DataAccessException {
        LoginResult result = new LoginResult();
        try {
            User user = userDataAccess.find(request.getUsername(), request.getPassword());
            AuthToken authToken = authTokenDataAccess.getAuthToken(request.getUsername());
            if (user != null) {
                result.setSuccess(true);
                if (authToken == null) {
                    result.setAuthtoken(generator.getRandomID());
                    result.setPersonID(user.getPersonID());
                    result.setUsername(user.getUsername());

                    AuthToken token = new AuthToken(user.getUsername(), result.getAuthtoken());
                    authTokenDataAccess.insert(token);

                }
                else {
                    result.setAuthtoken(authToken.getAuthtoken());
                    result.setPersonID(user.getPersonID());
                    result.setUsername(user.getUsername());
                }
                database.closeConnection(true);
            }
            else {
                result.setMessage("Error: User not registered or incorrect password");
                result.setSuccess(false);
                database.closeConnection(false);
            }

            return result;

        } catch (DataAccessException e) {
            e.printStackTrace();
            database.closeConnection(false);
            return null;
        }
    }

}
