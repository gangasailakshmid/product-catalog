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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product", indexes = {
		@Index(name = "idx_product_style_id", columnList = "style_id"),
		@Index(name = "idx_product_instore_date", columnList = "instore_date")
})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_code", nullable = false, unique = true, length = 32)
	private String productCode;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(name = "instore_date")
	private LocalDate inStoreDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "style_id", nullable = false)
	private Style style;

	@OneToMany(mappedBy = "product")
	private List<TicketPriceHistory> ticketPriceHistory;
}
