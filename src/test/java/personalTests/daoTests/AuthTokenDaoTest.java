package personalTests.daoTests;

import client.Client;
import data_access.AuthTokenDao;
import data_access.DataAccessException;
import data_access.Database;
import data_access.EventDao;
import logs.InitLogs;
import model.AuthToken;
import model.Event;
import model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDaoTest {
    private static final Logger logger;

    static {
        InitLogs.init();
        logger = Logger.getLogger(Client.class.getName());
    }

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final AuthToken A_ALMA = new AuthToken("alma", "gi231pc");

    private Database db;
    private AuthTokenDao aDao;

    private static boolean displayCurrentTest = true;

    public static void setDisplayCurrentTest(boolean displayCurrentTest) {
        AuthTokenDaoTest.displayCurrentTest = displayCurrentTest;
    }

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        db = new Database();

        Connection conn = db.getConnection();

        db.clearAllTables();

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
    @DisplayName("Add AuthToken")
    public void addAuthToken(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            aDao.insert(A_ALMA);
            AuthToken testToken = aDao.getAuthToken(ALMA.getUsername());
            assertNotNull(testToken);
            assertEquals(A_ALMA, testToken, "Token not saving into database correctly");
            AuthToken testToken2 = aDao.getAuthToken(ALMA.getUsername());
            assertNotNull(testToken2);
            assertEquals(A_ALMA, testToken2, "Token not saving into database correctly");
            System.out.println("Token added successfully");

        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Bad AuthToken Test")
    public void addBadEvent(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        aDao.insert(A_ALMA);
        assertThrows(DataAccessException.class, ()-> aDao.insert(A_ALMA));
    }

    @Test
    @DisplayName("Find AuthToken Test")
    public void findAuthToken(TestInfo testInfo) throws DataAccessException {
        printTestName(testInfo);
        try {
            AuthToken testAuthToken = aDao.getAuthToken(ALMA.getUsername());
            assertNull(testAuthToken);
            aDao.insert(A_ALMA);
            testAuthToken = aDao.getAuthToken(ALMA.getUsername());
            assertNotNull(testAuthToken);
            assertEquals(A_ALMA, testAuthToken, "AuthToken not being found correctly");
            System.out.println("AuthToken found successfully");
        } catch (DataAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Clear AuthTokens")
    public void clearAuthTokens(TestInfo testInfo) throws DataAccessException {
        aDao.insert(A_ALMA);
        AuthToken authToken = aDao.getAuthToken(ALMA.getUsername());
        assertNotNull(authToken);
        aDao.clear();
        authToken = aDao.getAuthToken(ALMA.getUsername());
        assertNull(authToken);
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
