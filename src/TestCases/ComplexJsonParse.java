package TestCases;

import CommanFun.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(PayLoad.courceContent());

		// 1. Print No of courses returned by API
		int count = js.getInt("courses.size()");

		System.out.println(count);

		// 2.Print Purchase Amount
		int totalPurchaseAmount = js.getInt("dashboard.purchaseAmount");

		System.out.println(totalPurchaseAmount);

		// 3. Print Title of the first course
		String strone = js.getString("courses[0].title");

		System.out.println(strone);

		// 4. Print All course titles and their respective Prices

		for (int i = 0; i < count; i++) {

			String courseTitle = js.get("courses[" + i + "].title");
			int coursePrise = js.get("courses[" + i + "].price");

			System.out.println(courseTitle);
			System.out.println(coursePrise);

		}

		// 5. Print no of copies sold by RPA Course

		System.out.println("Print no of copies sold by RPA Course ----- >");

		for (int i = 0; i < count; i++) {

			String courseTitle = js.get("courses[" + i + "].title");

			if (courseTitle.equalsIgnoreCase("RPA")) {

				int courseCopies = js.get("courses[" + i + "].copies");
				System.out.println(courseCopies);
				break;
			}

		}

		// 6. Print if Sum of all Course prices

		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount -----> ");
		int sum = 0;
		for (int i = 0; i < count; i++) {

			int coursePrice = js.get("courses[" + i + "].price");
			int courseCopies = js.get("courses[" + i + "].copies");
			int courseCount = coursePrice * courseCopies;
			sum = sum + courseCount;
			System.out.println(sum);

		}

		System.out.println(sum);
		/*
		 * System.out.println(sum);
		 * 
		 * if() {
		 * 
		 * 
		 * //System.out.println(courseCopies); //break; }
		 * 
		 * 
		 */
	}

}
