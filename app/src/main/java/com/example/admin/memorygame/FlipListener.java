package com.example.admin.memorygame;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by admin on 10/2/16.
 */

// http://stackoverflow.com/a/28430661
public class FlipListener implements ValueAnimator.AnimatorUpdateListener {

    private final View myFrontView;
    private final View myBackView;
    private boolean myFlipped;

    public FlipListener(final View front, final View back) {
        this.myFrontView = front;
        this.myBackView = back;
        this.myBackView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        final float value = animation.getAnimatedFraction();
        final float scaleValue = 0.625f + (1.5f * (value - 0.5f) * (value - 0.5f));

        if(value <= 0.5f){
            this.myFrontView.setRotationY(180 * value);
            this.myFrontView.setScaleX(scaleValue);
            this.myFrontView.setScaleY(scaleValue);
            if(myFlipped){
                setStateFlipped(false);
            }
        } else {
            this.myBackView.setRotationY(-180 * (1f- value));
            this.myBackView.setScaleX(scaleValue);
            this.myBackView.setScaleY(scaleValue);
            if(!myFlipped){
                setStateFlipped(true);
            }
        }
    }

    private void setStateFlipped(boolean flipped) {
        myFlipped = flipped;
        this.myFrontView.setVisibility(flipped ? View.GONE : View.VISIBLE);
        this.myBackView.setVisibility(flipped ? View.VISIBLE : View.GONE);
    }
}
