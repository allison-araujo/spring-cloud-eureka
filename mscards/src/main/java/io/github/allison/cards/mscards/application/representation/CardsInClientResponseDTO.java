package io.github.allison.cards.mscards.application.representation;


import io.github.allison.cards.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsInClientResponseDTO {

    private String name;
    private String flag;
    private BigDecimal limitReleased;


    public static CardsInClientResponseDTO fromModel(ClientCard model){
        return new CardsInClientResponseDTO(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimit()
        );
    }
}
