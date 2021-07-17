package services;

import data_generation.RandomItemGenerator;
import model.*;
import data_access.*;

import java.sql.Connection;

abstract class Service {

    protected Database database;
    protected String message;
    protected boolean success;
    protected RandomItemGenerator generator;
    protected Connection connection;

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

}
