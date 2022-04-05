package storeSellFish;

/**
 *
 * @author Dpl
 */
public class StoreFish {

    private int ID;                 //ID of the fish
    private String familyName;      //familyname of the fish
    private double importPrice;     //importprice of the fish
    private double sellingPrice;    //selingprice of the fish
    private String origin;          //origin of the fish

    /**
     * get the ID of fish
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the Id for fish
     *
     * @param Id
     * @throws StoreSellFishException
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * get the familyname of fish
     *
     * @return
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Set the familyname for fish
     *
     * @param familyname
     * @throws StoreSellFishException
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * get the importprice of fish
     *
     * @return
     */
    public double getImportPrice() {
        return importPrice;
    }

    /**
     * Set the importprice for fish
     *
     * @param importprice
     * @throws StoreSellFishException
     */
    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    /**
     * get the sellingprice of fish
     *
     * @return
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Set the sellingprice for fish
     *
     * @param sellingprice
     * @throws StoreSellFishException
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * get the origin of fish
     *
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the origin for fish
     *
     * @param origin
     * @throws StoreSellFishException
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public StoreFish(int ID, String familyName, double importPrice, double sellingPrice, String origin) throws StoreSellFishException {
        this.setID(ID);
        this.setFamilyName(familyName);
        this.setImportPrice(importPrice);
        this.setSellingPrice(sellingPrice);
        this.setOrigin(origin);
    }
}
