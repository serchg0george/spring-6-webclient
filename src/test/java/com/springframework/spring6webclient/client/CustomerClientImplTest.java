package com.springframework.spring6webclient.client;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@RequiredArgsConstructor
class CustomerClientImplTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void testGetBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto()
                .flatMap(dto -> customerClient.getCustomerById(dto.getId()))
                .subscribe(byIdDto -> {
            System.out.println(byIdDto.getCustomerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetListCustomerDto() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto().subscribe(dto -> {
            System.out.println(dto.getCustomerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetListCustomerJson() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetListCustomerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetListCustomer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}