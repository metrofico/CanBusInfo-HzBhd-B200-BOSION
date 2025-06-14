package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class MySettingDialogView extends SettingDialogView {
    private ExecutorService executorService;
    private List<SettingDialogBean> listClose;
    private List<SettingDialogBean> listOpen;

    public MySettingDialogView(Context context) {
        this(context, null);
    }

    public MySettingDialogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MySettingDialogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.listOpen = new ArrayList();
        this.listClose = new ArrayList();
        this.executorService = Executors.newSingleThreadExecutor();
        setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MySettingDialogView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i2) {
                MySettingDialogView.lambda$new$0(view, i2);
            }
        });
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.MySettingDialogView.1
            @Override // java.lang.Runnable
            public void run() {
                switch (MySettingDialogView.this.getId()) {
                    case R.id.ACC /* 2131361792 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Comfort)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Eco)));
                        break;
                    case R.id.Air_Conditioning /* 2131361803 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Eco)));
                        break;
                    case R.id.DCC /* 2131361806 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Comfort)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Sport)));
                        break;
                    case R.id.Dynamic_Bend_lighting /* 2131361807 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Comfort)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Eco)));
                        break;
                    case R.id.Engine /* 2131361810 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Comfort)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Eco)));
                        break;
                    case R.id.Steering /* 2131361831 */:
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Normal)));
                        MySettingDialogView.this.listOpen.add(new SettingDialogBean(MySettingDialogView.this.mContext.getString(R.string.Eco)));
                        break;
                }
                MySettingDialogView mySettingDialogView = MySettingDialogView.this;
                mySettingDialogView.setListFirst(mySettingDialogView.listOpen);
            }
        });
    }

    static /* synthetic */ void lambda$new$0(View view, int i) {
        switch (view.getId()) {
            case R.id.ACC /* 2131361792 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 4, (byte) i});
                break;
            case R.id.Air_Conditioning /* 2131361803 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 5, (byte) i});
                break;
            case R.id.DCC /* 2131361806 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 1, (byte) i});
                break;
            case R.id.Dynamic_Bend_lighting /* 2131361807 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 2, (byte) i});
                break;
            case R.id.Engine /* 2131361810 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 3, (byte) i});
                break;
            case R.id.Steering /* 2131361831 */:
                MessageSender.sendMsg(new byte[]{22, -117, 5, 6, (byte) i});
                break;
        }
    }
}
