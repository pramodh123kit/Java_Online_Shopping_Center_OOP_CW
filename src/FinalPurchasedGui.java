import javax.swing.*;
import java.awt.*;

public class FinalPurchasedGui {
    FinalPurchasedGui() {
        JFrame frame = new JFrame();

        ImageIcon image = new ImageIcon("Resources/bag.png");
        JLabel label = new JLabel();
        label.setText("Thank you for your purchase!");
        label.setIcon(image);
        label.setFont(new Font("Robot", Font.BOLD,22));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setIconTextGap(20);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(new Color(100, 93, 192));
        label.setBounds(0,0,470,440);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        LoginPage.centerFrameOnScreen(frame);
        frame.add(label);
        frame.setLayout(null);
    }
}
