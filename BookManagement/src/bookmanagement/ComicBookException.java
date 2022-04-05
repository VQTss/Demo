package bookmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class ComicBookException extends Exception {

    /**
     * Creates new ComicBookException
     *
     * @param message
     */
    public ComicBookException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param
     */
    @Override
    public String getMessage() {
        return "ComicBookException: " + super.getMessage();
    }
}
