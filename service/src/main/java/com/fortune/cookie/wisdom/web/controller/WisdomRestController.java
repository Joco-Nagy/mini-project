package com.fortune.cookie.wisdom.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

@RestController
@RequestMapping("/api")
public class WisdomRestController {

	private final WisdomSearchService wisdomSearchService;

	private final WisdomToWisdomResponseTransformer transformer;

	@Autowired
	public WisdomRestController(WisdomSearchService wisdomSearchService,
			WisdomToWisdomResponseTransformer wisdomToWisdomResponseTransformer) {
		this.wisdomSearchService = wisdomSearchService;
		this.transformer = wisdomToWisdomResponseTransformer;
	}

	@GetMapping("/categories/{category}/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AbstractResponse getWisdomByCategoryAndId(@PathVariable("category") String category,
			@PathVariable("id") Long id) {
		return transformer.convert(wisdomSearchService.getWisdomByCategoryAndId(category, id));
	}

}
