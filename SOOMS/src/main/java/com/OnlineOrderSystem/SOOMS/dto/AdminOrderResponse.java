package com.OnlineOrderSystem.SOOMS.dto;


import com.OnlineOrderSystem.SOOMS.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class AdminOrderResponse {
    private int orderId;
    private int userId;
    private double totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    //private List<OrderItemResponse> items;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    public AdminOrderResponse() {
    }
}
