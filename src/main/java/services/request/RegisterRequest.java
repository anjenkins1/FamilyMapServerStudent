package services.request;

public class RegisterRequest {

    /**
     * Register Request variables that must be non-empty and must be included in the request
     */
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    /**
     * Create new register request to test if all are included and not a duplicate username
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    /**
     * Gets the value of email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email - You can use getEmail() to get the value of email
     *
     * @param email variable to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the value of firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName - You can use getFirstName() to get the value of firstName
     *
     * @param firstName variable to be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the value of lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName - You can use getLastName() to get the value of lastName
     *
     * @param lastName variable to be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the value of gender
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender - You can use getGender() to get the value of gender
     *
     * @param gender variable to be set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
