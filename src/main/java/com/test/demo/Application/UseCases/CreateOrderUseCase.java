package com.test.demo.Application.UseCases;

import com.test.demo.Domain.Model.Product;
import com.test.demo.Domain.Model.ProductOrder;
import com.test.demo.Domain.Model.ProductOrderRepository;
import com.test.demo.Domain.Model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class CreateOrderUseCase {

    private final ProductOrderRepository productOrderRepository;

    private final ProductRepository productRepository;

    public ProductOrder execute(Long productId, Integer quantity) {
        Product product = productRepository.getProductById(productId);

        Objects.requireNonNull(product, "El producto con ID " + productId + " no fue encontrado");
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }

        ProductOrder productOrder = new ProductOrder(
                null,
                productId,
                quantity,
                product.getPrice() * quantity);

        return productOrderRepository.create(productOrder);

    }
}
