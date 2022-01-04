package com.bestbuy.info.categoriesinfo;


import com.bestbuy.model.CategoriesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CategoriesPojoPutTest extends TestBase {

    @Test
    public void updateCategoriesDataWithPut(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Learning Toys");
        categoriesPojo.setId("xyz00100002");
        Response response=given()
                .pathParam("id","abcat0102000")
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
