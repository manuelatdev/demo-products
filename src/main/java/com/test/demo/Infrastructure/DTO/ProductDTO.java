package com.test.demo.Infrastructure.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;
}
