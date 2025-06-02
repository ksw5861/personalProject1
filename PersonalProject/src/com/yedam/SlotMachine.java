package com.yedam;

import java.util.Random; // 랜덤 값을 생성하기 위한 클래스 임포트
import com.yedam.SlotSymbol; // 슬롯 심볼 enum 클래스 임포트

public class SlotMachine {
    // 슬롯머신의 행(줄) 수
    private static int ROWS = 3;

    // 슬롯머신의 열(칸) 수
    private static int COLS = 5;

    // 슬롯 판을 나타내는 2차원 배열 (각 칸에 심볼이 들어감)
    private SlotSymbol[][] board;

    // 랜덤 심볼 생성을 위한 Random 객체
    private Random random;

    // 정적 변수: 슬롯을 몇 번 돌렸는지 누적 횟수 저장
    public static int cnt = 0;

    // 생성자: 슬롯 보드 초기화 및 Random 객체 생성
    public SlotMachine() {
        board = new SlotSymbol[ROWS][COLS]; // 3행 5열 보드 생성
        random = new Random(); // 랜덤 객체 초기화
    }

    // 슬롯 판에 무작위 심볼을 채워 넣는 메소드 (슬롯 돌리기)
    public void spin() {
        for (int i = 0; i < ROWS; i++) { // 각 행마다
            for (int j = 0; j < COLS; j++) { // 각 열마다
                SlotSymbol[] symbols = SlotSymbol.values(); // 모든 심볼 리스트 가져오기
                int idx = random.nextInt(symbols.length); // 0~(심볼 수 - 1) 사이 난수 생성
                board[i][j] = symbols[idx]; // 해당 위치에 무작위 심볼 저장
            }
        }
        cnt++; // 도전 횟수 증가
    }

    // 현재 보드 상태를 콘솔에 출력하는 메소드
    public void printBoard() {
        for (int i = 0; i < ROWS; i++) { // 각 줄마다
            System.out.print("│ "); // 구분 선 출력
            for (int j = 0; j < COLS; j++) { // 각 칸의 심볼 출력
                System.out.print(board[i][j].getSymbol() + " "); // 심볼의 문자열 출력
            }
            System.out.println("│"); // 줄 끝 구분
        }
    }

    // 각 가로줄이 모두 같은 심볼로 채워졌는지 검사 (당첨 여부 확인)
    public boolean checkWin() {
        for (int i = 0; i < ROWS; i++) {
            SlotSymbol first = board[i][0]; // 해당 줄의 첫 번째 심볼
            boolean allMatch = true; // 일치 여부 초기화

            for (int j = 1; j < COLS; j++) { // 두 번째부터 끝까지 비교
                if (board[i][j] != first) { // 하나라도 다르면 불일치
                    allMatch = false;
                    break; // 반복 종료
                }
            }

            if (allMatch) { // 전부 같은 경우 당첨
                System.out.println("🎉 당첨! " + first.getSymbol() + "가 [" + (i + 1) + "]번 줄에 전부 모였습니다!!!");
                return true; // 당첨 시 true 반환
            }
        }

        return false; // 어떤 줄도 당첨이 아닌 경우 false 반환
    }
}