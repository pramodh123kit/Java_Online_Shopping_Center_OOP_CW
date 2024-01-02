import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingCartGui implements ActionListener {

    JFrame frame = new JFrame();
    private final JButton backToProductList;
    private final JButton removeItem;
    private final JButton purchaseBtn;
    private final JTable shoppingCartTable;
    private final JLabel totalCostTextArea;
    private final JLabel sameCategoryDiscount;
    private final JLabel firstTimePurchaseDiscount;
    private final JLabel finalCostLabel;

    ShoppingCartGui(List<ShoppingCart> shoppingList) {

        JLabel title = new JLabel();
        title.setText("Shopping Cart");
        title.setHorizontalAlignment(title.CENTER);
        title.setForeground(new Color(78, 51, 255));
        title.setFont(new Font("ROBOTO", Font.BOLD, 21));
        title.setBounds(348, 0, 400, 50);

        backToProductList = new JButton("Back to shopping");
        backToProductList.addActionListener(this);
        backToProductList.setFocusable(false);
        backToProductList.setFont(new Font("ROBOTO", Font.BOLD, 16));
        backToProductList.setForeground(new Color(241, 235, 255));
        backToProductList.setBackground(new Color(78, 51, 255));
        backToProductList.setBounds(829, 47, 170, 30);
        backToProductList.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        removeItem = new JButton("Remove Item");
        removeItem.addActionListener(this);
        removeItem.setFocusable(false);
        removeItem.setFont(new Font("ROBOTO", Font.BOLD, 16));
        removeItem.setForeground(new Color(241, 235, 255));
        removeItem.setBackground(new Color(78, 51, 255));
        removeItem.setBounds(100, 47, 130, 30);
        removeItem.setEnabled(false);
        removeItem.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        purchaseBtn = new JButton("Purchase");
        purchaseBtn.addActionListener(this);
        purchaseBtn.setFocusable(false);
        purchaseBtn.setFont(new Font("ROBOTO", Font.BOLD, 16));
        purchaseBtn.setForeground(new Color(241, 235, 255));
        purchaseBtn.setBackground(new Color(78, 51, 255));
        purchaseBtn.setBounds(340, 245, 120, 30);
        purchaseBtn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        ImageIcon backgroundImage = new ImageIcon("Resources/back2.jpg");
        JLabel text = new JLabel();
        text.setBounds(0, 0, 1200, 800);
        text.setIcon(backgroundImage);

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Product");
        defaultTableModel.addColumn("Quantity");
        defaultTableModel.addColumn("Price (£)");

        shoppingCartTable = new JTable(defaultTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int columnIndex = 0; columnIndex < shoppingCartTable.getColumnCount(); columnIndex++) {
            shoppingCartTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }

        for (ShoppingCart cartItem : shoppingList) {
            // Calculate total price (you may need to adjust this based on your ShoppingCart class)
            double totalPrice = cartItem.getQuantity() * cartItem.getPrice();

            // Create a single string with line breaks for each row
            String rowData = String.format("%s %s %s",
                    cartItem.getProductID(), cartItem.getProductName(), cartItem.getInformation());

            // Add a row with the product details, quantity, and total price
            defaultTableModel.addRow(new Object[]{rowData, cartItem.getQuantity(), totalPrice});
        }

        JTableHeader header = shoppingCartTable.getTableHeader();
        header.setBackground(new Color(78, 51, 255));

        Font headerFont = new Font("Roboto", Font.BOLD, 16);
        Color headerFontColor = Color.WHITE;

        shoppingCartTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        shoppingCartTable.setBorder(BorderFactory.createEmptyBorder());

        Color gridColor = new Color(78, 51, 255);
        shoppingCartTable.setGridColor(gridColor);

        int rowHeight = 35;
        shoppingCartTable.setRowHeight(rowHeight);

        header.setFont(headerFont);
        header.setForeground(headerFontColor);
        header.setBackground(new Color(78, 51, 255));
        header.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);
        scrollPane.setBounds(100, 110, 900, 230);

        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setLayout(null);
        cartPanel.setBounds(40, 30, 1100, 700);
        cartPanel.add(title);
        cartPanel.add(scrollPane);
        cartPanel.add(backToProductList);
        cartPanel.add(removeItem);

        JPanel productsCostPanel = new JPanel();
        productsCostPanel.setBackground(new Color(235, 235, 235));
        productsCostPanel.setLayout(null);
        productsCostPanel.setBounds(169, 400, 901, 300);

        totalCostTextArea = new JLabel();
        totalCostTextArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        totalCostTextArea.setBounds(500, 20, 300, 30);

        firstTimePurchaseDiscount = new JLabel();
        firstTimePurchaseDiscount.setFont(new Font("Roboto", Font.PLAIN, 14));
        firstTimePurchaseDiscount.setBounds(338, 60, 650, 30);

        sameCategoryDiscount = new JLabel();
        sameCategoryDiscount.setFont(new Font("Roboto", Font.PLAIN, 14));
        sameCategoryDiscount.setBounds(217, 100, 700, 30);

        finalCostLabel = new JLabel();
        finalCostLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        finalCostLabel.setBounds(460, 150, 300, 30);

        updateTotalCost(shoppingList);

        productsCostPanel.add(totalCostTextArea);
        productsCostPanel.add(sameCategoryDiscount);
        productsCostPanel.add(firstTimePurchaseDiscount);
        productsCostPanel.add(finalCostLabel);
        productsCostPanel.add(purchaseBtn);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 700);
        layeredPane.add(cartPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(productsCostPanel, JLayeredPane.PALETTE_LAYER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().add(layeredPane);
        frame.setResizable(false);
        LoginPage.centerFrameOnScreen(frame);
        frame.add(cartPanel);
        frame.add(text);
        frame.revalidate();
        frame.repaint();
    }

    public static void updateCategoryCounts(ShoppingCart shoppingItem, boolean increment) {
        String category = shoppingItem.getCategory();

        if ("Electronics".equals(category)) {
            if (increment) {
                ProductsDisplay.nbOfElectronics += shoppingItem.getQuantity();
            } else {
                ProductsDisplay.nbOfElectronics -= shoppingItem.getQuantity();
            }
        } else if ("Clothing".equals(category)) {
            if (increment) {
                ProductsDisplay.nbOfClothing += shoppingItem.getQuantity();
            } else {
                ProductsDisplay.nbOfClothing -= shoppingItem.getQuantity();
            }
        }
    }
    private void updateTotalCost(List<ShoppingCart> shoppingList) {
        double totalCost = ShoppingCart.calculateTotalCost(shoppingList);
        String addedCostLabel = String.format("Total            %.2f", totalCost);
        totalCostTextArea.setText(addedCostLabel + " £");

        double firstTimeDiscountAmount = 0;
        double sameCateDisAmount = 0;

        if (LoginPage.firstTimeSigned) {
            firstTimeDiscountAmount = (totalCost / 100) * 10;
            String firstTimeDiscountLabel = String.format("            - %.2f", firstTimeDiscountAmount);
            firstTimePurchaseDiscount.setText("First Purchase Discount (10%)" + firstTimeDiscountLabel + " £");

            if (hasThreeItemsInSameCategory(shoppingList)) {
                sameCateDisAmount = (totalCost / 100) * 20;
                String sameCategoryDiscountLabel = String.format("            - %.2f", sameCateDisAmount);
                sameCategoryDiscount.setText("Three items in the same Category Discount (20%)" + sameCategoryDiscountLabel + " £");
            } else {
                // Remove the discount label if there are less than three items in the same category
                sameCategoryDiscount.setText("");
            }
        } else {
            if (hasThreeItemsInSameCategory(shoppingList)) {
                sameCateDisAmount = (totalCost / 100) * 20;
                String sameCategoryDiscountLabel = String.format("            - %.2f", sameCateDisAmount);
                sameCategoryDiscount.setText("Three items in the same Category Discount (20%)" + sameCategoryDiscountLabel + " £");
                sameCategoryDiscount.setBounds(217, 60, 650, 30);
            } else {
                // Remove the discount label if there are less than three items in the same category
                sameCategoryDiscount.setText("");
            }
        }

        double finalAmount = totalCost - firstTimeDiscountAmount - sameCateDisAmount;
        String finalAmountLabel = String.format("             %.2f", finalAmount);
        finalCostLabel.setText("Final Cost" + finalAmountLabel + " £");
    }

    private boolean hasThreeItemsInSameCategory(List<ShoppingCart> shoppingList) {
        int electronicsCount = 0;
        int clothingCount = 0;

        for (ShoppingCart cartItem : shoppingList) {
            String category = cartItem.getCategory();

            if ("Electronics".equalsIgnoreCase(category)) {
                electronicsCount += cartItem.getQuantity();
            } else if ("Clothing".equalsIgnoreCase(category)) {
                clothingCount += cartItem.getQuantity();
            }
        }

        return electronicsCount >= 3 || clothingCount >= 3;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToProductList) {
            frame.dispose();
            ProductsDisplay productsDisplay = new ProductsDisplay();
        }
        if (e.getSource() == purchaseBtn) {
            frame.dispose();
            FinalPurchasedGui finalPurchasedGui = new FinalPurchasedGui();
        }
        if (e.getSource() == removeItem) {
            int selectedRow = shoppingCartTable.getSelectedRow();

            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) shoppingCartTable.getModel();
                String rowData = (String) model.getValueAt(selectedRow, 0);

                String productId = rowData.split(" ")[0];

                for (ShoppingCart shoppingItem : ShoppingCart.getShoppingList()) {
                    if (shoppingItem.getProductID().equals(productId)) {

                        int quantity = shoppingItem.getQuantity();
                        if (quantity > 1) {
                            shoppingItem.setQuantity(quantity - 1);
                            model.setValueAt(quantity - 1, selectedRow, 1);
                            ProductsDisplay.updateItemAvailability(shoppingItem.getProductID(),1,true);
                        } else {
                            model.removeRow(selectedRow);
                            ShoppingCart.removeProduct(shoppingItem);
                            ProductsDisplay.updateItemAvailability(shoppingItem.getProductID(),1,true);
                        }
                        updateTotalCost(ShoppingCart.getShoppingList());
                        break;
                    }
                }
            }
        }
    }
}