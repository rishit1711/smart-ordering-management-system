package com.OnlineOrderSystem.SOOMS.dto;



import java.util.List;

public class OrderRequest {
    private int userId;
    private List<OrderItemRequest> items;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public OrderRequest(int userId, List<OrderItemRequest> items) {
        this.userId = userId;
        this.items = items;
    }

    public OrderRequest() {
    }
}
