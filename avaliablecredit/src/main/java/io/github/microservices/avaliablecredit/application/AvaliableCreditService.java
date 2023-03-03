package io.github.microservices.avaliablecredit.application;


import feign.FeignException;
import io.github.microservices.avaliablecredit.application.exception.DadosClientNotFoundException;
import io.github.microservices.avaliablecredit.application.exception.ErrorCommunicationException;
import io.github.microservices.avaliablecredit.domain.model.CardClient;
import io.github.microservices.avaliablecredit.domain.model.DataClient;
import io.github.microservices.avaliablecredit.domain.model.SituationClient;
import io.github.microservices.avaliablecredit.infra.clients.CardsResourceClient;
import io.github.microservices.avaliablecredit.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliableCreditService {

    private final ClientResourceClient resourceClient;
    private final CardsResourceClient cardsClient;


    public SituationClient getSituationClient(String cpf)
            throws DadosClientNotFoundException ,ErrorCommunicationException{
        try {
            ResponseEntity<DataClient> dataClientResponse = resourceClient.dataClient(cpf);
            ResponseEntity<List<CardClient>> cardResponse = cardsClient.getCardByClient(cpf);

            return SituationClient
                    .builder()
                    .client(dataClientResponse.getBody())
                    .cards(cardResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw  new DadosClientNotFoundException();
            }

            throw new ErrorCommunicationException(e.getMessage(),status);

        }
    }


}
