
package quizmanagement;

/**
 * The class AnswerManagement is used to management answer bank
 *
 * @author Vo Quoc Thai CE160568
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author thaiq
 */
public class AnswerManagement {

    private String A_FILE; // The URL of data file that store all answers
    private int numberOfAnswer; // Number of answer that stored in data files
    private ArrayList<Answer> answers; // all instance of answer

    /**
     * Creates instance for answer management
     *
     * @param a_FILE
     * @throws AnswerException
     */
    public AnswerManagement(String A_FILE) throws AnswerException {
        if (A_FILE.equals("")) {
            throw new AnswerException("The URL of answer data file can't file empty!");
        } else {
            this.A_FILE = A_FILE;
            this.numberOfAnswer = 0;
            this.answers = new ArrayList<Answer>();
        }
    }

    /**
     * Loads data of answer from data file and store it into ArrayList
     *
     * @throws IOException
     * @throws AnswerException
     */
    public void loadAnswer() throws IOException, AnswerException {
        File aFile = new File(A_FILE);
        if (!aFile.exists()) {
            aFile.createNewFile(); // Check is file created
            System.out.printf("The data file answers.txt is not exits. "
                    + "Creating new data file answer.txt... Done!\n");
            this.numberOfAnswer = 0; // New data file with the number of answer is 0
        } else {
            // If file existed, so loading this data file
            System.out.printf("\nThe data file answer.txt is found"
                    + "Data of answer is loading...");
            // Load text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(A_FILE))) {
                String qID, aID, aContent, aStatus;
                this.numberOfAnswer = Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfAnswer; i++) {
                    // Read answer's information
                    aID = br.readLine();
                    aContent = br.readLine();
                    aStatus = br.readLine();
                    qID = br.readLine();
                    // Create new instance of Answer and adds to answer bank
                    this.answers.add(new Answer(Integer.parseInt(aID), aContent,
                            Boolean.parseBoolean(aStatus), Integer.parseInt(qID)));
                }
            }
            System.out.println("Done! [" + this.numberOfAnswer + " answer]");
        }
    }

    /**
     * Adds new answer to answer bank
     *
     * @param aContent
     * @param aStatus
     * @param qID
     * @return
     * @throws AnswerException
     */
    public int addAnswer(String aContent, boolean aStatus, int qID) throws AnswerException {
        this.answers.add(new Answer(++this.numberOfAnswer, aContent, aStatus, qID));
        return this.numberOfAnswer; // answer ID
    }

    /**
     * Find answer by answer id and return the index of this answer
     *
     * @param aID
     * @return
     */
    public int findAnswer(int aID) {
        for (int i = 0; i < this.answers.size(); i++) {
            Answer a = this.answers.get(i);
            if (a.getaID() == aID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the answer instance by answer id
     *
     * @param aID
     * @return
     */
    public Answer getAnswer(int aID) {
        int idx = this.findAnswer(aID);
        if (idx == -1) {
            return null;
        } else {
            return this.answers.get(idx);
        }
    }

    /**
     * Saves answer bank ( ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveAnswer() throws IOException {
        // Overwrite date file
        FileWriter fw = new FileWriter(new File(A_FILE), false);
        try {
            System.out.print("\nAnswer is saving into data file answer.txt...");
            // Writes number of answer
            fw.append(String.valueOf(this.numberOfAnswer) + "\n");
            for (int i = 0; i < this.numberOfAnswer; i++) {
                // Inits answer's information
                int aID = this.answers.get(i).getaID();
                String aContent = this.answers.get(i).getaContent();
                boolean aStatus = this.answers.get(i).getaStatus();
                int qID = this.answers.get(i).getqID();
                // Writes answer's information into data file
                fw.append(String.valueOf(aID) + "\n");
                fw.append(aContent + "\n");
                fw.append(String.valueOf(aStatus) + "\n");
                fw.append(String.valueOf(qID) + "\n");
            }
        } finally {
            // Save data file (RAM into HDD)
            fw.close();
            System.out.println("Done! [" + this.numberOfAnswer + " answer]");
        }
    }

    /**
     * Gets all answer that belong to question that identifies by question id
     *
     * @param qID
     * @param isShuffle
     * @return
     */
    public ArrayList<Answer> getAnswers(int qID, boolean isShuffle) {
        ArrayList<Answer> aList = new ArrayList<Answer>();
        for (int i = 0; i < this.numberOfAnswer; i++) {
            Answer a = this.answers.get(i);
            if (a.getqID() == qID) {
                aList.add(a);
            }
        }
        // Inits the index of all answer
        int[] idx = new int[aList.size()];
        for (int i = 0; i < aList.size(); i++) {
            idx[i] = i;
        }
        // if the random mode is turned on
        if (isShuffle) {
            int newIdx, tmp;
            Random ra = new Random();
            for (int i = 0; i < aList.size(); i++) {
                newIdx = ra.nextInt(aList.size());
                tmp = idx[i];
                idx[i] = idx[newIdx];
                idx[newIdx] = tmp;
            }
        }
        ArrayList<Answer> result = new ArrayList<Answer>();
        for (int i = 0; i < aList.size(); i++) {
            result.add(aList.get(idx[i]));
        }
        return result;
    }
}
