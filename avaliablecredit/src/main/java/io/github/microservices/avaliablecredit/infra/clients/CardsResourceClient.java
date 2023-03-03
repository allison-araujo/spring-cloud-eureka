package io.github.microservices.avaliablecredit.infra.clients;


import io.github.microservices.avaliablecredit.domain.model.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {
    @GetMapping(params = "cpf")
     ResponseEntity<List<CardClient>> getCardByClient(@RequestParam("cpf") String cpf);

}
