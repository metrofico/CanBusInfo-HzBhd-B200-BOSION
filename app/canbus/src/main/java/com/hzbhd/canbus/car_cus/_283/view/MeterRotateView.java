package com.hzbhd.canbus.car_cus._283.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class MeterRotateView extends ConstraintLayout {
    private ImageView imageDown;
    private ImageView imagePointer;
    private ImageView imageSelected;
    private ImageView imageSelectedAnimator;
    private ImageView imageUp;
    private int[] leftImages;
    private int[] leftTitles;
    private OnUpDownClickListener mOnUpDownClickListener;
    private RelativeLayout relativeLayout;
    private int[] rightImages;
    private int[] rightTitles;
    private TextView textView;

    public interface OnUpDownClickListener {
        void onCenter(View view);

        void onDown(View view);

        void onUp(View view);
    }

    public MeterRotateView(Context context) {
        this(context, null);
    }

    public MeterRotateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MeterRotateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.leftImages = new int[]{R.drawable._a6_meter_dangwei_n, R.drawable._a6_meter_yh_n, R.drawable._a6_meter_navi_n, R.drawable._a6_meter_acc_n, R.drawable._a6_meter_xhlc_n, R.drawable._a6_meter_ddsj_n, R.drawable._a6_meter_pjcs_n, R.drawable._a6_meter_pjyh_n};
        this.leftTitles = new int[]{R.string._283_meter_value_1, R.string._283_meter_value_16, R.string._283_meter_value_17, R.string._283_meter_value_18, R.string._283_meter_value_20, R.string._283_meter_value_21, R.string._283_meter_value_23, R.string._283_meter_value_24};
        this.rightImages = new int[]{R.drawable._a6_meter_chesu_n, R.drawable._a6_meter_xhlc_n, R.drawable._a6_meter_eco_n, R.drawable._a6_meter_dhzx_n, R.drawable._a6_meter_xhlc_n, R.drawable._a6_meter_ddsj_n, R.drawable._a6_meter_pjcs_n, R.drawable._a6_meter_pjyh_n};
        this.rightTitles = new int[]{R.string._283_meter_value_22, R.string._283_meter_value_6, R.string._283_meter_value_19, R.string._283_meter_value_17, R.string._283_meter_value_20, R.string._283_meter_value_21, R.string._283_meter_value_23, R.string._283_meter_value_24};
        LayoutInflater.from(context).inflate(R.layout._283_view_meter_rotate, (ViewGroup) this, true);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.relativeLayout.setOnLongClickListener(onLongClickListener);
        this.imageSelected.setOnLongClickListener(onLongClickListener);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.imageUp = (ImageView) findViewById(R.id.imageUp);
        this.imageDown = (ImageView) findViewById(R.id.imageDown);
        this.imagePointer = (ImageView) findViewById(R.id.imagePointer);
        this.imageSelected = (ImageView) findViewById(R.id.imageSelected);
        this.imageSelectedAnimator = (ImageView) findViewById(R.id.imageSelectedAnimator);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        this.textView = (TextView) findViewById(R.id.textView);
        this.imageUp.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterRotateView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1097x319c0f39(view);
            }
        });
        this.imageDown.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterRotateView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1098x5f74a998(view);
            }
        });
        this.imageSelected.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterRotateView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1099x8d4d43f7(view);
            }
        });
    }

    /* renamed from: lambda$onFinishInflate$0$com-hzbhd-canbus-car_cus-_283-view-MeterRotateView, reason: not valid java name */
    /* synthetic */ void m1097x319c0f39(View view) {
        OnUpDownClickListener onUpDownClickListener = this.mOnUpDownClickListener;
        if (onUpDownClickListener != null) {
            onUpDownClickListener.onUp(this);
        }
    }

    /* renamed from: lambda$onFinishInflate$1$com-hzbhd-canbus-car_cus-_283-view-MeterRotateView, reason: not valid java name */
    /* synthetic */ void m1098x5f74a998(View view) {
        OnUpDownClickListener onUpDownClickListener = this.mOnUpDownClickListener;
        if (onUpDownClickListener != null) {
            onUpDownClickListener.onDown(this);
        }
    }

    /* renamed from: lambda$onFinishInflate$2$com-hzbhd-canbus-car_cus-_283-view-MeterRotateView, reason: not valid java name */
    /* synthetic */ void m1099x8d4d43f7(View view) {
        OnUpDownClickListener onUpDownClickListener = this.mOnUpDownClickListener;
        if (onUpDownClickListener != null) {
            onUpDownClickListener.onCenter(this);
        }
    }

    public void startDisPlay2Animator(boolean z) {
        final ImageView imageView = this.imageSelected;
        int x = (int) imageView.getX();
        int y = ((int) imageView.getY()) - ((int) getContext().getResources().getDimension(R.dimen.system_ui_height));
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, "translationX", -x);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageView, "translationY", -y);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.7f);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat3);
        objectAnimatorOfFloat2.setDuration(600L);
        objectAnimatorOfFloat.setDuration(800L);
        objectAnimatorOfFloat3.setDuration(600L);
        animatorSet.start();
        imageView.setEnabled(false);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterRotateView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                imageView.setTranslationY(0.0f);
                imageView.setTranslationX(0.0f);
                imageView.setAlpha(1.0f);
                imageView.setEnabled(true);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                imageView.setTranslationY(0.0f);
                imageView.setTranslationX(0.0f);
                imageView.setAlpha(1.0f);
                imageView.setEnabled(true);
            }
        });
    }

    public void setRightShow() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.relativeLayout.getLayoutParams();
        layoutParams.startToStart = -1;
        layoutParams.setMarginEnd((int) getContext().getResources().getDimension(R.dimen.dp32));
        this.relativeLayout.setLayoutParams(layoutParams);
    }

    public void setLeftShow() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.relativeLayout.getLayoutParams();
        layoutParams.endToEnd = -1;
        layoutParams.setMarginStart((int) getContext().getResources().getDimension(R.dimen.dp32));
        this.relativeLayout.setLayoutParams(layoutParams);
    }

    public void setVisibleImage(int i) {
        this.imageUp.setVisibility(i);
        this.imageDown.setVisibility(i);
    }

    public void startAnimatorPointer(int i) {
        float f = i + 5;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.imagePointer, "rotation", -150.0f, i + 35, f, i + 15, i - 5, f, i - 3, i + 3, i - 1, i + 1, i);
        objectAnimatorOfFloat.setDuration(2000L);
        objectAnimatorOfFloat.start();
    }

    public void setRotatePointer(int i) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.imagePointer, "rotation", i);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.start();
    }

    public void setPointerVisible(int i) {
        this.imagePointer.setVisibility(i);
    }

    public void setSelectLeftImage(int i) {
        if (i >= 0) {
            int[] iArr = this.leftImages;
            if (i < iArr.length) {
                this.imageSelected.setImageResource(iArr[i]);
                this.imageSelectedAnimator.setImageResource(this.leftImages[i]);
                this.textView.setText(this.leftTitles[i]);
            }
        }
    }

    public void setSelectRightImage(int i) {
        if (i >= 0) {
            int[] iArr = this.rightImages;
            if (i < iArr.length) {
                this.imageSelected.setImageResource(iArr[i]);
                this.imageSelectedAnimator.setImageResource(this.rightImages[i]);
                this.textView.setText(this.rightTitles[i]);
            }
        }
    }

    public int getLeftImagesLength() {
        return this.leftImages.length;
    }

    public int getRightImagesLength() {
        return this.rightImages.length;
    }

    public void setSelectedImageVisible(int i) {
        this.imageSelected.setVisibility(i);
        this.imageSelectedAnimator.setVisibility(i);
    }

    public void setOnUpDownClickListener(OnUpDownClickListener onUpDownClickListener) {
        this.mOnUpDownClickListener = onUpDownClickListener;
    }
}
