package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RestfulBooker extends BaseTest {

    @BeforeMethod
    public void reqSetUp(){
        restClient = new RestClient(properties, baseURI);
    }

    @Test
    public void getAllBookingIds(){
        Response response = restClient.get(RESTFUL_ENDPOINT,false);
        response.then().assertThat().statusCode(200);

        List<Integer> ids = jp.readList(response,"$..bookingid");
        System.out.println(ids);
    }
}
