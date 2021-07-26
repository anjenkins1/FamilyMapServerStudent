package results;

public class SingleEventResult extends Result {

    String associatedUsername;
    String eventID;
    String personID;
    float latitude;
    float longitude;
    String country;
    String city;
    String eventType;
    int year;

    /**
     * Creates a new <code>SingleEventResult</code> object
     */
    public SingleEventResult() {

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
     * Gets the value of eventID
     *
     * @return eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets the eventID - You can use getEventID() to get the value of eventID
     *
     * @param eventID variable to be set
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
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
     * Gets the value of latitude
     *
     * @return latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude - You can use getLatitude() to get the value of latitude
     *
     * @param latitude variable to be set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the value of longitude
     *
     * @return longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude - You can use getLongitude() to get the value of longitude
     *
     * @param longitude variable to be set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the value of country
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country - You can use getCountry() to get the value of country
     *
     * @param country variable to be set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the value of city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city - You can use getCity() to get the value of city
     *
     * @param city variable to be set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the value of eventType
     *
     * @return eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Sets the eventType - You can use getEventType() to get the value of eventType
     *
     * @param eventType variable to be set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Gets the value of year
     *
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year - You can use getYear() to get the value of year
     *
     * @param year variable to be set
     */
    public void setYear(int year) {
        this.year = year;
    }
}
