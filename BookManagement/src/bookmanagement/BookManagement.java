package bookmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class BookManagement {

    private String BOOK_FILE;  // The URL of data file that stores all comic book
    private int numberOfBook; // Number of comic book that store in data file
    ArrayList<ComicBook> comicBooksList; // All instance of comic book
    Scanner sc = new Scanner(System.in);

    /**
     * Create instance for comic book management
     *
     * @param BOOK_FILE
     */
    public BookManagement(String BOOK_FILE) {
        this.numberOfBook = 0;
        this.comicBooksList = new ArrayList<ComicBook>();
        this.BOOK_FILE = BOOK_FILE;
    }
    //initialize width for table output
    private int widthID = 2;
    private int widthTitle = 5;
    private int widthRentalPrice = 13;
    private int widthAuthor = 6;
    private int widthVolume = 6;
    private String barLine = "";
    //initialize template for table output
    private String template = "";

    /**
     * Find book by ID
     *
     * @param ID
     * @return
     */
    public int findBookID(int ID) {
        for (int i = 0; i < this.comicBooksList.size(); i++) {
            ComicBook a = this.comicBooksList.get(i);
            if (a.getiD() == ID) {
                return i;
            }
        }
        return - 1;
    }

    /**
     * Loads data of comic book from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws ComicBookException
     */
    public void loadBook() throws IOException, ComicBookException {
        File bookFile = new File(BOOK_FILE);
        if (!bookFile.exists()) {
            bookFile.createNewFile();
            System.out.printf("The data file " + BOOK_FILE + " is not exits. "
                    + "Creating new data file " + BOOK_FILE + "... Done!\n");
        } else {
            System.out.printf("\nThe data file " + BOOK_FILE + " is found."
                    + " Data of book is loading... ");

            String Id, Title, bookRentalPrice, author, volume;
            try (BufferedReader br = new BufferedReader(new FileReader(BOOK_FILE))) {
                this.numberOfBook = Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfBook; i++) {
                    Id = br.readLine();
                    Title = br.readLine();
                    bookRentalPrice = br.readLine();
                    author = br.readLine();
                    volume = br.readLine();
                    updateWidth(Id, Title, bookRentalPrice, author, volume);
                    this.comicBooksList.add(new ComicBook(Integer.parseInt(Id), Title, Double.parseDouble(bookRentalPrice), author,
                            Integer.parseInt(volume)));
                }
            }
            System.out.println("Done! [" + this.numberOfBook + " books]");
        }
    }

    /**
     * Update width for table if have width greater than current width
     *
     * @param id
     * @param title
     * @param rentalPrice
     * @param author
     * @param volume
     */
    public void updateWidth(String id, String title, String rentalPrice, String author, String volume) {
        widthID = Math.max(widthID, id.length());
        widthTitle = Math.max(widthTitle, title.length());
        widthRentalPrice = Math.max(widthRentalPrice, rentalPrice.length());
        widthAuthor = Math.max(widthAuthor, author.length());
        widthVolume = Math.max(widthVolume, volume.length());

    }

    /**
     * update that print to screen
     */
    public void updateTemplate() {
        String col1 = "";
        for (int i = 0; i < widthID + 2; i++) {
            col1 += "-";
        }
        String col2 = "";
        for (int i = 0; i < widthTitle + 5; i++) {
            col2 += "-";
        }
        String col3 = "";
        for (int i = 0; i < widthRentalPrice + 2; i++) {
            col3 += "-";
        }
        String col4 = "";
        for (int i = 0; i < widthAuthor + 5; i++) {
            col4 += "-";
        }
        String col5 = "";
        for (int i = 0; i < widthVolume + 2; i++) {
            col5 += "-";
        }
        barLine = "+" + col1 + "+" + col2 + "+" + col3 + "+" + col4 + "+" + col5 + "+";
        template = "| %" + widthID + "s | %-" + widthTitle + "s    | %" + widthRentalPrice + "s | %-" + widthAuthor + "s    | %" + widthVolume + "s |\n";
    }

    /**
     * Add new ComicBook to comic book bank
     *
     * @param title
     * @param bookRentalPrice
     * @param author
     * @param volume
     * @return
     * @throws ComicBookException
     */
    public int add(String title, double bookRentalPrice, String author, int volume) throws ComicBookException {
        int id = 0, count = 0;
        for (int j = 1;; ++j) {
            boolean valid = true;
            for (int i = 0; i < this.comicBooksList.size(); i++) {
                ComicBook a = this.comicBooksList.get(i);
                if (a.getiD() == j) {
                    valid = false;
                } else if (a.getTitle().equals(title) && a.getBookRentalPrice() == bookRentalPrice
                        && a.getAuthor().equals(author) && a.getVolume() == volume) {
                    count++;
                }
            }
            if (valid == true) {
                id = j;
                this.comicBooksList.add(new ComicBook(id, title, bookRentalPrice, author, volume));
                break;
            } else if (count != 0) {
                break;
            }
        }
        return ++this.numberOfBook;
    }

    /**
     * Find book by title
     *
     * @param title
     */
    public void findTitle(String title) {
        int count = 0;

        for (int i = 0; i < this.comicBooksList.size(); i++) {
            ComicBook b = this.comicBooksList.get(i);
            if (b.getTitle().toLowerCase().contains(title.toLowerCase()) == true) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found '" + title + "' in ComicBook bank!");
        } else {
            updateTemplate();
            System.out.println(barLine);
            System.out.printf(template, "ID", "Title", "Price", "Author", "Volume");
            System.out.println(barLine);

            for (int i = 0; i < this.comicBooksList.size(); i++) {
                ComicBook b = this.comicBooksList.get(i);
                if (b.getTitle().toLowerCase().contains(title.toLowerCase()) == true) {
                    showBookByID(b.getiD());
                }
            }

            System.out.println(barLine);
        }

    }

    /**
     * Deleted book by id
     *
     * @param removeID
     * @return
     */
    public int deleteBook(int removeID) {
        int i = findBookID(removeID);
        if (i != -1) {
            {
                this.comicBooksList.remove(findBookID(removeID));
                return --this.numberOfBook;
            }
        } else {

            return -1;
        }

    }

    /**
     * Find book by author
     *
     * @param author
     */
    public void findAuthor(String author) {
        int count = 0;

        for (int i = 0; i < this.comicBooksList.size(); i++) {
            ComicBook b = this.comicBooksList.get(i);
            if (b.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found '" + author + "' in ComicBook bank!");
        } else {
            updateTemplate();
            System.out.println(barLine);
            System.out.printf(template, "ID", "Title", "Price", "Author", "Volume");
            System.out.println(barLine);

            for (int i = 0; i < this.comicBooksList.size(); i++) {
                ComicBook b = this.comicBooksList.get(i);
                if (b.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                    showBookByID(b.getiD());
                }
            }

            System.out.println(barLine);
        }

    }

    /**
     * Update price for comic book by id
     *
     * @param Id
     * @throws ComicBookException
     */
    public void updatePrice(int Id) throws ComicBookException {
        int i = findBookID(Id);
        double price = 0;
        boolean isValid = true;
        do {
            try {
                isValid = true;
                System.out.print("Please enter new price for " + this.comicBooksList.get(i).getTitle() + ": ");
                price = sc.nextDouble();
                if (price <= 0) {
                    System.out.println("The price must be a positive number!");
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: The price must be a positive integer number!");
                isValid = false;
                sc.nextLine();

            }
        } while (price <= 0);
        this.comicBooksList.get(i).setBookRentalPrice(price);
        showTableBook();

    }

    /**
     * Show all comic book following table format
     */
    public void showTableBook() {
        int No = 0;

        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "ID", "Title", "Price", "Author", "Volume");
        System.out.println(barLine);
        for (int i = 0; i < this.comicBooksList.size(); i++) {
            ComicBook a = this.comicBooksList.get(i);
            No++;
            System.out.printf(template, a.getiD() + "", a.getTitle(), a.getBookRentalPrice() + "$", a.getAuthor(), a.getVolume() + "");

        }
        System.out.println(barLine);
    }

    /**
     * Show book by id
     *
     * @param id
     */
    public void showBookByID(int id) {
        ComicBook a = this.comicBooksList.get(findBookID(id));
        System.out.printf(template, a.getiD() + "", a.getTitle(), a.getBookRentalPrice() + "$", a.getAuthor(), a.getVolume() + "");
    }

    /**
     * Save comic book bank ( ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveBook() throws IOException {
        // Overwrite data file
        FileWriter fw = new FileWriter(new File(BOOK_FILE));
        try {
            System.out.print("ComicBook is saving into data file ComicBooks.txt...");
            // Writes number of comic book
            fw.append(String.valueOf(this.numberOfBook) + "\n");
            for (int i = 0; i < this.numberOfBook; i++) {
                int iD = this.comicBooksList.get(i).getiD();
                String title = this.comicBooksList.get(i).getTitle();
                double bookRentalPrice = this.comicBooksList.get(i).getBookRentalPrice();
                String author = this.comicBooksList.get(i).getAuthor();
                int volume = this.comicBooksList.get(i).getVolume();
                // Writes comic book's information into data file
                fw.append(String.valueOf(iD) + "\n");
                fw.append(title + "\n");
                fw.append(String.valueOf(bookRentalPrice) + "\n");
                fw.append(author + "\n");
                fw.append(String.valueOf(volume) + "\n");
            }
        } finally {
            // Save data file ( from RAM into HDD)
            fw.close();
            System.out.println("Done! [" + this.numberOfBook + " books]");
        }
    }

}
