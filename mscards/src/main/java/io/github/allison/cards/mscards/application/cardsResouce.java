package io.github.allison.cards.mscards.application;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class cardsResouce {

    @GetMapping
    public String status(){
        return "ok MsCards";
    }


}
