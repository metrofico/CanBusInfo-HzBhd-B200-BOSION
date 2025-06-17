package com.hzbhd.canbus.car._195;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    protected static final int CAR_ID_360_2015_2017 = 0;
    protected static final int CAR_ID_360_PLUS_2018 = 1;
    protected static final int CAR_ID_EI5_2019_LOW = 11;
    protected static final int CAR_ID_EI5_2019_MID = 12;
    protected static final int CAR_ID_I5_2019 = 10;
    protected static final int CAR_ID_I6_INTERNET_17_18 = 6;
    protected static final int CAR_ID_I6_INTERNET_19 = 8;
    protected static final int CAR_ID_I6_UNINTERNET_17_18 = 7;
    protected static final int CAR_ID_I6_UNINTERNET_19 = 9;
    protected static final int CAR_ID_RX3_2018 = 2;
    protected static final int CAR_ID_RX5_INTERNET_16_18 = 4;
    protected static final int CAR_ID_RX5_UNINTERNET_16_18 = 5;
    protected static final int CAR_ID_RX5_VERTICAL = 3;
    private static int mFrontWindMode;
    private AirPageUiSet airPageUiSet;
    private Context mContext;
    private int mDifferent;
    private FrontArea mFrontArea;
    private MsgMgr mMsgMgr;
    OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.8
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.POWER);
                return;
            }
            if (i == 1) {
                UiMgr.this.sendAcCmd(AIR_CMD.AC);
            } else if (i == 2) {
                UiMgr.this.sendAcCmd(AIR_CMD.LOOP);
            } else {
                if (i != 3) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.AUTO);
            }
        }
    };
    OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                UiMgr.this.sendAcCmd(AIR_CMD.FRONT_DEFOG);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.sendAcCmd(AIR_CMD.REAR_DEFOG);
            }
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
        }
    };
    OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_UP);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_TEMP_DN);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.12
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAcCmd(AIR_CMD.LEFT_SEAT_HEAT);
        }
    };
    OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickMin() {
            UiMgr.this.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
        public void onClickPlus() {
            UiMgr.this.sendAcCmd(AIR_CMD.RIGHT_SEAT_HEAT);
        }
    };

    enum AIR_CMD {
        POWER,
        AC,
        LOOP,
        AUTO,
        LEFT_SEAT_HEAT,
        RIGHT_SEAT_HEAT,
        LEFT_TEMP_UP,
        LEFT_TEMP_DN,
        WIND_DN,
        WIND_UP,
        FRONT_DEFOG,
        REAR_DEFOG,
        MODE
    }

    private void setCarIdCmd() {
        switch (getCurrentCarId()) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
                items_i6_2016_2017();
                break;
            case 8:
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
                items_i6_2019();
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
                items_i5();
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 2});
                items_Ei5();
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
                items_Ei5();
                break;
            default:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
                items_rx3_2018();
                break;
        }
    }

    void setItemsCmds(int i, int i2, int i3) {
        switch (getCurrentCarId()) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                setItems_i6_2016_2017(i, i2, i3);
                break;
            case 8:
            case 9:
                setItems_i6_2019(i, i2, i3);
                break;
            case 10:
                setItems_i5(i, i2, i3);
                break;
            case 11:
            case 12:
                setItems_Ei5(i, i2, i3);
                break;
            default:
                setItemsRx3_2018(i, i2, i3);
                break;
        }
    }

    void items_Ei5() {
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_advanced_driver_assistance", "_194_light_set", "_194_window", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "panorama_setting"});
        removeSettingRightItemByNameList(this.mContext, new String[]{"_194_steering_wheel_feels", "_194_driving_lock", "_194_unlock_mode", "_194_smart_unlock_the_car_near", "_194_driving_luosuo", "_194_partition_temperature_settings", "_194_tire_pressure_reset", "_194_ecodriving", "_194_a_foldable_outside_mirror_automatically"});
    }

    void items_i5() {
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "_194_reset", "_194_driving_assistance", "_194_instrument_brightness"});
        removeSettingRightItemByNameList(this.mContext, new String[]{"_194_a_foldable_outside_mirror_automatically", "_194_partition_temperature_settings", "_194_tire_pressure_reset"});
    }

    void items_i6_2019() {
        removeSettingLeftItemByNameList(this.mContext, new String[]{"_194_lock_control", "_194_lighting_control", "_194_airconditioning_settings", "_194_driving_assistance", "_194_comfortable_and_convenient_2", "_194_driving_maintenance_2", "_194_restore_factory_settings_2", "_194_reset", "_194_advanced_driver_assistance", "_194_instrument_brightness", "_194_window", "panorama_setting"});
        removeSettingRightItemByNameList(this.mContext, new String[]{"_194_steering_wheel_feels", "_194_driving_luosuo", "_194_a_foldable_outside_mirror_automatically", "_194_vehicle_stability_control", "_194_welcome_light", "_194_welcome_lighting_time_of_the_lamp_holder", "_194_driving_lock"});
    }

    void items_i6_2016_2017() {
        removeSettingLeftItemByIndexRange(this.mContext, 0, 10);
        removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
    }

    void items_rx3_2018() {
        removeSettingLeftItem(this.mContext, 12, 12);
        removeSettingLeftItem(this.mContext, 11, 11);
        removeSettingLeftItem(this.mContext, 10, 10);
        removeSettingLeftItem(this.mContext, 9, 9);
        removeSettingLeftItem(this.mContext, 8, 8);
        removeSettingLeftItem(this.mContext, 7, 7);
        removeSettingLeftItem(this.mContext, 6, 6);
        removeSettingLeftItem(this.mContext, 0, 5);
        removeSettingLeftItem(this.mContext, 0, 4);
        removeSettingRightItem(this.mContext, 0, 6, 6);
        removeSettingRightItem(this.mContext, 0, 5, 5);
        removeSettingRightItem(this.mContext, 2, 2, 2);
        removeSettingRightItem(this.mContext, 2, 1, 1);
        removeSettingLeftItemByNameList(this.mContext, new String[]{"panorama_setting"});
    }

    void setItems_Ei5(int i, int i2, int i3) {
        if (i == 0) {
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) i3});
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte) i3});
                return;
            }
        }
        if (i == 1) {
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte) i3});
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte) i3});
                return;
            }
        }
        if (i == 2) {
            if (i2 != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte) i3});
        } else if (i == 4) {
            if (i2 != 0) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 1, (byte) i3});
        } else if (i == 5 && i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, 1, (byte) i3});
        }
    }

    void setItems_i5(int i, int i2, int i3) {
        switch (i) {
            case 0:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte) i3});
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 2, (byte) i3});
                    break;
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte) i3});
                    break;
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
                    break;
                } else if (i2 == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
                    break;
                } else if (i2 == 5) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1, (byte) i3});
                    break;
                }
                break;
            case 1:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte) i3});
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte) i3});
                    break;
                }
                break;
            case 2:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 2, (byte) i3});
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte) i3});
                    break;
                }
                break;
            case 3:
                switch (i2) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 2, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 3, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 4, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 5, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 6, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 7, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 8, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, 9, (byte) i3});
                        break;
                }
            case 4:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 4, (byte) i3});
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 4, (byte) (((byte) i3) + 2)});
                    break;
                } else if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) (((byte) i3) + 2)});
                    break;
                }
                break;
            case 5:
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1, (byte) i3});
                    break;
                }
                break;
            case 6:
                if (i2 == 0) {
                    SharePreUtil.setIntValue(this.mContext, "__195_SAVE_RADAR_DISP", i3);
                    this.mMsgMgr.initRadarDisp(this.mContext);
                    ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
                    parkPageUiSet.setShowRadar(i3 == 1);
                    parkPageUiSet.setShowCusPanoramicView(i3 == 0);
                    break;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                    break;
                }
                break;
        }
    }

    void setItems_i6_2016_2017(int i, int i2, int i3) {
        if (i != 0) {
            if (i == 1 && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
                return;
            }
            return;
        }
        if (i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) i3});
            return;
        }
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte) i3});
        } else if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
        } else {
            if (i2 != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
        }
    }

    void setItems_i6_2019(int i, int i2, int i3) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2 && i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) (((byte) i3) + 2)});
                    return;
                }
                return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
                return;
            } else {
                if (i2 != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 3, (byte) i3});
                return;
            }
        }
        if (i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) i3});
            return;
        }
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte) i3});
        } else if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
        } else {
            if (i2 != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
        }
    }

    void setItemsRx3_2018(int i, int i2, int i3) {
        if (i != 0) {
            if (i != 1) {
                return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 1, (byte) i3});
                return;
            } else if (i2 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 2, (byte) i3});
                return;
            } else {
                if (i2 != 2) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, 3, (byte) i3});
                return;
            }
        }
        if (i2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 1, (byte) i3});
            return;
        }
        if (i2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 2, (byte) i3});
            return;
        }
        if (i2 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, 3, (byte) i3});
        } else if (i2 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 3, (byte) i3});
        } else {
            if (i2 != 4) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 4, (byte) i3});
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        setCarIdCmd();
    }

    boolean isHaveCam360() {
        return getCurrentCarId() == 10;
    }

    void resetRadarAnd360() {
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        parkPageUiSet.setShowRadar(true);
        parkPageUiSet.setShowCusPanoramicView(false);
    }

    public UiMgr(final Context context) {
        this.mContext = context;
        this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        AirPageUiSet airUiSet = getAirUiSet(context);
        this.airPageUiSet = airUiSet;
        this.mFrontArea = airUiSet.getFrontArea();
        this.mDifferent = getCurrentCarId();
        this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.1
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
        this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
        this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight, null, null});
        this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.2
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
            parkPageUiSet.setShowRadar(SharePreUtil.getIntValue(context, "__195_SAVE_RADAR_DISP", 0) == 1);
            parkPageUiSet.setShowCusPanoramicView(SharePreUtil.getIntValue(context, "__195_SAVE_RADAR_DISP", 0) == 0);
        } else {
            resetRadarAnd360();
        }
        parkPageUiSet.setOnBackCameraStatusListener(new OnBackCameraStatusListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnBackCameraStatusListener
            public void addViewToWindows() {
                if (parkPageUiSet.getPanoramicBtnList() != null) {
                    parkPageUiSet.getPanoramicBtnList().clear();
                }
                ArrayList arrayList = new ArrayList();
                if (UiMgr.this.mDifferent == 10) {
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_low_speed_auto_open_360", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_car_line_off", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_2d", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_front_left", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_front_right", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_rear_left", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_rear_right", ""));
                    arrayList.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
                    int i = MsgMgr.cam360Guideline;
                    if (i == 0) {
                        ((ParkPageUiSet.Bean) arrayList.get(1)).setTitleSrn("_194_car_line_off");
                    } else if (i == 1) {
                        ((ParkPageUiSet.Bean) arrayList.get(1)).setTitleSrn("_194_car_line_static");
                    } else if (i == 2) {
                        ((ParkPageUiSet.Bean) arrayList.get(1)).setTitleSrn("_194_car_line_dynamic");
                    } else if (i == 3) {
                        ((ParkPageUiSet.Bean) arrayList.get(1)).setTitleSrn("_194_car_line_static_dynamic");
                    }
                    int i2 = MsgMgr.cam3603dMode;
                    if (i2 == 0) {
                        ((ParkPageUiSet.Bean) arrayList.get(2)).setTitleSrn("_194_2d");
                        arrayList.remove(7);
                        arrayList.remove(7);
                        arrayList.remove(7);
                        arrayList.remove(7);
                    } else if (i2 == 1) {
                        ((ParkPageUiSet.Bean) arrayList.get(2)).setTitleSrn("_194_3d");
                    }
                } else {
                    arrayList = null;
                    parkPageUiSet.setShowPanoramic(false);
                }
                parkPageUiSet.setPanoramicBtnList(arrayList);
            }
        });
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_194_front":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                        break;
                    case "_194_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
                        break;
                    case "_194_car_line_static":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 14});
                        break;
                    case "_194_car_line_dynamic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 15});
                        break;
                    case "_194_low_speed_auto_open_360":
                        byte[] bArr = new byte[3];
                        bArr[0] = 22;
                        bArr[1] = -126;
                        bArr[2] = (byte) (MsgMgr.isCam360LowSpeedOpen ? 16 : 17);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    case "_194_rear_left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
                        break;
                    case "_194_2d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                        break;
                    case "_194_3d":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                        break;
                    case "_194_car_line_off":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                        break;
                    case "_194_exit":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                        MsgMgr.mLast360st = false;
                        break;
                    case "_194_left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
                        break;
                    case "_194_rear":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
                        break;
                    case "_194_front_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
                        break;
                    case "_194_car_line_static_dynamic":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                        break;
                    case "_194_rear_right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
                        break;
                    case "_194_front_left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                        break;
                }
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.5
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
        if (getCurrentCarId() != 2 && getCurrentCarId() != 9 && getCurrentCarId() != 8) {
            this.airPageUiSet.getFrontArea().setShowCenterWheel(false);
        } else {
            this.airPageUiSet.getFrontArea().setShowCenterWheel(true);
        }
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_194_tire_pressure_reset")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 1, 1});
                } else if (titleSrn.equals("str_250_0_4")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._195.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                UiMgr.this.setItemsCmds(i, i2, i3);
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._195.UiMgr$14, reason: invalid class name */
    static /* synthetic */ class AnonymousClass14 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD;

        static {
            int[] iArr = new int[AIR_CMD.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD = iArr;
            try {
                iArr[AIR_CMD.POWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.AC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.LOOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.LEFT_SEAT_HEAT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.RIGHT_SEAT_HEAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.LEFT_TEMP_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.LEFT_TEMP_DN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.WIND_DN.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.WIND_UP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.FRONT_DEFOG.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.REAR_DEFOG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[AIR_CMD.MODE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    void sendAcCmd(AIR_CMD air_cmd) {
        switch (AnonymousClass14.$SwitchMap$com$hzbhd$canbus$car$_195$UiMgr$AIR_CMD[air_cmd.ordinal()]) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 8, 0});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, 0});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 17, 0});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 18, 0});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
                break;
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 2});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
                break;
            case 13:
                sendAirCommandFrontWindMode();
                break;
        }
    }

    protected static void sendAirCommandFrontWindMode() {
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, new byte[]{1, 2, 3, 4}[mFrontWindMode]});
        CanbusMsgSender.sendMsg(new byte[]{22, -118, 16, 0});
        int i = mFrontWindMode + 1;
        mFrontWindMode = i;
        if (i >= 4) {
            mFrontWindMode = 0;
        }
    }
}
