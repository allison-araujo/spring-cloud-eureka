package io.github.allison.cards.mscards.application;


import io.github.allison.cards.mscards.application.representation.CardSaveRequestDTO;
import io.github.allison.cards.mscards.application.representation.CardsInClientResponseDTO;
import io.github.allison.cards.mscards.domain.Card;
import io.github.allison.cards.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity register(@RequestBody CardSaveRequestDTO request){
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
    public ResponseEntity<List<CardsInClientResponseDTO>> getCardByClient(
            @RequestParam("cpf") String cpf){

        List<ClientCard> list = clientCardService.listCardByCpf(cpf);
        List<CardsInClientResponseDTO> resultList = list.stream()
                .map(CardsInClientResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);

    }

}
