package com.test.demo.Infrastructure.Repositories;

import com.test.demo.Infrastructure.Entities.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderJPARepository extends JpaRepository<ProductOrderEntity, Long> {
}
