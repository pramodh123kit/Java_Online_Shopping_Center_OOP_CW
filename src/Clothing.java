public class Clothing extends Product {
    private double Size;
    private String color;

    public Clothing(String productID, String productName, int availableItems, double price, String category,
                    String information, double size, String color) {
        super(productID, productName, availableItems, price, category, information);
        this.Size = size;
        this.color = color;
    }

    public double getSize() {
        return Size;
    }
    public void setSize(double size) {
        Size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("  Cloth size= " + getSize());
        System.out.println("  Cloth color= " + getColor());
    }
}
