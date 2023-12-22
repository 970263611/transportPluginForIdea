package com.dahuaboke.tails.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * author: dahua
 * date: 2023/12/13 15:49
 */
public class CommonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static Map<String, Object> strToMap(String jsonStr) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, Map.class);
    }

    public static String obj2Str(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static String sign(String s, String key, String method) throws Exception {
        Mac mac = Mac.getInstance(method);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("utf-8"), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(s.getBytes("utf-8"));
        return DatatypeConverter.printBase64Binary(hash);
    }

    public static String MD5Base64(String s) {
        if (s == null)
            return null;
        String encodeStr = "";
        byte[] utfBytes = s.getBytes();
        MessageDigest mdTemp;
        try {
            mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(utfBytes);
            byte[] md5Bytes = mdTemp.digest();
            encodeStr = Base64.encodeBase64String(md5Bytes);
        } catch (Exception e) {
            throw new Error("Failed to generate MD5 : " + e.getMessage());
        }
        return encodeStr;
    }
}
