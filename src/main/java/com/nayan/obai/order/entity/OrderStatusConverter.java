package com.nayan.obai.order.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer>
{

	@Override
	public Integer convertToDatabaseColumn(final OrderStatus status)
	{
		return status != null ? status.getCode() : null;
	}

	@Override
	public OrderStatus convertToEntityAttribute(final Integer dbCode)
	{
		return dbCode != null ? OrderStatus.fromCode(dbCode) : null;
	}
}