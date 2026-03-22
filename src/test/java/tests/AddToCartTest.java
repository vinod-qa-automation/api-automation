package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import   base.BaseTest;
import   endpoints.ShopperEndpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class AddToCartTest extends BaseTest
{

	@Test
	public void addProductToCartTest()
	{
		
		 String requestBody = """
	                {   "productId": 14916,
	                     "quantity": 1}""";
		 
		 
		 Response response =
	                given()
	                        .relaxedHTTPSValidation() 
	                        .spec(requestSpec)
	                        .pathParam("shopperId", shopperId)
	                        .body(requestBody)

	                .when()
	                        .post(ShopperEndpoints.ADD_TO_CART)

	                .then()
	                        .log().all()
	                        .extract()
	                        .response();
		 
		 int statusCode = response.getStatusCode();

	        Assert.assertEquals(statusCode, 201);

	        String message = response.jsonPath().getString("message");

	        Assert.assertEquals(message,"Created");
		
	}
	
}
