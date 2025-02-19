package com.test.store.Application.UseCases;

import com.test.store.Domain.Model.Product;
import com.test.store.Domain.Model.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public Product execute(Long productId, Product newProduct){
        return productRepository.update(productId, newProduct);
    }
}
