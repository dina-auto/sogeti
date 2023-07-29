package com.stepdefinition;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.ExcelDataReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Dineshkumar
 *
 */
public class APIStepDefinition {

	public RequestSpecification httpRequest;
	public Response response;
	public String filePath;
	List<Map<String, String>> testDataList;

	@Given("^I want to create the API base setup for Zipcode$")
	public void i_want_to_create_the_API_base_setup_for_Zipcode() throws Throwable {
		RestAssured.baseURI = "http://api.zippopotam.us";
	}

	@Then("^I run the request and validate Test status code and content type$")
	public void i_run_the_request_and_validate_Test_status_code_and_content_type() throws Throwable {
		try {
			given().when().get("/de/bw/stuttgart").then().assertThat().statusCode(200).contentType("application/json");

			System.out.println("Test - Verify Status Code and Content Type: Passed");
		} catch (AssertionError e) {
			System.out.println("Test - Verify Status Code and Content Type: Failed");
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	@Then("^I run the request and validate Response time$")
	public void i_run_the_request_and_validate_Response_time() throws Throwable {
		try {
			given().when().get("/de/bw/stuttgart").then().assertThat().time(lessThan(1000L)); // Response time should be
																								// less than 1s (1000
																								// milliseconds)

			System.out.println("Test - Verify Response Time: Passed");
		} catch (AssertionError e) {
			System.out.println("Test - Verify Response Time: Failed");
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	@Then("^I run the request and validate Country and State$")
	public void i_run_the_request_and_validate_Country_and_State() throws Throwable {
		try {
			given().when().get("/de/bw/stuttgart").then().assertThat().body("country", equalTo("Germany")).body("state",
					equalTo("Baden-Württemberg"));

			System.out.println("Test - Verify Country and State: Passed");
		} catch (AssertionError e) {
			System.out.println("Test - Verify Country and State: Failed");
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	@Then("^I run the request and validate Place name for specific post code$")
	public void i_run_the_request_and_validate_Place_name_for_specific_post_code() throws Throwable {
		String expectedPlaceName = "Stuttgart Degerloch";
		try {
			given().when().get("/de/bw/stuttgart").then().body("places.'post code'", hasItem("70597"))
					.body("places.'place name'", hasItem(expectedPlaceName));

			System.out.println("Test - Verify Place Name for Post Code 70597: Passed");
			System.out.println("Expected Place Name: " + expectedPlaceName);
			System.out.println("Actual Place Name: " + given().when().get("/de/bw/stuttgart").jsonPath()
					.getString("places.find { it.'post code' == '70597' }.'place name'"));
		} catch (AssertionError e) {
			System.out.println("Test - Verify Place Name for Post Code 70597: Failed");
			System.out.println("Expected Place Name: " + expectedPlaceName);
			System.out.println("Error Message: " + e.getMessage());
		}
	}

	@Given("^I read the excel and have the Data set$")
	public void i_read_the_excel_and_have_the_Data_set() throws Throwable {
		filePath = System.getProperty("user.dir") + "/src/test/resources/testdata/SogetiTestData.xlsx";

		testDataList = ExcelDataReader.readTestData(filePath, "Sheet1");
	}

	@When("^I run the request in data driven and validate the response$")
	public void i_run_the_request_in_data_driven_and_validate_the_response() throws Throwable {
		for (Map<String, String> testData : testDataList) {
			String country = testData.get("Country");
			String postalCode = testData.get("PostalCode");
			String excelplaceName = testData.get("PlaceName");

			System.out.println("Country Name - : " + country);
			System.out.println("Postal code - " + postalCode);

			RestAssured.baseURI = "http://api.zippopotam.us/" + country + "/" + postalCode;
			httpRequest = RestAssured.given();
			response = httpRequest.when().get();
			Assert.assertEquals(200, response.getStatusCode());
			System.out
					.println("The response status code is - " + response.getStatusCode() + "- validated successfully");
			System.out.println(response.contentType());
			Assert.assertEquals("application/json", response.contentType());
			System.out.println("The response content type - " + response.contentType() + "- validated successfully");
			JsonPath jp = response.jsonPath();
			String resPlaceName = jp.getString("places[0].'place name'").toString();
			Assert.assertEquals(excelplaceName, resPlaceName);
			System.out.println(
					"Excel Place name " + excelplaceName + " is matching with response place name " + resPlaceName);
		}
	}

}
