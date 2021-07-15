package services.results;

public class Result {

    /**
     * Message stored in the result for success or failure
     */
    public String message;

    /**
     * Stores whether or not the action was successful
     */
    public boolean success;

    public Result(){};

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * Gets the value of message
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message - You can use getMessage() to get the value of message
     *
     * @param message variable to be set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the value of success
     *
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success - You can use getSuccess() to get the value of success
     *
     * @param success variable to be set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}


