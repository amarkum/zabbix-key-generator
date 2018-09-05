package com.codeunits.zabbix.authkeygen;

import org.json.JSONException;
import org.json.JSONObject;

import com.codeunits.zabbix.authkeygen.controller.LoginDetailsFetch;


public class ZabbixAuthKeyGenerator {
    public static String userID = "";
    public static String password = "";
    public static String loginResponse = "";
    public static String authKey = "";
    public static String name = "";
    public static String surname = "";
    public static String alias = "";
    public static String userid = "";

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Please provide Zabbix UserID and Password\nUsage : java -jar zabbixKeygen UserID Password");
        }

        final String userID = args[0];
        final String password = args[1];

        try {
            loginResponse = LoginDetailsFetch.getLoginDetails(userID, password);
        } catch (JSONException e) {
            System.out.print("Can not Connect to Server\n");
            System.exit(0);
        }

        try {

            JSONObject resultJSON = new JSONObject(loginResponse);
            JSONObject detailsJSON = resultJSON.getJSONObject("result");

            authKey = detailsJSON.getString("sessionid");
            name = detailsJSON.getString("name");
            surname = detailsJSON.getString("surname");
            alias = detailsJSON.getString("alias");
            userid = detailsJSON.getString("userid").toUpperCase();

            System.out.print("Hello! " + surname + "," + name + " | " + alias + "\n");
            System.out.print("Your Auth Key : " + authKey + "\n");

        } catch (JSONException e) {
            System.out.print("Wrong UserID or Password\n");
            System.exit(0);
        }

        System.exit(0);
    }
}
