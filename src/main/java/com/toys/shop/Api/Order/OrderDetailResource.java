package com.toys.shop.Api.Order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Api.AbstractRestController;
import com.toys.shop.Entities.OrderDetail.OrderDetail;
import com.toys.shop.Repository.OrderDetailRepository;

@RestController
public class OrderDetailResource extends AbstractRestController{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@GetMapping("/order/detail/{orderId}")
	public ResponseEntity<List<OrderDetail>> getOrderDetail(
			@PathVariable("orderId") Integer id) throws Exception 
	{
		List<OrderDetail> orders = orderDetailRepository.findAll();
		 				orders = orders.stream()
								 .filter(item -> item.getOrder().getId()==id)
								 .collect(Collectors.toList());
		return new ResponseEntity<List<OrderDetail>>(orders, HttpStatus.OK);

	}
}
