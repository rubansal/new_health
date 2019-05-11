package com.nsit.thehealthcompany.Utils;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ViewPagerScrollerHandler extends Scroller {

    private double mScrollFactor = 1;

    public ViewPagerScrollerHandler(Context context, int mDuration) {
        super(context);
    }

    public ViewPagerScrollerHandler(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ViewPagerScrollerHandler(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    void setScrollDurationFactor(double scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) ((duration)*mScrollFactor));
    }
}
