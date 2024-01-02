import java.io.Serializable;

public abstract class Product implements Serializable{
    private String productID;
    private String productName;
    private int availableItems;
    private double price;
    private String category;
    private String information;

    public Product(String productID, String productName, int availableItems, double price, String category, String information){
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.category = category;
        this.information = information;
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

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public String getPrice() {
        return String.format("%.2f", price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void printInfo() {
        System.out.println("- " + getCategory()+ " item -");
        System.out.println("  Product ID= " + getProductID());
        System.out.println("  Product name= " + getProductName());
        System.out.println("  No.of available items= " + getAvailableItems());
        System.out.println("  Product price= " + getPrice() + " £");
    }
}