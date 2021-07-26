package services;

import data_generation.RandomItemGenerator;
import model.*;
import data_access.*;
import results.Result;

import java.sql.Connection;
import java.util.ArrayList;

abstract class Service {

    protected Database database;
    protected String message;
    protected boolean success;
    protected RandomItemGenerator generator;
    protected Connection connection;
    protected String authorizedUser;

    public Service() {
        database = new Database();
        generator = new RandomItemGenerator();

        try {
            connection = database.openConnection();
        } catch (DataAccessException e) {
            e.printStackTrace();
            message = "Error: Unable to access database";
            success = false;
        }

    }

    public Result checkAuthToken(String authToken) {
        Result result = new Result();
        AuthTokenDao authDao = new AuthTokenDao(connection);
        try {
            AuthToken token = authDao.getUserName(authToken);
            if (token != null) {
                authorizedUser = token.getAssociatedUsername();
                result.setSuccess(true);
            }
            else {
                result.setMessage("Error: Invalid Auth Token");
                result.setSuccess(false);
                closeDataStream(false, result);
            }
            return result;
        } catch(DataAccessException e) {
            e.printStackTrace();
            closeDataStream(false, result);
            return result;
        }
    }

    protected String verifyAuthToken(AuthTokenDao authDao, String authToken) throws DataAccessException {
        AuthToken token = authDao.getUserName(authToken);
        if (token != null) {
            return token.getAssociatedUsername();
        }
        else {
            return null;
        }
    }

    protected void closeDataStream(boolean commit, Result result) {
        try {
            database.closeConnection(commit);
        } catch (DataAccessException e) {
            result.setMessage("Error: " + e.toString());
            result.setSuccess(false);
        }
    }

    protected void addPersonsToDatabase(PersonDao personDao, ArrayList<Person> persons) throws DataAccessException {
        for (Person p : persons) {
            personDao.insert(p);
        }
    }

    protected void addEventsToDataBase(EventDao eventDao, ArrayList<Event> events) throws DataAccessException {
        for (Event e : events) {
            eventDao.insert(e);
        }
    }


}
