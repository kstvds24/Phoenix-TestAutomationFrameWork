package com.api.services;

import static com.api.utils.SpecUtil.RequestSpec;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;

import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPONT = "login";
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	private AuthService()
	{
		
	}
	public static Response Login(Object userCredentials)
	{
		LOGGER.info("Making logging request for payload {}",((UserBean)userCredentials).getUsername());
		return given()
		.spec(RequestSpec(userCredentials))
		.when()
		.post(LOGIN_ENDPONT);
	}

}
