package com.nsit.thehealthcompany;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class UserFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayout personalDetailsLayout = view.findViewById(R.id.personalDetailsLayout);
        LinearLayout foodPreferenceLayout = view.findViewById(R.id.foodPreferenceLayout);
        LinearLayout accountSettingLayout = view.findViewById(R.id.accountSettingLayout);
        LinearLayout notificationLayout = view.findViewById(R.id.notificationLayout);
        LinearLayout getHelpLayout = view.findViewById(R.id.getHelpLayout);

        personalDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalDetails.class);
                startActivity(intent);
            }
        });

        foodPreferenceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FoodPreferences.class);
                startActivity(intent);
            }
        });

        accountSettingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AccountSettings.class);
                startActivity(intent);
            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationSettings.class);
                startActivity(intent);
            }
        });

        getHelpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HelpScreen.class);
                startActivity(intent);
            }
        });

        ImageView updateWeightGifImageView = view.findViewById(R.id.updateWeightGifImageView);

        Glide.with(Objects.requireNonNull(getActivity())).load(R.drawable.temp_update_weight).into(updateWeightGifImageView);

    }
}