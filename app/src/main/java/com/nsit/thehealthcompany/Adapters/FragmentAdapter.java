package com.nsit.thehealthcompany.Adapters;

import android.os.Bundle;

import com.nsit.thehealthcompany.MainFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        return FragmentStatePagerAdapter.POSITION_NONE;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        DateTime pagerdate = DateTime.now(TimeZone.getDefault());
        DateTime days = pagerdate.plusDays(position - 14);

        Bundle bundle = new Bundle();
        bundle.putString("date", days.format("YYYY-MM-DD"));

        MainFragment mainFragment = new MainFragment();

        mainFragment.setArguments(bundle);

        return mainFragment;
    }
}
