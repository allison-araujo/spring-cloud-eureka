package io.github.allison.cards.mscards.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data

public class ClientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    @ManyToOne()
    @JoinColumn(name = "id_card")
    private Card cards;
    private BigDecimal limit;
}
