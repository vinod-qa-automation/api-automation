package tests;

import base.BaseTest;
import endpoints.MerchantEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class MerchantProfileTest extends BaseTest {

    @BeforeClass
    public void setupMerchant() {

        // login as merchant
        merchantLogin();

    }

   
   //  TEST — GET MERCHANT BY ID
     

    @Test
    public void getMerchantByIdTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(merchantRequestSpec)
                        .pathParam("merchantId", merchantId)

                .when()
                        .get(MerchantEndpoints.GET_MERCHANT_BY_ID)

                .then()
                        .log().all()
                        .extract()
                        .response();

       

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

      

        int returnedMerchantId =response.jsonPath() .getInt("data.userId");

        Assert.assertEquals( returnedMerchantId, merchantId);

        System.out.println( "Merchant details fetched successfully" );

    }
    
    
    
   // TEST — UPDATE MERCHANT
    

    @Test(priority = 2)
    public void updateMerchantTest() {

        String updateBody = """
            {
              "city": "Bangalore Rural",
              "commission": 20,
              "company": {
                "address": {
                  "addressId": 151008,
                  "buildingInfo": "No. 55, Outer Ring Road",
                  "city": "Bangalore Rural",
                  "country": "India",
                  "landmark": "Embassy Tech Village",
                  "name": "Office",
                  "phone": "9591658498",
                  "pincode": "560103",
                  "state": "Karnataka",
                  "streetInfo": "Outer Ring Road",
                  "type": "Office"
                },
                "companyId": 19240,
                "email": "pixxel@gmail.com",
                "gstn": "29AAGCR4375J1ZU",
                "name": "Syzygy Space Technologies",
                "phone": "9591658498",
                "registerNumber": "U72200KA2014PTC077308",
                "webAddress": "www.razorpay.com"
              },
              "country": "India",
              "email": "nagabhushan@gmail.com",
              "firstName": "Naga",
              "gender": "male",
              "lastName": "Bhushan",
              "password": "Naga@123",
              "phone": "9998887777",
              "productAdded": 0,
              "productLimit": 10,
              "role": "MERCHANT",
              "state": "Karnataka",
              "status": "ACTIVE",
              "zoneId": "BENGALURU373785"
            }
            """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(merchantRequestSpec)
                        .pathParam("merchantId", merchantId)
                        .body(updateBody)

                .when()
                        .put(MerchantEndpoints.UPDATE_MERCHANT)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        System.out.println("Merchant updated successfully");

    }
}
