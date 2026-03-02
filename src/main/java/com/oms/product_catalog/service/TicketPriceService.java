package com.oms.product_catalog.service;

import com.oms.product_catalog.dto.TicketPriceRequest;
import com.oms.product_catalog.dto.TicketPriceResponse;
import com.oms.product_catalog.entity.Product;
import com.oms.product_catalog.entity.TicketPriceHistory;
import com.oms.product_catalog.exception.ResourceNotFoundException;
import com.oms.product_catalog.repository.TicketPriceHistoryRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketPriceService {

	private final TicketPriceHistoryRepository ticketPriceHistoryRepository;
	private final ProductService productService;
	@Transactional(readOnly = true)
	public List<TicketPriceResponse> getAll() {
		return ticketPriceHistoryRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public List<TicketPriceResponse> getByProductId(String productId) {
		return ticketPriceHistoryRepository.findByProductProductCodeOrderByEffectiveDateDesc(productId)
				.stream()
				.map(this::map)
				.toList();
	}

	@Transactional
	public TicketPriceResponse create(TicketPriceRequest request) {
		Product product = productService.findByProductCodeEntity(request.productId());
		TicketPriceHistory history = new TicketPriceHistory();
		apply(request, history, product);
		return map(ticketPriceHistoryRepository.save(history));
	}

	@Transactional
	public TicketPriceResponse update(String productId, LocalDate effectiveDate, TicketPriceRequest request) {
		Product product = productService.findByProductCodeEntity(productId);
		TicketPriceHistory history = findByProductCodeAndEffectiveDate(productId, effectiveDate);
		apply(request, history, product);
		TicketPriceHistory saved = ticketPriceHistoryRepository.save(history);
		return map(saved);
	}

	@Transactional
	public void delete(String productId, LocalDate effectiveDate) {
		ticketPriceHistoryRepository.delete(findByProductCodeAndEffectiveDate(productId, effectiveDate));
	}

	private TicketPriceHistory findByProductCodeAndEffectiveDate(String productCode, LocalDate effectiveDate) {
		return ticketPriceHistoryRepository.findByProductProductCodeAndEffectiveDate(productCode, effectiveDate)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Ticket price not found for productId " + productCode + " and effectiveDate " + effectiveDate));
	}

	private void apply(TicketPriceRequest request, TicketPriceHistory history, Product product) {
		history.setProduct(product);
		history.setTicketPrice(request.ticketPrice());
		history.setEffectiveDate(request.effectiveDate());
	}

	private TicketPriceResponse map(TicketPriceHistory history) {
		return new TicketPriceResponse(
				history.getId(),
				history.getProduct().getProductCode(),
				history.getProduct().getProductCode(),
				history.getTicketPrice(),
				history.getEffectiveDate()
		);
	}
}
