package com.nayan.obai.order.service.impl;

import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.exception.OrderServiceException;
import com.nayan.obai.order.repository.OrderRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest
{
	@Mock
	private OrderRespository orderRespository;

	@InjectMocks
	private OrderServiceImpl orderService;

	private Order sampleOrder;

	@BeforeEach
	public void setUp() {
		sampleOrder = Order.builder().orderId(UUID.randomUUID()).build();
		// Set other required fields on sampleOrder
	}

	@Test
	public void testPlaceOrderSuccess() {
		Mockito.when(orderRespository.save(sampleOrder)).thenReturn(sampleOrder);

		final Order result = orderService.placeOrder(sampleOrder);

		assertNotNull(result);
		assertEquals(sampleOrder.getOrderId(), result.getOrderId());
		Mockito.verify(orderRespository, Mockito.times(1)).save(sampleOrder);
	}

	@Test
	public void testGetOrderSuccess() {
		final UUID orderId = sampleOrder.getOrderId();
		Mockito.when(orderRespository.findById(orderId)).thenReturn(Optional.of(sampleOrder));

		final Order result = orderService.getOrder(orderId);

		assertNotNull(result);
		assertEquals(orderId, result.getOrderId());
		Mockito.verify(orderRespository, Mockito.times(1)).findById(orderId);
	}

	@Test
	public void testGetOrderNotFoundShouldThrowException() {
		final UUID orderId = UUID.randomUUID();
		Mockito.when(orderRespository.findById(orderId)).thenReturn(Optional.empty());

		final OrderServiceException exception = assertThrows(OrderServiceException.class, () -> {
			orderService.getOrder(orderId);
		});

		assertEquals("Order you are trying to fetch does not exist in the system!", exception.getMessage());
		Mockito.verify(orderRespository, Mockito.times(1)).findById(orderId);
	}

}