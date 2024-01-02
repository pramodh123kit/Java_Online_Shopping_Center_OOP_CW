import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final String productID;
    private final String productName;
    private final String information;
    private int quantity;
    private final double price;
    private double totalPrice;
    private final String category;
    private static final List<ShoppingCart> shoppingList = new ArrayList<>();


    public ShoppingCart(String productID, String productName, String information, int quantity, double price, String category) {
        this.productID = productID;
        this.productName = productName;
        this.information = information;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getInformation() {
        return information;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static List<ShoppingCart> getShoppingList() {
        return shoppingList;
    }

    public static void addProduct(ShoppingCart shoppingItem) {
        shoppingList.add(shoppingItem);
        ShoppingCartGui.updateCategoryCounts(shoppingItem, true);
    }

    public static void removeProduct(ShoppingCart shoppingItem) {
        shoppingList.remove(shoppingItem);
        ShoppingCartGui.updateCategoryCounts(shoppingItem, false);
    }

    public static double calculateTotalCost(List<ShoppingCart> shoppingList) {
        double totalCost = 0.0;
        for (ShoppingCart cartItem : shoppingList) {
            totalCost += cartItem.getQuantity() * cartItem.getPrice();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", information='" + information + '\'' +
                ", quantity=" + quantity +
                ", price=" + totalPrice +
                '}';
    }
}