package com.github.nortthon.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("user.notFound");
    }
}
