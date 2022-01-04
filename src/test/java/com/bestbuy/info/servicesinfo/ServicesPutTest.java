package com.bestbuy.info.servicesinfo;

import com.bestbuy.model.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ServicesPutTest extends TestBase {

    @Test
    public void updateServicesDataWithPut(){

        ServicesPojo servicesPojo=new ServicesPojo();
        servicesPojo.setName("Web service");

        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("id",15)
                .body(servicesPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

}
