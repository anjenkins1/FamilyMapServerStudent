package personalTests.serviceTests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;
import services.*;
import request.LoginRequest;
import request.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;


    public class LoginServiceTest {

        private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
        private static final RegisterRequest ALMA_REQUEST = new RegisterRequest(ALMA);

        @BeforeEach
        @DisplayName("Connecting to Database")
        public void setup() throws DataAccessException {
            ClearService clearService = new ClearService();
            clearService.clear();
        }

        @Test
        @DisplayName("Test Valid Login")
        public void testLogin() throws DataAccessException {
            RegisterService registerService = new RegisterService();

            RegisterResult result = registerService.register(ALMA_REQUEST);

            assertNotNull(result);
            assertEquals(ALMA.getUsername(), result.getUsername(), "Users not equal");

            LoginService loginService = new LoginService();
            LoginResult loginResult = loginService.login(new LoginRequest(ALMA.getUsername(), ALMA.getPassword()));

            assertNotNull(loginResult);
            assertTrue(loginResult.isSuccess());
            assertEquals(result.getAuthtoken(), loginResult.getAuthtoken());
            assertEquals(result.getPersonID(), loginResult.getPersonID());
        }

        @Test
        @DisplayName("Test Invalid Login")
        public void testInvalidLogin() throws DataAccessException {
            RegisterService registerService = new RegisterService();

            RegisterResult result = registerService.register(ALMA_REQUEST);

            assertNotNull(result);
            assertEquals(ALMA.getUsername(), result.getUsername(), "Users not equal");

            LoginService loginService = new LoginService();
            LoginResult loginResult = loginService.login(new LoginRequest(ALMA.getUsername(), "wrong password"));

            assertNotNull(loginResult);
            assertFalse(loginResult.isSuccess());
            assertTrue(loginResult.getMessage().toLowerCase().contains("error"), "Error not thrown");
        }
    }
