package repetition;

import java.util.ArrayList;
public class Repetition {
    private ArrayList<String> usedWords;
    public Repetition() {
        this.usedWords = new ArrayList<>();
    }
    public void addWord(String word) {
        this.usedWords.add(word);
    }
    public boolean isRepeated(String word) {
        return usedWords.contains(word);
    }
}