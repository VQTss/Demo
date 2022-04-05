package fishmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Thai Vo Quoc CE160568
 */
public class FishManagement {

    private int numberOfFish; // Number of fishes that store in data file
    private String FISH_FILE;// The URL of data file that stores all fishes
    ArrayList<Fish> fishList; // All instance of comic book

    //initialize width for table output
    private int widthID = 2;  // width of ID
    private int widthFamilyName = 25; //  witdth of family name
    private int widthImportPrice = 20; // width of import price
    private int widthSellingPrice = 20; // width of selling price
    private int widthOrgin = 6; // width of origin 
    private String barLine = ""; //  Line +----+
    //initialize template for table output
    private String template = ""; // store all information of fish
    private String template1 = ""; // header such as  NO , ID , FamilyName,...

    /**
     *
     * @param FISH_FILE
     */
    public FishManagement(String FISH_FILE) throws FishException {
        if (FISH_FILE.equals("")) {
            throw new FishException("The URL of fish data file can't be empty!");
        } else {
            this.numberOfFish = 0; // The number of fish is 0
            this.FISH_FILE = FISH_FILE; // Inits the URL of data file thats stores fish bank
            this.fishList = new ArrayList<>(); // Create empty fish bank
        }
    }

    /**
     *
     * @param ID
     * @param FamilyName
     * @param ImportPrice
     * @param SellingPrice
     * @param orgin
     */
    public void updateWidth(String ID, String FamilyName, String ImportPrice, String SellingPrice, String orgin) {
        widthID = Math.max(widthID, ID.length()); // Find width max of ID
        widthFamilyName = Math.max(widthFamilyName, FamilyName.length()); // Find width max of family name
        widthImportPrice = Math.max(widthImportPrice, ImportPrice.length()); // Find width max of import price
        widthSellingPrice = Math.max(widthSellingPrice, SellingPrice.length()); // Find width max of selling price
        widthOrgin = Math.max(widthOrgin, orgin.length()); // Find width max of origin
    }

    /**
     *
     */
    public void updateTemplate() {
        // inits bar line
        String col1 = "";
        for (int i = 0; i < widthID + 3; i++) {
            col1 += "-";
        }
        // inits bar line
        String col2 = "";
        for (int i = 0; i < widthFamilyName + 5; i++) {
            col2 += "-";
        }
        // inits bar line
        String col3 = "";
        for (int i = 0; i < widthImportPrice + 2; i++) {
            col3 += "-";
        }
        // inits bar line
        String col5 = "";
        for (int i = 0; i < widthOrgin + 2; i++) {
            col5 += "-";
        }
        //Line +----+
        barLine = "+" + col1 + "+" + col1 + "+" + col2 + "+" + col3 + "+" + (col3) + "+" + col5 + "+";
        // store all information of fish
        template = "| %" + (widthID + 1) + "s | %" + (widthID + 1) + "s | %-" + (widthFamilyName + 3) + "s | %"
                + (widthImportPrice) + "s | %" + (widthImportPrice) + "s | %-" + widthOrgin + "s |\n";
        // header such as  NO , ID , FamilyName,...
        template1 = "| %" + (widthID + 1) + "s | %" + (widthID + 1) + "s |    %-" + (widthFamilyName) + "s | %"
                + (widthImportPrice - 2) + "s   |   %-" + (widthImportPrice - 5) + "s    | %-" + widthOrgin + "s |\n";
    }

