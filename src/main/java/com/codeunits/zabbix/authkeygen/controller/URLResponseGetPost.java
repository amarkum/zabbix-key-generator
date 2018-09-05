package com.codeunits.zabbix.authkeygen.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLResponseGetPost {

    public static String postRequest(final String uri, final String json) {
        try {
            final URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
            return response.toString();

        } catch (final MalformedURLException e) {
            System.out.println("Not a proper URL");

        } catch (final IOException e) {
            System.out.println("Failed to connect to Internet");
            System.exit(0);
        }
        return null;
    }

}

