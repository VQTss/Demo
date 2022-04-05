package storeSellFish;

/**
 *
 * @author Dpl
 */
public class StoreSellFishException extends Exception {

    /**
     * Create new store sell fish exception
     *
     * @param message
     */
    public StoreSellFishException(String message) {
        super(message);
    }

    /**
     * Get the exception message
     *
     * @return
     */
    public String getMessage() {
        return "StoreSellFishException: " + super.getMessage();
    }
}
