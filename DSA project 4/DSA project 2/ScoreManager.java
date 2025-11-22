import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScoreManager {
    private static final String FILE = "results.csv";

    public void saveScore(String name, int totalAttempts, Map<Integer, Integer> levelAttempts) {
    try (FileWriter fw = new FileWriter(FILE, true)) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String safeName = sanitize(name);

        StringBuilder lvls = new StringBuilder();
        
        for (int lvl = 1; lvl <= 3; lvl++) {
            int attempts = levelAttempts.getOrDefault(lvl, 0); // default 0 if not played
            lvls.append(lvl).append("=").append(attempts).append(";");
        }

        fw.write(String.format("%s,%d,%s,%s\n", safeName, totalAttempts, lvls.toString(), ts));
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public List<PlayerRecord> loadScores() {
        List<PlayerRecord> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 3) {
                    String name = parts[0];
                    int totalAttempts = Integer.parseInt(parts[1]);
                    Map<Integer,Integer> lvlMap = new HashMap<>();
                    String[] lvls = parts[2].split(";");
                    for(String lv : lvls){
                        if(lv.contains("=")){
                            String[] kv = lv.split("=");
                            lvlMap.put(Integer.parseInt(kv[0]), Integer.parseInt(kv[1]));
                        }
                    }
                    records.add(new PlayerRecord(name, totalAttempts, lvlMap));
                }
            }
        } catch (IOException e) {}
        return records;
    }

    public List<PlayerRecord> getTopFiveByAttempts() {
    List<PlayerRecord> records = loadScores();

    // Only include players who completed all levels (attempts > 0 for all levels)
    records.removeIf(r -> {
        for (int lvl = 1; lvl <= 3; lvl++) {
            if (!r.levelAttempts.containsKey(lvl) || r.levelAttempts.get(lvl) == 0) {
                return true; // remove this player
            }
        }
        return false;
    });

    // Sort by total attempts (ascending)
    records.sort(Comparator.comparingInt(r -> r.totalAttempts));

    // Return top 5
    return records.subList(0, Math.min(5, records.size()));
}



    private String sanitize(String s) {
        return s == null ? "" : s.replaceAll("[\\r\\n,]+", " ").trim();
    }
}
