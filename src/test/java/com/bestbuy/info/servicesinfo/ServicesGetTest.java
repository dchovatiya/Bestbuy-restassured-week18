package com.bestbuy.info.servicesinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class ServicesGetTest extends TestBase {
Response response;
//
@Test
public void getAllServicesData(){
    response=given()
            .when()
            .get();
    response.then().statusCode(200);
    response.prettyPrint();
}
    @Test
    public void getSingleProductsData(){
        response=given()
                .pathParam("id",8)
                .when()
                .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void getProductsDataWithParameters() {

        HashMap<String,Integer> qParam = new HashMap<>();
        qParam.put("$limit",2);
        qParam.put("$skip",3);
        response = given()
                .queryParams(qParam)
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void getServicesDataWithParameter(){

        HashMap<String,Integer> qParam=new HashMap<>();
        qParam.put("$limit",3);
        qParam.put("$skip",2);
        response=given()
                .queryParams(qParam)
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }

}
