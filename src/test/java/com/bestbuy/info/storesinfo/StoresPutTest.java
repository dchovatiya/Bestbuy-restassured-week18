package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class StoresPutTest extends TestBase
{
    @Test
    public void updateStoreRecord()
    {
        StoresPojo bestBuyPojo=new StoresPojo();
        bestBuyPojo.setName("South Town");
        bestBuyPojo.setType("Best Buy");
        bestBuyPojo.setAddress("519 kenton");
        bestBuyPojo.setAddress2("Harrow");
        bestBuyPojo.setCity("London");
        bestBuyPojo.setState("New York");
        bestBuyPojo.setZip("123458");
        bestBuyPojo.setLat(45.698754);
        bestBuyPojo.setLng(12.365478);
        Response response=given()
                .header("Content-Type", "application/json")
                .pathParam("id", 14)
                .body(bestBuyPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
