package com.nayan.obai.order.controller;

import com.nayan.obai.order.entity.Order;
import com.nayan.obai.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController
{
	@Autowired
	OrderService orderService;

	@PostMapping("/")
	ResponseEntity<Order> placeOrder(@RequestBody Order order)
	{
		final Order createdOrder = orderService.placeOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@GetMapping("/{orderId}")
	ResponseEntity<Order> getOrder(@PathVariable UUID orderId)
	{
		final Order order = orderService.getOrder(orderId);
		return ResponseEntity.ok(order);
	}
}
