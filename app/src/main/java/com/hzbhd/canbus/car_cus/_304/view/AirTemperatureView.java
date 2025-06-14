package com.hzbhd.canbus.car_cus._304.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.util.TimerUtil;

/* loaded from: classes2.dex */
public class AirTemperatureView extends RelativeLayout {
    public static final int MAX_ANGLE = 160;
    public static final int MIN_ANGLE = 20;
    private final String TAG;
    private float mCurrentAngle;
    private float mEventAngle;
    private TimerUtil mOnAdjustTimer;
    private OnTemperatureAdjustListener mOnTemperatureAdjustListener;
    private RelativeLayout mRlAirTemperature;
    private RelativeLayout mRlIndicator;
    private TextView mTvTemperature;

    public interface OnTemperatureAdjustListener {
        void onAdjust(float f);
    }

    public AirTemperatureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "_304_AirTemperatureView";
        findViews(context);
        this.mOnAdjustTimer = new TimerUtil();
    }

    private void findViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout._304_view_air_temperature, this);
        this.mRlAirTemperature = (RelativeLayout) findViewById(R.id.rl_air_temperature);
        this.mRlIndicator = (RelativeLayout) findViewById(R.id.rl_indicator);
        this.mTvTemperature = (TextView) findViewById(R.id.tv_temperature);
    }

    public void initViews(OnTemperatureAdjustListener onTemperatureAdjustListener) {
        this.mOnTemperatureAdjustListener = onTemperatureAdjustListener;
        this.mRlAirTemperature.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._304.view.AirTemperatureView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m1132x112ea52(view, motionEvent);
            }
        });
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-AirTemperatureView, reason: not valid java name */
    /* synthetic */ boolean m1132x112ea52(View view, MotionEvent motionEvent) {
        double degrees = Math.toDegrees(Math.atan2(motionEvent.getX() - (this.mRlAirTemperature.getWidth() / 2.0f), (this.mRlAirTemperature.getHeight() / 2.0f) - motionEvent.getY()));
        if (degrees < 20.0d) {
            degrees = 20.0d;
        }
        if (degrees > 160.0d) {
            degrees = 160.0d;
        }
        refresh((float) degrees);
        return true;
    }

    private void refresh(float f) {
        setIndicatorRotation(f);
        this.mTvTemperature.setText((AirActivity.getGear(f) + 17) + getContext().getString(R.string.str_temp_c_unit));
        this.mOnTemperatureAdjustListener.onAdjust(f);
    }

    public void refresh(String str, float f) {
        Log.i("_304_AirTemperatureView", "refresh: \ntemperature->" + str + "\nangle->" + f);
        this.mTvTemperature.setText(str);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mCurrentAngle, f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.hzbhd.canbus.car_cus._304.view.AirTemperatureView$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.m1133xd36b24f0(valueAnimator);
            }
        });
        long jAbs = (long) ((Math.abs(this.mEventAngle - f) / 10.0f) * 96.0f);
        Log.i("_304_AirTemperatureView", "refresh: duration - > " + jAbs);
        valueAnimatorOfFloat.setDuration(jAbs);
        valueAnimatorOfFloat.start();
    }

    /* renamed from: lambda$refresh$1$com-hzbhd-canbus-car_cus-_304-view-AirTemperatureView, reason: not valid java name */
    /* synthetic */ void m1133xd36b24f0(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mCurrentAngle = fFloatValue;
        setIndicatorRotation(fFloatValue);
    }

    private void setIndicatorRotation(float f) {
        this.mEventAngle = f;
        this.mRlIndicator.setRotation(f);
    }
}
