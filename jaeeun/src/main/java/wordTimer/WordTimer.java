package wordTimer;

import java.util.Timer;
import java.util.TimerTask;

public class WordTimer {
    private int seconds = 10;
    private boolean isInput = false;
    private Timer timer;

    public WordTimer() {
        this.timer = new Timer();
    }

    // 타이머를 시작하는 메서드
    public void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isInput) {
                    System.out.println("입력 감지됨. 타이머 종료.");
                    timer.cancel();
                } else {
                    seconds--;
                    if (seconds == 0) {
                        System.out.println("💥시간 초과💥");
                        timer.cancel();
                    } else {
                        System.out.println("🕒남은 시간: " + seconds);
                        System.out.println();
                    }
                }
            }
        };

        // 타이머 시작 (0초 후부터 1초 간격으로 실행)
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    // 외부에서 입력을 감지했을 때 호출하는 메서드
    public void setInput(boolean input) {
        this.isInput = input;
    }

    // 타이머 종료 여부를 반환하는 메서드 (필요에 따라 추가)
    public boolean isTimerRunning() {
        return seconds > 0 && !isInput;
    }
}
