package com.hzbhd.canbus.car._398;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    Context mContext;
    private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(2);
            }
        }
    };
    private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(5);
        }
    };
    private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(6);
        }
    };
    private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(7);
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(this.mContext);
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(11);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirMessage(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirMessage(21);
            }
        });
        this.airPageUiSet.setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._398.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }
}
