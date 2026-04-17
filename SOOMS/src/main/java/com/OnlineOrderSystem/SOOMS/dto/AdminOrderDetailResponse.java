package com.OnlineOrderSystem.SOOMS.dto;

import com.OnlineOrderSystem.SOOMS.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class AdminOrderDetailResponse {
    private int orderId;
    private int userId;
    private OrderStatus Status;
    private double TotalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItemResponseList;

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

    public OrderStatus getStatus() {
        return Status;
    }

    public void setStatus(OrderStatus status) {
        Status = status;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemResponse> getOrderItemResponseList() {
        return orderItemResponseList;
    }

    public void setOrderItemResponseList(List<OrderItemResponse> orderItemResponseList) {
        this.orderItemResponseList = orderItemResponseList;
    }

    public AdminOrderDetailResponse() {
    }
}
