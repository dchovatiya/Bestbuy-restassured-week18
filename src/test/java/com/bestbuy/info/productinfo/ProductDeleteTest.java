package com.bestbuy.info.productinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductDeleteTest extends TestBase {

    @Test
    public void deleteSingleDeleteInfo() {

        //with Path Parameter
        Response response = given()
                .pathParam("id", 8933)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
