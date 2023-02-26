package io.github.allison.cards.mscards.application;


import io.github.allison.cards.mscards.domain.ClientCard;
import io.github.allison.cards.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {


    private ClientCardRepository repository;

    public List<ClientCard> listCardByCpf(String cpf){
        return repository.findByCpf(cpf);
    }



}
