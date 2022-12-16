package org.monke.userservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return new ResponseEntity<>(
                Map.of("message", extractValidationMessage(exception)),
                HttpStatus.BAD_REQUEST);
    }

    private String extractValidationMessage(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception.getMessage();
        String[] messageParts = exceptionMessage.split(";");
        String finalPart = messageParts[messageParts.length -1];

        return finalPart.trim().replaceAll("default message \\[|]]","");
    }
}
