package io.github.microservices.avaliablecredit.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituationClient {

    private DataClient client;
    private List<CardClient> cards;

}
