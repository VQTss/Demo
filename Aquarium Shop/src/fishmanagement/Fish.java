package fishmanagement;

/**
 *
 * @author Thai Vo Quoc CE160568
 */
public final class Fish {

    private int ID; // Fish ID
    private String familyName; // Family name of Fish
    private double importPrice; // Import price of Fish 
    private double sellingPrice; // Selling price of Fish
    private String origin; //  Origin of Fish

    /**
     *
     * @param ID
     * @param familyName
     * @param importPrice
     * @param sellingPrice
     * @param origin
     * @throws FishException
     */
    public Fish(int ID, String familyName, double importPrice, double sellingPrice, String origin) throws FishException {
        this.setID(ID);
        this.setFamilyName(familyName);
        this.setImportPrice(importPrice);
        this.setSellingPrice(sellingPrice);
        this.setOrigin(origin);
    }

    /**
     * Gets the ID of fish
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the ID of fish
     *
     * @param ID
     * @throws FishException
     */
    public void setID(int ID) throws FishException {
        if (ID <= 0) {
            throw new FishException("The ID must be a integer number!");
        } else {
            this.ID = ID;
        }
    }

    /**
     * Gets the family name of fish
     *
     * @return
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Set the family name of fish
     *
     * @param familyName
     * @throws FishException
     */
    public void setFamilyName(String familyName) throws FishException {
        if (familyName.equals("")) {
            throw new FishException("The familyName can't be empty!");
        } else {
            this.familyName = familyName;
        }
    }

    /**
     * Gets the import price of fish
     *
     * @return
     */
    public double getImportPrice() {
        return importPrice;
    }

    /**
     * Sets the import price of fish
     *
     * @param importPrice
     * @throws FishException
     */
    public void setImportPrice(double importPrice) throws FishException {
        if (importPrice <= 0) {
            throw new FishException("The importPrice must be a number and greater than 0!");
        } else {
            this.importPrice = importPrice;
        }
    }

    /**
     * Gets the selling price of fish
     *
     * @return
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the selling price of fish
     *
     * @param sellingPrice
     * @throws FishException
     */
    public void setSellingPrice(double sellingPrice) throws FishException {
        if (sellingPrice <= 0) {
            throw new FishException("The sellingPrice must be a number and greater than 0!");
        } else {
            this.sellingPrice = sellingPrice;
        }
    }

    /**
     * Gets the origin of fish
     *
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of fish
     *
     * @param origin
     * @throws FishException
     */
    public void setOrigin(String origin) throws FishException {
        if (origin.equals("")) {
            throw new FishException("The origin can't be empty");
        } else {
            this.origin = origin;
        }
    }

}
