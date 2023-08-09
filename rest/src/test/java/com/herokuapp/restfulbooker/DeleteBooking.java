package com.herokuapp.restfulbooker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteBooking {
	
	@Test
	public void deleteBooking() {
JSONObject matter=new JSONObject();
		
		matter.put("firstname","gurupalli");
		matter.put("lastname", "tarun");
		matter.put("totalprice",123);
		matter.put("depositpaid", false);
		
		JSONObject date=new JSONObject();
		 date.put("checkin" , "2018-01-01");
	        date.put("checkout" , "2019-01-01");
	        
	    matter.put("bookingdates", date);
	    matter.put("additionalneeds", "bdvhbifkashbdlher");
	    
	    Response response=RestAssured.given().contentType(ContentType.JSON).body(matter.toString()).
	    		post("https://restful-booker.herokuapp.com/booking");
	    response.print();
	    
	    int bookingId=response.jsonPath().getInt("bookingid");
	    Response responseDel=RestAssured.given().auth().preemptive().basic("admin","password123").
	    		delete("https://restful-booker.herokuapp.com/booking"+bookingId);
	    responseDel.print();
	    
	    Assert.assertEquals(responseDel.getStatusCode(), 201,"ugkgki");
	    
	    try {
			PrintStream result= new PrintStream(new File("C:\\Users\\amazi\\eclipse-workspace\\rest\\target\\files\\delete.json"));
//			System.setOut(result);
			result.print(responseDel.prettyPrint());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		
	}

}
