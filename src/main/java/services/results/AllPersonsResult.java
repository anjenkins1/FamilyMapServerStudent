package services.results;

import model.Person;

import java.util.ArrayList;

public class AllPersonsResult extends Result {

    private ArrayList<Person> data;

    /**
     * Adds person list to message and makes success true
     * @param personList
     */
    public AllPersonsResult() {
        //data = new ArrayList<>();
    }

    /**
     * Gets the value of data
     *
     * @return data
     */
    public ArrayList<Person> getData() {
        return data;
    }

    /**
     * Sets the data - You can use getData() to get the value of data
     *
     * @param data variable to be set
     */
    public void setData(ArrayList<Person> data) {
        this.data = data;
    }
}
