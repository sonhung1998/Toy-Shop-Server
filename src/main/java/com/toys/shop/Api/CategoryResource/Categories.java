package com.toys.shop.Api.CategoryResource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toys.shop.Entities.Category;
import com.toys.shop.Repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class Categories {
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping(value = "/categories")
	public ResponseEntity<List<Category>> getAll() throws Exception {
		List<Category> categories = null;
		try {
			categories = categoryRepository.findAll();
		} catch (Exception e) {
			throw new Exception("Có lỗi xảy ra:" + e.getMessage());
		}
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
}
