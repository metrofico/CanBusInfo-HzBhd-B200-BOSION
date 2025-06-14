package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;

/* loaded from: classes2.dex */
public class HybridModeView extends RelativeLayout implements View.OnClickListener {
    private View mView;
    private RelativeLayout mode1;
    private RelativeLayout mode2;
    private RelativeLayout mode3;
    private RelativeLayout mode4;

    public HybridModeView(Context context) {
        this(context, null);
    }

    public HybridModeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HybridModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_hybrid_mode_view, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.mode1 = (RelativeLayout) this.mView.findViewById(R.id.mode1);
        this.mode2 = (RelativeLayout) this.mView.findViewById(R.id.mode2);
        this.mode3 = (RelativeLayout) this.mView.findViewById(R.id.mode3);
        this.mode4 = (RelativeLayout) this.mView.findViewById(R.id.mode4);
        this.mode1.setOnClickListener(this);
        this.mode2.setOnClickListener(this);
        this.mode3.setOnClickListener(this);
        this.mode4.setOnClickListener(this);
    }

    public void refreshUi() {
        setModel(GeneralDzData.hybrid_mode_e, this.mode1);
        setModel(GeneralDzData.hybrid_mode_power, this.mode2);
        setModel(GeneralDzData.hybrid_mode_keep, this.mode3);
        setModel(GeneralDzData.hybrid_mode_charge, this.mode4);
    }

    private void setModel(boolean z, RelativeLayout relativeLayout) {
        if (z) {
            scaleRelativeLayout(relativeLayout);
        } else {
            recoverRelativeLayout(relativeLayout);
        }
    }

    private void scaleRelativeLayout(RelativeLayout relativeLayout) {
        relativeLayout.setSelected(true);
        relativeLayout.setScaleX(1.1f);
        relativeLayout.setScaleY(1.1f);
    }

    private void recoverRelativeLayout(RelativeLayout relativeLayout) {
        relativeLayout.setSelected(false);
        relativeLayout.setScaleX(1.0f);
        relativeLayout.setScaleY(1.0f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mode1 /* 2131362860 */:
                MessageSender.sendMsg(new byte[]{22, -117, 32, 0});
                break;
            case R.id.mode2 /* 2131362861 */:
                MessageSender.sendMsg(new byte[]{22, -117, 33, 0});
                break;
            case R.id.mode3 /* 2131362862 */:
                MessageSender.sendMsg(new byte[]{22, -117, 34, 0});
                break;
            case R.id.mode4 /* 2131362863 */:
                MessageSender.sendMsg(new byte[]{22, -117, 35, 0});
                break;
        }
    }
}
