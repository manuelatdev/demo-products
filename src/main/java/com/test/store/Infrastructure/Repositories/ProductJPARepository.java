package com.test.store.Infrastructure.Repositories;

import com.test.store.Infrastructure.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {
}
