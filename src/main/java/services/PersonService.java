package services;

import data_access.DataAccessException;
import data_access.PersonDao;
import model.Person;
import services.results.AllPersonsResult;
import services.results.SinglePersonResult;

import java.util.ArrayList;

public class PersonService extends Service {

    /**
     * Data access objects
     */
    private PersonDao personDataAccess;

    public PersonService() {
        personDataAccess = new PersonDao(connection);
    };

    /**
     * Finds specified person based on given ID
     * @param personID
     * @return <code>SinglePersonResult</code>
     */
    public SinglePersonResult getOnePerson(String personID) {

        SinglePersonResult result = new SinglePersonResult();

        try {
            Person person = personDataAccess.getSinglePerson(personID);

            if (person == null || !person.getAssociatedUsername().equals(authorizedUser)) {
                result.setMessage("Error: Person not associated with that user");
                result.setSuccess(false);
                closeDataStream(false, result);
            }
            else {
                result.setAssociatedUsername(person.getAssociatedUsername());
                result.setPersonID(person.getPersonID());
                result.setFirstName(person.getFirstName());
                result.setLastName(person.getLastName());
                result.setMotherID(person.getMotherID());
                result.setFatherID(person.getFatherID());
                result.setSpouse(person.getSpouseID());
                result.setGender(person.getGender());
                result.setSuccess(true);
                closeDataStream(true, result);
            }
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            closeDataStream(false, result);
            return result;
        }

    }

    /**
     * Gives a list of all people in the database
     * @return <code>AllPersonsResult</code>
     */
    public AllPersonsResult getAllPeople() {
        AllPersonsResult result = new AllPersonsResult();
        ArrayList<Person> persons = new ArrayList<>();
        try {
            persons = personDataAccess.getAllPeople(authorizedUser);
            result.setData(persons);
            result.setSuccess(true);
            closeDataStream(true, result);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            closeDataStream(false, result);
            result.setSuccess(false);
            result.setMessage("Error: Unable to authenticate user");
            return result;
        }
    }
}
