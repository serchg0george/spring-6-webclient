package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CustomerClientImpl implements CustomerClient {

    public static final String CUSTOMER_PATH = "/api/v3/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";
    private final WebClient webClient;

    public CustomerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        return webClient.post()
                .uri(CUSTOMER_PATH)
                .body(Mono.just(customerDTO), CustomerDTO.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> Mono.just(voidResponseEntity
                        .getHeaders().get("Location").get(0)))
                .map(path -> path.split("/")[path.split("/").length - 1])
                .flatMap(this::getCustomerById);
    }

    @Override
    public Flux<CustomerDTO> getCustomerByName(String customerName) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path(CUSTOMER_PATH)
                        .queryParam("customerName", customerName).build())
                .retrieve()
                .bodyToFlux(CustomerDTO.class);
    }

    @Override
    public Mono<CustomerDTO> getCustomerById(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(CUSTOMER_PATH_ID)
                        .build(id))
                .retrieve()
                .bodyToMono(CustomerDTO.class);
    }

    @Override
    public Flux<CustomerDTO> listCustomerDto() {
        return webClient.get().uri(CUSTOMER_PATH)
                .retrieve().bodyToFlux(CustomerDTO.class);
    }

    @Override
    public Flux<JsonNode> listCustomerJsonNode() {
        return webClient.get().uri(CUSTOMER_PATH)
                .retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<Map> listCustomerMap() {
        return webClient.get().uri(CUSTOMER_PATH)
                .retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<String> listCustomer() {
        return webClient.get().uri(CUSTOMER_PATH)
                .retrieve().bodyToFlux(String.class);
    }
}
