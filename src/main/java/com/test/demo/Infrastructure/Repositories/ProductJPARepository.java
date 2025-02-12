package com.test.demo.Infrastructure.Repositories;

import com.test.demo.Infrastructure.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
