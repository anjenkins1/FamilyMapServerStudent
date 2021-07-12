package services.results;

import model.Person;

import java.util.ArrayList;

public class AllPersonsResult extends Result {

    /**
     * Adds person list to message and makes success true
     * @param personList
     */
    public AllPersonsResult(ArrayList<Person> personList) {
        this.success = true;
    }

    /**
     * Error message is added to message and success is made false
     * @param message
     */
    public AllPersonsResult(String message) {
        this.success = false;
    }
}
