package io.github.microservices.avaliablecredit.application;


import io.github.microservices.avaliablecredit.application.exception.DadosClientNotFoundException;
import io.github.microservices.avaliablecredit.application.exception.ErrorCommunicationMicroservicesException;
import io.github.microservices.avaliablecredit.domain.model.DataAssessment;
import io.github.microservices.avaliablecredit.domain.model.ReturnAssesstimentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        }catch(ErrorCommunicationMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }


    @PostMapping
    public ResponseEntity carryOutEvaluation(@RequestBody DataAssessment data){

        try {
            ReturnAssesstimentClient returnAssesstimentClient = avaliableCreditService.carryOutEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(returnAssesstimentClient);

        }catch (DadosClientNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch(ErrorCommunicationMicroservicesException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }



}
