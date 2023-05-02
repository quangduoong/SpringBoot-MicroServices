package com.quangduong.inventoryservice.controller;

import com.quangduong.inventoryservice.dto.InventoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.quangduong.inventoryservice.service.InventoryServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(
            @RequestParam(value = "sku-codes") List<String> skuCodes) {

        List<InventoryResponse> inventoryResponses = new ArrayList<>();

        skuCodes.forEach(skuCode -> inventoryResponses.add(InventoryResponse.builder()
                .skuCode(skuCode)
                .isInStock(inventoryService.isInStock(skuCode))
                .build()));

        return inventoryResponses;
    }

}
