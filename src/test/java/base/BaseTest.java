package base;

import endpoints.ShopperEndpoints;
import endpoints.MerchantEndpoints;
import  endpoints.AdminEndpoints;		
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class BaseTest {
	
	//Token
	public static String token;//Shopper Token
	public static String adminToken;
	public static String merchantToken;
     
	
	//IDs
    public static int shopperId;//Shopper Id
    public static int adminId;
    public static int merchantId;
    
    //Merchant Zone Id
    public static String  merchantZoneId;
    
    //Request Specifications
    protected RequestSpecification requestSpec;//Shopper Request Specification
    protected RequestSpecification adminRequestSpec;
    protected RequestSpecification merchantRequestSpec;

    
    
    //Shopper Login  
    
    @BeforeClass
    public void setupAndLogin() {

        RestAssured.baseURI =
                "https://www.shoppersstack.com/shopping";

        JSONObject request = new JSONObject();
        request.put("email", "anupam@gmail.com");
        request.put("password", "Anupam@123");
        request.put("role", "SHOPPER");

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .contentType("application/json")
                        .body(request.toString())

                .when()
                        .post(ShopperEndpoints.LOGIN);

        response.then()
                .log().all()
                .statusCode(200);

        token =
                response.jsonPath()
                        .getString("data.jwtToken");

        shopperId =
                response.jsonPath()
                        .getInt("data.userId");

        System.out.println("Token: " + token);
        System.out.println("ShopperId: " + shopperId);

      

        requestSpec =
                new RequestSpecBuilder()
                        .setContentType("application/json")
                        .addHeader("Authorization", "Bearer " + token)//Authorization: Bearer abc123xyz  and Key   = Authorization  Value = Bearer <token>
                        .build();
    }
    
    
    
    //Admin Login
    
    
    public void adminLogin() 
    {
    
    	JSONObject  request=new JSONObject();
    	
    	request.put("email","deepakgowda@gmail.com");
    	request.put("password","Deepak@123");
    	request.put("role","ADMIN");
    	
    	
    	Response response = given()
    	        .relaxedHTTPSValidation()
    	        .contentType("application/json")
    	        .body(request.toString())
    	.when()
    	        .post(AdminEndpoints.ADMIN_LOGIN);
    
    	
    	response.then()
    	         .log().all()
    	         .statusCode(200);
    	
    	adminToken=
    			response.jsonPath()
    			        .getString("data.jwtToken");
    	
    	adminId=
    			response.jsonPath()
    			        .getInt("data.userId");
    	
    	
    	
    	 System.out.println("Admin Token: " + adminToken);
         System.out.println("AdminId: " + adminId);
        
         
         
        adminRequestSpec=
        		  new RequestSpecBuilder()
        		  .setContentType("application/json")
        		  .addHeader( "Authorization",
                          "Bearer " + adminToken)
        		  .build();
    			 	
    }
    
    
     // Merchant Login
    
    public void merchantLogin()
    {
    	
    	JSONObject request=new JSONObject();
    	           request.put("email","nagabhushan@gmail.com");
    	           request.put("password","Naga@123");
    	           request.put("role", "MERCHANT");
    	           
    	Response response =
    			   given()
    	                  .relaxedHTTPSValidation()
                          .contentType("application/json")
                          .body(request.toString())
    	           .when()
    	                  .post(MerchantEndpoints.MERCHANT_LOGIN);
    	        response.then()
    	                .log().all()
    	                .statusCode(200);
    	        
    	  merchantToken=response.jsonPath().getString("data.jwtToken");
    	  
    	  merchantId=response.jsonPath().getInt("data.userId");
    	  
    	  merchantZoneId=response.jsonPath().getString("data.zoneId");
    	  
    	  System.out.println("Merchant Token: " + merchantToken);
    	  System.out.println("MerchantId: " + merchantId);
    	  System.out.println("Merchant ZoneId: " + merchantZoneId);
    	  
    	  
    	  merchantRequestSpec=
    			   new RequestSpecBuilder()
    			   .setContentType("application/json")
    			   .addHeader("Authorization","Bearer " + merchantToken)
    			   .build();
    	        
    	           
    }
    
}
