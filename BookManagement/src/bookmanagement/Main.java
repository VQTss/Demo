package bookmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class Main {

    private static BookManagement comicBook;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            comicBook = new BookManagement("ComicBooks.txt"); // Loads comic book bank
            comicBook.loadBook();

            Scanner cin = new Scanner(System.in); //  Creates new  a scanner
            int func;  // The function that selected by user
            do {
                System.out.println("\n---------COMIC BOOK RENTAL SHOP----------");
                System.out.println("1. Add new comic book.");
                System.out.println("2. Search book by title.");
                System.out.println("3. Search book of an author.");
                System.out.println("4. Update book rental price.");
                System.out.println("5. Delete comic book.");
                System.out.println("6. Quit.");
                System.out.print("\tPlease select a function: ");
                func = cin.nextInt();
                cin.nextLine();
                switch (func) {
                    case 1:
                        System.out.println("---Book Management [Add Book]---");
                        String Title = "";
                        double bookRentalPrice = 0.0;
                        String Author = "";
                        int Volume = 0;
                        boolean isValid = true;
                        // Gets the title of comic book
                        do {
                            System.out.print("Please enter title of book: ");
                            Title = cin.nextLine().trim();
                            if (Title.equals("")) {
                                System.out.println("Error: Title can not be empty!");
                            }
                        } while (Title.equals(""));
                        // Gets the book rental price of comic book
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter price of book: ");
                                bookRentalPrice = cin.nextDouble();
                                cin.nextLine();
                                if (bookRentalPrice < 0) {
                                    System.out.println("Error: The price can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The price of book must be a number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (bookRentalPrice < 0 || isValid == false);
                        // Gets the author of comic book
                        do {
                            System.out.print("Please enter author of book: ");
                            Author = cin.nextLine().trim();

                            if (Author.equals("")) {
                                System.out.println("Error: Author can not be empty!");
                            }
                        } while (Author.equals(""));
                        // Gets the volume of  comic book4
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter volume of book: ");
                                Volume = cin.nextInt();
                                cin.nextLine();
                                if (Volume < 0) {
                                    System.out.println("Error: The Volume can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The volume must be a integer number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (Volume < 0 || isValid == false);
                        // Add book for comic book
                        comicBook.add(Title, bookRentalPrice, Author, Volume);
                        System.out.println("Your book '" + Title + "' is created!");
                        //  Show list book following table format
                        comicBook.showTableBook();
                        cin.nextLine();
                        break;
                    case 2:
                        String title;
                        do {
                            System.out.print("Please enter title you want to find: ");
                            title = cin.nextLine().trim();
                            if (title.equals("")) {
                                System.out.println("Error: Title can not be empty!");
                            }
                        } while (title.equals(""));
                        // Find  title of comic book
                        comicBook.findTitle(title);
                        cin.nextLine();
                        break;
                    case 3:
                        String author;
                        do {
                            System.out.print("Please enter author you want to find: ");
                            author = cin.nextLine().trim();
                            if (author.equals("")) {
                                System.out.println("Error: Author can not be empty!");
                            }
                        } while (author.equals(""));
                        // Find  author of comic book
                        comicBook.findAuthor(author);
                        cin.nextLine();
                        break;
                    case 4:
                        int n = comicBook.comicBooksList.size();
                        int id = 0;
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter ID you want to update price : ");
                                id = cin.nextInt();

                                if (id < 0 || id > n) {
                                    System.out.println("Error: The ID can not be less than 0 or greater than " + n + "!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: The ID must be a integer number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (id < 0 || id > n || isValid == false);
                        // Update the book rental price of comic book
                        if (comicBook.findBookID(id) == -1) {
                            System.out.println("The ID can't found!");
                        } else {
                            comicBook.updatePrice(id);
                        }
                        cin.nextLine();
                        break;
                    case 5:
                        int removeID = 0;
                        int ID = comicBook.comicBooksList.size();
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter the ID of book that you want to delete: ");
                                removeID = cin.nextInt();
                                cin.nextLine();
                                if (removeID <= 0 || removeID > ID) {
                                    System.out.println("Error: the ID what you want to delete must be from 1 to " + ID + "!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: The ID must be a integer number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (removeID <= 0 || removeID > ID || isValid == false);
                        // Delete book of comic book
                        ComicBook a = comicBook.comicBooksList.get(removeID);
                        if (comicBook.deleteBook(removeID) == -1) {
                            System.out.println("The ID can't not found!");
                        } else {
                            System.out.println("The book '" + a.getTitle() + "' is deleted!");
                        }
                        //  Show list book following table format
                        comicBook.showTableBook();
                        cin.nextLine();
                        break;
                    case 6:
                        System.out.println("\n--------------------");
                        System.out.println("Thank for using our software!\n"
                                + "See you again!");
                        break;
                    default:
                        System.out.println("Error: The function must be from 1 to 6!");
                }
            } while (func != 6);
        } catch (Exception e) {
            System.out.println("Error: The selection must be a integer number");
        } finally {
            try {
                comicBook.saveBook(); // Save books
            } catch (Exception e) {
                System.out.println("Exception: Can't save books!");
            }
        }
    }

}
