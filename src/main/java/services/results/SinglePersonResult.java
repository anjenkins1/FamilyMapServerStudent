package services.results;

public class SinglePersonResult extends Result {

    /**
     * Takes in all event items associated with the one event and adds them to message variable for successful output
     * @param associatedUsername
     * @param personID
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public SinglePersonResult(String associatedUsername, String personID, String firstName, String lastName,
                              String gender, String fatherID, String motherID, String spouseID) {
        this.success = true;
    }

    /**
     * Error message is added to message and success is made false
     * @param message
     */
    public SinglePersonResult(String message) {
        this.message =message;
        this.success =false;
    }
}
