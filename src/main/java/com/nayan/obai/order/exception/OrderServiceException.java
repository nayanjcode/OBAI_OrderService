package com.nayan.obai.order.exception;

public class OrderServiceException extends RuntimeException
{
	public OrderServiceException() {
		super("Resource not found");
	}

	public OrderServiceException(String message) {
		super(message);
	}
}
