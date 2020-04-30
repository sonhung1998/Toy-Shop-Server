package com.toys.shop.Entities.OrderDetail;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class OrderDetailKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "PRODUCT_ID")
	private Integer productId;

	public OrderDetailKey(Integer orderId, Integer productId) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}
	
	

}
