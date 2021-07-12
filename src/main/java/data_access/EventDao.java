package data_access;

import model.Event;

import java.sql.Connection;
import java.util.List;

public class EventDao {

    private static Connection databaseConn;

    /**
     * <code>EventDao</code> must be constructed with an opened database connection
     * @param conn
     */
    public EventDao(Connection conn) {
        databaseConn = conn;
    }

    /**
     * Adds new event to the database
     * @param event - event to be added
     * @throws DataAccessException - catches SQL errors
     */
    public void insert (Event event) throws DataAccessException {

    }

    /**
     * Returns the single Event object with the specified ID
     * @param eventID - specified ID to get
     * @return - <code>Event</code>
     */
    public Event getSingleEvent (String eventID) {
        return null;
    }

    /**
     * Gets a <code>List</code> of <code>Events</code> for ALL events for ALL family members of the current user
     * @param auth_token - used to find the events associated with the user that is logged in with this auth_token
     * @return <code>List<Event></code>
     */
    public List<Event> getAllEvents (String auth_token) {
        return null;
    }
}
