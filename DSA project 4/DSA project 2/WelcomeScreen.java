import java.awt.*;
import javax.swing.*;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
        setTitle("Mystery Puzzle — Levels");
        setSize(900, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        // Background gradient panel
        JPanel bg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 102, 178) , getWidth(), getHeight(), new Color(200, 130, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setBounds(0, 0, 900, 560);
        bg.setLayout(null);

        JLabel title = new JLabel(" MYSTERY PUZZLE ", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 40, 700, 80);

        JLabel desc = new JLabel("Level Challenge — 3 Stages", SwingConstants.CENTER);      
        desc.setFont(new Font("SansSerif", Font.PLAIN, 20));
        desc.setForeground(Color.WHITE);
        desc.setBounds(100, 120, 700, 40);

        JLabel prompt = new JLabel("Enter your name:", SwingConstants.LEFT);
        prompt.setFont(new Font("SansSerif", Font.BOLD, 18));
        prompt.setForeground(Color.WHITE);
        prompt.setBounds(250, 200, 400, 30);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        nameField.setBounds(250, 240, 400, 44);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,200), 2, true));
        nameField.setOpaque(false);
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(new Color(255,255,255,20));

        JButton leaderboard = new JButton("Leaderboard");
        leaderboard.setFont(new Font("SansSerif", Font.BOLD, 18));
        leaderboard.setBounds(330, 380, 240, 50);
        leaderboard.setFocusPainted(false);
        leaderboard.setForeground(Color.WHITE);
        leaderboard.setBackground(new Color(102, 204, 255));
        leaderboard.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));
        leaderboard.addActionListener(e -> new TopPlayersWindow(new ScoreManager().getTopFiveByAttempts()));

        JButton start = new JButton("Start Adventure");
        start.setFont(new Font("SansSerif", Font.BOLD, 20));
        start.setBounds(330, 310, 240, 50);
        start.setFocusPainted(false);
        start.setForeground(Color.WHITE);
        start.setBackground(new Color(255, 102, 178));
        start.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        start.setOpaque(true);

        start.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Name required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            dispose();
            new GameEngine(name);
        });

        bg.add(title);
        bg.add(desc);
        bg.add(prompt);
        bg.add(nameField);
        bg.add(start);
        bg.add(leaderboard);

        add(bg);
        setVisible(true);
    }
}
