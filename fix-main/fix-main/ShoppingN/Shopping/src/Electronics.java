public class Electronics extends Product {
    private String brand;
    private double warranty;

    public Electronics(String productID, String productName, int NoOfProductsAvailable, double price,String brand,double warranty){
        super(productID,productName,NoOfProductsAvailable,price);
        this.brand = brand;
        this.warranty = warranty;

    }
    public String getBrand(){
        return brand;
    }
    public double getWarranty(){
        return warranty;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setWarranty(double warranty){
        this.warranty = warranty;
    }
    @Override
    public String getType(){
        return "Electronics";
    }

    @Override
    public String toString() {
        return "Product ID: " + getProductID() + "\nProduct Name: " + getProductName() + "\nAvailable Items: " + getNoOfProductsAvailable() + "\nPrice: $" + getPrice() +
                "\nBrand: " + brand + "\nWarranty: " + warranty;
    }
}
