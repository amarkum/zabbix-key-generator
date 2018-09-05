package com.codeunits.zabbix.authkeygen.controller;


import com.codeunits.zabbix.authkeygen.constants.Resources;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginDetailsFetch {
    public static String getLoginDetails(String userID, String password) throws JSONException {

        JSONObject itemIdJsonParent = new JSONObject();

        itemIdJsonParent.put("jsonrpc", "2.0");
        itemIdJsonParent.put("method", "user.login");
        itemIdJsonParent.put("id", 1);

        JSONObject itemIdJsonChild = new JSONObject();
        itemIdJsonChild.put("user", userID);
        itemIdJsonChild.put("password", password);
        itemIdJsonChild.put("userData", "true");
        itemIdJsonParent.put("params", itemIdJsonChild);

        String loginPostJSON = itemIdJsonParent.toString();
        String loginResponse = (URLResponseGetPost.postRequest(Resources.ZABBIX_URL, loginPostJSON));
        return loginResponse;
    }
}
