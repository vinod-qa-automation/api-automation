package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class UpdateCartItemTest extends BaseTest {

    @Test
    public void updateCartItemQuantityTest() {

        int itemId = 470398;     
        int productId = 14916;   

        String requestBody = """
                {
                    "productId": 14916,
                    "quantity": 2
                }
                """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("itemId", itemId)
                        .body(requestBody)

                .when()
                        .put(ShopperEndpoints.UPDATE_CART_ITEM)

                .then()
                        .log().all()
                        .extract()
                        .response();

        int statusCode =response.getStatusCode();

        Assert.assertEquals(statusCode, 200);

        String message =response.jsonPath().getString("message");

        Assert.assertEquals(message, "Data Updated");

        int updatedQuantity = response.jsonPath().getInt("data.quantity");

        Assert.assertEquals(updatedQuantity, 2);
    }
}
