package com.bestbuy.assertionexample;

import com.bestbuy.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * By Dimple Patel
 **/
public class AssertionExampleDemo
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response=given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Verify that if the total is equal to 1561
    @Test
    public void test001()
    {
        //response.body("total",equalTo(1561));
        response.body("total",equalTo(1559));

    }
    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void test002()
    {
        response.body("limit",equalTo(10));
    }
    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test003()
    {
        response.body("data.name",hasItem("Inver Grove Heights"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004()
    {
        response.body("data.name",hasItems("Roseville", "Burnsville", "Maplewood"));
    }
    //5. Verify the store id=7 inside store services of the third store of second services
    @Test
    public void test005()
    {
        //response.body("data[2].services[2]",hasKey("storeservices"),hasItem(hasEntry("storeId",7)));
        response.body("data[2].services[2].storeservices",hasEntry("storeId",7));
        //response.body("data.findAll{it.services[3]",hasItem(hasEntry("storeId",7)));
    }
    //6. Check hash map values ‘createdAt’ inside store services map where store name = Roseville
    @Test
    public void test006()
    {
        //json evaluator-->$.data[2].services[*].storeservices.createdAt
        response.body("data[2].services[1].storeservices",hasEntry("createdAt","2016-11-17T17:57:09.417Z"));
        //response.body("data[2].services[*].storeservices",hasEntry("createdAt","2016-11-17T17:57:09.417Z"));
    }
    //7. Verify the state = MN of forth store
    @Test
    public void test007()
    {
        //json evaluator-->$.data[0].state
        response.body("data[0]",hasEntry("state","MN"));
    }
    //8. Verify the store name = Rochester of 9th store //no data like Rochester
    //8. Verify the store name = Cedar Rapids  of 8th store

    @Test
    public void test008()
    {
        response.body("data[9]",hasEntry("name","Cedar Rapids"));
    }
    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void test009()
    {
        //$.data[5].services[1]
        response.body("data[5].services[1].storeservices",hasEntry("storeId",11));
    }
    // 10. Verify the serviceId = 4 for the 7th store of forth service
    // 10. Verify the storeId = 4 for the 7th store of forth service
    // changed serviceID to storeID=4

    @Test
    public void test0010()
    {
        //$.data[0].services[4]
        response.body("data[0].services[4].storeservices",hasEntry("storeId",4));

    }
}
