package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {

    Flux<BeerDTO> listBeerDto();

    Flux<JsonNode> listBeerJsonNode();

    Flux<Map> listBeerMap();

    Flux<String> listBeer();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDTO> createBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(BeerDTO beerDTO);

    Mono<Void> deleteBeer(BeerDTO beerDTO);
}