package com.hzbhd.canbus.car._168;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;


public class UiMgr extends AbstractUiMgr {
    protected static final String CAN_1168_SAVE_LANGUAGE = "__1168_SAVE_LANGUAGE";
    protected static final int CAR_ID_EXCELLE_2015 = 251;
    protected static final int CAR_ID_GL6 = 255;
    protected static final int CAR_ID_GL8_2017 = 7;
    protected static final int CAR_ID_LACROSSE_2004 = 253;
    protected static final int CAR_ID_MALIBU_2016 = 254;
    protected static final int CAR_ID_VERANO_2015 = 252;
    protected static int mDiffid;
    private static int mFrontWindMode;
    private static int mRearWindMode;
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private FrontArea mFrontArea;
    private MsgMgr mMsgMgr;
    private RearArea mRearArea;
    private OnOnStarPhoneMoreInfoClickListener mOnOnStarPhoneMoreInfoClickListener = new OnOnStarPhoneMoreInfoClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void init() {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 8, 1});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void numberClick(int i) {
            byte asciiNum = UiMgr.this.getAsciiNum(i);
            if (UiMgr.this.isCallTalking() || UiMgr.this.isCallOut()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -70, 4, asciiNum});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void handOn(String str) {
            if (UiMgr.this.isCallIn()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -70, 1, 0});
            } else {
                if (!UiMgr.this.isCallNone() || str.length() <= 0) {
                    return;
                }
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -69}, UiMgr.this.fillSpaces(str + "\u0000").getBytes()));
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void handOff() {
            if (UiMgr.this.isCallIn()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -70, 2, 0});
            } else if (UiMgr.this.isCallOut() || UiMgr.this.isCallTalking()) {
                CanbusMsgSender.sendMsg(new byte[]{22, -70, 3, 0});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void mute() {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 5, 0});
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void reDial() {
            if (GeneralOnStartData.mOnStarStatus == 0 || GeneralOnStartData.mOnStarStatus == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -70, 7, 0});
            }
        }

        @Override // com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener
        public void exit() {
            CanbusMsgSender.sendMsg(new byte[]{22, -70, 8, 0});
        }
    };
    private OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i == 9 && i2 == 1) {
                UiMgr.this.sendData(new byte[]{22, -102, 2, 1});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            switch (i) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) (i2 + 1), (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, 74, (byte) (i2 + 1), (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, 90, (byte) (i2 + 1), (byte) i3});
                    break;
                case 3:
                    if (i2 == 2 || i2 == 4) {
                        if (UiMgr.mDiffid != 251 && UiMgr.mDiffid != 252) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte) (i2 + 1), (byte) i3});
                            break;
                        } else if (i3 == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte) (i2 + 1), (byte) (i3 + 1)});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte) (i2 + 1), (byte) i3});
                            break;
                        }
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 106, (byte) (i2 + 1), (byte) i3});
                        break;
                    }
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte) (i2 + 1), (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte) (i2 + 1), (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte) (i2 + 1), (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte) (i2 + 1), (byte) i3});
                    break;
                case 9:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) (((byte) i3) + 1)});
                        SharePreUtil.setIntValue(UiMgr.this.mContext, UiMgr.CAN_1168_SAVE_LANGUAGE, i3);
                        UiMgr.this.mMsgMgr.setLanguage_recNull(UiMgr.this.mContext);
                        break;
                    }
                    break;
                case 10:
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 96, 5, 1, -62});
                        break;
                    } else if (i2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 96, 5, 1, -61});
                        break;
                    } else if (i2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 1});
                        break;
                    } else if (i2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -54, 2});
                        break;
                    }
                    break;
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommandSwitch(GeneralAirData.power, 1);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAirCommandSwitch(GeneralAirData.sync, 15);
                return;
            }
            if (i == 2) {
                UiMgr.this.sendAirCommandSwitch(GeneralAirData.sync, 2);
            } else {
                if (i != 3) {
                    return;
                }
                if (GeneralAirData.aqs) {
                    UiMgr.this.sendAirCommandSwitch(true ^ GeneralAirData.in_out_cycle, 7);
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 59, 7, 2});
                }
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommandSwitch(GeneralAirData.in_out_cycle, 7);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommandSwitch(GeneralAirData.auto, 4);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(30);
            } else if (i == 1) {
                UiMgr.this.sendAirCommand(35);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirCommand(25);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommandPlus(12);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommandReduce(12);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommandPlus(13);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommandReduce(13);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            Log.e("", "");
            UiMgr.this.sendAirCommandPlus(22);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommandReduce(22);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public byte getAsciiNum(int i) {
        if (i <= 9) {
            return (byte) (i + 48);
        }
        if (i == 10) {
            return (byte) 42;
        }
        return i == 11 ? (byte) 35 : (byte) 0;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        mDiffid = getCurrentCarId();
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommandReduce(11);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommandPlus(11);
            }
        });
        this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommandReduce(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommandPlus(21);
            }
        });
        SingletonForKt.INSTANCE.setAirListener(this.airPageUiSet);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        SingletonForKt.INSTANCE.setSettingsListener(settingUiSet);
        getOnStartPageUiSet(context).setmOnOnStarPhoneMoreInfoClickListener(this.mOnOnStarPhoneMoreInfoClickListener);
        this.mMsgMgr.setLanguage_recNull(this.mContext);
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCallIn() {
        return GeneralOnStartData.mOnStarStatus == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCallOut() {
        return GeneralOnStartData.mOnStarStatus == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCallTalking() {
        return GeneralOnStartData.mOnStarStatus == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCallNone() {
        return GeneralOnStartData.mOnStarStatus == 0 || GeneralOnStartData.mOnStarStatus == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String fillSpaces(String str) {
        if (str.length() < 32) {
            int length = 32 - str.length();
            for (int i = 0; i < length; i++) {
                str = str + " ";
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommandSwitch(boolean z, int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, (byte) (!z ? 1 : 0)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommandPlus(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, 1});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommandReduce(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, 2});
    }

    private void sendAirCommandFrontWindMode() {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, new byte[]{5, 9, 10, 33, 34}[mFrontWindMode], -1});
        int i = mFrontWindMode + 1;
        mFrontWindMode = i;
        if (i >= 5) {
            mFrontWindMode = 0;
        }
    }

    private void sendAirCommandRearWindMode() {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, new byte[]{17, 18, 19, 20}[mRearWindMode], -1});
        int i = mRearWindMode + 1;
        mRearWindMode = i;
        if (i >= 4) {
            mRearWindMode = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._168.UiMgr$13] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._168.UiMgr.13
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte) i, 0});
            }
        }.start();
    }
}
