package com.oms.product_catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "style", indexes = {
		@Index(name = "idx_style_name", columnList = "name")
})
public class Style {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "style_code", nullable = false, unique = true, length = 32)
	private String styleCode;

	@Column(nullable = false, length = 120)
	private String name;
}
