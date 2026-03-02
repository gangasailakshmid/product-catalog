package com.oms.product_catalog.service;

import com.oms.product_catalog.dto.StyleRequest;
import com.oms.product_catalog.dto.StyleResponse;
import com.oms.product_catalog.entity.Style;
import com.oms.product_catalog.exception.ResourceNotFoundException;
import com.oms.product_catalog.repository.StyleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StyleService {

	private final StyleRepository styleRepository;
	@Transactional(readOnly = true)
	public List<StyleResponse> getAll() {
		return styleRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public StyleResponse getByStyleId(String styleId) {
		return map(findByStyleCodeEntity(styleId));
	}

	@Transactional
	public StyleResponse create(StyleRequest request) {
		Style style = new Style();
		style.setStyleCode(request.styleCode());
		style.setName(request.name());
		Style saved = styleRepository.save(style);
		return map(saved);
	}

	@Transactional
	public StyleResponse update(String styleId, StyleRequest request) {
		Style style = findByStyleCodeEntity(styleId);
		style.setStyleCode(request.styleCode());
		style.setName(request.name());
		return map(styleRepository.save(style));
	}

	@Transactional
	public void delete(String styleId) {
		styleRepository.delete(findByStyleCodeEntity(styleId));
	}

	public Style findEntity(Long id) {
		return styleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Style not found: " + id));
	}

	public Style findByStyleCodeEntity(String styleCode) {
		return styleRepository.findByStyleCode(styleCode)
				.orElseThrow(() -> new ResourceNotFoundException("Style not found: " + styleCode));
	}

	private StyleResponse map(Style style) {
		return new StyleResponse(style.getId(), style.getStyleCode(), style.getName());
	}
}
