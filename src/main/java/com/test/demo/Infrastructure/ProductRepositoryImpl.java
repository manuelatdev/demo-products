package com.test.demo.Infrastructure;

import com.test.demo.Domain.Model.Product;
import com.test.demo.Domain.Model.ProductRepository;
import com.test.demo.Infrastructure.Entities.ProductEntity;
import com.test.demo.Infrastructure.Repositories.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component // ??
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;

    @Transactional
    @Override
    public Product create(Product product) {
        ProductEntity productEntityDb = productJPARepository.save(
                new ProductEntity(product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()));

        return new Product(  // ¿Es mejor hacer un mapper?
                productEntityDb.getProductId(),
                productEntityDb.getName(),
                productEntityDb.getDescription(),
                productEntityDb.getPrice()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> listProducts() {
        return productJPARepository.findAll().stream().map(
                productEntity -> new Product(
                        productEntity.getProductId(),
                        productEntity.getName(),
                        productEntity.getDescription(),
                        productEntity.getPrice()
                )).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long productId) {
        ProductEntity productEntity = productJPARepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found by id: " + productId)
        );

        return new Product(  // ¿Es mejor hacer un mapper?
                productEntity.getProductId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice()
        );

    }
}
