package com.test.store.Infrastructure.Mapper;

import com.test.store.Domain.Model.Product;
import com.test.store.Infrastructure.DTO.ProductDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice()
        );
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(dto -> ProductMapper.toDTO(dto))// lambda explícito
                .collect(Collectors.toList());
    }

    public static List<Product> toEntityList(List<ProductDTO> productDTOs) {
        if (productDTOs == null) {
            return Collections.emptyList();
        }
        return productDTOs.stream()
                .map(ProductMapper::toEntity)// lambda implícito con referencia a method
                .collect(Collectors.toList());
    }
}
