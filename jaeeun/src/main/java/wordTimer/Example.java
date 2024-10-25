package wordTimer;

import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        WordTimer timer = new WordTimer();

        Scanner sc = new Scanner(System.in);
        String text = "";

        while (true) {
            timer.startTimer();
            text = sc.nextLine();
            if (!text.equals("")) {
                timer.setInput(true);
                break;
            } else {
                timer.setInput(false);
            }
        }
    }
}
