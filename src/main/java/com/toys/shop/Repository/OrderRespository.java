package com.toys.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toys.shop.Entities.Order;
@Repository
public interface OrderRespository extends JpaRepository<Order, Integer>{

}
