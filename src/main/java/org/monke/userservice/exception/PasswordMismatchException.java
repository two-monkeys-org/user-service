package org.monke.userservice.exception;

public class PasswordMismatchException extends Exception{
    public PasswordMismatchException(String message) {
        super(message);
    }
}
