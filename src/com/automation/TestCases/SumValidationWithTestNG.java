package com.automation.TestCases;

import org.testng.annotations.Test;

import com.automation.utilities.Helper;
import com.automation.utilities.PayLoadData;

import io.restassured.path.json.JsonPath;

public class SumValidationWithTestNG {

	
		
		@Test
		public void sumOfCourses() {
			JsonPath complex_Data = Helper.rawStringToJson(PayLoadData.course_Price());
			// calculating the size of the array
			int sizeOfArray = complex_Data.getInt("courses.size()");
			
			
		}
	
}
