package com.oms.product_catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket_price_history", indexes = {
		@Index(name = "idx_ticket_price_product_effective", columnList = "product_id,effective_date"),
		@Index(name = "idx_ticket_price_effective_date", columnList = "effective_date")
}, uniqueConstraints = {
		@UniqueConstraint(name = "uk_ticket_price_product_effective", columnNames = {"product_id", "effective_date"})
})
public class TicketPriceHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "ticket_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal ticketPrice;

	@Column(name = "effective_date", nullable = false)
	private LocalDate effectiveDate;
}
