package data_generation;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class RandomItemSelector {

    public RandomItemSelector() {

    }

    public String getRandomName(String jsonFilePath) throws IOException {
        Random rand = new Random();
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Gson gson = new Gson();
            Names names = gson.fromJson(bufferedReader, Names.class);

            int index = rand.nextInt(names.getNames().size());
            System.out.println(names.getNames().get(index));
            return names.getNames().get(index);

        }
    }

    public Location getRandomLocation(String jsonFilePath) throws IOException {
        Random rand = new Random();
        try (FileReader fileReader = new FileReader(jsonFilePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Gson gson = new Gson();
            LocationArray locations = gson.fromJson(bufferedReader, LocationArray.class);

            int index = rand.nextInt(locations.getLocations().size());
            System.out.println(locations.getLocations().get(index));
            return locations.getLocations().get(index);

        }
    }

    private class LocationArray {
        private ArrayList<Location> data = new ArrayList<>();

        /**
         * Gets the value of locations
         *
         * @return locations
         */
        public ArrayList<Location> getLocations() {
            return data;
        }

        /**
         * Sets the locations - You can use getLocations() to get the value of locations
         *
         * @param locations variable to be set
         */
        public void setLocations(ArrayList<Location> locations) {
            this.data = locations;
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
