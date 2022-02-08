package TestCases;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

/*import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
*/


public class AuthTwoZero {

public static void main(String[] args) throws InterruptedException {

	//Note : Every time need to provide by using browser authentication with below URL 
	
	//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=exactCode
	
String url ="https://rahulshettyacademy.com/getCourse.php?state=exactCode&code=4%2F0AY0e-g44GC6X_s-QjfpN2_wpgiFvJ3M8TU7gmZbrF7VRkhrWquGVlhfuyv5etBfkbsFkow&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";

String partialcode=url.split("code=")[1];

String code=partialcode.split("&scope")[0];

System.out.println("My auto code ----->" + code);

String response = given().urlEncodingEnabled(false)
                         .queryParams("code",code)
                         .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                         .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                         .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                         .queryParams("grant_type", "authorization_code")
                         .when().log().all()
                         .post("https://www.googleapis.com/oauth2/v4/token").asString();

    JsonPath jsonPath = new JsonPath(response);
    String accessToken = jsonPath.getString("access_token");
    System.out.println(accessToken);
    
    
    
String r2= given().queryParams("access_token", accessToken).

when().get("https://rahulshettyacademy.com/getCourse.php").asString();

System.out.println(r2);


}


}

