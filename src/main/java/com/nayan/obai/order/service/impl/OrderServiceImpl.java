package com.nayan.obai.order.service.impl;

import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.exception.OrderServiceException;
import com.nayan.obai.order.repository.OrderRespository;
import com.nayan.obai.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired
	private OrderRespository orderRespository;

	public OrderServiceImpl(final OrderRespository orderRespository)
	{
		this.orderRespository = orderRespository;
	}

	@Override
	public Order placeOrder(final Order order)
	{
		return orderRespository.save(order);
	}


	@Override
	public Order getOrder(final UUID orderId)
	{
		return orderRespository.findById(orderId).orElseThrow(() -> new OrderServiceException("Order you are trying to fetch does not exist in the system!"));
	}

	@Override
	public List<Order> getAllOrders()
	{
		return orderRespository.findAll();
	}
}
