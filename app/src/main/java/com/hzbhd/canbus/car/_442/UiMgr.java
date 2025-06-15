package com.hzbhd.canbus.car._442;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    RadarView radarView;
    private int windModeTag = 0;
    OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.1
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onLeftSeatClick() {
            if (UiMgr.this.windModeTag != 0) {
                if (UiMgr.this.windModeTag != 1) {
                    if (UiMgr.this.windModeTag != 2) {
                        if (UiMgr.this.windModeTag == 3) {
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.windModeTag = 0;
                            return;
                        }
                        return;
                    }
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.windModeTag = 3;
                    return;
                }
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.windModeTag = 2;
                return;
            }
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.windModeTag = 1;
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
        public void onRightSeatClick() {
            if (UiMgr.this.windModeTag != 0) {
                if (UiMgr.this.windModeTag != 1) {
                    if (UiMgr.this.windModeTag != 2) {
                        if (UiMgr.this.windModeTag == 3) {
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
                            UiMgr.this.windModeTag = 0;
                            return;
                        }
                        return;
                    }
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
                    UiMgr.this.windModeTag = 3;
                    return;
                }
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.windModeTag = 2;
                return;
            }
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            UiMgr.this.windModeTag = 1;
        }
    };
    OnAirBtnClickListener topAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i != 2) {
                return;
            }
            UiMgr.this.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener leftAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener rightAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirBtnClickListener bottomAir = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                return;
            }
            if (i != 2) {
                return;
            }
            UiMgr.this.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirTemperatureUpDownClickListener centerTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };
    OnAirWindSpeedUpDownClickListener windSpeedControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._442.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            UiMgr.this.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            UiMgr.this.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            UiMgr.this.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.radarView == null) {
            this.radarView = new RadarView(context);
        }
        this.radarView.refreshRadar();
        return this.radarView;
    }

    public void refreshRadar() {
        RadarView radarView = this.radarView;
        if (radarView != null) {
            radarView.refreshRadar();
        }
    }

    public UiMgr(Context context) {
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topAir, this.leftAir, this.rightAir, this.bottomAir});
        airUiSet.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.centerTemp, null});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windSpeedControl);
    }

    public void sendAir(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 3, -108, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i8, -64});
    }
}
