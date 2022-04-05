package wallet;

/**
 *
 * @author Group 4
 */
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class WalletManagement {

    private int numberOfAmount; // Number of amounts that store in data file 
    private String W_FILE; // The URL of data file that stores all money
    LinkedList<IncomeSpending> linkedList; // All instance of income and spending
    //initialize width for table output
    private int widthNo = 5;
    private int widthDate = 20;
    private int widthMoney = 18;

    /**
     * Update width for table if have width greater than current width
     *
     * @param widthDate
     * @param widthMoney
     */
    public void updateWidth(String widthDate, String widthMoney) {
        this.widthDate = Math.max(this.widthDate, widthDate.length());
        this.widthMoney = Math.max(this.widthMoney, widthMoney.length());
    }
    //initialize template for table output
    String template = " ";
    String barline = "";
    String template1 = " ";

    /**
     * update that print to screen
     */
    public void updateTemplate() {
        String col1 = "";
        for (int i = 0; i < widthNo; i++) {
            col1 += "-";
        }
        String col2 = "";
        for (int i = 0; i < widthDate; i++) {
            col2 += "-";
        }
        String col3 = "";
        for (int i = 0; i < widthMoney + 10; i++) {
            col3 += "-";
        }
        barline = "+" + col1 + "+" + col1 + "+" + col2 + "+" + col3 + "+" + col2 + "+";
        template1 = "| %-" + (widthNo - 2) + "s | %-" + (widthNo - 2) + "s |   %-" + (widthDate - 5) + "s  |  %-" + (widthMoney - 4+10) + "s  |   %-" + (widthDate - 5) + "s  |\n";
        template = "| %-" + (widthNo - 2) + "s | %-" + (widthNo - 2) + "s |   %-" + (widthDate - 5) + "s  |  %-" + (widthMoney - 5+10) + "sVND|   %-" + (widthDate - 5) + "s  |\n";

    }

    /**
     * Create instance for wallet management
     *
     * @param w_FILE
     */
    public WalletManagement(String w_FILE) {
        this.numberOfAmount = 0;
        W_FILE = w_FILE;
        this.linkedList = new LinkedList<IncomeSpending>();
    }

    /**
     * Loads data of wallet from data file and stored it into Linked List
     *
     * @throws IOException
     * @throws IncomeSpendingException
     */
    public void loadFile() throws IOException, IncomeSpendingException {
        File file = new File(W_FILE);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("The data file wallet.txt is not exits! "
                    + "Creating new data file wallet.txt... Done!");
        } else {
            System.out.print("The data file " + W_FILE + " is found."
                    + " Data of wallet  loading...");

            try (BufferedReader bf = new BufferedReader(new FileReader(W_FILE))) {
                this.numberOfAmount = Integer.valueOf(bf.readLine());
                for (int i = 0; i < this.numberOfAmount; i++) {
                    String date, money, reason, id;
                    id = bf.readLine();
                    date = bf.readLine().toLowerCase().trim();
                    reason = bf.readLine().toLowerCase().trim();
                    money = bf.readLine();
                    updateWidth(reason, money);
                    linkedList.add(new IncomeSpending(Integer.valueOf(id), date, reason, Integer.valueOf(money)));
                }
            }
            System.out.println(" Done! [" + this.numberOfAmount + " amounts]");
        }
    }

    /**
     * Save wallet bank ( LinkedList) into data file
     *
     * @throws IOException
     */
    public void saveFile() throws IOException {
        // Overwrite data file
        FileWriter fw = new FileWriter(W_FILE);
        try {
            // Writes number of wallet management
            System.out.print("\nThe wallet is saving into data file " + W_FILE);
            fw.append(String.valueOf(this.numberOfAmount) + "\n");
            for (int i = 0; i < this.numberOfAmount; i++) {
                // Writes wallet's information into data file
                fw.append(String.valueOf(linkedList.get(i).getId()) + "\n");
                fw.append(linkedList.get(i).getDate() + "\n");
                fw.append(linkedList.get(i).getReason() + "\n");
                fw.append(String.valueOf(linkedList.get(i).getMoney()) + "\n");
            }
        } finally {
            fw.close(); // Save data file ( from RAM into HDD)
            System.out.println(" Done! [" + this.numberOfAmount + " amounts]");
        }
    }

    /**
     * Add new wallet to wallet bank
     *
     * @param date
     * @param reason
     * @param money
     * @return
     * @throws IncomeSpendingException
     */
    public int addWallet(String date, String reason, long money) throws IncomeSpendingException {
        int id = 0, count = 0;
        for (int j = 1;; ++j) {
            boolean valid = true;
            for (int i = 0; i < this.linkedList.size(); i++) {
                IncomeSpending a = this.linkedList.get(i);
                if (a.getId() == j) {
                    valid = false;
                } else if (a.getDate().equals(date) && a.getMoney() == money
                        && a.getReason().equals(reason)) {
                    count++;
                }
            }
            if (valid == true) {
                id = j;
                updateWidth(reason, String.valueOf(money));
                this.linkedList.add(new IncomeSpending(id, date, reason, money));
                break;
            } else if (count != 0) {
                break;
            }
        }
        return ++this.numberOfAmount;
    }

    /**
     * Print all wallet in table
     */
    public void PrintFullTable() {
        updateTemplate();
        int No = 1;
        System.out.println(barline);
        System.out.printf(template1, "No", "ID", "Date", "Money", "Reason");
        System.out.println(barline);
        for (int i = 0; i < this.linkedList.size(); i++) {
            NumberFormat formatter = new DecimalFormat("###,###,###,###.##");
            String t = formatter.format(this.linkedList.get(i).getMoney());
            System.out.printf(template, No, this.linkedList.get(i).getId(), this.linkedList.get(i).getDate(), t,
                    this.linkedList.get(i).getReason());
            No++;
        }
        System.out.println(barline);
    }

    /**
     * Update wallet
     *
     * @param reason
     * @param money
     * @param t
     * @throws IncomeSpendingException
     */
    public void UpdateMoney(String reason, long money, int t) throws IncomeSpendingException {
        IncomeSpending updateMoney = this.linkedList.get(t);
        updateWidth(reason, String.valueOf(money));
        updateMoney.setReason(reason);
        updateMoney.setMoney(money);

    }

    /**
     * Find date the same with date parameter
     *
     * @param date
     * @return
     */
    public ArrayList<Integer> CheckMoney(String date) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.linkedList.size(); i++) {
            if (this.linkedList.get(i).getDate().equalsIgnoreCase(date)) {
                result.add(i);
            }
        }
        return result;
    }

