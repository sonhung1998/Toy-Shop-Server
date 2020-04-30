package com.toys.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toys.shop.Entities.OrderDetail.OrderDetail;
import com.toys.shop.Entities.OrderDetail.OrderDetailKey;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
	@Modifying
	@Transactional
	@Query(value = "delete from toys_shop.order_detail where order_detail.ORDER_ID=?1", nativeQuery = true)
	void deleteAllByOrder(int orderId);
}
