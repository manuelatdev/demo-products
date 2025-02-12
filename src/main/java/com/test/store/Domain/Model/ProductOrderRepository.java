package com.test.store.Domain.Model;

import java.util.List;

public interface ProductOrderRepository {

    ProductOrder getProductOrderById(Long productOrderId);

    List<ProductOrder> listProductOrders();

    ProductOrder create(ProductOrder productOrder);

    ProductOrder updateProductOrder(ProductOrder productOrder);

    Void deleteProductOrder(Long productOrderId);
}
