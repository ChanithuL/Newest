import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> ListOfProducts;

    public ShoppingCart(List<Product> listOfProducts) {
        this.ListOfProducts = listOfProducts;
    }
    public void Add(Product product){
        ListOfProducts.add(product);

    }
    public void Remove(String productID){
        ListOfProducts.remove(productID);

    }
    public double CalculateCost(){
        double total = 0.0;

        for (Product product : ListOfProducts) {
            total += product.getPrice();
        }
        return total;
    }
}
