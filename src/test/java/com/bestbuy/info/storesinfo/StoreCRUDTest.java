package com.bestbuy.info.storesinfo;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 */
public class StoreCRUDTest extends TestBase {
    static String nameP = "North West London";
    static String name = "North West London";
    static String type = "SupperBig";
    static String address = "104 HeadStone Dr";
    static String address2 = "Main Road";
    static String city = "Harrow";
    static String state = "London";
    static String zip = "51112";
    static double lat = 45.036556;
    static double lng = -93.025986;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeID;

    @Test
    public void test001() {
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
                .body(storesPojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void test002() {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> storeList =
                given()
                        .queryParam("type", type)
                        .queryParam("city", city)
                        .when()
                        .get()
                        .then().log().all()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(storeList);
        storeID = (int) storeList.get("id");
        System.out.println(storeID);
    }

    @Test
    public void test003() {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";
        System.out.println(storeID);
        name = "London Wood";
        hours = "Mon: 10-10; Tue: 10-9; Wed: 10-11; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5";
        name = name + "_Updated";

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

        given()
                .header("Content-Type", "application/json")
                .pathParam("storeId", storeID)
                .body(storesPojo)
                .when()
                .put("/{storeId}")
                .then().log().all().statusCode(200);

        HashMap<String, Object> storeList =
                given()
                        .queryParam("type", type)
                        .queryParam("city", city)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2);
        System.out.println(storeList);
    }

    @Test
    public void test004() {
        given()
                .pathParam("storeID", storeID)
                .when()
                .delete("/{storeID}")
                .then()
                .statusCode(200);
        given()
                .pathParam("storeID", storeID)
                .when()
                .get("/{storeID}")
                .then()
                .statusCode(404);

    }

}