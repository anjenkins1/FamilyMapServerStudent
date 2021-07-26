package results;

public class SinglePersonResult extends Result {

    String associatedUsername;
    String personID;
    String firstName;
    String lastName;
    String gender;
    String fatherID;
    String motherID;
    String spouse;

    /**
     * Takes in all event items associated with the one event and adds them to message variable for successful output
     * @param associatedUsername
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouse
     */
    public SinglePersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouse) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouse = spouse;
    }

    public SinglePersonResult() {

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
     * Gets the value of spouse
     *
     * @return spouse
     */
    public String getSpouse() {
        return spouse;
    }

    /**
     * Sets the spouse - You can use getSpouse() to get the value of spouse
     *
     * @param spouse variable to be set
     */
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString() {
        return "SinglePersonResult{" +
                "associatedUsername='" + associatedUsername + '\'' +
                ", personID='" + personID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", fatherID='" + fatherID + '\'' +
                ", motherID='" + motherID + '\'' +
                ", spouse='" + spouse + '\'' +
                '}';
    }
}
