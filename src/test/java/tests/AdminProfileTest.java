package tests;

import base.BaseTest;
import endpoints.AdminEndpoints;

import  io.restassured.response.Response;

import  org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class AdminProfileTest extends BaseTest
{
  
	@BeforeClass
	public void setupAdmin()
	{
		adminLogin();
	}
	
	//TEST 1 — GET ADMIN DETAILS
	
	@Test(priority = 1)
	public void getAdminByIdTest()
	{
		
	Response response =
		given()
		      .relaxedHTTPSValidation()
		      .spec(adminRequestSpec)
		      .pathParam("adminId",adminId)
	   .when()
	          .get(AdminEndpoints.GET_ADMIN_BY_ID)
	   .then()
	          .log().all()
	          .extract().response();
		
	int statusCode=response.getStatusCode();
	
	Assert.assertEquals(statusCode, 200);
	
	int returnedAdminId=response.jsonPath().getInt("data.userId");
	
	Assert.assertEquals(returnedAdminId, adminId);
	
	System.out.println( "Admin details verified successfully");
		      
	}
	
	
	// TEST 2 — UPDATE ADMIN DETAILS
	
	@Test(priority = 2)
	public void updateAdminByIdTest()
	{
		String updateBody= """
                {
                "city": "Bangalore",
                "country": "India",
                "email": "deepakgowda@gmail.com",
                "firstName": "Deepak",
                "lastName": "Gowda",
                "gender": "MALE",
                "phone": "8589869585",
                "role": "ADMIN",
                "state": "Karnataka",
                "status": "ACTIVE",
                "zoneId": "ALPHA"
              }
              """;
		
	  Response response = given()
		       .relaxedHTTPSValidation()
		       .spec(adminRequestSpec)
		       .pathParam("adminId", adminId)
		       .body(updateBody)
		.when()
		       .put(AdminEndpoints.UPDATE_ADMIN_BY_ID)
		 .then()
		        .log().all()
		        .extract().response();
	  
	  int statusCode=response.getStatusCode();
	  
	  Assert.assertEquals(statusCode,200);
	  
	  System.out.println("Admin profile updated successfully");		  
	}
	
}
