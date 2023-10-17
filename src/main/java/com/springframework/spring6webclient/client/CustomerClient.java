package com.springframework.spring6webclient.client;

import reactor.core.publisher.Flux;

public interface CustomerClient {

    Flux<String> listCustomer();

}
