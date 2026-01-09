package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("******************RESPONSE*********************");
		LOGGER.info("BASE URI {}", requestSpec.getURI());
		LOGGER.info("HTTP METHOD {}", requestSpec.getMethod());
		redactHeader(requestSpec);

		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		LOGGER.info("******************RESPONSE*********************");
		LOGGER.info("BASE URI {}", response.getStatusLine());
		LOGGER.info("HTTP METHOD {}", response.getTimeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE HEADERS: \n {}", response.getHeaders());
		redactResponseBody(response);
		return response;
	}

	private void redactHeader(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();
		for (Header h : headerList) {
			if (h.getName().equalsIgnoreCase("Authorization")) {
				LOGGER.info("HEADER  {}  :  {}", h.getName(), "\"[REDACTED]\"");
			} else {
				LOGGER.info("HEADER  {}  :  {}", h.getName(), h.getValue());
			}
		}

	}

	public void redactPayload(FilterableRequestSpecification requestSpec) {
		if (requestSpec.getBody() != null) {
			String requestPayload = requestSpec.getBody().toString();
			requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"",
					"\"password\" : \"[REDACTED]\"");
			LOGGER.info("REQUEST PAYLOAD: \n {}", requestPayload);
		}
	}

	public void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\" : \"[REDACTED]\"");
		responseBody = responseBody.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\" : \"[REDACTED]\"");
		LOGGER.info("RESPONSE BODY: \n {}", responseBody);
	}

}
