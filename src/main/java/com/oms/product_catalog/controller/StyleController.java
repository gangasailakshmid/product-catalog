package com.oms.product_catalog.controller;

import com.oms.product_catalog.dto.StyleRequest;
import com.oms.product_catalog.dto.StyleResponse;
import com.oms.product_catalog.service.StyleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/styles")
@RequiredArgsConstructor
public class StyleController {

	private final StyleService styleService;

	@GetMapping
	public List<StyleResponse> getAll() {
		return styleService.getAll();
	}

	@GetMapping("/{styleId}")
	public StyleResponse getByStyleId(@PathVariable String styleId) {
		return styleService.getByStyleId(styleId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StyleResponse create(@Valid @RequestBody StyleRequest request) {
		return styleService.create(request);
	}

	@PutMapping("/{styleId}")
	public StyleResponse update(@PathVariable String styleId, @Valid @RequestBody StyleRequest request) {
		return styleService.update(styleId, request);
	}

	@DeleteMapping("/{styleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String styleId) {
		styleService.delete(styleId);
	}
}
