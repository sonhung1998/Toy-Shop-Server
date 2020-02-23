package com.toys.shop.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "product")
@RequiredArgsConstructor
@NoArgsConstructor(force = true)

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private final String name;

	@Column(name = "DESCRIPTION")
	private final String description;

	@Column(name = "IMAGE")
	private final String image;

	@Column(name = "LENGTH")
	private final float length;

	@Column(name = "WIDTH")
	private final float width;

	@Column(name = "HEIGHT")
	private final float height;

	@Column(name = "PRICE")
	private final int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private final Manufacturer manufacturer;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private final Category category;
	

}
