package com.OnlineOrderSystem.SOOMS.Controller;

import com.OnlineOrderSystem.SOOMS.OrderStatus;
import com.OnlineOrderSystem.SOOMS.dto.*;
import com.OnlineOrderSystem.SOOMS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> Place(@RequestBody OrderRequest orderRequest) {
        OrderResponse order = orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

    @GetMapping("Order/{id}")
    public ResponseEntity<OrderResponse> GetOrder(@PathVariable int id) {
        OrderResponse order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);

    }

    @GetMapping("/users/{userid}/orders")
    public ResponseEntity<List<OrderResponse>> UserOrder(@PathVariable int userid) {
        List<OrderResponse> list = orderService.GetAllUserOrders(userid);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/admin/orders")
    public List<AdminOrderResponse> getOrders() {
        return orderService.getAllOrders();

    }

    @GetMapping("/admin/orders/{id}")
    public AdminOrderDetailResponse getOrderById(@PathVariable int id) {
        return orderService.getOrderInfo(id);
    }

    @PutMapping("/admin/orders/{id}/status")
    public ResponseEntity<OrderStatusUpdateResponse> orderStatus(@PathVariable int id, @RequestBody OrderStatusUpdateRequest request) {
        OrderStatusUpdateResponse response = orderService.UpdateOrderStatus(id, request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelorder(@PathVariable int id) {
        OrderResponse orderResponse = orderService.CancelOrder(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam OrderStatus status) {
        List<OrderResponse> orderResponseList = orderService.getOrders(status);
        return ResponseEntity.ok(orderResponseList);
    }
    @PutMapping("/admin/orders/{id}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable int id){
        OrderResponse orderResponse = orderService.CancelByAdmin(id);
        return ResponseEntity.ok(orderResponse);
    }
}






