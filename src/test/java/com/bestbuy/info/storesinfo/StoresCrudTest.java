package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import gherkin.deps.com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StoresCrudTest extends TestBase
{

    static String name = "prime testing";
    static String type = "IT Academy";
    static String address = "12 london Road";
    static String address2 = "Harrow street";
    static String city = "London";
    static String state = "West midland";
    static String zip = "HA3 6UR";
    static double lat = 36.586;
    static double lng = 22.365;
    static String hours = "mon to fri :9 to 5";
    static Integer storeId;

    //8928
    @Test
    public void test001Post() {
        StoresPojo bestBuyPojo = new StoresPojo();
        bestBuyPojo.setName(name);
        bestBuyPojo.setType(type);
        bestBuyPojo.setAddress(address);
        bestBuyPojo.setAddress2(address2);
        bestBuyPojo.setCity(city);
        bestBuyPojo.setState(state);
        bestBuyPojo.setZip(zip);
        bestBuyPojo.setLat(lat);
        bestBuyPojo.setLat(lng);
        bestBuyPojo.setHours(hours);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(bestBuyPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002Get() {
        String s1 = "data.findAll{it.name='";
        String s2 = "'}.get(0)";

        HashMap<String, Object> value =
                given()
                        .when()
                        .get()
                        .then().statusCode(200)
                        .extract()
                        .path(s1 + name + s2);

        storeId = (Integer) value.get("id");
        System.out.println("StoreId:" + storeId);
    }

    @Test
    public void test003Patch() {
        String p1 = "data.findAll{it.id='";
        String p2 = "'}.get(0)";

        name = name + "_updated";
        type = type + "_updated";
        address = address2 + "_updated";
        address2 = address2 + "_updated";

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        Response response = given()
                .header("Content-Type", "application/json")
                .pathParam("sId", storeId)
                .body(storesPojo)
                .when()
                .put("/{sId}");
        response.then().log().all().statusCode(200);

        HashMap<String, Object> value =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + storeId + p2);

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(value));
    }
    @Test
    public void test004Delete() {
        Response response = given()
                .pathParam("storeID", storeId)
                .when()
                .delete("/{storeID}");
        response.then().statusCode(200);
        response.prettyPrint();

        Response response1 =
                given()
                        .pathParam("storeID", storeId)
                        .when()
                        .get("/{storeID}");
        response1.then().statusCode(404);

    }
}
