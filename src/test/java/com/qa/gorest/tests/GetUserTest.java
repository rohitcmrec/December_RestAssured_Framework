package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIStatusCode;
import com.qa.gorest.pojos.User;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserTest extends BaseTest {

    @BeforeMethod
    public void getUserSetUp(){
        restClient = new RestClient(properties, baseURI);
    }

    @Test
    public void getAllUser() {
        Response response = restClient.get(GOREST_ENDPOINT,true);
        response.then().assertThat().statusCode(APIStatusCode.OK.getCode());
        List<User> responseUserList = response.as(new TypeRef<List<User>>() {});
        System.out.println(responseUserList.get(0).getEmail());
    }

    @Test
    public void getUser() {
        Response response = restClient.get(GOREST_ENDPOINT+"8115421",true);
        response.then().assertThat().statusCode(APIStatusCode.OK.getCode());
    }

    @Test
    public void getUserWithQueryParams() {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name","rohit");
        queryParams.put("status","active");
        Response response = restClient.get(GOREST_ENDPOINT,null,queryParams,true);
        response.then().assertThat().statusCode(APIStatusCode.OK.getCode());
    }
}
