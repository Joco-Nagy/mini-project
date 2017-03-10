package com.fortune.cookie.wisdom.config.exceptionhandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fortune.cookie.wisdom.web.controller.WisdomRestController;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.domain.factory.ResponseLinkFactory;

@ControllerAdvice(basePackageClasses = WisdomRestController.class)
public class LinkerAdvice implements ResponseBodyAdvice<WisdomResponse> {

	private ResponseLinkFactory linkFactory;

	@Autowired
	public LinkerAdvice(ResponseLinkFactory linkFactory) {
		super();
		this.linkFactory = linkFactory;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public WisdomResponse beforeBodyWrite(WisdomResponse body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		body.addLink(linkFactory.getLinkForWisdom(body.getCategory(), body.getId(), "self"));
		return body;
	}

}
