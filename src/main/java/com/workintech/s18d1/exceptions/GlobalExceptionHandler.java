package com.workintech.s18d1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleBurgerException(BurgerException exception) {
        BurgerErrorResponse burgerErrorResponse = new BurgerErrorResponse(exception.getMessage(), exception.getHttpStatus().value(), System.currentTimeMillis());
        return new ResponseEntity<>(burgerErrorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleAllExceptions(Exception exception) {
        BurgerErrorResponse errorResponse = new BurgerErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
