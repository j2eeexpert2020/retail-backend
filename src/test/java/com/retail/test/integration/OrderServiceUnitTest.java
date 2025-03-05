package com.retail.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.retail.entity.Order;
import com.retail.repository.OrderRepository;
import com.retail.service.OrderService;

@ExtendWith(MockitoExtension.class)

public class OrderServiceUnitTest {
	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;

	public OrderServiceUnitTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetOrderById() {
		Order order = new Order();
		order.setId(1L);
		when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
		Order retrievedOrder = orderService.getOrderById(1L);
		assertEquals(1L, retrievedOrder.getId());
	}
}
