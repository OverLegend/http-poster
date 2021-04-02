package com.nicolorancan.main.utils;


import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.String.format;

public class PostRequester {

    public void SendRequest(String targetURL, String[] paramsName, String[] paramsValue) {

        if (paramsName.length != paramsValue.length) return;

        HttpURLConnection connection = null;

        try {


            Map<String, String> params = new HashMap<>();
            for (int i = 0; i < paramsName.length; i++) {
                params.put(paramsName[i], paramsValue[i]);
            }

            StringBuilder postData = new StringBuilder();

            for (Entry<String, String> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(format(
                        "%s=%s",
                        URLEncoder.encode(param.getKey(), "UTF-8"),
                        URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"))
                )
            }

            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            connection.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

            for (int c; (c = in.read()) >= 0;)
                System.out.print((char)c);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
