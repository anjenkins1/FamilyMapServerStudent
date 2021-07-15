package data_generation;

public class Location {
    private String country;
    private String city;
    private float latitude;
    private float longitude;

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

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
