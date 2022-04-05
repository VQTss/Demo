   
package quizmanagement;

/**
 * The class QuestionManagement is used to manage question bank
 *
 * @author Vo Quoc Thai CE160568
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManagement {

    private String Q_FILE; // The URL of the data file that stores all question
    private int numberOfQuestion; // Number of question that stored in data file
    private ArrayList<Question> questions; // All instance of question
    private AnswerManagement am; // Instance of AnswerManagement

    /**
     * Creates instance for question management
     *
     * @param Q_FILE
     * @param am
     * @throws QuestionException
     */ 
    public QuestionManagement(String Q_FILE, AnswerManagement am) throws QuestionException {
        if (Q_FILE.equals("")) {
            throw new QuestionException("The URL of question data file can't be empty");
        } else {
            this.Q_FILE = Q_FILE; // Inits the URL of data file thats stores question bank
            this.numberOfQuestion = 0; // The number of question is 0
            this.questions = new ArrayList<Question>(); // Create empty question bank
            this.am = am; // Inits the answer management
        }
    }

    /**
     * Loads data of questions from data file and stored it into ArrayList
     *
     * @throws QuestionException
     * @throws IOException
     */
    public void loadQuestion() throws QuestionException, IOException {
        File qFile = new File(Q_FILE);
        // Checks is file created
        if (!qFile.exists()) {
            // If not, creates new file
            qFile.createNewFile();
            System.out.println("The data file question.txt is not exits."
                    + " Creating new data file question.txt");
        } else {
            // If file existed, so loading this data file
            System.out.print("\n The data file question.txt is found."
                    + " Data of question is loading...");
            BufferedReader br = new BufferedReader(new FileReader(Q_FILE));
            try {
                String qID, qContent, qMark;
                this.numberOfQuestion = Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfQuestion; i++) {
                    // Reads answer's information
                    qID = br.readLine();
                    qContent = br.readLine();
                    qMark = br.readLine();
                    // Create new instance of Answer and adds to answer bank
                    this.questions.add(new Question(Integer.parseInt(qID),
                            Double.parseDouble(qMark), qContent));
                }
            } finally {
                br.close();
            }
            System.out.println("Done! [" + this.numberOfQuestion + " question]");
        }
    }

    /**
     * Get number of questions
     *
     * @return
     */
    public int getSize() {
        return this.numberOfQuestion;
    }

    /**
     * Add new question to question bank
     *
     * @param qMark
     * @param qContent
     * @return
     * @throws QuestionException
     */
    public int addQuestion(double qMark, String qContent) throws QuestionException {
        this.questions.add(new Question(++this.numberOfQuestion, qMark, qContent));
        return this.numberOfQuestion;
    }

    /**
     * Finds question by question id and return the index of this question
     *
     * @param qID
     * @return
     */
    public int findQuestion(int qID) {
        for (int i = 0; i < this.numberOfQuestion; i++) {
            Question q = this.questions.get(i);
            if (q.getqID() == qID) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the question instance by question id
     *
     * @param qID
     * @return
     */
    public Question getQuestion(int qID) {
        int idx = this.findQuestion(qID);
        if (idx == -1) {
            return null;
        } else {
            return this.questions.get(idx);
        }
    }

    /**
     * Saves question bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveQuestion() throws IOException {
        // Overwrite data file
        FileWriter fw = new FileWriter(new File(Q_FILE), false);
        try {
            System.out.print("\nQuestion is saving into data file question.txt...");
            fw.append(String.valueOf(this.numberOfQuestion) + "\n");
            for (int i = 0; i < this.numberOfQuestion; i++) {
                int qID = this.questions.get(i).getqID();
                String qContent = this.questions.get(i).getqContent();
                double qMark = this.questions.get(i).getqMark();
                fw.append(qID + "\n");
                fw.append(qContent + "\n");
                fw.append(String.valueOf(qMark) + "\n");
            }
        } finally {
            fw.close();
            System.out.println("Done! [" + this.numberOfQuestion + " question]");
        }
    }

    /**
     * Checks that the user's answer is correct or incorrect
     *
     * @param qID
     * @param answers
     * @return
     */
    public boolean isQuestionCorrect(int qID, ArrayList<Answer> answers) {
        boolean isCorrect = true;
        for (int i = 0; i < answers.size(); i++) {
            // The answer of user is correct even if the user's selected is the
            // same with answer's status
            isCorrect = isCorrect && answers.get(i).isCorrect();
        }
        return isCorrect;
    }

    /**
     * gets the question formatted string that includes question content and all
     * answer that comes with random mode
     *
     * @param qID
     * @param isShuffle
     * @return
     */
    public String showQuestion(int qID, boolean isShuffle) {
        Question q = getQuestion(qID);
        ArrayList<Answer> aList = am.getAnswers(qID, isShuffle);
        String str = "";
        str += q.toString();
        char aNo = 'a';
        for (int i = 0; i < aList.size(); i++, aNo++) {
            str += " " + aNo + ". " + aList.get(i).toString();
        }
        return str;
    }

    /**
     * gets the question formatted string that includes question content and all
     * answer that comes with a list of answer
     *
     * @param qID
     * @param aList
     * @return
     */
    public String showQuestion(int qID, ArrayList<Answer> aList) {
        Question q = getQuestion(qID);
        String str = "";
        str += q.toString();
        char aNo = 'a';
        for (int i = 0; i < aList.size(); i++, aNo++) {
            str += " " + aNo + ". " + aList.get(i).toString();
        }
        return str;
    }

    /**
     * Display all question of question bank
     */
    public void showQuestionBank() {
        int qNo = 1;
        for (int i = 0; i < this.questions.size(); i++) {
            Question q = this.questions.get(i);
            String t = showQuestion(q.getqID(), false);
            System.out.println(qNo + ". " + t);
            qNo++;
        }
    }

    /**
     * Gets the first qNumber of question bank
     *
     * @param qNumber
     * @param isShuffle
     * @return
     */
    public ArrayList<Question> getQuestions(int qNumber, boolean isShuffle) {
        ArrayList<Question> qList = new ArrayList<Question>();
        // Inits the index of all answer
        int[] idx = new int[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            idx[i] = i;
        }
        if (isShuffle) {
            int newIdx, tmp;
            Random random = new Random();
            // Randomizes indexes of answer bank
            for (int i = 0; i < questions.size(); i++) {
                newIdx = random.nextInt(questions.size());
                tmp = idx[i];
                idx[i] = idx[newIdx];
                idx[newIdx] = tmp;
            }
        }
        for (int i = 0; i < qNumber; i++) {
            qList.add(questions.get(idx[i]));
        }
        return qList;
    }

}
