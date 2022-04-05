package quizmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class QuestionException extends Exception {

    /**
     * Create new QuestionException
     *
     * @param message
     */
    public QuestionException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param message
     */
    @Override
    public String getMessage() {
        return "QuestionException: " + super.getMessage();
    }
}
