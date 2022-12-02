package org.monke.userservice.exception;

public class EmailTakenException extends Exception{
    public EmailTakenException(String message) {
        super(message);
    }
}
