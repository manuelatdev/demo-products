package com.test.store.Infrastructure;

import com.test.store.Domain.Model.ProductOrder;
import com.test.store.Domain.Model.ProductOrderRepository;
import com.test.store.Infrastructure.Entities.ProductOrderEntity;
import com.test.store.Infrastructure.Repositories.ProductOrderJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductOrderRepositoryImpl implements ProductOrderRepository {

    private final ProductOrderJPARepository productOrderJPARepository;

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

    @Transactional
    @Override
    public ProductOrder updateProductOrder(ProductOrder productOrder) {
        ProductOrderEntity productOrderEntity = new ProductOrderEntity(
                null,
                productOrder.getProductId(),
                productOrder.getQuantity(),
                productOrder.getTotalPrice()
        );

        ProductOrderEntity oldProductOrder = productOrderJPARepository.save(productOrderEntity);

        return new ProductOrder(
                oldProductOrder.getProductOrderId(),
                oldProductOrder.getProductId(),
                oldProductOrder.getQuantity(),
                oldProductOrder.getTotalPrice()
        );
    }

    @Override
    public ProductOrder create(ProductOrder productOrder) {
        ProductOrderEntity productOrderEntity = new ProductOrderEntity(
                null,
                productOrder.getProductId(),
                productOrder.getQuantity(),
                productOrder.getTotalPrice()
        );

        productOrderEntity = productOrderJPARepository.save(productOrderEntity);

        return new ProductOrder(
                productOrderEntity.getProductOrderId(),
                productOrderEntity.getProductId(),
                productOrderEntity.getQuantity(),
                productOrderEntity.getTotalPrice());
    }

    @Override
    public Void deleteProductOrder(Long productOrderId) {
        productOrderJPARepository.deleteById(productOrderId);
        return null;
    }


}
