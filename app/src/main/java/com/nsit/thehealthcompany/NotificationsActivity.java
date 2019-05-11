package com.nsit.thehealthcompany;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Button triggerNotificationBtn = findViewById(R.id.triggerNotificationBtn);
        Button notNowBtn = findViewById(R.id.notNowBtn);

        triggerNotificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NotificationsActivity.this, "This is trigger Notification Btn", Toast.LENGTH_SHORT).show();
            }
        });

        notNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NotificationsActivity.this, "This is Not Now Btn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
