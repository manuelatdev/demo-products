package com.test.demo.Infrastructure;

import com.test.demo.Domain.Model.ProductOrder;
import com.test.demo.Domain.Model.ProductOrderRepository;
import com.test.demo.Infrastructure.Entities.ProductOrderEntity;
import com.test.demo.Infrastructure.Repositories.ProductOrderJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductOrderRepositoryImpl implements ProductOrderRepository {

    private final ProductOrderJPARepository productOrderJPARepository;


    @Override
    public ProductOrder create(ProductOrder productOrder) {
        ProductOrderEntity productOrderEntity = new ProductOrderEntity(
                null,
                productOrder.getProductId(),
                productOrder.getQuantity(),
                productOrder.getTotalPrice()
        );
        System.out.println(productOrderEntity);
        productOrderEntity = productOrderJPARepository.save(productOrderEntity);
        System.out.println(productOrderEntity);
        return new ProductOrder(
                productOrderEntity.getProductOrderId(),
                productOrderEntity.getProductId(),
                productOrderEntity.getQuantity(),
                productOrderEntity.getTotalPrice());
    }

    @Override
    public List<ProductOrder> listProductOrders() {
        return productOrderJPARepository.findAll().stream().map(productOrderEntity ->
                new ProductOrder(
                        productOrderEntity.getProductOrderId(),
                        productOrderEntity.getProductId(),
                        productOrderEntity.getQuantity(),
                        productOrderEntity.getTotalPrice()
                )).toList();
    }

    @Override
    public ProductOrder getProductOrderById(Long productOrderId) {
        ProductOrderEntity productOrderEntity = productOrderJPARepository.findById(productOrderId).orElseThrow(
                () -> new RuntimeException("Order not found by id: " + productOrderId));

        return new ProductOrder(
                productOrderEntity.getProductOrderId(),
                productOrderEntity.getProductId(),
                productOrderEntity.getQuantity(),
                productOrderEntity.getTotalPrice()
        );
    }
}
