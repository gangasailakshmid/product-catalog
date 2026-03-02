package com.oms.product_catalog.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TicketPriceResponse(
		Long id,
		String productId,
		String productCode,
		BigDecimal ticketPrice,
		LocalDate effectiveDate
) {
}
