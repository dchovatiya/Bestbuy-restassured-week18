package com.bestbuy.info.servicesinfo;

import com.bestbuy.model.ServicesPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ServicesPatchTest extends TestBase {

    @Test
    public void updateServicesWithPatch(){

        ServicesPojo servicesPojo=new ServicesPojo();
        servicesPojo.setName("Api service");

        Response response=given()
                .header("Content-Type","application/json")
                .pathParam("id",15)
                .body(servicesPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
