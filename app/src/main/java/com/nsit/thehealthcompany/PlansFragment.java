package com.nsit.thehealthcompany;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsit.thehealthcompany.Adapters.PlansViewPagerAdapter;

import java.util.Objects;

public class PlansFragment extends Fragment {

    View view;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plans, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = view.findViewById(R.id.plansViewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutPager);

        viewPager.setAdapter(new PlansViewPagerAdapter(Objects.requireNonNull(getActivity()), viewPager));
        tabLayout.setupWithViewPager(viewPager, true);
    }
}