import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in); // Creating a Scanner object
        System.out.println("\n" + " " + "=".repeat(60));
        System.out.println("\t\t\t\t\tOnline Shopping System \n" +  " " + "=".repeat(60));

        int option;
        while (true) {

            // PRINTING MENU OPTIONS.
            System.out.println("\n-------------------------|MAIN MENU|-------------------------");
            System.out.println("\t 1) Manager Console");
            System.out.println("\t 2) User GUI");
            System.out.println("\t 0) Quit \n" + "-".repeat(61));

            System.out.print("Enter your choice: ");
            try {
                // Getting the menu option from user.
                option = input.nextInt();
                System.out.println();
                // If user enters 0, it will quit the program.
                if (option == 0) {
                    System.out.println("You've successfully quit..");
                    break;
                }

                switch (option) {  // Cases for user's menu option
                    case 1 -> {
                        WestminsterShoppingManager manager = new WestminsterShoppingManager();
                        manager.start();
                    }
                    case 2 -> {
                        if (fileExists()) {
                            UserGUI gui = new UserGUI();
                            break;
                        } else {
                            System.out.println("There are no products added by the manager");
                        }
                    }
                    default -> System.out.println("Option out of bounds. Try again.");
                }
                // Showing errors for user input mistakes.
            } catch (InputMismatchException e) {
                System.out.println("Error! Enter an integer");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                input.nextLine();
            }
        }

//        UserGUI gui = new UserGUI();

    }

    private static boolean fileExists() {
        File file = new File("Product list.ser");
        return file.exists() ;
    }
}
