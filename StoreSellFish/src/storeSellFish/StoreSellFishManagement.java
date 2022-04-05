package storeSellFish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The class StoreSellFishManagement is used to manage fish
 *
 * @author Dpl
 */
public class StoreSellFishManagement {

    private ArrayList<StoreFish> StoreFishs;    //All instance of fish
    private String F_FILE;                      //The URL of file that contains all fish data
    private int numberOfFish;                   //Number of fish 

    //HashSet to check if ID is exist
    private Set<Integer> ids = new HashSet<Integer>();
    //Format sellingprice and importprice
    DecimalFormat Df = new DecimalFormat("0.0");
    //pattern to display tens, hundreds, thousands
    String pattern = "###,###.###";

    // width for each columns of the table (change base on max length of each column's content)
    private int widthID = 3;
    private int widthfamilyName = 11;
    private int widthimportPrice = 18;
    private int widthsellingPrice = 18;
    private int widthorigin = 6;

    //initialize the line of table
    private String barLine = "";
    //initialize template for table output
    private String template = "";

    public StoreSellFishManagement(String F_FILE) throws StoreSellFishException {

        this.F_FILE = F_FILE;                         // The URL of data file thats stores fish 
        this.StoreFishs = new ArrayList<StoreFish>(); // The URL of the file containing all the fish data
        this.numberOfFish = 0;                        // Number of fish in list     
    }

    /**
     * Load fish in the fishs.txt file
     *
     * @throws StoreSellFishException
     * @throws IOException
     */
    public void loadFish() throws StoreSellFishException, IOException {
        File FFile = new File(F_FILE);

        if (!FFile.exists()) {      //Checks is file created
            FFile.createNewFile();  //If not, creates new file
            System.out.println("The date file fishs.txt is not exist. "
                    + "Creating new data file fishs.txt... "
                    + "Done!");
            this.numberOfFish = 0; //New data file with the number of fish is 0
        } else {
            //If file is existed, so loading this data file
            System.out.print("\nThe data file fishs.txt is found. "
                    + "Data of fish is loading...");
            //Loads text file into buffer
            try (BufferedReader br = new BufferedReader(new FileReader(FFile))) {
                String No = numberOfFish + "", ID, familyName, importPrice, sellingPrice, origin;
                this.numberOfFish = Integer.parseInt(br.readLine()); //Reads number of fish

                for (int i = 0; i < this.numberOfFish; i++) {
                    //Reads fish's information
                    ID = br.readLine().trim();
                    ids.add(Integer.parseInt(ID));
                    familyName = br.readLine().trim();
                    importPrice = br.readLine().trim();
                    sellingPrice = br.readLine().trim();
                    origin = br.readLine().trim();

                    updateWidth(ID, familyName, importPrice, sellingPrice, origin);
                    //Create new instance of Answer and adds to answer bank
                    this.StoreFishs.add(new StoreFish(Integer.parseInt(ID),
                            familyName,
                            Double.parseDouble(importPrice),
                            Double.parseDouble(sellingPrice),
                            origin));
                }
            }
            System.out.println("Done! [" + this.numberOfFish + " fish]");
        }
    }

    /**
     * update the column's width base on new information
     *
     * @param id
     * @param familyName
     * @param importPrice
     * @param sellingPrice
     * @param origin
     */
    public void updateWidth(String ID, String familyName, String importPrice, String sellingPrice, String origin) {
        widthID = Math.max(widthID, ID.length());
        widthfamilyName = Math.max(widthfamilyName, familyName.length());
        widthimportPrice = Math.max(widthimportPrice, importPrice.length());
        widthsellingPrice = Math.max(widthsellingPrice, sellingPrice.length());
        widthorigin = Math.max(widthorigin, origin.length());

    }

    /**
     * Update the template for printing out table of result
     */
    public void updateTemplate() {
        String col1 = "";
        for (int i = 0; i < widthID + 2; i++) {
            col1 += "-";
        }
        String col2 = "";
        for (int i = 0; i < widthfamilyName + 5; i++) {
            col2 += "-";
        }
        String col3 = "";
        for (int i = 0; i < widthimportPrice + 2; i++) {
            col3 += "-";
        }
        String col4 = "";
        for (int i = 0; i < widthsellingPrice + 2; i++) {
            col4 += "-";
        }
        String col5 = "";
        for (int i = 0; i < widthorigin + 2; i++) {
            col5 += "-";
        }
        barLine = "+" + col1 + "+" + col1 + "+" + col2 + "+" + col3 + "+" + col4 + "+" + col5 + "+";
        template = "| %-" + widthID + "s | %-" + widthID + "s | %-" + widthfamilyName + "s    | %-" + widthimportPrice + "s | %-" + widthsellingPrice + "s | %-" + widthorigin + "s |\n";
    }

    /**
     * Save data when user quit the program
     *
     * @throws IOException
     */
    public void saveFishs() throws IOException {
        FileWriter fw = new FileWriter(new File(F_FILE), false); //Overwrite data file

        try {
            System.out.print("\nFishs is saving into data file Fishs.txt...");
            fw.append(String.valueOf(this.numberOfFish) + "\n"); //Writes number of fish
            for (StoreFish fish : this.StoreFishs) {
                //Inits fish's information
                int ID = fish.getID();
                String family = fish.getFamilyName();
                double importPrice = fish.getImportPrice();
                String origin = fish.getOrigin();
                double sellingPrice = fish.getSellingPrice();

                //Writes fish's information into data file
                fw.append(String.valueOf(ID) + "\n");
                fw.append(family + "\n");
                fw.append(String.valueOf(importPrice) + "\n");
                fw.append(String.valueOf(sellingPrice) + "\n");
                fw.append(origin + "\n");
            }
        } finally {
            fw.close(); //Save data file (from RAM to HDD)
            System.out.println("Done! [" + this.numberOfFish + " Fish]");
        }
    }

