package com.quangduong.productservice;

import com.github.javafaker.Faker;
import com.quangduong.productservice.model.Product;
import com.quangduong.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ProductServiceApplication {

    private final Faker faker;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        log.info("Application is running...");
    }

//    @Bean
    CommandLineRunner commandLineRunner() {
        log.info("Product data seeding is started...");
        return args -> {
            List<Product> productList = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                productList.add(
                        Product.builder()
                                .name(faker.name().firstName() + " " + faker.name().lastName())
                                .description(faker.lorem().toString())
                                .price(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
                                .build()
                );
            }

            productRepository.saveAll(productList);

            log.info("Seeding complete.");
        };
    }

}
