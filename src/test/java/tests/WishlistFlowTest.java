package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class WishlistFlowTest extends BaseTest {

    private static int productId;

   //ADD TO WISHLIST

    @Test(priority = 1)
    public void addProductToWishlistTest() {

        productId = 14916;

        String requestBody = """
                {
                    "productId": 14916,
                    "quantity": 1
                }
                """;

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .body(requestBody)

                .when()
                        .post(ShopperEndpoints.ADD_TO_WISHLIST)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals( response.getStatusCode(),201 );

        String message = response.jsonPath().getString("message");

        Assert.assertTrue( message.equals("Created") ||message.equals("Success"));

        System.out.println("Stored Product ID: " + productId );
    }

    
     
     // GET WISHLIST
    

    @Test( priority = 2,dependsOnMethods = "addProductToWishlistTest")
    public void getWishlistTest() {

        System.out.println("Product ID from Add test: " + productId);

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)

                .when()
                        .get(ShopperEndpoints.GET_WISHLIST)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals( response.getStatusCode(), 200 );

        List<Object> wishlistItems =response.jsonPath() .getList("data");

        Assert.assertNotNull( wishlistItems,"Wishlist should not be null" );

        Assert.assertFalse(wishlistItems.isEmpty(), "Wishlist should not be empty");

        System.out.println("Wishlist size: " + wishlistItems.size());

        int returnedProductId = response.jsonPath().getInt("data[0].productId");

        Assert.assertEquals(returnedProductId, productId);

        System.out.println("Product verified in wishlist: " + returnedProductId);
    }

   
     
    // DELETE WISHLIST ITEM
    

    @Test( priority = 3, dependsOnMethods = "getWishlistTest")
    public void deleteWishlistItemTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("shopperId", shopperId)
                        .pathParam("productId", productId)

                .when()
                        .delete(ShopperEndpoints.DELETE_FROM_WISHLIST)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),204);

        System.out.println("Product deleted from wishlist: "+ productId);
    }
}
