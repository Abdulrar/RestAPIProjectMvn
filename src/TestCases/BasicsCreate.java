package TestCases;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;  
import static org.hamcrest.Matchers.*;


public class BasicsCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// validate Add place working as expected 
		//Rest Assured works on three principles 1)Given 2)When and 3)Then   
		///given - all input details 
		//when - Submit the API - resource,http method (POST, PUT, GET and DELETE)
		//Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Because there are some packages they are static in nature --- like given() we have to manually imported package
		//'equalTo' method is coming from "hamcrest.Matchers" java package is it also static 
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)");
		
		
		// In the given '.header' is input but same method in Then that is output validation
		
	}

}
