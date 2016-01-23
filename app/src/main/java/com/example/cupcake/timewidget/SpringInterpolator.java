package com.example.cupcake.timewidget;

import android.view.animation.Interpolator;

/**
 * Created by cupcake on 16-1-13.
 */
public class SpringInterpolator implements Interpolator {

    @Override
    public float getInterpolation(float input) {
        if (input == 0) {
            return 1;
        } else {
            return (float)(Math.exp(-input * 10 / 3) * Math.cos(3 * input * 10));
        }
    }
}
