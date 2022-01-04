package com.bestbuy.info.servicesinfo;

import com.bestbuy.model.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ServicesPostTest extends TestBase {
    @Test
    public void createServiceData(){

        ServicesPojo servicesPojo=new ServicesPojo();
        servicesPojo.setName("Best Mobile");

        Response response=given()
                .header("Content-Type","application/json")
                .body(servicesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();

    }
}
