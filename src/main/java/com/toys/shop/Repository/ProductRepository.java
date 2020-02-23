package com.toys.shop.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
