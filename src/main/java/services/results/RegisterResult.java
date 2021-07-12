package services.results;

import model.AuthToken;
import model.User;

public class RegisterResult extends Result{

    /**
     * Strings to be returned in Success Response
     */
    private String authtoken;
    private String username;
    private String personID;

    /**
     * Register Result constructor for Successful response
     * @param message
     * @param addedUser
     * @param authToken
     */
    public RegisterResult(String message, User addedUser, AuthToken authToken) {
        this.message = message;
        this.username = addedUser.getUsername();
        this.authtoken = authToken.getAuthtoken();
        this.personID = addedUser.getPersonID();
        this.success = true;
    }

    /**
     * Register Result constructor for Unsuccessful response, makes all success response strings null
     * @param message
     */
    public RegisterResult(String message) {
        this.username = null;
        this.authtoken = null;
        this.personID = null;
        this.message = message;
        this.success = false;
    }

}
