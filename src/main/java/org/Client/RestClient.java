package org.Client;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RestClient {

    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
        return closeableHttpResponse;

    }

    public CloseableHttpResponse get(String url, Map<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        for (Map.Entry<String, String> entry: headerMap.entrySet())
        {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
        return closeableHttpResponse;

    }

    public CloseableHttpResponse post(String url, String entityDetails, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(entityDetails));
        for (Map.Entry<String, String> entry: headerMap.entrySet())
        {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        return closeableHttpResponse;

    }

    public CloseableHttpResponse put(String url, String entityDetails, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setEntity(new StringEntity(entityDetails));
        for (Map.Entry<String, String> entry: headerMap.entrySet())
        {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPut);
        return closeableHttpResponse;

    }


}
