package services.request;

public class LoginRequest {

    /**
     * Non-empty values for request generation
     */
    private String username;
    private String password;

    /**
     * Construct new LoginRequest in order to make a valid request
     * @param username
     * @param password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
     * Gets the value of password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password - You can use getPassword() to get the value of password
     *
     * @param password variable to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
