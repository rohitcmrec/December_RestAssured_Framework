package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIStatusCode;
import com.qa.gorest.pojos.User;
import com.qa.gorest.utils.StringUtil;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SchemaValidationCreateUser extends BaseTest {

    @BeforeMethod
    public void schemaSetup(){
        restClient = new RestClient(properties, baseURI);
    }

    @Test(description = "create user using POJO")
    public void createUserUsingPOJOObject() {
        User user = new User("Shalu", StringUtil.getRandomEmail(), "female", "active");
        Response response = restClient.post(GOREST_ENDPOINT,user, "json",true);
        response.then().assertThat().statusCode(APIStatusCode.CREATED.getCode())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUser.json"));
    }
}
