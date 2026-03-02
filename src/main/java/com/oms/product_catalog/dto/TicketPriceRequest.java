package com.oms.product_catalog.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TicketPriceRequest(
		@NotBlank String productId,
		@NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal ticketPrice,
		@NotNull LocalDate effectiveDate
) {
}
