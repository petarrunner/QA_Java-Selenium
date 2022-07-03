package tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class apiTest {

    @Test
    public void apiGET() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/users");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        System.out.println("status code: " + response.getStatusCode());
        Assert.assertEquals("Status code is not 200", 200, response.getStatusCode());
    }

    @Test
    public void apiGetDummy() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";

        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/employees");

        String responseBody = response.getBody().asString();
        System.out.println("ResponseBody: " + responseBody);
        System.out.println("Response code: " + response.getStatusCode());
    }

    @Test
    public void apiPOST() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";

        JSONObject requestParams = new JSONObject();
        requestParams.put("employee_name", "Petar");
        requestParams.put("employee_salary", "70000");
        requestParams.put("employee_age", "31");

        System.out.println(requestParams);

        RequestSpecification request = RestAssured.given();

        request.body(request.toString());
        request.contentType("application/json");

        try {
            Response response = request.post("/create");

            int statusCode = response.getStatusCode();
            System.out.println("status code:" + statusCode);
            Assert.assertEquals(200, statusCode);
            System.out.println(response.asString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
