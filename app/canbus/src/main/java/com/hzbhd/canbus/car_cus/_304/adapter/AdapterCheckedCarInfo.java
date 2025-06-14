package com.hzbhd.canbus.car_cus._304.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._304.bean.CheckedCarInfoBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AdapterCheckedCarInfo extends RecyclerView.Adapter<ViewHolder> {
    private int mTextSizeCurrent;
    private int mTextSizeIdle;
    private final String TAG = "_304_AdapterCheckedCarInfoljqdebug";
    private final long ANIMATION_DURATION = 200;
    private List<CheckedCarInfoBean> mList = new ArrayList();

    public AdapterCheckedCarInfo(Context context) {
        this.mTextSizeCurrent = (int) context.getResources().getDimension(R.dimen.dp28);
        this.mTextSizeIdle = (int) context.getResources().getDimension(R.dimen.dp18);
    }

    public void addData(List<CheckedCarInfoBean> list) {
        this.mList = list;
        if (list.size() > 0) {
            this.mList.get(1).setChecked(true);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout._304_adapter_checked_car_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CheckedCarInfoBean checkedCarInfoBean = this.mList.get(i);
        viewHolder.textView.setText(checkedCarInfoBean.getTitle());
        int i2 = checkedCarInfoBean.isChecked() ? this.mTextSizeCurrent : this.mTextSizeIdle;
        viewHolder.playAnimation(checkedCarInfoBean.getTextSize(), i2, 200L);
        checkedCarInfoBean.setTextSize(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.textView);
        }

        public void playAnimation(int i, int i2, long j) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(i, i2);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.hzbhd.canbus.car_cus._304.adapter.AdapterCheckedCarInfo$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m1130x31fac39c(valueAnimator);
                }
            });
            valueAnimatorOfFloat.setDuration(j);
            valueAnimatorOfFloat.start();
        }

        /* renamed from: lambda$playAnimation$0$com-hzbhd-canbus-car_cus-_304-adapter-AdapterCheckedCarInfo$ViewHolder, reason: not valid java name */
        /* synthetic */ void m1130x31fac39c(ValueAnimator valueAnimator) {
            this.textView.setTextSize(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public void setSelectedPosition(int i) {
        for (int i2 = 0; i2 < this.mList.size(); i2++) {
            this.mList.get(i2).setChecked(false);
        }
        this.mList.get(i).setChecked(true);
    }
}
