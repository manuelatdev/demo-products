package com.test.store.Application.UseCases;

import com.test.store.Domain.Model.Product;
import com.test.store.Domain.Model.ProductOrder;
import com.test.store.Domain.Model.ProductOrderRepository;
import com.test.store.Domain.Model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UpdateOrderUseCase {
    private final ProductOrderRepository productOrderRepository;

    private final ProductRepository productRepository;

    public ProductOrder execute(ProductOrder productOrder) {
        Product product = productRepository.getProductById(productOrder.getProductId());
        ProductOrder oldProductOrder = productOrderRepository.getProductOrderById(productOrder.getProductOrderId());

        Objects.requireNonNull(product, "El producto con ID " + productOrder.getProductId() + " no fue encontrado");
        if (productOrder.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }

        oldProductOrder.setTotalPrice(productOrder.getQuantity() * product.getPrice());

        return oldProductOrder;

    }
}
