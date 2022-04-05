
package quizmanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class QuizManagement is used to manage quiz
 *
 * @author Thai Vo Quoc CE160568
 */
public class QuizManagement {

    private static QuestionManagement qm;
    private static AnswerManagement am;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            am = new AnswerManagement("answer.txt");
            am.loadAnswer(); // Loads answer bank
            qm = new QuestionManagement("question.txt", am);
            qm.loadQuestion(); // Loads question bank
            Scanner sc = new Scanner(System.in); // Create a scanner
            int choice; // the function that selected by user
            do {
                // Show menu
                System.out.println("\n--------QUIZ MANAGEMENT--------");
                System.out.println("1. Add question.");
                System.out.println("2. Show question bank.");
                System.out.println("3. Create quiz.");
                System.out.println("4. Quit.");
                // Gets function that selected by user
                System.out.print("    Please enter a function: ");
                choice = sc.nextInt();
                sc.nextLine();
                String strUserEntered = "";
                switch (choice) {
                    case 1:
                        System.out.println("-------QUIZ MANAGEMENT [ADD NEW QUESTION]-------");
                        String qContent = "";
                        double qMark = 0.0;
                        // Gets the content of question
                        do {
                            System.out.print("Please enter content of question: ");
                            qContent = sc.nextLine().trim();
                            if (qContent.equals("")) {
                                System.out.println("Error: Question content can't be empty");
                            }
                        } while (qContent.equals(""));
                        // Gets the mark question
                        do {
                            System.out.print("Please enter mark of question: ");
                            qMark = sc.nextDouble();
                            sc.nextLine();
                            if (qMark < 0 || qMark > 10) {
                                System.out.println("Error: Question mark must be from 0 to 10");
                            }
                        } while (qMark < 0 || qMark > 10);
                        // Creates new question and get it's id
                        int qID = qm.addQuestion(qMark, qContent);
                        System.out.println("Your question is created!");
                        System.out.println("+++[ADD ANSWER FOR QUESTION]+++");
                        // Adds answer for this question
                        int aNo = 0;
                        do {
                            aNo++;
                            System.out.println("... Answer " + aNo + " ...");
                            String aContent = "";
                            boolean aStatus = false;
                            // Gets the content of answer
                            do {
                                System.out.print("Please enter content of answer " + aNo
                                        + ": ");
                                aContent = sc.nextLine();
                                if (aContent.equals("")) {
                                    System.out.println("Error: Answer content can't"
                                            + " be empty!");
                                }
                            } while (aContent.equals(""));
                            // Gets the status of answer
                            do {
                                System.out.print("Is this answer True or False? (True/False): ");
                                strUserEntered = sc.nextLine().trim();
                                if (strUserEntered.equals("True")) {
                                    aStatus = true;
                                } else if (strUserEntered.equals("False")) {
                                    aStatus = false;
                                } else {
                                    System.out.println("Error: You must type 'True' or 'False'!");
                                }
                            } while (!((strUserEntered.equals("True")) || (strUserEntered.equals("False"))));
                            am.addAnswer(aContent, aStatus, qID); // Creates new answer
                            do {
                                System.out.print("Do you want to more answer? (Yes/No): ");
                                strUserEntered = sc.nextLine().trim();
                                if (!(strUserEntered.equals("Yes") || strUserEntered.equals("No"))) {
                                    System.out.println("Error: You must type 'Yes' or 'No'");
                                }
                            } while (!(strUserEntered.equals("Yes") || strUserEntered.equals("No")));
                        } while ((strUserEntered.equals("Yes")));
                        break;
                    case 2:
                        System.out.println("----- QUIZ MANAGEMENT [QUESTION BANK] ("
                                + qm.getSize() + "questions) -----");
                        qm.showQuestionBank();
                        break;
                    case 3:
                        // examination
                        int totalQuestionNumbers = qm.getSize();
                        int qNumber = 0;
                        boolean isRandom = false;
                        double mark = 0.0;
                        double totalMark = 0.0;
                        int correctCount = 0;
                        System.out.println("----- QUIZ MANAGEMENT [EXAMINATION] (" + totalQuestionNumbers
                                + " questions) -----");
                        // Gets number of question the test
                        do {
                            System.out.print("How many question of the test ");
                            qNumber = sc.nextInt();
                            sc.nextLine();
                            if (qNumber < 1 || totalQuestionNumbers < qNumber) {
                                System.out.println("Number of question must be from 1 to "
                                        + totalQuestionNumbers);
                            }
                        } while (qNumber < 1 || totalQuestionNumbers < qNumber);
                        // Turn on/off the random mode
                        do {
                            System.out.print("Do you want to shuffle the test? (True/False): ");
                            strUserEntered = sc.nextLine();
                            if (strUserEntered.equals("True")) {
                                isRandom = true;
                            } else if (strUserEntered.equals("False")) {
                                isRandom = false;
                            } else {
                                System.out.println("Error: You must type 'True' or 'False'");
                            }
                        } while (!(strUserEntered.equals("True") || strUserEntered.equals("False")));
                        // Generates the test
                        System.out.println("+++ The test is generating...");
                        ArrayList<Question> qList = qm.getQuestions(qNumber, isRandom);
                        System.out.println("Done! +++");
                        System.out.println("\n###########################");
                        System.out.println("#       TESTING         #");
                        ArrayList<Answer> aList;
                        Question q;
                        int qNo = 1;
                        char ans,
                         last;
                        for (int i = 0; i < qList.size(); i++) {
                            q = qList.get(i);
                            qID = q.getqID();
                            aList = am.getAnswers(qID, isRandom);
                            System.out.println("#######################");
                            System.out.println(qNo + ". " + qm.showQuestion(qID, aList));
                            qNo++;
                            do {
                                // Gets the answer of user
                                System.out.print("   >>> Please select answer: ");
                                ans = (sc.nextLine()).charAt(0);
                                last = (char) ('a' + aList.size() - 1);
                                if (ans < 'a' || last < ans) {
                                    System.out.println("Error: You answer must be from 'a' to"
                                            + +last + " !");
                                } else {
                                    aList.get(ans - 'a').setaSelected(true);
                                }
                            } while (ans < 'a' || last < ans);

                            boolean isUserCorrect = qm.isQuestionCorrect(qID, aList);

                            totalMark += q.getqMark();

                            if (isUserCorrect) {
                                mark += q.getqMark();
                                correctCount++;
                                System.out.println("    +++ Congratulation! You answer is CORRECT");
                            } else {
                                System.out.println("    --- So sad! Your answer is WRONG!!!");
                            }
                            sc.nextLine();
                        }

                        System.out.println("++++++++++++++++++++++++++++++");
                        System.out.println("You are FINISH!!!");
                        System.out.println("Correct rate is" + correctCount
                                + "/" + qList.size() + " ("
                                + String.format("%.2f", ((double) correctCount * 100 / qList.size())) + "%)");
                        System.out.println("Total mark is " + String.format("%.2f", mark)
                                + "/" + String.format("%.2f", totalMark) + " ("
                                + String.format("%.2f", ((double) mark * 100 / totalMark)) + "%) ");
                        sc.nextLine();
                        break;

                    case 4:
                        System.out.println("\n------------------------------------------");
                        System.out.println("Thank you for using our software!\n"
                                + "See you again!");
                        break;
                    default:
                        System.out.println("Error: The function must be from 1 to 4!");
                }
            } while (choice != 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                am.saveAnswer(); // save answers
            } catch (Exception e) {
                System.out.println("Exception: Can't save answers!");
            }
            try {
                qm.saveQuestion();// save questions
            } catch (Exception e) {
                System.out.println("Exception: Can't save questions!");
            }
        }
    }
}
