package model;

import java.util.Objects;

/**
 * Base Event object with variable representations of database fields.
 */

public class Event {

    /**
     * User (Username) to which this person belongs
     */
    String associatedUsername;

    /**
     * Unique identifier for this event (non-empty string)
     */
    String eventID;

    /**
     * Unique Person ID assigned to this user’s generated Person object
     */
    String personID;

    /**
     * Latitude of event’s location (float)
     */
    float latitude;

    /**
     * Longitude of event’s location (float)
     */
    float longitude;

    /**
     * Country in which event occurred
     */
    String country;

    /**
     * City in which event occurred
     */
    String city;

    /**
     * Type of event (birth, baptism, christening, marriage, death, etc.)
     */
    String eventType;

    /**
     * Year in which event occurred (integer)
     */
    int year;

    /**
     * Constructor that must have all required variables for class initialization
     * @param associatedUsername User (Username) to which this person belongs
     * @param eventID Unique identifier for this event (non-empty string)
     * @param personID Unique Person ID assigned to this user’s generated Person object
     * @param latitude Latitude of event’s location (float)
     * @param longitude Longitude of event’s location (float)
     * @param country Country in which event occurred
     * @param city City in which event occurred
     * @param eventType Type of event (birth, baptism, christening, marriage, death, etc.)
     * @param year Year in which event occurred (integer)
     */
    public Event(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public Event() {

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

    /**
     * Class toString in list form
     * @return all variables in a list
     */
    @Override
    public String toString() {
        return "Event{" +
                "associatedUsername='" + associatedUsername + '\'' +
                ", eventID='" + eventID + '\'' +
                ", personID='" + personID + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", eventType='" + eventType + '\'' +
                ", year=" + year +
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
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Float.compare(event.getLatitude(), getLatitude()) == 0 && Float.compare(event.getLongitude(), getLongitude()) == 0 && getYear() == event.getYear() && getAssociatedUsername().equals(event.getAssociatedUsername()) && getEventID().equals(event.getEventID()) && getPersonID().equals(event.getPersonID()) && getCountry().equals(event.getCountry()) && getCity().equals(event.getCity()) && getEventType().equals(event.getEventType());
    }

    /**
     * Generates unique hashCode for class
     * @return integer of unique hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(getAssociatedUsername(), getEventID(), getPersonID(), getLatitude(), getLongitude(), getCountry(), getCity(), getEventType(), getYear());
    }
}
