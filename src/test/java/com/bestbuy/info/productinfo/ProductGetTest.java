package com.bestbuy.info.productinfo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class ProductGetTest {
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        RestAssured.basePath="/products";
    }
//
    @Test
    public void getAllProducts() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getSingleProducts() {
     /*   Response response = given()
                .when()
                .get("/3");
        response.then().statusCode(200);
        response.prettyPrint();*/
        //with Path Parameter
        Response response = given()
                .pathParam("id", 43900)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void searchProductsWithParameter() {
        HashMap<String,Object> qParams=new HashMap<>();
        qParams.put("price",5.49);
        qParams.put("model","MN2400B4Z");

        Response response = given()
           /*     .queryParam("programme", "Financial Analysis")
                .queryParam("limit", 2)*/
                .queryParams(qParams)
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }




}
