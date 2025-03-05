package com.retail.test.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.retail.entity.Order;
import com.retail.repository.OrderRepository;

@Testcontainers
@SpringBootTest
public class OrderRepositoryTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"))
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @BeforeAll
    static void startContainer() {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

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
