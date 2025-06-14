package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingProgressView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class HybridView extends RelativeLayout {
    private ExecutorService executorService;
    private boolean isSeekbar;
    private List<SettingDialogBean> listA;
    private Context mContext;
    private Handler mHandler;
    private View mView;
    private SettingDialogView sdv;
    private SettingProgressView spv;
    private SettingProgressView spv_temp;
    private SettingSelectView ssv;

    private int getSelectPositionA(int i) {
        if (i == 10) {
            return 1;
        }
        if (i != 13) {
            return i != 255 ? 0 : 3;
        }
        return 2;
    }

    private int getTempProgress(int i) {
        if (i == 254) {
            return 0;
        }
        if (i == 255) {
            return 27;
        }
        if (i > 59 || i <= 32) {
            return 0;
        }
        return i - 32;
    }

    public HybridView(Context context) {
        this(context, null);
    }

    public HybridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HybridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.executorService = Executors.newSingleThreadExecutor();
        this.listA = new ArrayList();
        this.isSeekbar = false;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                HybridView.this.isSeekbar = false;
            }
        };
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_hybrid_view, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1124x1900f6f7();
            }
        }, 1000L);
    }

    private void initView() {
        this.sdv = (SettingDialogView) this.mView.findViewById(R.id.sdv);
        this.ssv = (SettingSelectView) this.mView.findViewById(R.id.ssv);
        this.spv = (SettingProgressView) this.mView.findViewById(R.id.spv);
        this.spv_temp = (SettingProgressView) this.mView.findViewById(R.id.spv_temp);
        this.executorService.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1123x5a877be7();
            }
        });
        this.spv.setAll(10, 10.0f, this.mContext.getString(R.string._283_light_unit_1));
        this.spv.setBigAndSmallValueText("", "");
        this.spv_temp.setMaxAndMinProgress(0, 27);
        this.spv_temp.setInterval(0.5f);
        this.spv_temp.setMinProgress(16);
        this.spv_temp.setBigAndSmallValueText(getContext().getString(R.string._283_hybrid_cold), getContext().getString(R.string._283_hybrid_hot));
    }

    /* renamed from: lambda$initView$1$com-hzbhd-canbus-car_cus-_283-view-carsetting-HybridView, reason: not valid java name */
    /* synthetic */ void m1123x5a877be7() {
        this.listA.add(new SettingDialogBean(getContext().getString(R.string._283_hybrid_5)));
        this.listA.add(new SettingDialogBean(getContext().getString(R.string._283_hybrid_10)));
        this.listA.add(new SettingDialogBean(getContext().getString(R.string._283_hybrid_13)));
        this.listA.add(new SettingDialogBean(getContext().getString(R.string._283_hybrid_big)));
        this.sdv.setListFirst(this.listA);
    }

    private void initClick() {
        this.ssv.setOnItemClickListener(new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                MessageSender.sendMsg((byte) 77, (byte) 3, z);
            }
        });
        this.sdv.setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, 77, 1, i != 1 ? i != 2 ? i != 3 ? (byte) 5 : (byte) -1 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED : (byte) 10});
            }
        });
        this.spv.setOnItemClickListener(new SettingProgressView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingProgressView.OnItemClickListener
            public final void onProgressChanged(View view, int i) {
                this.f$0.m1121xbbf1fdfb(view, i);
            }
        });
        this.spv_temp.setOnItemClickListener(new SettingProgressView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingProgressView.OnItemClickListener
            public final void onProgressChanged(View view, int i) {
                this.f$0.m1122x48df151a(view, i);
            }
        });
    }

    /* renamed from: lambda$initClick$4$com-hzbhd-canbus-car_cus-_283-view-carsetting-HybridView, reason: not valid java name */
    /* synthetic */ void m1121xbbf1fdfb(View view, int i) {
        this.isSeekbar = true;
        MessageSender.sendMsg(new byte[]{22, 77, 4, (byte) (i * this.spv.getInterval())});
    }

    /* renamed from: lambda$initClick$5$com-hzbhd-canbus-car_cus-_283-view-carsetting-HybridView, reason: not valid java name */
    /* synthetic */ void m1122x48df151a(View view, int i) {
        this.isSeekbar = true;
        MessageSender.sendMsg(new byte[]{22, 77, 2, i == 16 ? (byte) -2 : i == 43 ? (byte) -1 : (byte) (i + 16)});
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1124x1900f6f7() {
        this.ssv.setSelect(GeneralDzData.hybrid_air_use_electricity);
        this.sdv.setSelect(getSelectPositionA(GeneralDzData.hybrid_big_charge_current));
        if (!this.isSeekbar) {
            this.spv.setValueProgress(GeneralDzData.hybrid_low_charge);
            this.spv_temp.setProgressNoInterval(getTempProgress(GeneralDzData.hybrid_temperature_in));
        } else {
            this.mHandler.removeMessages(0);
            this.mHandler.sendEmptyMessageDelayed(0, 200L);
        }
    }
}
