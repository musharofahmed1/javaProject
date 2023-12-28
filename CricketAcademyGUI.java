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
                // Assuming you have text fields for player name, duration,
                // and a combo box for course type
                int id = Integer.parseInt(JOptionPane.showInputDialog("Player ID:"));
                String playerName = JOptionPane.showInputDialog("Enter Player Name:");
                int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Duration (minutes):"));
                Category[] values = Category.values();
                Category category = (Category) JOptionPane.showInputDialog(null,
                        "Select Course Type:", "Activity Type",
                        JOptionPane.QUESTION_MESSAGE, null,
                        values, values[0]);

                manager.addPractice(id,playerName, category, duration);
                manager.saveData();
                updateTableData();
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
         tableModel.addColumn("player ID");
        tableModel.addColumn("Player Name");
        tableModel.addColumn("Course Type");
        tableModel.addColumn("Duration (min)");

        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateTableData() {
        tableModel.setRowCount(0);

        ArrayList<MatchPractice> data = manager.getPracticeList();

        for (MatchPractice practice : data) {
            Object[] rowData = {practice.getId(),practice.getPlayerName(), practice.getCategory(), practice.getDurationInMinutes()};
            tableModel.addRow(rowData);
        }
    }
}
