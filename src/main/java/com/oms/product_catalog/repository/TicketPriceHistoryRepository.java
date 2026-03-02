package com.oms.product_catalog.repository;

import com.oms.product_catalog.entity.TicketPriceHistory;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceHistoryRepository extends JpaRepository<TicketPriceHistory, Long> {
	List<TicketPriceHistory> findByProductProductCodeOrderByEffectiveDateDesc(String productCode);

	Optional<TicketPriceHistory> findByProductProductCodeAndEffectiveDate(String productCode, LocalDate effectiveDate);
}
