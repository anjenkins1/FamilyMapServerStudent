package services.results;

import model.Event;

import java.util.ArrayList;

public class AllEventResult extends Result {
    /**
     * Adds person list to message and makes success true
     * @param personList
     */
    public AllEventResult(ArrayList<Event> personList) {
        this.success = true;
    }

    /**
     * Error message is added to message and success is made false
     * @param message
     */
    public AllEventResult(String message) {
        this.success = false;
    }
}
