package com.nsit.thehealthcompany;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PrivacyAndTermsActivity extends AppCompatActivity {

    private TextView privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_and_terms);

        privacy=findViewById(R.id.privacy);
        privacy.setText("Privacy Policy &");

    }
}
