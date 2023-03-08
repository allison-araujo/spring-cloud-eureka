package io.github.microservices.avaliablecredit.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class ReturnAssesstimentClient {

    private List<CardApproved> card;
}
