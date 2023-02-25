package io.github.allison.cards.mscards.application;


import io.github.allison.cards.mscards.application.representation.CardSaveRequestDTO;
import io.github.allison.cards.mscards.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService service;

    @GetMapping
    public String status(){
        return "ok MsCards";
    }
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequestDTO request){
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }


    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeLimit(@RequestParam("income") Long income){
        List<Card> list = service.getCardIncomeEqualOfLess(income);
        return ResponseEntity.ok(list);


    }

}
