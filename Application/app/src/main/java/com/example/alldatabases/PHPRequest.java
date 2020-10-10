package com.example.alldatabases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class PHPRequest {
    private URL url;
    String result;
    public PHPRequest(String url) throws MalformedURLException { this.url = new URL(url); }

    private String readStream(InputStream in) throws IOException {
        StringBuilder jsonHtml = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = null;

        while((line = reader.readLine()) != null)
            jsonHtml.append(line);

        reader.close();
        return jsonHtml.toString();
    }

    public String PhPtest(final String data1, final String data2, final String data3,final String data4) {
        try {
            String postData = "lati=" + data1 + "&" + "longi=" + data2 + "&" + "black=" + data3+ "&" + "speed=" + data4;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("#######", "|mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmd");
            if (conn != null) {
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(5000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                Log.i("#######", "|ddddddddddddddddddddddddddddddddddddd");
                outputStream.write(postData.getBytes("UTF-8"));
                Log.i("#######", "|nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                outputStream.flush();
                Log.i("#######", "|eeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                outputStream.close();
                result = readStream(conn.getInputStream());
                Log.i("#######", "|wwwwwwwwwww"+result);
                conn.disconnect();
            }
        } catch (Exception e) {
            Log.i("PHPRequest", "request was failed.");
            e.printStackTrace();
            return null;
        }
        return "ok";
    }
}

