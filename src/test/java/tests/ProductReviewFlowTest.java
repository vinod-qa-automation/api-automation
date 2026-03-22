package tests;

import base.BaseTest;
import endpoints.ShopperEndpoints;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.*;

public class ProductReviewFlowTest extends BaseTest {

    private static int reviewId;

    private static final int productId = 14916;
 
    //TEST 1 — ADD REVIEW
   

    @Test(priority = 1)
    public void addReviewTest() {

        String requestBody = """
                {
                  "dateTime": "%s",
                  "description": "Very good product",
                  "heading": "Excellent",
                  "rating": 5,
                  "shopperId": %d,
                  "shopperName": "Vinod"
                }
                """.formatted(
                LocalDateTime.now(),
                shopperId
        );

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .queryParam("productId", productId)
                        .body(requestBody)

                .when()
                        .post(ShopperEndpoints.ADD_REVIEW)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),200);

        reviewId =response.jsonPath().getInt("data.reviewId");

        System.out.println( "Review ID created: " + reviewId );
    }

    
   // TEST 2 — GET REVIEWS
   

    @Test( priority = 2, dependsOnMethods = "addReviewTest")
    public void getReviewsByProductTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .pathParam("productId", productId)

                .when()
                        .get(ShopperEndpoints.GET_REVIEWS_BY_PRODUCT)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),200);

        List<Object> reviews =response.jsonPath().getList("data");

        Assert.assertFalse( reviews.isEmpty() );

        System.out.println( "Total reviews: " + reviews.size());
    }

   
   // TEST 3 — UPDATE REVIEW
    

    @Test( priority = 3, dependsOnMethods = "getReviewsByProductTest" )
    public void updateReviewTest() {

        String updateBody = """
                {
                  "dateTime": "%s",
                  "description": "Updated review text",
                  "heading": "Good",
                  "rating": 4,
                  "shopperId": %d,
                  "shopperName": "Vinod"
                }
                """.formatted(
                LocalDateTime.now(),
                shopperId
        );

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .queryParam("productId", productId)
                        .pathParam("reviewId", reviewId)
                        .body(updateBody)

                .when()
                        .put(ShopperEndpoints.UPDATE_REVIEW)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(),200);

        System.out.println("Review updated successfully");
    }

    
   // TEST 4 — DELETE REVIEW
    

    @Test(priority = 4, dependsOnMethods = "updateReviewTest")
    public void deleteReviewTest() {

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .spec(requestSpec)
                        .queryParam("productId", productId)
                        .pathParam("reviewId", reviewId)

                .when()
                        .delete(ShopperEndpoints.DELETE_REVIEW)

                .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals( response.getStatusCode(), 200 );

        System.out.println( "Review deleted successfully");
    }
}
