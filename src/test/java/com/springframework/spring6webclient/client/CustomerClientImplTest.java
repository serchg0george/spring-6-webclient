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
    void testListCustomerJson() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListCustomerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testListCustomer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}