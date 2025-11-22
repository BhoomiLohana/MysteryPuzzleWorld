import java.util.*;

public class QuestionBank {

    private static final HashMap<Integer, ArrayList<Question>> bank = new HashMap<>();

    static {
        ArrayList<Question> lvl1 = new ArrayList<>();
        // Level 1: Symbol Riddles
        lvl1.add(new Question("☀️ + ☕", "Morning Energy", new String[]{"Morning Energy", "Sun Tea", "Coffee Sun", "Day Coffee"}));
        lvl1.add(new Question("🚴 + 🛣️", "Cycling", new String[]{"Cycling", "Running", "Driving", "Skating"}));
        lvl1.add(new Question("🎧 + 🎵", "Music", new String[]{"Music", "Silence", "Noise", "Concert"}));
        lvl1.add(new Question("📚 + ✍️", "Study", new String[]{"Study", "Play", "Rest", "Travel"}));
        lvl1.add(new Question("❤️ + 🍫", "Love Chocolate", new String[]{"Love Chocolate", "Heart Candy", "Chocolate Love", "Sweet Heart"}));
        lvl1.add(new Question("🌙 + ☕", "Night Coffee", new String[]{"Night Coffee", "Evening Tea", "Late Work", "Moon Tea"}));
        lvl1.add(new Question("⭐ + 🏆", "Star Winner", new String[]{"Star Winner", "Champion", "Star Player", "Gold Medal"}));
        lvl1.add(new Question("🍎 + 💻", "Apple Computer", new String[]{"Apple Computer", "Laptop", "Mac", "Tablet"}));
        lvl1.add(new Question("🎤 + 🎶", "Singing", new String[]{"Singing", "Talking", "Shouting", "Dancing"}));
        lvl1.add(new Question("⚽ + 🥅", "Football", new String[]{"Football", "Soccer", "Goal", "Game"}));
        lvl1.add(new Question("🌧️ + ☂️", "Rainy Day", new String[]{"Rainy Day", "Umbrella", "Storm", "Weather"}));
        lvl1.add(new Question("🍕 + 🧀", "Cheese Pizza", new String[]{"Cheese Pizza", "Pizza Cheese", "Cheesy Snack", "Pizza Slice"}));
        lvl1.add(new Question("🛌 + 🌙", "Sleep Time", new String[]{"Sleep Time", "Bed Night", "Dream Time", "Night Rest"}));
        lvl1.add(new Question("📷 + 🏞️", "Photography", new String[]{"Photography", "Photos", "Camera Work", "Picture"}));
        lvl1.add(new Question("✈️ + 🌍", "Travel", new String[]{"Travel", "Fly Around", "Vacation", "Journey"}));

        ArrayList<Question> lvl2 = new ArrayList<>();
        // Level 2: Word Fix (missing letters)
        lvl2.add(new Question("C_m_u__ty", "Community",
            new String[]{"Community", "Comunitee", "Communtiy", "Commuinity"}));
        lvl2.add(new Question("Respons_b_l_ty", "Responsibility",
            new String[]{"Responsibility", "Responsiblity", "Responsibilty", "Responcibility"}));
        lvl2.add(new Question("Ins_u_fi_ien_", "Insufficient",
            new String[]{"Insufficient", "Insufficiant", "Insuficient", "Insufficent"}));
        lvl2.add(new Question("Unfo_t_n_te", "Unfortunate",
            new String[]{"Unfortunate", "Unfortunete", "Unfortanate", "Unfotunate"}));
        lvl2.add(new Question("C_mpl_c_t_on", "Complication",
            new String[]{"Complication", "Complecation", "Complicetion", "Complicashion"}));
        lvl2.add(new Question("C_ns_de_at_on", "Consideration",
            new String[]{"Consideration", "Consideretion", "Concederation", "Consideresion"}));
        lvl2.add(new Question("Pr_s_rv_t_on", "Preservation",
            new String[]{"Preservation", "Presevation", "Presarvation", "Preservetion"}));
        lvl2.add(new Question("R_v_la_ion", "Revelation",
            new String[]{"Revelation", "Revelashion", "Revelattion", "Revelasion"}));
        lvl2.add(new Question("C_r_os_ty", "Curiosity",
            new String[]{"Curiosity", "Curiousty", "Curiocity", "Curiasity"}));
        lvl2.add(new Question("H_m_li_t_on", "Humiliation",
            new String[]{"Humiliation", "Humelation", "Humliation", "Humiletion"}));
        lvl2.add(new Question("Exce__ent", "Excellent",
            new String[]{"Excellent", "Excellant", "Exelent", "Excellint"}));
        lvl2.add(new Question("Imagi__tion", "Imagination",
            new String[]{"Imagination", "Imaginetion", "Imaginashion", "Imaginatoin"}));
        lvl2.add(new Question("Kn_wled__e", "Knowledge",
            new String[]{"Knowledge", "Knowlege", "Knowledg", "Knowladge"}));
        lvl2.add(new Question("Progr__ming", "Programming",
            new String[]{"Programming", "Programing", "Progrmming", "Progreamming"}));

        ArrayList<Question> lvl3 = new ArrayList<>();
        // Level 3: Memory sequences/Pattern(symbols)
        lvl3.add(new Question("🔴🟢🔵", "🔴🟢🔵", new String[]{"🔵🔴🟢", "🔴🔵🟢", "🔴🟢🔵", "🟢🔵🔴"}));
        lvl3.add(new Question("⭐🌙☀️", "⭐🌙☀️", new String[]{"🌙⭐☀️", "⭐🌙☀️", "☀️🌙⭐", "⭐☀️🌙"}));
        lvl3.add(new Question("🟥⬛🟨", "🟥⬛🟨", new String[]{"⬛🟥🟨", "🟥⬛🟨", "🟨⬛🟥", "🟥🟨⬛"}));
        lvl3.add(new Question("🍎🍌🍇", "🍎🍌🍇", new String[]{"🍇🍌🍎", "🍎🍌🍇", "🍌🍎🍇", "🍎🍇🍌"}));
        lvl3.add(new Question("🔺⚫🟩", "🔺⚫🟩", new String[]{"🔺🟩⚫", "⚫🔺🟩", "🔺⚫🟩", "🟩⚫🔺"}));
        lvl3.add(new Question("🎵🎶🎵", "🎵🎶🎵", new String[]{"🎶🎵🎵", "🎵🎶🎵", "🎶🎶🎵", "🎵🎵🎶"}));
        lvl3.add(new Question("❤️💛💙", "❤️💛💙", new String[]{"💛💙❤️", "❤️💛💙", "💙❤️💛", "💛❤️💙"}));
        lvl3.add(new Question("🌈☁️🌧️", "🌈☁️🌧️", new String[]{"🌈🌧️☁️", "☁️🌈🌧️", "🌈☁️🌧️", "🌧️☁️🌈"}));
        lvl3.add(new Question("🔔🔕🔔", "🔔🔕🔔", new String[]{"🔕🔔🔔", "🔔🔕🔔", "🔔🔔🔕", "🔕🔕🔔"}));
        lvl3.add(new Question("🌵🌸🌵", "🌵🌸🌵", new String[]{"🌸🌵🌵", "🌵🌵🌸", "🌵🌸🌵", "🌸🌸🌵"}));
        lvl3.add(new Question("🟢🔵🟡", "🟢🔵🟡", new String[]{"🟢🟡🔵", "🔵🟢🟡", "🟢🔵🟡", "🟡🔵🟢"}));
        lvl3.add(new Question("🍓🍋🍊", "🍓🍋🍊", new String[]{"🍊🍋🍓", "🍓🍋🍊", "🍋🍓🍊", "🍓🍊🍋"}));
        lvl3.add(new Question("🔷🔶🔷", "🔷🔶🔷", new String[]{"🔶🔷🔷", "🔷🔶🔷", "🔷🔷🔶", "🔶🔶🔷"}));
        lvl3.add(new Question("🌞🌜⭐", "🌞🌜⭐", new String[]{"🌜🌞⭐", "🌞🌜⭐", "⭐🌜🌞", "🌞⭐🌜"}));

        bank.put(1, lvl1);
        bank.put(2, lvl2);
        bank.put(3, lvl3);
    }

    public static Queue<Question> getRandomQuestions(int level, int count) {
        ArrayList<Question> list = new ArrayList<>(bank.get(level));
        Collections.shuffle(list);
        Queue<Question> q = new LinkedList<>();
        for (int i = 0; i < Math.min(count, list.size()); i++) {
            q.add(list.get(i));
        }
        return q;
    }

    public static List<Question> getQuestionsForLevels(Set<Integer> levels) {
    List<Question> list = new ArrayList<>();
    for(int lvl : levels) {
        list.addAll(bank.getOrDefault(lvl, new ArrayList<>()));
    }
    return list;
}


    public static int getTotalForLevel(int level) {
        return bank.getOrDefault(level, new ArrayList<>()).size();
    }
}
