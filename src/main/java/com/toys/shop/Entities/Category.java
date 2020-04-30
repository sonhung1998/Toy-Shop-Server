package com.toys.shop.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NAME")
	private final String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;

	@OneToMany(mappedBy = "parentCategory",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonIgnore
	private List<Category> childCategory;

	@ManyToOne
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	private Category parentCategory;

}
