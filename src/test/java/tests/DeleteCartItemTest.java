package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteCartItemTest extends BaseTest {

    @Test
    public void deleteCartItemTest() {

        int productId = 14916;  

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("productId", productId)

                .when()
                        .delete(ShopperEndpoints.DELETE_CART_ITEM)

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

        Assert.assertEquals(message, "Success");
    }
}
