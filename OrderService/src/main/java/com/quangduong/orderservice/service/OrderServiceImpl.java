package com.quangduong.orderservice.service;

import com.quangduong.orderservice.dto.OrderRequest;
import com.quangduong.orderservice.mapper.OrderLineItemMapper;
import com.quangduong.orderservice.model.Order;
import com.quangduong.orderservice.model.OrderLineItem;
import com.quangduong.orderservice.repository.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderLineItemMapper mapper;
    private final OrderRepository repository;

    public void placeOrder(@NonNull OrderRequest orderRequest) {
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
