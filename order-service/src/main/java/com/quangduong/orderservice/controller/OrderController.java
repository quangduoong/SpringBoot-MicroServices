package com.quangduong.orderservice.controller;

import com.quangduong.orderservice.dto.OrderRequest;
import com.quangduong.orderservice.service.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryServiceFallbackMethod")
    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody @Valid OrderRequest orderRequest, HttpServletResponse response) {
        response.setStatus(201);
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> inventoryServiceFallbackMethod(
            OrderRequest orderRequest,
            HttpServletResponse response,
            TimeoutException ioException
    ) {
        response.setStatus(500);
        return CompletableFuture.supplyAsync(() -> "Cannot reach Inventory Service right now.");
//        throw new InternalError("Cannot reach Inventory Service right now.");
    }
}
