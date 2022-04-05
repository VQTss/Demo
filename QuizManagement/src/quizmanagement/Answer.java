package quizmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class Answer {

    private int aID;  // Answer id
    private String aContent; //  Answer content
    private boolean aStatus; // Answer is correct or not
    private boolean aSelected; // Answer is select or not
    private int qID; // Question id that answer belongs to

    /**
     * Create new answer
     * @param aID
     * @param aContent
     * @param aStatus
     * @param qID
     * @throws AnswerException
     */
    public Answer(int aID, String aContent, boolean aStatus, int qID) throws AnswerException {
        this.setaID(aID);
        this.setaContent(aContent);
        this.setaStatus(aStatus);
        this.setaSelected(false);
        this.setqID(qID);
    }

    /**
     * Gets the id of answer
     *
     * @return
     */
    public int getaID() {
        return aID;
    }

    /**
     * Sets id for answer
     *
     * @param aID
     * @throws AnswerException
     */
    public void setaID(int aID) throws AnswerException {
        if (aID <= 0) {
            throw new AnswerException("Answer ID must be a positive number");
        } else {
            this.aID = aID;
        }
    }

    /**
     * Gets the content of answer
     *
     * @return
     */
    public String getaContent() {
        return aContent;
    }

    /**
     * Sets the content of answer
     *
     * @param aContent
     * @throws AnswerException
     */
    public void setaContent(String aContent) throws AnswerException {
        if (aContent.equals("")) {
            throw new AnswerException("Answer content can't be empty");
        }
        this.aContent = aContent;
    }

    /**
     * Checks the answer is true or false
     *
     * @return
     */
    public boolean getaStatus() {
        return aStatus;
    }

    /**
     * Sets status for answer
     *
     * @param aStatus
     */
    public void setaStatus(boolean aStatus) {
        this.aStatus = aStatus;
    }

    /**
     * Checks the answer is selected or not
     *
     * @return
     */
    public boolean isaSelected() {
        return aSelected;
    }

    /**
     * Selects or un-selects answer
     *
     * @param aSelected
     */
    public void setaSelected(boolean aSelected) {
        this.aSelected = aSelected;
    }

    /**
     * Checks if the answer if correct or not
     *
     * @return
     */
    public boolean isCorrect() {
        return this.aStatus == this.aSelected;
    }

    /**
     * Gets question id that answer is belongs to
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
     * @throws AnswerException
     */
    public void setqID(int qID) throws AnswerException {
        if (qID <= 0) {
            throw new AnswerException("Question ID must be a positive number");
        } else {
            this.qID = qID;
        }
    }

    @Override
    public String toString() {
        return this.aContent + "\n";
    }
}
