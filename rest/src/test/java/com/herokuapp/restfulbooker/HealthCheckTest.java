package com.herokuapp.restfulbooker;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;


public class HealthCheckTest {
	
	@Test
	public void healthCheckTest() {
		
		given().
		when().
		 get("https://restful-booker.herokuapp.com/ping").
		
		then(). 
		assertThat().
		statusCode(201);
		
	}

}
