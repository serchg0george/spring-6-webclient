package com.springframework.spring6webclient.client;

import reactor.core.publisher.Flux;

import java.util.Map;

public interface CustomerClient {

    Flux<Map> listCustomerMap();

    Flux<String> listCustomer();

}
