package com.nsit.thehealthcompany;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.nsit.thehealthcompany.Adapters.FragmentAdapter;
import com.nsit.thehealthcompany.DTO.UserCalorieDTO;
import com.nsit.thehealthcompany.DatabaseHelper.UserMealDatabaseHelper;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ArrayList<UserCalorieDTO> userCalorieDTOArrayList;
    private View view;
    private LinearLayout progressLayoutMain;
    private ViewPager mainScreenViewPager;

    private UserMealDatabaseHelper userMealDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assert getArguments() != null;
        userCalorieDTOArrayList = getArguments().getParcelableArrayList("UserCaloriesDetailsList");
        userMealDatabaseHelper=new UserMealDatabaseHelper(getContext());
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppBarLayout appBarLayout = view.findViewById(R.id.appBarLayout);
        progressLayoutMain = view.findViewById(R.id.progressLayoutMain);
        mainScreenViewPager = view.findViewById(R.id.mainScreenViewPager);


        TextView goalTextView = view.findViewById(R.id.goalTextView);
        TextView leanBarDataTextView = view.findViewById(R.id.leanBarDataTextView);
        TextView totalKCalLeftTextView = view.findViewById(R.id.totalKCalLeftTextView);
        TextView leanGreenTeaDataTextView = view.findViewById(R.id.leanGreenTeaDataTextView);
        TextView proteinDataTextView = view.findViewById(R.id.proteinDataTextView);
        TextView carbsDataTextView = view.findViewById(R.id.carbsDataTextView);
        TextView fatDataTextView = view.findViewById(R.id.fatDataTextView);

        int id=Integer.parseInt(userCalorieDTOArrayList.get(0).getId());
        String date_recorded=userCalorieDTOArrayList.get(0).getDateRecorded();
        String breakfast=userCalorieDTOArrayList.get(0).getBreakfast().toString();
        String lunch=userCalorieDTOArrayList.get(0).getLunch().toString();
        String dinner=userCalorieDTOArrayList.get(0).getDinner().toString();
        String snacks=userCalorieDTOArrayList.get(0).getSnacks().toString();
        int total_calories=userCalorieDTOArrayList.get(0).getCalories();
        int current_weight=userCalorieDTOArrayList.get(0).getWeight();


//        userMealDatabaseHelper.insertUserMeal(id, date_recorded, breakfast, lunch, dinner, snacks, total_calories, current_weight);
//        System.out.println("Data from user meal table"+userMealDatabaseHelper.getUserDetails());

        if (userCalorieDTOArrayList.size()>0){
            totalKCalLeftTextView.setText(String.valueOf(userCalorieDTOArrayList.get(0).getCalories()));
            proteinDataTextView.setText(String.valueOf(userCalorieDTOArrayList.get(0).getTotalProtein()+" g left"));
            carbsDataTextView.setText(String.valueOf(userCalorieDTOArrayList.get(0).getTotalCarbs()+" g left"));
            fatDataTextView.setText(String.valueOf(userCalorieDTOArrayList.get(0).getTotalFat()+" g left"));
        }

        mainScreenViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        mainScreenViewPager.setCurrentItem(5000, true);
        mainScreenViewPager.post(new Runnable() {
            @Override
            public void run() {
                mainScreenViewPager.setCurrentItem(5000, false);
            }
        });
        Objects.requireNonNull(mainScreenViewPager.getAdapter()).notifyDataSetChanged();
        mainScreenViewPager.setOffscreenPageLimit(0);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (i>=0){
                    progressLayoutMain.setAlpha(1);
                    progressLayoutMain.setVisibility(View.VISIBLE);
                }
                else if(i>-50){
                    progressLayoutMain.setAlpha(0.7F);
                    progressLayoutMain.setVisibility(View.VISIBLE);
                }
                else if(i>-100){
                    progressLayoutMain.setAlpha(0.5F);
                    progressLayoutMain.setVisibility(View.VISIBLE);
                }
                else if(i>-200){
                    progressLayoutMain.setAlpha(0.3F);
                    progressLayoutMain.setVisibility(View.VISIBLE);
                }
                else if(i>-400){
                    progressLayoutMain.setAlpha(0.1F);
                    progressLayoutMain.setVisibility(View.VISIBLE);
                }
                else{
                    progressLayoutMain.setVisibility(View.GONE);
                }
            }
        });

    }

    void swap(int i){
        mainScreenViewPager.setCurrentItem(getItem(i), true);
    }

    private int getItem(int i) {
        return mainScreenViewPager.getCurrentItem() + i;
    }
}