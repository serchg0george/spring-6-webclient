package com.springframework.spring6webclient.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CustomerClientImpl implements CustomerClient {

    private final WebClient webClient;

    public CustomerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }


    @Override
    public Flux<String> listCustomer() {
        return webClient.get().uri("/api/v3/customer", String.class)
                .retrieve().bodyToFlux(String.class);
    }
}
