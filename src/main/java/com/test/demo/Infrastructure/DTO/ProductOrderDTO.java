package com.test.demo.Infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductOrderDTO {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
}
