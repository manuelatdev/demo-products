package com.test.store.Domain.Model;

import java.util.List;

public interface ProductRepository {

    Product getProductById(Long productId);

    List<Product> listProducts();

    Product update(Product product);

    Product create(Product product);

    Void delete(Long productId); // ¿Qué debe devolver delete?
}
