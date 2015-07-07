package com.itworks.firstaid;

public enum CoverEnumerator {
    NONE(0),
    PHOTO(1),
    VIDEO(2);
    private final int value;
    private CoverEnumerator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
