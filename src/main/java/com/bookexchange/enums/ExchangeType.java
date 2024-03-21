package com.bookexchange.enums;

public enum ExchangeType {
    EXCHANGE(1),
    BORROW(2);

    private int type;
    ExchangeType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
