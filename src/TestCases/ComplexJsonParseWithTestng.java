package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommanFun.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParseWithTestng {

		//6. Verify if Sum of all Course prices matches with Purchase Amount
	
	@Test
	public void sumOfCourses() {
		
		JsonPath js = new JsonPath(PayLoad.courceContent());
		int count = js.getInt("courses.size()");	
	
		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount -----> ");
	
		int sum = 0;
		for (int i = 0; i < count; i++) {

			int coursePrice = js.get("courses[" + i + "].price");
			int courseCopies = js.get("courses[" + i + "].copies");
			int courseCount = coursePrice * courseCopies;
			sum = sum + courseCount;

		}
		
		System.out.println("Expected sum is -----> "+sum);
		
		int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(totalPurchaseAmount, sum);

	}
}