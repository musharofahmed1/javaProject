import java.io.Serializable;

public class MatchPractice implements Serializable {
    private String playerName;
    private CourseType courseType;
    private int durationInMinutes;

    public MatchPractice(String playerName, CourseType courseType, int durationInMinutes) {
        this.playerName = playerName;
        this.courseType = courseType;
        this.durationInMinutes = durationInMinutes;
    }

    public String getPlayerName() {
        return playerName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}

enum CourseType {
    JYM, NET_PRACTICE, FIELDING, SWIMMING
}
