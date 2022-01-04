package com.bestbuy.info.servicesinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ServicesDeleteTest extends TestBase {

    @Test
    public void deleteServicesData(){

        Response response=given()
                .pathParam("id",20)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
