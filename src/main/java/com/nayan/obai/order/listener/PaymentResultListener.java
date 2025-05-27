package com.nayan.obai.order.listener;

import com.nayan.obai.order.config.RabbitConfig;
import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.entity.OrderStatus;
import com.nayan.obai.order.event.PaymentResultEvent;
import com.nayan.obai.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultListener
{
	OrderService orderService;

	public PaymentResultListener(OrderService orderService) {
		this.orderService = orderService;
	}

	@RabbitListener(queues = RabbitConfig.PAYMENT_RESULT_QUEUE)
	public void handlePaymentSuccess(final PaymentResultEvent event) {
		Order order = orderService.getOrder(event.getOrderId());
		if(event.isSuccessful())
		{
			order.setOrderStatus(OrderStatus.SUCCESS);
		} else {
			order.setOrderStatus(OrderStatus.FAILED);
		}
		orderService.placeOrder(order);
	}
}
