package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;
import com.qa.gorest.frameworkException.APIFrameworkException;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {

    public <T> T read(Response response, String jsonPath) {
        String jsonResponse = response.getBody().asString();
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (JsonPathException e) {
            e.printStackTrace();
            throw new APIFrameworkException("Invalid JsonPath provided");
        }
    }

    public <T> List<T> readList(Response response, String jsonPath) {
        String jsonResponse = response.getBody().asString();
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (JsonPathException e) {
            e.printStackTrace();
            throw new APIFrameworkException("Invalid JsonPath provided");
        }
    }

    public <T> List<Map<T,T>> readMap(Response response, String jsonPath) {
        String jsonResponse = response.getBody().asString();
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (JsonPathException e) {
            e.printStackTrace();
            throw new APIFrameworkException("Invalid JsonPath provided");
        }
    }
}
