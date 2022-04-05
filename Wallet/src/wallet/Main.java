package wallet;

/**
 *
 * @author Group 4
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {

    private static WalletManagement wm;
    private static boolean isValid = true;

    /**
     * Check date have to valid or not
     *
     * @param arrInput
     * @param arrCurrent
     * @param date
     * @param a
     */
    public static void checkDate(String[] arrInput, String[] arrCurrent, String date, int a) {
        int arrInput0 = Integer.valueOf(arrInput[0]);
        int arrInput1 = Integer.valueOf(arrInput[1]);
        int arrInput2 = Integer.valueOf(arrInput[2]);
        int arrCurrent0 = Integer.valueOf(arrCurrent[0]);
        int arrCurrent1 = Integer.valueOf(arrCurrent[1]);
        int arrCurrent2 = Integer.valueOf(arrCurrent[2]);
        if ((arrInput0 < arrCurrent0) || (arrInput0 == arrCurrent0 && arrInput1 <= arrCurrent1)) {
            if (arrInput0 == arrCurrent0 && arrInput1 == arrCurrent1 && arrInput2 > arrCurrent2) {
                System.out.println("    The date must be less current date " + date + "!");
                isValid = false;
            } else {
                if (arrInput1 < 0 || arrInput1 > 12) {
                    System.out.println("    The month must be from 1 to 12!");
                    isValid = false;
                } else {
                    if (arrInput1 == 4 || arrInput1 == 6 || arrInput1 == 9 || arrInput1 == 11) {
                        if (arrInput2 <= 30) {
                            isValid = true;
                        } else {
                            System.out.println("    The month " + arrInput1 + " have 30 day!");
                            System.out.println("    The day of " + arrInput1 + " must be greater than 0 and less than 30!");
                            isValid = false;
                        }
                    } else if (arrInput1 == 2) {
                        if (((arrInput0 % 4 == 0 && arrInput0 % 100 != 0) || arrInput0 % 400 == 0)) {
                            if (arrInput2 <= 29) {
                                isValid = true;
                            } else {
                                System.out.println("    The day must be greater than 0 and less than 20");
                                isValid = false;
                            }
                        } else {
                            if (arrInput2 <= 28) {
                                isValid = true;
                            } else {
                                System.out.println("    The day must be greater than 0 and less than 28");
                                isValid = false;
                            }
                        }
                    } else {
                        if (arrInput2 <= 31) {
                            isValid = true;
                        } else {
                            System.out.println("    The day must be greater than 0 and less than 31");
                            isValid = false;
                        }
                    }
                }
            }
        } else if (a == 0) {
            System.out.println("    The date must be less current date " + date + "!");
            isValid = false;
        } else {
            System.out.println("    The begin date must be greater than end date " + date + "!");
            isValid = false;
        }
    }

    /**
     * Check parameter ID from user have the same ID in ArrayList
     *
     * @param a
     * @param id
     * @return
     */
    public static int checkID(ArrayList<Integer> a, int id) {
        for (int i = 0; i < a.size(); i++) {
            if (wm.linkedList.get(a.get(i)).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            wm = new WalletManagement("src/data/wallet.txt"); // Loads wallet bank
            wm.loadFile();
            Scanner sc = new Scanner(System.in);  //  Creates new  a scanner
            int chose = 0; // The function that selected by user

            do {
                try {
                    System.out.println("------------Wallet Management------------");
                    System.out.println("1. Add new income or spending.");
                    System.out.println("2. Update income or spending.");
                    System.out.println("3. Remove income or spending.");
                    System.out.println("4. Statistics income and spending.");
                    System.out.println("5. Quit.");
                    System.out.print("  Please select a function: ");
                    chose = sc.nextInt();
                    sc.nextLine();
                    switch (chose) {
                        case 1:
                            System.out.println("------------Add [Income or spending]------------");
                            String dateFormat = "";
                            Date date = new Date();
                            SimpleDateFormat FormatDate = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate = FormatDate.format(date);
                            //Gets the date of wallet
                            do {
                                isValid = true;
                                System.out.print("Please enter date follow format yyyy-mm-dd: ");
                                dateFormat = sc.nextLine().trim();
                                if (dateFormat.equals("")) { //
                                    System.out.println("   The date can't be empty!");
                                    isValid = false;
                                } else if (!dateFormat.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    // Alert error if user input wrong data
                                    System.out.println("    The date must be follow format yyyy-mm-dd!");
                                    isValid = false;
                                } else {
                                    Pattern pattern = Pattern.compile("-");
                                    String[] arrCurrent = pattern.split(strDate);
                                    String[] arrInput = pattern.split(dateFormat);
                                    checkDate(arrInput, arrCurrent, strDate, 0);
                                }
                            } while (isValid == false);
                            //Gets the reason of wallet
                            String reason = "";
                            do {
                                isValid = true;
                                System.out.print("Please enter reason: ");
                                reason = sc.nextLine();
                                reason = reason.replaceAll("\\s\\s+", " ").trim().toLowerCase();
                                if (reason.equals("")) {
                                    System.out.println("    The reason can't be empty!");
                                    isValid = false;
                                }
                            } while (isValid == false);
                            long money = 0;
                            //Gets the money of wallet
                            do {
                                try {
                                    isValid = true;
                                    System.out.print("Please enter money you were "
                                            + "income (positive number) or spending (negative number): ");
                                    money = sc.nextLong();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    // Alert error if user input wrong data
                                    System.out.println("    The money must be a number!");
                                    isValid = false;
                                    sc.nextLine();
                                }
                            } while (isValid == false);
                            System.out.println("Created a income and spending success.");
                            // Add wallet for wallet
                            wm.addWallet(dateFormat, reason, money);
                            //  Show list wallet following table format
                            wm.PrintFullTable();
                            sc.nextLine();
                            isValid = false;
                            break;
                        case 2:
                            System.out.println("------------Update [Income or spending]------------");
                            String dateFormat2 = "";
                            Date date2 = new Date();
//                            wm.PrintFullTable();
                            SimpleDateFormat FormatDate2 = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate2 = FormatDate2.format(date2);
                            //Gets the date of wallet
                            do {
                                isValid = true;
                                System.out.print("Please enter date follow format yyyy-mm-dd: ");
                                dateFormat2 = sc.nextLine().trim();
                                if (dateFormat2.equals("")) { //
                                    System.out.println("   The date can't be empty!");
                                    isValid = false;
                                } else if (!dateFormat2.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    System.out.println("    The date must be follow format yyyy-mm-dd!");
                                    isValid = false;
                                } else {
                                    Pattern pattern = Pattern.compile("-");
                                    String[] arrCurrent = pattern.split(strDate2);
                                    String[] arrInput = pattern.split(dateFormat2);
                                    checkDate(arrInput, arrCurrent, strDate2, 0);
                                }
                            } while (isValid == false);
                            ArrayList<Integer> check2 = wm.CheckMoney(dateFormat2);
                            int checkid = 0;
                            int id = 0;
                            if (check2.size() == 0) {
                                System.out.println("Not found " + dateFormat2 + "!");
                                sc.nextLine();
                                isValid = false;
                                break;
                            } else {
                                wm.ShowTable(check2);

                                do {
                                    //Gets the ID of wallet
                                    try {
                                        isValid = true;
                                        System.out.print("Please enter ID you want to update in table: ");
                                        id = sc.nextInt();
                                        sc.nextLine();
                                        checkid = checkID(check2, id);
                                        if (checkid == -1) {
                                            System.out.println("    The ID must be in table!");
                                            isValid = false;
                                        } else if (id <= 0) {
                                            System.out.println("    The ID must be greater than 0!");
                                            isValid = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("    The ID must be a integer number!");
                                        isValid = false;
                                        sc.nextLine();
                                    }
                                } while (isValid == false);
                                System.out.println("Updated income and spending in " + dateFormat2 + " with ID is " + id);
                            }
                            String reason2 = "";
                            do {
                                //Gets the reason of wallet
                                isValid = true;
                                System.out.print("Please enter reason: ");
                                reason2 = sc.nextLine();
                                reason2 = reason2.replaceAll("\\s\\s+", " ").trim().toLowerCase();
                                if (reason2.equals("")) {
                                    System.out.println("    The reason can't be empty!");
                                    isValid = false;
                                }
                            } while (isValid == false);
                            long money2 = 0;
                            do {
                                //Gets the money of wallet
                                try {
                                    isValid = true;
                                    System.out.print("Please enter money you were "
                                            + "income (positive number) or spending (negative number): ");
                                    money2 = sc.nextLong();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("    The money must be a number!");
                                    isValid = false;
                                    sc.nextLine();
                                }
                            } while (isValid == false);
                            // Update wallet for wallet
                            System.out.println("Update a income and spending with ID" + id + " success");
                            wm.UpdateMoney(reason2, money2, check2.get(checkid));
                            //  Show list wallet following table format
                            wm.PrintFullTable();
                            sc.nextLine();
                            isValid = false;
                            break;
                        case 3:
                            System.out.println("------------Remove [Income or spending]------------");
                            String dateFormat3 = "";
                            Date date3 = new Date();
                            SimpleDateFormat FormatDate3 = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate3 = FormatDate3.format(date3);
                            do {
                                //Gets the date of wallet
                                isValid = true;
                                System.out.print("Please enter date follow format yyyy-mm-dd: ");
                                dateFormat3 = sc.nextLine().trim();
                                if (dateFormat3.equals("")) {
                                    System.out.println("   The date can't be empty!");
                                    isValid = false;
                                } else if (!dateFormat3.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                    System.out.println("    The date must be follow format yyyy-mm-dd!");
                                    isValid = false;
                                } else {
                                    Pattern pattern = Pattern.compile("-");
                                    String[] arrCurrent = pattern.split(strDate3);
                                    String[] arrInput = pattern.split(dateFormat3);
                                    checkDate(arrInput, arrCurrent, strDate3, 0);
                                }
                            } while (isValid == false);
                            ArrayList<Integer> check3 = wm.CheckMoney(dateFormat3);
                            int checkid3 = 0;
                            int id3 = 0;
                            String xacnhan = "";
                            int a = 0;
                            if (check3.size() == 0) {
                                System.out.println("Not found " + dateFormat3 + "!");
                                sc.nextLine();
                                isValid = false;
                                break;
                            } else {
                                wm.ShowTable(check3);
                                do {
                                    //Gets ID money of wallet
                                    try {
                                        isValid = true;
                                        System.out.print("Please enter ID you want to remove in table: ");
                                        id3 = sc.nextInt();
                                        sc.nextLine();
                                        checkid3 = checkID(check3, id3);
                                        if (checkid3 == -1) {
                                            System.out.println("    The ID must be in table!");
                                            isValid = false;
                                        } else if (id3 <= 0) {
                                            System.out.println("    The ID must be greater than 0!");
                                            isValid = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("    The ID must be a integer number!");
                                        isValid = false;
                                        sc.nextLine();
                                    }
                                    do {
                                        isValid = true;
                                        System.out.print("Do you want to remove income and spending? (Y/N): ");
                                        xacnhan = sc.nextLine().trim().toLowerCase();
                                        if (xacnhan.equals("")) {
                                            System.out.println("    Can't be empty!");
                                            isValid = false;
                                        } else if (xacnhan.equals("y") || xacnhan.equals("n")) {
//                                              isValid = true;
                                        } else {
                                            System.out.println("    Must be enter Y/N!");
                                            isValid = false;
                                        }
                                    } while (isValid == false);

                                } while (isValid == false);

                            }
                            if (xacnhan.equalsIgnoreCase("y")) {
                                System.out.println("Remove income and spending in " + dateFormat3 + " with ID is " + id3);
                                // Remove wallet for wallet
                                wm.removeIncome(id3);
                                //  Show list wallet following table format
                                System.out.println("Remove a income and spending success.");
                                wm.PrintFullTable();
                                sc.nextLine();
                            }
                            
                            isValid = false;
                            break;
                        case 4:
                            System.out.println("------------Statistics [Income or spending]------------");
                            String dateFormat4 = "";
                            Date date4 = new Date();
                            SimpleDateFormat FormatDate4 = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate4 = FormatDate4.format(date4);
                            String dateFormat5 = "";
                            Date date5 = new Date();
                            SimpleDateFormat FormatDate5 = new SimpleDateFormat("yyyy-MM-dd");
                            String strDate5 = FormatDate5.format(date5);
                            Pattern pattern1 = Pattern.compile("-");
                            do {
                                isValid = true;
                                do {
                                    // Gets the date begin of  wallet
                                    isValid = true;
                                    System.out.print("Please enter date begin follow format yyyy-mm-dd: ");
                                    dateFormat4 = sc.nextLine().trim();
                                    if (dateFormat4.equals("")) {
                                        System.out.println("   The date can't be empty!");
                                        isValid = false;
                                    } else if (!dateFormat4.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                        System.out.println("    The date must be follow format yyyy-mm-dd!");
                                        isValid = false;
                                    } else {
                                        Pattern pattern = Pattern.compile("-");
                                        String[] arrCurrent = pattern.split(strDate4);
                                        String[] arrInput = pattern.split(dateFormat4);
                                        checkDate(arrInput, arrCurrent, strDate4, 0);
                                    }
                                } while (isValid == false);

                                do {
                                    isValid = true;
                                    // Gets the date end of  wallet
                                    System.out.print("Please enter date end follow format yyyy-mm-dd: ");
                                    dateFormat5 = sc.nextLine().trim();
                                    if (dateFormat5.equals("")) {
                                        System.out.println("   The date can't be empty!");
                                        isValid = false;
                                    } else if (!dateFormat5.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                        System.out.println("    The date must be follow format yyyy-mm-dd!");
                                        isValid = false;
                                    } else {
                                        Pattern pattern = Pattern.compile("-");
                                        String[] arrCurrent = pattern.split(strDate5);
                                        String[] arrInput = pattern.split(dateFormat5);
                                        checkDate(arrInput, arrCurrent, strDate5, 0);
                                    }
                                } while (isValid == false);
                                String[] arrInput5 = pattern1.split(dateFormat5);
                                String[] arrInput4 = pattern1.split(dateFormat4);
                                checkDate(arrInput4, arrInput5, dateFormat5, 1);
                            } while (isValid == false);
                            System.out.println("Statistics income and spending from " + dateFormat4 + " to " + dateFormat5);
                            //  Show list wallet following table format and statistics income and spending
                            wm.showTableDate(dateFormat4, dateFormat5);
                            sc.nextLine();
                            isValid = false;
                            break;
                        case 5:
                            System.out.println("\n--------------------------------------");
                            System.out.println("Thanks for using our software!\n"
                                    + "See you again!");
                            isValid = true;
                            break;
                        default:
                            System.out.println("Error: The function must be a integer number from 1 to 5!");
                            isValid = false;
                            break;
                    }
                } catch (InputMismatchException | ParseException e) {
                    System.out.println("Error: The function must be a integer number from 1 to 5!");
                    isValid = false;
                    sc.nextLine();
                }
            } while (isValid == false);
        } catch (IOException | IncomeSpendingException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                wm.saveFile(); // Save wallet
            } catch (IOException e) {
                System.out.println("Exception: Can't save wallets!");
            }
        }
    }
}
