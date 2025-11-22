import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class TopPlayersWindow extends JFrame {

    public TopPlayersWindow(List<PlayerRecord> topPlayers) {

        setTitle("🏆 Top 5 Players — Performance by Levels");
        setSize(650, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(255, 75, 95),
                        0, getHeight(), new Color(255, 220, 170)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(new BorderLayout(10,10));
        add(bg);

        JLabel title = new JLabel("Top 5 Players", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 28));
        title.setForeground(new Color(255,255,255));
        title.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
        bg.add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.BOLD, 16));
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setForeground(Color.BLACK);

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for(PlayerRecord p : topPlayers){
            sb.append(rank++).append(". ").append(p.name).append("\n");
            sb.append("   Total Attempts: ").append(p.totalAttempts).append("\n");
            sb.append("   Level-wise Attempts:\n");
            for(Map.Entry<Integer,Integer> e : p.levelAttempts.entrySet()){
                sb.append("     → Level ").append(e.getKey())
                  .append(": ").append(e.getValue()).append(" attempt(s)\n");
            }
            sb.append("\n");
        }
        area.setText(sb.toString());

        JScrollPane sp = new JScrollPane(area);
        sp.setBorder(BorderFactory.createLineBorder(new Color(255,215,0),4));
        bg.add(sp, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton close = new JButton("Close");
        close.setFont(new Font("SansSerif", Font.BOLD, 18));
        close.setPreferredSize(new Dimension(140,45));
        close.setBackground(new Color(253, 245, 230));
        close.setForeground(Color.BLACK);
        close.setFocusPainted(false);
        close.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));
        close.addActionListener(e-> dispose());
        buttonPanel.add(close);

        bg.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
