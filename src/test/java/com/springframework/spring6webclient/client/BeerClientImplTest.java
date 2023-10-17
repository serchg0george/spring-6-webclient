package com.springframework.spring6webclient.client;

import com.springframework.spring6webclient.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    @Order(10)
    void testDeleteBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDto()
                .next()
                .flatMap(dto -> beerClient.deleteBeer(dto))
                .doOnSuccess(delete -> atomicBoolean.set(true))
                .subscribe();

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(9)
    void testPatchBeer() {
        final String NAME = "PATCH BEER NAME";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDto()
                .next()
                .doOnNext(customerDTO -> customerDTO.setBeerName(NAME))
                .flatMap(dto -> beerClient.patchBeer(dto))
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(8)
    void testUpdateBeer() {
        final String NAME = "NEW BEER NAME";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDto()
                .next()
                .doOnNext(customerDTO -> customerDTO.setBeerName(NAME))
                .flatMap(dto -> beerClient.updateBeer(dto))
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(7)
    void testCreateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle("IPA")
                .quantityOnHand(150)
                .upc("12345")
                .build();

        beerClient.createBeer(newDto)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(6)
    void testGetBeerByBeerStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.getBeerByBeerStyle("Pale Ale")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(5)
    void testGetBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDto()
                .flatMap(dto -> beerClient.getBeerById(dto.getId()))
                .subscribe(byIdDto -> {
                    System.out.println(byIdDto.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(4)
    void testGetListBeerDto() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerDto().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(3)
    void testGetListBeerJson() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(2)
    void testGetListBeerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(1)
    void testGetListBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        beerClient.listBeer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}