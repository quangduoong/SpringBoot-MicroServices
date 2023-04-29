package com.quangduong.productservice.repository;

import com.quangduong.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

public interface ProductRepository extends
        MongoRepository<Product, String>,
        PagingAndSortingRepository<Product, String> {
    Page<Product> findAllByPrice(BigDecimal price, Pageable pageable);
}
