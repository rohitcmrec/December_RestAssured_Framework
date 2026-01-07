package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CircuitTest extends BaseTest {

    @BeforeMethod
    public void circuitSetup(){
        restClient = new RestClient(properties, baseURI);
    }

    @Test
    public void getCircuitTest(){
        Response response = restClient.get("/f1/circuits/",false);
        List<String> name = jp.readList(response,"$.MRData.CircuitTable.Circuits[?(@.Location.country=='UK')].circuitName");
        Assert.assertTrue(name.contains("Aintree"));

    }
}
