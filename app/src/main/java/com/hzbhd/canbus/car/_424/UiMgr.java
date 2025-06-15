package com.hzbhd.canbus.car._424;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    AirPageUiSet airPageUiSet;
    int i = 26;
    private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirMessage(1);
            } else if (i == 1) {
                UiMgr.this.sendAirMessage(4);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirMessage(2);
            }
        }
    };
    private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(5);
        }
    };
    private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(6);
        }
    };
    private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAirMessage(7);
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirMessage(14);
        }
    };

    public UiMgr(Context context) {
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                switch (UiMgr.this.i) {
                    case 26:
                    case 27:
                    case 28:
                        UiMgr.this.i++;
                        break;
                    case 29:
                        UiMgr.this.i = 26;
                        break;
                }
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(uiMgr.i);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                switch (UiMgr.this.i) {
                    case 26:
                    case 27:
                    case 28:
                        UiMgr.this.i++;
                        break;
                    case 29:
                        UiMgr.this.i = 26;
                        break;
                }
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAirMessage(uiMgr.i);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirMessage(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirMessage(11);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.ActiveRequest(49);
            }
        });
        getTireUiSet(context).setOnTirePageStatusListener(new OnTirePageStatusListener() { // from class: com.hzbhd.canbus.car._424.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnTirePageStatusListener
            public void onStatusChange(int i) {
                UiMgr.this.ActiveRequest(72);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirMessage(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ActiveRequest(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }
}
