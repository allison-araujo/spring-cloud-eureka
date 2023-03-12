package io.github.allison.cards.mscards.application;


import io.github.allison.cards.mscards.application.representation.CardSaveRequest;
import io.github.allison.cards.mscards.application.representation.CardWithClientResponse;
import io.github.allison.cards.mscards.domain.Card;
import io.github.allison.cards.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Log4j2
@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsController {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok MsCards";
    }
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeLimit(@RequestParam("income") Long income){
        List<Card> list = cardService.getCardIncomeEqualOfLess(income);
        return ResponseEntity.ok(list);

    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardWithClientResponse>> getCardsByClient(
            @RequestParam("cpf") String cpf){
        List<ClientCard> list = clientCardService.listCardByCpf(cpf);
        List<CardWithClientResponse> resultList = list.stream()
                .map(CardWithClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }


}
