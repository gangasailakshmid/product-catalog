package com.oms.product_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ProductRequest(
		@NotBlank String productCode,
		@NotBlank String description,
		@NotBlank String styleId,
		LocalDate inStoreDate
) {
}
