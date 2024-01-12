public class Clothing extends Product{
    private char size;
    private String colour;

    public Clothing(String productID, String productName, int NoOfProductsAvailable, double price,char size, String colour) {
        super(productID,productName,NoOfProductsAvailable,price);
        this.size = size;
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    @Override
    public String getType(){
        return "Clothing";
    }

    @Override
    public String toString() {
        return "Product ID: " + getProductID() + "\nProduct Name: " + getProductName() + "\nAvailable Items: " + getNoOfProductsAvailable() + "\nPrice: $" + getPrice() +
            "\nSize: " + size + "\nColour: " + colour;

    }
}
