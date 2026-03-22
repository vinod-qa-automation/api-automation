package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateShopperProfileTest extends BaseTest {

    @Test
    public void updateShopperProfile()
    {
       
        JSONObject request = new JSONObject();

        request.put("city", "Mysore");
        request.put("country", "India");
        request.put("email", "anupam@gmail.com");
        request.put("firstName", "Anupam");
        request.put("gender", "MALE");
        request.put("lastName", "Mittal");
        request.put("password", "Anupam@123");
        request.put("phone", 9780343578L);
        request.put("state", "Karnataka");
        request.put("zoneId", "ALPHA");

       
        given()
                .relaxedHTTPSValidation()
                .header("Authorization","Bearer " + token)
                .contentType("application/json")
                .pathParam("shopperId",shopperId )
                .body(request.toString())
                .when()
                .patch(ShopperEndpoints.UPDATE_SHOPPER_PROFILE )
        .then()
                .log().all()
                .statusCode(200)              
                .body("message", equalTo("Success"))               
                .body("data.city", equalTo("Mysore"))              
                .body("data.email", equalTo("anupam@gmail.com"))
                .body("data.role", equalTo("SHOPPER"))              
                .body("data.userId", equalTo(shopperId));
    }
}
