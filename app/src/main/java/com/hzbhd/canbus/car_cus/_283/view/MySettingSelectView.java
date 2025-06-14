package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;

/* loaded from: classes2.dex */
public class MySettingSelectView extends SettingSelectView {
    public MySettingSelectView(Context context) {
        this(context, null);
    }

    public MySettingSelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MySettingSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnItemClickListener(new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MySettingSelectView.1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public void onClick(View view, boolean z) {
                MySettingSelectView.this.onClick1(view);
            }
        });
    }

    public void onClick1(View view) {
        switch (view.getId()) {
            case R.id.Comfort /* 2131361805 */:
                MessageSender.sendMsg(new byte[]{22, -117, 1, 0, 0});
                break;
            case R.id.Eco /* 2131361809 */:
                MessageSender.sendMsg(new byte[]{22, -117, 4, 0, 0});
                break;
            case R.id.Indivdual /* 2131361824 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 0, 0});
                break;
            case R.id.Normal /* 2131361827 */:
                MessageSender.sendMsg(new byte[]{22, -117, 2, 0, 0});
                break;
            case R.id.Sport /* 2131361830 */:
                MessageSender.sendMsg(new byte[]{22, -117, 3, 0, 0});
                break;
            case R.id.xuedi /* 2131363801 */:
                MessageSender.sendMsg(new byte[]{22, -117, 6, 0, 0});
                break;
            case R.id.yueye /* 2131363805 */:
                MessageSender.sendMsg(new byte[]{22, -117, 7, 0, 0});
                break;
            case R.id.yueye_personal /* 2131363806 */:
                MessageSender.sendMsg(new byte[]{22, -117, 8, 0, 0});
                break;
        }
    }
}
