package com.fortune.cookie.wisdom.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.fortune.cookie.wisdom.service.RestTemplateBasedWisdomSearchService;
import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.CategoryListResponse;
import com.fortune.cookie.wisdom.web.domain.CategoryResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

public class CategoriesRestControllerTest {
	private CategoriesRestController underTest;
	private WisdomSearchService service;
	private WisdomToWisdomResponseTransformer transformer;

	@Before
	public void setUp() {
		service = Mockito.mock(RestTemplateBasedWisdomSearchService.class);
		transformer = Mockito.mock(WisdomToWisdomResponseTransformer.class);
		underTest = new CategoriesRestController(service, transformer);
	}

	@Test
	public void testGetCategoriesShouldCallServiceAndTransformerAndLinkFactory() {
		// GIVEN
		String category = "Test_category";
		List<String> categories = Arrays.asList(category);
		BDDMockito.given(service.getCategories()).willReturn(categories);
		// WHEN
		CategoryListResponse response = underTest.getCategories();
		// THEN
		assertThat("size of response list", response.getCategories().size() == 1);
		assertThat("response type check", response.getCategories().get(0), instanceOf(CategoryResponse.class));
		assertThat("response contains the correct category",
				((CategoryResponse) response.getCategories().get(0)).getCategory(), equalTo(category));
	}
}