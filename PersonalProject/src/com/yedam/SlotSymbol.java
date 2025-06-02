package com.yedam;


public enum SlotSymbol {
    SLOT("🎰"),
    DIAMOND("💎"),
    HEART("❤"),
    TROPHY("🏆"),
    CLOVER("🍀");

    private String symbol;

    SlotSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}