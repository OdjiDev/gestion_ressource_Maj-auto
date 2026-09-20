package com.odji.spring_back_end.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Object id) {
        super(String.format("%s introuvable avec l'identifiant : %s", resource, id));
    }
}

