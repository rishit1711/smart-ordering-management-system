package com.OnlineOrderSystem.SOOMS.dto;

import com.OnlineOrderSystem.SOOMS.OrderStatus;
import com.OnlineOrderSystem.SOOMS.entity.OrderItem;

import java.util.List;

public class OrderResponse {
    private int OrderId;
    private int userId;
    private double totalAmount;
    private OrderStatus status;
    private List<OrderItemResponse> items;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
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

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }

//    public OrderResponse(int orderId, int userId, double totalAmount, String status, List<OrderItemResponse> items) {
//        OrderId = orderId;
//        this.userId = userId;
//        this.totalAmount = totalAmount;
//        this.status = status;
//        this.items = items;
//    }

    public OrderResponse() {
    }
}

