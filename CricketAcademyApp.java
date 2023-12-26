import javax.swing.*;

public class CricketAcademyApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CricketAcademyGUI gui = new CricketAcademyGUI();
            gui.setVisible(true);
        });
    }
}
