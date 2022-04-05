package storeSellFish;

import java.util.Scanner;

/**
 *
 * @author Dpl
 */
public class StoreFishMain {

    /**
     * Function to get string input
     *
     * @param scanner
     * @param message
     * @return
     */
    public static String getString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    /**
     * Function to get string input
     *
     * @param scanner
     * @param message
     * @return
     */
    public static int getInteger(Scanner scanner, String message) {
        Integer input = null;
        while (input == null) {
            try {
                input = Integer.parseInt(getString(scanner, message));
            } catch (NumberFormatException e) {
                System.out.println("    Your input must be an integer!");
            }
        }
        return input;
    }

    /**
     * Function to get real number input
     *
     * @param scanner
     * @param message
     * @return
     */
    public static double getRealNumber(Scanner cin, String message) {
        Double input = null;
        while (input == null) {
            try {
                input = Double.parseDouble(getString(cin, message));
                if (input < 0) {
                    System.out.println("    Your input must be greater than 0!");
                    input = null;
                }
                if (input > 9999999999999l) {
                    System.out.println("The number you are entering is too large!");
                    input = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("    Your input must be a real number!");
            }
        }
        return input;
    }

    /**
     * Function to get userchoice input
     *
     * @param scanner
     * @param message
     * @return
     */
    public static int getInputInteger(Scanner cin, String message, int min, int max) {
        Integer input = null;
        while (input == null) {
            input = getInteger(cin, message);
            if (input < min || input > max) {
                System.out.println("    Your input must be an integer from " + min + " to " + max + "!");
                input = null;
            }
        }
        return input;
    }

    private static StoreSellFishManagement SF;

    /**
     * Main function
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //load the data file
            SF = new StoreSellFishManagement("src/data/Fishs.txt");
            SF.loadFish();
            Scanner scanner = new Scanner(System.in); //Creates a scanner
            int func;                                 //The function that selected by user

            do {
                //Shows menu                
                System.out.println("\n---------FISH STORE---------");
                System.out.println("1. Add new fish.");
                System.out.println("2. Search fish by fish family.");
                System.out.println("3. Search for fish by price.");
                System.out.println("4. Update fish selling price.");
                System.out.println("5. Show list of Fish on sale.");
                System.out.println("6. Quit.\n");

                //Gets function that selected by user
                func = getInputInteger(scanner, "Please enter your choice(1-6): ", 1, 6);

                switch (func) {
                    // Add new fish
                    case 1:
                        System.out.println("\n------------------------------------");
                        System.out.println("        *** ADD NEW FISH *** ");
                        //variable to check if ID is valid or not
                        boolean validId = false;
                        int id = -1;
                        do {
                            id = getInputInteger(scanner, "Please enter new fish ID: ", 0, 999999);
                            //Check if ID is already exists
                            if (!SF.checkvalidID(id)) {
                                System.out.println("    This ID is already existing.");
                            } else {
                                validId = true;
                            }
                        } while (!validId);

                        //ID is valid, then get user inputs
                        String familyname = getString(scanner, "Please enter new fish: ");
                        Double importprice = getRealNumber(scanner, "Please enter the imported price for fish: ");
                        Double sellingPrice = getRealNumber(scanner, "Please enter the selling price for fish: ");
                        String origin = getString(scanner, "Please enter the origin of fish: ");

                        boolean check = SF.CheckNameandOrigin(familyname, origin);
                        if (check == false) {
                            System.out.println("This fish is already in the list!");
                            break;
                        }
                        //add the new fish
                        SF.AddFish(id, familyname, importprice, sellingPrice, origin);
                        SF.updateTemplate();

                        //announce when add successfully
                        System.out.println("\nSuccessfully Add new fishs!");

                        //display all comic books after adding
                        System.out.println("   All Fish: ");
                        SF.listFish();

                        //wait for user enter
                        scanner.nextLine();
                        break;

                    //Search fish by family name
                    case 2:
                        System.out.println("\n------------------------------------------------");
                        System.out.println("        *** SEARCH FISH BY FAMILY NAME ***");
                        String query = getString(scanner, "Please enter the family name of fish that you want to search: ");
                        //use findbyfamilyname function to find 
                        SF.findByFamilyName(query);
                        //wait for user enter
                        scanner.nextLine();
                        break;

                    //Search fish by price between min and max
                    case 3:
                        System.out.println("\n----------------------------------------------------");
                        System.out.println("        *** SEARCH FISH BY PRICE(MIN,MAX) ***");
                        double min,
                         max;
                        //get user input
                        do {
                            min = getRealNumber(scanner, "Please enter the min price you want to find: ");
                            max = getRealNumber(scanner, "Please enter the max price you want to find: ");
                            //check and report errors
                            if (min >= max) {
                                System.out.println("Max price must be greater than min price!");
                            }
                        } while (min >= max);
                        //use FindFishbyPrice function
                        SF.FindFishbyPrice(min, max);
                        //wait for user enter
                        scanner.nextLine();
                        break;

                    //Update fish seliing price
                    case 4:
                        System.out.println("\n--------------------------------------------");
                        System.out.println("      *** UPDATE FISH SELLING PRICE ***");
                        //List all fish
                        SF.listFish();
                        int keyID = getInputInteger(scanner, "Please enter the fish ID that you want to update price: ", 0, 999999);
                        //check if ID is exist
                        if (SF.checkvalidID(keyID)) {
                            System.out.println("    ID not found.");

                            //if ID not exist, go back to menu 
                            scanner.nextLine();
                            break;
                        }

                        //if ID exist, get the new selling price and update it
                        Double newPrice = getRealNumber(scanner, "Please enter new selling price for the fish with ID " + keyID + ": ");
                        SF.updateSellingPrice(keyID, newPrice);

                        //wait for user enter
                        scanner.nextLine();
                        break;

                    //List all fish on sale
                    case 5:
                        //use fishDiscount function
                        SF.fishDiscount();
                        //wait for user enter
                        scanner.nextLine();
                        break;

                    //Quit
                    case 6:
                        System.out.println("Thank for using our software!\n"
                                + "See you again!");
                        break;

                }
            } while (func != 6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                SF.saveFishs();//Saves fish
            } catch (Exception e) {
                System.out.println("Exception: Can't save Fishs!");
            }

        }
    }
}
