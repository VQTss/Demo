package fishmanagement;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Thai Vo Quoc CE160568
 */
public class Main {

    /**
     * initialize object
     */
    public static FishManagement fm;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String familyName = "";
        double importPrice = 0;
        double sellingPrice = 0;
        String orgin = "";

        try {
            fm = new FishManagement("src/data/FishFile.txt");
            fm.loadFile(); // Loads fish bank
            Scanner sc = new Scanner(System.in); //  Creates new  a scanner
            int chose = 0;// The choose that selected by user
            boolean isVaild = true;
            do {
                try {
                    System.out.println("\n---------Aquarium  SHOP----------");
                    System.out.println("1. Add new fish.");
                    System.out.println("2. Search fish by family name.");
                    System.out.println("3. Search fish by price.");
                    System.out.println("4. Update price fish.");
                    System.out.println("5. Show fish discounting.");
                    System.out.println("6. Quit.");
                    System.out.print("\tPlease select a function: ");
                    chose = sc.nextInt();
                    sc.nextLine();
                    switch (chose) {
                        case 1:
                            System.out.println("---Aquarium  Management [Add Fish]---");
                            // Gets the family name from user
                            do {
                                System.out.print("Please enter family name of fish: ");
                                familyName = sc.nextLine().trim();
                                if (familyName.equals("")) {
                                    // Alert error if user input string empty
                                    System.out.println("Error: The family name can't empty!");
                                }
                            } while (familyName.equals(""));
                            // Gets the import price from user
                            do {
                                try {
                                    isVaild = true;
                                    System.out.print("Please enter import price of fish: ");
                                    importPrice = sc.nextDouble();
                                    sc.nextLine();
                                    if (importPrice <= 0) {
                                        // Alert error if user input price less than or equal 0!
                                        System.out.println("Error: The import price can't less than or equal 0!");
                                        isVaild = false;
                                    }
                                } catch (InputMismatchException e) {
                                    // Alert error if user input wrong data type
                                    System.out.println("Error: The import price must be a number!");
                                    sc.nextLine();
                                    isVaild = false;
                                }
                            } while (isVaild == false);
                            // Gest the selling price from user
                            do {
                                try {
                                    isVaild = true;
                                    System.out.print("Please enter selling price of fish: ");
                                    sellingPrice = sc.nextDouble();
                                    sc.nextLine();
                                    if (sellingPrice <= 0) {
                                        // Alert error if user input selling price less than or equal 0!
                                        System.out.println("Error: The selling price can't less than or equal 0! ");
                                        isVaild = false;
                                    }
                                } catch (InputMismatchException e) {
                                    // Alert error if user input wrong data type
                                    isVaild = false;
                                    System.out.println("Error: The selling must be a number!");
                                    sc.nextLine();
                                }
                            } while (isVaild == false);
                            // Gets the orgin from user
                            do {
                                isVaild = true;
                                System.out.print("Please enter orgin of fish: ");
                                orgin = sc.nextLine().trim();
                                if (orgin.equals("")) {
                                    // Alert error if user input string empty
                                    System.out.println("The orgin can't empty!");
                                    isVaild = false;
                                }
                            } while (isVaild == false);
                            // Add fish for fish management
                            fm.addFish(familyName, importPrice, sellingPrice, orgin);
                            System.out.println("The fish " + familyName + " is created");
                            // show all fish list
                            fm.showTable();
                            break;
                        case 2:
                            System.out.println("---Fish Management [Find Family Name Of Fish]---");
                            String fName = "";
                            // Gets keyword from user
                            do {
                                isVaild = true;
                                System.out.print("Please enter family name, you want to find: ");
                                fName = sc.nextLine().trim();
                                if (fName.equals("")) {
                                    // Alert error if user input string empty
                                    System.out.println("Error: The family name can't empty!");
                                    isVaild = false;
                                }
                            } while (isVaild == false);
                            System.out.println("The keyword '" + fName + "' you want to find.");
                            // find keyword of family name in fish list  and show family name contains keyword
                            fm.findFamily(fName);
                            sc.nextLine();
                            break;
                        case 3:
                            System.out.println("---Fish Management [Find Price Fish]---");
                            double maxPrice = 0,
                             minPrice = 0;
                            // Gets min price that user want to find
                            do {
                                do {
                                    try {
                                        isVaild = true;
                                        System.out.print("Please enter min-price, you want to find: ");
                                        minPrice = sc.nextDouble();
                                        sc.nextLine();
                                        if (minPrice <= 0) {
                                            System.out.println("The min-price must be greater than 0!");
                                            isVaild = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("The min-price must be a number!");
                                        isVaild = false;
                                        sc.nextLine();
                                    }
                                } while (isVaild == false);
                                // Gets max price that user want to find
                                do {
                                    try {
                                        isVaild = true;
                                        System.out.print("Please enter max-price, you want to find: ");
                                        maxPrice = sc.nextDouble();
                                        sc.nextLine();
                                        if (maxPrice <= 0) {
                                            System.out.println("The max-price must be greater than 0!");
                                            isVaild = false;
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("The max-price must be a number!");
                                        isVaild = false;
                                        sc.nextLine();
                                    }
                                } while (isVaild == false);
                                if (minPrice >= maxPrice) {
                                    System.out.println("The min-price must be less than max-price!");
                                    isVaild = false;
                                }
                            } while (isVaild == false);
                            System.out.println("You want to find price: from $" + minPrice + " to $" + maxPrice);
                            // Find price follow min price and max price that the user want to find
                            fm.checkPrice(minPrice, maxPrice);
                            sc.nextLine();
                            break;
                        case 4:
                            System.out.println("---Aquarium  Management [Update Price Fish]---");
                            fm.showTable();
                            int id = 0;
                            double selling = 0;
                            // Gets ID that the user want to update
                            do {
                                try {
                                    isVaild = true;
                                    System.out.print("Please enter ID, you want to update selling price:");
                                    id = sc.nextInt();
                                    sc.nextLine();
                                    if (id <= 0) {
                                        System.out.println("The ID must be greater than 0!");
                                        isVaild = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("The ID must be a integer number!");
                                    isVaild = false;
                                    sc.nextLine();
                                }
                            } while (isVaild == false);
                            // Gets selling price that the user want to update by ID
                            do {
                                try {
                                    isVaild = true;
                                    System.out.print("Please enter selling price, you want to update price:");
                                    selling = sc.nextDouble();
                                    if (selling <= 0) {
                                        System.out.println("The selling price must be greater than 0!");
                                        isVaild = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("The selling price must be a integer number!");
                                    isVaild = false;
                                    sc.nextLine();
                                }
                            } while (isVaild == false);
                            // Update selling price by ID
                            fm.updatePrice(id, selling);
                            sc.nextLine();
                            break;
                        case 5:
                            System.out.println("---Aquarium  Management [Discount Fish]---");
                            // show fish is discounting
                            fm.discount();
                            sc.nextLine();
                            break;
                        case 6:
                            // Exit the program and thanks the user
                            System.out.println("\n--------------------------------------");
                            System.out.println("Thank for using our software!\n"
                                    + "See you again!");
                            break;
                        default:
                            // Alert if user input less than 1 and greater than 6
                            System.out.println("Error: The function must be a integer number from 1 to 6!");
                    }
                } catch (InputMismatchException e) {
                    // Alert if user input wrong data type
                    System.out.println("Error: The function must be a integer number from 1 to 6!");
                    isVaild = false;
                    sc.nextLine();
                }
            } while (chose != 6 || isVaild == false);
        } catch (Exception e) {
            // show exception in program
            System.out.println(e.getMessage());
        } finally {
            try {

                fm.saveFish();// Save fish 
            } catch (Exception e) {
                System.out.println("Exception: Can't save fishes!");
            }
        }
    }
}
