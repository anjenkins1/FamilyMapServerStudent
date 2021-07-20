package personalTests.daoTests;

import client.Client;
import logs.InitLogs;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

public class EventDaoTest {

    private static final Logger logger;

    static {
        InitLogs.init();
        logger = Logger.getLogger(Client.class.getName());
    }

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final Event E_ALMA_DEATH = new Event("alma", "almaDeath", "alma_chief_judge", 5.21f, 3.20f, "country", "city", "death", 100);
    private static final Event E_ALMA_BIRTH = new Event("alma", "almaBirth", "alma_chief_judge", 5.21f, 3.20f, "country", "city", "death", 20);
    private static final AuthToken A_ALMA = new AuthToken("alma", "gi231pc");

    private Database db;
    private EventDao eDao;
    private AuthTokenDao aDao;

    private static boolean displayCurrentTest = true;

    public static void setDisplayCurrentTest(boolean displayCurrentTest) {
        EventDaoTest.displayCurrentTest = displayCurrentTest;
    }

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        db = new Database();

        Connection conn = db.getConnection();

        db.clearAllTables();

        eDao = new EventDao(conn);
        aDao = new AuthTokenDao(conn);
    }

    @AfterEach
    public void closeConnection() throws DataAccessException {
        try {
            db.closeConnection(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("Add Event")
    public void addPerson(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA_BIRTH);
            Event testEvent = eDao.getSingleEvent(E_ALMA_BIRTH.getEventID());
            assertNotNull(testEvent);
            assertEquals(E_ALMA_BIRTH, testEvent, "Event not saving into database correctly");
            Event testEvent2 = eDao.getSingleEvent(E_ALMA_BIRTH.getEventID());
            assertNotNull(testEvent2);
            assertEquals(E_ALMA_BIRTH, testEvent2, "Event not saving into database correctly");
            System.out.println("Event added successfully");

        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Bad Event Test")
    public void addBadEvent(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        eDao.insert(E_ALMA_BIRTH);
        assertThrows(DataAccessException.class, ()-> eDao.insert(E_ALMA_BIRTH));
    }

    @Test
    @DisplayName("Find Event Test")
    public void findEvent(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            Event testEvent = eDao.getSingleEvent(E_ALMA_BIRTH.getEventID());
            assertNull(testEvent);
            eDao.insert(E_ALMA_BIRTH);
            testEvent = eDao.getSingleEvent(E_ALMA_BIRTH.getEventID());
            assertNotNull(testEvent);
            assertEquals(E_ALMA_BIRTH, testEvent, "Event not being found correctly");
            System.out.println("Event found successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName ("Find Bad Event Test")
    public void findBadEvent(TestInfo testInfo) throws DataAccessException {
        assertDoesNotThrow(()->eDao.getSingleEvent(E_ALMA_BIRTH.getEventID()));
    }

    @Test
    @DisplayName("Clear events")
    public void clearEvents(TestInfo testInfo) throws DataAccessException {
        eDao.insert(E_ALMA_BIRTH);
        Event testEvent = eDao.getSingleEvent(E_ALMA_BIRTH.getEventID());
        assertNotNull(testEvent);
        eDao.removeAllUserEvents(ALMA.getUsername());
        testEvent = eDao.getSingleEvent(ALMA.getUsername());
        assertNull(testEvent);
    }


    @Test
    @DisplayName("Get all events")
    public void getAllEvents(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA_BIRTH);
            eDao.insert(E_ALMA_DEATH);

            ArrayList<Event> modelEvents = new ArrayList<>();
            modelEvents.add(E_ALMA_BIRTH);
            modelEvents.add(E_ALMA_DEATH);

            ArrayList<Event> testEvents = eDao.getAllEvents(ALMA.getUsername());
            assertNotNull(testEvents);
            assertEquals(modelEvents, testEvents, "Events incorrectly accessed");

            System.out.println("All Events accessed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Remove event from user")
    public void removeEventFromUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA_BIRTH);
            eDao.insert(E_ALMA_DEATH);

            ArrayList<Event> testEvents = eDao.getAllEvents(ALMA.getUsername());
            assertNotNull(testEvents);
            eDao.removeAllUserEvents(ALMA.getUsername());
            ArrayList<Event> testEvents2 = eDao.getAllEvents(ALMA.getUsername());
            assertNull(testEvents2);

            System.out.println("All Events accessed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Prints the test name
     *
     * @param testInfo The name of the test
     */
    private void printTestName(TestInfo testInfo) {
        if (displayCurrentTest) System.out.println("Running " + testInfo.getDisplayName() + "...");
        logger.info("Running " + testInfo.getDisplayName() + "...");
    }
}

