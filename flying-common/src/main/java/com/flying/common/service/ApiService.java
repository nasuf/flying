package com.flying.common.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flying.common.bean.HttpResult;

/**
 * 负责和外部接口对接，发起http请求
 * 
 */
@Service
public class ApiService {

    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    @Autowired(required = false)
    private RequestConfig requestConfig;

    /**
     * 执行DoGET请求
     * 
     * @param url
     * @return 如果响应是200，返回具体的响应内容，其他响应返回null
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doGet(String url) throws ClientProtocolException, IOException {
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return null;
    }

    /**
     * 带有参数的GET请求
     * 
     * @param url
     * @param param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */
    public String doGet(String url, Map<String, Object> param) throws ClientProtocolException, IOException,
            URISyntaxException {
        // 定义请求的参数
        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
        }
        return doGet(uriBuilder.build().toString());
    }

    /**
     * 指定POST请求
     * 
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPost(String url, Map<String, Object> param) throws IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (param != null) {
            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体,并且指定参数的编码为UTF-8
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 执行PUT请求
     * 
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPut(String url, Map<String, Object> param) throws IOException {
        // 创建http POST请求
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(this.requestConfig);
        if (param != null) {
            // 设置post参数
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造一个form表单式的实体,并且指定参数的编码为UTF-8
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
            // 将请求实体设置到httpPost对象中
            httpPut.setEntity(formEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPut);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 指定POST请求
     * 
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPostJson(String url, String json) throws IOException {
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(this.requestConfig);
        if (json != null) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 没有参数的post请求
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResult doPost(String url) throws IOException {
        return doPost(url, null);
    }

    /**
     * 执行PUT请求
     * 
     * @param url
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doPut(String url) throws IOException {
        return this.doPut(url, null);
    }

    /**
     * 执行DELETE请求,通过POST提交，_method指定真正的请求方法
     * 
     * @param url
     * @param param 请求参数
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doDelete(String url, Map<String, Object> param) throws IOException {
        param.put("_method", "DELETE");
        return this.doPost(url, param);
    }

    /**
     * 执行DELETE请求(真正的DELETE请求)
     * 
     * @param url
     * @return 状态码和请求的body
     * @throws IOException
     */
    public HttpResult doDelete(String url) throws IOException {
        // 创建http DELETE请求
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpDelete);
            if (response.getEntity() != null) {
                return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                        response.getEntity(), "UTF-8"));
            }
            return new HttpResult(response.getStatusLine().getStatusCode(), null);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
