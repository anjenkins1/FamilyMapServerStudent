package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AuthToken;
import model.Event;
import model.Person;
import model.User;

public class PersonDao {

    private Connection databaseConn;

    /**
     * <code>PersonDao</code> must be constructed with an opened database connection
     * @param conn
     */
    public PersonDao(Connection conn) {
        databaseConn = conn;
    }

    /**
     * Adds new person to the database
     * @param person - person to be added
     * @throws DataAccessException - catches SQL errors
     */
    public void insert (Person person) throws DataAccessException {
        String sql = "INSERT INTO Person (person_id, associated_username, first_name, last_name, gender, father_id, mother_id, spouse_id)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to register user");
        }
    }

    /**
     * Return the single Person object with the specified ID
     * @param personID - <code>String</code> specified ID
     * @return - <code>Person</code>
     */
    public Person getSinglePerson (String personID) throws DataAccessException {
        Person returnPerson;
        ResultSet rs = null;

        String sql = "SELECT * FROM Person WHERE person_id = '" + personID + "'";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            if (rs.next()) {
                returnPerson = new Person(rs.getString("person_id"), rs.getString("associated_username"),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"),
                        rs.getString("father_id"), rs.getString("mother_id"), rs.getString("spouse_id"));
                return returnPerson;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Clears all persons
     * @throws DataAccessException
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Person";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to delete all persons");
        }
    }

    /**
     * Returns ALL family members of the current user.
     * @param auth_token - user <code>String</code> auth token to get the current user
     * @return <code>List<Person></code>
     */
    public ArrayList<Person> getAllPeopleAuth (String auth_token) throws DataAccessException {

        ArrayList<Person> people = new ArrayList<>();
        AuthTokenDao aDao = new AuthTokenDao(databaseConn);
        Person returnPerson;
        ResultSet rs = null;

        try {
            AuthToken auth = aDao.getUserName(auth_token);

            if (auth != null) {
                String sql = "SELECT * FROM Person WHERE associated_username = '" + auth.getAssociatedUsername() + "'";
                try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        returnPerson = new Person(rs.getString("person_id"), rs.getString("associated_username"),
                                rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"),
                                rs.getString("father_id"), rs.getString("mother_id"), rs.getString("spouse_id"));
                        people.add(returnPerson);
                    }
                    if (people.isEmpty()) {
                        return null;
                    }
                    return people;
                } catch(SQLException e) {
                    e.printStackTrace();
                    throw new DataAccessException("Unable to access specified Person");
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new DataAccessException("No authentication token associated with that user");
            }
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("No authentication token associated with that user");
        }
    }

    public ArrayList<Person> getAllPeople (String username) throws DataAccessException {

        ArrayList<Person> people = new ArrayList<>();
        Person returnPerson;
        ResultSet rs = null;

        String sql = "SELECT * FROM Person WHERE associated_username = '" + username + "'";
        try (PreparedStatement stmt = databaseConn.prepareStatement(sql)) {

            rs = stmt.executeQuery();

            while (rs.next()) {
                returnPerson = new Person(rs.getString("person_id"), rs.getString("associated_username"),
                        rs.getString("first_name"), rs.getString("last_name"), rs.getString("gender"),
                        rs.getString("father_id"), rs.getString("mother_id"), rs.getString("spouse_id"));
                people.add(returnPerson);
            }
            if (people.isEmpty()) {
                return null;
            }
            return people;
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to access specified Person");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Removes all people from specified user
     * @param username - user to be removed
     * @throws DataAccessException
     */
    public void removeAllUserPersons (String username) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE associated_username = ?";

        try(PreparedStatement stmt = databaseConn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to delete all persons from user");
        }
    }
}
