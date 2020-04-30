package com.toys.shop.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.shop.Entities.Enum.OrderType;
import com.toys.shop.Entities.OrderDetail.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "`order`")
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DATE_ORDER")
	@CreationTimestamp
	private Date dateOrder;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private OrderType status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	Customer customer;

	@OneToMany(mappedBy = "order",
			   cascade = CascadeType.ALL,
			   orphanRemoval = true,
			   fetch=FetchType.LAZY)
	@JsonIgnore
	List<OrderDetail> orderDetails;

	public Order(OrderType status, Customer customer) {
		super();
		this.status = status;
		this.customer = customer;
	}

}
