package com.hy.dynamicdatasource.util.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SimpleHttpClient
{

    public static HttpResponseResult execute(HttpUriRequest httpUriRequest, Map<String, String> headers) throws IOException {
        if (httpUriRequest != null) {
            if (headers != null) {
                Set<Map.Entry<String, String>> headersSet = headers.entrySet();
                for (Map.Entry<String, String> header : headersSet) {
                    httpUriRequest.addHeader(header.getKey(), header.getValue().toString());
                }
            }
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpUriRequest);
            return new HttpResponseResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        } else {
            return null;
        }
    }

    public static HttpResponseResult get(String url, Map<String, String> headers, Map<String, String> params) throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet();
        if (params != null) {
            StringBuilder paramsSb = new StringBuilder();
            Set<Map.Entry<String, String>> paramsSet = params.entrySet();
            for (Map.Entry<String, String> param : paramsSet) {
                paramsSb.append(param.getKey());
                paramsSb.append("=");
                paramsSb.append(param.getValue());
            }
            if (url.contains("?")) {
                url = url + paramsSb.toString();
            } else {
                url = url + "?" + paramsSb.toString();
            }
        }
        httpGet.setURI(new URI(url));
        return SimpleHttpClient.execute(httpGet, headers);
    }

    public static HttpResponseResult post(String url, Map<String, String> headers, HttpEntity entity) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(url));
        httpPost.setEntity(entity);
        return SimpleHttpClient.execute(httpPost, headers);
    }

    public static HttpResponseResult post(String url, Map<String, String> headers, Map<String, String> params) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(url));
        if (params != null && params.size() > 0) {
            List<NameValuePair> paramsList = new ArrayList<>();
            Set<Map.Entry<String, String>> paramsSet = params.entrySet();
            for (Map.Entry<String, String> param : paramsSet) {
                paramsList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        }
        return SimpleHttpClient.execute(httpPost, headers);
    }

    public static HttpResponseResult post(String url, Map<String, String> headers, String body) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(url));
        httpPost.setEntity(new StringEntity(body));
        return SimpleHttpClient.execute(httpPost, headers);
    }

    public static class SimpleHttpException extends Exception
    {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public SimpleHttpException(Throwable e)
        {
            super(e);
        }

        public SimpleHttpException(String msg)
        {
            super(msg);
        }

    }

    public static class HttpResponseResult
    {
        private final int statusCode;

        private final String responseText;

        public HttpResponseResult(int statusCode, String responseText)
        {
            this.statusCode = statusCode;
            this.responseText = responseText;
        }

        public int getStatusCode()
        {
            return statusCode;
        }

        public String getResponseText()
        {
            return responseText;
        }

    }

}

