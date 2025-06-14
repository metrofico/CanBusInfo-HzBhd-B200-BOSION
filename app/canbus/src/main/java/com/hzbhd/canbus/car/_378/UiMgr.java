package com.hzbhd.canbus.car._378;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private OnAirBtnClickListener mFrontTopbtnclicklistener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(14);
            } else if (i == 1) {
                UiMgr.this.sendAirMessage(7);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(13);
            }
        }
    };
    private OnAirBtnClickListener mFrontLeftbtnclicklistener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(1);
        }
    };
    private OnAirBtnClickListener mFrontRightbtnclicklistener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(15);
        }
    };
    private OnAirBtnClickListener mFrontBottombtnclicklistener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(9);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirMessage(10);
            } else if (i == 2) {
                UiMgr.this.sendAirMessage(8);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAirMessage(6);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mTempleftlistener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(2);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(3);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempRightlistener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(11);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(12);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopbtnclicklistener, this.mFrontLeftbtnclicklistener, this.mFrontRightbtnclicklistener, this.mFrontBottombtnclicklistener});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempleftlistener, null, this.mTempRightlistener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._378.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(5);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte) i});
    }
}
