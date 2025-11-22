import java.util.Map;

public class PlayerRecord {
    String name;
    int totalAttempts;
    Map<Integer, Integer> levelAttempts;

    public PlayerRecord(String name, int totalAttempts, Map<Integer, Integer> levelAttempts) {
        this.name = name;
        this.totalAttempts = totalAttempts;
        this.levelAttempts = levelAttempts;
    }
}
