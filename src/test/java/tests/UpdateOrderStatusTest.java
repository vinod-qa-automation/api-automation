package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateOrderStatusTest extends BaseTest {

    @Test(priority = 2)
    public void updateOrderStatusTest() {

        int orderId = CreateOrderTest.orderId;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("orderId", orderId)
                        .queryParam("status", "DELIVERED")

                .when()
                        .patch(ShopperEndpoints.UPDATE_ORDER_STATUS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        String message = response.jsonPath().getString("message");

        Assert.assertEquals(message, "OK");

        System.out.println("Order status updated successfully for Order ID: "+ orderId );
    }
}
