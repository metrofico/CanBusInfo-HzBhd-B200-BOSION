package com.hzbhd.canbus.car._261;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, 0});
        }
    };
    private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 19, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 19, 0});
            } else if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 22, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 22, 0});
            } else {
                if (i != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 21, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 21, 0});
            }
        }
    };
    private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 28, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 28, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 29, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 29, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 31, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 31, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 30, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 30, 0});
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 33, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 33, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 32, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 32, 0});
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, this.mOnAirBottomLeftBtnClickListener, null, this.mOnAirBottomBtnClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
        airUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._261.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 0});
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 0});
            }
        });
    }
}
