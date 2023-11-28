import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel {
    private ArrayList<Item> inventoryList;
    private DefaultTableModel tableModel;
    private JTable itemTable;

    private static final String ITEMS_FILE = "items.dat";

    public InventoryPanel() {
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
        JButton addItemButton = new JButton("Add Item");
        JButton editItemButton = new JButton("Edit Item");
        JButton removeItemButton = new JButton("Remove Item");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemDialog();
            }
        });

        editItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editItemDialog();
            }
        });

        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });

        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addItemButton);
        buttonPanel.add(editItemButton);
        buttonPanel.add(removeItemButton);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addItemDialog() {
        JTextField categoryField = new JTextField();
        JTextField UPCField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField manufacturerField = new JTextField();
        JTextField expirationDateField = new JTextField();
        JTextField priceField = new JTextField();

        Object[] message = {
                "Category:", categoryField,
                "Universal Product Code:", UPCField,
                "Name:", nameField,
                "Manufacturer:", manufacturerField,
                "Expiration Date:", expirationDateField,
                "Price:", priceField
        };
 
        int option = JOptionPane.showConfirmDialog(this, message, "Add New Item", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String category = categoryField.getText();
            String UPC = UPCField.getText();
            String name = nameField.getText();
            String manufacturer = manufacturerField.getText();
            String expirationDate = expirationDateField.getText();

            try {
                double price = Double.parseDouble(priceField.getText());

                Item newItem = new Item(category, UPC, name, price, manufacturer, expirationDate);
                inventoryList.add(newItem);
                saveItems(inventoryList);
                updateTable();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editItemDialog() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            Item selectedItem = inventoryList.get(selectedRow);

            // Provide fields with existing item details for editing
            JTextField categoryField = new JTextField(selectedItem.getCategory());
            JTextField UPCField = new JTextField(selectedItem.getUPC());
            JTextField nameField = new JTextField(selectedItem.getName());
            JTextField manufacturerField = new JTextField(selectedItem.getManufacturer());
            JTextField expirationDateField = new JTextField(selectedItem.getExpirationDate());
            JTextField priceField = new JTextField(String.valueOf(selectedItem.getPrice()));

            Object[] message = {
                    "Category:", categoryField,
                    "Universal Product Code:", UPCField,
                    "Name:", nameField,
                    "Manufacturer:", manufacturerField,
                    "Expiration Date:", expirationDateField,
                    "Price:", priceField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Edit Item", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Update the item in the list
                selectedItem.setCategory(categoryField.getText());
                selectedItem.setUPC(UPCField.getText());
                selectedItem.setName(nameField.getText());
                selectedItem.setManufacturer(manufacturerField.getText());
                selectedItem.setExpirationDate(expirationDateField.getText());
                try {
                    double price = Double.parseDouble(priceField.getText());
                    selectedItem.setPrice(price);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Save the updated list
                saveItems(inventoryList);
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to edit.", "Edit Item", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removeItem() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            inventoryList.remove(selectedRow);
            saveItems(inventoryList);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.", "Remove Item", JOptionPane.INFORMATION_MESSAGE);
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

    private void saveItems(ArrayList<Item> items) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ITEMS_FILE))) {
            oos.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void updateTable() {
        tableModel.setRowCount(0);
        for (Item item : inventoryList) {
            Object[] rowData = {item.getCategory(), item.getName(), item.getManufacturer(), item.getExpirationDate(), item.getPrice()};
            tableModel.addRow(rowData);
        }
    }
}

