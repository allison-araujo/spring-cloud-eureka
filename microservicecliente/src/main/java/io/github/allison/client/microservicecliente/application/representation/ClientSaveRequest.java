package io.github.allison.client.microservicecliente.application.representation;


import io.github.allison.client.microservicecliente.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {

    private String cpf;
    private String name;
    private Integer age;


    public Client toModel(){
        return new Client(cpf,name,age);

    }

}
