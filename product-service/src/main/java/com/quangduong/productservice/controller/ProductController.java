package com.quangduong.productservice.controller;

import com.quangduong.productservice.dto.ProductRequest;
import com.quangduong.productservice.dto.ProductResponse;
import com.quangduong.productservice.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest dto) {
        productService.createProduct(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findProducts(
            @RequestParam int page, @RequestParam int size, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "quangduong");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        return productService.findProducts(page, size);
    }

    @GetMapping("/cookie-test")
    public String cookieTest (@CookieValue(value = "username") String username){
        return "Hello " + username;
    }
}
