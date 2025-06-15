package com.hzbhd.canbus.car._250;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    protected static final int CAR_ID_AX3_2019 = 6;
    protected static final int CAR_ID_AX5_2016 = 2;
    protected static final int CAR_ID_AX7_2015_2017 = 1;
    protected static final int CAR_ID_AX7_2018 = 3;
    protected static final int CAR_ID_AX7_2018_WITH_360 = 4;
    protected static final int CAR_ID_AX7_2019_2020 = 5;
    protected static final int CAR_ID_DEFAULT = 0;
    protected static final int CAR_ID_XUANYI = 7;
    private static int mFrontWindMode;
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private FrontArea mFrontArea;
    private MsgMgr mMsgMgr;
    SettingPageUiSet settingPageUiSet;
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirInfo(2, 0);
            UiMgr.this.sendAirInfo(0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirInfo(1, 0);
            UiMgr.this.sendAirInfo(0, 0);
        }
    };
    private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAirInfo(0, 2);
            UiMgr.this.sendAirInfo(0, 0);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAirInfo(0, 1);
            UiMgr.this.sendAirInfo(0, 0);
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.POWER);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.AC);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.AUTO);
            } else if (i == 1) {
                UiMgr.this.sendAcCmd(AIR_CMD.LOOP);
            } else {
                if (i != 2) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.REAR_DEFOG);
            }
        }
    };
    private int mDifferent = getCurrentCarId();

    enum AIR_CMD {
        POWER,
        AC,
        WIND_DN,
        WIND_UP,
        AUTO,
        LOOP,
        REAR_DEFOG,
        MODE
    }

    private byte sendInfo(int i) {
        if (i == 0) {
            return (byte) 1;
        }
        if (i == 1) {
            return (byte) 2;
        }
        if (i != 2) {
            return i != 3 ? (byte) 5 : (byte) 4;
        }
        return (byte) 3;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mDifferent;
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 6) {
            removeMainPrjBtnByName(context, MainAction.SETTING);
        }
        if (this.mDifferent == 4) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_250_original_car_set", "_250_profiles", "_250_personalized_instrument_settings", "_250_and_ambient_lighting_brightness_meter", "_250_power_tailgate", "_250_vehicle_settings"});
        } else {
            getMsgMgr(this.mContext).updateSettings(context, "__250_SAVE_RADAR_DISP", getSettingLeftIndexes(context, "_250_vehicle_settings"), getSettingRightIndex(context, "_250_vehicle_settings", "_278_radar_disp"), SharePreUtil.getIntValue(context, "__250_SAVE_RADAR_DISP", 0));
        }
        if (this.mDifferent != 4) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"car_setting"});
        }
        if (this.mDifferent != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_250_Combination_meter_style_settings"});
        }
        if (this.mDifferent != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_250_auto_lock"});
        }
        if (this.mDifferent != 14) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_250_theme_change"});
        }
        if (this.mDifferent != 8) {
            removeSettingLeftItemByNameList(this.mContext, new String[]{"_250_Drive_assistance"});
        }
        int i2 = this.mDifferent;
        if (i2 != 8 && i2 != 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_250_Language"});
        }
        int i3 = this.mDifferent;
        if (i3 == 8 || i3 == 13) {
            removeSettingRightItemByNameList(this.mContext, new String[]{"_250_language"});
        }
    }

    boolean isHaveCam360() {
        return getCurrentCarId() == 4 || getCurrentCarId() == 5 || getCurrentCarId() == 7;
    }

    void resetRadarAnd360() {
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        parkPageUiSet.setShowRadar(true);
        parkPageUiSet.setShowCusPanoramicView(false);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.airPageUiSet = getAirUiSet(context);
        this.settingPageUiSet = getSettingUiSet(context);
        this.mFrontArea = this.airPageUiSet.getFrontArea();
        sendcarInfo(this.mContext);
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onLeftSeatClick() {
                UiMgr.this.sendAcCmd(AIR_CMD.MODE);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener
            public void onRightSeatClick() {
                UiMgr.this.sendAcCmd(AIR_CMD.MODE);
            }
        });
        this.mFrontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, null, null, this.mOnAirBtnClickListenerFrontBottom});
        this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAcCmd(AIR_CMD.WIND_DN);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAcCmd(AIR_CMD.WIND_UP);
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        if (isHaveCam360()) {
            parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(context, "__250_SAVE_RADAR_DISP", 0) == 1);
            parkPageUiSet.setShowCusPanoramicView(SharePreUtil.getIntValue(context, "__250_SAVE_RADAR_DISP", 0) == 0);
        } else {
            resetRadarAnd360();
        }
        this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 0 && i2 == 12) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
                    Context context2 = context;
                    Toast.makeText(context2, context2.getText(R.string.reset_completed), 0).show();
                }
            }
        });
        if (isHaveCam360()) {
            this.mMsgMgr.initRadarDisp(this.mContext);
        }
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                if (parkPageUiSet.getPanoramicBtnList() != null) {
                    parkPageUiSet.getPanoramicBtnList().clear();
                }
                parkPageUiSet.setShowPanoramic(true);
                ArrayList arrayList = new ArrayList();
                if (UiMgr.this.mDifferent != 5) {
                    if (UiMgr.this.mDifferent == 7) {
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_full", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_front_camera_2d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_2d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_left_camera_2d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_right_camera_2d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_swith_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_front_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_left_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_right_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_left_up_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_right_up_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_left_down_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_right_down_camera_3d", ""));
                        arrayList.add(new ParkPageUiSet.Bean(0, "_250_exit", ""));
                    } else {
                        arrayList = null;
                        parkPageUiSet.setShowPanoramic(false);
                    }
                } else {
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_full", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_front_camera_2d", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_2d", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_left_camera_2d", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_right_camera_2d", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_250_exit", ""));
                }
                parkPageUiSet.setPanoramicBtnList(arrayList);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_250_rear_camera_2d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 5});
                        break;
                    case "_250_rear_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 18});
                        break;
                    case "_250_left_camera_2d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 3});
                        break;
                    case "_250_left_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 19});
                        break;
                    case "_250_exit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 0});
                        MsgMgr.mLast360st = 0;
                        FutureUtil.instance.detectParkPanoramic(false, 0);
                        break;
                    case "_250_full":
                        if (!MsgMgr.mIs360Full) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 6});
                            break;
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -120, 7});
                            break;
                        }
                    case "_250_swith_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 8});
                        break;
                    case "_250_left_up_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 21});
                        break;
                    case "_250_front_camera_2d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 2});
                        break;
                    case "_250_front_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 17});
                        break;
                    case "_250_left_down_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 23});
                        break;
                    case "_250_right_up_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 22});
                        break;
                    case "_250_right_down_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 24});
                        break;
                    case "_250_exit_full":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 7});
                        break;
                    case "_250_right_camera_2d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 4});
                        break;
                    case "_250_right_camera_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -120, 20});
                        break;
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemTouchListener(new OnSettingItemTouchListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemTouchListener
            public void onTouchItem(int i, int i2, View view, MotionEvent motionEvent) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                if (UiMgr.this.getCurrentCarId() == 4) {
                    titleSrn.hashCode();
                    if (titleSrn.equals("str_250_0_4") && motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -122, 1});
                        return;
                    }
                    return;
                }
                titleSrn.hashCode();
                if (titleSrn.equals("str_250_0_4") && motionEvent.getAction() == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 1});
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 55});
                    FutureUtil.instance.detectParkPanoramic(true, 1);
                }
            }
        });
        this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (UiMgr.this.getCurrentCarId() != 4) {
                    UiMgr.this.setItemsCmd(i, i2, i3);
                    return;
                }
                if (i != 0) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.m0x27SettingData, 1, i3 == 1)});
                    return;
                }
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte) DataHandleUtils.setIntByteWithBit(MsgMgr.m0x27SettingData, 0, i3 == 1)});
                    return;
                }
                if (i2 != 3) {
                    return;
                }
                SharePreUtil.setIntValue(UiMgr.this.mContext, "__250_SAVE_RADAR_DISP", i3);
                UiMgr.this.mMsgMgr.initRadarDisp(UiMgr.this.mContext);
                UiMgr uiMgr = UiMgr.this;
                ParkPageUiSet parkPageUiSet2 = uiMgr.getParkPageUiSet(uiMgr.mContext);
                parkPageUiSet2.setShowRadar(i3 == 1);
                parkPageUiSet2.setShowCusPanoramicView(i3 == 0);
            }
        });
        this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._250.UiMgr.8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public void onSwitch(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_250_auto_lock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, 36, (byte) i3});
                        break;
                    case "_250_Lane_Keeping_Assist_System":
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, 39, (byte) i3});
                        break;
                    case "_250_Traffic_sign_recognition":
                        CanbusMsgSender.sendMsg(new byte[]{22, -119, 41, (byte) i3});
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setItemsCmd(int i, int i2, int i3) {
        char c;
        if (i == 0) {
            c = 1;
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 1, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 2, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 3, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 4, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 5, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 6, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 7, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 8, (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 9, (byte) i3});
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 11, (byte) i3});
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 10, (byte) i3});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
            }
        } else if (i == 1) {
            c = 1;
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 16, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 17, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 18, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 19, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 20, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 21, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 22, (byte) i3});
                    break;
            }
        } else if (i == 2) {
            c = 1;
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 14, (byte) i3});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 15, (byte) i3});
            }
        } else if (i == 3) {
            c = 1;
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 23, (byte) (((byte) i3) + 1)});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 24, (byte) i3});
            } else if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 25, (byte) i3});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 26, (byte) (((byte) i3) + 1)});
            }
        } else if (i == 4) {
            c = 1;
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 27, (byte) i3});
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 28, (byte) i3});
            } else if (i2 == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 29, (byte) i3});
            } else if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -119, 30, (byte) (((byte) i3) + 1)});
            }
        } else if (i == 5) {
            switch (i2) {
                case 0:
                    c = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 31, (byte) i3});
                    break;
                case 1:
                    c = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 32, (byte) i3});
                    break;
                case 2:
                    c = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 33, (byte) i3});
                    break;
                case 3:
                    c = 1;
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 34, (byte) i3});
                    break;
                case 4:
                    Log.i("enter radar", "enter radar");
                    SharePreUtil.setIntValue(this.mContext, "__250_SAVE_RADAR_DISP", i3);
                    this.mMsgMgr.initRadarDisp(this.mContext);
                    ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
                    parkPageUiSet.setShowRadar(i3 == 1);
                    parkPageUiSet.setShowCusPanoramicView(i3 == 0);
                    c = 1;
                    getMsgMgr(this.mContext).updateSettings(this.mContext, "__250_SAVE_RADAR_DISP", i, i2, i3);
                    break;
                case 5:
                    Log.i("enter cam", "enter cam");
                    CanbusMsgSender.sendMsg(new byte[]{22, -120, 1});
                    FutureUtil.instance.detectParkPanoramic(true, 1);
                    c = 1;
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 35, (byte) (i3 + 1)});
                    c = 1;
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 37, sendInfo(i3)});
                    c = 1;
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, 38, (byte) (i3 + 1)});
                    c = 1;
                    break;
                default:
                    c = 1;
                    break;
            }
        } else {
            c = 1;
        }
        String titleSrn = this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        titleSrn.hashCode();
        if (titleSrn.equals("_250_Lane_Keeping_Assist_System_Assist_Mode")) {
            byte[] bArr = new byte[4];
            bArr[0] = 22;
            bArr[c] = -119;
            bArr[2] = 40;
            bArr[3] = (byte) i3;
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    void sendAcCmd(AIR_CMD air_cmd) {
        byte[] bArr = {22, -57, 0, 0, 0, 0, 0, 0};
        switch (AnonymousClass13.$SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[air_cmd.ordinal()]) {
            case 1:
                bArr[2] = Byte.MIN_VALUE;
                CanbusMsgSender.sendMsg(bArr);
                bArr[2] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 2:
                bArr[2] = 2;
                CanbusMsgSender.sendMsg(bArr);
                bArr[2] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 3:
                bArr[3] = 1;
                CanbusMsgSender.sendMsg(bArr);
                bArr[3] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 4:
                bArr[3] = 2;
                CanbusMsgSender.sendMsg(bArr);
                bArr[3] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 5:
                bArr[2] = 32;
                CanbusMsgSender.sendMsg(bArr);
                bArr[2] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 6:
                if (GeneralAirData.in_out_cycle) {
                    bArr[2] = 4;
                    CanbusMsgSender.sendMsg(bArr);
                    bArr[2] = 0;
                    CanbusMsgSender.sendMsg(bArr);
                    break;
                } else {
                    bArr[2] = 8;
                    CanbusMsgSender.sendMsg(bArr);
                    bArr[2] = 0;
                    CanbusMsgSender.sendMsg(bArr);
                    break;
                }
            case 7:
                bArr[3] = 4;
                CanbusMsgSender.sendMsg(bArr);
                bArr[3] = 0;
                CanbusMsgSender.sendMsg(bArr);
                break;
            case 8:
                sendAirCommandFrontWindMode(bArr);
                break;
        }
    }

    /* renamed from: com.hzbhd.canbus.car._250.UiMgr$13, reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD;

        static {
            int[] iArr = new int[AIR_CMD.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD = iArr;
            try {
                iArr[AIR_CMD.POWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.AC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.WIND_DN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.WIND_UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.AUTO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.LOOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.REAR_DEFOG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[AIR_CMD.MODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte) i, (byte) i2, 0});
    }

    protected static void sendAirCommandFrontWindMode(byte[] bArr) {
        bArr[4] = new byte[]{16, 8, 4, 2}[mFrontWindMode];
        CanbusMsgSender.sendMsg(bArr);
        int i = mFrontWindMode + 1;
        mFrontWindMode = i;
        if (i >= 4) {
            mFrontWindMode = 0;
        }
    }

    private void sendcarInfo(Context context) {
        int i = this.mDifferent;
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
            return;
        }
        if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
            return;
        }
        if (i == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
        } else if (i == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 8});
        } else {
            if (i != 11) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 9});
        }
    }

    protected int getSettingLeftIndexes(Context context, String str) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(it.next().getTitleSrn())) {
                return i;
            }
        }
        return -1;
    }

    protected int getSettingRightIndex(Context context, String str, String str2) {
        List<SettingPageUiSet.ListBean> list = getPageUiSet(context).getSettingPageUiSet().getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            if (str.equals(next.getTitleSrn())) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
                Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (str2.equals(it2.next().getTitleSrn())) {
                        return i2;
                    }
                }
            }
        }
        return -1;
    }
}
