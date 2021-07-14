package data_access;

import model.AuthToken;
import model.Event;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "INSERT INTO Event (event_id, associated_username, person_id, country, city, event_type, latitude, longitude, year)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setString(4, event.getCountry());
            stmt.setString(5, event.getCity());
            stmt.setString(6, event.getEventType());
            stmt.setFloat(7, event.getLatitude());
            stmt.setFloat(8, event.getLongitude());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to register user");
        }
    }

    /**
     * Returns the single Event object with the specified ID
     * @param eventID - specified ID to get
     * @return - <code>Event</code>
     */
    public Event getSingleEvent (String eventID) throws DataAccessException {
        Event returnEvent;
        ResultSet rs = null;

        String sql = "SELECT * FROM Event WHERE event_id = '" + eventID + "'";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnEvent = new Event(rs.getString("associated_username"), rs.getString("event_id"), rs.getString("person_id"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("event_type"), rs.getInt("year"));
                return returnEvent;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified Event");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Gets a <code>List</code> of <code>Events</code> for ALL events for ALL family members of the current user
     * @param auth_token - used to find the events associated with the user that is logged in with this auth_token
     * @return <code>ArrayList<Event></code>
     */
    public ArrayList<Event> getAllEvents (String auth_token) throws DataAccessException{

        ArrayList<Event> events = new ArrayList<>();
        AuthTokenDao aDao = new AuthTokenDao(databaseConn);
        Event returnEvent;
        ResultSet rs = null;

        try {
            AuthToken auth = aDao.getUserName(auth_token);

            if (auth != null) {
                String sql = "SELECT * FROM Event WHERE associated_username = '" + auth.getAssociatedUsername() + "'";
                try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        returnEvent = new Event(rs.getString("associated_username"), rs.getString("event_id"), rs.getString("person_id"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                                rs.getString("country"), rs.getString("city"), rs.getString("event_type"), rs.getInt("year"));
                        events.add(returnEvent);
                    }
                    if (events.isEmpty()) {
                        return null;
                    }
                    return events;
                } catch(SQLException e) {
                    e.printStackTrace();
                    throw new DataAccessException("Unable to access specified Event");
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new DataAccessException("No authentication token associated with that user");
            }
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("No authentication token associated with that user");
        }
    }

    /**
     * Removes all events from specified user
     * @param username - user to be removed
     * @throws DataAccessException
     */
    public void removeAllUserEvents (String username) throws DataAccessException {
        String sql = "DELETE FROM Event WHERE associated_username = ?";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            stmt.setString(1, username);

            stmt.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to delete all events from user");
        }
    }
}
