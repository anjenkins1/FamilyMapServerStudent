package data_generation;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RandomItemGenerator {

    private static final String CHARACTERS = "1234567890abcdefghijklmnopqrstuvwqyz";
    private static final String FEMALE_NAME_FILE = "json/fnames.json";
    private static final String MALE_NAME_FILE = "json/mnames.json";
    private static final String SURNAMES_NAME_FILE = "json/snames.json";
    private static final String LOCATIONS_FILE = "json/locations.json";

    private ArrayList<String> alreadyUsedIDs;

    public RandomItemGenerator() {
        alreadyUsedIDs = new ArrayList<>();
    }

    public RandomItemGenerator(ArrayList<String> alreadyUsedIDs) {
        this.alreadyUsedIDs = alreadyUsedIDs;
    }

    public String getRandomFemaleName() {
        try {
            return getRandomName(FEMALE_NAME_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRandomMaleName() {
        try {
            return getRandomName(MALE_NAME_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRandomSurname() {
        try {
            return getRandomName(SURNAMES_NAME_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a random name from the specified name.json file
     * @param jsonFilePath
     * @return String
     * @throws IOException
     */
    public String getRandomName(String jsonFilePath) throws IOException {
        Random rand = new Random();
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Gson gson = new Gson();
            Names names = gson.fromJson(bufferedReader, Names.class);

            int index = rand.nextInt(names.getNames().size());
            return names.getNames().get(index);

        }
    }

    /**
     * Gets a random <code>Location</code> object from the specified locations.json file
     * @param jsonFilePath
     * @return - <code>Location</code>
     * @throws IOException
     */
    public Location getRandomLocation(String jsonFilePath) {
        Random rand = new Random();
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Gson gson = new Gson();
            LocationArray locations = gson.fromJson(bufferedReader, LocationArray.class);

            int index = rand.nextInt(locations.getData().size());
            return locations.getData().get(index);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Location getRandomLocation() {
        return getRandomLocation(LOCATIONS_FILE);
    }

    public int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String getRandomID() {
/*        Random random = new Random();

        char[] text = new char[8];
        for (int i = 0; i < 8; i++) {
            text[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        }*/
        String output = UUID.randomUUID().toString();

        if (alreadyUsedIDs.contains(output)) {
            getRandomID();
        }
        else {
            alreadyUsedIDs.add(output);
            return output;
        }

        return null;
    }

    /**
     * Gets the value of alreadyUsedIDs
     *
     * @return alreadyUsedIDs
     */
    public ArrayList<String> getAlreadyUsedIDs() {
        return alreadyUsedIDs;
    }

    /**
     * Sets the alreadyUsedIDs - You can use getAlreadyUsedIDs() to get the value of alreadyUsedIDs
     *
     * @param alreadyUsedIDs variable to be set
     */
    public void setAlreadyUsedIDs(ArrayList<String> alreadyUsedIDs) {
        this.alreadyUsedIDs = alreadyUsedIDs;
    }

    private class LocationArray {
        private ArrayList<Location> data = new ArrayList<>();

        /**
         * Gets the value of data
         *
         * @return data
         */
        public ArrayList<Location> getData() {
            return data;
        }

        /**
         * Sets the data - You can use getData() to get the value of data
         *
         * @param data variable to be set
         */
        public void setData(ArrayList<Location> data) {
            this.data = data;
        }
    }

    private class Names {
        private ArrayList<String> data = new ArrayList<>();

        /**
         * Gets the value of names
         *
         * @return names
         */
        public ArrayList<String> getNames() {
            return data;
        }

        /**
         * Sets the names - You can use getNames() to get the value of names
         *
         * @param names variable to be set
         */
        public void setNames(ArrayList<String> names) {
            this.data = names;
        }
    }
}
