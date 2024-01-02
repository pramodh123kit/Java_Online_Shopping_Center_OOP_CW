import java.util.*;
import java.io.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private static final Scanner input = new Scanner(System.in); // Creating a Scanner object
    private static final List<Product> productList = new ArrayList<>(); // Initializing a list to store products

    /**
     * This method acts as a starting point for the Manager console. The menu provides options
     * for adding a new product, deleting a product, printing the list of products, saving the
     * list of products into a file.
     * When the application runs from start, that saved file gets loaded in and updates the pre
     * added products into the current product list, allowing the manager to continue changing
     * the products.
     * The number of products that are already existing are shown below the menu options.

     * Option 1: Add a product
     * Option 2: Delete a product
     * Option 3: Print the list of the products
     * Option 4: Save the products into a file
     * Option 0: Exits the Manager console and return to Main Menu
     */
    public void start() {

        readFromFile(); // Read back the products that were saved
        int option;
        while (true) {

            // PRINTING MENU OPTIONS.
            System.out.println("\n------------------------|MANAGER MENU|-----------------------");
            System.out.println("Please select an option:");
            System.out.println("\t 1) Add a new product ");
            System.out.println("\t 2) Delete a product ");
            System.out.println("\t 3) Print the products ");
            System.out.println("\t 4) Save products ");
            System.out.println("\t 0) Return Main menu \n" + "-".repeat(61));
            System.out.println("No.of items = " + productList.size());

            System.out.print("Enter option: ");

            try {
                Scanner input = new Scanner(System.in);
                // Getting the menu option from the manager.
                option = input.nextInt();
                System.out.println();
                // If manager enters 0, it will go back to main menu.
                if (option == 0) {
                    System.out.println("You're returning to Main menu..");
                    break;
                }

                switch (option) {  // Cases for manager's menu option
                    case 1 -> addProduct();
                    case 2 -> deleteProduct();
                    case 3 -> printProductList();
                    case 4 -> saveToFile();
                    default -> System.out.println("Option out of bounds. Try again.");
                }
                // Showing errors for manager input mistakes.
            } catch (InputMismatchException e) {
                System.out.println("Error! Enter an integer");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                input.nextLine();
            }
        }
    }

    /**
     * When this method is called, first it will check if the product list limit has been reached or
     * not. If yes, it will display the error message accordingly and if not, it will continue with
     * the rest of operations in the method.

     * When the manager wants to add a product he/she has to choose the category of the product first.
     * Then enter other relevant information regarding that specific category to create an instance of
     * that category and add it into the productList arrayList.
     * All the manager input validations have been added.
     */
    @Override
    public void addProduct(){

        // Checking if the product list limit has been reached or not
        if (productList.size() < 50) {
            System.out.println("Select the product type: ");
            System.out.println("\t1. Electronics");
            System.out.println("\t2. Clothing");

            System.out.print("Enter your choice: ");
            int productType = input.nextInt();

            // If the manager selects either an electronic item or clothing item
            if (productType == 1 || productType == 2) {
                System.out.println();
                String productID = validateStringInput("Enter product ID: ");
                // Calling the uniqueIDChecker() method to know if the product ID is unique or not
                if (uniqueIDChecker(productID)) {
                    return;
                }
                // Validating manager inputs
                String productName = validateStringInput("Enter product name: ");
                int availableItems = validateIntInput("Enter No. of available items: ");
                double price = validateDoubleInput("Enter price (£): ", "Error! Enter a numeric price");


                switch (productType) {
                    case 1 -> {
                        String brand = validateStringInput("Enter brand: ");
                        int warrantyPeriod = validateIntInput("Enter warranty period (in months): ");
                        String information = brand + ", " + warrantyPeriod;
                        String category = "Electronics";
                        System.out.println();
                        // Creates an instance of the Electronics class
                        Electronics electronic = new Electronics(productID, productName, availableItems, price,
                                category, information, brand, warrantyPeriod);
                        // Adding the instance of the electronic class into the productList arrayList
                        productList.add(electronic);
                        System.out.println("Product added successfully!");
                    }
                    case 2 -> {
                        double size = validateDoubleInput("Enter size: ", "Error! Enter a size");
                        String color = validateStringInput("Enter color: ");
                        String information = size + ", " + color;
                        String category = "Clothing";
                        System.out.println();
                        System.out.println();
                        // Creates an instance of the Clothing class
                        Clothing clothing = new Clothing(productID, productName, availableItems, price, category,
                                information, size, color);
                        // Adding the instance of the electronic class into the productList arrayList
                        productList.add(clothing);
                        System.out.println("Product added successfully!");
                    }
                }
                // If not, it will display an error message asking the manager to enter again
            } else {
                System.out.println("Out of bounds, Choose between 1 or 2");
            }
        } else {
            System.out.println("Max number of products has been reached. Going back to manager's menu...");
        }
    }

    /**
     * This method acts as the product remover from the product list. When this method is called
     * and if the product list empty, the relevant error message will appear saying
     * "No products in the list to be deleted...".
     * If the product list is not empty then the manager will be asked on which product he/she
     * wants to delete by entering the productID of that specific item.
     * After deletion, the deleted product's information will be displayed alongside with how
     * many products remaining in the product list.
     */
    @Override
    public void deleteProduct() {
        // Checking if the product list is empty or not
        int length = productList.size();
        if (!productList.isEmpty()) {
            System.out.print("Enter the product ID you wish to delete: ");
            String option = input.next();

            for (Product item : productList) {
                if (item.getProductID().equals(option)) {
                    System.out.println("\nPRODUCT THAT GOT REMOVED\n");
                    item.printInfo();
                    productList.remove(item);
                    System.out.println("\nProduct removed successfully!");
                    System.out.println("The No.of products left: " + productList.size());
                    input.nextLine();
                    return;
                }
            }
            if (length == productList.size()) {
                // If the loop completes and no product is found
                System.out.println("\nProduct doesn't exist...returning to menu");
            }
        } else {
            System.out.println("No products in the list to be deleted...");
        }
    }


    /**
     * Prints the list of products. If the product list is not empty, it sorts the products alphabetically
     * by product ID. Then the product's information will be printed by calling the printInfo() method from
     * the Product class.
     * If the product list is empty, it will display the error message accordingly.
     */
    @Override
    public void printProductList() {
        // Checking if the product list is empty or not
        if (!productList.isEmpty()) {
            // Sort the product list alphabetically by product ID
            productList.sort(Comparator.comparing(Product::getProductID));

            System.out.println("---------|Product List|---------\n");

            for (Product product : productList) {
                // Calling the printInfo() method from the Product class to print the information about the product
                product.printInfo();
                System.out.println();
            }
        } else {
            System.out.println("No products added to display...");
        }
    }


    /**
     * If the product list is empty, it will display the error message accordingly. If not, this method will
     * write the information of the productList into a new file that is serialized called "Product list.ser"
     * If the product list is empty this will not be saved into a file and an error message will be displayed
     * accordingly.
     */
    @Override
    public void saveToFile() {
        // Checking if the product list is empty or not
        if (!productList.isEmpty()) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Product list.ser"))) {
                // Writing the information in the productList arrayList into the new file
                outputStream.writeObject(productList);
                System.out.println("Product list saved to file.");
            } catch (IOException e) {
                System.out.println("Error occurred while saving...");
            }
            // If empty, it won't save to the file as there is nothing to save
        } else {
            System.out.println("There are no products in the list to save..returning to menu");
        }
    }


    /**
     * Reads the list of products from the serialized file "Product list.ser". If the file exists it reads the
     * serialized product list and updates the current product list.
     */
    private static void readFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Product list.ser"))) {
            /* Reading the information in the file that was created previously and creating a new arrayList with
             updated information */
            List<Product> savedProductList = (List<Product>) inputStream.readObject();
            productList.addAll(savedProductList);
        } catch (IOException | ClassNotFoundException e) {
            // Ignoring the error in case this is the first time manager is running the console
        }
    }

    /**
     * This method checks the availability of the product ID in the product list.
     * @param productID This method takes the productID as the input in order to check if the ID already exists.
     * @return If the product ID doesn't exist, this will be returned, so it will loop out of the method.
     */
    public static boolean uniqueIDChecker(String productID) {

        // Looping through the productList arrayList to find if the product ID already existing or not
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                System.out.println("Product ID already exists..returning to menu");
                return true;
            }
        }
        return false;
    }


    /**
     * This method validates if the manager inputted a String value or not
     * @param message takes the input from the manager
     * @return if it contains a String value, then it gets returned
     */
    public static String validateStringInput(String message) {
        String inputString;
        do {
            System.out.print(message);
            inputString = input.next();
        } while (inputString.isEmpty());
        return inputString;
    }

    /**
     * This method validates if the manager inputted an integer value or not
     * @param message takes the input from the manager
     * @return this returns the integer value if it is a positive number
     */
    public static int validateIntInput(String message) {
        int inputInt;
        do {
            System.out.print(message);
            while (!input.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                System.out.print(message);
                input.next();
            }
            inputInt = input.nextInt();
            input.nextLine(); // Consume the newline character
        } while (inputInt <= 0);
        return inputInt;
    }

    /**
     * This method validates if the manager inputted a double value or not
     * @param message takes the input from the manager
     * @param error the error code, it should display if it returns false
     * @return this returns the double value
     */
    public static double validateDoubleInput(String message, String error) {
        double inputDouble;
        do {
            System.out.print(message);
            while (!input.hasNextDouble()) {
                System.out.println(error);
                System.out.print(message);
                input.next();
            }
            inputDouble = input.nextDouble();
            input.nextLine(); // Consume the newline character
        } while (inputDouble <= 0);
        return inputDouble;
    }

    public List<Product> getProductList() {
        return productList;
    }
}