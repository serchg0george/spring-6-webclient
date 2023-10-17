package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.BeerDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Flow;

public interface BeerClient {

    Flux<BeerDTO> listBeerDto();

    Flux<JsonNode> listBeerJsonNode();

    Flux<Map> listBeerMap();

    Flux<String> listBeer();

    Mono<BeerDTO> getBeerById(String id);
}