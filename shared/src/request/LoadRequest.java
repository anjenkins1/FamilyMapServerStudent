package request;

import model.Event;
import model.Person;
import model.User;

import java.util.ArrayList;

public class LoadRequest {

    /**
     * Lists of data to be loaded into the database
     */
    private ArrayList<User> users;
    private ArrayList<Person> persons;
    private ArrayList<Event> events;

    public LoadRequest() {}

    /**
     * Constructor to create LoadRequest object
     * @param users
     * @param persons
     * @param events
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Gets the value of users
     *
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets the users - You can use getUsers() to get the value of users
     *
     * @param users variable to be set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
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
     * Sets the persons - You can use getData() to get the value of persons
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
     * Sets the events - You can use getData() to get the value of events
     *
     * @param events variable to be set
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "LoadRequest{" +
                "users=" + users.toString() +
                ", persons=" + persons.toString() +
                ", events=" + events.toString() +
                '}';
    }
}
