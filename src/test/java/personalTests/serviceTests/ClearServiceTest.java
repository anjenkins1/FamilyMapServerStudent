package personalTests.serviceTests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;
import services.*;
import services.request.RegisterRequest;
import services.results.RegisterResult;


public class ClearServiceTest {

    private static final User ALMA = new User("alma_chief_judge", "alma", "visions", "alma@cityofzarahemla.org", "Alma", "ChiefJudge", "m");
    private static final RegisterRequest ALMA_REQUEST = new RegisterRequest(ALMA);

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    @DisplayName("Test Valid Clear")
    public void testClear() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        assertNotNull(result);
        assertEquals(ALMA.getUsername(), result.getUsername(), "Users not equal");

        ClearService clearService = new ClearService();
        clearService.clear();

        RegisterService registerService2 = new RegisterService();

        RegisterResult result2 = registerService2.register(ALMA_REQUEST);

        assertNotNull(result2);
        assertEquals(ALMA.getUsername(), result2.getUsername(), "Users not equal");

    }
}

