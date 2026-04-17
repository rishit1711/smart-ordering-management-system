package com.OnlineOrderSystem.SOOMS.repository;

import com.OnlineOrderSystem.SOOMS.OrderStatus;
import com.OnlineOrderSystem.SOOMS.dto.OrderResponse;
import com.OnlineOrderSystem.SOOMS.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(int userid);
    List<Order> findByStatus(OrderStatus status);
}
