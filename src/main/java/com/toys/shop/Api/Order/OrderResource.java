package com.toys.shop.Api.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Api.AbstractRestController;
import com.toys.shop.Entities.Customer;
import com.toys.shop.Entities.GeneralData;
import com.toys.shop.Entities.Order;
import com.toys.shop.Entities.Product;
import com.toys.shop.Entities.Enum.OrderType;
import com.toys.shop.Entities.OrderDetail.OrderDetail;
import com.toys.shop.Entities.OrderDetail.OrderDetailKey;
import com.toys.shop.Repository.OrderDetailRepository;
import com.toys.shop.Repository.OrderRespository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderResource extends AbstractRestController {
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderRespository orderRepository;

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable("orderId") Integer id) throws Exception {
		List<Order> order = orderRepository.findAll();
		Order orderToFind = order.stream().filter(item -> item.getId() == id).findAny().orElse(null);
		return new ResponseEntity<Order>(orderToFind, HttpStatus.OK);

	}

	@PostMapping("/order")
	public ResponseEntity<Order> orderProducts(@RequestBody GeneralData<Customer, List<Product>> data)
			throws Exception {
		Order order = null;
		OrderDetailKey orderDetailKey = null;
		if (!data.getSecondKey().isEmpty()) {
			order = orderRepository.save(new Order(OrderType.PENDING, data.getFirstKey()));
			for (Product product : data.getSecondKey()) {
				orderDetailKey = new OrderDetailKey(order.getId(), product.getId());
				orderDetailRepository.save(new OrderDetail(orderDetailKey, product.getAmount()));
			}
		}
		return new ResponseEntity<Order>(order, HttpStatus.OK);

	}

	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody GeneralData<Order, OrderDetail> data) {

		return null;
	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer id) throws Exception {
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (!orderOptional.isPresent()) {
			throw new Exception("Đơn hàng không tồn tại !");
		}
		Order orderDelete = orderOptional.get();
		try {
			orderDetailRepository.deleteAllByOrder(orderDelete.getId());
			orderRepository.delete(orderDelete);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return new ResponseEntity<String>("Xóa đơn hàng thành công !", HttpStatus.OK);
	}

}
