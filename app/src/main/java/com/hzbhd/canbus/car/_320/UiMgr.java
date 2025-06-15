package com.hzbhd.canbus.car._320;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import java.util.Arrays;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private byte[] m0x8AData = {22, -118, 0, 0};
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.2
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommandUpDown(2, 1);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommandUpDown(2, 2);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.3
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(0, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(1, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(2, i);
        }
    };
    private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr.this.sendAirCommand(3, i);
        }
    };
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public UiMgr(Context context) {
        this.mContext = context;
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
        FrontArea frontArea = airUiSet.getFrontArea();
        OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.mOnUpDownClickListener;
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{onAirTemperatureUpDownClickListener, null, onAirTemperatureUpDownClickListener});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._320.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommandUpDown(1, 2);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommandUpDown(1, 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i, int i2) {
        String str = getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[i][i2];
        byte[] bArr = this.m0x8AData;
        Arrays.copyOf(bArr, bArr.length);
        str.hashCode();
        switch (str) {
            case "front_defog":
                sendAirCommandUpDown(7, GeneralAirData.front_defog ? 2 : 1);
                break;
            case "rear_defog":
                sendAirCommandUpDown(6, GeneralAirData.rear_defog ? 2 : 1);
                break;
            case "ac":
                sendAirCommandUpDown(4, GeneralAirData.ac ? 2 : 1);
                break;
            case "auto":
                sendAirCommandUpDown(9, GeneralAirData.auto ? 2 : 1);
                break;
            case "power":
                sendAirCommandUpDown(0);
                break;
            case "in_out_cycle":
                sendAirCommandUpDown(5, GeneralAirData.in_out_cycle ? 2 : 1);
                break;
            case "blow_head_foot":
                sendAirCommandUpDown(8, 2);
                break;
            case "blow_foot":
                sendAirCommandUpDown(8, 3);
                break;
            case "blow_head":
                sendAirCommandUpDown(8, 1);
                break;
            case "blow_window_foot":
                sendAirCommandUpDown(8, 4);
                break;
        }
    }

    private void sendAirCommand(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendAirCommandUpDown(int i) {
        sendAirCommandUpDown(i, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommandUpDown(final int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, (byte) i2});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._320.UiMgr.7
            @Override // java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) i, 0});
            }
        }, 100L);
    }
}
