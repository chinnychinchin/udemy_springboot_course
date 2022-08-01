package com.example.demo.exception;

import com.example.demo.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

// @ControllerAdvice makes this restcontroller class applicable across all the other controllers
// allows us to share methods across multiple @Controller classes
@ControllerAdvice
// We call this a rest controller because this is class that is providing a response back in case of errors
@RestController
public class CustomizedCompanyResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // Makes sure it handles all exception classes
    public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) // Makes sure it handles all exception classes
    public final ResponseEntity<Object> handleUserNotFoundException (UserNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
