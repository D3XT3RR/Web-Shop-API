package com.zarlok.webshop.restapi.exception;

public class RoleAlreadyExistException extends RuntimeException{
    public RoleAlreadyExistException(String message) {
        super(message);
    }

    public RoleAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
