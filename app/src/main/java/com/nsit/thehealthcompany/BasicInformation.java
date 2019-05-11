package com.nsit.thehealthcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nsit.thehealthcompany.Adapters.BasicSignUpInfoAdapter;
import com.nsit.thehealthcompany.Utils.NonSwipeableCustomViewPager;

public class BasicInformation extends AppCompatActivity {

    private TextView nextTextView;
    private NonSwipeableCustomViewPager basicInfoViewPager;

    @Override
    public void onBackPressed() {
        int currentItem = basicInfoViewPager.getCurrentItem();
        System.out.println("Current item is : "+currentItem);
        if(currentItem == 1 || currentItem ==2){
            RelativeLayout bottomLayout = findViewById(R.id.bottomLayoutBasicInfo);
            System.out.println("Child count: "+bottomLayout.getChildCount());
            System.out.println("Hey : "+bottomLayout.getChildAt(1));
            basicInfoViewPager.setCurrentItem(currentItem-1, true);
            if(currentItem == 1){
                bottomLayout.removeViewAt(1);
            }
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        Intent intent = getIntent();
        int ID = intent.getIntExtra("email",0);
        String email = intent.getStringExtra("email");

        basicInfoViewPager = findViewById(R.id.basicInfoViewPager);
        nextTextView = findViewById(R.id.nextTextView);

        basicInfoViewPager.setOffscreenPageLimit(3);
        basicInfoViewPager.setScrollDurationFactor(8);
        basicInfoViewPager.setAdapter(new BasicSignUpInfoAdapter(this, basicInfoViewPager, nextTextView, ID, email));
    }
}
