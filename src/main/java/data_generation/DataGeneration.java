package data_generation;

import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class DataGeneration {
    private ArrayList<Event> events;
    private ArrayList<Person> persons;
    private RandomItemGenerator generator;

    private final int MIN_MARRIAGE_AGE = 13;
    private final int MIN_PREGNANT_AGE = 13;
    private final int MAX_PREGNANT_AGE = 50;
    private final int MAX_DEATH_AGE = 120;

    public DataGeneration() {
        events = new ArrayList<>();
        persons = new ArrayList<>();
        generator = new RandomItemGenerator();
    }

    public void generateData(Person user, int numGenerations) {
            int currentYear = 2000 + generator.getRandomNumberInRange(-5,5);
            persons.add(user);
            events.add(generateBirth(user, currentYear));
            if (numGenerations > 0) {
                generateParents(user, numGenerations-1, currentYear, true);
            }

    }

    public void generateParents(Person currPerson, int generation, int currYear, boolean isUsersParents) {
        int parentalGap = currYear - generator.getRandomNumberInRange(20,40);

        Person mom = new Person();
        mom.setGender("f");
        mom.setFirstName(generator.getRandomFemaleName());
        mom.setPersonID(generator.getRandomID());
        mom.setAssociatedUsername(currPerson.getAssociatedUsername());
        mom.setLastName(currPerson.getLastName());

        Person dad = new Person();
        dad.setGender("m");
        dad.setPersonID(generator.getRandomID());
        dad.setAssociatedUsername(currPerson.getAssociatedUsername());
        dad.setFirstName(generator.getRandomMaleName());
        dad.setLastName(currPerson.getLastName());

        if (isUsersParents || currPerson.getGender() == "m") {
            dad.setLastName(currPerson.getLastName());
            mom.setLastName(currPerson.getLastName());
        }
        else if (currPerson.getGender() == "f") {
            String newLastName = generator.getRandomSurname();
            dad.setLastName(newLastName);
            mom.setLastName(newLastName);
        }

        dad.setSpouseID(mom.getPersonID());
        mom.setSpouseID(dad.getSpouseID());

        currPerson.setMotherID(mom.getPersonID());
        currPerson.setFatherID(dad.getPersonID());

        persons.add(mom);
        persons.add(dad);

        int momBirthYear = parentalGap + generator.getRandomNumberInRange(-5,5);
        int dadBirthYear = parentalGap + generator.getRandomNumberInRange(-5,5);

        int marriageYear = parentalGap - generator.getRandomNumberInRange(MIN_MARRIAGE_AGE, MAX_PREGNANT_AGE);

        Location marriageLocation = generator.getRandomLocation();

        events.add(generateBirth(mom, momBirthYear));
        events.add(generateDeath(mom, momBirthYear));

        events.add(generateBirth(dad, dadBirthYear));
        events.add(generateDeath(dad, dadBirthYear));

        events.add(generateMarriage(dad, marriageYear, marriageLocation));
        events.add(generateMarriage(mom, marriageYear, marriageLocation));

        if (generation > 0) {
            generateParents(mom, generation-1, parentalGap, false);
            generateParents(dad, generation-1, parentalGap, false);
        }
    }

    private Event generateDeath(Person currPerson, int birthYear) {
        int deathYear = birthYear - generator.getRandomNumberInRange(MAX_PREGNANT_AGE, MAX_DEATH_AGE);
        Event event = createNewEvent(currPerson.getAssociatedUsername(), currPerson.getPersonID());
        event.setEventType("death");
        event.setYear(deathYear);
        return addRandomLocation(event);
    }

    private Event generateBirth(Person currPerson, int birthYear) {
        Event event = createNewEvent(currPerson.getAssociatedUsername(), currPerson.getPersonID());
        event.setEventType("birth");
        event.setYear(birthYear);
        return addRandomLocation(event);
    }

    private Event generateMarriage(Person parent, int marriageYear, Location marriageLocation) {
        Event event = createNewEvent(parent.getAssociatedUsername(), parent.getPersonID());
        event.setEventType("marriage");
        event.setYear(marriageYear);
        event.setLatitude(marriageLocation.getLatitude());
        event.setLongitude(marriageLocation.getLongitude());
        event.setCountry(marriageLocation.getCountry());
        event.setCity(marriageLocation.getCity());
        return event;
    }

    private Event addRandomLocation(Event event) {
        Location randomLocation = generator.getRandomLocation("json/locations.json");
        event.setLatitude(randomLocation.getLatitude());
        event.setLongitude(randomLocation.getLongitude());
        event.setCountry(randomLocation.getCountry());
        event.setCity(randomLocation.getCity());
        return event;
    }

    private Event createNewEvent(String associated_username, String personID) {
        Event event = new Event();
        event.setAssociatedUsername(associated_username);
        event.setEventID(generator.getRandomID());
        event.setPersonID(personID);
        return event;
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
