package com.oms.product_catalog.dto;

import jakarta.validation.constraints.NotBlank;

public record StyleRequest(
		@NotBlank String styleCode,
		@NotBlank String name
) {
}
