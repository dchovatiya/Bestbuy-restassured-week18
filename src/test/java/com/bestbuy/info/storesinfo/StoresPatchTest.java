package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class StoresPatchTest extends TestBase
{
    @Test
    public void updateStoreRecordWithPatch()
    {
        StoresPojo bestBuyPojo=new StoresPojo();
        bestBuyPojo.setCity("Bombay");
        Response response=given()
                .header("Content-Type", "application/json")
                .pathParam("id", 14)
                .body(bestBuyPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void patchStore() {

        StoresPojo bestBuyPojo = new StoresPojo();

/*Pojo is not working so we need to use String variable with required data field and value
        bestBuyPojo.setAddress("104 HeadStone Dr");
        bestBuyPojo.setHours("Mon: 10-11; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-10");*/

        String bodyData = "{\n  \"address\": \"104 HeadStone Dr\",\n  \"hours\": \"Mon: 10-11; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-10\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("id",8921)
                .body(bodyData)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
