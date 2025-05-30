package com.yedam;

import java.util.Random;

import com.yedam.SlotSymbol;

public class SlotMachine {
    private static final int ROWS = 3;
    private static final int COLS = 5;

    private SlotSymbol[][] board;
    private Random random;

    public SlotMachine() {
        board = new SlotSymbol[ROWS][COLS];
        random = new Random();
    }
    public static int cnt = 0;
    // 슬롯판을 랜덤 심볼로 채우기
    public void spin() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                SlotSymbol[] symbols = SlotSymbol.values();
                int idx = random.nextInt(symbols.length);
                board[i][j] = symbols[idx];
            }
        }
        cnt++;
    }

    // 슬롯판 출력
    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            System.out.print("│ ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j].getSymbol() + " ");
            }
            System.out.println("│");
        }
    }
    
    // 가로줄 당첨 검사
    public boolean checkWin() {
        for (int i = 0; i < ROWS; i++) {
            SlotSymbol first = board[i][0];
            boolean allMatch = true;
            for (int j = 1; j < COLS; j++) {
                if (board[i][j] != first) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                System.out.println("🎉 당첨! " + first.getSymbol() + "가 [" + (i + 1) + "]번 줄에 전부 모였습니다!!!");
                return true; // 당첨된 경우 true 반환
            }
        }
        return false; // 당첨 없으면 false 반환
    }
}