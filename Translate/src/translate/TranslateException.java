package translate;

/**
 *
 * @author Thai Vo Quoc
 */
public class TranslateException extends Exception {

    /**
     * Creates new TranslateException
     *
     * @param message
     */
    public TranslateException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param
     */
    @Override
    public String getMessage() {
        return "Exception:" + super.getMessage();
    }

}
