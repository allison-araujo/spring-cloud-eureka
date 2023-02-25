package io.github.allison.cards.mscards.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FlagCard flag;
    private BigDecimal income;

    private BigDecimal basicLimite;

    public Card(String name, FlagCard flag, BigDecimal income, BigDecimal basicLimite) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.basicLimite = basicLimite;
    }
}