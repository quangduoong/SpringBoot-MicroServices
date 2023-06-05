package com.quangduong.inventoryservice.controller;

import com.quangduong.inventoryservice.dto.InventoryResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.quangduong.inventoryservice.service.InventoryServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @SneakyThrows
    public List<InventoryResponse> isInStock(
            @RequestParam(value = "sku-codes") List<String> skuCodes) {
        log.info("Wait for 10s.");
        Thread.sleep(10000);
        log.info("Wait ended.");

        List<InventoryResponse> inventoryResponses = new ArrayList<>();

        skuCodes.forEach(skuCode -> inventoryResponses.add(InventoryResponse.builder()
                .skuCode(skuCode)
                .isInStock(inventoryService.isInStock(skuCode))
                .build()));

        return inventoryResponses;
    }

}
