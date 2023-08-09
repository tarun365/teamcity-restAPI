package com.herokuapp.restfulbooker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class GetBookingInfo{
	
	@Test
	public void getBookingInfo(){
		
		Response response=RestAssured.get("https://restful-booker.herokuapp.com/booking/11");
		response.print();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		 try {
				PrintStream result= new PrintStream(new File("C:\\Users\\amazi\\eclipse-workspace\\rest\\target\\files\\get.json"));
//				System.setOut(result);
				result.print(response.prettyPrint());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		/*byte[] responseAsByteArray=response.asByteArray();
		File targetFileForByteArray=new File("src/main/resources/targetFileForByteArray.json");
		//Files.write(responseAsByteArray,targetFileForByteArray);*/
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
	


