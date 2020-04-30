package com.toys.shop.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.shop.Entities.OrderDetail.OrderDetail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IMAGE")
	private String image;

	@Column(name = "LENGTH")
	private float length;

	@Column(name = "WIDTH")
	private float width;

	@Column(name = "HEIGHT")
	private float height;

	@Column(name = "PRICE")
	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Manufacturer manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Category category;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	List<OrderDetail> orderDetails;
	
	Integer amount;
	
	public Product(String name, String description, String image, float length, float width, float height, int price,
			Manufacturer manufacturer, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.length = length;
		this.width = width;
		this.height = height;
		this.price = price;
		this.manufacturer = manufacturer;
		this.category = category;
	}

}
