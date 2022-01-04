package com.bestbuy.info.storesinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class StoreGetTest extends TestBase
{
    @Test
    public void getAllStoresList()
    {
        Response response=given()
                .when()
                .get();
        response.then().statusCode(200); //validating in then
        response.prettyPrint();

    }
    @Test
    public void getSingleStoreIdInformation()
    {
        Response response=given()
                .pathParam("id",4)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getSingleStoreByType()
    {
        Response response=given()
                .queryParams("type","Off licence")
                .pathParam("id",8922)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
