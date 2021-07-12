package services.request;

import model.Event;
import model.Person;
import model.User;

import java.util.ArrayList;

public class LoadRequest {

    /**
     * Lists of data to be loaded into the database
     */
    private ArrayList<User> userList;
    private ArrayList<Person> personList;
    private ArrayList<Event> eventList;

    /**
     * Constructor to create LoadRequest object
     * @param userList
     * @param personList
     * @param eventList
     */
    public LoadRequest(ArrayList<User> userList, ArrayList<Person> personList, ArrayList<Event> eventList) {
        this.userList = userList;
        this.personList = personList;
        this.eventList = eventList;
    }

    /**
     * Gets the value of userList
     *
     * @return userList
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Sets the userList - You can use getUserList() to get the value of userList
     *
     * @param userList variable to be set
     */
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    /**
     * Gets the value of personList
     *
     * @return personList
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Sets the personList - You can use getPersonList() to get the value of personList
     *
     * @param personList variable to be set
     */
    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    /**
     * Gets the value of eventList
     *
     * @return eventList
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }

    /**
     * Sets the eventList - You can use getEventList() to get the value of eventList
     *
     * @param eventList variable to be set
     */
    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }
}
