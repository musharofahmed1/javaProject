import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CricketAcademyGUI extends JFrame {
    private CricketAcademyManager manager;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public CricketAcademyGUI() {
        manager = new CricketAcademyManager();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Cricket Academy Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createSaveButton();
        createRetrieveButton();
        createTable();

        pack();
        setLocationRelativeTo(null);
    }

    private void createSaveButton() {
        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Season[] values = Season.values();
                    Season season = (Season) JOptionPane.showInputDialog(null,
                            "Season:", "Choose",
                            JOptionPane.QUESTION_MESSAGE, null,
                            values, values[0]);
    
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Player ID:"));
                    String playerName = JOptionPane.showInputDialog("Enter Player Name:");
    
                    // Validate player name (ensure it is not empty and contains at least one non-whitespace character)
                    if (playerName.trim().isEmpty()) {
                        throw new IllegalArgumentException("Player name cannot be empty.");
                    }
    
                    int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Duration (minutes):"));
                    Category[] values1 = Category.values();
                    Category category = (Category) JOptionPane.showInputDialog(null,
                            "Category:", "Activity Type",
                            JOptionPane.QUESTION_MESSAGE, null,
                            values1, values1[0]);
    
                    // Validate inputs
                    if (id < 0 || duration < 0) {
                        throw new IllegalArgumentException("ID and duration must be non-negative.");
                    }
    
                    manager.addPractice(season, id, playerName, category, duration);
                    manager.saveData();
                    updateTableData();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number for ID and Duration.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        add(saveButton, BorderLayout.NORTH);
    }
    

    private void createRetrieveButton() {
        JButton retrieveButton = new JButton("Retrieve Data");
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.retrieveData();
                updateTableData();
            }
        });

        add(retrieveButton, BorderLayout.SOUTH);
    }

    private void createTable() {
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        tableModel.addColumn("Season");
        tableModel.addColumn("Player ID");
        tableModel.addColumn("Player Name");
        tableModel.addColumn("Category Type");
        tableModel.addColumn("Duration (min)");

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateTableData() {
        tableModel.setRowCount(0);

        ArrayList<MatchPractice> data = manager.getPracticeList();

        for (MatchPractice practice : data) {
            Object[] rowData = {practice.getSeason(), practice.getId(), practice.getPlayerName(), practice.getCategory(), practice.getDurationInMinutes()};
            tableModel.addRow(rowData);
        }
    }
}
