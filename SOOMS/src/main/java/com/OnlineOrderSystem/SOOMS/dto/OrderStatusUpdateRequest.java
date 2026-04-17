package com.OnlineOrderSystem.SOOMS.dto;

import com.OnlineOrderSystem.SOOMS.OrderStatus;

public class OrderStatusUpdateRequest {
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
