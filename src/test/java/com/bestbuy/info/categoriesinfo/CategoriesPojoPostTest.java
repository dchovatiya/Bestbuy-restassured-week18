package com.bestbuy.info.categoriesinfo;

import com.bestbuy.model.CategoriesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CategoriesPojoPostTest extends TestBase {
    @Test
    public void createCategoriesData(){

        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Birthdays");
        categoriesPojo.setId("hpuro0019999");
        Response response=given()
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

}
