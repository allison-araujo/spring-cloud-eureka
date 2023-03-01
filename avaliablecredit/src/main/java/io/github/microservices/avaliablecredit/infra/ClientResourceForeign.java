package io.github.microservices.avaliablecredit.infra;


import io.github.microservices.avaliablecredit.domain.model.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclient", path = "/client")
public interface ClientResourceForeign {

     @GetMapping(params = "cpf")
     ResponseEntity<DataClient> dataClient(@RequestParam("cpf") String cpf);


}
