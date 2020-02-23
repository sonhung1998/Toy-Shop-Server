package com.toys.shop.Entities;

import java.util.List;

import javax.persistence.CascadeType;
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
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "manufacturer")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private final String name;

	@Column(name = "DESCRIPTION")
	private final String description;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
	private List<Product> products;

}
