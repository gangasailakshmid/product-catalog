package com.oms.product_catalog.repository;

import com.oms.product_catalog.entity.Style;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Long> {
	Optional<Style> findByStyleCode(String styleCode);
}
