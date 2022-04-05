package fishmanagement;

/**
 *
 * @author Thai Vo Quoc CE160568
 */
public class FishException extends Exception {

    /**
     * Creates new FishException
     *
     * @param message
     */
    public FishException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @return
     */
    @Override
    public String getMessage() {
        return "FishException: " + super.getMessage();
    }

}
