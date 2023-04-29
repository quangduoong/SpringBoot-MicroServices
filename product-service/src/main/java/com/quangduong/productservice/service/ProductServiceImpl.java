package com.quangduong.productservice.service;

import com.quangduong.productservice.dto.ProductRequest;
import com.quangduong.productservice.dto.ProductResponse;
import com.quangduong.productservice.model.Product;
import com.quangduong.productservice.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createProduct(@NonNull ProductRequest dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is save.", product.getId());
    }

    @Override
    public List<ProductResponse> findProducts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        List<ProductResponse> productResponseList = new ArrayList<>();

        productPage.forEach(product -> productResponseList
                .add(modelMapper.map(product, ProductResponse.class)));

        return productResponseList;
    }
}
