package com.springframework.spring6webclient.client;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {

    Flux<Map> listBeerMap();

    Flux<String> listBeer();

}
