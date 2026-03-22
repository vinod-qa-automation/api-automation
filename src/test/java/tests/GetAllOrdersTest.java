package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class GetAllOrdersTest extends BaseTest {

    @Test
    public void getAllOrdersTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)

                .when()
                        .get(ShopperEndpoints.GET_ALL_ORDERS)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode =
                response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        String message =
                response.jsonPath()
                        .getString("message");

        Assert.assertTrue(
                message.equals("OK") ||
                message.equals("Success")
        );

        List<Object> orders =
                response.jsonPath()
                        .getList("data");

        if (orders == null) {
            System.out.println("No orders found");
        } else {
            System.out.println("Total Orders: " + orders.size());
        }
    }
}