package com.example.lg.twcircleapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by LG on 2017-09-26.
 */

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://dhxodn88.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userTime, Response.Listener<String> listener) {

        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userTime", userTime);


    }

       @Override
    public Map<String, String> getParams() {
        return parameters;

    }
}



