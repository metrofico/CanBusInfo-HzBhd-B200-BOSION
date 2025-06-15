package com.hzbhd.canbus.car._278;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    protected static final String CAN_1168_SAVE_LANGUAGE = "__1168_SAVE_LANGUAGE";
    protected static final int CAR_ID_EXCELLE_2015 = 251;
    protected static final int CAR_ID_GL6 = 255;
    protected static final int CAR_ID_GL8_2017 = 7;
    protected static final int CAR_ID_H2S_RED_BLUE_2017_2018 = 4;
    protected static final int CAR_ID_H2_2014_2016 = 1;
    protected static final int CAR_ID_H2_BULE_2017_2018 = 2;
    protected static final int CAR_ID_H2_RED_2017_2018 = 3;
    protected static final int CAR_ID_H6_BLUE_RED_2017_2018 = 12;
    protected static final int CAR_ID_H6_BLUE_RED_CHANGE_2017_2018 = 12;
    protected static final int CAR_ID_H6_CHANGE_2019 = 12;
    protected static final int CAR_ID_H6_COUPLE_2016 = 10;
    protected static final int CAR_ID_H6_COUPLE_2019_2020 = 11;
    protected static final int CAR_ID_H6_COUPLE_BLUE_2017 = 7;
    protected static final int CAR_ID_H6_COUPLE_RED_2017 = 6;
    protected static final int CAR_ID_H6_COUPLE_TOP = 11;
    protected static final int CAR_ID_H6_SPORT_2013_2017 = 5;
    protected static final int CAR_ID_H6_SPORT_2018 = 8;
    protected static final int CAR_ID_H9_2015_2018 = 9;
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
    private View mMyPanoramicView;
    private RearArea mRearArea;
    private int mFlSeatHeat = 0;
    private int mFrSeatHeat = 0;
    private int mFlSeatCold = 0;
    private int mFrSeatCold = 0;
    private OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
        public void onConfirmClick(int i, int i2) {
            if (i == 9 && i2 == 1) {
                UiMgr.this.sendData(new byte[]{22, -102, 2, 1});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.8
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            Log.i("onClickItem", "onClickItem leftPos=" + i);
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 7, (byte) (((byte) i3) + 1)});
                    return;
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 8, (byte) i3});
                    return;
                }
            }
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) (((byte) i3) + 1)});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) (((byte) i3) + 1)});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) (((byte) i3) + 1)});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) (((byte) i3) + 1)});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) (((byte) i3) + 1)});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                    break;
                case 12:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                    break;
                case 13:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte) i3});
                    break;
                case 18:
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte) i3});
                    break;
                case 20:
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "__278_SAVE_RADAR_DISP", i3);
                    UiMgr.this.mMsgMgr.initRadarDisp(UiMgr.this.mContext);
                    UiMgr uiMgr = UiMgr.this;
                    ParkPageUiSet parkPageUiSet = uiMgr.getParkPageUiSet(uiMgr.mContext);
                    parkPageUiSet.setShowRadar(i3 == 1);
                    parkPageUiSet.setShowCusPanoramicView(i3 == 0);
                    break;
            }
        }
    };
    private OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.9
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
        public void onClickItem(int i, int i2) {
            if (2 == i) {
                if (i2 == 0) {
                    UiMgr.this.sendAirCommand(40);
                } else if (i2 == 1) {
                    UiMgr.this.sendAirCommand(41);
                } else if (i2 == 2) {
                    UiMgr.this.sendAirCommand(42);
                } else if (i2 == 3) {
                    UiMgr.this.sendAirCommand(43);
                }
            }
            if (i == 0 && i2 == 19) {
                CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, 1});
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(16);
            } else if (i == 1) {
                UiMgr.this.sendAirCommand(17);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAirCommand(34);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(19);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAirCommand(35);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.13
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
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(30);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(31);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirCommand(32);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirCommand(33);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.16
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
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.17
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontLeftSeatHeatDn();
            } else {
                UiMgr.this.sendAirCommand(36);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontLeftSeatHeatUp();
            } else {
                UiMgr.this.sendAirCommand(36);
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.18
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontRightSeatHeatDn();
            } else {
                UiMgr.this.sendAirCommand(37);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontRightSeatHeatUp();
            } else {
                UiMgr.this.sendAirCommand(37);
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeftCold = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.19
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontLeftSeatColdDn();
            } else {
                UiMgr.this.sendAirCommand(38);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontLeftSeatColdUp();
            } else {
                UiMgr.this.sendAirCommand(38);
            }
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRightCold = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.20
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontRightSeatColdDn();
            } else {
                UiMgr.this.sendAirCommand(39);
            }
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            if (UiMgr.mDiffid == 11) {
                UiMgr.this.setFrontRightSeatColdUp();
            } else {
                UiMgr.this.sendAirCommand(39);
            }
        }
    };

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new MyPanoramicView(context);
        }
        return this.mMyPanoramicView;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        mDiffid = getCurrentCarId();
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        this.mFrontArea = airUiSet.getFrontArea();
        this.mRearArea = this.airPageUiSet.getRearArea();
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(context, "__278_SAVE_RADAR_DISP", 0) == 1);
        parkPageUiSet.setShowCusPanoramicView(SharePreUtil.getIntValue(context, "__278_SAVE_RADAR_DISP", 0) == 0);
        this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, this.onMinPlusClickListenerLeftCold, this.onMinPlusClickListenerRightCold});
        this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(29);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(28);
            }
        });
        this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommandReduce(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommandPlus(21);
            }
        });
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.sendAirCommandFrontWindMode();
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.sendAirCommandFrontWindMode();
            }
        });
        this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAirCommandRearWindMode();
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAirCommandRearWindMode();
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
        settingUiSet.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
        this.mMsgMgr.initRadarDisp(this.mContext);
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 4, (byte) (i + 0)});
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 2, (byte) (i + 0)});
                } else {
                    if (i2 != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 3, (byte) (i + 0)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._278.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass22.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    Log.i("ljq", "position: value = " + i);
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 6, (byte) (i + 10)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -122, 5, (byte) (i + 10)});
                }
            }
        });
        this.mMsgMgr.initRadarDisp(this.mContext);
    }

    /* renamed from: com.hzbhd.canbus.car._278.UiMgr$22, reason: invalid class name */
    static /* synthetic */ class AnonymousClass22 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) getCurrentCarId()});
        if (getCurrentCarId() == 3 || getCurrentCarId() == 1 || getCurrentCarId() == 2 || getCurrentCarId() == 4) {
            MsgMgr.maxRadarLen = 4;
        } else {
            MsgMgr.maxRadarLen = 7;
        }
        if (getCurrentCarId() == 4 || getCurrentCarId() == 8 || getCurrentCarId() == 12) {
            MsgMgr.trackType = 1;
        } else {
            MsgMgr.trackType = 0;
        }
    }

    private void sendAirCommandSwitch(boolean z, int i) {
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

    protected static void sendAirCommandFrontWindMode() {
        byte[] bArr = {21, 24, 25, 26, 27};
        CanbusMsgSender.sendMsg(new byte[]{22, -124, bArr[mFrontWindMode], 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -124, bArr[mFrontWindMode], 0});
        int i = mFrontWindMode + 1;
        mFrontWindMode = i;
        if (i >= 5) {
            mFrontWindMode = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommandRearWindMode() {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, new byte[]{17, 18, 19, 20}[mRearWindMode], -1});
        int i = mRearWindMode + 1;
        mRearWindMode = i;
        if (i >= 4) {
            mRearWindMode = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._278.UiMgr$21] */
    public void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._278.UiMgr.21
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, 0});
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontLeftSeatHeatUp() {
        this.mFlSeatHeat = GeneralAirData.front_left_seat_heat_level;
        if (GeneralAirData.front_left_seat_heat_level < 3) {
            int i = this.mFlSeatHeat + 1;
            this.mFlSeatHeat = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte) i});
        } else {
            this.mFlSeatHeat = 0;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte) 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontLeftSeatHeatDn() {
        this.mFlSeatHeat = GeneralAirData.front_left_seat_heat_level;
        if (GeneralAirData.front_left_seat_heat_level > 0) {
            int i = this.mFlSeatHeat - 1;
            this.mFlSeatHeat = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte) i});
        } else {
            this.mFlSeatHeat = 3;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 44, (byte) 3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontRightSeatHeatUp() {
        this.mFrSeatHeat = GeneralAirData.front_right_seat_heat_level;
        if (GeneralAirData.front_right_seat_heat_level < 3) {
            int i = this.mFrSeatHeat + 1;
            this.mFrSeatHeat = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte) i});
        } else {
            this.mFrSeatHeat = 0;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte) 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontRightSeatHeatDn() {
        this.mFrSeatHeat = GeneralAirData.front_right_seat_heat_level;
        if (GeneralAirData.front_right_seat_heat_level > 0) {
            int i = this.mFrSeatHeat - 1;
            this.mFrSeatHeat = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte) i});
        } else {
            this.mFrSeatHeat = 3;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 45, (byte) 3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontLeftSeatColdUp() {
        this.mFlSeatCold = GeneralAirData.front_left_seat_cold_level;
        if (GeneralAirData.front_left_seat_cold_level < 3) {
            int i = this.mFlSeatCold + 1;
            this.mFlSeatCold = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte) i});
        } else {
            this.mFlSeatCold = 0;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte) 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontLeftSeatColdDn() {
        this.mFlSeatCold = GeneralAirData.front_left_seat_cold_level;
        if (GeneralAirData.front_left_seat_cold_level > 0) {
            int i = this.mFlSeatCold - 1;
            this.mFlSeatCold = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte) i});
        } else {
            this.mFlSeatCold = 3;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 46, (byte) 3});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontRightSeatColdUp() {
        this.mFrSeatCold = GeneralAirData.front_right_seat_cold_level;
        if (GeneralAirData.front_right_seat_cold_level < 3) {
            int i = this.mFrSeatCold + 1;
            this.mFrSeatCold = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte) i});
        } else {
            this.mFrSeatCold = 0;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte) 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFrontRightSeatColdDn() {
        this.mFrSeatCold = GeneralAirData.front_right_seat_cold_level;
        if (GeneralAirData.front_right_seat_cold_level > 0) {
            int i = this.mFrSeatCold - 1;
            this.mFrSeatCold = i;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte) i});
        } else {
            this.mFrSeatCold = 3;
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 47, (byte) 3});
        }
    }
}
