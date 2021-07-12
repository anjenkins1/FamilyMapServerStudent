package services.results;

import model.AuthToken;
import model.User;

public class LoginResult extends Result {
    /**
     * Strings to be returned in Success Response
     */
    private String authtoken;
    private String username;
    private String personID;

    /**
     * Login Result constructor for Successful response
     * @param message - Success message
     * @param retrievedUser - use the user object retrieved from the service
     * @param authToken - authtoken retrieved from logged in user
     */
    public LoginResult(String message, User retrievedUser, AuthToken authToken) {
        this.message = message;
        this.username = retrievedUser.getUsername();
        this.authtoken = authToken.getAuthtoken();
        this.personID = retrievedUser.getPersonID();
        this.success = true;
    }

    /**
     * Login Result constructor for Unsuccessful response, makes all success response strings null
     * @param message - Unsuccessful action message
     */
    public LoginResult(String message) {
        this.username = null;
        this.authtoken = null;
        this.personID = null;
        this.message = message;
        this.success = false;
    }
}
