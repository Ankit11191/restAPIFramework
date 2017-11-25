package main.java.restAPIFramework.com.rest.service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import java.util.List;

import org.json.JSONObject;

public class Services {

	List<JSONObject> list;
	public Response createAPI()
	{
		RequestSpecification requestSpecification=RestAssured.given();
		Response response=requestSpecification.get(ServiceURL.url);
		return response;
	}

	public static void main(String[] args) {
		Services services=new Services();
		Response res=services.createAPI();
		System.out.println(res.getBody().asString());
	}
}
