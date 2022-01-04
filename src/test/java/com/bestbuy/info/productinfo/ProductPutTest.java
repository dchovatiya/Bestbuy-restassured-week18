package com.bestbuy.info.productinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductPutTest extends TestBase {
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        RestAssured.basePath="/products";
    }
    @Test
    public void updateStoresDetails() {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("mobile");
        storesPojo.setType("mi");
        storesPojo.setAddress("Queensburry");
        storesPojo.setAddress2("hergaroad");
        storesPojo.setCity("London");
        storesPojo.setState("London");
        storesPojo.setZip("HA5 2SA");
        storesPojo.setLat(0);
        storesPojo.setLng(0);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999680)
                .body(storesPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
