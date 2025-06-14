package com.hzbhd.canbus.car._283;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.activity.AirActivity;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private String[] mAirBtnListFrontBottom;
    private String[] mAirBtnListFrontLeft;
    private String[] mAirBtnListFrontRight;
    private String[] mAirBtnListFrontTop;
    private String[] mAirBtnListRearBottom;
    private Context mContext;
    private CusPanoramicView mMyPanoramicView;
    private float tempInterval = 0.5f;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._283.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontTop[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._283.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontLeft[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._283.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontRight[i]);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._283.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            uiMgr.sendAirCommand(uiMgr.mAirBtnListFrontBottom[i]);
        }
    };
    OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._283.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.subWind();
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.addWind();
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new AnonymousClass6();
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new AnonymousClass7();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new CusPanoramicView(context);
        }
        this.mMyPanoramicView.refreshUiLine();
        return this.mMyPanoramicView;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        this.mAirBtnListFrontTop = lineBtnAction[0];
        this.mAirBtnListFrontLeft = lineBtnAction[1];
        this.mAirBtnListFrontRight = lineBtnAction[2];
        this.mAirBtnListFrontBottom = lineBtnAction[3];
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListener);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
    }

    public static void subWind() {
        int i = GeneralAirData.front_wind_level;
        if (i <= 0 || i > 7) {
            return;
        }
        byte b = (byte) (i - 1);
        CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, b});
        CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, b});
    }

    public static void addWind() {
        int i = GeneralAirData.front_wind_level;
        if (i < 0 || i >= 7) {
            return;
        }
        byte b = (byte) (i + 1);
        CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, b});
        CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, b});
    }

    /* renamed from: com.hzbhd.canbus.car._283.UiMgr$6, reason: invalid class name */
    class AnonymousClass6 implements OnAirTemperatureUpDownClickListener {
        AnonymousClass6() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.UiMgr$6$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.m356lambda$onClickUp$0$comhzbhdcanbuscar_283UiMgr$6();
                }
            }).start();
        }

        /* renamed from: lambda$onClickUp$0$com-hzbhd-canbus-car-_283-UiMgr$6, reason: not valid java name */
        /* synthetic */ void m356lambda$onClickUp$0$comhzbhdcanbuscar_283UiMgr$6() throws InterruptedException {
            UiMgr.this.addTemp((byte) 20, GeneralDzData.air_front_left_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UiMgr.this.addTemp((byte) 21, GeneralDzData.air_front_right_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            UiMgr.this.addTemp((byte) 22, GeneralDzData.air_rear_temp);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.UiMgr$6$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.m355lambda$onClickDown$1$comhzbhdcanbuscar_283UiMgr$6();
                }
            }).start();
        }

        /* renamed from: lambda$onClickDown$1$com-hzbhd-canbus-car-_283-UiMgr$6, reason: not valid java name */
        /* synthetic */ void m355lambda$onClickDown$1$comhzbhdcanbuscar_283UiMgr$6() throws InterruptedException {
            UiMgr.this.subTemp((byte) 20, GeneralDzData.air_front_left_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UiMgr.this.subTemp((byte) 21, GeneralDzData.air_front_right_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            UiMgr.this.subTemp((byte) 22, GeneralDzData.air_rear_temp);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._283.UiMgr$7, reason: invalid class name */
    class AnonymousClass7 implements OnAirTemperatureUpDownClickListener {
        AnonymousClass7() {
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.UiMgr$7$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.m358lambda$onClickUp$0$comhzbhdcanbuscar_283UiMgr$7();
                }
            }).start();
        }

        /* renamed from: lambda$onClickUp$0$com-hzbhd-canbus-car-_283-UiMgr$7, reason: not valid java name */
        /* synthetic */ void m358lambda$onClickUp$0$comhzbhdcanbuscar_283UiMgr$7() throws InterruptedException {
            UiMgr.this.addTemp((byte) 20, GeneralDzData.air_front_left_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UiMgr.this.addTemp((byte) 21, GeneralDzData.air_front_right_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            UiMgr.this.addTemp((byte) 22, GeneralDzData.air_rear_temp);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.UiMgr$7$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.m357lambda$onClickDown$1$comhzbhdcanbuscar_283UiMgr$7();
                }
            }).start();
        }

        /* renamed from: lambda$onClickDown$1$com-hzbhd-canbus-car-_283-UiMgr$7, reason: not valid java name */
        /* synthetic */ void m357lambda$onClickDown$1$comhzbhdcanbuscar_283UiMgr$7() throws InterruptedException {
            UiMgr.this.subTemp((byte) 20, GeneralDzData.air_front_left_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UiMgr.this.subTemp((byte) 21, GeneralDzData.air_front_right_temp);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            UiMgr.this.subTemp((byte) 22, GeneralDzData.air_rear_temp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTemp(byte b, String str) {
        getTempInterval();
        try {
            if (str.equals("HI")) {
                return;
            }
            if (GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("LO") ? 16.0f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_c_unit), "")).floatValue()) + this.tempInterval) * 2.0f;
                if (fFloatValue >= 59.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -1});
                    return;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                    return;
                }
            }
            float fFloatValue2 = ((str.equals("LO") ? 61.0f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_f_unit), "")).floatValue()) * 2.0f) + 2.0f;
            if (fFloatValue2 >= 170.0f) {
                MessageSender.sendMsg(new byte[]{22, 58, b, -1});
            } else {
                MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue2});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subTemp(byte b, String str) {
        getTempInterval();
        try {
            if (str.equals("LO")) {
                return;
            }
            if (GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("HI") ? 29.5f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_c_unit), "")).floatValue()) - this.tempInterval) * 2.0f;
                if (fFloatValue <= 32.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -2});
                    return;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                    return;
                }
            }
            float fFloatValue2 = ((str.equals("HI") ? 85.0f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_f_unit), "")).floatValue()) * 2.0f) - 2.0f;
            if (fFloatValue2 <= 122.0f) {
                MessageSender.sendMsg(new byte[]{22, 58, b, -2});
            } else {
                MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue2});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTempInterval() {
        try {
            this.tempInterval = Float.valueOf(SharePreUtil.getStringValue(this.mContext, AirActivity.TEMP_INTERVAL, "0.5")).floatValue();
        } catch (Exception e) {
            this.tempInterval = 0.5f;
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        if (str.equals("ac")) {
            sendMsg((byte) 15, GeneralAirData.ac);
        } else if (str.equals("in_out_cycle")) {
            sendMsg((byte) 19, GeneralAirData.in_out_cycle);
        }
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg(new byte[]{22, 58, b, (byte) (!z ? 1 : 0)});
    }
}
