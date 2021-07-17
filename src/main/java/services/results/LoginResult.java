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
     * @param retrievedUser - use the user object retrieved from the service
     * @param authToken - authtoken retrieved from logged in user
     */
    public LoginResult(User retrievedUser, AuthToken authToken) {
        this.message = null;
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

    public LoginResult() {
        this.username = null;
        this.authtoken = null;
        this.personID = null;
        this.message = null;
        this.success = false;
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

    @Override
    public String toString() {
        return "LoginResult{" +
                "authtoken='" + authtoken + '\'' +
                ", username='" + username + '\'' +
                ", personID='" + personID + '\'' +
                '}';
    }
}
