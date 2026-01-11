package com.api.services;

import static io.restassured.RestAssured.given;
import static com.api.utils.SpecUtil.RequestSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPONT = "login";
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
	private AuthService()
	{
		
	}
	@Step("Perform Login request with th user credentials")
	public static Response Login(Object userCredentials)
	{
		LOGGER.info("Making logging request for payload {}",((UserBean)userCredentials).getUsername());
		return given()
		.spec(RequestSpec(userCredentials))
		.when()
		.post(LOGIN_ENDPONT);
	}

}
