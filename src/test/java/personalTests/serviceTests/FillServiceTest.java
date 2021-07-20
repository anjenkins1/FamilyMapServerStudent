package personalTests.serviceTests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;
import services.*;
import services.request.RegisterRequest;
import services.results.AllEventResult;
import services.results.AllPersonsResult;
import services.results.FillResult;
import services.results.RegisterResult;

import javax.xml.crypto.Data;
import java.util.ArrayList;


public class FillServiceTest {

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final RegisterRequest ALMA_REQUEST = new RegisterRequest(ALMA);

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    @DisplayName("Test Fill Default")
    public void testFillDefault() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        AllEventResult eventsResult = eventService.getAllEvents();

        assertEquals(91, eventsResult.getData().size());

        PersonService personService = new PersonService();

        personService.checkAuthToken(result.getAuthtoken());

        AllPersonsResult personsResult = personService.getAllPeople();

        assertEquals(31, personsResult.getData().size());
    }

    @Test
    @DisplayName("Test Fill 2")
    public void testFill2() throws DataAccessException {
        int generations = 2;
        int minimumPeople = (int) Math.pow(2, generations + 1) - 1;
        int minEvents = minimumPeople * 2;

        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        FillService fillService = new FillService(result.getUsername(), generations);

        FillResult fillResult = fillService.fill();

        assertTrue(fillResult.success);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        AllEventResult eventsResult = eventService.getAllEvents();

        assertTrue(minEvents <= eventsResult.getData().size(), "Not enough events");

        PersonService personService = new PersonService();

        personService.checkAuthToken(result.getAuthtoken());

        AllPersonsResult personsResult = personService.getAllPeople();

        assertTrue(minimumPeople <= personsResult.getData().size(), "Not enough people");
    }

    @Test
    @DisplayName("Test Fill 5")
    public void testFill5() throws DataAccessException {
        int generations = 5;
        int minimumPeople = (int) Math.pow(2, generations + 1) - 1;
        int minEvents = minimumPeople * 2;

        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        FillService fillService = new FillService(result.getUsername(), generations);

        FillResult fillResult = fillService.fill();

        assertTrue(fillResult.success);

        EventService eventService = new EventService();

        eventService.checkAuthToken(result.getAuthtoken());

        AllEventResult eventsResult = eventService.getAllEvents();

        assertTrue(minEvents <= eventsResult.getData().size(), "Not enough events");

        PersonService personService = new PersonService();

        personService.checkAuthToken(result.getAuthtoken());

        AllPersonsResult personsResult = personService.getAllPeople();

        assertTrue(minimumPeople <= personsResult.getData().size(), "Not enough people");
    }

    @Test
    @DisplayName("Test Invalid Fill")
    public void testInvalidFill() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        FillService fillService = new FillService("Wrong username", 4);

        FillResult fillResult = fillService.fill();

        assertFalse(fillResult.success);
        assertTrue(fillResult.getMessage().toLowerCase().contains("error"), "Does not contain error");
    }

}

