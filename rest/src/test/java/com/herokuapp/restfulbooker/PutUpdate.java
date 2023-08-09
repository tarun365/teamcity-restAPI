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

public class PutUpdate {
	
	@Test
	public void putUpdate() {
		
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
	    		post("https://restful-booker.herokuapp.com/booking ");
	    response.print();
	    
	    int bookingId=response.jsonPath().getInt("bookingid");
	    //System.out.println(bookingId);
	    
	    Assert.assertEquals(response.getStatusCode(), 200,"sdbhkjbckihbrik");
	    
JSONObject matter1=new JSONObject();
		
		matter1.put("firstname","tarun");
		matter1.put("lastname", "gurupalli");
		matter1.put("totalprice",125);
		matter1.put("depositpaid", true);
		
		JSONObject date1=new JSONObject();
		 date1.put("checkin" , "2018-02-23");
	        date1.put("checkout" , "2019-02-26");
	        
	    matter1.put("bookingdates", date1);
	    matter1.put("additionalneeds", "bdvhbifkashbdlher");
	    
	    Response responseNew=RestAssured.given().
	    		auth().preemptive().basic("admin","password123").contentType(ContentType.JSON).body(matter1.toString()).
	    		put("https://restful-booker.herokuapp.com/booking/"+bookingId);
	    responseNew.print();
	    Assert.assertEquals(responseNew.getStatusCode(), 200,"sdbhkjbckihbrik");
	    
	    try {
			PrintStream result= new PrintStream(new File("C:\\Users\\amazi\\eclipse-workspace\\rest\\target\\files\\put.json"));
//			System.setOut(result);
			result.print(responseNew.prettyPrint());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		
	}

}
