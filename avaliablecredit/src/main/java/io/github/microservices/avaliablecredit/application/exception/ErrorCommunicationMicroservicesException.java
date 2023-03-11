package io.github.microservices.avaliablecredit.application.exception;

import lombok.Getter;

public class ErrorCommunicationMicroservicesException extends Exception{
    @Getter
    private Integer status;
    public ErrorCommunicationMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
