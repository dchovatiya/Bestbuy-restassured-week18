package com.bestbuy.info.productinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ProductPatchTest extends TestBase {

    @Test
    public void updateStudentWithPatch(){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setType("Realme");

        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("id",8928 )
                .body(storesPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    /*   StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail("primetesting1@gmail.com");

        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("id", 103)
                .body(studentPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();*/
    }
}
