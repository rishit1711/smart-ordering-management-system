package com.OnlineOrderSystem.SOOMS.dto;

import com.OnlineOrderSystem.SOOMS.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class product_request {
    @NotBlank
    private String name;
    @NotNull
    private double price;
    @NotNull
    @Min(0)
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public product_request(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public product_request() {
    }
}
