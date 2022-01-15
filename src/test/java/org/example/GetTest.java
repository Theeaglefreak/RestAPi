package org.example;

import org.Client.RestClient;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.baseTest.Base;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utils.TestUtils;

import java.io.IOException;
import java.util.HashMap;


public class GetTest extends Base {

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
        apiURL = prop.getProperty("ServiceURL");
        URL = serviceURL + apiURL;


    }

    @Test
    public void getCall() throws IOException {
        restclient = new RestClient();
        closeableHttpResponse = restclient.get(URL);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is ----->" + statusCode);
        Assert.assertEquals(statusCode, STATUS_CODE_200);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("JSon response is " + jsonObject);
        Header[] header = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeader = new HashMap<>();
        for (Header headerName : header
        ) {
            allHeader.put(headerName.getName(), headerName.getValue());
        }

        System.out.println("all headers are  " + allHeader);


    }


    @Test(priority = 1)
    public void getCallWithHeaders() throws IOException {
        restclient = new RestClient();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        closeableHttpResponse = restclient.get(URL,headers);


        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("status code is ----->" + statusCode);
        Assert.assertEquals(statusCode, STATUS_CODE_200);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API---> " + responseJson);

        //per_page:
        String perPageValue = TestUtils.getValueByJPath(responseJson, "/per_page");
        System.out.println("value of per page is-->" + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);

        //total:
        String totalValue = TestUtils.getValueByJPath(responseJson, "/total");
        System.out.println("value of total is-->" + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        //get the value from JSON ARRAY:
        String lastName = TestUtils.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = TestUtils.getValueByJPath(responseJson, "/data[0]/id");
        String avatar = TestUtils.getValueByJPath(responseJson, "/data[0]/avatar");
        String firstName = TestUtils.getValueByJPath(responseJson, "/data[0]/first_name");

        System.out.println(lastName);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(firstName);


        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("JSon response is " + jsonObject);
        Header[] header = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeader = new HashMap<>();
        for (Header headerName : header
        ) {
            allHeader.put(headerName.getName(), headerName.getValue());
        }

        System.out.println("all headers are  " + allHeader);


    }

}
