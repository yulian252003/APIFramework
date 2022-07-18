package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils
{
	 static RequestSpecification res;
	 ResponseSpecification resspec;
	 Response response;
	 static String place_id;
	 TestDataBuild data=new TestDataBuild();

	 @Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		
		 
		        res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
		      
	}

	 @When("user calls {string} with {string} http request")
	 public void user_calls_with_http_request(String resource, String httpmethod) {
	
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpmethod.equalsIgnoreCase("POST"))	
		response=res.when().post(resourceAPI.getResource());
		else if(httpmethod.equalsIgnoreCase("GET"))
			response=res.when().get(resourceAPI.getResource());
			
				/*then().spec(resspec).extract().response();*/
	}

	@Then("the API call is success with satus code {int}")
	public void the_API_call_is_success_with_satus_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	  
	    assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resourcename) throws IOException {
	//requestSpec
		place_id=getJsonPath(response,"place_id");
		res=given().spec(requestSpecification().queryParam("place_id", place_id));
		user_calls_with_http_request(resourcename,"GET");
		Object actualname=getJsonPath(response,"name");
		assertEquals(actualname,expectedname);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException 
	{
		
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}


}
