public class Question {
    private final String prompt;      // for emoji or memory display or word pattern
    private final String correct;     // correct answer text
    private final String[] options;   // 4 options

    public Question(String prompt, String correct, String[] options) {
        this.prompt = prompt;
        this.correct = correct;
        this.options = options;
    }

    public String getPrompt() { return prompt; }
    public String getCorrect() { return correct; }
    public String[] getOptions() { return options; }
}
