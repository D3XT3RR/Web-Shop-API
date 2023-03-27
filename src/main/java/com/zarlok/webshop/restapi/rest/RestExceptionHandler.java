package com.zarlok.webshop.restapi.rest;

import com.zarlok.webshop.restapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {

    //
    // Product Exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException exception){
        return response(exception, HttpStatus.NOT_FOUND);
    }

    //
    // Review Exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ReviewNotFoundException exception) {
        return response(exception, HttpStatus.NOT_FOUND);
    }

    //
    // User Exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException exception) {
        return response(exception,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistException exception){
        return response(exception, HttpStatus.CONFLICT);
    }

    //
    // Role Exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RoleNotFoundException exception){
        return response(exception, HttpStatus.NOT_FOUND);
    }

    //
    // Generic Exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException exception){
        return response(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception){
        return response(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HttpRequestMethodNotSupportedException exception){
        return response(exception, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RequestRejectedException exception){
        return response(exception, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AccessDeniedException exception){
        return response(exception, HttpStatus.FORBIDDEN);
    }

    // Helper Method
    private ResponseEntity<ErrorResponse> response(Exception e, HttpStatus httpStatus){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(httpStatus.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
