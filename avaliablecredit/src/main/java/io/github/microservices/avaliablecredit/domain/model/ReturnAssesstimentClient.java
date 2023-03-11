package io.github.microservices.avaliablecredit.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnAssesstimentClient {

    private List<CardApproved> card;
}
