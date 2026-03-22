package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetOrderInvoiceTest extends BaseTest {

    @Test(priority = 3)
    public void getOrderInvoiceTest() {

        int orderId = CreateOrderTest.orderId;

        System.out.println("Order ID: " + orderId);

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("orderId", orderId)

                .when()
                        .get(ShopperEndpoints.GET_ORDER_INVOICE)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode =
                response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        System.out.println(
                "Invoice retrieved successfully"
        );
    }
}