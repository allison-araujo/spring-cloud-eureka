package io.github.microservices.avaliablecredit.application.exception;

public class DadosClientNotFoundException extends Exception{
    public DadosClientNotFoundException() {
        super("Data client not found cpf");
    }
}
