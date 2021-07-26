package model;

import java.util.Objects;

/**
 * Base AuthToken object with variable representations of database fields.
 */

public class AuthToken {

    /**
     * User (Username) to which this person belongs
     */
    String associatedUsername;

    /**
     * Unique identifier for client-server communication
     */
    String authtoken;

    /**
     * Constructor that must have all required variables for class initialization
     * @param associatedUsername User (Username) to which this person belongs
     * @param authtoken Unique identifier for client-server communication
     */
    public AuthToken(String associatedUsername, String authtoken) {
        this.associatedUsername = associatedUsername;
        this.authtoken = authtoken;
    }

    /**
     * Gets the value of associatedUsername
     *
     * @return associatedUsername
     */
    public String getAssociatedUsername() {
        return associatedUsername;
    }

    /**
     * Sets the associatedUsername - You can use getAssociatedUsername() to get the value of associatedUsername
     *
     * @param associatedUsername variable to be set
     */
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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
     * Class toString in list form
     * @return all variables in a list
     */
    @Override
    public String toString() {
        return "AuthToken{" +
                "associatedUsername='" + associatedUsername + '\'' +
                ", authtoken='" + authtoken + '\'' +
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
        if (!(o instanceof AuthToken)) return false;
        AuthToken authToken = (AuthToken) o;
        return getAssociatedUsername().equals(authToken.getAssociatedUsername()) && getAuthtoken().equals(authToken.getAuthtoken());
    }

    /**
     * Generates unique hashCode for class
     * @return integer of unique hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(getAssociatedUsername(), getAuthtoken());
    }
}
