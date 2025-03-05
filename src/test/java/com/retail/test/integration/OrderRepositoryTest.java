package com.retail.test.integration;


import com.retail.entity.Order;
import com.retail.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
public class OrderRepositoryTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"))
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrder() {
        Order order = new Order();
        order.setProductId(1L);
        order.setQuantity(2);
        order.setPrice(new java.math.BigDecimal("29.99"));
        order.setOrderStatus("PENDING");
        Order savedOrder = orderRepository.save(order);
        assertNotNull(savedOrder.getId());
    }
}