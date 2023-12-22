package com.dahuaboke.tails.adapter;

import com.dahuaboke.tails.util.CommonUtils;
import com.dahuaboke.tails.util.HTTP;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: dahua
 * date: 2023/12/22 9:42
 */
public class AliTranslateAdapter extends TranslateAdapter {

    private static final String method = "POST";
    private static final String version = "2019-01-02";
    private static final String accept = "application/json";
    private static final String content_type = "application/json;chrset=utf-8";
    private static final String urlStr = "http://mt.cn-hangzhou.aliyuncs.com/api/translate/web/ecommerce";
    private static final SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);

    private String appid;
    private String secret;

    public AliTranslateAdapter(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    @Override
    protected String doTransport(String text) {
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            return null;
        }
        Map<String, String> aliBodyMap = new HashMap();
        aliBodyMap.put("FormatType", "text");
        aliBodyMap.put("SourceLanguage", "auto");
        aliBodyMap.put("TargetLanguage", "zh");
        aliBodyMap.put("SourceText", text);
        aliBodyMap.put("Scene", "title");
        Map<String, String> headers = new HashMap();
        String bodyStr;
        try {
            bodyStr = CommonUtils.obj2Str(aliBodyMap);
        } catch (JsonProcessingException e) {
            return null;
        }
        String bodyMd5 = CommonUtils.MD5Base64(bodyStr);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        String date = df.format(new Date());
        String uuid = UUID.randomUUID().toString();
        String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
                + "x-acs-signature-method:HMAC-SHA1\n"
                + "x-acs-signature-nonce:" + uuid + "\n"
                + "x-acs-version:" + version + "\n"
                + url.getFile();
        String signature = HMACSha1(stringToSign, secret);
        String authHeader = "acs " + appid + ":" + signature;
        headers.put("Accept", accept);
        headers.put("Content-Type", content_type);
        headers.put("Content-MD5", bodyMd5);
        headers.put("Date", date);
        headers.put("Host", url.getHost());
        headers.put("Authorization", authHeader);
        headers.put("x-acs-signature-nonce", uuid);
        headers.put("x-acs-signature-method", "HMAC-SHA1");
        headers.put("x-acs-version", version);
        try {
            String post = HTTP.POST(url, bodyStr, headers);
            Map<String, Object> result = CommonUtils.strToMap(post);
            if ("200".equals(result.get("Code"))) {
                Map<String, String> data = (Map) result.get("Data");
                String size = data.get("WordCount");
                return data.get("Translated");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String HMACSha1(String data, String key) {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            throw new Error("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        AliTranslateAdapter translateAdapter = new AliTranslateAdapter("LTAINVUr128sJEse", "6OnkI4QugJUprIG5YuqufO3xvvHoam");
        String s = translateAdapter.doTransport("i love you");
        System.out.println(s);
    }
}
