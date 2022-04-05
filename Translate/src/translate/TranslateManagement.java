    package translate;

import java.io.*;
import java.util.*;

/**
 *
 * @author Thai Vo Quoc
 */
public class TranslateManagement {

    private int numberOfVocabulary; // Number of comic book that store in data file
    Hashtable<String, String> TranslateBank;  // All instance of vocabulary
    private String T_FILE; // The URL of data file that stores all vocabulary
    Set<String> stringSetKey = new HashSet<>(); // Use store key
    ArrayList<String> stringArrayListValue = new ArrayList<>(); // Use store value
    //initialize width for table output
    private int widthVietnamese = 10;
    private int widthNo = 3;
    //initialize template for table output
    private String template = "";
    private String barLine = "";
    private String template1 = "";
    private String barLine1 = "";

    /**
     * Update width for table if have width greater than current width
     *
     * @param vietnamese
     */
    public void updateWidth(String vietnamese) {
        widthVietnamese = Math.max(widthVietnamese, vietnamese.length());
    }

    /**
     * update that print to screen
     */
    public void updateTemplate() {
        String col1 = "";
        for (int i = 0; i < widthVietnamese + 4; i++) {
            col1 += "-";
        }
        String col2 = "";
        for (int i = 0; i < widthNo + 3; i++) {
            col2 += "-";
        }
        barLine1 = "+" + col2 + "+" + col1 + "+" + col1 + "+" + col1 + "+";
        barLine = "+" + col2 + "+" + col1 + "+" + col1 + "+";
        template = "| %" + (widthNo + 1) + "s | %-" + (widthVietnamese + 2) + "s | %-" + (widthVietnamese + 2) + "s |\n";
        template1 = "| %" + (widthNo + 1) + "s | %-" + (widthVietnamese + 2) + "s | %-" + (widthVietnamese + 2) + "s | %-" + (widthVietnamese + 2) + "s |\n";
    }

