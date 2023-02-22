package io.github.allison.client.microservicecliente.infra.repository;

import io.github.allison.client.microservicecliente.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {


}
