package com.bestbuy.info.categoriesinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CategoriesPojoDeleteTest extends TestBase {

    @Test
    public void deleteCategoriesData() {

        Response response = given()
                .pathParam("id", "abcat0102005")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
