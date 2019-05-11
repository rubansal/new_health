package com.nsit.thehealthcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.nsit.thehealthcompany.Retrofit.Calls.GetUserCalorieInfo;
import com.nsit.thehealthcompany.Utils.CommonUtils;
import com.nsit.thehealthcompany.Utils.SessionManagement;

import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SessionManagement sessionManagement = new SessionManagement(this);
        Map<String, String> userDetails = sessionManagement.getLoggedInUserDetails();
        if(sessionManagement.isUserExists()){
            String email = userDetails.get("email");
            String password = userDetails.get("password");
            GetUserCalorieInfo getUserCalorieInfo = new GetUserCalorieInfo(this, email, null);
            getUserCalorieInfo.execute();
        }
        else{
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }
    }

}
