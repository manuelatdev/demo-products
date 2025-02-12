package com.test.demo.Infrastructure.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id")
    private Long productOrderId;

    private Long productId;

    private Integer quantity;

    private Double totalPrice;
}
