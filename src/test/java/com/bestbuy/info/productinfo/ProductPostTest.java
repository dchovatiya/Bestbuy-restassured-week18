package com.bestbuy.info.productinfo;

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductPostTest {
    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        RestAssured.basePath="/products";
    }

    @Test
    public void createNewProductsRecord(){

        ProductPojo productsPojo=new ProductPojo();
        productsPojo.setName("mobile");
        productsPojo.setType("Hardware");
        productsPojo.setPrice(40.5);
        productsPojo.setUpc("041333430111");
        productsPojo.setShipping(1);
        productsPojo.setDescription("useful for gamer");
        productsPojo.setManufacturer("iphone");
        productsPojo.setModel("i2526");
        productsPojo.setUrl("https://www.amazon.co.uk/Apple-iPhone-13-Pro-1TB/dp/B09G9489JR?ref_=ast_sto_dp&th=1&psc=1");
        productsPojo.setImage("https://www.amazon.co.uk/Apple-iPhone-13-Pro-1TB/dp/B09G9489JR?ref_=ast_sto_dp&th=1&psc=1");

        Response response =given()
                .header("Content-Type","application/json")
                .body(productsPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

}
