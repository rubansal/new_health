package com.nsit.thehealthcompany;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.nsit.thehealthcompany.Retrofit.Calls.SignUp;

public class SignUpScreen extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText passwordEditText;
    private EditText emailIDEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailIDEditText = findViewById(R.id.emailIDEditText);
        phoneEditText = findViewById(R.id.phoneNumberEditText);
        TextView signInTextView = findViewById(R.id.signInTextView);
        Button signUpBtn = findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = emailIDEditText.getText().toString();
                String firstName = firstNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String emailID = emailIDEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = "NA";
                String city = "NA";
                String state = "NA";
                String pinCode = "123456";
                String platform = "THCAPP";

                JsonObject dataObject = new JsonObject();
                dataObject.addProperty("username", username);
                dataObject.addProperty("password",password);
                dataObject.addProperty("first_name",firstName);
                dataObject.addProperty("email",emailID);
                dataObject.addProperty("phone",phone);
                dataObject.addProperty("address",address);
                dataObject.addProperty("pincode",pinCode);
                dataObject.addProperty("city",city);
                dataObject.addProperty("state",state);
                dataObject.addProperty("platform",platform);

                SignUp signUp = new SignUp(SignUpScreen.this, dataObject);
                signUp.execute();
            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
