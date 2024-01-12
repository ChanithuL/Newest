import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productID;
    private String productName;
    private int NoOfProductsAvailable;
    private double price;

    public Product(String productID, String productName, int NoOfProductsAvailable, double price) {
        this.productID = productID;
        this.productName = productName;
        this.NoOfProductsAvailable = NoOfProductsAvailable;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfProductsAvailable() {
        return NoOfProductsAvailable;
    }

    public void setNoOfProductsAvailable(int noOfProductsAvailable) {
        NoOfProductsAvailable = noOfProductsAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productID + "Product Name: " + productName + "Available Items: " + NoOfProductsAvailable + "Price: $" + price;
    }
    public abstract String getType();

}
