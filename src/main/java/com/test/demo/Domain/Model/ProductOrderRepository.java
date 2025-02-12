package com.test.demo.Domain.Model;

import java.util.List;

public interface ProductOrderRepository {
    ProductOrder create(ProductOrder productOrder);

    List<ProductOrder> listProductOrders();

    ProductOrder getProductOrderById(Long productOrderId);
}
