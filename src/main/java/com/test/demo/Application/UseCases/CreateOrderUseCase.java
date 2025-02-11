package com.test.demo.Application.UseCases;

import com.test.demo.Domain.Model.Product;
import com.test.demo.Domain.Model.ProductOrder;
import com.test.demo.Domain.ProductOrderRepository;
import com.test.demo.Domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private final ProductOrderRepository productOrderRepository;

    public CreateOrderUseCase(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }


    public ProductOrder execute(ProductOrder productOrder){
        if(productOrder.getQuantity() > 0)
        {
            Product product = productRepository.findById(productOrder.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productOrder.getProductId()));

            productOrder.setTotalPrice(product.getPrice() * productOrder.getQuantity());

            return productOrderRepository.save(productOrder);
        } else {
            throw new RuntimeException("La cantidad debe ser mayor que 0");
        }
    }
}
