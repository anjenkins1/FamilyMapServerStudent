package services;

import data_access.AuthTokenDao;
import data_access.UserDao;
import model.User;
import services.request.LoginRequest;
import services.results.LoginResult;

public class LoginService extends Service {

    /**
     * Data access objects for user authentication
     */
    private UserDao userDataAccess;
    private AuthTokenDao authTokenDataAccess;

    /**
     * Takes in a login request, checks if user exists and has proper credentials for login
     * @param request - provided username and password
     * @return <code>LoginResult</code>
     */
    public LoginResult login(LoginRequest request) {
        return null;
    }

}
