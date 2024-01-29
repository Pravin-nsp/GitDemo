package com.automation.TestCases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.utilities.Helper;
import com.automation.utilities.PayLoadData;

import groovyjarjarpicocli.CommandLine.Help;

public class Basic_Tests {

	public static String place_id;
	public static String to_be_updated_address="70 Summer walk, USA";
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//Post Method
		String body=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(PayLoadData.post_body_data()).
		when().post("/maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString() ;
		
		
		
		JsonPath response_Post=Helper.rawStringToJson(body);
		
		place_id=response_Post.getString("place_id");
		
		//PUT Method
		String body_update=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(PayLoadData.update_body_data()).
				when().put("/maps/api/place/update/json").
				then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath response_update=Helper.rawStringToJson(body_update);
		
		System.out.println(body_update);
		
		
		//Get Method
		String body_get=given().log().all().queryParam("key","qaclick123").queryParam("place_id", place_id).
		when().get("/maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath response_get=Helper.rawStringToJson(body_get);
		
		Assert.assertEquals(response_get.getString("address"),to_be_updated_address);
	}
	
	
	
}
