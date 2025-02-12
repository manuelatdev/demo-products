package com.test.store.Infrastructure.Controllers;

import com.test.store.Application.UseCases.CreateOrderUseCase;
import com.test.store.Application.UseCases.UpdateOrderUseCase;
import com.test.store.Domain.Model.ProductOrderRepository;
import com.test.store.Infrastructure.DTO.ProductOrderDTO;
import com.test.store.Infrastructure.Mapper.ProductOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class ProductOrderController {

    private final ProductOrderRepository productOrderRepository;

    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;

    @GetMapping("/{productOrderId}")
    public ResponseEntity<ProductOrderDTO> getProductOrder(@PathVariable Long productOrderId){
        if (productOrderId == null || productOrderId == 0) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(ProductOrderMapper.toDTO(productOrderRepository.getProductOrderById(productOrderId)));
    }


    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getProductOrders() {
        return ResponseEntity.ok(ProductOrderMapper.toDTOList(productOrderRepository.listProductOrders()));
    }

    @PutMapping
    public ResponseEntity<ProductOrderDTO> updateProductOrder(@RequestBody ProductOrderDTO productOrder){
        if (productOrder == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(ProductOrderMapper.toDTO(
                updateOrderUseCase.execute(ProductOrderMapper.toEntity(productOrder))));
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

    @DeleteMapping("/{productOrderId}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable Long productOrderId){
        if (productOrderId == null || productOrderId == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        productOrderRepository.deleteProductOrder(productOrderId);

        return ResponseEntity.ok(null);
    }

}
