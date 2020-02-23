package com.toys.shop.Api.ProductResource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Entities.Product;
import com.toys.shop.Repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class Products {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAll() throws Exception {
		List<Product> products = null;
		try {
			products = productRepository.findAll();
		} catch (Exception e) {
			throw new Exception("Có lỗi xảy ra:" + e.getMessage());
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
