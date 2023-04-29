package com.quangduong.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t-order_line_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
