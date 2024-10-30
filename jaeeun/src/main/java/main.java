import useApi.GetApi;
import wordTimer.WordTimer;

import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);
    static String before = "";
    static int turn = 0;
    static GetApi api = new GetApi();

    static WordTimer wordTimer = new WordTimer();
    public static void main(String[] args) {
        System.out.print("사용자의 수를 입력해주세요: ");
        int playerNum = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        int[] points = new int[playerNum];
        String[] playerList = setPlayer(playerNum);

        while (true) {
            if (turn == playerNum) {
                turn = 0; // 턴 순환
            }

            System.out.println();
            System.out.println(playerList[turn] + "(" + points[turn] + ") 님의 차례: ");

            String word = wordTimer.startTimer(); // 타이머 시작 및 입력 대기

            // 입력 처리
            if (wordTimer.isTimeOut()) {
                System.out.println("실패: 입력이 없습니다.");
                break;
            }

            // 첫 입력 여부 체크
            if (before.isEmpty()) {
                if (!api.validcheck(word)) {
                    points[turn] -= 10;
                    System.out.println(playerList[turn] + "님, 오답입니다!");
                    break;
                }

                before = word;
                points[turn] += word.length();
            } else {
                boolean isValid = validCheck(word);

                if (!isValid) {
                    System.out.println(playerList[turn] + "님 오답을 입력하셨습니다!");
                    points[turn] -= 10;
                    break;
                }

                before = word;
                points[turn] += word.length();
            }

            ++turn; // 턴 증가
        }

        System.out.println();
        findWinner(playerNum, playerList, points);
    }

    private static String[] setPlayer(int playerNum) {
        String[] playerList = new String[playerNum];
        System.out.println("플레이어명은 영어로만 입력해주세요!");

        for (int i = 0; i < playerNum; i++) {
            System.out.print((i + 1) + "번째 플레이어: ");
            playerList[i] = sc.nextLine();
        }

        return playerList;
    }

    private static boolean validCheck(String word) {
        int before_len = before.length();
        return word.charAt(0) == before.charAt(before_len - 1) && api.validcheck(word);
    }

    private static void findWinner(int playerNum, String[] playerList, int[] points) {
        int max_point = -1;
        int max_idx = -1;

        for (int i = 0; i < playerNum; i++) {
            System.out.println(playerList[i] + "님의 점수는 " + points[i] + "점 입니다");
            if (points[i] > max_point) {
                max_point = points[i];
                max_idx = i;
            }
        }

        if (max_idx != -1) {
            System.out.println(playerList[max_idx] + "님의 승리!!!!");
        }
    }
}
