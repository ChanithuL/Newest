import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.*;



interface  ShoppingManager {
    void Add();
    void Remove();

    void SaveList(String Fname, List<Product> products);

    void LoadList(String Fname);
}

public class WestminsterShoppingManager implements ShoppingManager{

    public static final Object[] COLUMN_NAMES = {"Product ID", "Name","Category", "Price(£)","info"};
    private static final String DATABASE_FILENAME = "Saves.dat";
    public List<Product> ListOfProducts;
    private int MaxItems = 50;
    private GUI gui;


    public WestminsterShoppingManager() {
        this.ListOfProducts = new ArrayList<>();

    }
    public void setGUI(GUI gui) {
        this.gui = gui;
    }
    public void Menu(){
        Scanner input = new Scanner(System.in);
        int choice =-1;
        while(choice!=0){
            System.out.println("******Menu******");
            System.out.println();
            System.out.println("1: Add product");
            System.out.println("2: Remove product");
            System.out.println("3: Print list of products");
            System.out.println("4: Save in file");
            System.out.println("5: Load from file");
            System.out.println("6: Open the GUI");
            System.out.println("0: Exit");
            System.out.println("Enter a choice: ");

            while (!input.hasNextInt()) {
                System.out.println("Input an integer, wrong input!.");
                input.next(); // Consume the invalid input
            }
            choice = input.nextInt();
            switch(choice){
                case 1:
                    Add();
                    break;
                case 2:
                    Remove();
                    break;
                case 3:
                    PrintList();
                    break;
                case 4:
                    SaveList(DATABASE_FILENAME, this.getListOfProducts());
                    break;
                case 5:
                    LoadList(DATABASE_FILENAME);
                    break;
                case 6:
                    if (this.gui != null) break;

                    this.setGUI(new GUI(this));
                    gui.updateTable();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    return;
            }
        }
    }


    @Override
    public void Add(){
        if(ListOfProducts.size()<MaxItems) {
            Product product = getInput();
            ListOfProducts.add(product);
            System.out.println("Product added to list");
            System.out.println(ListOfProducts.get(0));

            // Update the GUI if it exists
            if (this.gui != null) {
                gui.updateTable();
            }
        }else{
            System.out.println("List is already full");
        }
    }


    @Override
    public void Remove() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter product to remove");
        String ProductR = input.nextLine();
        for(int i=0;i<ListOfProducts.size();i++){
            if(ProductR.equals(ListOfProducts.get(i).getProductID())){
                ListOfProducts.remove(i);
                System.out.println("Product removed");

                // Update the GUI if it exists
                if (this.gui != null) {
                    gui.updateTable();
                }
                break;
            }
            else{
                System.out.println("Product wasnt removed");
            }
        }


    }
    public List<Product> getListOfProducts() {
        return ListOfProducts;
    }




    public Product getInput(){
       Scanner input = new Scanner(System.in);

       System.out.println("What type of category is the product, Choose 'E' or 'C': ");
       String category = input.nextLine();

       System.out.println("Enter the product ID: ");
       String productID = input.nextLine();

       System.out.println("Enter the product name: ");
       String productName = input.nextLine();

       System.out.println("Enter the stock: ");
       int stock = Integer.parseInt(input.nextLine());

       System.out.println("Enter the price: ");
       double price = Double.parseDouble(input.nextLine());

       switch (category.toUpperCase()) {
           case "E":
               System.out.println("Enter the brand: ");
               String brand = input.nextLine();

               System.out.println("Enter the warranty: ");
               double warranty = Double.parseDouble(input.nextLine());

               return new Electronics(productID, productName, stock, price, brand, warranty);
           case "C":
               System.out.println("Enter the size: ");
               char size = input.nextLine().charAt(0);

               System.out.println("Enter the color: ");
               String color = input.nextLine();

               return new Clothing(productID, productName, stock, price, size, color);
       }
       return null;
    }

    public void PrintList(){
        Collections.sort(ListOfProducts, (p1, p2) -> p1.getProductID().compareToIgnoreCase(p2.getProductID()));
        for(Product product: ListOfProducts){
            System.out.println("Product ID: "+product.getProductID());
            System.out.println("Product Name: "+product.getProductName());
            System.out.println("Product Price: "+product.getPrice());
            System.out.println("Number of product available: "+product.getNoOfProductsAvailable());
            System.out.println("Product type is: "+product.getType());
            if (product instanceof Electronics) {
                Electronics electronics = (Electronics) product;
                System.out.println("Brand: " + electronics.getBrand());
                System.out.println("Warranty: " + electronics.getWarranty());
            } else if (product instanceof Clothing) {
                Clothing clothing = (Clothing) product;
                System.out.println("Size: " + clothing.getSize());
                System.out.println("Color: " + clothing.getColour());
            }
            System.out.println("--------------------");
        }
    }
    public void SaveList(String Fname, List<Product> products) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(Fname))) {
            objectOut.writeObject(this.getListOfProducts()); // Write each product object to the file
            System.out.println("Product information saved to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void LoadList(String Fname) {
        try (FileInputStream fileIn = new FileInputStream(Fname);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            List<Product> loadedProducts = (List<Product>) objectIn.readObject();

            ListOfProducts.addAll(loadedProducts);  // Add the loaded products to the list
            System.out.println("Loaded list:");
            PrintList();  // Optionally, print the loaded products

            // Update the GUI if it exists
            if (this.gui != null) {
                gui.updateTable();
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading list: " + e.getMessage());
        }
    }





}
