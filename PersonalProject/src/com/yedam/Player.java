package com.yedam;


public class Player {
    private int money;

    public Player(int initialMoney) {
        this.money = initialMoney;
    }

    public int getMoney() {
        return money;
    }

    // 돈을 넣는 메서드 (예: 당첨금 추가)
    public void addMoney(int amount) {
        if (amount > 0) {
            money += amount;
        }
    }

    // 돈을 쓰는 메서드 (예: 베팅, 구매 등)
    public boolean spendMoney(int amount) {
        if (amount > 0 && money >= amount) {
            money -= amount;
            return true; // 성공적으로 지불함
        }
        return false; // 돈 부족
    }
}