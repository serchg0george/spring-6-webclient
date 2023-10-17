package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {

    Flux<JsonNode> listBeerJsonNode();

    Flux<Map> listBeerMap();

    Flux<String> listBeer();

}