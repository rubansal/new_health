package com.nsit.thehealthcompany.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nsit.thehealthcompany.R;

public class IntroSliderViewPagerAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater layoutInflater;

    public IntroSliderViewPagerAdapter(Context context){
        this.context  = context;
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
        View itemView = layoutInflater.inflate(R.layout.intro_slide_one,container,false);
        switch (position){
            case 0 :
                itemView = layoutInflater.inflate(R.layout.intro_slide_one,container,false);
                break;
            case 1 :
                itemView = layoutInflater.inflate(R.layout.intro_slide_two,container,false);
                break;
            case 2 :
                itemView = layoutInflater.inflate(R.layout.intro_slide_three,container,false);
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
