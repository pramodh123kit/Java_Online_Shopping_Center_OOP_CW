public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int availableItems, double price, String category,
                       String information, String brand, int warrantyPeriod) {
        super(productID, productName, availableItems, price, category, information);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("  Product brand= " + getBrand());
        System.out.println( "  Warranty period= " + getWarrantyPeriod());
    }
}
