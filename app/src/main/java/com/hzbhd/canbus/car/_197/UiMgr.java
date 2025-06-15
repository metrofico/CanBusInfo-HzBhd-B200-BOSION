package com.hzbhd.canbus.car._197;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    private final String TAG = "_197_UiMgr";
    private int mAirAddition = 204;
    private int mDifferent = getCurrentCarId();
    private MsgMgr mMsgMgr;
    private TimerUtil mTimerUtil;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (!SharePreUtil.getBoolValue(context, "share_197_is_hybird_vehicle", true)) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
        MyApplication.IS_SET = true;
    }

    public UiMgr(final Context context) {
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    UiMgr.this.getTimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._197.UiMgr.1.1
                        int[] dataType = {38, 49, 55};
                        int i = 0;

                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            int i2 = this.i;
                            int[] iArr = this.dataType;
                            if (i2 >= iArr.length) {
                                UiMgr.this.getTimerUtil().stopTimer();
                            } else {
                                this.i = i2 + 1;
                                CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte) iArr[i2], 0});
                            }
                        }
                    }, 0L, 100L);
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_197_update_history_fuel")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_55_0x67_data1_bit64")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte) i3});
                } else if (titleSrn.equals("_197_lock_unlcok_feedback_tone")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_197_clear_15_fuel")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
                } else if (titleSrn.equals("_197_clear_history_fuel")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
                }
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                switch (titleSrn) {
                    case "_118_setting_title_96":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                        break;
                    case "_197_amplifier_mute":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        break;
                    case "_197_unlock_when_operating_key_twice":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case "_197_autolock_by_shift_to_p":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte) i3});
                        break;
                    case "_197_automatic_relock_time":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte) i3});
                        break;
                    case "_197_Driver_seat_open_door_linkage_unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte) i3});
                        break;
                    case "_197_rear_system":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 0, (byte) i3});
                        break;
                    case "geely_daytime_running_lights":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte) i3});
                        break;
                    case "_197_rear_camera_guide":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte) i3});
                        break;
                    case "_197_linkage_between_internal_and_external_circulation_and_auto_key":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte) i3});
                        break;
                    case "amplifier_switch":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        break;
                    case "_197_remote_2_press_unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte) i3});
                        break;
                    case "_186_asl":
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) Math.pow(2.0d, i3 * 3)});
                        break;
                    case "_197_autolock_by_shift_from_p":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte) i3});
                        break;
                    case "_197_rear_system_lock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte) i3});
                        break;
                    case "_197_emergency_flashing_light_response_when_unlocking_locking":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte) i3});
                        break;
                    case "_197_smart_car_lock_and_one_button_start":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte) i3});
                        break;
                    case "_55_0x67_data1_bit32":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte) i3});
                        break;
                    case "_197_smart_car_door_unlock":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte) i3});
                        break;
                    case "_197_autolock_by_speed":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte) i3});
                        break;
                    case "_18_vehicle_setting_item_3_43":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case "_197_linkage_of_air_conditioner_and_auto_key":
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte) i3});
                        break;
                }
            }
        });
        AirPageUiSet airUiSet = getAirUiSet(context);
        dealAirDifferent(context, airUiSet);
        final String[][] lineBtnAction = airUiSet.getFrontArea().getLineBtnAction();
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m302lambda$new$0$comhzbhdcanbuscar_197UiMgr(lineBtnAction, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m303lambda$new$1$comhzbhdcanbuscar_197UiMgr(lineBtnAction, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m304lambda$new$2$comhzbhdcanbuscar_197UiMgr(lineBtnAction, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                this.f$0.m305lambda$new$3$comhzbhdcanbuscar_197UiMgr(lineBtnAction, i);
            }
        }});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(9);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(10);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(11);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(12);
            }
        }});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(20);
            }
        });
        airUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(13);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                UiMgr.this.sendAirCommand(14);
            }
        }});
        final String[][] lineBtnAction2 = airUiSet.getRearArea().getLineBtnAction();
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public void onClickItem(int i) {
                UiMgr.this.sendAirCommand(lineBtnAction2[3][i]);
            }
        }});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.12
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(44);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.13
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                UiMgr.this.sendAirCommand(43);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                UiMgr.this.sendAirCommand(44);
            }
        }});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.14
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                UiMgr.this.sendAirCommand(46);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                UiMgr.this.sendAirCommand(45);
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.15
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) (i + 7)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) (i + 7)});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.16
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass18.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) (i + 2)});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) (i + 2)});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) (i + 2)});
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i});
                }
            }
        });
        final DriverDataPageUiSet driverDataPageUiSet = getDriverDataPageUiSet(context);
        driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._197.UiMgr.17
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                if (i == -1) {
                    int[] leftAndRight = UiMgr.this.getMsgMgr(context).getLeftAndRight("_197_update_history_fuel");
                    driverDataPageUiSet.setLeftPosition(leftAndRight[0]);
                    driverDataPageUiSet.setRightPosition(leftAndRight[1]);
                }
            }
        });
    }

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car-_197-UiMgr, reason: not valid java name */
    /* synthetic */ void m302lambda$new$0$comhzbhdcanbuscar_197UiMgr(String[][] strArr, int i) {
        sendAirCommand(strArr[0][i]);
    }

    /* renamed from: lambda$new$1$com-hzbhd-canbus-car-_197-UiMgr, reason: not valid java name */
    /* synthetic */ void m303lambda$new$1$comhzbhdcanbuscar_197UiMgr(String[][] strArr, int i) {
        sendAirCommand(strArr[1][i]);
    }

    /* renamed from: lambda$new$2$com-hzbhd-canbus-car-_197-UiMgr, reason: not valid java name */
    /* synthetic */ void m304lambda$new$2$comhzbhdcanbuscar_197UiMgr(String[][] strArr, int i) {
        sendAirCommand(strArr[2][i]);
    }

    /* renamed from: lambda$new$3$com-hzbhd-canbus-car-_197-UiMgr, reason: not valid java name */
    /* synthetic */ void m305lambda$new$3$comhzbhdcanbuscar_197UiMgr(String[][] strArr, int i) {
        sendAirCommand(strArr[3][i]);
    }

    /* renamed from: com.hzbhd.canbus.car._197.UiMgr$18, reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(String str) {
        str.hashCode();
        switch (str) {
            case "pollrn_removal":
                sendAirCommand(15);
                break;
            case "windshield_deicing":
                sendAirCommand(49);
                break;
            case "rear_lock":
                sendAirCommand(37);
                break;
            case "rear_defog":
                sendAirCommand(4);
                break;
            case "rear_power":
                sendAirCommand(48);
                break;
            case "blow_positive":
                sendAirCommand(17);
                break;
            case "blow_negative":
                sendAirCommand(42);
                break;
            case "max_front":
                sendAirCommand(38);
                break;
            case "ac":
                sendAirCommand(16);
                break;
            case "auto":
                sendAirCommand(2);
                break;
            case "dual":
                sendAirCommand(8);
                break;
            case "power":
                sendAirCommand(1);
                break;
            case "in_out_cycle":
                sendAirCommand(35);
                break;
            case "auto_wind_mode":
                sendAirCommand(47);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAirCommand(int i) {
        int i2 = this.mAirAddition;
        if (i2 == 34) {
            this.mAirAddition = 204;
        } else if (i2 == 204) {
            this.mAirAddition = 34;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) i, (byte) this.mAirAddition, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        Log.i("ljq", "getMsgMgr: " + this.mMsgMgr);
        return this.mMsgMgr;
    }

    void updateMainActivityItem(Context context, String str, boolean z) {
        List<String> btnAction = getMainUiSet(context).getBtnAction();
        if (btnAction.contains(str) ^ z) {
            if (z) {
                btnAction.add(str);
            } else {
                btnAction.remove(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    private void dealAirDifferent(Context context, AirPageUiSet airPageUiSet) {
        airPageUiSet.setHaveRearArea(false);
        airPageUiSet.getFrontArea().setShowSeatHeat(false);
        removeFrontAirButtonByName(context, AirBtnAction.POLLRN_REMOVAL);
        removeFrontAirButtonByName(context, AirBtnAction.WINDSHIELD_DEICING);
        if ((this.mDifferent & 1) == 0) {
            removeFrontAirButtonByName(context, "dual");
            removeFrontAirButtonByName(context, "auto");
        }
        if (this.mDifferent == 17) {
            removeFrontAirButtonByName(context, "dual");
        }
        int i = this.mDifferent;
        if (i == 32) {
            airPageUiSet.getFrontArea().setWindMaxLevel(4);
        } else if (i == 33) {
            airPageUiSet.getFrontArea().setWindMaxLevel(8);
        }
        if (this.mDifferent != 1) {
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_NEGATIVE);
        }
    }

    void initSettingPage(Context context) {
        int[] leftAndRight = getMsgMgr(context).getLeftAndRight("");
        if (leftAndRight[0] == -1 || leftAndRight[1] == -1) {
            return;
        }
        getMsgMgr(context).updateSettings(leftAndRight[0], leftAndRight[1], 0);
    }

    private int[] getAirBtnPosition(Context context, String str) {
        int[] iArr = {-1, -1};
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        for (int i = 0; i < lineBtnAction.length; i++) {
            String[] strArr = lineBtnAction[i];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (strArr[i2].equals(str)) {
                    iArr[0] = i;
                    iArr[1] = i2;
                }
            }
        }
        return iArr;
    }

    private void addAirBtn(Context context, int i, int i2, String str) {
        String[][] lineBtnAction = getAirUiSet(context).getFrontArea().getLineBtnAction();
        for (String[] strArr : lineBtnAction) {
            for (String str2 : strArr) {
                if (str2.equals(str)) {
                    return;
                }
            }
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(lineBtnAction[i]));
        arrayList.add(i2, str);
        lineBtnAction[i] = (String[]) arrayList.toArray(new String[0]);
    }
}
