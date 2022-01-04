package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class StoresPostTest extends TestBase
{
    @Test
    public void createStoreRecord()
    {
        /*HashMap<String,String> serviceList=new HashMap<>();
        HashMap<String,Integer> storeServices=new HashMap<>();
        serviceList.put("id","1");
        serviceList.put("name","BigBang");
        serviceList.put("id","2");
        serviceList.put("name","Charles");
        storeServices.put("storeId",5);
        storeServices.put("serviceId",1);
        storeServices.put("serviceId","6");
        storeServices.put("serviceId","2");*/

        StoresPojo bestBuyPojo=new StoresPojo();
        bestBuyPojo.setName("Glenwood Store");
        bestBuyPojo.setType("Off licence");
        bestBuyPojo.setAddress("14 glenwood");
        bestBuyPojo.setAddress2("Harrow");
        bestBuyPojo.setCity("Wales");
        bestBuyPojo.setState("MN");
        bestBuyPojo.setZip("124563");
        bestBuyPojo.setLat(44.265389);
        bestBuyPojo.setLng(98.256314);
        bestBuyPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        //bestBuyPojo.setServices();
        /*bestBuyPojo.setServices(serviceList);
        bestBuyPojo.setStoreServices(storeServices);*/
        Response response=given()
                .header("Content-Type", "application/json")
                .body(bestBuyPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
