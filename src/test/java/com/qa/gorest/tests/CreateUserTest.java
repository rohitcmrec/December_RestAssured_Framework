package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIStatusCode;
import com.qa.gorest.pojos.User;
import com.qa.gorest.utils.StringUtil;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @BeforeMethod
    public void createUserSetUp(){
        restClient = new RestClient(properties, baseURI);
    }

    @Test
    public void createUserUsingPOJOObject() {
        User user = new User("Shalu", StringUtil.getRandomEmail(), "female", "active");
        Response response = restClient.post(GOREST_ENDPOINT,user, "json",true);
        response.then().assertThat().statusCode(APIStatusCode.CREATED.getCode());
    }

    @Test
    public void createUserUsingPOJOBuilder() {
        User user = User.builder().name("Rohit").email(StringUtil.getRandomEmail()).gender("male").status("active").build();
        Response response = restClient.post(GOREST_ENDPOINT,user, "json",true);
        response.then().assertThat().statusCode(APIStatusCode.CREATED.getCode());
    }

    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"rohit","male","inactive"},
                {"rami","female","inactive"}
        };
    }

    @Test(dataProvider = "data")
    public void createUserUsingDataProvider(String name, String gender, String status) {
        User user = User.builder().name(name).email(StringUtil.getRandomEmail()).gender(gender).status(status).build();
        Response response = restClient.post(GOREST_ENDPOINT,user, "json",true);
        response.then().assertThat().statusCode(APIStatusCode.CREATED.getCode());
    }
}
