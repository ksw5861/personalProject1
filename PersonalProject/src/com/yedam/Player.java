package com.yedam;


public class Player {
    private int money;

    public Player(int initialMoney) {
        this.money = initialMoney;
    }

    public int getMoney() {
        return money;
    }

    // 돈 추가메소드 (당첨시 현재 가진돈에 당첨금 추가)
    public void addMoney(int amount) {
        if (amount > 0) {
            money += amount;
        }
    }

    // 돈 사용하는 메소드 ->bet amount 원씩베팅
    public boolean spendMoney(int amount) {
        if (amount > 0 && money >= amount) {
            money -= amount;
            return true; // 성공적으로 지불함
        }
        return false; // 돈 부족
    }
}