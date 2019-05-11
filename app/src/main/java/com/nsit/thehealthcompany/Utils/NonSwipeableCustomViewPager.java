package com.nsit.thehealthcompany.Utils;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

public class NonSwipeableCustomViewPager extends ViewPager {

    private ViewPagerScrollerHandler mScroller = null;

    public NonSwipeableCustomViewPager(Context context) {
        super(context);
        postInitViewPager();
    }

    public NonSwipeableCustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    private void postInitViewPager() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewpager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            mScroller = new ViewPagerScrollerHandler(getContext(), (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

}