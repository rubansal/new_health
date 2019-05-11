package com.nsit.thehealthcompany;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TempScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

        Button privacyAndTermsBtn = findViewById(R.id.privacyAndTermsBtn);
        Button hiScreenBtn = findViewById(R.id.hiScreenBtn);
        Button registerScreenBtn = findViewById(R.id.registerScreenBtn);
        Button addMealBtn = findViewById(R.id.addMealBtn);
        Button showMealDescBtn = findViewById(R.id.showMealDescBtn);
        Button mainScreenBtn = findViewById(R.id.mainScreenBtn);
        Button personalDetails=findViewById(R.id.personalDetailsBtn);
        Button loginScreenBtn = findViewById(R.id.loginScreenBtn);
        Button signUpScreenBtn = findViewById(R.id.signUpScreenBtn);
        Button addScreenBtn = findViewById(R.id.addScreenBtn);
        Button introSliderBtn = findViewById(R.id.introSliderBtn);

        Button integratedScreens  = findViewById(R.id.integratedScreens);

        privacyAndTermsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, PrivacyAndTermsActivity.class);
                startActivity(intent);
            }
        });

        hiScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, BasicInformation.class);
                startActivity(intent);
            }
        });

        registerScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, SearchMeal.class);
                startActivity(intent);
            }
        });

        showMealDescBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, MealNurtitionDescription.class);
                startActivity(intent);
            }
        });

        mainScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loginScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        signUpScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });

        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TempScreen.this, PersonalDetails.class);
                startActivity(intent);
            }
        });

        addScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TempScreen.this, AddScreen.class);
                startActivity(intent);
            }
        });

        introSliderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TempScreen.this, GetStartedActivity.class);
                startActivity(intent);
            }
        });









        integratedScreens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TempScreen.this, HealthyScreen.class);
                startActivity(intent);
            }
        });

    }
}
