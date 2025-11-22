# Mystery Puzzle World

**A Java Swing-based level-driven puzzle game that enhances memory, logic, and cognitive skills using Queues, Stacks, HashMaps, and ArrayLists.**

---

## Table of Contents
1. [Overview](#overview)  
2. [Features](#features)  
3. [Game Levels](#game-levels)  
4. [Data Structures Used](#data-structures-used)  
5. [GUI Design](#gui-design)  
6. [How to Run](#how-to-run)  
7. [Folder Structure](#folder-structure)  
8. [Future Enhancements](#future-enhancements)  
9. [References](#references)  

---

## Overview
Mystery Puzzle World is an interactive puzzle game developed using Java Swing.  
It is designed to improve players’ logical reasoning, memory, and cognitive skills through three progressive levels: **Emoji Guess**, **Word Fix**, and **Memory Sequence**.  
The system uses core data structures like **Queue**, **Stack**, **HashMap**, and **ArrayList** for gameplay management and features a modern, visually appealing GUI with dynamic feedback and a persistent leaderboard stored in CSV format.

---

## Features
- Level-based progression with increasing difficulty  
- Real-time scoring and attempt tracking  
- Randomized puzzle selection  
- Persistent leaderboard using CSV  
- Interactive and responsive GUI  
- Replayable for continued practice  

---

## Game Levels
1. **Emoji Guess** – Interpret visual emojis to form meaningful words  
2. **Word Fix** – Correct, complete, or unscramble words  
3. **Memory Sequence** – Memorize symbol patterns and recall them in sequence  

---

## Data Structures Used
| Data Structure | Purpose |
|----------------|--------|
| Queue          | Level-based FIFO question flow |
| Stack          | Reverse-order review of answers |
| HashMap        | Player → level-wise attempts & scores |
| ArrayList      | Storing question banks |
| Sorting        | Ranking top players in leaderboard |
| CSV            | Persistent storage of leaderboard |

---

## GUI Design
- Gradient backgrounds and modern fonts  
- Rounded panels and hover animations  
- Progress bars for level completion  
- CardLayout for smooth screen transitions  
- Emoji support for visual cues  

---

## How to Run
1. Ensure Java JDK is installed on your system.  
2. Open project in an IDE (like VS Code or Eclipse).  
3. Compile and run `GameEngine.java`.  
4. Enter your name and start playing!  

---

MysteryPuzzleWorld/
├── DSA project 4/
│ └── DSA project 2/
│ ├── GameEngine.java
│ ├── Main.java
│ ├── PlayerRecord.java
│ ├── Question.java
│ ├── QuestionBank.java
│ ├── ResultScreen.java
│ ├── ScoreManager.java
│ ├── TopPlayersWindow.java
│ └── WelcomeScreen.java
├── results.csv
└── README.md


## Future Enhancements
- Add new puzzle categories (riddles, images, movies)  
- Timer-based scoring for competitive gameplay  
- Multiplayer mode with networking  
- Database or binary tree storage for leaderboard  
- Sound effects and animations  
- Difficulty tiers (Easy, Medium, Hard)  

---

## References
- Baah, R., Govender, S., & Subramaniam, P. (2024). Gamified learning systems and cognitive improvement.  
- Eckel, B. (2006). *Thinking in Java* (4th ed.). Prentice Hall.  
- Goodrich, M. T., Tamassia, R., & Goldwasser, M. H. (2014). *Data Structures and Algorithms in Java* (6th ed.). Wiley.  
- Liang, Y. D. (2021). *Introduction to Java Programming and Data Structures* (12th ed.). Pearson.  
- Oracle. Java SE Documentation.  
- Rosser, A., & Soler, J. (2024). Memory aids using visual symbolic prompts.  
- BMC Medical Education. (2025). Interactive feedback and long-term retention in digital learning.
