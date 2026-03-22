package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class AddressFlowTest extends BaseTest {

    private static int addressId;

   
    // TEST 1 — ADD ADDRESS
   

    @Test(priority = 1)
    public void addAddressTest() {

        String requestBody = """
                {
                  "buildingInfo": "123",
                  "city": "Bangalore",
                  "country": "India",
                  "landmark": "Near Metro",
                  "name": "Vinod",
                  "phone": "9876543210",
                  "pincode": "560010",
                  "state": "Karnataka",
                  "streetInfo": "MG Road",
                  "type": "Office"
                }
                """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .body(requestBody)

                .when()
                        .post(ShopperEndpoints.ADD_ADDRESS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.getStatusCode(),
                201
        );

        addressId =response.jsonPath().getInt("data.addressId");

        System.out.println("Address ID created: " + addressId);
    }

    
   // TEST 2 — GET ALL ADDRESSES
   
   

    @Test(priority = 2, dependsOnMethods = "addAddressTest")
    public void getAllAddressesTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)

                .when()
                        .get(ShopperEndpoints.GET_ALL_ADDRESSES)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals( response.getStatusCode(),200);

        List<Object> addresses =response.jsonPath().getList("data");

        Assert.assertFalse(addresses.isEmpty());

        System.out.println("Total addresses: " + addresses.size() );
    }

    
  //  TEST 3 — GET ADDRESS BY ID
   
   

    @Test(priority = 3,dependsOnMethods = "getAllAddressesTest")
    public void getAddressByIdTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("addressId", addressId)

                .when()
                        .get(ShopperEndpoints.GET_ADDRESS_BY_ID)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),200);

        int returnedAddressId = response.jsonPath().getInt("data.addressId");

        Assert.assertEquals(returnedAddressId,addressId);

        System.out.println("Address verified: " + returnedAddressId);
    }

    
   // TEST 4 — UPDATE ADDRESS
    

    @Test(priority = 4,dependsOnMethods = "getAddressByIdTest")
    public void updateAddressTest() {

        String updateBody = """
                {
                  "buildingInfo": "456",
                  "city": "Bangalore",
                  "country": "India",
                  "landmark": "Near Mall",
                  "name": "Vinod",
                  "phone": "9876543210",
                  "pincode": "560010",
                  "state": "Karnataka",
                  "streetInfo": "Brigade Road",
                  "type": "Office"
                }
                """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("addressId", addressId)
                        .body(updateBody)

                .when()
                        .put(ShopperEndpoints.UPDATE_ADDRESS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),200);

        System.out.println("Address updated successfully");
    }

  
   
   // TEST 5 — DELETE ADDRESS
   

    @Test(priority = 5, dependsOnMethods = "updateAddressTest" )
    public void deleteAddressTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("addressId", addressId)

                .when()
                        .delete(ShopperEndpoints.DELETE_ADDRESS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(), 204);

        System.out.println( "Address deleted successfully" );
    }
}