    /**
     * Loads data file fish from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws FishException
     */
    public void loadFile() throws IOException, FishException {
        File fishFile = new File(FISH_FILE);
        if (!fishFile.exists()) { // Check file is created
            fishFile.createNewFile(); // If not, create new file
            System.out.println("The data file" + FISH_FILE + "is not exits."
                    + "Create new data file" + FISH_FILE + "... Done!\n");
            this.numberOfFish = 0; // new data file with the number of fish is  0
        } else {
            // If file is existed, so loading this data file
            System.out.printf("\nThe data file " + FISH_FILE + " is found."
                    + " Data of fish is loading... ");
            String Id, familyName, importPrice, sellingPrice, origin;
            // Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(FISH_FILE))) {
                this.numberOfFish = Integer.parseInt(br.readLine()); // Read number of fish
                for (int i = 0; i < this.numberOfFish; i++) {
                    // Read fish's information
                    Id = br.readLine().trim();
                    familyName = br.readLine().trim();
                    importPrice = br.readLine().trim();
                    sellingPrice = br.readLine().trim();
                    origin = br.readLine().trim();
                    updateWidth(Id, familyName, importPrice, sellingPrice, origin);
                    // Create new instance of Fish and adds to fishes bank
                    fishList.add(new Fish(Integer.parseInt(Id), familyName,
                            Double.parseDouble(importPrice), Double.parseDouble(sellingPrice), origin));
                    // Update width information of fish
                }
            }
        }
        System.out.println("Done! [" + this.numberOfFish + " fishes]");
    }

    /**
     * Saves fish bank (ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveFish() throws IOException {
        FileWriter fw = new FileWriter(FISH_FILE);
        try {
            System.out.print("Fishes is saving into data file " + FISH_FILE + "...");
            // write number of fish
            fw.append(String.valueOf(numberOfFish) + "\n");
            for (int i = 0; i < this.numberOfFish; i++) {
                // inits fish's information
                int id = this.fishList.get(i).getID();
                String familyName = this.fishList.get(i).getFamilyName();
                double importPrice = this.fishList.get(i).getImportPrice();
                double sellingPrice = this.fishList.get(i).getSellingPrice();
                String origin = this.fishList.get(i).getOrigin();
                // write fish's information into data file
                fw.append(String.valueOf(id) + "\n");
                fw.append(familyName + "\n");
                fw.append(String.valueOf(importPrice) + "\n");
                fw.append(String.valueOf(sellingPrice) + "\n");
                fw.append(origin + "\n");
            }
        } finally {
            // Save data file ( from RAM into HDD)
            fw.close();
            System.out.println(" Done! [" + this.numberOfFish + " fishes]");
        }
    }

    /**
     * Add new fish to fish bank
     *
     * @param familyName
     * @param importPrice
     * @param sellingPrice
     * @param origin
     * @return
     * @throws FishException
     */
    public int addFish(String familyName, double importPrice, double sellingPrice, String origin) throws FishException {
        // Check if user input has been fish bank
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish f = this.fishList.get(i);
            if (f.getFamilyName().equalsIgnoreCase(familyName) && f.getImportPrice() == importPrice && f.getSellingPrice() == sellingPrice && f.getOrigin().equalsIgnoreCase(origin)) {
                return this.numberOfFish; // fish id
            }
        }
        //
        this.fishList.add(new Fish(++numberOfFish, familyName, importPrice, sellingPrice, origin));
        updateWidth(String.valueOf(numberOfFish), familyName, String.valueOf(importPrice), String.valueOf(sellingPrice), origin);
        return this.numberOfFish;
    }

    /**
     * Find fish by ID and return the index of this fish
     *
     * @param id
     * @return
     */
    public int findID(int id) {
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish fish = this.fishList.get(i);
            if (fish.getID() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Find family name and show family by keyword
     *
     * @param familyName
     */
    public void findFamily(String familyName) {

        ArrayList<Fish> fName = new ArrayList<>();
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish fish = this.fishList.get(i);
            if (fish.getFamilyName().toLowerCase().contains(familyName.toLowerCase())) {

                fName.add(fish);
            }
        }
        if (fName.size() == 0) {
            System.out.println("Not found family name!");
        } else {
            System.out.println("The family name you want to finding is ...");
            showTable(fName);
        }
    }

    /**
     * Compare price following min max of user input
     *
     * @param minPrice
     * @param maxPrice
     */
    public void checkPrice(double minPrice, double maxPrice) {

        ArrayList<Fish> fl = new ArrayList<>();
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish f = this.fishList.get(i);
            if (f.getSellingPrice() >= minPrice && f.getSellingPrice() <= maxPrice) {

                fl.add(f);
            }
        }
        if (fl.size() == 0) {
            System.out.println("Can't not found price!");
        } else {
            showTable(fl);
        }
    }

    /**
     * show table follow array list
     *
     * @param f1
     */
    public void showTable(ArrayList<Fish> f1) {
        int No = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template1, "No.", "ID", "Family name", "Import price", "Selling price", "Origin");
        System.out.println(barLine);
        for (int i = 0; i < f1.size(); i++) {
            Fish f = f1.get(i);
            No++;
            System.out.printf(template, No + "", f.getID(), f.getFamilyName(), "$" + f.getImportPrice(), "$" + f.getSellingPrice(), f.getOrigin());
        }
        System.out.println(barLine);
    }

    /**
     * show all fish
     */
    public void showTable() {
        int No = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template1, "No.", "ID", "Family name", "Import price", "Selling price", "Origin");
        System.out.println(barLine);
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish f = this.fishList.get(i);
            No++;
            System.out.printf(template, No + "", f.getID(), f.getFamilyName(), "$" + f.getImportPrice(), "$" + f.getSellingPrice(), f.getOrigin());
        }
        System.out.println(barLine);
    }

    /**
     * Update selling price by ID
     *
     * @param id
     * @param newPrice
     * @throws FishException
     */
    public void updatePrice(int id, double newPrice) throws FishException {
//        showTable();
        int checkId = findID(id);
        if (checkId != -1) {
            Fish f = this.fishList.get(checkId);
            System.out.print("Before, the '" + f.getFamilyName() + "' has price: " + f.getSellingPrice() + "$, then update is ");
            f.setSellingPrice(newPrice);
            System.out.println(f.getSellingPrice() + "$");
            showTable();
            System.out.println("Update selling-price success");
        } else {
            System.out.println("Not found ID!");
        }
    }

    /**
     * Show discount of selling price in fish bank
     */
    public void discount() {
        ArrayList<Fish> fPrice = new ArrayList<>();
        for (int i = 0; i < this.fishList.size(); i++) {
            Fish f = this.fishList.get(i);
            if (f.getImportPrice() > f.getSellingPrice()) {
                fPrice.add(f);
            }
        }
        if (fPrice.size() == 0) {
            System.out.println("Not discount!");
        } else {
            System.out.println("The fish is discounting ...");
            showTable(fPrice);
        }
    }
}
