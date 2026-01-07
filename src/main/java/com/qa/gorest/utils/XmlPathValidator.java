package com.qa.gorest.utils;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class XmlPathValidator {

    private XmlPath getXmlPath(Response response) {
        String responseBody = response.getBody().asString();
        return new XmlPath(responseBody);
    }

    public <T> T read(Response response, String xmlPathExpression) {
        XmlPath xmlPath = getXmlPath(response);
        return xmlPath.get(xmlPathExpression);
    }

    public <T> List<T> readList(Response response, String xmlPathExpression) {
        XmlPath xmlPath = getXmlPath(response);
        return xmlPath.get(xmlPathExpression);
    }

    public <T> List<Map<T, T>> readMap(Response response, String xmlPathExpression) {
        XmlPath xmlPath = getXmlPath(response);
        return xmlPath.get(xmlPathExpression);
    }
}
