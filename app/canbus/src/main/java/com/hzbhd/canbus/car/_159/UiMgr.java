package com.hzbhd.canbus.car._159;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private com.hzbhd.canbus.car._97.MsgMgr mMsgMgr;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(0, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(1, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(2, i);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.resolveAirBtn(3, i);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(2);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(3);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(11);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(12);
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (this.mDifferent != 1) {
            removeFrontAirButtonByName(context, AirBtnAction.STEERING_WHEEL_HEATING);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._159.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(5);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveAirBtn(int i, int i2) {
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        if (TextUtils.isEmpty(str)) {
        }
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommand(1);
                break;
            case "ac":
                sendAirCommand(14);
                break;
            case "auto":
                sendAirCommand(13);
                break;
            case "mono":
                sendAirCommand(15);
                break;
            case "power":
                sendAirCommand(7);
                break;
            case "blow_window":
                sendAirCommand(10);
                break;
            case "in_out_cycle":
                sendAirCommand(6);
                break;
            case "blow_foot":
                sendAirCommand(8);
                break;
            case "blow_head":
                sendAirCommand(9);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte) i, 0});
    }
}
