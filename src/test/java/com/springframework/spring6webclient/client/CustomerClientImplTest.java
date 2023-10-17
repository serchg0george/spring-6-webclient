package com.springframework.spring6webclient.client;

import com.springframework.spring6webclient.model.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@RequiredArgsConstructor
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerClientImplTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    @Order(10)
    void testDeleteCustomer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto()
                .next()
                .flatMap(dto -> customerClient.deleteCustomer(dto))
                .doOnSuccess(delete -> atomicBoolean.set(true))
                .subscribe();

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(9)
    void testPatchCustomer() {
        final String NAME = "PATCH PATCH PATCH NAME";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto()
                .next()
                .doOnNext(customerDTO -> customerDTO.setCustomerName(NAME))
                .flatMap(dto -> customerClient.patchCustomer(dto))
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(8)
    void testUpdateCustomer() {
        final String NAME = "NEW NEWEST NAME NNN";

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto()
                .next()
                .doOnNext(customerDTO -> customerDTO.setCustomerName(NAME))
                .flatMap(dto -> customerClient.updateCustomer(dto))
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(7)
    void testCreateCustomer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        CustomerDTO newDto = CustomerDTO.builder()
                .customerName("NEWEST CUSTOMER NAME")
                .build();

        customerClient.createCustomer(newDto)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(6)
    void testGetCustomerByName() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.getCustomerByName("My first customer")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(5)
    void testGetCustomerById() {
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
    @Order(4)
    void testGetListCustomerDto() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerDto().subscribe(dto -> {
            System.out.println(dto.getCustomerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(3)
    void testGetListCustomerJson() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(2)
    void testGetListCustomerMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    @Order(1)
    void testGetListCustomer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        customerClient.listCustomer().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}