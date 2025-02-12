package com.test.store.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    private Long productOrderId;

    private Long productId;

    private Integer quantity;

    private Double totalPrice;
}
