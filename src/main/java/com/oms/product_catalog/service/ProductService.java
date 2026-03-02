package com.oms.product_catalog.service;

import com.oms.product_catalog.dto.ProductRequest;
import com.oms.product_catalog.dto.ProductResponse;
import com.oms.product_catalog.entity.Product;
import com.oms.product_catalog.entity.Style;
import com.oms.product_catalog.exception.ResourceNotFoundException;
import com.oms.product_catalog.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final StyleService styleService;
	@Transactional(readOnly = true)
	public List<ProductResponse> getAll() {
		return productRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public ProductResponse getByProductId(String productId) {
		return map(findByProductCodeEntity(productId));
	}

	@Transactional
	public ProductResponse create(ProductRequest request) {
		Style style = styleService.findByStyleCodeEntity(request.styleId());
		Product product = new Product();
		product.setProductCode(request.productCode());
		product.setDescription(request.description());
		product.setInStoreDate(request.inStoreDate());
		product.setStyle(style);
		Product saved = productRepository.save(product);
		return map(saved);
	}

	@Transactional
	public ProductResponse update(String productId, ProductRequest request) {
		Style style = styleService.findByStyleCodeEntity(request.styleId());
		Product product = findByProductCodeEntity(productId);
		product.setProductCode(request.productCode());
		product.setDescription(request.description());
		product.setInStoreDate(request.inStoreDate());
		product.setStyle(style);
		return map(productRepository.save(product));
	}

	@Transactional
	public void delete(String productId) {
		productRepository.delete(findByProductCodeEntity(productId));
	}

	public Product findEntity(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
	}

	public Product findByProductCodeEntity(String productCode) {
		return productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productCode));
	}

	private ProductResponse map(Product product) {
		return new ProductResponse(
				product.getId(),
				product.getProductCode(),
				product.getDescription(),
				product.getInStoreDate(),
				product.getStyle().getStyleCode(),
				product.getStyle().getStyleCode(),
				product.getStyle().getName()
		);
	}
}
