package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIStatusCode;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AmadeusTest extends BaseTest {

    private String accessToken;

    @Parameters({"grantType","clientId","clientSecret"})
    @BeforeMethod
    public void amadeusSetUp(String grantType, String clientId, String clientSecret){
        restClient = new RestClient(properties, baseURI);
        accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT,grantType,clientId,clientSecret);
    }

    @Test
    public void getFlightInfoTest() {

        RestClient restClient1 = new RestClient(properties, baseURI);

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("origin", "PAR");
        queryParams.put("maxPrice", 200);

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer "+ accessToken);

        Response response = restClient1.get(AMADEUS_FLIGHT_ENDPOINT, headersMap, queryParams,false);
        response.then().statusCode(APIStatusCode.OK.getCode());

    }

}
