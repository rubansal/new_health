package com.nsit.thehealthcompany;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nsit.thehealthcompany.DTO.UserCalorieDTO;
import com.nsit.thehealthcompany.Utils.SessionManagement;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<UserCalorieDTO> userCalorieDTOArrayList;
    private Bundle bundle = null;
    private FloatingActionButton addMealFabIcon;
    private ImageView homeIcon;
    private ImageView userIcon;
    private ImageView planIcon;
    private ImageView buzzIcon;
    private Fragment fragment;
    private LinearLayout addIconLayout;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void setSelectedIcon(int position){
        homeIcon.setImageResource(R.drawable.home_icon);
        userIcon.setImageResource(R.drawable.user_icon);
        planIcon.setImageResource(R.drawable.plan_icon);
        buzzIcon.setImageResource(R.drawable.buzz_icon);
        switch (position){
            case 0 :
                homeIcon.setImageResource(R.drawable.home_icon_selected);
                break;
            case 1:
                userIcon.setImageResource(R.drawable.user_icon_selected);
                break;
            case 2:
                planIcon.setImageResource(R.drawable.plan_icon);
                break;
            case 3:
                buzzIcon.setImageResource(R.drawable.buzz_icon_selected);
                break;
            default:
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userCalorieDTOArrayList = intent.getParcelableArrayListExtra("UserCaloriesDetailsList");

        homeIcon = findViewById(R.id.homeIcon);
        userIcon = findViewById(R.id.userIcon);
        planIcon = findViewById(R.id.planIcon);
        buzzIcon = findViewById(R.id.buzzIcon);
        addMealFabIcon = findViewById(R.id.addMealFabIcon);
//        addIconLayout = findViewById(R.id.addIconLayout);


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(fragment instanceof HomeFragment)){
                    setSelectedIcon(0);
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                }
            }
        });

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(fragment instanceof UserFragment)){
                    setSelectedIcon(1);
                    fragment = new UserFragment();
                    loadFragment(fragment);
                }
            }
        });

        planIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(fragment instanceof PlansFragment)){
                    setSelectedIcon(2);
                    fragment = new PlansFragment();
                    loadFragment(fragment);
                }
            }
        });

        buzzIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(fragment instanceof BuzzFragment)){
                    setSelectedIcon(3);
                    fragment = new BuzzFragment();
                    loadFragment(fragment);
                }
            }
        });

        addMealFabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                addIconLayout.setVisibility(View.VISIBLE);
//                addIconLayout.bringToFront();
            }
        });

        loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment){
            if (bundle == null){
                bundle = new Bundle();
                bundle.putParcelableArrayList("UserCaloriesDetailsList", userCalorieDTOArrayList);
            }
            fragment.setArguments(bundle);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    public View getFrameContainer(){
        return findViewById(R.id.frame_container);
    }

}
