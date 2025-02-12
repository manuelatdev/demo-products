package com.test.store.Infrastructure.Mapper;


import com.test.store.Domain.Model.ProductOrder;
import com.test.store.Infrastructure.DTO.ProductOrderDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductOrderMapper {

    public static ProductOrderDTO toDTO(ProductOrder productOrder) {
        if (productOrder == null) {
            return null;
        }

        return new ProductOrderDTO(
                productOrder.getProductOrderId(),
                productOrder.getProductId(),
                productOrder.getQuantity(),
                productOrder.getTotalPrice()
        );
    }

    public static ProductOrder toEntity(ProductOrderDTO productOrderDTO) {
        if (productOrderDTO == null) {
            return null;
        }

        return new ProductOrder(
                productOrderDTO.getOrderId(),  // Mapea orderId a productOrderId
                productOrderDTO.getProductId(),
                productOrderDTO.getQuantity(),
                productOrderDTO.getTotalPrice()
        );
    }

    public static List<ProductOrderDTO> toDTOList(List<ProductOrder> productOrders) {
        if (productOrders == null) {
            return Collections.emptyList();
        }
        return productOrders.stream()
                .map(ProductOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<ProductOrder> toEntityList(List<ProductOrderDTO> productOrderDTOs) {
        if (productOrderDTOs == null) {
            return Collections.emptyList();
        }
        return productOrderDTOs.stream()
                .map(ProductOrderMapper::toEntity)
                .collect(Collectors.toList());
    }
}

