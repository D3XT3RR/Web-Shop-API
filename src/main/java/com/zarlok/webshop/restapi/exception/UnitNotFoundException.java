package com.zarlok.webshop.restapi.exception;

public class UnitNotFoundException extends RuntimeException{
    public UnitNotFoundException() {
        super();
    }

    public UnitNotFoundException(String message) {
        super(message);
    }

    public UnitNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
