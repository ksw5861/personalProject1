package com.yedam;

import java.util.Scanner; // 사용자 입력을 받기 위해 Scanner 클래스 임포트
import com.yedam.SlotMachine; // 슬롯머신 기능을 제공하는 클래스 임포트

public class SlotGame {
    // 플레이어의 정보를 담을 객체
    private Player player;

    // 슬롯머신 동작을 담당하는 객체
    private SlotMachine slotMachine;

    // 사용자 입력을 받기 위한 Scanner 객체
    private Scanner scanner;

    // 한 판당 고정된 베팅 금액
    private static int BET_AMOUNT = 10;

    // 당첨 시 지급되는 상금
    private static int WIN_REWARD = 500000;

    // 화면을 깨끗하게 보이도록 공백 줄을 여러 번 출력하는 메서드
    public static void screenClear() {
        for (int i = 0; i < 30; i++) System.out.println(); // 공백 30줄 출력
    }

    // 텍스트를 천천히 한 글자씩 출력하는 메소드 (몰입감을 위한 연출)
    public static void slowPrint(String text, long delayMillis) {
        for (char ch : text.toCharArray()) { // 문자열을 문자 배열로 변환하여 반복
            System.out.print(ch); // 문자 출력
            try {
                Thread.sleep(delayMillis); // 설정한 시간(ms)만큼 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 발생 시 현재 스레드를 다시 인터럽트 상태로 설정
            }
        }
        System.out.println(); // 한 줄 띄우기
    }

    // 생성자: 게임 시작 시 플레이어의 시작 금액을 설정하고 객체 초기화
    public SlotGame(int startingMoney) {
        player = new Player(startingMoney); // 플레이어 객체 생성
        slotMachine = new SlotMachine(); // 슬롯머신 객체 생성
        scanner = new Scanner(System.in); // 입력받기 위한 Scanner 초기화
    }

    // 실제 게임 실행 메서드
    public void play() {
        System.out.println("==🎰 슬롯머신 게임에 오신 것을 환영합니다 🎰==");

        while (true) { // 무한 루프: 사용자가 종료를 선택할 때까지 반복
            System.out.println("\n현재 보유 금액: " + player.getMoney() + "원");

            // 안내 메시지 출력 및 입력 받기
            System.out.print("베팅 금액 " + BET_AMOUNT + "원을 내고 게임을 시작하려면 Enter를 누르세요 (종료하려면 q 입력): ");
            String input = scanner.nextLine(); 

            // 사용자가 'q' 또는 'Q'를 입력하면 게임 종료
            if (input.equalsIgnoreCase("q")) {
                System.out.println("게임을 종료합니다. 감사합니다!");
                break;
            }

            // 플레이어가 베팅 금액을 지불할 수 있는지 확인
            if (!player.spendMoney(BET_AMOUNT)) {
                System.out.println("잔액이 부족합니다. 게임을 종료합니다.");
                break; // 돈이 부족하면 게임 종료
            }

            // 슬롯머신 돌리기 전 화면 연출
            screenClear(); // 화면 클리어
            slowPrint("|🎰 🎰 🎰 🎰 🎰|", 20); // 점점 느려지는 연출
            slowPrint("|🎰 🎰 🎰 🎰 🎰|", 50);
            slowPrint("|🎰 🎰 🎰 🎰 🎰|", 90);
            screenClear(); // 다시 화면 클리어

            // 슬롯머신 돌리기
            slotMachine.spin(); // 슬롯 보드 회전
            slotMachine.printBoard(); // 결과 출력

            // 당첨 여부 확인
            if (slotMachine.checkWin()) {
                System.out.println("축하합니다! 상금 " + WIN_REWARD + "원을 획득했습니다!");
                System.out.printf("%d번의 도전끝에 당첨!!!", SlotMachine.cnt - 1); // 도전 횟수 출력
                player.addMoney(WIN_REWARD); // 플레이어에게 상금 지급
            } else {
                System.out.println("아쉽지만 당첨되지 않았습니다.");
                System.out.printf("현재도전횟수: %d", SlotMachine.cnt - 1); // 현재까지 도전 횟수 출력
            }
        }
    }
}