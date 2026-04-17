package com.OnlineOrderSystem.SOOMS.dto;

import com.OnlineOrderSystem.SOOMS.OrderStatus;

public class OrderStatusUpdateResponse {
    private int orderId;
    private OrderStatus status;
    private String message;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderStatusUpdateResponse() {
    }
}
