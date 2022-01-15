package org.example;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Client.RestClient;
import org.Data.TestData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.baseTest.Base;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends Base{
    Base base;
    String serviceURL;
    String apiURL;
    String URL;
    RestClient restclient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        base = new Base();
        serviceURL = prop.getProperty("URL");
        apiURL = prop.getProperty("PostserviceURL");
        URL = serviceURL + apiURL;

    }

    @Test
    public void postApiTest() throws IOException {
        restclient = new RestClient();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        //add json object

        ObjectMapper mapper = new ObjectMapper();
        TestData testData= new TestData("Soumya","VP");

        //object file to JSON

        mapper.writeValue(new File("D:\\Projects\\RestAPI\\src\\main\\java\\org\\Data\\User.json"), testData);


        // print on console

        String jsonString= mapper.writeValueAsString(testData);
        System.out.println("JSON String is ----->" + jsonString );



        closeableHttpResponse = restclient.post(URL, jsonString,headers);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is ----->" + statusCode);
        Assert.assertEquals(statusCode, STATUS_CODE_201);


        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("JSon response is " + jsonObject);

        // json to java object

       TestData testDataObj=  mapper.readValue(responseString, TestData.class);
        System.out.println(testData.getName().equalsIgnoreCase(testDataObj.getName()));
        System.out.println(testData.getJob().equalsIgnoreCase(testDataObj.getJob()));





    }
}
