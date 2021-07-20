package services;

import data_access.DataAccessException;
import data_access.EventDao;
import model.Event;
import services.results.AllEventResult;
import services.results.SingleEventResult;

import java.util.ArrayList;

public class EventService extends Service {

    /**
     * Data access objects
     */
    private EventDao eventDataAccess;

    public EventService () {
        eventDataAccess = new EventDao(connection);
    }

    /**
     * Finds specified event based on given ID
     * @param eventID
     * @return <code>SingleEventResult</code>
     */
    public SingleEventResult getOneEvent(String eventID) {
        SingleEventResult result = new SingleEventResult();

        try {
            Event event = eventDataAccess.getSingleEvent(eventID);

            if (event == null || !event.getAssociatedUsername().equals(authorizedUser)) {
                result.setMessage("Error: Person not associated with that user");
                result.setSuccess(false);
                closeDataStream(false, result);
            }
            else {
                result.setAssociatedUsername(event.getAssociatedUsername());
                result.setPersonID(event.getPersonID());
                result.setEventID(event.getEventID());
                result.setCity(event.getCity());
                result.setCountry(event.getCountry());
                result.setLatitude(event.getLatitude());
                result.setLongitude(event.getLongitude());
                result.setYear(event.getYear());
                result.setEventType(event.getEventType());
                result.setSuccess(true);
                closeDataStream(true, result);
            }
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            closeDataStream(false, result);
            return result;
        }

    }

    /**
     * Gives a list of all events in the database
     * @return <code>AllEventResult</code>
     */
    public AllEventResult getAllEvents() {
        AllEventResult result = new AllEventResult();
        ArrayList<Event> events = new ArrayList<>();
        try {
            events = eventDataAccess.getAllEvents(authorizedUser);
            result.setData(events);
            result.setSuccess(true);
            closeDataStream(true, result);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            closeDataStream(false, result);
            result.setSuccess(false);
            result.setMessage("Error: Unable to authenticate user");
            return result;
        }
    }

}
