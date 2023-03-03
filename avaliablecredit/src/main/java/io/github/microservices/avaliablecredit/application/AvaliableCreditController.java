package io.github.microservices.avaliablecredit.application;


import io.github.microservices.avaliablecredit.application.exception.DadosClientNotFoundException;
import io.github.microservices.avaliablecredit.application.exception.ErrorCommunicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.github.microservices.avaliablecredit.domain.model.SituationClient;


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
    public ResponseEntity findSituationClient(@RequestParam("cpf") String cpf){
        try {
            SituationClient situationClient = avaliableCreditService.getSituationClient(cpf);
            return ResponseEntity.ok(situationClient);
        }catch (DadosClientNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(ErrorCommunicationException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }



}
