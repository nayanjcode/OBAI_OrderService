package com.nayan.obai.order.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Orders")
public class Order
{
	@Id
	@Column(name = "orderId")
	@GeneratedValue
	private UUID orderId;

	@Column(name = "customerId")
	private UUID customerId;

	@Column(name = "totalAmount")
	private BigDecimal totalAmount;

	@ElementCollection
	@CollectionTable(
			name = "OrderProduct",
			joinColumns = @JoinColumn(name = "orderId")
	)
	private List<Product> products;

	@Column(name = "orderStatus")
	@Convert(converter = OrderStatusConverter.class)
	private OrderStatus orderStatus;
}
