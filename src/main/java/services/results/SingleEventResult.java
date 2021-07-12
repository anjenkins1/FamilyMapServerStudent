package services.results;

public class SingleEventResult extends Result {

    /**
     * Takes in all event items associated with the one event and adds them to message variable for successful output
     * @param associatedUsername
     * @param eventID
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
    public SingleEventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country,
                             String city, String eventType, int year) {
        this.success = true;
    }

    /**
     * Error message is added to message and success is made false
     * @param message
     */
    public SingleEventResult(String message) {
        this.message =message;
        this.success =false;
    }
}
