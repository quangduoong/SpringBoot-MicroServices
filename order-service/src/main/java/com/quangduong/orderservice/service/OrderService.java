package com.quangduong.orderservice.service;

import com.quangduong.orderservice.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
