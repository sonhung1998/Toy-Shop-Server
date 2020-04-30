package com.toys.shop.Api.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Api.AbstractRestController;
import com.toys.shop.Entities.Product;
import com.toys.shop.Repository.CategoryRepository;
import com.toys.shop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductsResource extends AbstractRestController{
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAll(
			@RequestParam(value = "categoryId", required = true, defaultValue = "0") int categoryId,
			@RequestParam(value = "manufacturerId", required = true, defaultValue = "0") int manufacturerId)
			throws Exception {
		log.info("categoryId:"+categoryId+" manufacturerId:"+manufacturerId);
		List<Product> products = null;
		try {
			
			if (categoryId == 0 && manufacturerId == 0) {
				
				products = productRepository.findAll();
			}
			if (categoryId != 0 && manufacturerId == 0) {
				products = productRepository.finAllByCategory(categoryId);

			}
			if (categoryId == 0 && manufacturerId != 0) {
				products = productRepository.finAllByManufacturer(manufacturerId);
			}
			if (categoryId != 0 && manufacturerId != 0) {
				products = productRepository.finAllByManufacturerAndCategory(categoryId, manufacturerId);
			}

		} catch (Exception e) {
			throw new Exception("Có lỗi xảy ra:" + e.getMessage());
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
