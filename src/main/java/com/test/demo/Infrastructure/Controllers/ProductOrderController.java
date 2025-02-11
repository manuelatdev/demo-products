package com.test.demo.Infrastructure.Controllers;

import com.test.demo.Application.UseCases.CreateOrderUseCase;
import com.test.demo.Domain.Model.Product;
import com.test.demo.Domain.Model.ProductOrder;
import com.test.demo.Domain.ProductOrderRepository;
import com.test.demo.Domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    CreateOrderUseCase createOrderUseCase;

    @GetMapping
    public List<ProductOrder> getProductOrders(){
        return productOrderRepository.findAll();
    }

    @PostMapping
    public ProductOrder createProductOrder(@RequestBody ProductOrder productOrder){
        return createOrderUseCase.execute(productOrder);
    }
}
