import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportPanel extends JPanel {
    private JPanel parentPanel; // Reference to the parent panel (TransactionPanel)
    private JLabel errorMessageLabel;
    private JLabel profitLabel;
    private JLabel lossLabel;

    public ReportPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the TransactionPanel
                CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
                cardLayout.show(parentPanel, "menuPanel");
            }
        });

        add(backButton, BorderLayout.SOUTH);

        // Add components for customizing time period
        JPanel customizePanel = new JPanel();
        customizePanel.setLayout(new GridLayout(5, 2));

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        JTextField startDateField = new JTextField();
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        JTextField endDateField = new JTextField();

        // Add ComboBox for format selection
        String[] exportFormats = {"PDF", "Excel"};
        JComboBox<String> formatComboBox = new JComboBox<>(exportFormats);

        JButton generateButton = new JButton("Generate Report");

        errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(Color.RED); // Set the text color to red
        profitLabel = new JLabel();
        lossLabel = new JLabel();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected time period and generate the report
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();

                // Check if the date format is valid
                if (isValidDateFormat(startDate) && isValidDateFormat(endDate)) {
                    // Get the selected export format
                    String selectedFormat = (String) formatComboBox.getSelectedItem();

                    // TODO: Add logic to generate and export the report for the specified time period
                    // For now, let's just print the selected time period and export format
                    System.out.println("Generating report for period: " + startDate + " to " + endDate);
                    System.out.println("Exporting to " + selectedFormat);

                    // Display the profit and loss
                    profitLabel.setText("Profits: $0.00");
                    lossLabel.setText("Losses: $0.00");

                    errorMessageLabel.setText(""); // Clear any previous error messages
                } else {
                    errorMessageLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
                }
            }
        });

        customizePanel.add(startDateLabel);
        customizePanel.add(startDateField);
        customizePanel.add(endDateLabel);
        customizePanel.add(endDateField);
        customizePanel.add(new JLabel()); // Empty label for spacing
        customizePanel.add(formatComboBox); // Format selection ComboBox
        customizePanel.add(generateButton);
        customizePanel.add(errorMessageLabel); // Error message label
        customizePanel.add(profitLabel); // Profit label
        customizePanel.add(lossLabel); // Loss label

        add(customizePanel, BorderLayout.CENTER);
    }

    // Check if the date format is valid
    private boolean isValidDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Disable lenient parsing

        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
