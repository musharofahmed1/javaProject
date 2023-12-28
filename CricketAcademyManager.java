import java.io.*;
import java.util.ArrayList;

public class CricketAcademyManager implements Serializable {

    //declare a array for practice list
    private ArrayList<MatchPractice> practiceList;

    //declare a constructor
    public CricketAcademyManager() {
        this.practiceList = new ArrayList<>();
    }

    public void addPractice(int id,String playerName, Category category, int durationInMinutes) {
        //create a object of MatchPractice
        MatchPractice practice = new MatchPractice(id,playerName, category, durationInMinutes);
        //why this line
        practiceList.add(practice);
    }

    //method for return practice list
    public ArrayList<MatchPractice> getPracticeList() {
        return practiceList;
    }


    // e part buja jar na
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("academy_data.ser"))) {
            oos.writeObject(practiceList);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // e part buja jar na
    public void retrieveData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("academy_data.ser"))) {
            practiceList = (ArrayList<MatchPractice>) ois.readObject();
            System.out.println("Data retrieved successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
