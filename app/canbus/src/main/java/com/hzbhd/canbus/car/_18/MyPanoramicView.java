package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.common.settings.constant.BodaSysContant;

/* loaded from: classes.dex */
public class MyPanoramicView extends RelativeLayout {
    private Button mBtnTest;
    private Context mContext;

    public MyPanoramicView(Context context) {
        super(context);
        this.mContext = context;
        Button button = (Button) LayoutInflater.from(context).inflate(R.layout.layout_panoramice_273_view, this).findViewById(R.id.btn_panoramice);
        this.mBtnTest = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._18.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.showLog(BodaSysContant.CarSettings.Sign.Click);
            }
        });
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
