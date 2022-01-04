package com.bestbuy.extractingresponsedata;

import gherkin.deps.com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * By Dimple Patel
 **/
public class SearchJsonPathExample {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                //.then().log().all();
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        //$.data[4].name
        String storeNm = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store name is : " + storeNm);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        //$.data[*].name
        List<String> nameList = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + new GsonBuilder().setPrettyPrinting().create().toJson(nameList));
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the storeId of all the stores
    @Test
    public void test005() {
        //$.data[*].services[*]..storeId
        //List<Integer>storeId=response.extract().path("data.services.storeId");
        List<String> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of all store Id is : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeId));
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<String> dataList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + new GsonBuilder().setPrettyPrinting().create().toJson(dataList.size()));
        //System.out.println("List of size are : " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the value of the store where store name = St Cloud(couldn't get the name st cloud)
    //7. Get all the value of the store where store name = Roseville

    @Test
    public void test007()//not working
    {
        List<HashMap<String,?>>values=response.extract().path("data.findAll{it.store=='Roseville'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the store are : " + new GsonBuilder().setPrettyPrinting().create().toJson(values));
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the address of the store where store name = Rochester
    //8. Get the address of the store where store name = Burnsville
    @Test
    public void test008()
    {
        //$.data[3].address
        List<String> address = response.extract().path("data.findAll{it.name=='Burnsville'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Address of the store is : " + new GsonBuilder().setPrettyPrinting().create().toJson(address));
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store
    @Test
    public void test009()
    {
        //$.data[7].services
        List<String> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the services of 8th store is : " + new GsonBuilder().setPrettyPrinting().create().toJson(services));
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get store services of the store where service name = Windows Store
    @Test
    public void test0010()//not working
    {
        //$.data[*].services..storeservices
        //List<HashMap<String, String>> storeServices = response.extract().path("data.services.storeservices.findAll{it.name=='Windows Store'}");
        //List<HashMap<String, String>> storeServices = response.extract().path("data.services.findAll{it.storeservices.findAll{it.name=='Windows Store'}}");
        //List<HashMap<String, String>> storeServices = response.extract().path("data.services.findAll{it.name=='Windows Store'}.storeservices");
        //List<String> service = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
        List<HashMap<String,?>> sList =response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store services of the store Windows Store is : " + new GsonBuilder().setPrettyPrinting().create().toJson(sList));
        System.out.println("------------------End of Test---------------------------");

    }
    //11. Get all the storeId of all the store
    @Test
    public void test0011()
    {
        //$.data[*].services[*]..storeId
        List<Integer> storeID = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId.");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of all store id is  : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeID));
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test0012()
    {
        //$.data[*].services..id
        List<Integer> id = response.extract().path("data.services.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of all store id is  : " + new GsonBuilder().setPrettyPrinting().create().toJson(id));
        System.out.println("------------------End of Test---------------------------");
    }
    //13. Find the store names Where state = ND//no ND so using
    //13. Find the store names Where state = SD
    @Test
    public void test0013()
    {
        //$.data[7].state
        //List<HashMap<String,?>> storeName=response.extract().path("data[7].services.findAll{it.name=='SD'}");
        List<String> storeName=response.extract().path("data.findAll{it.state=='SD'}.services.name");
        //List<String> storeName=response.extract().path("data[7].services.findAll{it.name=='SD'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All store names where state is SD are  : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeName));
        System.out.println("------------------End of Test---------------------------");
    }
    //14. Find the Total number of services for the store where store name = Rochester//not in the list
    //14. Find the Total number of services for the store where store name = Apple Shop
    @Test
    public void test0014()
    {
        List<Integer> totalServices=response.extract().path("data.findAll{it.name=='Apple Shop'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of services for the store  : " + new GsonBuilder().setPrettyPrinting().create().toJson(totalServices.size()));
        System.out.println("------------------End of Test---------------------------");
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015()
    {
        //$.data[*].services..createdAt
        List<String> createdAll=response.extract().path("data.services.findAll{it.name=='Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All store names where state is SD are  : " + new GsonBuilder().setPrettyPrinting().create().toJson(createdAll));
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test0016()
    {
        List<String> names = response.extract().path("data.findAll{it.name=='Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all the services  : " + new GsonBuilder().setPrettyPrinting().create().toJson(names));
        System.out.println("------------------End of Test---------------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void test0017()
    {
        //$.data[*].zip
        List<String> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all the stores are : " + new GsonBuilder().setPrettyPrinting().create().toJson(zip));
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test0018()
    {
        //$.data[2].name
        List<String> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        //List<Integer> zip = response.extract().path("data[2].zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of Roseville is : " + new GsonBuilder().setPrettyPrinting().create().toJson(zip));
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test0019()//null value coming
    {
        //List<HashMap<String, ?>> storeServicesDetails = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.storeservices");
        //List<String> storeServicesDetails = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
        List<HashMap<String, ?>> storeServicesDetails = response.extract().path("data.findAll{it.name=='Magnolia Home Theater'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store service details of Magnolia home theater : " + new GsonBuilder().setPrettyPrinting().create().toJson(storeServicesDetails));
        System.out.println("------------------End of Test---------------------------");
    }
    //20. Find the lat of all the stores
    @Test
    public void test0020()
    {
        //$.data[*].lat
        List<Object> lat=response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("lat of all the stores are  : " + new GsonBuilder().setPrettyPrinting().create().toJson(lat));
        System.out.println("------------------End of Test---------------------------");
    }
}



