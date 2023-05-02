package com.quangduong.orderservice.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.quangduong.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quangduong.orderservice.dto.OrderRequest;
import com.quangduong.orderservice.mapper.OrderLineItemMapper;
import com.quangduong.orderservice.model.Order;
import com.quangduong.orderservice.model.OrderLineItem;
import com.quangduong.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderLineItemMapper mapper;
    private final OrderRepository repository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        String inventoryServiceUrl = "http://localhost:8082/api/v1/inventory";
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(this.mapper::toOrderLineItem).toList();
        List<String> skuCodes = orderLineItems.stream().map(OrderLineItem::getSkuCode).toList();
        List<InventoryResponse> inventoryResponseList;
        Order order = new Order();
        boolean result;

        log.info("Placing order...");
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItems(orderLineItems);
        // Call Inventory Service to check stock quantity
        inventoryResponseList = List.of(
                Objects.requireNonNull(this.webClient.get()
                        .uri(inventoryServiceUrl, uriBuilder -> uriBuilder.queryParam("sku-codes", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block()));
        // Check if any item is out of stock
        result = inventoryResponseList.stream().allMatch(InventoryResponse::getIsInStock);
        if (!result) throw new IllegalArgumentException("Product is not in stock.");
        // Save order
        this.repository.save(order);
        log.info("Saved order.");
    }
}
