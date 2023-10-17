package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.CustomerDTO;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.Flow;

public interface CustomerClient {

    Flux<CustomerDTO> listCustomerDto();

    Flux<JsonNode> listCustomerJsonNode();

    Flux<Map> listCustomerMap();

    Flux<String> listCustomer();

    Mono<CustomerDTO> getCustomerById(String id);
}