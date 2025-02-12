package com.test.store.Infrastructure;

import com.test.store.Domain.Model.Product;
import com.test.store.Domain.Model.ProductRepository;
import com.test.store.Infrastructure.Entities.ProductEntity;
import com.test.store.Infrastructure.Repositories.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository // ??
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;



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

    @Transactional
    @Override
    public Product update(Product product){
        ProductEntity oldProduct = productJPARepository.findById(product.getProductId()).orElseThrow(() -> new RuntimeException("Not found product with id: " + product.getProductId()));

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());

        return new Product(
                oldProduct.getProductId(),
                oldProduct.getName(),
                oldProduct.getDescription(),
                oldProduct.getPrice()
        );
    }

    @Transactional
    @Override
    public Product create(Product product) {
        ProductEntity productEntityDb = productJPARepository.save(
                new ProductEntity(product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()));

        return new Product(  // revisar
                productEntityDb.getProductId(),
                productEntityDb.getName(),
                productEntityDb.getDescription(),
                productEntityDb.getPrice()
        );
    }

    @Override
    public Void delete(Long productId) {
        productJPARepository.deleteById(productId);
        return null;
    }
}
