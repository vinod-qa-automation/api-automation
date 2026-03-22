package tests;

import base.BaseTest;
import endpoints.AdminEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class AdminMerchantTest extends BaseTest {

    @BeforeClass
    public void setupAdmin() {

        adminLogin();

    }  
   //  TEST 1 — GET ALL MERCHANTS
     

    @Test(priority = 1)
    public void getAllMerchantsTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(adminRequestSpec)
                        .queryParam("zoneId", merchantZoneId)

                .when()
                        .get(AdminEndpoints.GET_ALL_MERCHANTS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        List<Object> merchants =response.jsonPath().getList("data");

        Assert.assertNotNull(merchants);

        System.out.println("Merchants list fetched successfully");

    }

   
   //  TEST 2 — UPDATE MERCHANT STATUS
     

    @Test(priority = 2)
    public void updateMerchantStatusTest() {

        int merchantId = 373810;   // use real merchant id

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(adminRequestSpec)
                        .pathParam("merchantId", merchantId)
                        .queryParam("status", "APPROVED")

                .when()
                        .patch(AdminEndpoints.UPDATE_MERCHANT_STATUS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode =response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        System.out.println( "Merchant status updated successfully");

    }

  //  TEST 3 — GET MERCHANTS BY STATUS AND ZONE  
    
    @Test(priority = 3)
    public void getMerchantsByStatusAndZoneTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(adminRequestSpec)
                        .queryParam("status", "BLOCKED")
                        .queryParam("zoneId", merchantZoneId)

                .when()
                        .get(AdminEndpoints.GET_MERCHANTS_BY_STATUS_AND_ZONE)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        List<Object> merchants =response.jsonPath().getList("data");

        Assert.assertEquals(statusCode, 200);

        System.out.println( "Merchants fetched by status and zone successfully" );

    }

}
