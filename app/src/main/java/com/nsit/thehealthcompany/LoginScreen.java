package com.nsit.thehealthcompany;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nsit.thehealthcompany.Retrofit.Calls.BlogPosts;
import com.nsit.thehealthcompany.Retrofit.Calls.Login;
import com.nsit.thehealthcompany.Utils.CommonUtils;

public class LoginScreen extends AppCompatActivity {

    private EditText emailIDEditText;
    private EditText passwordEditText;
    private Login login;
    private ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        login.cancel(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailIDEditText = findViewById(R.id.emailIDEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button signInBtn = findViewById(R.id.signInBtn);
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        TextView signUpTextView = findViewById(R.id.signUpTextView);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailIDEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                startLoginTask(email, password);
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Operation to perform on click on forgot password
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void startLoginTask(String email, String password){
        progressDialog = CommonUtils.getProgressDialog(LoginScreen.this, "Logging In","Just wait a while..");
        login = new Login(LoginScreen.this, email, password, progressDialog);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                login.cancel(true);
            }
        });
        login.execute();
    }

}
