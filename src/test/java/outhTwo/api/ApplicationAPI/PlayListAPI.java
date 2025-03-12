package outhTwo.api.ApplicationAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import outhTwo.api.SpecBuilder;
import outhTwo.pojo.Example;

public class PlayListAPI {
	
	
	
	public static ValidatableResponse post(Example e) throws Exception {
		return RestAssured.given()
				.spec(SpecBuilder.getRequestSpec()).body(e)
				.when()
				.post("users/31n7c3vcjfxsccoba5pdmgwibcdq/playlists")
				.then().spec(SpecBuilder.getResponseSpec());
				
	}
	
	
	
	public static ValidatableResponse get(String PlayListID) throws Exception {
		
		return RestAssured.given()
    	.spec(SpecBuilder.getRequestSpec())
    	.when()
    	.get(String.format("/playlists/%s", PlayListID))
    	.then().spec(SpecBuilder.getResponseSpec())
    	;	
	}
	
	
public static ValidatableResponse put(String PlayListID, Example e) throws Exception {
		
	return RestAssured.given().spec(SpecBuilder.getRequestSpec())
	.body(e)
	.when()
	.put(String.format("playlists/%s", PlayListID))
	.then().spec(SpecBuilder.getResponseSpec())
	;
	
	}

}
