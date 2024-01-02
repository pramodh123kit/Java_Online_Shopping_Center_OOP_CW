import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testAddProduct() {
        // You can simulate user input for testing
        String simulatedInput = "1\nTestID\nTestProduct\n10\n20.5\nTestBrand\n12\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.addProduct();

        List<Product> productList = shoppingManager.getProductList();
        assertEquals(1, productList.size());
        assertEquals("TestID", productList.get(0).getProductID());
    }

    @Test
    public void testDeleteProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Add a product for testing
        Electronics electronics = new Electronics("TestID", "TestProduct", 10, 20.5,
                "Electronics", "TestBrand, 12", "TestBrand", 12);
        shoppingManager.getProductList().add(electronics);

        // You can simulate user input for testing
        String simulatedInput = "TestID\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        shoppingManager.deleteProduct();

        List<Product> productList = shoppingManager.getProductList();
        assertTrue(productList.isEmpty());
    }

    @Test
    public void testPrintProductList() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Add a product for testing
        Electronics electronics = new Electronics("TestID", "TestProduct", 10, 20.5,
                "Electronics", "TestBrand, 12", "TestBrand", 12);
        shoppingManager.getProductList().add(electronics);

        shoppingManager.printProductList();

        String expectedOutput = "---------|Product List|---------\n\n";
        assertTrue(outputStreamCaptor.toString().contains(expectedOutput));
    }

    @Test
    public void testSaveToFile() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Add a product for testing
        Electronics electronics = new Electronics("TestID", "TestProduct", 10, 20.5,
                "Electronics", "TestBrand, 12", "TestBrand", 12);
        shoppingManager.getProductList().add(electronics);

        shoppingManager.saveToFile();

        // You may want to check the file existence or content after saving
        // Example: assertFileContent("Product list.ser", "TestID");
    }

    // Add more tests for other methods as needed
}