    /**
     * Show all vocabulary following table format
     */
    public void showTableFull() {
        int No = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "No.", "English", "Vietnamese");
        System.out.println(barLine);
        Set<String> keySet = this.TranslateBank.keySet();
        for (String key : keySet) {
            No++;
            System.out.printf(template, No + "", key, this.TranslateBank.get(key));
        }
        System.out.println(barLine);

    }

    /**
     * Show all vocabulary following table format by HashTable
     *
     * @param hashtable
     */
    public void showTableFull(Hashtable<String, String> hashtable) {
        int No = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "No.", "English", "Vietnamese");
        System.out.println(barLine);
        Set<String> keySet = hashtable.keySet();
        for (String key : keySet) {
            No++;
            System.out.printf(template, No + "", key, hashtable.get(key));
        }
        System.out.println(barLine);

    }

    /**
     * Show all vocabulary following table format by set and arraylist
     *
     * @param stringSet
     * @param test
     */
    public void showTableFull(Set<String> stringSet, ArrayList<String> test) {
        int No = 0;
        updateTemplate();
        System.out.println(barLine1);
        System.out.printf(template1, "No.", "English", "Vietnamese", "Wrong Answer");
        System.out.println(barLine1);
        int i = 0;
        for (String key : stringSet) {
            No++;
            System.out.printf(template1, No + "", key, this.TranslateBank.get(key), test.get(i));
            i++;
        }
        System.out.println(barLine1);

    }

    /**
     * Create instance for vocabulary management
     *
     * @param T_FILE
     */
    public TranslateManagement(String T_FILE) {
        this.T_FILE = T_FILE;
        this.numberOfVocabulary = 0;
        this.TranslateBank = new Hashtable<String, String>();
    }

    /**
     * Loads data of vocabulary from data file and stored it into HashTable
     *
     * @throws IOException
     */
    public void uploadFile() throws IOException {
        File ft = new File(T_FILE);
        if (!ft.exists()) {
            ft.createNewFile();
            System.out.println("The data file TranslateEV.txt is not exits! "
                    + "Creating new data file TranslateEV.txt... Done.");
            this.numberOfVocabulary = 0;
        } else {
            System.out.print("The data file " + T_FILE + " is found. "
                    + "Data of vocabulary loading...");
            try (BufferedReader br = new BufferedReader(new FileReader(T_FILE))) {
                String vietNamese, english;
                this.numberOfVocabulary = Integer.valueOf(br.readLine());
                for (int i = 0; i < this.numberOfVocabulary; i++) {
                    english = br.readLine().trim().toLowerCase();
                    vietNamese = br.readLine().trim().toLowerCase();
                    updateWidth(english);
                    this.stringSetKey.add(english);
                    TranslateBank.put(english, vietNamese);
                }
            }
            System.out.println(" Done! [" + this.numberOfVocabulary + " vocabulary]");
        }
    }

    /**
     * Save vocabulary bank ( HashTable) into data file
     *
     * @throws IOException
     */
    public void saveFile() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(T_FILE));
        try {
            System.out.print("\n Vocabulary is saving into data file " + T_FILE + "...");
            // Writes number of vocabulary
            fileWriter.append(String.valueOf(this.numberOfVocabulary) + "\n");
            Set<String> keySet = this.TranslateBank.keySet();
            for (String key : keySet) {
                // Writes vocabulary's information into data file
                fileWriter.append(key + "\n");
                fileWriter.append(this.TranslateBank.get(key) + "\n");
            }
        } finally {
            // Save data file ( from RAM into HDD)// Save data file ( from RAM into HDD)
            fileWriter.close();
            System.out.println(" Done! [" + this.numberOfVocabulary + " vocabulary]");
        }
    }

    /**
     * Find word english in Hashtable
     *
     * @param english
     */
    public void FindEnglish(String english) {
        Set<String> keySet = this.TranslateBank.keySet();
        Hashtable<String, String> result = new Hashtable<>();
        for (String key : keySet) {
            if (this.TranslateBank.containsKey(english)) {
                result.put(english, this.TranslateBank.get(english));
                break;
            } else if (key.toLowerCase().contains(english.toLowerCase())) {
                result.put(key, this.TranslateBank.get(key));
            }
        }
        if (result.size() == 0) {
            System.out.println("Can't found " + english);
        } else {
            showTableFull(result);
        }
    }

    /**
     * Find word vietNamese in Hashtable
     *
     * @param vietNamese
     */
    public void FindVietName(String vietNamese) {
        Set<String> keySet = this.TranslateBank.keySet();
        Hashtable<String, String> result = new Hashtable<>();
        for (String key : keySet) {
//            if (this.TranslateBank.containsValue(vietNamese)) {
//                String t = CheckVietnamese(vietNamese);
//                result.put(t,vietNamese);
//                break;
//            } else 
            if (this.TranslateBank.get(key).toLowerCase().contains(vietNamese.toLowerCase())) {
                result.put(key, this.TranslateBank.get(key));
            }
        }
        if (result.size() == 0) {
            System.out.println("Can't found " + vietNamese);
        } else {
            showTableFull(result);
        }
    }

    /**
     * Check key has equal with parameter english from user
     *
     * @param english
     * @return
     */
    public boolean checkEnglish(String english) {
        Set<String> keySet = this.TranslateBank.keySet();
        for (String key : keySet) {
            if (key.equalsIgnoreCase(english)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add new a vocabulary in HashTable
     *
     * @param english
     * @param vietNamese
     * @return
     * @throws TranslateException
     */
    public int addVocabulary(String english, String vietNamese) throws TranslateException {
        this.stringSetKey.add(english);
        Translate translate = new Translate(vietNamese.toLowerCase(), english.toLowerCase());
        updateWidth(english);
        this.TranslateBank.put(translate.getEnglish(), translate.getVietNamese());
        return ++this.numberOfVocabulary;
    }

    /**
     * Copy data from Set into ArrayList that key and index
     *
     * @return
     */
    public ArrayList<String> shuffer() {

        for (String a : this.stringSetKey) {
            this.stringArrayListValue.add(a);
        }
        return this.stringArrayListValue;
    }

    /**
     * check answer from user with answer of bank quiz
     *
     * @param ans
     * @param test
     * @return
     */
    public boolean checkAns(String ans, String test) {
        if (ans.equalsIgnoreCase(test)) {
            return true;
        }
        return false;
    }
    Set<String> wrongAns1 = new HashSet<>();
    ArrayList<String> ans = new ArrayList<>();

    /**
     * Store answer of quiz and answer of user
     *
     * @param wrong
     * @param answerWrong
     */
    public void wrongAns(String wrong, String answerWrong) {
        this.wrongAns1.add(wrong);
        updateWidth(answerWrong);
        this.ans.add(answerWrong);
    }

    public String CheckVietnamese(String vietNamese) {
        Set<String> keySet = this.TranslateBank.keySet();
        for (String key : keySet) {
            if (this.TranslateBank.get(key).equalsIgnoreCase(vietNamese)) {
                return key;
            }
        }
        return null;
    }
}
