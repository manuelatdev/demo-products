package com.test.store.Infrastructure.Controllers;

import com.test.store.Application.UseCases.UpdateProductUseCase;
import com.test.store.Domain.Model.ProductRepository;
import com.test.store.Infrastructure.DTO.ProductDTO;
import com.test.store.Infrastructure.Mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    private final UpdateProductUseCase updateProductUseCase;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.getProductById(productId)));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(ProductMapper.toDTOList(productRepository.listProducts()));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO product) {
        if (productId == null || productId == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        if (product == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(ProductMapper.toDTO(updateProductUseCase.execute(productId, ProductMapper.toEntity(product))));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.create(ProductMapper.toEntity(product))));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {  // ¿Qué devolvemos cuando DELETE?
        if (productId == null || productId == 0) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(productRepository.delete(productId));
    }

}
