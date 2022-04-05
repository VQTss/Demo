
package bookmanagement;

/**
 *
 * @author Vo Quoc Thai CE160568
 */
public class ComicBook {

    private int iD; //  ComicBook id
    private String title; // ComicBook title
    private double bookRentalPrice; // ComicBook book rental price
    private String author; // ComicBook author
    private int volume; // ComicBook volume

    /**
     * Create new ComicBook
     *
     * @param iD
     * @param title
     * @param bookRentalPrice
     * @param author
     * @param volume
     * @throws ComicBookException
     */
    public ComicBook(int iD, String title, double bookRentalPrice, String author, int volume) throws ComicBookException {
        this.setiD(iD);
        this.setTitle(title);
        this.setBookRentalPrice(bookRentalPrice);
        this.setAuthor(author);
        this.setVolume(volume);
    }

    /**
     * Gets the id of comic book
     *
     * @return
     */
    public int getiD() {
        return iD;
    }

    /**
     * Sets the id of comic book
     *
     * @param iD
     * @throws ComicBookException
     */
    public void setiD(int iD) throws ComicBookException {
        if (iD <= 0) {
            throw new ComicBookException("ID Book must be a positive integer");
        } else {
            this.iD = iD;
        }
    }

    /**
     * Gets the title of comic book
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of comic book
     *
     * @param title
     * @throws ComicBookException
     */
    public void setTitle(String title) throws ComicBookException {
        if (title.equals("")) {
            throw new ComicBookException("Title book can't be empty");
        } else {
            this.title = title;
        }
    }

    /**
     * Gets the book rental price of comic book
     *
     * @return
     */
    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    /**
     * Sets the book rental price of comic book
     *
     * @param bookRentalPrice
     * @throws ComicBookException
     */
    public void setBookRentalPrice(double bookRentalPrice) throws ComicBookException {
        if (bookRentalPrice <= 0) {
            throw new ComicBookException("Book Rental Price must be a positive integer");
        } else {
            this.bookRentalPrice = bookRentalPrice;
        }
    }

    /**
     * Gets the author of comic book
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of comic book
     *
     * @param author
     * @throws ComicBookException
     */
    public void setAuthor(String author) throws ComicBookException {
        if (author.equals("")) {
            throw new ComicBookException("Name of Author can't be empty");
        } else {
            this.author = author;
        }
    }

    /**
     * Gest the volume of comic book
     *
     * @return
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Sets the volume of comic book
     *
     * @param volume
     * @throws ComicBookException
     */
    public void setVolume(int volume) throws ComicBookException {
        if (volume <= 0) {
            throw new ComicBookException("Volume Book must be a positive integer");
        } else {
            this.volume = volume;
        }
    }

    public void format1() {

    }
}
