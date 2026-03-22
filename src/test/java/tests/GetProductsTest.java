package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetProductsTest extends BaseTest {

    @Test
    public void getAllProducts()
    {
        given()
                .relaxedHTTPSValidation()
                .header("Authorization","Bearer " + token )//Authorization: Bearer abc123xyz  and Key   = Authorization  Value = Bearer <token>
                .queryParam("zoneId","ALPHA")
        .when()
                .get(ShopperEndpoints.GET_PRODUCTS)
        .then()
                .log().all()               
                .statusCode(200)            
                .body("message",equalTo("Success"));
                
    }
}
