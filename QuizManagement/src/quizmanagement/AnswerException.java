package quizmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class AnswerException extends Exception {

    /**
     * create new AnswerException
     *
     * @param message
     */
    public AnswerException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param message
     */
    @Override
    public String getMessage() {
        return "AnswerException: " + super.getMessage();
    }
}
