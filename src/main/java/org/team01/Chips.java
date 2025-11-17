package org.team01;

public enum Chips {
    WHITE(1),
    RED(5),
    BLUE(10),
    GREEN(25),
    BLACK(100);


    private final int chipValue;

    private Chips(int chipValue) {
        this.chipValue = chipValue;
    }

    public int getChipValue() {
        return this.chipValue;
    }
}
