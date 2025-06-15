package com.hzbhd.canbus.car._300;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private MsgMgr mMsgMgr;
    private final int MSG_AIR_COMMAND_UP = 0;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._300.UiMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) message.arg1, 0});
            }
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public UiMgr(Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == 1 && i2 == 0) {
                    UiMgr.this.sendAirCommand(16);
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendAirCommand(10);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendAirCommand(2);
                } else if (i == 2) {
                    UiMgr.this.sendAirCommand(3);
                } else {
                    if (i != 3) {
                        return;
                    }
                    UiMgr.this.sendAirCommand(8);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendAirCommand(4);
                } else {
                    if (i != 1) {
                        return;
                    }
                    UiMgr.this.sendAirCommand(5);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendAirCommand(11);
                } else {
                    if (i != 1) {
                        return;
                    }
                    UiMgr.this.sendAirCommand(1);
                }
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendAirCommand(33);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendAirCommand(34);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendAirCommand(35);
                } else if (i == 3) {
                    UiMgr.this.sendAirCommand(36);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.sendAirCommand(9);
                }
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(13);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(14);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(15);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._300.UiMgr.10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(7);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i, 1});
        Message messageObtain = Message.obtain(this.mHandler);
        messageObtain.what = 0;
        messageObtain.arg1 = i;
        this.mHandler.sendMessageDelayed(messageObtain, 100L);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
