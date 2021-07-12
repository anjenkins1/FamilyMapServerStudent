package services.results;

public class ClearResult extends Result {

    /**
     * Constructs ClearResult object with provided message and success boolean
     * @param message - message detailing result of action
     * @param success - true if the action was successful
     */
    public ClearResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
