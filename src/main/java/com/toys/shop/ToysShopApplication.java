package com.toys.shop;

import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.toys.shop.Entities.Order;
import com.toys.shop.Entities.OrderDetail.OrderDetail;
import com.toys.shop.Entities.OrderDetail.OrderDetailKey;
import com.toys.shop.Repository.OrderDetailRepository;
import com.toys.shop.Repository.OrderRespository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan({ "com.toys.shop.*" })
@SpringBootApplication
public class ToysShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ToysShopApplication.class, args);
		OrderRespository orderRepository = context.getBean(OrderRespository.class);
//		OrderDetailRepository orderDetailRepository = context.getBean(OrderDetailRepository.class);
//		OrderDetailKey orderDetailKey = null;
//		List<Order> listOrder = orderRepository.findAll();
//		for (Order order : listOrder) {
//			orderDetailKey = new OrderDetailKey(order.getId(), 39);
//			orderDetailRepository.save(new OrderDetail(orderDetailKey, new Random().nextInt(10) + 1));
//		}
//
	}

}
