package outhTwo.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.hamcrest.Matchers;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	
	
	
	
	public static RequestSpecification getRequestSpec() throws Exception {
		String filePath =System.getProperty("user.dir")+"\\src\\test\\resources\\Application.log";
		PrintStream P= null;
		try {
			P = new PrintStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestSpecBuilder rsb = new RequestSpecBuilder();
		 
		return		rsb.setBaseUri("https://api.spotify.com")
		 .setBasePath("/v1")
		.addHeader("Authorization", String.format("Bearer %s", TokenManager.renewToken()))
		.addFilter(new RequestLoggingFilter(LogDetail.ALL))
		.addFilter(new RequestLoggingFilter(P))
		.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
		.addFilter(new ResponseLoggingFilter(P))
		.setContentType(ContentType.JSON)
		.build();	
	}
	
	
	public static ResponseSpecification getResponseSpec() {
		ResponseSpecBuilder resb = new ResponseSpecBuilder();
		return
				resb
		.expectResponseTime(Matchers.lessThanOrEqualTo(3000L))
		.build();
		
	}

}