    /**
     * display the target list in the table format base on template
     *
     * @param list
     */
    public void displayFish(ArrayList<StoreFish> list) {
        updateTemplate();
        Df.applyPattern(pattern);
        int index = 1;
        System.out.println(barLine);
        System.out.printf(template, "No.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
        System.out.println(barLine);
        for (StoreFish fish : list) {
            System.out.printf(template, index++ + "", fish.getID() + "", fish.getFamilyName(), "$" + Df.format(fish.getImportPrice()), "$" + Df.format(fish.getSellingPrice()), fish.getOrigin());
        }
        System.out.println(barLine);
    }

    /**
     * list all fish
     */
    public void listFish() {
        displayFish(this.StoreFishs);
    }

    /**
     * Add new comic book
     *
     * @param id
     * @param familyname
     * @param importPrice
     * @param sellingPrice
     * @param origin
     * @throws StoreSellFishException
     */
    public void AddFish(int ID, String familyname, Double importPrice, Double sellingPrice, String origin) throws StoreSellFishException {
        updateWidth(ID + "", familyname, importPrice + "", sellingPrice + "", origin);
        //the number of comics increased
        this.numberOfFish++;
        //add new object comic books to the arraylist of comic books       
        this.StoreFishs.add(new StoreFish(ID, familyname, importPrice, sellingPrice, origin));
        //add new ID to set of name
        ids.add(ID);
    }

    /**
     * List fish on sale (importprice > sellingprice)
     *
     */
    public void fishDiscount() {
        //Arraylist result to store result
        ArrayList<StoreFish> result = new ArrayList<StoreFish>();
        //iterating all of fish in storefish
        for (StoreFish fish : this.StoreFishs) {
            if (fish.getImportPrice() > fish.getSellingPrice()) {
                result.add(fish);
            }
        }
        //check and printing out the result
        if (result.size() == 0) {
            System.out.println("There are no fish on sale!");
        } else {
            System.out.println("List of fish on sale:");
            displayFish(result);
        }
    }

    /**
     * Find fish by family name of fish
     *
     * @param query
     */
    public void findByFamilyName(String query) {
        //make keyword lowercase
        String keyword = query.toLowerCase();

        //list to store result
        ArrayList<StoreFish> result = new ArrayList<StoreFish>();

        //iterating all of fish in storefish
        for (StoreFish fish : this.StoreFishs) {
            if (fish.getFamilyName().toLowerCase().contains(keyword) == true) {
                result.add(fish);
            }
        }

        //printing out the result
        if (result.size() == 0) {
            System.out.println("There are no fish whose family name contains the keyword '" + query + "'!");
        } else {
            System.out.println("Result search for family name '" + query + "':");
            displayFish(result);
        }
    }

    /**
     * check if ID is exist (not valid) or not
     *
     * @param id
     * @return
     */
    public boolean checkvalidID(int ID) {
        return !this.ids.contains(ID);
    }

    public void updateSellingPrice(int Id, double newPrice) throws StoreSellFishException {
        //pattern to display tens, hundreds, thousands
        Df.applyPattern(pattern);
        //title for printing out the result purpose
        String resFname = "";
        String buffer = (newPrice + "");
        this.widthsellingPrice = Math.max(this.widthsellingPrice, buffer.length());
        //iterating all of fish in storefish
        for (StoreFish fish : this.StoreFishs) {
            if (fish.getID() == Id) {
                resFname = fish.getFamilyName();

                fish.setSellingPrice(newPrice);
            }
        }

        //announce when update successfully
        System.out.println("\nUpdate the selling price of '" + resFname + "' to $" + newPrice + " successfully!");

        //display the result
        System.out.println("    All fish after update: ");
        displayFish(this.StoreFishs);
    }

    /**
     * Find fish by price (price between min and max)
     *
     * @param min
     * @param max
     */
    public void FindFishbyPrice(double min, double max) {
        //pattern to display tens, hundreds, thousands
        Df.applyPattern(pattern);
        //Arraylist result to store result
        ArrayList<StoreFish> result = new ArrayList();
        //Count to check if fish price between min and max
        int count = 0;
        //iterating all of fish in storefish
        for (StoreFish f : StoreFishs) {
            if (f.getSellingPrice() >= min && f.getSellingPrice() <= max) {
                result.add(f);
                count++;
            }
        }
        //Check and printing out the result
        if (count == 0) {
            System.out.println("There are no fish that sell for between $" + Df.format(min) + " and $" + Df.format(max) + "");
        } else {
            System.out.println("The list of all fish selling price between $" + Df.format(min) + " and $" + Df.format(max) + ":");
            displayFish(result);
        }
    }

    /**
     * Check if fish is already in the list
     *
     * @param familyname
     * @param origin
     * @return
     */
    public boolean CheckNameandOrigin(String familyname, String origin) {
        boolean check = true;
        //iterating all of fish in storefish
        for (StoreFish f : StoreFishs) {
            //If familyname and origin are already exists return check to false
            if ((f.getFamilyName().equalsIgnoreCase(familyname)) && (f.getOrigin().equalsIgnoreCase(origin))) {
                return check = false;
            }
        }
        //If the fish is not exists return true
        return true;
    }
}
