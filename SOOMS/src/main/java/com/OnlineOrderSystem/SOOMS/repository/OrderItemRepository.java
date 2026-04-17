package com.OnlineOrderSystem.SOOMS.repository;

import com.OnlineOrderSystem.SOOMS.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
