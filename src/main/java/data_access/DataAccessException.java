package data_access;

import javax.xml.crypto.Data;

public class DataAccessException extends Exception {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException() {
        super();
    }
}
