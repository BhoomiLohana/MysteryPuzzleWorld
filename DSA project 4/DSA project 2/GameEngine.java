import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GameEngine extends JFrame {

    private final String playerName;
    private int currentLevel = 1;
    private final int QUESTIONS_PER_LEVEL = 4;

    private Queue<Question> currentQueue;
    private final Stack<String> answerStack = new Stack<>();
    private int correctThisLevel = 0;
    private int totalCorrectAll = 0;

    private final Map<Integer, Integer> levelAttempts = new HashMap<>();
    private int currentAttemptThisLevel = 0;

    private JPanel topBar, centerPanel;
    private JLabel titleLabel, levelLabel, progressLabel;

    public GameEngine(String name) {
        this.playerName = name;
        setupUI();
        loadLevel(currentLevel);
    }

    private void setupUI() {
        setTitle("Mystery Puzzle — " + playerName);
        setSize(1000, 660);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        topBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0,
                        new Color(20, 120, 200),
                        getWidth(), getHeight(),
                        new Color(200, 70, 180)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topBar.setBounds(0, 0, 1000, 120);
        topBar.setLayout(null);

        titleLabel = new JLabel("Mystery Puzzle", SwingConstants.LEFT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        titleLabel.setBounds(28, 18, 500, 40);

        levelLabel = new JLabel("", SwingConstants.LEFT);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        levelLabel.setBounds(28, 62, 300, 30);

        progressLabel = new JLabel("", SwingConstants.RIGHT);
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        progressLabel.setBounds(700, 62, 280, 30);

        topBar.add(titleLabel);
        topBar.add(levelLabel);
        topBar.add(progressLabel);
        add(topBar);

        centerPanel = new JPanel(null);
        centerPanel.setBounds(50, 140, 900, 470);
        centerPanel.setBackground(new Color(245, 240, 255));
        centerPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 120, 220), 5, true));
        add(centerPanel);

        setVisible(true);
    }

    private void loadLevel(int level) {
        this.currentLevel = level;
        this.correctThisLevel = 0;
        currentAttemptThisLevel++;
        levelAttempts.put(level, currentAttemptThisLevel);
        
        showLevelMessage(level);   // <-- Add this line here

        this.currentQueue = QuestionBank.getRandomQuestions(level, QUESTIONS_PER_LEVEL);
        levelLabel.setText("Level " + level + " — " + getLevelName(level));
        progressLabel.setText("Correct this level: 0 / " + QUESTIONS_PER_LEVEL);
        showNext();
    }

    private String getLevelName(int level) {
        switch (level) {
            case 1: return "Emoji Guess";
            case 2: return "Word Fix";
            case 3: return "Memory Sequence";
            default: return "Unknown";
        }
    }

    private void showNext() {
        centerPanel.removeAll();
        centerPanel.revalidate();
        centerPanel.repaint();

        if (currentQueue == null || currentQueue.isEmpty()) {
            SwingUtilities.invokeLater(this::handleLevelCompletion);
            return;
        }

        Question q = currentQueue.poll();
        progressLabel.setText("Correct this level: " + correctThisLevel + " / " + QUESTIONS_PER_LEVEL);

        if (currentLevel == 3) {
            showMemoryQuestion(q);
        } else {
            showStandardQuestion(q);
        }
    }

    private void showStandardQuestion(Question q) {
        JLabel prompt = new JLabel(q.getPrompt(), SwingConstants.CENTER);
        prompt.setFont(new Font("Serif", Font.BOLD, 36));
        prompt.setBounds(40, 40, 860, 80);
        prompt.setForeground(new Color(80, 40, 80));
        centerPanel.add(prompt);

        List<String> opts = new ArrayList<>(Arrays.asList(q.getOptions()));
        Collections.shuffle(opts);

        // -------------------------------
        // FIXED: Aligned 4 answer buttons
        // -------------------------------
        JPanel answerGrid = new JPanel(new GridLayout(2, 2, 30, 30));
        answerGrid.setBounds(180, 160, 540, 250); // centered & balanced
        answerGrid.setOpaque(false);

        for (String text : opts) {
            JButton b = makeButton(text);
            answerGrid.add(b);

            b.addActionListener(e -> {
                answerStack.push(text);
                boolean correct = normalize(text).equals(normalize(q.getCorrect()));
                handleAnswer(b, correct);
            });
        }

        centerPanel.add(answerGrid);
    }

    private void showMemoryQuestion(Question q) {
        JLabel seq = new JLabel(q.getPrompt(), SwingConstants.CENTER);
        seq.setFont(new Font("Serif", Font.BOLD, 72));
        seq.setBounds(40, 40, 860, 160);
        centerPanel.add(seq);
        centerPanel.revalidate();
        centerPanel.repaint();

        javax.swing.Timer t = new javax.swing.Timer(1000, e -> {
            centerPanel.removeAll();

            JLabel instruct = new JLabel("Which sequence did you just see?", SwingConstants.CENTER);
            instruct.setFont(new Font("SansSerif", Font.BOLD, 26));
            instruct.setBounds(40, 20, 860, 60);
            centerPanel.add(instruct);

            List<String> opts = new ArrayList<>(Arrays.asList(q.getOptions()));
            Collections.shuffle(opts);

            JPanel answerGrid = new JPanel(new GridLayout(2, 2, 30, 30));
            answerGrid.setBounds(180, 140, 540, 250);
            answerGrid.setOpaque(false);

            for (String text : opts) {
                JButton b = makeButton(text);
                answerGrid.add(b);

                b.addActionListener(evt -> {
                    answerStack.push(text);
                    boolean correct = normalize(text).equals(normalize(q.getCorrect()));
                    handleAnswer(b, correct);
                });
            }

            centerPanel.add(answerGrid);

            centerPanel.revalidate();
            centerPanel.repaint();
        });
        t.setRepeats(false);
        t.start();
    }

    private JButton makeButton(String label) {
        JButton b = new JButton(label);
        b.setFont(new Font("SansSerif", Font.BOLD, 22));
        b.setFocusPainted(false);
        b.setBackground(Color.WHITE);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 90, 200), 2, true),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                b.setBackground(new Color(255, 180, 250));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                b.setBackground(Color.WHITE);
            }
        });
        return b;
    }

    private void handleAnswer(JButton btn, boolean correct) {
        for (Component c : centerPanel.getComponents()) {
            if (c instanceof JButton) c.setEnabled(false);
        }

        if (correct) {
            btn.setBackground(new Color(150, 255, 180));
            correctThisLevel++;
            totalCorrectAll++;
            JOptionPane.showMessageDialog(this, "✅ Correct!");
        } else {
            btn.setBackground(new Color(255, 160, 160));
            JOptionPane.showMessageDialog(this, "❌ Wrong!");
        }

        progressLabel.setText("Correct this level: " + correctThisLevel + " / " + QUESTIONS_PER_LEVEL);

        javax.swing.Timer t = new javax.swing.Timer(700, e -> showNext());
        t.setRepeats(false);
        t.start();
    }

    private void handleLevelCompletion() {
        if (correctThisLevel >= QUESTIONS_PER_LEVEL) {
            if (currentLevel < 3) {
                int opt = JOptionPane.showOptionDialog(this,
                        "🎉 You cleared Level " + currentLevel + "!\nProceed to Level " + (currentLevel + 1) + "?",
                        "Level Cleared",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (opt == JOptionPane.YES_OPTION) {
                    currentAttemptThisLevel = 0;
                    loadLevel(currentLevel + 1);
                } else {
                    endGame(false);
                }
            } else {
                endGame(true);
            }
        } else {
            int opt = JOptionPane.showOptionDialog(this,
                    "You answered " + correctThisLevel + " / " + QUESTIONS_PER_LEVEL + " correctly.\nRetry Level " + currentLevel + "?",
                    "Level Failed",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            if (opt == JOptionPane.YES_OPTION) {
                loadLevel(currentLevel);
            } else {
                endGame(false);
            }
        }
    }

    private void endGame(boolean victory) {
        ScoreManager sm = new ScoreManager();
        sm.saveScore(playerName, getTotalAttempts(), levelAttempts);

        List<PlayerRecord> topPlayers = sm.getTopFiveByAttempts();
        new ResultScreen(playerName, victory, totalCorrectAll, answerStack, levelAttempts, topPlayers);

        dispose();
    }

    private int getTotalAttempts() {
        return levelAttempts.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    private void showLevelMessage(int level) {
    String msg = switch (level) {
        case 1 -> "Level 1: Look at the emoji and guess the hidden word or meaning.";
        case 2 -> "Level 2: A word will appear with missing or wrong letters. Choose the correct spelling.";
        case 3 -> "Level 3: A sequence will flash briefly. Remember it and pick the correct answer.";
        default -> "";
    };

    JOptionPane.showMessageDialog(this, msg, 
            "Level " + level + " Instructions",
            JOptionPane.INFORMATION_MESSAGE);
}

    private String normalize(String s) {
        if (s == null) return "";
        return s.toLowerCase().replaceAll("\\s+", "")
                .replaceAll("[^\\p{L}\\p{N}🔴🟢🔵⭐🌙☀️🍎🍌🍇]", "");
    }
}