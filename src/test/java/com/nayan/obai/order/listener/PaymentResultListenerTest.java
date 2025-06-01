package com.nayan.obai.order.listener;

import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.entity.OrderStatus;
import com.nayan.obai.order.event.PaymentResultEvent;
import com.nayan.obai.order.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PaymentResultListenerTest
{
	@Mock
	private OrderService orderService;

	@InjectMocks
	private PaymentResultListener paymentResultListener;


	@Test
	public void testHandlePaymentSuccess_SuccessfulPayment() {
		final UUID orderId = UUID.randomUUID();
		final PaymentResultEvent event = Mockito.mock(PaymentResultEvent.class);
		Mockito.when(event.getOrderId()).thenReturn(orderId);
		Mockito.when(event.isSuccessful()).thenReturn(true);

		final Order order = Order.builder().orderId(orderId).build();
		Mockito.when(orderService.getOrder(orderId)).thenReturn(order);

		paymentResultListener.handlePaymentSuccess(event);

		Assertions.assertEquals(OrderStatus.SUCCESS, order.getOrderStatus());
		Mockito.verify(orderService).placeOrder(order);
	}

	@Test
	public void testHandlePaymentSuccess_FailedPayment() {
		final UUID orderId = UUID.randomUUID();
		final PaymentResultEvent event = Mockito.mock(PaymentResultEvent.class);
		Mockito.when(event.getOrderId()).thenReturn(orderId);
		Mockito.when(event.isSuccessful()).thenReturn(false);

		final Order order = Order.builder().orderId(orderId).build();
		Mockito.when(orderService.getOrder(orderId)).thenReturn(order);

		paymentResultListener.handlePaymentSuccess(event);

		Assertions.assertEquals(OrderStatus.FAILED, order.getOrderStatus());
		Mockito.verify(orderService).placeOrder(order);
	}

}