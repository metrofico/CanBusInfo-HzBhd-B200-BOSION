package com.hzbhd.canbus.car._266;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private MsgMgr msgMgr;
    private ParkPageUiSet parkPageUiSet;
    int windDirection = 1;
    private OnAirBtnClickListener mRearBtnListnener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.4
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i != 0) {
                return;
            }
            UiMgr.this.SendAirMessage(19);
        }
    };
    private OnAirBtnClickListener mFrontTopbtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.5
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendAirMessage(5);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendAirMessage(6);
            }
        }
    };
    private OnAirBtnClickListener mFrontBottombtnListener = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.SendAirMessage(2);
            } else if (i == 1) {
                UiMgr.this.SendAirMessage(1);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.SendAirMessage(7);
            }
        }
    };
    private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendAirMessage(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendAirMessage(14);
        }
    };
    private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.SendAirMessage(15);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.SendAirMessage(16);
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getEachId(), 33});
        if (getCurrentCarId() == 2) {
            removeMainPrjBtn(this.mContext, 0, 1);
        }
        if (getEachId() != 3) {
            this.parkPageUiSet.setShowRadar(false);
            this.airPageUiSet.getFrontArea().setAllBtnCanClick(false);
            this.airPageUiSet.getFrontArea().setCanSetWindSpeed(false);
            this.airPageUiSet.getFrontArea().setCanSetLeftTemp(false);
            this.airPageUiSet.getFrontArea().setCanSetRightTemp(false);
            this.airPageUiSet.getRearArea().setAllBtnCanClick(false);
        }
    }

    public UiMgr(Context context) {
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.airPageUiSet = getAirUiSet(context);
        this.parkPageUiSet = getParkPageUiSet(context);
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopbtnListener, null, null, this.mFrontBottombtnListener});
        this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mRearBtnListnener});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                if (UiMgr.this.getEachId() != 3) {
                    return;
                }
                UiMgr.this.SendAirMessage(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                if (UiMgr.this.getEachId() != 3) {
                    return;
                }
                UiMgr.this.SendAirMessage(21);
            }
        });
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.SendAirMessage(12);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.SendAirMessage(11);
            }
        });
        getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._266.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    switch (i2) {
                        case 0:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 7, (byte) i3});
                            break;
                        case 1:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 6, (byte) i3});
                            break;
                        case 2:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 5, (byte) i3});
                            break;
                        case 3:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 4, (byte) i3});
                            break;
                        case 4:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 3, (byte) i3});
                            break;
                        case 5:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 2, (byte) i3});
                            break;
                        case 6:
                            CanbusMsgSender.sendMsg(new byte[]{22, 126, 33, 0, 1, (byte) i3});
                            break;
                        case 7:
                            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) i3});
                            UiMgr.this.msgMgr.languageSet(i3);
                            break;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendAirMessage(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }
}
