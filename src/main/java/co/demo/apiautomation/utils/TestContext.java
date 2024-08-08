package co.demo.apiautomation.utils;

import java.util.HashMap;
import java.util.Map;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
		
	public Response response;
	public static Map<String, Object> session = new HashMap<String, Object>();
	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");
	
	public RequestSpecification requestSetup(String appBaseURL) {	
		RestAssured.reset();
		Options options = Options.builder().logStacktrace().build();
		RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options);
		if (appBaseURL.equalsIgnoreCase("app1")) {
			System.out.println("Base URL 1 called");
			RestAssured.baseURI = PropertiesFile.getProperty("app1.baseURL");
			System.out.println(RestAssured.baseURI);
		} else if (appBaseURL.equalsIgnoreCase("app2")) {
			System.out.println("Base URL 2 called");
			RestAssured.baseURI = PropertiesFile.getProperty("app2.baseURL");
		} else if (appBaseURL.equalsIgnoreCase("app3")) {
			System.out.println("Base URL 3 called");
			RestAssured.baseURI = PropertiesFile.getProperty("app3.baseURL");
		} else {
			System.out.println("Base URL 4 called");
			RestAssured.baseURI = PropertiesFile.getProperty("app4.baseURL");
		}
		return RestAssured.given()
				.config(config)
				.filter(new RestAssuredRequestFilter())				
				.contentType(CONTENT_TYPE)
				.accept(CONTENT_TYPE);
	} 
}
