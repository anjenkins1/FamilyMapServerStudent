package personalTests;

import client.Client;
import data_generation.RandomItemGenerator;
import logs.InitLogs;
import org.junit.jupiter.api.*;

import data_access.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PersonalTests {

    private static final Logger logger;

    static {
        InitLogs.init();
        logger = Logger.getLogger(Client.class.getName());
    }

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final User NEPHI = new User("nephi_younger", "nephi", "visions", "nephi@wilderness.org", "Nephi", "Younger", "m");
    private static final Person P_ALMA = new Person("alma_chief_judge", "alma", "Alma", "ChiefJudge", "m");
    private static final Person P_NEPHI = new Person("nephi_younger", "alma", "Nephi", "Younger", "m");
    private static final AuthToken A_ALMA = new AuthToken("alma", "gi231pc");
    private static final Event E_ALMA = new Event("alma", "death1", "alma_chief_judge", 3.21f, 5.43f, "america", "zarahemla", "death", -100);
    private static final Event E_ALMA2 = new Event("alma", "birth1", "alma_chief_judge", 3.21f, 5.43f, "america", "nephi", "birth", -180);
    private Database db;
    private UserDao uDao;
    private PersonDao pDao;
    private EventDao eDao;
    private AuthTokenDao aDao;

    private static boolean displayCurrentTest = true;

    public static void setDisplayCurrentTest(boolean displayCurrentTest) {
        PersonalTests.displayCurrentTest = displayCurrentTest;
    }

    @Test
    @DisplayName("Gson parser for names")
    public void gsonNameParser(TestInfo testInfo) {
        RandomItemGenerator item = new RandomItemGenerator();
        try {
            item.getRandomName("json/snames.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Gson parser for locations")
    public void gsonLocationParser(TestInfo testInfo) {
        RandomItemGenerator item = new RandomItemGenerator();
        item.getRandomLocation("json/locations.json");
    }

    @Test
    @DisplayName("Random ID generator")
    public void randomIDGenerator(TestInfo testInfo) {
        ArrayList<String> ids = new ArrayList<>();

        RandomItemGenerator items = new RandomItemGenerator(ids);

        for (int i = 0; i < 20; i++) {
            System.out.println(items.getRandomID());
        }

    }
/*
    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        db = new Database();

        Connection conn = db.getConnection();

        db.clearAllTables();

        uDao = new UserDao(conn);
        pDao = new PersonDao(conn);
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
    @DisplayName("Register User Test")
    public void registerUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            uDao.insert(ALMA);
            User testUser = uDao.find(ALMA.getUsername(), ALMA.getPassword());
            assertNotNull(testUser);
            assertEquals(ALMA, testUser, "User not saving correctly");
            System.out.println("User registered successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Register Bad User Test")
    public void registerBadUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        uDao.insert(ALMA);
        assertThrows(DataAccessException.class, ()-> uDao.insert(ALMA));
    }

    @Test
    @DisplayName("Find User Test")
    public void findUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            User testUser = uDao.find(ALMA.getUsername(), ALMA.getPassword());
            assertNull(testUser);
            uDao.insert(ALMA);
            testUser = uDao.find(ALMA.getUsername(), ALMA.getPassword());
            assertNotNull(testUser);
            assertEquals(ALMA, testUser, "User not being found correctly");
            System.out.println("User found successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName ("Find Bad User Test")
    public void findBadUser(TestInfo testInfo) throws DataAccessException {
        assertDoesNotThrow(()->uDao.find(NEPHI.getUsername(), NEPHI.getPassword()));
    }

    @Test
    @DisplayName("Clear users")
    public void clearUsers(TestInfo testInfo) throws DataAccessException {
        uDao.insert(ALMA);
        User testUser = uDao.find(ALMA.getUsername(), ALMA.getPassword());
        assertNotNull(testUser);
        uDao.clear();
        testUser = uDao.find(ALMA.getUsername(), ALMA.getPassword());
        assertNull(testUser);
    }

    @Test
    @DisplayName("Add Person")
    public void addPerson(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            pDao.insert(P_ALMA);
            Person testPerson = pDao.getSinglePerson(P_ALMA.getPersonID());
            assertNotNull(testPerson);
            assertEquals(P_ALMA, testPerson, "Person not saving into database correctly");
            Person testPerson2 = pDao.getSinglePerson(ALMA.getPersonID());
            assertNotNull(testPerson2);
            assertEquals(P_ALMA, testPerson2, "Person not saving into database correctly");
            System.out.println("Person added successfully");

        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Bad Person Test")
    public void addBadPerson(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        pDao.insert(P_ALMA);
        assertThrows(DataAccessException.class, ()-> pDao.insert(P_ALMA));
    }

    @Test
    @DisplayName("Find Person Test")
    public void findPerson(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            Person testPerson = pDao.getSinglePerson(P_ALMA.getPersonID());
            assertNull(testPerson);
            pDao.insert(P_ALMA);
            testPerson = pDao.getSinglePerson(P_ALMA.getPersonID());
            assertNotNull(testPerson);
            assertEquals(P_ALMA, testPerson, "Person not being found correctly");
            System.out.println("Person found successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName ("Find Bad Person Test")
    public void findBadPerson(TestInfo testInfo) throws DataAccessException {
        assertDoesNotThrow(()->pDao.getSinglePerson(P_ALMA.getPersonID()));
    }

    @Test
    @DisplayName("Clear persons")
    public void clearPersons(TestInfo testInfo) throws DataAccessException {
        pDao.insert(P_ALMA);
        Person testPerson = pDao.getSinglePerson(P_ALMA.getPersonID());
        assertNotNull(testPerson);
        pDao.clear();
        testPerson = pDao.getSinglePerson(P_ALMA.getPersonID());
        assertNull(testPerson);
    }
*/

/*
    @Test
    @DisplayName("Add Event")
    public void addEvent(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA);
            Event testEvent = eDao.getSingleEvent(E_ALMA.getEventID());
            assertNotNull(testEvent);
            assertEquals(E_ALMA, testEvent, "Event not saving into database correctly");
            System.out.println("Event added successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Bad Event Test")
    public void addBadEvent(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        eDao.insert(E_ALMA);
        assertThrows(DataAccessException.class, ()-> eDao.insert(E_ALMA));
    }
*/

/*    @Test
    @DisplayName("Get all events")
    public void getAllEvents(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            pDao.insert(P_ALMA);
            pDao.insert(P_NEPHI);

            aDao.insert(A_ALMA);
            ArrayList<Person> modelPersons = new ArrayList<>();
            modelPersons.add(P_ALMA);
            modelPersons.add(P_NEPHI);

            ArrayList<Person> testPersons = pDao.getAllPeople(A_ALMA.getAuthtoken());
            assertNotNull(testPersons);
            assertEquals(modelPersons, testPersons, "Persons incorrectly accessed");

            System.out.println("All Persons accessed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Remove events from user")
    public void removeEventsFromUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA);
            eDao.insert(E_ALMA2);
            aDao.insert(A_ALMA);
            ArrayList<Event> testEvents = eDao.getAllEvents(A_ALMA.getAuthtoken());
            assertNotNull(testEvents);
            eDao.removeAllUserEvents(ALMA.getUsername());
            testEvents = eDao.getAllEvents(A_ALMA.getAuthtoken());
            assertNull(testEvents);

            System.out.println("All Events removed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Get all people")
    public void getAllPersons(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            eDao.insert(E_ALMA);
            eDao.insert(E_ALMA2);

            aDao.insert(A_ALMA);
            ArrayList<Event> modelEvents = new ArrayList<>();
            modelEvents.add(E_ALMA);
            modelEvents.add(E_ALMA2);

            ArrayList<Event> testEvents = eDao.getAllEvents(A_ALMA.getAuthtoken());
            assertNotNull(testEvents);
            assertEquals(modelEvents, testEvents, "Events incorrectly accessed");

            System.out.println("All Events accessed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Remove people from user")
    public void removePersonsFromUser(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            pDao.insert(P_ALMA);
            pDao.insert(P_NEPHI);

            aDao.insert(A_ALMA);
            ArrayList<Person> testPerson = pDao.getAllPeople(A_ALMA.getAuthtoken());
            assertNotNull(testPerson);
            pDao.removeAllUserPersons(ALMA.getUsername());
            testPerson = pDao.getAllPeople(A_ALMA.getAuthtoken());
            assertNull(testPerson);

            System.out.println("All Persons removed successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }*/

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
