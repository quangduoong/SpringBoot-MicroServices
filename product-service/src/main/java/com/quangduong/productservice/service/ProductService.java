package com.quangduong.productservice.service;

import com.quangduong.productservice.dto.ProductRequest;
import com.quangduong.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest dto);
    List<ProductResponse> findProducts(int page, int size);
}
