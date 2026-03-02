package com.oms.product_catalog.controller;

import com.oms.product_catalog.dto.TicketPriceRequest;
import com.oms.product_catalog.dto.TicketPriceResponse;
import com.oms.product_catalog.service.TicketPriceService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket-prices")
@RequiredArgsConstructor
public class TicketPriceController {

	private final TicketPriceService ticketPriceService;

	@GetMapping
	public List<TicketPriceResponse> getAll() {
		return ticketPriceService.getAll();
	}

	@GetMapping("/{productId}")
	public List<TicketPriceResponse> getByProductId(@PathVariable String productId) {
		return ticketPriceService.getByProductId(productId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TicketPriceResponse create(@Valid @RequestBody TicketPriceRequest request) {
		return ticketPriceService.create(request);
	}

	@PutMapping("/{productId}/{effectiveDate}")
	public TicketPriceResponse update(
			@PathVariable String productId,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate effectiveDate,
			@Valid @RequestBody TicketPriceRequest request) {
		return ticketPriceService.update(productId, effectiveDate, request);
	}

	@DeleteMapping("/{productId}/{effectiveDate}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(
			@PathVariable String productId,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate effectiveDate) {
		ticketPriceService.delete(productId, effectiveDate);
	}
}
