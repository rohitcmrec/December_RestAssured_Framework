package com.qa.gorest.client;

import com.qa.gorest.constants.APIStatusCode;
import com.qa.gorest.frameworkException.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class RestClient {

    private RequestSpecBuilder requestSpecBuilder;
    private Properties properties;
    private String baseURI;

    public RestClient(Properties properties, String baseURI) {
        requestSpecBuilder = new RequestSpecBuilder();
        this.properties = properties;
        this.baseURI = baseURI;
    }

    private void addAuthorizationToken() {
        requestSpecBuilder.addHeader("Authorization", "Bearer " + properties.getProperty("token"));
    }

    private void addContentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                requestSpecBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                requestSpecBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                requestSpecBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart":
                requestSpecBuilder.setContentType(ContentType.MULTIPART);
                break;
            default:
                throw new APIFrameworkException("Invalid content type");
        }
    }

    private void addLog() {
        requestSpecBuilder.addFilters(List.of(new RequestLoggingFilter(), new ResponseLoggingFilter()));
    }

    private RequestSpecification createReqSpec(boolean authRequired) {
        requestSpecBuilder.setBaseUri(baseURI);
        if (authRequired) {
            addAuthorizationToken();
        }
        addLog();
        return requestSpecBuilder.build();
    }

    private RequestSpecification createReqSpec(Map<String, String> headersMap, boolean authRequired) {
        requestSpecBuilder.setBaseUri(baseURI);
        addLog();
        if (authRequired) {
            addAuthorizationToken();
        }
        if (headersMap != null) {
            requestSpecBuilder.addHeaders(headersMap);
        }
        return requestSpecBuilder.build();
    }

    private RequestSpecification createReqSpec(Map<String, String> headersMap, Map<String, Object> queryMap, boolean authRequired) {
        requestSpecBuilder.setBaseUri(baseURI);
        addLog();
        if (authRequired) {
            addAuthorizationToken();
        }
        if (headersMap != null) {
            requestSpecBuilder.addHeaders(headersMap);
        }
        if (queryMap != null) {
            requestSpecBuilder.addQueryParams(queryMap);
        }
        return requestSpecBuilder.build();
    }

    private RequestSpecification createReqSpec(Object requestBody, String contentType, boolean authRequired) {
        requestSpecBuilder.setBaseUri(baseURI);
        addLog();
        if (authRequired) {
            addAuthorizationToken();
        }
        if (requestBody != null) {
            requestSpecBuilder.setBody(requestBody);
        }
        addContentType(contentType);
        return requestSpecBuilder.build();
    }

    private RequestSpecification createReqSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean authRequired) {
        requestSpecBuilder.setBaseUri(baseURI);
        addLog();
        if (requestBody != null) {
            requestSpecBuilder.setBody(requestBody);
        }
        if (authRequired) {
            addAuthorizationToken();
        }
        if (headersMap != null) {
            requestSpecBuilder.addHeaders(headersMap);
        }
        addContentType(contentType);
        return requestSpecBuilder.build();
    }

    //http util method

    public Response get(String serviceURL, boolean authRequired) {

        return RestAssured.given().spec(createReqSpec(authRequired)).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headersMap, boolean authRequired) {

        return RestAssured.given().spec(createReqSpec(headersMap,authRequired)).when().get(serviceURL);
    }

    public Response get(String serviceURL, Map<String, String> headersMap, Map<String, Object> queryMap, boolean authRequired) {

        return RestAssured.given().spec(createReqSpec(headersMap, queryMap,authRequired)).when().get(serviceURL);
    }

    public Response post(String serviceURL, Object object, String contentType, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType,authRequired))
                .when().post(serviceURL);
    }

    public Response post(String serviceURL, Object object, String contentType, Map<String, String> headersMap, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType, headersMap,authRequired))
                .when().post(serviceURL);
    }

    public Response put(String serviceURL, Object object, String contentType, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType,authRequired))
                .when().put(serviceURL);
    }

    public Response put(String serviceURL, Object object, String contentType, Map<String, String> headersMap, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType, headersMap,authRequired))
                .when().put(serviceURL);
    }

    public Response patch(String serviceURL, Object object, String contentType, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType,authRequired))
                .when().patch(serviceURL);
    }

    public Response patch(String serviceURL, Object object, String contentType, Map<String, String> headersMap, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(object, contentType, headersMap,authRequired))
                .when().patch(serviceURL);
    }

    public Response delete(String serviceURL, boolean authRequired) {
        return RestAssured.given().spec(createReqSpec(authRequired)).when().delete(serviceURL);
    }

    public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret  ) {
        //1. POST - get the access token
        RestAssured.baseURI = "https://test.api.amadeus.com";

        String accessToken = RestAssured.given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .when()
                .post(serviceURL)
                .then().log().all()
                .assertThat()
                .statusCode(APIStatusCode.OK.getCode())
                .extract().path("access_token");

        System.out.println("access token: " + accessToken);
        return 	accessToken;

    }
}

