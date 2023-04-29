package com.quangduong.orderservice.controller;

import com.quangduong.orderservice.dto.OrderRequest;
import com.quangduong.orderservice.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest){
       orderService.placeOrder(orderRequest);
       log.info("Placed order.");
    }
}
