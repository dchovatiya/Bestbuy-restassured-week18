package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class StoresDeleteTest extends TestBase
{
    @Test
    public void deleteStoreRecord()
    {
        StoresPojo studentPojo=new StoresPojo();
        Response response=given()
                .header("Content-Type", "application/json")
                .pathParam("id",8923)
                .body(studentPojo)
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();
    }

}
