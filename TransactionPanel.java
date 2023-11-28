import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionPanel extends JPanel {

    private JPanel cardPanel;

    public TransactionPanel(JPanel cardPanel) {
        this.cardPanel = cardPanel;

        JButton orderButton = new JButton("Go to Order Panel");
        JButton reportButton = new JButton("Go to Report Panel");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "order");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "report");
            }
        });

        setLayout(new GridLayout(2, 1));
        add(orderButton);
        add(reportButton);
    }
}
