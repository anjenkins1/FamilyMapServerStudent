package results;

import model.Event;

import java.util.ArrayList;

public class AllEventResult extends Result {

    private ArrayList<Event> data;

    /**
     * Create new, empth <code>AllEventResult</code> Object
     */
    public AllEventResult() {
        super();
    }

    /**
     * Gets the value of data
     *
     * @return data
     */
    public ArrayList<Event> getData() {
        return this.data;
    }

    /**
     * Sets the data - You can use getData() to get the value of data
     *
     * @param data variable to be set
     */
    public void setData(ArrayList<Event> data) {
        this.data = data;
    }
}
