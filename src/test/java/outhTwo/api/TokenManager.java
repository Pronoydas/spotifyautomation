package outhTwo.api;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.PropertiesUtility;

public class TokenManager {
	
	private static String token;
	private static Instant exp ;
	
	// synchronized - This keyword ensure one thred can access at one time 
	public  static String renewToken() throws Exception {
		
		if(token == null || Instant.now().isAfter(exp)) {
			Response r =getAccessToken();
			token = r.jsonPath().get("access_token");
			int expriyTime = r.jsonPath().get("expires_in");
			exp =Instant.now().plusSeconds(expriyTime);
		}
		
		return token;
		
	}
	
	
	
	
	
	
	private static Response getAccessToken() throws Exception {
		Map<String, String> param = new HashMap<>();
		param.put("grant_type", PropertiesUtility.getPropertiyValue("grant_type"));
		param.put("refresh_token",PropertiesUtility.getPropertiyValue("refresh_token"));
		param.put("client_id",PropertiesUtility.getPropertiyValue("client_id"));
		
		return RestAssured.given()
		.baseUri("https://accounts.spotify.com")
		.basePath("/api/token")
		.header("Authorization", "Basic NzM5NDc5OThmNjA3NDZlYmFmYjQyZmUzNDlhZTg2MmQ6YTlmMWM2NWU2NmUwNDhjMWE2MjRiYWNkYTUyNDMyMTA=")
		.contentType(ContentType.URLENC)
		.formParams(param)
		.when()
		.post();
		//.then().extract().response().jsonPath().get("access_token");
		
	}

}
