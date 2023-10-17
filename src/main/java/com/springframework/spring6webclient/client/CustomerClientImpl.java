package com.springframework.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.springframework.spring6webclient.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class CustomerClientImpl implements CustomerClient {

    public static final String CUSTOMER_PATH = "/api/v3/customer";
    private final WebClient webClient;

    public CustomerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
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
