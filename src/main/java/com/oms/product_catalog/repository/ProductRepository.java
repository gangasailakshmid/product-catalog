package com.oms.product_catalog.repository;

import com.oms.product_catalog.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByProductCode(String productCode);
}
