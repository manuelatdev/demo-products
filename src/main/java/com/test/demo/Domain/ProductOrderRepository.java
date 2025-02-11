package com.test.demo.Domain;

import com.test.demo.Domain.Model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
