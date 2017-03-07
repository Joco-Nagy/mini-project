package com.fortune.cookie.wisdom.service.transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResponseToPojoTransformer {

	public <T> List<T> convertResponseToWisdoms(String responseBody, Class<T> listType) {
		ObjectMapper mapper = new ObjectMapper();
		List<T> result = new ArrayList<>();
		try {
			result.addAll(mapper.readValue(responseBody,
					mapper.getTypeFactory().constructCollectionType(List.class, listType)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
