package io.github.allison.cards.mscards.application;


import io.github.allison.cards.mscards.domain.Card;
import io.github.allison.cards.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card save(Card card){
        return repository.save(card);

    }

    public List<Card> getCardIncomeEqualOfLess(long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeBigDecimal);

    }
}
