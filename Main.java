import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private static String username = "Admin"; 
    private static String password = "1234";
    private CardLayout cardLayout;
    private JPanel mainPanel; 
    private JPanel loginPanel;
    private JPanel menuPanel;
    private JLabel loginError;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton inventoryButton;
    private JButton transactionButton;
    private JButton logoutButton;
    
    public Main() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout()); 
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Login Panel//
        loginPanel = new JPanel();

        usernameLabel = new JLabel("Username");
        usernameInput = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordInput = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameInput.getText();
                char[] enteredPassword = passwordInput.getPassword();

                if (username.equals(enteredUsername) && password.equals(new String(enteredPassword))) {
                    loginError.setText("Successful");
                    cardLayout.show(mainPanel, "menuPanel");
                } else {
                    loginError.setText("Incorrect password");
                }
            }
        });
        loginError = new JLabel();
        JPanel credPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        
        
        loginPanel.setLayout(new GridLayout(2, 1));

        credPanel.setLayout(new GridLayout(2, 2));
        credPanel.add(usernameLabel);
        credPanel.add(usernameInput);
        credPanel.add(passwordLabel);
        credPanel.add(passwordInput);

        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(loginError);
        buttonPanel.add(loginButton);

        loginPanel.add(credPanel);
        loginPanel.add(buttonPanel);
        //login panel//

        // Menu Panel//
        menuPanel = new JPanel();

        inventoryButton = new JButton("Inventory"); 
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Inventory");
                setExtendedState(JFrame.MAXIMIZED_BOTH);// Set the frame to full screen
                setUndecorated(true);// Remove window decorations
            }  
        });

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "loginPanel");
            }
        });

        transactionButton = new JButton("Transaction");
        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Transaction");
            }
        });

        menuPanel.setLayout(new GridLayout(3, 1));
        menuPanel.add(inventoryButton);
        menuPanel.add(transactionButton);
        menuPanel.add(logoutButton);
        
        //Menu Panel//

        InventoryPanel invent = new InventoryPanel();
        TransactionPanel trans = new TransactionPanel(mainPanel);
        OrderPanel order = new OrderPanel(mainPanel);
        ReportPanel report = new ReportPanel(mainPanel);

        mainPanel.add(invent, "Inventory");
        mainPanel.add(trans, "Transaction");
        mainPanel.add(trans, "order");
        mainPanel.add(trans, "report");
        mainPanel.add(loginPanel, "loginPanel");
        mainPanel.add(menuPanel, "menuPanel");
        cardLayout.show(mainPanel, "loginPanel");
        
        add(mainPanel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Main();
    }
}
