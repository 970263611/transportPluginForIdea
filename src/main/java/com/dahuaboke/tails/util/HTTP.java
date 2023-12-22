package com.dahuaboke.tails.util;

import okhttp3.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/12/13 15:42
 */
public class HTTP {

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static String GET(String url, Map<String, String> headers) throws Exception {
        Request.Builder builder = new Request.Builder();
        if (ObjectUtils.isNotEmpty(headers)) {
            headers.forEach((k, v) -> {
                builder.header(k, v);
            });
        }
        Request request = builder.get().url(url.toString()).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.code() == 200) {
            return response.body().string();
        }
        throw new Exception(response.message());
    }

    public static String POST(String url, String body, Map<String, String> headers) throws Exception {
        return POST(url, body, headers, null);
    }

    public static String POST(URL url, String body, Map<String, String> headers) throws Exception {
        return POST(url, body, headers, null);
    }

    public static String POST(String url, String body, Map<String, String> headers, String contextType) throws Exception {
        return POST(new URL(url), body, headers, contextType);
    }

    public static String POST(URL url, String body, Map<String, String> headers, String contextType) throws Exception {
        Request.Builder builder = new Request.Builder();
        if (ObjectUtils.isNotEmpty(headers)) {
            headers.forEach((k, v) -> {
                builder.header(k, v);
            });
        }
        RequestBody requestBody;
        if (StringUtils.isEmpty(contextType)) {
            requestBody = RequestBody.create(body.getBytes());
        } else {
            MediaType mediaType = MediaType.get(contextType);
            requestBody = RequestBody.create(body.getBytes(), mediaType);
        }
        Request request = builder.post(requestBody).url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.code() == 200) {
            return response.body().string();
        }
        throw new Exception(response.message());
    }
}
