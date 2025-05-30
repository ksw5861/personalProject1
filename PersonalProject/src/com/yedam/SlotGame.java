package com.yedam;

import java.util.Scanner;

import com.yedam.SlotMachine;

public class SlotGame {
    private Player player;
    private SlotMachine slotMachine;
    private Scanner scanner;

    private static final int BET_AMOUNT = 10;      // 1판 베팅 금액
    private static final int WIN_REWARD = 500000;      // 당첨 시 상금

    public SlotGame(int startingMoney) {
        player = new Player(startingMoney);
        slotMachine = new SlotMachine();
        scanner = new Scanner(System.in);
    }

    public void play() {
        System.out.println("==🎰 슬롯머신 게임에 오신 것을 환영합니다 🎰==");

        while (true) {
            System.out.println("\n현재 보유 금액: " + player.getMoney() + "원");
            System.out.print("베팅 금액 " + BET_AMOUNT + "원을 내고 게임을 시작하려면 Enter를 누르세요 (종료하려면 q 입력): ");
            String input = scanner.nextLine(); 
            // 엔터를 누르면 "공백"이 입력되기 때문에 
            // if 구문을 거치지 않고 바로 슬롯을 돌리고 결과를 출력함.

            if (input.equalsIgnoreCase("q")) {
                System.out.println("게임을 종료합니다. 감사합니다!");
                break;
            }

            // 베팅금액 차감 시도
            if (!player.spendMoney(BET_AMOUNT)) {
                System.out.println("잔액이 부족합니다. 게임을 종료합니다.");
                break;
            }

            // 슬롯 돌리고 출력
            slotMachine.spin();
            slotMachine.printBoard();

            // 결과 확인
            if (slotMachine.checkWin()) {
                System.out.println("축하합니다! 상금 " + WIN_REWARD + "원을 획득했습니다!");
                System.out.printf("%d번의 도전끝에 당첨!!!", SlotMachine.cnt);
                player.addMoney(WIN_REWARD);
            } else {
                System.out.println("아쉽지만 당첨되지 않았습니다.");
                System.out.printf("현재도전횟수: %d", SlotMachine.cnt);
            }
        }
    }
}