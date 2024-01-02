import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDisplay implements ActionListener {

    private final JFrame frame = new JFrame();
    private final JComboBox<String> comboBox;
    private final JButton cartButton;
    private final JButton addToCart;
    private final JPanel selectedItemPanel;
    private static JTable productListTable = null;
    private static DefaultTableModel defaultTableModel = null;
    private List<Product> productList = new ArrayList<>();
    private static List<Product> originalProductList;
    public static int nbOfElectronics;
    public static int nbOfClothing;

    ProductsDisplay() {


        String[] categories = {"All", "Electronics", "Clothing"};
        productList = deserializeProducts();
        originalProductList = new ArrayList<>(productList);

        ImageIcon backgroundImage = new ImageIcon("Resources/back2.jpg");
        JLabel text = new JLabel();
        text.setBounds(0, 0, 1200, 970);
        text.setIcon(backgroundImage);

        JLabel title = new JLabel();
        title.setText("Online Shopping System");
        title.setHorizontalAlignment(title.CENTER);
        title.setForeground(new Color(78, 51, 255));
        title.setFont(new Font("ROBOTO", Font.BOLD, 21));
        title.setBounds(370, 0, 400, 50);

        cartButton = new JButton("Shopping Cart");
        cartButton.addActionListener(this);
        cartButton.setFocusable(false);
        cartButton.setFont(new Font("ROBOTO", Font.BOLD, 16));
        cartButton.setForeground(new Color(241, 235, 255));
        cartButton.setBackground(new Color(78, 51, 255));
        cartButton.setBounds(860, 50, 150, 30);
        cartButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel categoryText = new JLabel("Select Product Category");
        categoryText.setBounds(310, 85, 160, 40);
        categoryText.setFont(new Font("ROBOTO", Font.BOLD, 13));

        comboBox = new JComboBox<>(categories);
        comboBox.addActionListener(this);
        comboBox.setPreferredSize(new Dimension(200, 30));
        comboBox.setBounds(480, 90, 200, 30);
        comboBox.setFont(new Font("ROBOTO", Font.BOLD, 13));

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Product ID");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Category");
        defaultTableModel.addColumn("Price (£)");
        defaultTableModel.addColumn("Info");
        defaultTableModel.addColumn("Items available");

        productListTable = new JTable(defaultTableModel);
//        productListTable.getColumnModel().getColumn(2).setCellRenderer(new CustomCellRenderer());
        updateTableModel(productList);
//
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//
//
//        for (int columnIndex = 0; columnIndex < productListTable.getColumnCount(); columnIndex++) {
//            productListTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
//        }


        JTableHeader header = productListTable.getTableHeader();
        header.setBackground(new Color(78, 51, 255));
        Font headerFont = new Font("Roboto", Font.BOLD, 16);
        Color headerFontColor = Color.WHITE;

        productListTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        productListTable.setBorder(BorderFactory.createEmptyBorder());

        Color gridColor = new Color(78, 51, 255);
        productListTable.setGridColor(gridColor);

        int rowHeight = 35;
        productListTable.setRowHeight(rowHeight);

        header.setFont(headerFont);
        header.setForeground(headerFontColor);
        header.setBackground(new Color(78, 51, 255));
        header.setBorder(BorderFactory.createEmptyBorder());

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(defaultTableModel);
        productListTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(productListTable);
        scrollPane.setBounds(115, 200, 900, 230);

        addToCart = new JButton("Add to Shopping Cart");
        addToCart.addActionListener(this);
        addToCart.setFocusable(false);
        addToCart.setFont(new Font("ROBOTO", Font.BOLD, 16));
        addToCart.setForeground(new Color(241, 235, 255));
        addToCart.setBackground(new Color(78, 51, 255));
        addToCart.setBounds(455, 800, 200, 40);
        addToCart.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        addToCart.setEnabled(false);
        addToCart.setToolTipText("Select an item first in order to add");

        JPanel productListPanel = new JPanel();
        productListPanel.setBackground(Color.WHITE);
        productListPanel.setLayout(null);
        productListPanel.setBounds(36, 30, 1115, 872);
        productListPanel.add(title);
        productListPanel.add(cartButton);
        productListPanel.add(comboBox);
        productListPanel.add(categoryText);
        productListPanel.add(scrollPane);
        productListPanel.add(addToCart);

        selectedItemPanel = new JPanel();
        selectedItemPanel.setBackground(new Color(235, 235, 235));
        selectedItemPanel.setLayout(null);
        selectedItemPanel.setBounds(154, 500, 901, 300);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1130, 900);
        layeredPane.add(productListPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(selectedItemPanel, JLayeredPane.PALETTE_LAYER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 970);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().add(layeredPane);
        frame.setResizable(false);
        frame.setTitle("Westminster Shopping Center");
        centerFrameOnScreen(frame);
        frame.add(productListPanel);
        frame.add(text);
        frame.revalidate();
        frame.repaint();

        ListSelectionModel selectionModel = productListTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productListTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get the data of the selected row
                        TableModel model = productListTable.getModel();
                        String productID = model.getValueAt(selectedRow, 0).toString();
                        String productName = model.getValueAt(selectedRow, 1).toString();
                        String category = model.getValueAt(selectedRow, 2).toString();
                        String price = model.getValueAt(selectedRow, 3).toString();
                        String information = model.getValueAt(selectedRow, 4).toString();
                        String availableItems = model.getValueAt(selectedRow, 5).toString();

                        String[] electronicSpecific = information.split(", ");
                        String brand = electronicSpecific[0];
                        String warrantyPeriod = electronicSpecific[1];

                        String[] clothSpecific = information.split(", ");
                        String size = clothSpecific[0];
                        String color = clothSpecific[1];
                        updateSelectedItemPanel(productID, productName, category, price, availableItems, brand,
                                                warrantyPeriod, size, color);
                    }
                }
            }
        });
    }

    private void updateSelectedItemPanel(String productID, String productName, String category,
                                         String price, String availableItems, String brand, String warrantyPeriod,
                                         String size, String color) {
        selectedItemPanel.removeAll();

        JLabel selectedTitle = new JLabel("Selected Product - Details");
        selectedTitle.setFont(new Font("ROBOTO", Font.BOLD, 13));
        selectedTitle.setBounds(30, 30, 200, 20);
        selectedItemPanel.add(selectedTitle);

        JLabel productIDLabel = new JLabel("Product ID: " + productID);
        productIDLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        productIDLabel.setBounds(30, 60, 200, 20);
        selectedItemPanel.add(productIDLabel);

        JLabel categoryLabel = new JLabel("Category: " + category);
        categoryLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        categoryLabel.setBounds(30, 120, 200, 20);
        selectedItemPanel.add(categoryLabel);

        JLabel productNameLabel = new JLabel("Product Name: " + productName);
        productNameLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        productNameLabel.setBounds(30, 90, 200, 20);
        selectedItemPanel.add(productNameLabel);

        JLabel priceLabel = new JLabel("Price: " + price + " £");
        priceLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        priceLabel.setBounds(30, 150, 200, 20);
        selectedItemPanel.add(priceLabel);

        if (category.equals("Clothing")) {
            JLabel clothSize = new JLabel("Size: " + size);
            clothSize.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            clothSize.setBounds(30, 180, 300, 20);
            selectedItemPanel.add(clothSize);

            JLabel clothColor = new JLabel("Color: " + color);
            clothColor.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            clothColor.setBounds(30, 210, 300, 20);
            selectedItemPanel.add(clothColor);

        } else {
            JLabel electronicBrand = new JLabel("Brand: " + brand);
            electronicBrand.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            electronicBrand.setBounds(30, 180, 300, 20);
            selectedItemPanel.add(electronicBrand);

            JLabel electronicWarranty = new JLabel("Warranty in months: " + warrantyPeriod);
            electronicWarranty.setFont(new Font("ROBOTO", Font.PLAIN, 13));
            electronicWarranty.setBounds(30, 210, 300, 20);
            selectedItemPanel.add(electronicWarranty);
        }

        JLabel availableItemsLabel = new JLabel("Available Items: " + availableItems);
        availableItemsLabel.setFont(new Font("ROBOTO", Font.PLAIN, 13));
        availableItemsLabel.setBounds(30, 240, 200, 20);
        selectedItemPanel.add(availableItemsLabel);
        addToCart.setEnabled(true);
        addToCart.setToolTipText(null);
        selectedItemPanel.revalidate();
        selectedItemPanel.repaint();
    }

    public static void updateItemAvailability(String productID, int quantity, boolean isRemoving) {
        for (Product product : originalProductList) {
            if (product.getProductID().equals(productID)) {
                int changeQuantity;
                if (isRemoving) {
                    changeQuantity = quantity;
                } else {
                    changeQuantity = -quantity;
                }
                int availableItems = product.getAvailableItems() + changeQuantity;
                product.setAvailableItems(Math.max(availableItems, 0)); // Ensure non-negative availability

                if (availableItems <= 0) {
                    // If the item is out of stock, update the information
                    product.setAvailableItems(0);
                }
                break;
            }
        }
        updateTableModel(originalProductList);
    }

    private static class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable productListTable, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(productListTable, value, isSelected, hasFocus,
                                                                        row, column);

            // Check if the value is less than 3
            if (value instanceof Integer && (int) value < 3) {
                // Set the background color to red
                component.setBackground(Color.RED);
                component.setForeground(Color.WHITE);
            } else {
                // Reset the background color
                component.setBackground(Color.WHITE);
                component.setForeground(Color.BLACK);
            }
            return component;
        }
    }

    private static void updateTableModel(List<Product> filteredProducts) {
        defaultTableModel.setRowCount(0);

        for (Product product : filteredProducts) {
            defaultTableModel.addRow(new Object[]{
                    product.getProductID(), product.getProductName(),
                    product.getCategory(), product.getPrice(),
                    product.getInformation(), product.getAvailableItems()
            });
        }

        // Force the "Items available" column to be Integer
        TableColumn itemsAvailableColumn = productListTable.getColumn("Items available");
        itemsAvailableColumn.setCellRenderer(new CustomCellRenderer());
    }


    private static ArrayList<Product> deserializeProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Product list.ser"))) {
            // Only load the product list once
            if (originalProductList == null) {
                originalProductList = (ArrayList<Product>) ois.readObject();
            }
            return (ArrayList<Product>) originalProductList;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        int x = (screenWidth - frameWidth) / 2;
        int y = ((screenHeight - frameHeight) / 2) - 30;

        frame.setLocation(x, y);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String selectedCategory = (String) comboBox.getSelectedItem();

            if ("All".equals(selectedCategory)) {
                updateTableModel(productList);
            } else {
                List<Product> filteredProducts = new ArrayList<>();
                for (Product product : productList) {
                    if (selectedCategory.equals(product.getCategory())) {
                        filteredProducts.add(product);
                    }
                }
                updateTableModel(filteredProducts);
            }
            resetRowSorter();
        }


        if (e.getSource() == addToCart) {
            int selectedRow = productListTable.getSelectedRow();
            if (selectedRow != -1) {
                TableModel model = productListTable.getModel();
                String productID = model.getValueAt(selectedRow, 0).toString();
                String productName = model.getValueAt(selectedRow, 1).toString();
                String information = model.getValueAt(selectedRow, 4).toString();
                double price = Double.parseDouble(model.getValueAt(selectedRow, 3).toString());
                String category = model.getValueAt(selectedRow,2).toString();

                // Check if the item is out of stock
                if (isOutOfStock(productID)) {
                    ImageIcon invalidAddAttempt = new ImageIcon("Resources/wrong.png");
                    String[] options = {"Ok"};
                    JOptionPane.showOptionDialog(
                            frame,
                            "This item can not be added more",
                            "Invalid",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            invalidAddAttempt,
                            options,
                            options[0]
                    );
                } else {
                    // Check if the item already exists in the shopping cart
                    ShoppingCart existingItem = findItemById(productID);
                    if (existingItem != null) {
                        // Increment the quantity if the item already exists
                        existingItem.setQuantity(existingItem.getQuantity() + 1);
                        existingItem.setTotalPrice(existingItem.getTotalPrice() + price);
                        if (existingItem.getCategory().equals("Electronics")) {
                            nbOfElectronics++;
                        } else if (existingItem.getCategory().equals("Clothing")) {
                            nbOfClothing++;
                        }
                    } else {
                        ShoppingCart shoppingItem = new ShoppingCart(productID, productName, information, 1, price, category);
                        shoppingItem.setTotalPrice(price);
                        if (shoppingItem.getCategory().equals("Electronics")) {
                            nbOfElectronics++;
                        } else if (shoppingItem.getCategory().equals("Clothing")) {
                            nbOfClothing++;
                        }
                        ShoppingCart.addProduct(shoppingItem);

                    }

                    // Update item availability in productList
                    updateItemAvailability(productID, 1, false);

                    // Update the table model
                    updateTableModel(productList);
                }
            }
        }
            if (e.getSource() == cartButton) {

                if (ShoppingCart.getShoppingList().isEmpty()) {
                    ImageIcon invalidAddAttempt = new ImageIcon("Resources/wrong.png");
                    String[] options = {"Ok"};
                    JOptionPane.showOptionDialog(
                            frame,
                            "You need to add a product to the cart first",
                            "Invalid",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            invalidAddAttempt,
                            options,
                            options[0]
                    );
                } else {
                    frame.dispose();
                    ShoppingCartGui shoppingCartGui = new ShoppingCartGui(ShoppingCart.getShoppingList());
                }
            }
        }

    private boolean isOutOfStock(String productID) {
        for (Product product : originalProductList) {
            if (product.getProductID().equals(productID) && product.getAvailableItems() <= 0) {
                return true; // Item is out of stock
            }
        }
        return false; // Item is available
    }

    private ShoppingCart findItemById(String productID) {
        for (ShoppingCart item : ShoppingCart.getShoppingList()) {
            if (item.getProductID().equals(productID)) {
                return item;
            }
        }
        return null;
    }

    private void resetRowSorter() {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) productListTable.getRowSorter();
        sorter.setSortKeys(null);
    }
}