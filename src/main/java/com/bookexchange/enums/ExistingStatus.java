package com.bookexchange.enums;

public enum ExistingStatus {
    ACTIVE(1),
    UNDER_EXCHANGE(2);

    private int status;
    ExistingStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
