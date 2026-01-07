package com.qa.gorest.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import com.qa.gorest.utils.JsonPathValidator;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    //service urls
    protected static final String GOREST_ENDPOINT = "/public/v2/users/";
    protected static final String RESTFUL_ENDPOINT = "/booking";
    protected static final String AMADEUS_TOKEN_ENDPOINT = "v1/security/oauth2/token";
    protected static final String AMADEUS_FLIGHT_ENDPOINT = "v1/shopping/flight-destinations";



    protected ConfigurationManager cp;
    protected Properties properties;
    protected RestClient restClient;
    protected String baseURI;
    protected JsonPathValidator jp;

    @Parameters({"baseURI"})
    @BeforeClass
    public void setUp(String baseURI) {
        RestAssured.filters(new AllureRestAssured());
        cp = new ConfigurationManager();
        properties = cp.initProp();
        this.baseURI = baseURI;
        jp = new JsonPathValidator();
    }
}
