package com.hzbhd.canbus.util;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* loaded from: classes2.dex */
public class AnimationUtil {
    private View mView;
    private WeightAnimation mWeightAnimation;
    private WidthAnimation mWidthAnimation;

    public AnimationUtil() {
        this.mWidthAnimation = new WidthAnimation();
        this.mWeightAnimation = new WeightAnimation();
    }

    public void setView(View view) {
        this.mView = view;
    }

    public boolean playWidthAnimation(int i, long j) {
        WidthAnimation widthAnimation = this.mWidthAnimation;
        if (widthAnimation == null) {
            return false;
        }
        return widthAnimation.play(i, j);
    }

    public boolean playWeightAnimation(float f, long j) {
        WeightAnimation weightAnimation = this.mWeightAnimation;
        if (weightAnimation == null) {
            return false;
        }
        return weightAnimation.play(f, j);
    }

    private class WidthAnimation {
        private int mCurrentValue;

        private WidthAnimation() {
        }

        private ValueAnimator getWidthAnimator(int i, long j) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mCurrentValue, i);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.hzbhd.canbus.util.AnimationUtil.WidthAnimation.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    WidthAnimation.this.mCurrentValue = Math.round(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    AnimationUtil.this.mView.setLayoutParams(new RelativeLayout.LayoutParams(WidthAnimation.this.mCurrentValue, -1));
                }
            });
            valueAnimatorOfFloat.setDuration(j);
            return valueAnimatorOfFloat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean play(int i, long j) {
            if (i == this.mCurrentValue) {
                return false;
            }
            getWidthAnimator(i, j).start();
            return true;
        }
    }

    private class WeightAnimation {
        private float mCurrentValue;

        private WeightAnimation() {
        }

        private ValueAnimator getWeightAnimator(float f, long j) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mCurrentValue, f);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.hzbhd.canbus.util.AnimationUtil.WeightAnimation.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    WeightAnimation.this.mCurrentValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    AnimationUtil.this.mView.setLayoutParams(new LinearLayout.LayoutParams(0, -1, WeightAnimation.this.mCurrentValue));
                }
            });
            valueAnimatorOfFloat.setDuration(j);
            return valueAnimatorOfFloat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean play(float f, long j) {
            if (f == this.mCurrentValue) {
                return false;
            }
            getWeightAnimator(f, j).start();
            return true;
        }
    }
}
