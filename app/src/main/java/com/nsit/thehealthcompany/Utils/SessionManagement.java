package com.nsit.thehealthcompany.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SessionManagement {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManagement(Context context){
        this.context = context;
    }

    public boolean isUserExists(){
        sharedPreferences = context.getSharedPreferences("LoginCredentials",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    public Map<String, String> getLoggedInUserDetails(){
        sharedPreferences = context.getSharedPreferences("LoginCredentials",Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        Map<String, String> userDetails = new HashMap<>();
        assert email != null;
        userDetails.put("email", email);
        assert password != null;
        userDetails.put("password", password);
        return userDetails;

    }

    public boolean addUser(String email, String password, boolean isLoggedIn){
        sharedPreferences = context.getSharedPreferences("LoginCredentials",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("isLoggedIn", isLoggedIn);
        return editor.commit();
    }

}
