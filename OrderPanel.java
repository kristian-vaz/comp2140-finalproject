import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class OrderPanel extends JPanel {
    private JPanel parentPanel;

    private ArrayList<Item> inventoryList;
    private DefaultTableModel tableModel;
    private JTable itemTable;
    private ArrayList<Item> orderList;

    private static final String ITEMS_FILE = "items.dat";

    public OrderPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        inventoryList = loadItems();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Category");
        tableModel.addColumn("Name");
        tableModel.addColumn("Manufacturer");
        tableModel.addColumn("Expiration Date");
        tableModel.addColumn("Price");

        itemTable = new JTable(tableModel);

        // Load data into the table on startup
        updateTable();

        JScrollPane scrollPane = new JScrollPane(itemTable);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the TransactionPanel
                CardLayout cardLayout = (CardLayout) parentPanel.getLayout();
                cardLayout.show(parentPanel, "menuPanel");
            }
        });

        JButton addItemButton = new JButton("Add Item to Order");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToOrder();
            }
        });

        JButton removeItemButton = new JButton("Remove Item from Order");
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemFromOrder();
            }
        });

        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(removeItemButton);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        orderList = new ArrayList<>();
    }

    private void addItemToOrder() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            Item selectedItem = inventoryList.get(selectedRow);

            orderList.add(selectedItem);
            JOptionPane.showMessageDialog(this, "Item added to order.", "Add Item to Order", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to add to order.", "Add Item to Order", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removeItemFromOrder() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            Item selectedItem = orderList.get(selectedRow);

            orderList.remove(selectedItem);
            JOptionPane.showMessageDialog(this, "Item removed from order.", "Remove Item from Order", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove from order.", "Remove Item from Order", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private ArrayList<Item> loadItems() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ITEMS_FILE))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Item>) obj;
            } else {
                System.out.println("Invalid file format");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Item item : inventoryList) {
            Object[] rowData = {item.getCategory(), item.getName(), item.getManufacturer(), item.getExpirationDate(), item.getPrice()};
            tableModel.addRow(rowData);
        }
    }
}
