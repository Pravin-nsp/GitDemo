package com.automation.utilities;

import io.restassured.path.json.JsonPath;

public class Helper {

	public static JsonPath rawStringToJson(String val) {
		JsonPath convertedValue=new JsonPath(val);
		return convertedValue;
	}
}