//    /**
//     * Find 
//     * @param a
//     * @param money
//     * @param reason
//     * @throws IncomeSpendingException
//     */
//    public void FindIncome(ArrayList<Integer> a,long money,String reason) throws IncomeSpendingException {
//        for (int i = 0; i < a.size(); i++) {
//            if (this.linkedList.get(a.get(i)).getMoney() == money){
//                this.linkedList.get(a.get(i)).setMoney(money);
//                this.linkedList.get(a.get(i)).setReason(reason);
//            }
//        }
//        PrintFullTable();
//    }
    /**
     * Show table by arraylist parameter
     *
     * @param a
     */
    public void ShowTable(ArrayList<Integer> a) {
        updateTemplate();
        int No = 1;
        System.out.println(barline);
        System.out.printf(template1, "No", "ID", "Date", "Money", "Reason");
        System.out.println(barline);
        for (int i = 0; i < a.size(); i++) {
            NumberFormat formatter = new DecimalFormat("###,###,###,###.##");
            String t = formatter.format(this.linkedList.get(a.get(i)).getMoney());
            System.out.printf(template, No, this.linkedList.get(a.get(i)).getId(), this.linkedList.get(a.get(i)).getDate(), t,
                    this.linkedList.get(a.get(i)).getReason());
            No++;
        }
        System.out.println(barline);
    }

    /**
     * Remove wallet by ID
     *
     * @param ID
     */
    public void removeIncome(int ID) {
        for (int i = 0; i < this.linkedList.size(); i++) {
            if (this.linkedList.get(i).getId() == ID) {
                this.linkedList.remove(i);
                --this.numberOfAmount;
                break;
            }
        }
    }

    /**
     * Gets all money current
     *
     * @return
     */
    public long getTotalMoney() {
        long totalMoney = 0;
        for (int i = 0; i < this.linkedList.size(); i++) {
            totalMoney += this.linkedList.get(i).getMoney();
        }
        return totalMoney;
    }

    /**
     * Show table follow date ( from begin date to end date ) of user input And
     * print total current money and total money from begin date to end date
     *
     * @param begin
     * @param end
     * @throws ParseException
     */
    public void showTableDate(String begin, String end) throws ParseException {
        ArrayList<Integer> index = new ArrayList<>();
        long totalIncome = 0;
        long totalSpending = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(begin);
        Date date2 = sdf.parse(end);
        for (int i = 0; i < this.linkedList.size(); i++) {
            Date date3 = sdf.parse(this.linkedList.get(i).getDate());
            if (date3.after(date1) && (date3.before(date2)) || (date1.equals(date3) || date2.equals(date3))) {
                index.add(i);
                if (this.linkedList.get(i).getMoney() < 0) {
                    totalSpending += this.linkedList.get(i).getMoney();
                } else {
                    totalIncome += this.linkedList.get(i).getMoney();
                }
            }
        }
        if (index.size() == 0) {
            System.out.println("    From the begin data " + begin + " to " + end + " is not found!");
        } else {
            NumberFormat formatter = new DecimalFormat("###,###,###,###.##");
            String t1 = formatter.format(totalIncome);
            String t2 = formatter.format(totalSpending);
            String t3 = formatter.format(getTotalMoney());
            System.out.println("The total income    : " + t1 + " VND");
            System.out.println("The total spending  : " + t2 + " VND");
            System.out.println("The total assets    : " + t3 + " VND");
            ShowTable(index);
        }
    }
}
