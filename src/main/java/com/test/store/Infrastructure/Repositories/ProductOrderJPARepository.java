package com.test.store.Infrastructure.Repositories;

import com.test.store.Infrastructure.Entities.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderJPARepository extends JpaRepository<ProductOrderEntity, Long> {
}
