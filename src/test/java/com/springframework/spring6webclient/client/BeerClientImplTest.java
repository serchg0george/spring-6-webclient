package com.springframework.spring6webclient.client;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void testListBeer() {
        beerClient.listBeer().subscribe(response -> {
            System.out.println(response);
        });

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}