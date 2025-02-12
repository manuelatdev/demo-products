package com.test.demo.Infrastructure.Controllers;

import com.test.demo.Application.UseCases.CreateOrderUseCase;
import com.test.demo.Domain.Model.ProductOrderRepository;
import com.test.demo.Infrastructure.DTO.ProductOrderDTO;
import com.test.demo.Infrastructure.Mapper.ProductOrderMapper;
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

    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getProductOrders() {
        return ResponseEntity.ok(ProductOrderMapper.toDTOList(productOrderRepository.listProductOrders()));
    }

    @PostMapping // He probado a generar todos los casos posibles de respuesta, como referencia
    public ResponseEntity<?> createProductOrder(@RequestBody ProductOrderDTO productOrder) {
        try {
            if (productOrder.getProductId() == null || productOrder.getQuantity() == null) {
                return ResponseEntity.badRequest().body("El ID del producto y la cantidad son obligatorios");
            }
            if (productOrder.getQuantity() <= 0) {
                return ResponseEntity.badRequest().body("La cantidad debe ser mayor que 0");
            }

            ProductOrderDTO orderDTO = ProductOrderMapper.toDTO(
                    createOrderUseCase.execute(productOrder.getProductId(), productOrder.getQuantity()));

            return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al crear la orden: " + e.getMessage());
        }
    }

}
