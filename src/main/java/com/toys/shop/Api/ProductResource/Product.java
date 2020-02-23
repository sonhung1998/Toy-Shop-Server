package com.toys.shop.Api.ProductResource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Repository.CategoryRepository;
import com.toys.shop.Repository.ManufacturerRepository;
import com.toys.shop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class Product {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
 
	@GetMapping("/product/{productId}")
	public ResponseEntity<com.toys.shop.Entities.Product> getProduct(@PathVariable(name = "productId") Integer id)
			throws Exception {
		Optional<com.toys.shop.Entities.Product> productOptial = productRepository.findById(id);

		if (productOptial.isPresent() == false) {
			throw new Exception("Không tìm thấy sản phẩm với id =" + id);
		}
		com.toys.shop.Entities.Product product = productOptial.get();

		return new ResponseEntity<com.toys.shop.Entities.Product>(product, HttpStatus.OK);

	}

	@PostMapping("/product/{productId}")
	public ResponseEntity<com.toys.shop.Entities.Product> updateProduct(
			@PathVariable(name = "productId") Integer id,
			@RequestBody com.toys.shop.Entities.Product productUpdate) throws Exception {
		Optional<com.toys.shop.Entities.Product> productOptial = productRepository.findById(id);
		if (productOptial.isPresent()) {
			throw new Exception("Sản phẩm này đã tồn tại");
		}
		log.info("Category ID is:"+productUpdate.getCategory().getId());
		com.toys.shop.Entities.Product productResponse = productRepository.save(productUpdate);
		
		if (productResponse == null) {
			throw new Exception("Cập nhật sản phẩm không thành công !");
		}
		
		return new ResponseEntity<com.toys.shop.Entities.Product>(productResponse, HttpStatus.OK);
	}

}
