package tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiZadatakTest extends BaseTest {

    private static final String apiKey = "f399f648-e6ca-49b2-8cbd-0f657b3db451";

    @Test
    public void apiGET() {
        RestAssured.baseURI = "https://pro-api.coinmarketcap.com/v1/cryptocurrency";

        RequestSpecification httpRequest = given();
        httpRequest.contentType("application/json");
        httpRequest.header("X-CMC_PRO_API_KEY", apiKey);

        Response response = httpRequest.request(Method.GET, "/info?id=3843");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        JSONObject jsonObjectStatus = new JSONObject(responseBody).getJSONObject("status");
        System.out.println("JSON status string: " + jsonObjectStatus.toString());
        String error_message = jsonObjectStatus.get("error_message").toString();
        System.out.println("Error message => " + error_message);
        Assert.assertEquals(error_message, "null");

        JSONObject jsonObjectData = new JSONObject(responseBody).getJSONObject("data");
        System.out.println("jsonObjectData => " + jsonObjectData.toString());

        JSONObject jsonObjectID = jsonObjectData.getJSONObject("3843");
        System.out.println("jsonObjectID => " + jsonObjectID.toString());

        String symbol = jsonObjectID.getString("symbol");
        System.out.println("Symbol => " + symbol);
        Assert.assertEquals(symbol, "BOLT");

        String dateAdded = jsonObjectID.getString("date_added");
        System.out.println("Date added => " + dateAdded);
        Assert.assertEquals(dateAdded, "2019-04-05T00:00:00.000Z");

        String logo = jsonObjectID.getString("logo");
        System.out.println("Logo => " + logo);
        Assert.assertEquals(logo, "https://s2.coinmarketcap.com/static/img/coins/64x64/3843.png");

        JSONObject jsonObjectURLs = jsonObjectData.getJSONObject("3843").getJSONObject("urls");
        System.out.println("Urls => " + jsonObjectURLs.toString());

        JSONArray urlsArray = jsonObjectURLs.getJSONArray("source_code");
        String source_code = urlsArray.get(0).toString();
        System.out.println("Source code => " + source_code);
        Assert.assertEquals("https://github.com/SyQic-Ops/bolt", source_code);

        JSONArray websiteArray = jsonObjectURLs.getJSONArray("website");
        String website = websiteArray.get(0).toString();
        System.out.println("website => " + website);
        Assert.assertEquals("https://bolt.global/", website);
    }
}