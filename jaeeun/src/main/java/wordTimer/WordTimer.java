package wordTimer;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class WordTimer {
    private int seconds = 10;
    private boolean isInput = false;
    private boolean timeOut = false;
    private Timer timer; // 매번 새 인스턴스를 생성할 것
    private Scanner sc = new Scanner(System.in);
    private boolean isTimerRunning = false; // 타이머 실행 중인지 여부

    public WordTimer() {
        this.timer = new Timer();
    }

    // 타이머를 초기화하는 메서드
    public void resetTimer() {
        this.seconds = 10; // 초 초기화
        this.isInput = false; // 입력 초기화
        this.isTimerRunning = false; // 타이머 실행 상태 초기화
    }

    // 타이머를 시작하는 메서드
    public String startTimer() {
        resetTimer(); // 타이머 초기화

        // 새 Timer 인스턴스 생성
        timer = new Timer();

        if (isTimerRunning) {
            System.out.println("타이머가 이미 실행 중입니다.");
            return ""; // 또는 적절한 예외 처리
        }

        String[] inputContainer = new String[1]; // 입력을 저장할 배열

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isInput) {
                    System.out.println("입력 감지됨. 타이머 종료.");
                    cancelTimer(); // 타이머 종료
                } else {
                    seconds--;
                    if (seconds == 0) {
                        System.out.println("💥시간 초과💥");
                        timeOut = true;
                        cancelTimer(); // 타이머 종료
                    } else {
                        System.out.println("🕒남은 시간: " + seconds);
                    }
                }
            }
        };

        // 타이머 시작
        timer.scheduleAtFixedRate(task, 0, 1000);
        isTimerRunning = true; // 타이머가 실행 중임을 표시

        // 입력을 기다리기 위한 별도의 스레드 생성
        new Thread(() -> {
            inputContainer[0] = sc.nextLine(); // 입력을 저장
            setInput(true); // 입력 감지
            cancelTimer(); // 타이머 종료
        }).start();

        // 입력을 기다리는 루프
        while (!isInput) {
            try {
                Thread.sleep(100); // 잠시 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isTimerRunning = false; // 타이머 실행 상태 초기화
        return inputContainer[0]; // 입력값 반환
    }

    // 외부에서 입력을 감지했을 때 호출하는 메서드
    public void setInput(boolean input) {
        this.isInput = input;
    }

    public boolean isInput() {
        return isInput;
    }

    public boolean isTimeOut() {
        return timeOut;
    }

    // 타이머가 실행 중인지 확인하는 메서드
    public boolean isTimerRunning() {
        return isTimerRunning; // 타이머 실행 상태 반환
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge(); // 필요시에 이전 태스크 제거
            isTimerRunning = false; // 타이머 실행 상태 초기화
        }
    }
}
