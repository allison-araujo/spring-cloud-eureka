package io.github.microservices.avaliablecredit.application.exception;

import lombok.Getter;

public class ErrorCommunicationException extends Exception{
    @Getter
    private Integer status;
    public ErrorCommunicationException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
