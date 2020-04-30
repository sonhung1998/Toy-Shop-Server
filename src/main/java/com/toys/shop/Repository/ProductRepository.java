package com.toys.shop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select * from product where CATEGORY_ID= ?1 ", nativeQuery = true)
	List<Product> finAllByCategory(int categoryId);

	@Query(value = "select * from product where MANUFACTURER_ID= ?1 ", nativeQuery = true)
	List<Product> finAllByManufacturer(int manufacturerId);

	@Query(value = "select * from product where CATEGORY_ID= ?1 AND MANUFACTURER_ID= ?2", nativeQuery = true)
	List<Product> finAllByManufacturerAndCategory(int categoryId, int manufacturerId);
}
