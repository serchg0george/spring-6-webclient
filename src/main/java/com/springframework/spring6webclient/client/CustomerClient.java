package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CustomerClient {

    Flux<CustomerDTO> listCustomerDto();

    Flux<JsonNode> listCustomerJsonNode();

    Flux<Map> listCustomerMap();

    Flux<String> listCustomer();

    Mono<CustomerDTO> getCustomerById(String id);

    Flux<CustomerDTO> getCustomerByName(String customerName);

    Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO);

    Mono<CustomerDTO> updateCustomer(CustomerDTO customerDTO);

    Mono<CustomerDTO> patchCustomer(CustomerDTO customerDTO);

    Mono<Void> deleteCustomer(CustomerDTO customerDTO);
}