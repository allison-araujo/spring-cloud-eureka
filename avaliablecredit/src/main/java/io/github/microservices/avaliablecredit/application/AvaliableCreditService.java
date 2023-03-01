package io.github.microservices.avaliablecredit.application;


import io.github.microservices.avaliablecredit.domain.model.DataClient;
import io.github.microservices.avaliablecredit.domain.model.SituationClient;
import io.github.microservices.avaliablecredit.infra.ClientResourceForeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliableCreditService {

    private  final ClientResourceForeign clientResourceForeign;


    public SituationClient getSituationClient(String cpf){
        //obter dados dos clinentes -MSLCIENT
        //obter cards client - //MSCARDS
        //comunicar com outros microserviços


        ResponseEntity<DataClient> dataClientResponse =  clientResourceForeign.dataClient(cpf);
            return SituationClient.builder()
                    .client(dataClientResponse.getBody())
                    .build();
    }


}
