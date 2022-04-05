package quizmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class Question {

    private int qID; // Question ID
    private double qMark; // Question mark
    private String qContent; // Question content

    /**
     * Gets the id of question
     *
     * @return
     */
    public int getqID() {
        return qID;
    }

    /**
     * Sets id for question
     *
     * @param qID
     * @throws QuestionException
     */
    public void setqID(int qID) throws QuestionException {
        if (qID <= 0) {
            throw new QuestionException("Question ID must be a positive integer");
        } else {
            this.qID = qID;
        }
    }

    /**
     * Gets the mark of question
     *
     * @return
     */
    public double getqMark() {
        return qMark;
    }

    /**
     * Sets mark for question
     *
     * @param qMark
     * @throws QuestionException
     */
    public void setqMark(double qMark) throws QuestionException {
        if (qMark < 0 || qMark > 10) {
            throw new QuestionException("Question mark must be from 0 to 10");
        } else {
            this.qMark = qMark;
        }
    }

    /**
     * Gets the content of question
     *
     * @return
     */
    public String getqContent() {
        return qContent;
    }

    /**
     * Sets content for question
     *
     * @param qContent
     * @throws QuestionException
     */
    public void setqContent(String qContent) throws QuestionException {
        if (qContent.equals("")) {
            throw new QuestionException("Question content can't be empty");
        } else {
            this.qContent = qContent;
        }
    }

    /**
     * Creates new question
     *
     * @param qID
     * @param qMark
     * @param qContent
     * @throws QuestionException
     */
    public Question(int qID, double qMark, String qContent) throws QuestionException {
        this.setqID(qID);
        this.setqMark(qMark);
        this.setqContent(qContent);
    }

    /**
     * Convert question to string return
     */
    @Override
    public String toString() {
        return this.qContent + "\n";
    }
}
