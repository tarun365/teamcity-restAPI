package com.herokuapp.restfulbooker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostCreate {
	
	
	@Test
	public void postCreate() {
		
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
	    
	    Assert.assertEquals(response.getStatusCode(), 200,"Status is not 200");
	    
	    SoftAssert softAssert=new SoftAssert();
	    String actualFirstName=response.jsonPath().getString("booking.firstname");
	    softAssert.assertEquals(actualFirstName, "gurupalli", "   firstname  in response is not expected");
	    
	    String actualLastName=response.jsonPath().getString("booking.lastname");
	    softAssert.assertEquals(actualLastName, "tarun", "  lastname   in response is not expected");
	    
	    int price=response.jsonPath().getInt("booking.totalprice");
	    softAssert.assertEquals(price, 123, " price in response is not expected");
	    
	    boolean depositPaid=response.jsonPath().getBoolean("booking.depositpaid");
	    softAssert.assertFalse(depositPaid, " deposit paid is not false");
	    
	    String checkInDate=response.jsonPath().getString("booking.bookingdates.checkin");
	    softAssert.assertEquals(checkInDate, "2018-01-01", "  checkin   in response is not expected");
	    
	    String checkOutDate=response.jsonPath().getString("booking.bookingdates.checkout");
	    softAssert.assertEquals(checkOutDate, "2019-01-01", "check out in response is not expected");
	    
	    String additional=response.jsonPath().getString("booking.additionalneeds");
	    softAssert.assertEquals(additional, "bdvhbifkashbdlher", " additional in response is not expected");
	    
	    softAssert.assertAll();
	    try {
			PrintStream result= new PrintStream(new File("C:\\Users\\amazi\\eclipse-workspace\\rest\\target\\files\\post.json"));
//			System.setOut(result);
			result.print(response.prettyPrint());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
	    
		
		
		
		
		
	}

}
