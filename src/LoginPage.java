import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LoginPage implements ActionListener {
    List<User> userList;
    JFrame frame = new JFrame();
    JPanel signInPanel;
    JPanel logInPanel;
    JLabel text;
    public static boolean firstTimeSigned;

    // LOG IN PAGE COMPONENTS
    JLabel logInTitle = new JLabel("Log In");
    JButton loginButton = new JButton("Login");
    JLabel signUpPrompt = new JLabel("Don't have an account?");
    JButton signUpButton = new JButton("Sign up");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username ");
    JLabel userPasswordLabel = new JLabel("Password ");
    JLabel messageLabel = new JLabel();

    // SIGN IN PAGE COMPONENTS
    JLabel signInTitle = new JLabel("Sign Up");
    JLabel logInPrompt = new JLabel("Already have an account?");
    JButton logInButtonFromSign = new JButton("Log In");
    JLabel newUserIDLabel = new JLabel("Username");
    JTextField newUserIDField = new JTextField();
    JLabel newUserPasswordLabel = new JLabel("Password ");
    JPasswordField newUserPasswordField = new JPasswordField();
    JLabel confirmUserLabel = new JLabel("Confirm");
    JLabel confirmUserLabel2 = new JLabel("password");
    JPasswordField confirmUserField = new JPasswordField();
    JButton signInButton = new JButton("Sign up");
    JLabel messageLabelFromSign = new JLabel();


    LoginPage() {

        userList = new ArrayList<>();
        loadUserList();

        // LOG IN PAGE
        logInTitle.setBounds(50,50,75,30);
        logInTitle.setFont(new Font("ROBOTO",Font.BOLD,25));

        userIDLabel.setBounds(50,150,75,35);
        userIDLabel.setFont(new Font("ROBOTO", Font.BOLD, 13));
        userIDLabel.setForeground(new Color(100, 93, 192));
        userPasswordLabel.setBounds(50,200,95,35);
        userPasswordLabel.setFont(new Font("ROBOTO", Font.BOLD, 13));
        userPasswordLabel.setForeground(new Color(100, 93, 192));

        messageLabel.setBounds(125,300,250,35);
        messageLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));

        userIDField.setBounds(125,154,230,25);
        userIDField.setFont(new Font("ROBOTO", Font.BOLD, 14));
        userPasswordField.setBounds(125,206,230,25);
        userPasswordField.setFont(new Font("ROBOTO", Font.BOLD, 14));

        loginButton.setBounds(50,260,305,23);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("ROBOTO", Font.BOLD, 13));
        loginButton.setForeground(new Color(241, 235, 255));
        loginButton.setBackground(new Color(78, 51, 255));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        signUpPrompt.setBounds(50,450,200,30);

        signUpButton.setBounds(255,450,100,25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);
        signUpButton.setFont(new Font("ROBOTO", Font.BOLD, 13));
        signUpButton.setForeground(new Color(241, 235, 255));
        signUpButton.setBackground(new Color(78, 51, 255));
        signUpButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));




        //SIGN IN PAGE
        signInTitle.setBounds(50,50,100,30);
        signInTitle.setFont(new Font("ROBOTO",Font.BOLD,25));

        logInPrompt.setBounds(50,450,200,30);

        signInButton.setBounds(255,450,100,25);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signInButton.setFont(new Font("ROBOTO", Font.BOLD, 13));
        signInButton.setForeground(new Color(241, 235, 255));
        signInButton.setBackground(new Color(78, 51, 255));
        signInButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        logInButtonFromSign.setBounds(255,450,100,25);
        logInButtonFromSign.setFocusable(false);
        logInButtonFromSign.addActionListener(this);
        logInButtonFromSign.setFont(new Font("ROBOTO", Font.BOLD, 13));
        logInButtonFromSign.setForeground(new Color(241, 235, 255));
        logInButtonFromSign.setBackground(new Color(78, 51, 255));
        logInButtonFromSign.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        signInButton.setBounds(50,310,305,23);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signInButton.setFont(new Font("ROBOTO", Font.BOLD, 13));
        signInButton.setForeground(new Color(241, 235, 255));
        signInButton.setBackground(new Color(78, 51, 255));
        signInButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));


        newUserIDLabel.setBounds(50,150,75,35);
        newUserIDLabel.setFont(new Font("ROBOTO", Font.BOLD, 13));
        newUserIDLabel.setForeground(new Color(100, 93, 192));

        newUserIDField.setBounds(125,154,230,25);
        newUserIDField.setFont(new Font("ROBOTO", Font.BOLD, 14));

        newUserPasswordLabel.setBounds(50,200,95,35);
        newUserPasswordLabel.setFont(new Font("ROBOTO", Font.BOLD, 13));
        newUserPasswordLabel.setForeground(new Color(100, 93, 192));

        newUserPasswordField.setBounds(125,206,230,25);
        newUserPasswordField.setFont(new Font("ROBOTO", Font.BOLD, 14));

        confirmUserLabel.setBounds(50,245,95,35);
        confirmUserLabel.setFont(new Font("ROBOTO", Font.BOLD, 13));
        confirmUserLabel.setForeground(new Color(100, 93, 192));

        confirmUserLabel2.setBounds(50,258,95,35);
        confirmUserLabel2.setFont(new Font("ROBOTO", Font.BOLD, 13));
        confirmUserLabel2.setForeground(new Color(100, 93, 192));

        confirmUserField.setBounds(125,255,230,25);
        confirmUserField.setFont(new Font("ROBOTO", Font.BOLD, 14));

        messageLabelFromSign.setBounds(125,340,250,35);
        messageLabelFromSign.setFont(new Font("ROBOTO", Font.BOLD, 16));


        logInPanel = new JPanel();
        logInPanel.setBounds(400, 100, 400, 500);
        logInPanel.setBackground(new Color(221, 219, 241));
        logInPanel.setLayout(null);
        logInPanel.add(logInTitle);
        logInPanel.add(loginButton);
        logInPanel.add(signUpPrompt);
        logInPanel.add(signUpButton);
        logInPanel.add(userIDField);
        logInPanel.add(userPasswordField);
        logInPanel.add(userIDLabel);
        logInPanel.add(userPasswordLabel);
        logInPanel.add(messageLabel);


        signInPanel = new JPanel();
        signInPanel.setBounds(400, 100, 400, 500);
        signInPanel.setBackground(new Color(221, 219, 241));
        signInPanel.setLayout(null);
        signInPanel.add(signInTitle);
        signInPanel.add(newUserIDLabel);
        signInPanel.add(newUserIDField);
        signInPanel.add(logInPrompt);
        signInPanel.add(logInButtonFromSign);
        signInPanel.add(newUserPasswordLabel);
        signInPanel.add(newUserPasswordField);
        signInPanel.add(confirmUserLabel);
        signInPanel.add(confirmUserField);
        signInPanel.add(confirmUserLabel2);
        signInPanel.add(signInButton);
        signInPanel.add(messageLabelFromSign);

        ImageIcon backgroundImage = new ImageIcon("Resources/back2.jpg");
        text = new JLabel();
        text.setBounds(0,0,1200,800);
        text.setIcon(backgroundImage);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        centerFrameOnScreen(frame);
        frame.add(signInPanel);
        frame.add(text);
        frame.setBackground(Color.white);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            userIDField.setText(null);
            userPasswordField.setText(null);
            messageLabel.setText(null);
            frame.remove(logInPanel);
            frame.add(signInPanel);
            frame.add(text);
            frame.revalidate();
            frame.repaint();
        }
        if (e.getSource() == logInButtonFromSign) {
            newUserIDField.setText(null);
            newUserPasswordField.setText(null);
            confirmUserField.setText(null);
            messageLabelFromSign.setText(null);
            frame.remove(signInPanel);
            frame.add(logInPanel);
            frame.add(text);
            frame.revalidate();
            frame.repaint();
        }

        if (e.getSource() == loginButton) {
            loadUserList();

            String username = userIDField.getText();
            String password = new String(userPasswordField.getPassword());

            if (username.isEmpty()) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            } else if (password.isEmpty()) {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Password not found");
            } else {
                boolean userFound = false;

                for (User user : userList) {
                    if (user.getUsername().equals(username)) {
                        userFound = true;

                        if (user.getPassword().equals(password)) {
                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("Login Successful");

                            String[] options = {"Continue to Shop"};
                            ImageIcon correctIcon = new ImageIcon("Resources/correct.png");
                            JOptionPane.showOptionDialog(
                                    null,
                                    "You have successfully logged in!",
                                    "Login Successful",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    correctIcon,
                                    options,
                                    options[0]
                            );
                            frame.dispose();
                            ProductsDisplay productsDisplay = new ProductsDisplay();
                            return;
                        } else {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("Wrong Password");
                            break;
                        }
                    }
                }

                if (!userFound) {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("User not found");
                }
            }
        }

        if (e.getSource() == signInButton) {

            String username = newUserIDField.getText();
            String password = new String(newUserPasswordField.getPassword());
            String confirmPass = String.valueOf(confirmUserField.getPassword());

            if (username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                messageLabelFromSign.setForeground(Color.red);
                messageLabelFromSign.setText("Fill all blanks");
            } else {
                boolean userExists = false;

                for (User user : userList) {
                    if (user.getUsername().equals(username)) {
                        userExists = true;
                        break;
                    }
                }

                if (userExists) {
                    messageLabelFromSign.setForeground(Color.red);
                    messageLabelFromSign.setText("User already exists");

                } else if (!password.equals(confirmPass)) {
                    messageLabelFromSign.setForeground(Color.red);
                    messageLabelFromSign.setText("Password doesn't match");
                } else {
                    messageLabelFromSign.setForeground(Color.green);
                    messageLabelFromSign.setText("Sign in Successful");

                    String[] options = {"Continue to Shop"};
                    ImageIcon correctIcon = new ImageIcon("src/correct.png");
                    JOptionPane.showOptionDialog(
                            null,
                            "You have successfully Signed ip!",
                            "Sign up Successful",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            correctIcon,
                            options,
                            options[0]
                    );
                    boolean firstTimePurchase = true;
                    User newUser = new User(username, password);
                    firstTimeSigned = true;
                    userList.add(newUser);
                    saveUserList();

                    frame.dispose();
                    ProductsDisplay productsDisplay = new ProductsDisplay();
                }
            }
        }
    }

    private void saveUserList() {
        if (!userList.isEmpty()) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("User list.ser"))) {
                outputStream.writeObject(userList);
            } catch (IOException ignored) {
            }
        }
    }

    private void loadUserList() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("User list.ser"))) {
            List<User> savedUserList = (List<User>) inputStream.readObject();
            userList.addAll(savedUserList);
        } catch (IOException | ClassNotFoundException ignored) {
        }

    }
    public static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        frame.setLocation(x, y);
    }
}