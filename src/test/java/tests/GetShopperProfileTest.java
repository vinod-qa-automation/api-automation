package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class GetShopperProfileTest extends BaseTest {

    @Test
    public void getShopperProfile()
    {
        given()
            .relaxedHTTPSValidation()
            .header("Authorization","Bearer " + token )
            .pathParam("shopperId", shopperId )
        .when()
            .get(ShopperEndpoints.GET_SHOPPER_PROFILE )
        .then()
            .log().all()
            .statusCode(200)
            .body("message", equalTo("Success"))
            .body("data.email", equalTo("anupam@gmail.com"))
            .body("data.role",equalTo("SHOPPER"))
            .body("data.status", equalTo("ACTIVE"))
            .body("data.userId",equalTo(shopperId));
    }
}
