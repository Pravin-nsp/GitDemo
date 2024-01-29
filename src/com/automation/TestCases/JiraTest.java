package com.automation.TestCases;

import org.testng.annotations.Test;

import com.automation.utilities.PayLoadData;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {
	
	public static String key="10005";
//public static String;
	//public static String;
	public static void main(String[] args) {
		
		//Base URI
		RestAssured.baseURI="http://localhost:8080";
		
		//Session creation
		SessionFilter ss=new SessionFilter();
		//Creating new issue
		given().log().all().header("Content-Type", "application/json").body("{\n"
				+ "    \"username\": \"nsp\",\n"
				+ "    \"password\": \"nsp\"\n"
				+ "}").filter(ss). when().post("/rest/auth/1/session").then().log().all().statusCode(200).extract().response().asString();
	
		
		given().log().all().header("Content-Type","application/json").pathParam("key", "10005").body(PayLoadData.jiraComment()).filter(ss).
		when().post("/rest/api/2/issue/{key}/comment").
		then().log().all().statusCode(201);
	}
}
