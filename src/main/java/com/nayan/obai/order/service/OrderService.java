package com.nayan.obai.order.service;

import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.entity.OrderStatus;

import java.util.UUID;

public interface OrderService
{
	Order placeOrder(Order order);
	Order getOrder(UUID orderId);
}
