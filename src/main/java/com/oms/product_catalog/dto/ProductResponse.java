package com.oms.product_catalog.dto;

import java.time.LocalDate;

public record ProductResponse(
		Long id,
		String productCode,
		String description,
		LocalDate inStoreDate,
		String styleId,
		String styleCode,
		String styleName
) {
}
