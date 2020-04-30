package com.toys.shop.Entities.OrderDetail;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.shop.Entities.Order;
import com.toys.shop.Entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("PRODUCT_ID")
	@JoinColumn(name = "PRODUCT_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("ORDER_ID")
	@JoinColumn(name = "ORDER_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	Order order;

	@Column(name = "QUANTITY")
	private Integer quantity;


	public OrderDetail(OrderDetailKey id, Integer quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	
	

}
