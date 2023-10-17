package com.springframework.spring6webclient.client;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@RequiredArgsConstructor
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void testListBeerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}