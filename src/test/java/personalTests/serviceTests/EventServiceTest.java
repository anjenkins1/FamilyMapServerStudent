package personalTests.serviceTests;

import data_access.DataAccessException;
import data_generation.RandomItemGenerator;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ClearService;
import services.EventService;
import services.RegisterService;
import request.RegisterRequest;
import results.*;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {
    private RandomItemGenerator generator = new RandomItemGenerator();

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final RegisterRequest ALMA_REQUEST = new RegisterRequest(ALMA);

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    @DisplayName("Test Valid Events")
    public void testValidEvents() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        AllEventResult events = eventService.getAllEvents();

        assertNotNull(events);
        assertTrue(events.isSuccess());
        assertNotNull(events.getData());
    }

    @Test
    @DisplayName("Test Valid Event")
    public void testValidEvent() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        AllEventResult events = eventService.getAllEvents();

        EventService eventService2 = new EventService();

        eventService2.checkAuthToken(result.getAuthtoken());

        SingleEventResult event = eventService2.getOneEvent(events.getData().get(0).getEventID());

        assertNotNull(event);
        assertEquals(result.getUsername(), event.getAssociatedUsername(), "Usernames not equal");
        assertEquals(result.getPersonID(), event.getPersonID(), "Person IDs not equal");

        assertTrue(event.isSuccess());
    }

    @Test
    @DisplayName("Test Invalid Event")
    public void testInvalidEvent() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        SingleEventResult event = eventService.getOneEvent("bad person ID");

        assertNotNull(event);
        assertFalse(event.isSuccess(), "Result not false");
        assertTrue(event.getMessage().toLowerCase().contains("error"), "Error not found in return result");
    }

    @Test
    @DisplayName("Test Invalid AuthToken")
    public void testInvalidAuth_Token() throws DataAccessException {
        EventService eventService = new EventService();

        assertFalse(eventService.checkAuthToken("Bad Auth").isSuccess());

    }

}
