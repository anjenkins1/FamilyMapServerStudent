package results;

public class FillResult extends Result {

    /**
     * Constructs FillResult object with provided message and success boolean
     * @param message - message detailing result of action
     * @param success - true if the action was successful
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public FillResult() {

    }
}
