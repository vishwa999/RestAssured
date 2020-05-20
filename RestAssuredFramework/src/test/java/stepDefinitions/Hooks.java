package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenarion() throws IOException {
		
		StepDefinition m=new StepDefinition();
		
		if(StepDefinition.reqPlace_id==null) {
		m.add_Place_Payload_with("Vishwanath", "Hindi","Kasturi nagar");
		m.user_calls_with_http_request("AddPlaceApi","POST");
		m.the_API_call_is_successs_with_status_code(200);
		m.verify_place_id_created_using_with_using_httpMethod("Vishwanath", "GetPlaceApi");
		}
	}

}
