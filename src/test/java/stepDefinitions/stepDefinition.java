package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils
{
	public static RequestSpecification res;
	public static ResponseSpecification resspec;
	Response response;
	

	@Given("Add Place Payload")
	public static void add_Place_Payload() throws FileNotFoundException {
	    // Write code here that turns the phrase above into concrete actions
		
		
		TestDataBuild data=new TestDataBuild();
		        res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad());
		      
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response=res.when().post("/maps/api/place/add/json")
				.then().spec(resspec).extract().response();
	}

	@Then("the API call is success with satus code {int}")
	public void the_API_call_is_success_with_satus_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    String resp=response.asString();
	    JsonPath js= new JsonPath(resp);
	    assertEquals(js.get(keyValue).toString(),Expectedvalue);
	}


}
