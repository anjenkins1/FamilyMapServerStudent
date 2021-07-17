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

    public RegisterResult() {

    }

    /**
     * Gets the value of authtoken
     *
     * @return authtoken
     */
    public String getAuthtoken() {
        return authtoken;
    }

    /**
     * Sets the authtoken - You can use getAuthtoken() to get the value of authtoken
     *
     * @param authtoken variable to be set
     */
    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    /**
     * Gets the value of username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username - You can use getUsername() to get the value of username
     *
     * @param username variable to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the value of personID
     *
     * @return personID
     */
    public String getPersonID() {
        return personID;
    }

    /**
     * Sets the personID - You can use getPersonID() to get the value of personID
     *
     * @param personID variable to be set
     */
    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
