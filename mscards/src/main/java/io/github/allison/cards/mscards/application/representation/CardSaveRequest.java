package io.github.allison.cards.mscards.application.representation;


import io.github.allison.cards.mscards.domain.Card;
import io.github.allison.cards.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {

    private String name;
    private FlagCard flag;

    private BigDecimal income;

    private BigDecimal limit;

    public Card toModel(){
        return new Card(name, flag,income,limit);
    }
}
