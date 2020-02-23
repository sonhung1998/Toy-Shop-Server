package com.toys.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
