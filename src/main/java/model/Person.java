package model;

import java.util.Objects;

/**
 * Base Person object with variable representations of database fields.
 */

public class Person {

    /**
     *  Unique identifier for this person (non-empty string)
     */
    String personID;

    /**
     * User (Username) to which this person belongs
     */
    String associatedUsername;

    /**
     * Person's first name (non-empty string)
     */
    String firstName;

    /**
     * Person's last name (non-empty string)
     */
    String lastName;

    /**
     * Person's gender (string: 'f' or 'm')
     */
    String gender;

    /**
     * Person ID of person's father (possibly null)
     */
    String fatherID;

    /**
     * Person ID of person's mother (possibly null)
     */
    String motherID;

    /**
     * Person ID of person's spouse (possibly null)
     */
    String spouseID;

    /**
     * Constructor that takes in REQUIRED variables and sets father, mother, and spouse ID to null
     * @param personID Unique identifier for this person (non-empty string)
     * @param associatedUsername User (Username) to which this person belongs
     * @param firstName Person's first name (non-empty string)
     * @param lastName Person's last name (non-empty string)
     * @param gender Person's gender (string: 'f' or 'm')
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    /**
     * Constructor that takes in all values
     * @param personID Unique identifier for this person (non-empty string)
     * @param associatedUsername User (Username) to which this person belongs
     * @param firstName Person's first name (non-empty string)
     * @param lastName Person's last name (non-empty string)
     * @param gender Person's gender (string: 'f' or 'm')
     * @param fatherID Person ID of person's father (possibly null)
     * @param motherID Person ID of person's mother (possibly null)
     * @param spouseID Person ID of person's spouse (possibly null)
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person() {

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
     * Gets the value of fatherID
     *
     * @return fatherID
     */
    public String getFatherID() {
        return fatherID;
    }

    /**
     * Sets the fatherID - You can use getFatherID() to get the value of fatherID
     *
     * @param fatherID variable to be set
     */
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    /**
     * Gets the value of motherID
     *
     * @return motherID
     */
    public String getMotherID() {
        return motherID;
    }

    /**
     * Sets the motherID - You can use getMotherID() to get the value of motherID
     *
     * @param motherID variable to be set
     */
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    /**
     * Gets the value of spouseID
     *
     * @return spouseID
     */
    public String getSpouseID() {
        return spouseID;
    }

    /**
     * Sets the spouseID - You can use getSpouseID() to get the value of spouseID
     *
     * @param spouseID variable to be set
     */
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    /**
     * Class toString in list form
     * @return all variables in a list
     */
    @Override
    public String toString() {
        return "Person{" +
                "personID='" + personID + '\'' +
                ", associatedUsername='" + associatedUsername + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", fatherID='" + fatherID + '\'' +
                ", motherID='" + motherID + '\'' +
                ", spouseID='" + spouseID + '\'' +
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
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPersonID().equals(person.getPersonID()) && getAssociatedUsername().equals(person.getAssociatedUsername()) && getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName()) && getGender().equals(person.getGender()) && Objects.equals(getFatherID(), person.getFatherID()) && Objects.equals(getMotherID(), person.getMotherID()) && Objects.equals(getSpouseID(), person.getSpouseID());
    }

    /**
     * Generates unique hashCode for class
     * @return integer of unique hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPersonID(), getAssociatedUsername(), getFirstName(), getLastName(), getGender(), getFatherID(), getMotherID(), getSpouseID());
    }
}
