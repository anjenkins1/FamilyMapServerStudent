package data_generation;

import model.*;

import java.util.ArrayList;

public class BaseGenerator {

    private String username;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;
    private ArrayList<String> alreadyUsedIDs;

    private int numGenerations;
    private int currYear = 2000;

    public BaseGenerator(String username, int numGenerations, ArrayList<String> alreadyUsedIDs) {
        this.username = username;
        this.numGenerations = numGenerations;
        this.alreadyUsedIDs = alreadyUsedIDs;
    }

    private void startRoot() {

    }

    private void getMother(int currYear, int numGenerations) {

    }

    private void getFather(int currYear, int numGenerations) {

    }

    /**
     * Gets the value of persons
     *
     * @return persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Sets the persons - You can use getPersons() to get the value of persons
     *
     * @param persons variable to be set
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets the value of events
     *
     * @return events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events - You can use getEvents() to get the value of events
     *
     * @param events variable to be set
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
