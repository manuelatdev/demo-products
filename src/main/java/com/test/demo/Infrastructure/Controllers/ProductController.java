package com.test.demo.Infrastructure.Controllers;

import com.test.demo.Domain.Model.ProductRepository;
import com.test.demo.Infrastructure.DTO.ProductDTO;
import com.test.demo.Infrastructure.Mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(ProductMapper.toDTOList(productRepository.listProducts()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.getProductById(productId)));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.create(ProductMapper.toEntity(product))));
    }
}
