package com.toys.shop.Api.Order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Api.AbstractRestController;
import com.toys.shop.Entities.Order;
import com.toys.shop.Entities.OrderDetail.OrderDetail;
import com.toys.shop.Repository.OrderDetailRepository;
import com.toys.shop.Repository.OrderRespository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrdersResource extends AbstractRestController {
	@Autowired
	OrderRespository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@GetMapping("/orders/detail")
	public ResponseEntity<Object> getAllOrderDetail() throws Exception {
		List<OrderDetail> orderDetailList = null;
		
		try {
			orderDetailList = orderDetailRepository.findAll();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return new ResponseEntity<Object>(orderDetailList, HttpStatus.OK);

	}
	
	@GetMapping("/orders")
	public ResponseEntity<Object> getAllOrder() throws Exception {
		List<Order> orderList = null;
		
		try {
			orderList = orderRepository.findAll();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return new ResponseEntity<Object>(orderList, HttpStatus.OK);

	}
}
