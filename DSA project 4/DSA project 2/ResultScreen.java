import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.swing.*;

public class ResultScreen extends JFrame {

    public ResultScreen(String player, boolean victory, int totalCorrect,
                        Stack<String> answers, Map<Integer, Integer> levelAttempts,
                        List<PlayerRecord> topPlayers) {

        setTitle("🏆 Quest Complete — Performance Summary");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(200, 130, 255),
                        0, getHeight(), new Color(255, 140, 180)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(new GridBagLayout());
        add(bg);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        final int QUESTIONS_PER_LEVEL = 4;
        int totalAttempts = levelAttempts.values().stream().mapToInt(i -> i).sum();
        int totalQuestionsAttempted = levelAttempts.values().stream().mapToInt(i -> i * QUESTIONS_PER_LEVEL).sum();
        int accuracy = totalQuestionsAttempted == 0 ? 0 : (totalCorrect * 100 / totalQuestionsAttempted);

        // TITLE
        JLabel title = new JLabel(
                victory ? "🏆 YOU CONQUERED THE QUEST!" : "Game Over — Try Again!",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Georgia", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        bg.add(title, c);

        // PLAYER SUMMARY
        JPanel summaryPanel = new JPanel(new GridLayout(4,1));
        summaryPanel.setOpaque(false);

        JLabel s1 = new JLabel("👤 Player: " + player);
        JLabel s2 = new JLabel("✔ Correct Answers: " + totalCorrect);
        JLabel s3 = new JLabel("🎯 Accuracy: " + accuracy + "%");
        JLabel s4 = new JLabel("📌 Total Attempts: " + totalAttempts);

        for (JLabel lbl : new JLabel[]{s1,s2,s3,s4}) {
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        }

        summaryPanel.add(s1);
        summaryPanel.add(s2);
        summaryPanel.add(s3);
        summaryPanel.add(s4);

        c.gridy = 1;
        bg.add(summaryPanel, c);

        // MOTIVATIONAL MESSAGE BASED ON LEVELS PLAYED
        int maxLevelPlayed = levelAttempts.keySet().stream().max(Integer::compare).orElse(0);
        String suggestion;
        switch(maxLevelPlayed) {
            case 1:
                suggestion = "💡 Great start! Try Level 2 to unlock more challenges!";
                break;
            case 2:
                suggestion = "👍 Nice progress! Level 3 awaits — can you master it?";
                break;
            case 3:
                suggestion = "🔥 Amazing! You completed all levels! Try again to beat your best score!";
                break;
            default:
                suggestion = "Let's begin your adventure! Start Level 1!";
        }

        JLabel msg = new JLabel(
                victory ? "🔥 Excellent! You’ve mastered this challenge!" : suggestion,
                SwingConstants.CENTER
        );
        msg.setFont(new Font("SansSerif", Font.BOLD, 18));
        msg.setForeground(Color.YELLOW);
        c.gridy = 2;
        bg.add(msg, c);

        // LEVEL ATTEMPTS (show unplayed levels as 0)
        StringBuilder lvlText = new StringBuilder("<html><center><h2>Level Attempts:</h2>");
        for(int i=1; i<=3; i++){
            int attempts = levelAttempts.getOrDefault(i, 0);
            lvlText.append("Level ").append(i).append(": ").append(attempts).append(" attempt(s)<br>");
        }
        lvlText.append("</center></html>");

        JLabel lvlLabel = new JLabel(lvlText.toString(), SwingConstants.CENTER);
        lvlLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lvlLabel.setForeground(Color.WHITE);
        c.gridy = 3;
        bg.add(lvlLabel, c);

        // BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton playAgain = new JButton("Play Again");
        JButton exit = new JButton("Exit");
        JButton showTop = new JButton("Show Top 5 Players");
        JButton review = new JButton("Review Answers");

        review.setFont(new Font("SansSerif", Font.BOLD, 18));
        review.setPreferredSize(new Dimension(220,45));
        review.addActionListener(e -> {
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 16));
            List<String> reversed = new java.util.ArrayList<>(answers);
            java.util.Collections.reverse(reversed);
            for(int i=0;i<reversed.size();i++){
                area.append("Q"+(i+1)+": " + reversed.get(i) + "\n");
            }
            JScrollPane sp = new JScrollPane(area);
            JFrame f = new JFrame("Answer Review");
            f.setSize(600,400);
            f.add(sp);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
        buttonPanel.add(review);

        playAgain.setFont(new Font("SansSerif", Font.BOLD, 18));
        exit.setFont(new Font("SansSerif", Font.BOLD, 18));
        showTop.setFont(new Font("SansSerif", Font.BOLD, 18));

        playAgain.setPreferredSize(new Dimension(150,45));
        exit.setPreferredSize(new Dimension(150,45));
        showTop.setPreferredSize(new Dimension(220,45));

        buttonPanel.add(playAgain);
        buttonPanel.add(exit);
        buttonPanel.add(showTop);

        c.gridy = 4;
        c.gridx = 0;
        c.gridwidth = 2;
        bg.add(buttonPanel, c);

        // ACTIONS
        playAgain.addActionListener(e -> {
            dispose();
            new WelcomeScreen();
        });

        exit.addActionListener(e -> System.exit(0));
        showTop.addActionListener(e -> new TopPlayersWindow(topPlayers));

        setVisible(true);
    }
}
