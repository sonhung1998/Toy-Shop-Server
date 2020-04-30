package com.toys.shop.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DISTRICT")
	private String district;
	
	@Column(name = "CITY")
	private String city;
	
	@OneToMany(mappedBy="address")
	@JsonIgnore
	private List<Customer>customers;
	
	public Address(String district, String city) {
		super();
		this.district = district;
		this.city = city;
	}
}
