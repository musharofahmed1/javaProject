import java.io.Serializable;

public class MatchPractice implements Serializable {
    private int id;
    private String playerName;
    private Category category;
    private int durationInMinutes;
    

    public MatchPractice(int id,String playerName, Category category, int durationInMinutes) {
        this.id = id;
        this.playerName = playerName;
        this.category = category;
        this.durationInMinutes = durationInMinutes;
    }
    public int getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Category getCategory() {
        return category;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}

//why we choose enum class
enum Category {
    JYM, NET_PRACTICE, FIELDING,  SWIMMING
}
