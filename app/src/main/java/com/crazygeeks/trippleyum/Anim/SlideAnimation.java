package com.crazygeeks.trippleyum.Anim;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by mymac on 2/26/17.
 */

public class SlideAnimation extends Animation {

    private static final float SPEED = 0.5f;

    private float mStart;
    private float mEnd;

    public SlideAnimation(float fromX, float toX) {
        mStart = fromX;
        mEnd = toX;

        setInterpolator(new LinearInterpolator());

        float duration = Math.abs(mEnd - mStart) / SPEED;
        setDuration((long) duration);
    }




}
