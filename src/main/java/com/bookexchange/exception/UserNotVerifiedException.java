package com.bookexchange.exception;

public class UserNotVerifiedException extends RuntimeException {

    public UserNotVerifiedException() {
        super("User Not Verified. Only Verified Users can add/exchange Books");
    }
}
