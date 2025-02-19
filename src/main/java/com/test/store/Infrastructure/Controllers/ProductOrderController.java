package com.test.store.Infrastructure.Controllers;

import com.test.store.Application.UseCases.CreateOrderUseCase;
import com.test.store.Application.UseCases.UpdateOrderUseCase;
import com.test.store.Domain.Model.ProductOrder;
import com.test.store.Domain.Model.ProductOrderRepository;
import com.test.store.Infrastructure.DTO.ProductOrderDTO;
import com.test.store.Infrastructure.Mapper.ProductOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderRepository productOrderRepository;

    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    @GetMapping("/{productOrderId}")
    public ResponseEntity<ProductOrderDTO> getProductOrder(@PathVariable Long productOrderId) {
        if (productOrderId == null || productOrderId == 0) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(ProductOrderMapper.toDTO(productOrderRepository.getProductOrderById(productOrderId)));
    }


    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getProductOrders() {
        return ResponseEntity.ok(ProductOrderMapper.toDTOList(productOrderRepository.listProductOrders()));
    }

    @PutMapping("/{productOrderId}")
    public ResponseEntity<ProductOrderDTO> updateProductOrder(@PathVariable(name = "productOrderId") Long productOrderId,
                                                              @RequestBody ProductOrderDTO productOrder) {
        if (productOrderId == null || productOrderId == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        if (productOrder == null) {
            return ResponseEntity.badRequest().body(null);
        }


        return ResponseEntity.ok(ProductOrderMapper.toDTO(
                updateOrderUseCase.execute(productOrderId, ProductOrderMapper.toEntity(productOrder))));
    }


    @PostMapping
    public ResponseEntity<ProductOrderDTO> createProductOrder(@RequestBody ProductOrderDTO productOrder) {

        if (productOrder.getProductId() == null || productOrder.getQuantity() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        if (productOrder.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        ProductOrderDTO orderDTO = ProductOrderMapper.toDTO(
                createOrderUseCase.execute(productOrder.getProductId(), productOrder.getQuantity()));

        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/{productOrderId}") // Revisar para devolver 404 en lugar de lanzar excepción
    public ResponseEntity<Void> deleteProductOrder(@PathVariable(name = "productOrderId") Long productOrderId) {
        if (productOrderId == null || productOrderId == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        ProductOrder produtToDelete = productOrderRepository.getProductOrderById(productOrderId);
        if (produtToDelete == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
        productOrderRepository.deleteProductOrder(productOrderId);

        return ResponseEntity.noContent().build();
    }

}
