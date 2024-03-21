package com.bookexchange.enums;

public enum ItemType {
    BOOK(1);

    private int type;

    ItemType(int type) {
       this.type = type;
    }

    public int getType() {
        return type;
    }
}
