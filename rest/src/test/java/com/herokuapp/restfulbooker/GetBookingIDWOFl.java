package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import java.util.*;

import io.restassured.response.Response;



public class GetBookingIDWOFl {
	
	@Test
	public void getBookingIDWOFl() {
		
		Response response=RestAssured.get("https://restful-booker.herokuapp.com/booking");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		List<Integer> bookingIds=response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(),"Empty");
		
		
		
		
		
		
		
	}
	

}
