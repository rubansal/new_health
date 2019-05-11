package com.nsit.thehealthcompany;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

public class FragmentAdapter extends FragmentPagerAdapter {

    FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 14;
    }

    @Override
    public int getItemPosition(@NonNull Object object){
        return FragmentStatePagerAdapter.POSITION_NONE;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        DateTime pagerdate = DateTime.now(TimeZone.getDefault());
        DateTime days = pagerdate.plusDays(position - 13);

        Bundle bundle = new Bundle();
        bundle.putString("date", days.format("YYYY-MM-DD").toString());

        MainFragment mainFragment = new MainFragment();

        mainFragment.setArguments(bundle);

        return mainFragment;
    }
}
