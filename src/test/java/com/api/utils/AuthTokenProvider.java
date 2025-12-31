package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role, String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

	private AuthTokenProvider() {

	}

	public static String getToken(Role role) {
		LOGGER.info("Cecking if token for {} role is present in the cache", role);
		if (tokenCache.containsKey(role)) {
			return tokenCache.get(role);
		}
		LOGGER.info("Token not found making the login request for the role {}", role);
		UserCredentials user = null;
		if (role == Role.FD) {
			user = new UserCredentials("iamfd", "password");
		} else if (role == Role.SUP) {
			user = new UserCredentials("iamsup", "password");
		} else if (role == Role.ENG) {
			user = new UserCredentials("iameng", "password");
		} else if (role == Role.QC) {
			user = new UserCredentials("iamqc", "password");
		}
		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON).body(user)
				.log().uri().log().body().log().headers().post("login").then().log().all().statusCode(200)
				.body("message", equalTo("Success")).body("data.token", notNullValue()).extract().jsonPath()
				.getString("data.token");
		System.out.println(token);
		tokenCache.put(role, token);
		return token;
	}

}
