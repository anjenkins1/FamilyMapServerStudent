package services;

import model.*;
import data_access.*;

abstract class Service {

    protected Database database;

    public Service() {
        database = new Database();
    }

}
