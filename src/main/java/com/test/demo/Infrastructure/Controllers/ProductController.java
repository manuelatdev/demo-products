package com.test.demo.Infrastructure.Controllers;

import com.test.demo.Domain.Model.Product;
import com.test.demo.Domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado con ID: " + id)
        );
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }
}
