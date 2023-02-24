package io.github.allison.client.microservicecliente.application;


import io.github.allison.client.microservicecliente.application.representation.ClientSaveRequest;
import io.github.allison.client.microservicecliente.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
@Slf4j
public class ClientResource {

    private final ClienteService service;

    @GetMapping
    public String status(){
        log.info("get status microservices client");
        return "ok";

    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request){
        Client client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }
    @GetMapping(params = "cpf")
    public ResponseEntity dataClient(@RequestParam("cpf") String cpf){
        var client = service.getByCPF(cpf);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

}
