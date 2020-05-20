package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.control.customizers.builder.PostCompletionFactory;

import com.sun.tools.xjc.reader.Util;

import cucumber.runtime.Utils;
import io.restassured.RestAssured;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.AddPlace;
import pojo.Location;
import resourses.APIResources;
import resourses.SpecUtils;
import resourses.TestDataBuild;

public class StepDefinition extends SpecUtils {
	
	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification request;
	Response response,httpMethodresponse;
	static String reqPlace_id;
	
	JsonPath js;
	
	TestDataBuild data=new TestDataBuild();
	
	
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
			
        request=given().spec(requestSpecification())
				 .body(data.addPlacePayLoad(name,language,address));
	   
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String AddPlaceApi,String httpMethod) {
		
		
		// 
		APIResources apiResources= APIResources.valueOf(AddPlaceApi);
		System.out.println(apiResources.getResources());
		
		if(httpMethod.contentEquals("POST")) 
		     httpMethodresponse=request.when().post(apiResources.getResources());
				//then().log().all().spec(responseSpecification()).extract().response();
		else if(httpMethod.contentEquals("GET")) 
		{
			httpMethodresponse=request.when().get(apiResources.getResources());
					//then().log().all().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.contentEquals("DELETE"))
		{
			httpMethodresponse=request.when().delete(apiResources.getResources());
		}
		
	}

	@Then("the API call is successs with status code {int}")
	public void the_API_call_is_successs_with_status_code(Integer int1) {
		response=httpMethodresponse.then().log().all().spec(responseSpecification()).extract().response();
		assertEquals(response.getStatusCode(),200);
		System.out.println("----------response code---------"+response.getStatusCode());
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String statusKey, String Expectedvalue) {
		
		//String resp=response.asString();
		//JsonPath js=new JsonPath(resp);	
		assertEquals(getResponseKeyValue(response,statusKey).toString(),Expectedvalue);	
		System.out.println(response);
	}
	
	@Then("verify place_id created using with {string} using httpMethod {string}")
	public void verify_place_id_created_using_with_using_httpMethod(String actualName, String GetPlaceApi) throws IOException {
	    System.out.println(response);
	             reqPlace_id=getResponseKeyValue(response,"place_id");
	    
	             request=given().spec(requestSpecification()).queryParam("place_id", reqPlace_id);           
	             user_calls_with_http_request(GetPlaceApi,"GET");
	             String name=getResponseKeyValue(httpMethodresponse,"name");
	             assertEquals(name,actualName);
	}
	
	@Given("delete place with payload")
	public void delete_place_with_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		StepDefinition m=new StepDefinition();
		
		request=given().spec(requestSpecification()).body(data.deletePlacePayload(reqPlace_id));
		
	}

}
