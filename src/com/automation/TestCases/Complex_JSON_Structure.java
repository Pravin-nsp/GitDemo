package com.automation.TestCases;

import org.testng.Assert;

import com.automation.utilities.Helper;
import com.automation.utilities.PayLoadData;

import io.restassured.path.json.JsonPath;

public class Complex_JSON_Structure {
	/*
	 * 1. Print No of courses returned by API
	 * 
	 * 2.Print Purchase Amount
	 * 
	 * 3. Print Title of the first course
	 * 
	 * 4. Print All course titles and their respective Prices
	 * 
	 * 5. Print no of copies sold by RPA Course
	 * 
	 * 6. Verify if Sum of all Course prices matches with Purchase Amount
	 * 
	 * 
	 */

	
	public static void main(String[] args) {
		// Converting the Data to JSON even if it is not an API at this moment

		JsonPath complex_Data = Helper.rawStringToJson(PayLoadData.course_Price());
		// calculating the size of the array
		int sizeOfArray = complex_Data.getInt("courses.size()");

		// Test Validation 1
		System.out.println(sizeOfArray);

		int totalAmount = complex_Data.getInt("dashboard.purchaseAmount");

		int sum = 0;

		for (int i = 0; i < sizeOfArray; i++) {
			sum += complex_Data.getInt("courses[" + i + "].price")*complex_Data.getInt("courses[" + i + "].copies");
		}

		// Test Validation 2
		System.out.println("Total Purchase Amount is " + totalAmount);

		// Test Validation 3
		String courseTitle = complex_Data.getString("courses[0].title");

		System.out.println("Title of the first course is " + courseTitle);

		// Test Validation 4
		for (int i = 0; i < sizeOfArray; i++) {
			String current_courseTitle = complex_Data.getString("courses[" + i + "].title");
			int price = complex_Data.getInt("courses[" + i + "].price");

			System.out.println("Title of the course is " + current_courseTitle + " and the price is " + price);
		}
		int copiesSold=0;
		//Test Validation 5
		for (int i = 0; i < sizeOfArray; i++) {
			String current_courseTitle = complex_Data.getString("courses[" + i + "].title");
			
			if(current_courseTitle.equals("RPA")){
				copiesSold=complex_Data.getInt("courses["+i+"].copies");				
				break;
			}
		
		}
		System.out.println("The no of RPA copies sold is "+copiesSold);
		//Test case Validation 6
		Assert.assertEquals(sum, totalAmount);
		
		System.out.println("Sum is "+sum+" and total is "+totalAmount);
		
		
	}

}
