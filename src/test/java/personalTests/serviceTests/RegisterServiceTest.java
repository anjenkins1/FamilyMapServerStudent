package personalTests.serviceTests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;
import services.*;
import request.RegisterRequest;
import results.RegisterResult;


public class RegisterServiceTest {

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final RegisterRequest ALMA_REQUEST = new RegisterRequest(ALMA);

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    @DisplayName("Test Valid Registration")
    public void testValidRegistration() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        assertNotNull(result);
        assertEquals(ALMA.getUsername(), result.getUsername(), "Users not equal");
    }

    @Test
    @DisplayName("Test Invalid Registration")
    public void testInvalidRegistration() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        RegisterService registerService2 = new RegisterService();

        RegisterResult result2 = registerService2.register(ALMA_REQUEST);

        assertNotNull(result2);
        assertFalse(result2.success, "Result not false");
        assertTrue(result2.getMessage().toLowerCase().contains("error"), "Error not found in return result");
    }

}

