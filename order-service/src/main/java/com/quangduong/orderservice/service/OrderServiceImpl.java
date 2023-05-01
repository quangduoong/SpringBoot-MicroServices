package com.quangduong.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quangduong.orderservice.dto.OrderRequest;
import com.quangduong.orderservice.mapper.OrderLineItemMapper;
import com.quangduong.orderservice.model.Order;
import com.quangduong.orderservice.model.OrderLineItem;
import com.quangduong.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderLineItemMapper mapper;
    private final OrderRepository repository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderLineItem> orderLineItems;

        log.info("Placing order...");
        order.setOrderNumber(UUID.randomUUID().toString());
        orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(mapper::toOrderLineItem).toList();
        order.setOrderLineItems(orderLineItems);
        repository.save(order);
        log.info("Saved order.");
    }
}
