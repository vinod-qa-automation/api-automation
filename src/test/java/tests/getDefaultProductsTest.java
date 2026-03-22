package tests;

import  static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import  endpoints.ShopperEndpoints;
import static io.restassured.RestAssured.*;
import base.BaseTest;

public class getDefaultProductsTest extends BaseTest
{
@Test
public void getAllDefaultProducts()
{

	given()
	        .relaxedHTTPSValidation()
	        .header("Authorization","Bearer " + token )
            .queryParam("zoneId","ALPHA")
    .when()
            .get(ShopperEndpoints.GET_DEFAULT_PRODUCTS)
    .then()
             .log().all()
             .and().statusCode(200)
             .body("message",equalTo("Success"))
             .time(lessThan(5000l));	
}
}
