package wallet;

/**
 *
 * @author Group 4
 */
public class IncomeSpendingException extends Exception {

    /**
     * Creates new IncomeSpendingException
     *
     * @param message
     */
    public IncomeSpendingException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param
     */
    @Override
    public String getMessage() {
        return "Exception: " + super.getMessage();
    }
}
