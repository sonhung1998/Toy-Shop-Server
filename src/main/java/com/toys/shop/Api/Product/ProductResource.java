package com.toys.shop.Api.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.toys.shop.Entities.Product;
import com.toys.shop.Repository.CategoryRepository;
import com.toys.shop.Repository.ManufacturerRepository;
import com.toys.shop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductResource {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
 
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(
			@PathVariable(name = "productId") Integer id)
			throws Exception 
	{
		Optional<Product> productOptial = productRepository.findById(id);

		if (productOptial.isPresent() == false) {
			throw new Exception("Không tìm thấy sản phẩm với id =" + id);
		}
		Product product = productOptial.get();

		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@PostMapping("/product")
	public ResponseEntity<Product> updateProduct(
			@RequestBody Product productCreate) throws Exception {
		Product productResponse = productRepository.save(productCreate);
		if (productResponse == null) {
			throw new Exception("Cập nhật sản phẩm không thành công !");
		}
		return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<String>deleteProduct(
			@PathVariable(name = "productId") Integer id) throws Exception
	{
		Optional<Product> productOptial = productRepository.findById(id);
		if (productOptial.isPresent()==false) {
			throw new Exception("Sản phẩm không tồn tại !");
		}
		try {
			productRepository.delete(productOptial.get());
		} catch (Exception e) {
			throw new Exception("Lỗi khi xóa sản phẩm:"+e.getMessage());
		}
		
		return new ResponseEntity<String>("Xóa sản phẩm thành công",HttpStatus.OK);
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable(name = "productId",required=true) Integer id,
			@RequestBody Product productUpdate) throws Exception {
		Optional<Product> productOptial = productRepository.findById(id);
		if (productOptial.isPresent()==false) {
			throw new Exception("Sản phẩm này không tồn tại");
		}
		Product productResponse = productRepository.save(productUpdate);
		
		if (productResponse == null) {
			throw new Exception("Cập nhật sản phẩm không thành công !");
		}
		
		return new ResponseEntity<Product>(productResponse, HttpStatus.OK);
	}
	
	@PostMapping(value = "/product/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadImage(
			@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException 
	{
		String filePath="E:\\Toy-Shop\\Public\\Images\\";
		File convertFile = new File(filePath + fileUpload.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fileOutput = new FileOutputStream(convertFile);
		fileOutput.write(fileUpload.getBytes());
		fileOutput.close();
		return new ResponseEntity<Object>("Upload successully !", HttpStatus.OK);
	}

}
