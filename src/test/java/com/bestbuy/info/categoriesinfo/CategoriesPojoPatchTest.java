package com.bestbuy.info.categoriesinfo;

import com.bestbuy.model.CategoriesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CategoriesPojoPatchTest extends TestBase {

    @Test
    public void updateCategoriesDataWithPatch(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Smart TVs");
        Response response=given()
                .pathParam("id","abcat0101001")
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
