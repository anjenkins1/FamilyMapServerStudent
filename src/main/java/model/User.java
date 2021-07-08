package model;

import java.util.Objects;

/**
 * Base User object with variable representations of database fields.
 */

public class User {

    /**
     * Unique Person ID assigned to this user’s generated Person object
     */
    String personID;

    /**
     * Unique username (non-empty string)
     */
    String username;

    /**
     * User's password (non-empty string)
     */
    String password;

    /**
     * User's email address (non-empty string)
     */
    String email;

    /**
     * User's first name (non-empty string)
     */
    String firstName;

    /**
     * User's last name (non-empty string)
     */
    String lastName;

    /**
     * User's gender (string: 'f' or 'm')
     */
    String gender;

    /**
     * Constructor that must have all required variables for class initialization
     * @param personID Unique Person ID assigned to this user’s generated Person object
     * @param username Unique username (non-empty string)
     * @param password User's password (non-empty string)
     * @param email User's email address (non-empty string)
     * @param firstName User's first name (non-empty string)
     * @param lastName User's last name (non-empty string)
     * @param gender User's gender (string: 'f' or 'm')
     */
    public User(String personID, String username, String password, String email, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    /**
     * Class toString in list form
     * @return all variables in a list
     */
    @Override
    public String toString() {
        return "User{" +
                "personID='" + personID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    /**
     * Checks if objects are equal
     * @param o object to be compared
     * @return boolean, true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getPersonID().equals(user.getPersonID()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getEmail().equals(user.getEmail()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getGender().equals(user.getGender());
    }

    /**
     * Generates unique hashCode for class
     * @return integer of unique hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPersonID(), getUsername(), getPassword(), getEmail(), getFirstName(), getLastName(), getGender());
    }
}
