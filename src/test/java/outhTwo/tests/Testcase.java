package outhTwo.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import outhTwo.api.ApplicationAPI.PlayListAPI;
import outhTwo.pojo.ErrorRoot;
import outhTwo.pojo.Example;
import utility.FakerUtility;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Testcase {
	RequestSpecification requestSpec ;
	ResponseSpecification responseSpec;

	String playListId="";


	@BeforeClass(alwaysRun = true)
	public void setup()  {


	}



	@Test(description = "Create new Playlist" ,priority = -3)
	public void CreatePlaylist() throws Exception {
		Example e = new Example();
		e.setCollaborative(false);
		e.setName(FakerUtility.generateName());
		e.setDescription(FakerUtility.generateName());
		e.set_public(false);
		//Making Api calls 
		Example responsePayload =
				PlayListAPI.post(e)
				.assertThat().statusCode(201)
				.contentType(ContentType.JSON)
				.extract().response().as(Example.class);
		//Asserting Response using PoJO
		assertEquals(responsePayload.getName(), e.getName());
		assertEquals(responsePayload.getDescription(), e.getDescription());
		assertFalse(responsePayload.getCollaborative());
		assertFalse(responsePayload.get_public());
		//Extract PlaylistId for other Test cases 
		playListId=responsePayload.getId();


	}

	@Test(description = "Retrive A PlayList" , priority = 0)
	public void retrivePlayList() throws Exception {
		PlayListAPI.get(playListId)
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON);
		//    	.body("description", equalTo(payload.get("description")),
		//				"name" , equalTo(payload.get("name"))
		//				)
	}

	@Test(description = "Update the PlayList" , priority = 1)
	public void updatePlayList() throws Exception {

		Example reqPayload = new Example();
		reqPayload.setCollaborative(false);
		reqPayload.setName("Rest Assure PlayList With Pojo Update");
		reqPayload.setDescription("Playlist Created From Rest Assure Update");
		reqPayload.set_public(false);

		PlayListAPI.put(playListId, reqPayload)
		.assertThat().statusCode(StatusCodeExl.CODE_200.i);


	}

	@Test(description = "Create new Playlist Without Name" ,priority = 3)
	public void CreatePlaylistNeg() throws Exception {
		Example reqPayload = new Example();
		reqPayload.setCollaborative(false);
		reqPayload.setDescription("Playlist Created From Rest Assure Update");
		reqPayload.set_public(false);

		ErrorRoot er = PlayListAPI.post(reqPayload)
				.assertThat().statusCode(StatusCodeExl.CODE_400.i)
				.contentType(ContentType.JSON)
				.extract().response()
				.as(ErrorRoot.class);

		assertEquals(er.getError().getStatus(), StatusCodeExl.CODE_400.i);
		assertEquals(er.getError().getMessage(), StatusCodeExl.CODE_400.string);

	}

}
