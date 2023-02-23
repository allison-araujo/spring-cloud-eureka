package io.github.allison.client.microservicecliente.application;

import io.github.allison.client.microservicecliente.domain.Client;
import io.github.allison.client.microservicecliente.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClientRepository repository;

    @Transactional
    public Client save(Client client){
        return repository.save(client);

    }

    public Optional<Client> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }

}
