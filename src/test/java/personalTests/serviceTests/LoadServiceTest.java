package personalTests.serviceTests;

import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data_access.*;
import model.*;
import services.*;
import request.LoadRequest;
import request.RegisterRequest;
import results.LoadResult;
import results.RegisterResult;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class LoadServiceTest {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private LoadRequest loadRequest;

    @BeforeEach
    @DisplayName("Connecting to Database")
    public void setup() throws DataAccessException {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    @DisplayName("Test Valid Load")
    public void testLoad() throws DataAccessException {

        LoadService loadService = new LoadService();

        fillLoadRequest();

        LoadResult loadResult = loadService.load(loadRequest);

        assertNotNull(loadResult);
        assertTrue(loadResult.isSuccess());
        assertTrue(loadResult.getMessage().toLowerCase().contains("successfully"));

    }

    @Test
    @DisplayName("Test Bad Load")
    public void testBadLoad() throws DataAccessException {

        LoadService loadService = new LoadService();

        loadRequest = new LoadRequest();

        LoadResult loadResult = loadService.load(loadRequest);

        assertNotNull(loadResult);
        assertFalse(loadResult.isSuccess());
        assertFalse(loadResult.getMessage().toLowerCase().contains("successfully"));

    }


    private void fillLoadRequest() {

        try {
            JsonReader jsonReader = new JsonReader(new FileReader("passoffFiles/LoadData.json"));
            loadRequest = GSON.fromJson(jsonReader, LoadRequest.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

