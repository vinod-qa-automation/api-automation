package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class CreateOrderTest extends BaseTest {

    public static int orderId;  

    @Test(priority = 1)
    public void createOrderTest() {

        String requestBody = """
        {
          "address": {
            "addressId": 150989,
            "buildingInfo": "BTM Layout",
            "city": "Bengaluru",
            "country": "India",
            "landmark": "Near Silk Board",
            "name": "Anupam",
            "phone": "9876543210",
            "pincode": "560010",
            "state": "Karnataka",
            "streetInfo": "2nd Stage",
            "type": "Home"
          },
          "paymentMode": "COD"
        }
        """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .body(requestBody)

                .when()
                        .post(ShopperEndpoints.CREATE_ORDER)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 201);

        String message = response.jsonPath() .getString("message");

        Assert.assertEquals(message, "Created");

        orderId = response.jsonPath().getInt("data.orderId");

        Assert.assertTrue(orderId > 0);

        System.out.println("Created Order ID: " + orderId);
    }
}
