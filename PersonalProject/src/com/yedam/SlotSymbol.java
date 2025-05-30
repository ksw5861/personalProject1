package com.yedam;


public enum SlotSymbol {
    MONEY("🎰"),
    DIAMOND("💎"),
    HEART("❤"),
    TROPHY("🏆"),
    CLOVER("🍀");

    private final String symbol;

    SlotSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}