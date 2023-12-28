import java.io.*;
import java.util.ArrayList;

public class CricketAcademyManager implements Serializable {
    private ArrayList<MatchPractice> practiceList;

    public CricketAcademyManager() {
        this.practiceList = new ArrayList<>();
    }

    public void addPractice(Season season, int id, String playerName, Category category, int durationInMinutes) {
        MatchPractice practice = new MatchPractice(season, id, playerName, category, durationInMinutes);
        practiceList.add(practice);
    }

    public ArrayList<MatchPractice> getPracticeList() {
        return practiceList;
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("academy_data.ser"))) {
            oos.writeObject(practiceList);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retrieveData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("academy_data.ser"))) {
            practiceList = (ArrayList<MatchPractice>) ois.readObject();
            System.out.println("Data retrieved successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
