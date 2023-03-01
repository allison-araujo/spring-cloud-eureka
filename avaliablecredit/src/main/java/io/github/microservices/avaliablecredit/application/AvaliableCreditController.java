package io.github.microservices.avaliablecredit.application;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.microservices.avaliablecredit.domain.model.SituationClient;
import io.github.microservices.avaliablecredit.application.AvaliableCreditService;


@RestController
@RequestMapping("avaliable-credit")
@RequiredArgsConstructor
public class AvaliableCreditController {

    private final AvaliableCreditService avaliableCreditService;

    @GetMapping
    public String status(){
        return "OK";
    }

    @GetMapping(value = "situation-client", params = "cpf")
    public ResponseEntity<SituationClient> findSituationClient(@RequestParam("cpf") String cpf){

        SituationClient situationClient = avaliableCreditService.getSituationClient(cpf);
        return ResponseEntity.ok(situationClient);

    }



}
