package com.test.demo.Domain.Model;

import java.util.List;

public interface ProductRepository {
    Product create(Product product);

    List<Product> listProducts();

    Product getProductById(Long productId);
}
