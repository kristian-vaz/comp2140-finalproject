import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPanel extends JPanel {
    private JPanel parentPanel;  // Reference to the parent panel (TransactionPanel)

    public ReportPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add any components or set up the content of the ReportPanel as needed
        // For example:
        // JLabel titleLabel = new JLabel("Report Panel");
        // add(titleLabel, BorderLayout.CENTER);

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
    } 
}
