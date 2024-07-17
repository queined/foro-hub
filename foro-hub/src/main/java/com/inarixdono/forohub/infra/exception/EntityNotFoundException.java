package com.inarixdono.forohub.infra.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super("Entity not found");
    }
}
