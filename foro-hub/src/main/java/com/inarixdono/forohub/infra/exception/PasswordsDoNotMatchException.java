package com.inarixdono.forohub.infra.exception;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
