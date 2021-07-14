package personalTests.dao;

import client.Client;
import logs.InitLogs;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;

import java.sql.Connection;
import java.util.logging.Logger;

public class UserDaoTest {

    private static final Logger logger;

    static {
        InitLogs.init();
        logger = Logger.getLogger(Client.class.getName());
    }

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final User NEPHI = new User("nephi_younger", "nephi", "visions", "nephi@wilderness.org", "Nephi", "Younger", "m");
    private Database db;
    private UserDao uDao;
    private AuthTokenDao aDao;

    private static boolean displayCurrentTest = true;

    public static void setDisplayCurrentTest(boolean displayCurrentTest) {
        UserDaoTest.displayCurrentTest = displayCurrentTest;
    }

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        db = new Database();

        Connection conn = db.getConnection();

        db.clearAllTables();

        uDao = new UserDao(conn);
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
