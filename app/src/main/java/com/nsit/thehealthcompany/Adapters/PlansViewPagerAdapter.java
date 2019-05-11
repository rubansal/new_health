package com.nsit.thehealthcompany.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nsit.thehealthcompany.R;
import com.nsit.thehealthcompany.TakeTest;

public class PlansViewPagerAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater layoutInflater;
    ViewPager plansViewPager;

    public PlansViewPagerAdapter(Context context, ViewPager plansViewPager){
        this.context  = context;
        this.plansViewPager = plansViewPager;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.plan_one_pager,container,false);
        switch (position){
            case 0 :
                itemView = layoutInflater.inflate(R.layout.plan_one_pager,container,false);
                itemView.findViewById(R.id.takeTestBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, TakeTest.class);
                        context.startActivity(intent);
                    }
                });
                break;
            case 1 :
                itemView = layoutInflater.inflate(R.layout.plan_two_pager,container,false);
                break;
            case 2 :
                itemView = layoutInflater.inflate(R.layout.plan_three_pager,container,false);
                break;
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
