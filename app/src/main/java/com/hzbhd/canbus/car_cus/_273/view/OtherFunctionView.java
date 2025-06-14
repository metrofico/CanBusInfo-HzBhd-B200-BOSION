package com.hzbhd.canbus.car_cus._273.view;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class OtherFunctionView extends RelativeLayout implements ViewInterface {
    private Context mContext;
    private View mView;

    private void findViews() {
    }

    private void initViews() {
    }

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void onDestroy() {
    }

    @Override // com.hzbhd.canbus.car_cus._273.view.ViewInterface
    public void refreshUi(Bundle bundle) {
    }

    public OtherFunctionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._273_view_other_function, this);
        findViews();
        initViews();
    }
}
