package TestCases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;  
import static org.hamcrest.Matchers.*;
import CommanFun.PayLoad;

public class BasicsGet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Rest Assured works on three principles 1)Given 2)When and 3)Then   
		///given - all input details 
		//when - Submit the API - resource,http method
		//Then - validate the response
		
		
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Because there are some packages they are static in nature --- like given() we have ti manually inport package
		//equalTo method is coming from "hamcrest.Matchers" java package is it also static 
		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(PayLoad.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("JSON Responce body ----> "+responce);
		
//for parsing(Pada nirmanamu) Json (JsonPath as a class which takes string as an input and convert that in to as a Json) 
		JsonPath js = new JsonPath(responce); 
		String pleaseID = js.getString("place_id");
		
		System.out.println("JSON Responce body with pleaseID ----> "+pleaseID);
		
		
		//Get please 
		
		System.out.println("Get please --------------------> ");
		
		String getResponce = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pleaseID).when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();;
		
		System.out.println("JSON Get Responce body ----> "+getResponce);
	}

}
