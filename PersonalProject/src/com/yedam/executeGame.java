package com.yedam;

import com.yedam.SlotGame;
import com.yedam.SlotMachine;

public class executeGame {
    public static void main(String[] args) {
        SlotMachine slot = new SlotMachine();
        slot.spin();
        slot.printBoard();
        SlotGame game = new SlotGame(99999); // 시작 돈 100원
        game.play();

        if (slot.checkWin()) {
            System.out.println("축하합니다! 상금을 받으세요!");
        } else {
            System.out.println("아쉽지만, 다시 도전하세요!");
        }
    }
}