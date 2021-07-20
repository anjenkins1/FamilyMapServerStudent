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

public class PersonDaoTest {

    private static final Logger logger;

    static {
        InitLogs.init();
        logger = Logger.getLogger(Client.class.getName());
    }

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final User NEPHI = new User("nephi_younger", "nephi", "visions", "nephi@wilderness.org", "Nephi", "Younger", "m");
    private static final Person P_NEPHI = new Person("nephi_younger", "alma", "Nephi", "Younger", "m");
    private static final Person P_ALMA = new Person("alma_chief_judge", "alma", "Alma", "ChiefJudge", "m");
    private static final AuthToken A_ALMA = new AuthToken("alma", "gi231pc");

    private Database db;
    private PersonDao pDao;
    private AuthTokenDao aDao;

    private static boolean displayCurrentTest = true;

    public static void setDisplayCurrentTest(boolean displayCurrentTest) {
        PersonDaoTest.displayCurrentTest = displayCurrentTest;
    }

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        db = new Database();

        Connection conn = db.getConnection();

        db.clearAllTables();

        pDao = new PersonDao(conn);
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


    @Test
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

            ArrayList<Person> testPersons = pDao.getAllPeople(A_ALMA.getAssociatedUsername());
            assertNotNull(testPersons);
            assertEquals(modelPersons, testPersons, "Persons incorrectly accessed");

            System.out.println("All Persons accessed successfully");
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
            ArrayList<Person> testPerson = pDao.getAllPeople(A_ALMA.getAssociatedUsername());
            assertNotNull(testPerson);
            pDao.removeAllUserPersons(ALMA.getUsername());
            testPerson = pDao.getAllPeople(A_ALMA.getAuthtoken());
            assertNull(testPerson);

            System.out.println("All Persons removed successfully");
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

