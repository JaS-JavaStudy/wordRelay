import java.util.ArrayList;
public class repetition {
    private ArrayList<String> usedWords;
    public repetition() {
        this.usedWords = new ArrayList<>();
    }
    public void addWord(String word) {
        this.usedWords.add(word);
    }
    public boolean isRepeated(String word) {
        return usedWords.contains(word);
    }
}