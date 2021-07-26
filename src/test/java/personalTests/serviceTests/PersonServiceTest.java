package personalTests.serviceTests;

import data_generation.RandomItemGenerator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import data_access.*;
import model.*;
import services.*;
import request.RegisterRequest;
import results.AllEventResult;
import results.AllPersonsResult;
import results.RegisterResult;
import results.SinglePersonResult;


public class PersonServiceTest {

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
    @DisplayName("Test Valid Person")
    public void testValidPerson() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        PersonService personService = new PersonService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        personService.checkAuthToken(result.getAuthtoken());

        SinglePersonResult person = personService.getOnePerson(result.getPersonID());

        assertNotNull(person);
        assertEquals(ALMA.getUsername(), person.getAssociatedUsername(), "Usernames not equal");
        assertEquals(result.getPersonID(), person.getPersonID(), "Person IDs not equal");

        assertTrue(person.isSuccess());
    }

    @Test
    @DisplayName("Test Invalid Person")
    public void testInvalidPerson() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        PersonService personService = new PersonService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        personService.checkAuthToken(result.getAuthtoken());

        SinglePersonResult person = personService.getOnePerson("Bad Person ID");

        assertNotNull(person);
        assertFalse(person.isSuccess(), "Result not false");
        assertTrue(person.getMessage().toLowerCase().contains("error"), "Error not found in return result");
    }

    @Test
    @DisplayName("Test Invalid AuthToken")
    public void testInvalidAuth_Token() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        PersonService personService = new PersonService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        assertFalse(personService.checkAuthToken("Bad Auth").isSuccess());

    }

    @Test
    @DisplayName("Test Valid Persons")
    public void testValidPersons() throws DataAccessException {
        RegisterService registerService = new RegisterService();

        PersonService personService = new PersonService();

        RegisterResult result = registerService.register(ALMA_REQUEST);

        personService.checkAuthToken(result.getAuthtoken());

        AllPersonsResult person = personService.getAllPeople();

        assertNotNull(person);
        assertTrue(person.isSuccess());
        assertNotNull(person.getData());
    }

}

