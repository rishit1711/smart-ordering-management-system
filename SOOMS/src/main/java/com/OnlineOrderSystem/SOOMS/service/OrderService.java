package com.OnlineOrderSystem.SOOMS.service;

import com.OnlineOrderSystem.SOOMS.OrderStatus;
import com.OnlineOrderSystem.SOOMS.dto.*;
import com.OnlineOrderSystem.SOOMS.entity.Order;
import com.OnlineOrderSystem.SOOMS.entity.OrderItem;
import com.OnlineOrderSystem.SOOMS.entity.Product;
import com.OnlineOrderSystem.SOOMS.entity.User;
import com.OnlineOrderSystem.SOOMS.repository.OrderRepository;
import com.OnlineOrderSystem.SOOMS.repository.Productrepository;
import com.OnlineOrderSystem.SOOMS.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public Productrepository productrepository;
    @Autowired
    public UserRepository userRepository;

@Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setStatus(OrderStatus.Placed);
        order.setTotalAmount(0.0);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productrepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient Stock for :" + product.getName());
            }

            // Deduct stock
            product.setStock(product.getStock() - itemRequest.getQuantity());
            productrepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); // child → parent
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());

            double itemTotal = itemRequest.getQuantity() * product.getPrice();
            orderItem.setTotalPrice(itemTotal);

            total += itemTotal;

            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(savedOrder.getId());
        orderResponse.setUserId(savedOrder.getUserId());
        orderResponse.setTotalAmount(savedOrder.getTotalAmount());
        orderResponse.setStatus(savedOrder.getStatus());
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            OrderItemResponse res = new OrderItemResponse();
            res.setProductId(item.getProduct().getId());
            res.setProductName(item.getProduct().getName());
            res.setPrice(item.getPrice());
            res.setQuantity(item.getQuantity());
            res.setTotalPrice(item.getTotalPrice());

            orderItemResponses.add(res);
        }
        orderResponse.setItems(orderItemResponses);

        return orderResponse;
    }


    public OrderResponse getOrderById(int id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order With this ID Not found"));

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setUserId(order.getUserId());
        orderResponse.setOrderId(order.getId());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setStatus(order.getStatus());

        ArrayList<OrderItemResponse> orderItemResponseList = new ArrayList<>();

        for (OrderItem item : order.getItems()) {

            OrderItemResponse response = new OrderItemResponse();
            response.setProductId(item.getProduct().getId());
            response.setProductName(item.getProduct().getName());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            response.setTotalPrice(item.getTotalPrice());

            orderItemResponseList.add(response);
        }

        orderResponse.setItems(orderItemResponseList);

        return orderResponse;
    }

    public List<OrderResponse> GetAllUserOrders(int userid) {

        User user = userRepository.findById(userid).orElseThrow(()-> new RuntimeException("User Not Found"));
        List<Order> orders = orderRepository.findByUserId(userid); // is userid se attach jitne orders hai sab dedo
        List<OrderResponse> responseList = new ArrayList<>();
        for(Order order : orders){
            OrderResponse response = convertToResponse(order);
            responseList.add(response);
        }
        return  responseList;

    }

    private OrderResponse convertToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            OrderItemResponse response = new OrderItemResponse();
            response.setProductId(item.getProduct().getId());
            response.setProductName(item.getProduct().getName());
            response.setPrice(item.getPrice());
            response.setQuantity(item.getQuantity());
            response.setPrice(item.getPrice());
            response.setTotalPrice(item.getTotalPrice());

            orderItemResponses.add(response);
        }
        orderResponse.setItems(orderItemResponses);
        return orderResponse;


    }

    public List<AdminOrderResponse> getAllOrders() {
       List<Order> orderList = orderRepository.findAll();
       List<AdminOrderResponse> adminOrderResponseList = new ArrayList<>();
       for(Order order : orderList){
           AdminOrderResponse adminOrderResponse = new AdminOrderResponse();
           adminOrderResponse.setOrderId(order.getId());
           adminOrderResponse.setUserId(order.getUserId());
           adminOrderResponse.setStatus(order.getStatus());
           adminOrderResponse.setTotalAmount(order.getTotalAmount());
           adminOrderResponse.setCreatedAt(order.getCreatedAt());

           adminOrderResponseList.add(adminOrderResponse);
       }
       return adminOrderResponseList;



    }

    public AdminOrderDetailResponse getOrderInfo(int id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
        AdminOrderDetailResponse adminOrderDetailResponse = new AdminOrderDetailResponse();
        adminOrderDetailResponse.setOrderId(order.getId());
        adminOrderDetailResponse.setUserId(order.getUserId());
        adminOrderDetailResponse.setStatus(order.getStatus());
        adminOrderDetailResponse.setCreatedAt(order.getCreatedAt());
        adminOrderDetailResponse.setTotalAmount(order.getTotalAmount());
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(item.getProduct().getId());
            orderItemResponse.setProductName(item.getProduct().getName());
            orderItemResponse.setPrice(item.getPrice());
            orderItemResponse.setQuantity(item.getQuantity());
            orderItemResponse.setTotalPrice(item.getTotalPrice());

            itemResponses.add(orderItemResponse);

        }
        adminOrderDetailResponse.setOrderItemResponseList(itemResponses);
        return adminOrderDetailResponse;


    }


    public OrderStatusUpdateResponse UpdateOrderStatus(int id, OrderStatusUpdateRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found"));
        order.setStatus(request.getStatus());
        orderRepository.save(order);
        OrderStatusUpdateResponse statusUpdateResponse = new OrderStatusUpdateResponse();
        statusUpdateResponse.setOrderId(order.getId());
        statusUpdateResponse.setStatus(order.getStatus());
        statusUpdateResponse.setMessage("Order Status Updated SuccessFully");

        return statusUpdateResponse;

    }


    public OrderResponse CancelOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found"));
        if(order.getStatus().equals("Cancelled")){
            throw new RuntimeException("Order Cannot Be Cancelled");
        }
        order.setStatus(OrderStatus.Cancelled);
        orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setUserId(order.getUserId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());

        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(item.getProduct().getId());
            orderItemResponse.setProductName(item.getProduct().getName());
            orderItemResponse.setPrice(item.getPrice());
            orderItemResponse.setTotalPrice(item.getTotalPrice());
            orderItemResponse.setQuantity(item.getQuantity());

            itemResponses.add(orderItemResponse);
        }
        response.setItems(itemResponses);
        return response;
    }


    public List<OrderResponse> getOrders(OrderStatus status) {
        List<Order> orders = orderRepository.findByStatus(status);
        List<OrderResponse> responseList = new ArrayList<>();
        for(Order order1 : orders){
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order1.getId());
            orderResponse.setUserId(order1.getUserId());
            orderResponse.setStatus(order1.getStatus());
            orderResponse.setTotalAmount(order1.getTotalAmount());

            List<OrderItemResponse> itemResponses = new ArrayList<>();
            for(OrderItem item : order1.getItems()){
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setProductId(item.getProduct().getId());
                itemResponse.setProductName(item.getProduct().getName());
                itemResponse.setQuantity(item.getQuantity());
                itemResponse.setPrice(item.getPrice());
                itemResponse.setTotalPrice(item.getTotalPrice());
                itemResponses.add(itemResponse);
            }
            orderResponse.setItems(itemResponses);
            responseList.add(orderResponse);


        }
        return responseList;
    }


    public OrderResponse CancelByAdmin(int id) {
    Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found"));
    if(order.getStatus().equals(OrderStatus.Cancelled)){
        throw  new RuntimeException("Order Already Cancelled");
    }
    else if(order.getStatus().equals(OrderStatus.Delivered)){
        throw new RuntimeException("Once Delievered Cannot be Cancelled");

    }
    else{
        order.setStatus(OrderStatus.Cancelled);
        orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProductId(item.getProduct().getId());
            itemResponse.setProductName(item.getProduct().getName());
            itemResponse.setPrice(item.getPrice());
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setTotalPrice(item.getTotalPrice());

            orderItemResponses.add(itemResponse);

        }
        orderResponse.setItems(orderItemResponses);
        return  orderResponse;


    }

    }
}