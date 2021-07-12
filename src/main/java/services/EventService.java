package services;

import data_access.AuthTokenDao;
import data_access.EventDao;
import services.results.AllEventResult;
import services.results.SingleEventResult;

public class EventService extends Service {

    /**
     * Data access objects
     */
    private EventDao eventDataAccess;
    private AuthTokenDao authTokenDataAccess;

    public EventService () {}

    /**
     * Finds specified event based on given ID
     * @param eventID
     * @return <code>SingleEventResult</code>
     */
    public SingleEventResult getOneEvent(String eventID) {
        return null;
    }

    /**
     * Gives a list of all events in the database
     * @return <code>AllEventResult</code>
     */
    public AllEventResult getAllEvents() {
        return null;
    }

}
