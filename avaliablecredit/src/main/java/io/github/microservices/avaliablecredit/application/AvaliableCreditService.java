package io.github.microservices.avaliablecredit.application;


import feign.FeignException;
import io.github.microservices.avaliablecredit.application.exception.DadosClientNotFoundException;
import io.github.microservices.avaliablecredit.application.exception.ErrorCommunicationMicroservicesException;
import io.github.microservices.avaliablecredit.domain.model.*;
import io.github.microservices.avaliablecredit.infra.clients.CardsResourceClient;
import io.github.microservices.avaliablecredit.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliableCreditService {

    private final ClientResourceClient resourceClient;
    private final CardsResourceClient cardsClient;


    public SituationClient getSituationClient(String cpf)
            throws DadosClientNotFoundException , ErrorCommunicationMicroservicesException {
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

            throw new ErrorCommunicationMicroservicesException(e.getMessage(),status);

        }
    }


    public ReturnAssesstimentClient carryOutEvaluation(String cpf, Long income)
            throws DadosClientNotFoundException, ErrorCommunicationMicroservicesException {
        try {
            ResponseEntity<DataClient> dataClientResponse = resourceClient.dataClient(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsClient.getCardsIncomeLimit(income);

            List<Card> cards = cardsResponse.getBody();
            var listCardApproved =  cards.stream().map(card -> {


                DataClient dataClient = dataClientResponse.getBody();

                BigDecimal limitBasic = card.getLimitBasic();
                BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal ageBD = BigDecimal.valueOf(dataClient.getAge());
                var fat = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = fat.multiply(limitBasic);


                CardApproved approved = new CardApproved();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlag());
                approved.setLimitApproved(limitApproved);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnAssesstimentClient(listCardApproved);


        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClientNotFoundException();
            }

            throw new ErrorCommunicationMicroservicesException(e.getMessage(), status);

        }
    }



}
