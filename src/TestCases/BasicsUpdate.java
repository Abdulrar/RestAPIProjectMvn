package TestCases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;  
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import CommanFun.PayLoad;
import CommanFun.ReusableFunctions;

public class BasicsUpdate {

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
		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(PayLoad.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("JSON Responce body ----> "+responce);
		
		//for parsing Json (JsonPath as a class which takes as an input and convert that in to as a Json) 
		JsonPath js = new JsonPath(responce); 
		String pleaseID = js.getString("place_id");
		
		System.out.println("JSON Responce body with pleaseID ----> "+pleaseID);
	
		
		
		//Update place
		String newAdress = "70 Summer walk, USA";
		
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+pleaseID+"\",\r\n" + 
				"\"address\":\""+newAdress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//Get place and validate updated place is exist or not 
		
System.out.println("Get please --------------------> ");
		
		String getResponce = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pleaseID).when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();;
		
		System.out.println("JSON Get Responce body ----> "+getResponce);
		
		
		//JsonPath js1 = new JsonPath(getResponce); 
		
		JsonPath js1 = ReusableFunctions.rowToJson(getResponce);
		String actualAdress = js1.getString("address");
		
		// Above Jsonpath does not have validation so we go for Junit or TestNG
		
		Assert.assertEquals(actualAdress, newAdress);
		
		
		System.out.println("JSON Get Responce address ----> "+actualAdress);
		
		
	}

}
