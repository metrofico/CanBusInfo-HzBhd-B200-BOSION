package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;




public final class UiMgr extends AbstractUiMgr {
    private final String TAG;
    private final String[][] mDiagitalKeyboardActions;
    private int mDifferentId;
    private int mEachId;
    private final String[][] mFeaturesKeyboardActions;
    private boolean mIsFeaturesKeyboard;
    private final MsgMgr mMsgMgr;

    private final int resolveVolume(int selectpos) {
        if (selectpos == 1) {
            return 10;
        }
        if (selectpos == 2) {
            return 11;
        }
        if (selectpos != 3) {
            return selectpos != 4 ? 9 : 13;
        }
        return 12;
    }

    public UiMgr(final Context context) {

        this.TAG = "_1094_UiMgr";
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

        MsgMgr msgMgr = (MsgMgr) canMsgMgr;
        this.mMsgMgr = msgMgr;
        this.mDiagitalKeyboardActions = new String[][]{new String[]{SyncAction.KEYBOARD_NUMBER_1, SyncAction.KEYBOARD_NUMBER_2, SyncAction.KEYBOARD_NUMBER_3}, new String[]{SyncAction.KEYBOARD_NUMBER_4, SyncAction.KEYBOARD_NUMBER_5, SyncAction.KEYBOARD_NUMBER_6}, new String[]{SyncAction.KEYBOARD_NUMBER_7, SyncAction.KEYBOARD_NUMBER_8, SyncAction.KEYBOARD_NUMBER_9}, new String[]{SyncAction.KEYBOARD_STAR, SyncAction.KEYBOARD_NUMBER_0, SyncAction.KEYBOARD_WELL}, new String[]{SyncAction.KEYBOARD_PICKUP, SyncAction.KEYBOARD_HANGUP, "null"}};
        this.mFeaturesKeyboardActions = new String[][]{new String[]{"null", "up", "null"}, new String[]{"left", SyncAction.KEYBOARD_OK, "right"}, new String[]{"null", "down", "null"}, new String[]{SyncAction.KEYBOARD_INFO, SyncAction.KEYBOARD_MENU, SyncAction.KEYBOARD_DEVICE}, new String[]{SyncAction.KEYBOARD_PREV, SyncAction.KEYBOARD_SHUFF, SyncAction.KEYBOARD_NEXT}};
        this.mIsFeaturesKeyboard = true;
        removeSettingRightItemByNameList(context, new String[]{"_94_distance_prompt"});
        removeSettingRightItemByNameList(context, new String[]{"_94_atmosphere_lamp"});
        msgMgr.getM0x61DataRecord();
        this.mDifferentId = getCurrentCarId();
        this.mEachId = getEachId();
        Log.i("_1094_UiMgr", "mDifferentId: " + this.mDifferentId + "    mEachId: " + this.mEachId);
        if (this.mEachId == 3) {
            removeMainPrjBtnByName(context, "sync");
        }
        if (this.mDifferentId != 19) {
            removeMainPrjBtnByName(context, MainAction.HYBIRD);
        }
        if (this.mDifferentId == 14) {
            removeMainPrjBtnByName(context, MainAction.PANEL_KEY);
        }
        AirPageUiSet airUiSet = getAirUiSet(context);
        if (this.mDifferentId == 13) {
            airUiSet.setHaveRearArea(false);
            removeFrontAirButtonByName(context, "auto");
            removeFrontAirButtonByName(context, "dual");
            removeFrontAirButtonByName(context, AirBtnAction.STEERING_WHEEL_HEATING);
            removeFrontAirButtonByName(context, AirBtnAction.AUTO_WIND_LV);
        }
        final FrontArea frontArea = airUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m1074lambda9$lambda4$lambda0(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m1075lambda9$lambda4$lambda1(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m1076lambda9$lambda4$lambda2(this.f$0, frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m1077lambda9$lambda4$lambda3(this.f$0, frontArea, i);
            }
        }});
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(26);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(27);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(28);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(29);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(31);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(30);
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(7);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$9
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(9);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$10
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(8);
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$1$11
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                this.this$0.sendAirCommand(10);
            }
        }});
        Unit unit = Unit.INSTANCE;
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                UiMgr.m1078lambda9$lambda8$lambda5(this.f$0, rearArea, i);
            }
        }});
        Unit unit2 = Unit.INSTANCE;
        rearArea.setTempSetViewOnUpDownClickListeners(new UiMgr$1$2$3[]{null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$2$3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                this.this$0.sendAirCommand(20);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                this.this$0.sendAirCommand(19);
            }
        }, null});
        Unit unit3 = Unit.INSTANCE;
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                this.this$0.sendAirCommand(21);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                this.this$0.sendAirCommand(22);
            }
        });
        Unit unit4 = Unit.INSTANCE;
        Unit unit5 = Unit.INSTANCE;
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        if (this.mDifferentId == 13) {
            settingUiSet.getList().get(9).getItemList().get(1).setValueSrnArray(CollectionsKt.arrayListOf("close", "_94_khaki", "_250_red", "_250_pink", "ford_raise_color_light_purple", "_250_blue", "_250_green", "_94_azure"));
        }
        int i = this.mDifferentId;
        if (i != 22 && i != 24 && i != 29) {
            removeSettingLeftItemByNameList(context, new String[]{"drive_assist", "_94_safety_warning", "light_settings", "_94_electric_mirror_settings", "_94_key_lock", "_94_wiper", "_94_general_settings", "airSetting", "other_set"});
        }
        if (this.mDifferentId == 24) {
            removeSettingLeftItemByNameList(context, new String[]{"_94_safety_warning", "other_set", "_94_electric_mirror_settings", "_94_general_settings", "airSetting"});
            removeSettingRightItemByNameList(context, new String[]{"_94_lane_keeping_mode", "_94_warning_intensity", "_94_reverse_gear_incoming_car_warning", "_94_tsc_control", "_94_cruise_control", "_94_automatic_engine_shutdown", "_94_headlight_delay", "_94_automatic_high_beam", "_94_switch_prohibited", "_94_voice_feedback", "_94_false_lock_warning", "_94_remote_unlock", "_94_automatically_unlock", "_94_remote_control_on", "_94_remote_control_off", "_94_activate_remote_start", "_94_air_conditioning_control", "_94_cycle", "_94_rain_sensing_wiper", "_94_repeat_wiper_once", "_94_rear_wiper"});
        }
        if (this.mDifferentId == 22) {
            removeSettingRightItemByNameList(context, new String[]{"_94_esp_state", "_94_remote_control", "_334_day_light", "_250_i_went_home_with", "_94_indoor_lamp", "_94_speed_lock", "_94_one_click", "_94_auto_wiper", "_94_automatic_maintenance"});
        }
        if (this.mDifferentId != 14) {
            removeSettingRightItemByNameList(context, new String[]{"seat_set"});
        }
        if (this.mDifferentId != 4) {
            removeSettingRightItemByNameList(context, new String[]{"_94_ambient_light", "_94_active_city", "_94_powerfold_mirrors"});
        }
        if (this.mDifferentId == 29) {
            removeSettingRightItemByNameList(context, new String[]{"_94_tsc_control", "_94_cruise_control", "_94_esp_state", "_94_remote_control", "_334_day_light", "_250_i_went_home_with", "_94_speed_lock", "_94_one_click", "_94_auto_wiper", "_94_automatic_maintenance"});
            removeSettingLeftItemByNameList(context, new String[]{"airSetting", "_94_general_settings"});
        } else {
            removeSettingLeftItemByNameList(context, new String[]{"_94_passengerSideSecureAirbag", "_94_cross_country", "_94_panoramic_and_video"});
            removeSettingRightItemByNameList(context, new String[]{"_94_auto_hold", "_94_lane_centring", "_94_sensitivity", "_94_pre_collision_assist", "_94_evasive_steering_assist", "_94_reverse_brake_assist", "_94_parking_sensirs", "_94_key_detection_alert", "_94_auto_lock", "_94_key_free", "_94_park_lock_control", "_94_hands_free"});
        }
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i2, int i3, int i4) {
                UiMgr.m1067lambda22$lambda10(settingUiSet, this, context, i2, i3, i4);
            }
        });
        settingUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i2, int i3, int i4) {
                UiMgr.m1068lambda22$lambda11(settingUiSet, i2, i3, i4);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i2, int i3, int i4) {
                UiMgr.m1069lambda22$lambda18(settingUiSet, this, i2, i3, i4);
            }
        });
        settingUiSet.setOnSettingItemTouchListener(new OnSettingItemTouchListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemTouchListener
            public final void onTouchItem(int i2, int i3, View view, MotionEvent motionEvent) {
                UiMgr.m1070lambda22$lambda19(settingUiSet, i2, i3, view, motionEvent);
            }
        });
        settingUiSet.setOnSettingItemSeekbarSetTextListener(new OnSettingItemSeekbarSetTextListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener
            public final String onSetText(int i2, int i3, int i4) {
                return UiMgr.m1071lambda22$lambda20(settingUiSet, context, i2, i3, i4);
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i2, int i3) {
                UiMgr.m1072lambda22$lambda21(settingUiSet, i2, i3);
            }
        });
        Unit unit6 = Unit.INSTANCE;
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i2) {
                UiMgr.m1073lambda31$lambda30(parkPageUiSet, this, i2);
            }
        });
        Unit unit7 = Unit.INSTANCE;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$4$1

            /* compiled from: UiMgr.kt */
            
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
                    iArr[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 2;
                    iArr[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 3;
                    iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {

                int i2 = WhenMappings.$EnumSwitchMapping$0[amplifierBand.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, (byte) progress});
                    return;
                }
                if (i2 == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -61, 1, (byte) progress});
                } else if (i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -61, 2, (byte) progress});
                } else {
                    if (i2 != 4) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte) progress});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$4$2

            /* compiled from: UiMgr.kt */
            
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
                    iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
                    iArr[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {

                int i2 = WhenMappings.$EnumSwitchMapping$0[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -61, 3, (byte) (value + 7)});
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -61, 4, (byte) (value + 7)});
                }
            }
        });
        Unit unit8 = Unit.INSTANCE;
        final SyncPageUiSet syncPageUiSet = getSyncPageUiSet(context);
        syncPageUiSet.setOnSyncStateChangeListener(new OnSyncStateChangeListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$5$1
            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onResume() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, 2});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncStateChangeListener
            public void onStop() {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, -126});
            }
        });
        syncPageUiSet.setOnSyncItemClickListener(new OnSyncItemClickListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$5$2
            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onLeftIconClick(String action) {
                int i2;
                String[][] strArr;
                if (action != null) {
                    switch (action.hashCode()) {
                        case 103772132:
                            if (action.equals("media")) {
                                i2 = 2;
                                break;
                            } else {
                                return;
                            }
                        case 106642798:
                            if (action.equals("phone")) {
                                i2 = 3;
                                break;
                            } else {
                                return;
                            }
                        case 112386354:
                            if (action.equals(SyncAction.LEFT_ICON_VOICE)) {
                                i2 = 1;
                                break;
                            } else {
                                return;
                            }
                        case 503739367:
                            if (action.equals(SyncAction.LEFT_ICON_KEYBOARD)) {
                                SyncPageUiSet syncPageUiSet2 = syncPageUiSet;
                                if (this.mIsFeaturesKeyboard) {
                                    this.mIsFeaturesKeyboard = false;
                                    strArr = this.mDiagitalKeyboardActions;
                                } else {
                                    this.mIsFeaturesKeyboard = true;
                                    strArr = this.mFeaturesKeyboardActions;
                                }
                                syncPageUiSet2.setKeyboardActions(strArr);
                                this.mMsgMgr.updateSync(null);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i2});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onListItemClick(int index) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (index + 145)});
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onSoftKeyClick(int index) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (MsgMgr.SyncKey.INSTANCE.getK1().getShort() + index)});
            }

            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnClick(String action) {
                if (action != null) {
                    int iHashCode = action.hashCode();
                    int i2 = 27;
                    switch (iHashCode) {
                        case -1886439238:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_0)) {
                                i2 = 13;
                                break;
                            } else {
                                return;
                            }
                        case -1886439237:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_1)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum1().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439236:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_2)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum2().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439235:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_3)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum3().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439234:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_4)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum4().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439233:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_5)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum5().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439232:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_6)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum6().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439231:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_7)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum7().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439230:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_8)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum8().getShort();
                                break;
                            } else {
                                return;
                            }
                        case -1886439229:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_9)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum9().getShort();
                                break;
                            } else {
                                return;
                            }
                        default:
                            switch (iHashCode) {
                                case -1335157162:
                                    if (!action.equals(SyncAction.KEYBOARD_DEVICE)) {
                                        return;
                                    }
                                    break;
                                case -1224574323:
                                    if (action.equals(SyncAction.KEYBOARD_HANGUP)) {
                                        i2 = 4;
                                        break;
                                    } else {
                                        return;
                                    }
                                case -988476804:
                                    if (action.equals(SyncAction.KEYBOARD_PICKUP)) {
                                        i2 = 5;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3548:
                                    if (action.equals(SyncAction.KEYBOARD_OK)) {
                                        i2 = 12;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3739:
                                    if (action.equals("up")) {
                                        i2 = 10;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 96964:
                                    if (!action.equals("aux")) {
                                        return;
                                    }
                                    break;
                                case 3089570:
                                    if (action.equals("down")) {
                                        i2 = 11;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3237038:
                                    if (action.equals(SyncAction.KEYBOARD_INFO)) {
                                        i2 = 6;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3317767:
                                    if (action.equals("left")) {
                                        i2 = 25;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3347807:
                                    if (action.equals(SyncAction.KEYBOARD_MENU)) {
                                        i2 = 2;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3377907:
                                    if (action.equals(SyncAction.KEYBOARD_NEXT)) {
                                        i2 = 9;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3449395:
                                    if (action.equals(SyncAction.KEYBOARD_PREV)) {
                                        i2 = 8;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3540562:
                                    if (action.equals(SyncAction.KEYBOARD_STAR)) {
                                        i2 = MsgMgr.SyncKey.INSTANCE.getStar().getShort();
                                        break;
                                    } else {
                                        return;
                                    }
                                case 3645646:
                                    if (action.equals(SyncAction.KEYBOARD_WELL)) {
                                        i2 = MsgMgr.SyncKey.INSTANCE.getWell().getShort();
                                        break;
                                    } else {
                                        return;
                                    }
                                case 108511772:
                                    if (action.equals("right")) {
                                        i2 = 26;
                                        break;
                                    } else {
                                        return;
                                    }
                                case 109418880:
                                    if (action.equals(SyncAction.KEYBOARD_SHUFF)) {
                                        i2 = 7;
                                        break;
                                    } else {
                                        return;
                                    }
                                default:
                                    return;
                            }
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i2});
                }
            }

            @Override // com.hzbhd.canbus.interfaces.OnSyncItemClickListener
            public void onKeyboardBtnLongClick(String action) {
                int i2;
                if (action != null) {
                    switch (action.hashCode()) {
                        case -1886439238:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_0)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum0().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439237:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_1)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum1().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439236:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_2)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum2().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439235:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_3)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum3().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439234:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_4)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum4().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439233:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_5)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum5().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439232:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_6)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum6().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439231:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_7)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum7().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439230:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_8)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum8().getLong();
                                break;
                            } else {
                                return;
                            }
                        case -1886439229:
                            if (action.equals(SyncAction.KEYBOARD_NUMBER_9)) {
                                i2 = MsgMgr.SyncKey.INSTANCE.getNum9().getLong();
                                break;
                            } else {
                                return;
                            }
                        default:
                            return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i2});
                }
            }
        });
        Unit unit9 = Unit.INSTANCE;
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._94.UiMgr$6$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int position) {
                List<String> list = panelKeyPageUiSet.getList();
                String str = list != null ? list.get(position) : null;
                if (str != null) {
                    switch (str.hashCode()) {
                        case -1483394203:
                            if (str.equals("_94_parallel_parking")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 1});
                                break;
                            }
                            break;
                        case -1440517953:
                            if (str.equals("_94_parallel_berth_out")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 3});
                                break;
                            }
                            break;
                        case -404582956:
                            if (str.equals("_94_vertical_parking")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 2});
                                break;
                            }
                            break;
                        case 94756344:
                            if (str.equals("close")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -88, 0});
                                break;
                            }
                            break;
                    }
                }
            }
        });
        Unit unit10 = Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-0, reason: not valid java name */
    public static final void m1074lambda9$lambda4$lambda0(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[0][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-1, reason: not valid java name */
    public static final void m1075lambda9$lambda4$lambda1(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[1][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-2, reason: not valid java name */
    public static final void m1076lambda9$lambda4$lambda2(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[2][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1077lambda9$lambda4$lambda3(UiMgr this$0, FrontArea frontArea, int i) {

        String str = frontArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-9$lambda-8$lambda-5, reason: not valid java name */
    public static final void m1078lambda9$lambda8$lambda5(UiMgr this$0, RearArea rearArea, int i) {

        String str = rearArea.getLineBtnAction()[3][i];

        this$0.sendAirCommand(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-22$lambda-10, reason: not valid java name */
    public static final void m1067lambda22$lambda10(SettingPageUiSet settingPageUiSet, UiMgr this$0, Context context, int i, int i2, int i3) {


        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2088909201:
                    if (titleSrn.equals("_94_driver_cushion")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 7, (byte) i3});
                        break;
                    }
                    break;
                case -1966526692:
                    if (titleSrn.equals("_94_sound_effect_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -61, 6, (byte) i3});
                        break;
                    }
                    break;
                case -1928055775:
                    if (titleSrn.equals("_94_cycle")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 90, (byte) i3});
                        break;
                    }
                    break;
                case -1895666868:
                    if (titleSrn.equals("_94_tire_pressure_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 112, (byte) i3});
                        break;
                    }
                    break;
                case -1693206700:
                    if (titleSrn.equals("_94_temperature_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 114, (byte) i3});
                        break;
                    }
                    break;
                case -1495583937:
                    if (titleSrn.equals("_94_indoor_lamp")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 37, (byte) i3});
                        break;
                    }
                    break;
                case -1430029131:
                    if (titleSrn.equals("_94_headlight_delay")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 33, (byte) MsgMgr.INSTANCE.getM0xC90x21ItemValues()[i3]});
                        break;
                    }
                    break;
                case -1206262650:
                    if (titleSrn.equals("_94_mirror_fold_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 6, (byte) i3});
                        break;
                    }
                    break;
                case -1093208114:
                    if (titleSrn.equals("_81_turn_signals_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 3), 0});
                        break;
                    }
                    break;
                case -984400282:
                    if (titleSrn.equals("_94_charging_indicator")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 25), 0});
                        break;
                    }
                    break;
                case -922538678:
                    if (titleSrn.equals("ford_range_unit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 14), 0});
                        break;
                    }
                    break;
                case -916760944:
                    if (titleSrn.equals("_94_go_home_with_me_duration")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 9, (byte) i3});
                        break;
                    }
                    break;
                case -903809344:
                    if (titleSrn.equals("speed_linkage_volume_adjustment")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -61, 5, (byte) i3});
                        break;
                    }
                    break;
                case -807237109:
                    if (titleSrn.equals("_94_warning_intensity")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, (byte) i3});
                        break;
                    }
                    break;
                case -759500786:
                    if (titleSrn.equals("_94_position")) {
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -61;
                        bArr[2] = 7;
                        bArr[3] = (byte) (i3 != 1 ? i3 != 2 ? 0 : 1 : 2);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    }
                    break;
                case -711254215:
                    if (titleSrn.equals("_94_welcome_lamp_duration")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 2, (byte) i3});
                        break;
                    }
                    break;
                case -688412993:
                    if (titleSrn.equals("_94_air_conditioning_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 88, (byte) i3});
                        break;
                    }
                    break;
                case -426649003:
                    if (titleSrn.equals("_94_passenger")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 11, (byte) i3});
                        break;
                    }
                    break;
                case -205537133:
                    if (titleSrn.equals("_94_lane_keeping_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 0, (byte) i3});
                        break;
                    }
                    break;
                case 72545322:
                    if (titleSrn.equals("_94_measure_unit")) {
                        byte[] bArr2 = new byte[4];
                        bArr2[0] = 22;
                        bArr2[1] = -55;
                        bArr2[2] = 113;
                        bArr2[3] = (byte) (i3 != 1 ? i3 != 2 ? 0 : 3 : 2);
                        CanbusMsgSender.sendMsg(bArr2);
                        break;
                    }
                    break;
                case 102584022:
                    if (titleSrn.equals("language_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, (byte) MsgMgr.INSTANCE.getMLanguageIndexs()[i3], 0});
                        break;
                    }
                    break;
                case 108612084:
                    if (titleSrn.equals("_18_vehicle_setting_item_3_210")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0, (byte) i3});
                        break;
                    }
                    break;
                case 301736346:
                    if (titleSrn.equals("_94_sensitivity")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 18, (byte) i3});
                        break;
                    }
                    break;
                case 341432570:
                    if (titleSrn.equals("_94_welcome_lamp")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 38, (byte) MsgMgr.INSTANCE.getM0xC90x26ItemValues()[i3]});
                        break;
                    }
                    break;
                case 378909815:
                    if (titleSrn.equals("_94_passenger_cushion")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 9, (byte) i3});
                        break;
                    }
                    break;
                case 382166093:
                    if (titleSrn.equals("_94_driver")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 10, (byte) i3});
                        break;
                    }
                    break;
                case 388217829:
                    if (titleSrn.equals("_94_passenger_backrest")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 8, (byte) i3});
                        break;
                    }
                    break;
                case 697133026:
                    if (titleSrn.equals("_250_i_went_home_with")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 36, (byte) i3});
                        break;
                    }
                    break;
                case 768246420:
                    if (titleSrn.equals("_94_indoor_lamp_duration")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 5, (byte) i3});
                        break;
                    }
                    break;
                case 946029336:
                    if (titleSrn.equals("_94_cruise_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 4, (byte) i3});
                        break;
                    }
                    break;
                case 1195239661:
                    if (titleSrn.equals("_94_driver_backrest")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -89, 6, (byte) i3});
                        break;
                    }
                    break;
                case 1274456812:
                    if (titleSrn.equals("_94_rear_defog_duration")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 7, (byte) i3});
                        break;
                    }
                    break;
                case 1456389983:
                    if (titleSrn.equals("_279_vehicle_config")) {
                        this$0.mMsgMgr.setConfiguration(context, i3);
                        this$0.mMsgMgr.updateSettings("_279_vehicle_config", Integer.valueOf(i3));
                        break;
                    }
                    break;
                case 1698388376:
                    if (titleSrn.equals("_94_remote_unlock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 83, (byte) i3});
                        break;
                    }
                    break;
                case 1708492483:
                    if (titleSrn.equals("_270_setting_12")) {
                        this$0.send0xC5Command(0, this$0.mDifferentId == 13 ? i3 : i3 + 1);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-22$lambda-11, reason: not valid java name */
    public static final void m1068lambda22$lambda11(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2130367498:
                    if (titleSrn.equals("_94_blind_spot_monitoring")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 19, (byte) i3});
                        break;
                    }
                    break;
                case -2115859702:
                    if (titleSrn.equals("_94_auto_hold")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 8, (byte) i3});
                        break;
                    }
                    break;
                case -2115740810:
                    if (titleSrn.equals("_94_auto_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 94, (byte) i3});
                        break;
                    }
                    break;
                case -2065696511:
                    if (titleSrn.equals("_118_setting_title_49")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -90, (byte) i3, 0});
                        break;
                    }
                    break;
                case -2000321219:
                    if (titleSrn.equals("_81_hill_start_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (22 - i3), 0});
                        break;
                    }
                    break;
                case -1949092471:
                    if (titleSrn.equals("_94_remote_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 7, (byte) i3});
                        break;
                    }
                    break;
                case -1914871750:
                    if (titleSrn.equals("_94_automatic_maintenance")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 100, (byte) i3});
                        break;
                    }
                    break;
                case -1898253674:
                    if (titleSrn.equals("_94_powerfold_mirrors")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (35 - i3), 0});
                        break;
                    }
                    break;
                case -1847101398:
                    if (titleSrn.equals("_94_one_click")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 92, (byte) i3});
                        break;
                    }
                    break;
                case -1775953625:
                    if (titleSrn.equals("remote_window_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 3, (byte) i3});
                        break;
                    }
                    break;
                case -1750834091:
                    if (titleSrn.equals("_94_remote_control_on")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 85, (byte) i3});
                        break;
                    }
                    break;
                case -1730839467:
                    if (titleSrn.equals("_94_rear_wiper")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 98, (byte) i3});
                        break;
                    }
                    break;
                case -1655599903:
                    if (titleSrn.equals("_94_steering_air")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 89, (byte) i3});
                        break;
                    }
                    break;
                case -1527392571:
                    if (titleSrn.equals("_94_automatically_unlock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 84, (byte) i3});
                        break;
                    }
                    break;
                case -1482315655:
                    if (titleSrn.equals("ford_alert_tone")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 7), 0});
                        break;
                    }
                    break;
                case -1460835735:
                    if (titleSrn.equals("_94_distance_prompt")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 16, (byte) i3});
                        break;
                    }
                    break;
                case -1413016952:
                    if (titleSrn.equals("_94_reversing_video_hold")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -82, (byte) i3, 0});
                        break;
                    }
                    break;
                case -1383316679:
                    if (titleSrn.equals("_94_park_lock_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -112, (byte) i3});
                        break;
                    }
                    break;
                case -1336915177:
                    if (titleSrn.equals("_94_voice_feedback")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 81, (byte) i3});
                        break;
                    }
                    break;
                case -1314502145:
                    if (titleSrn.equals("_94_active_city")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (31 - i3), 0});
                        break;
                    }
                    break;
                case -1281339383:
                    if (titleSrn.equals("_334_day_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 35, (byte) i3});
                        break;
                    }
                    break;
                case -1208161818:
                    if (titleSrn.equals("_94_reverse_gear_incoming_car_warning")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte) i3});
                        break;
                    }
                    break;
                case -1153463264:
                    if (titleSrn.equals("_94_auto_wiper")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 99, (byte) i3});
                        break;
                    }
                    break;
                case -1006589135:
                    if (titleSrn.equals("_94_key_free")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 95, (byte) i3});
                        break;
                    }
                    break;
                case -945464094:
                    if (titleSrn.equals("_94_pre_collision_assist_drive")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 11, (byte) i3});
                        break;
                    }
                    break;
                case -868074322:
                    if (titleSrn.equals("_94_sensitivity_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 10, (byte) i3});
                        break;
                    }
                    break;
                case -850356856:
                    if (titleSrn.equals("_94_evasive_steering_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case -726963990:
                    if (titleSrn.equals("_94_ambient_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (33 - i3), 0});
                        break;
                    }
                    break;
                case -601152713:
                    if (titleSrn.equals("_94_electric_trunk")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 48, (byte) i3});
                        break;
                    }
                    break;
                case -484785208:
                    if (titleSrn.equals("_94_speed_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 91, (byte) i3});
                        break;
                    }
                    break;
                case -473747180:
                    if (titleSrn.equals("_94_repeat_wiper_once")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 97, (byte) i3});
                        break;
                    }
                    break;
                case -269631444:
                    if (titleSrn.equals("_94_instrument_direction_key")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (24 - i3), 0});
                        break;
                    }
                    break;
                case -237758202:
                    if (titleSrn.equals("_94_lane_centring")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 9, (byte) i3});
                        break;
                    }
                    break;
                case -118618073:
                    if (titleSrn.equals("_94_fatigue_driving_warning")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 20, (byte) i3});
                        break;
                    }
                    break;
                case -43539982:
                    if (titleSrn.equals("_284_setting_item_49")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 4, (byte) i3});
                        break;
                    }
                    break;
                case -18891618:
                    if (titleSrn.equals("_81_rain_sensor")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -91, (byte) i3, 0});
                        break;
                    }
                    break;
                case 140719972:
                    if (titleSrn.equals("_94_switch_prohibited")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 80, (byte) i3});
                        break;
                    }
                    break;
                case 359304527:
                    if (titleSrn.equals("_94_esp_state")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 6, (byte) i3});
                        break;
                    }
                    break;
                case 530702356:
                    if (titleSrn.equals("_94_active_braking")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 17, (byte) i3});
                        break;
                    }
                    break;
                case 592419048:
                    if (titleSrn.equals("_94_automatic_folding")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 64, (byte) i3});
                        break;
                    }
                    break;
                case 627547641:
                    if (titleSrn.equals("_94_intelligent_unlock_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1, (byte) i3});
                        break;
                    }
                    break;
                case 632066595:
                    if (titleSrn.equals("speed_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 8, (byte) i3});
                        break;
                    }
                    break;
                case 660283455:
                    if (titleSrn.equals("_94_hill_descent_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -80, (byte) i3});
                        break;
                    }
                    break;
                case 722440927:
                    if (titleSrn.equals("_94_rear_drive_acceleration_lock")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -79, (byte) i3});
                        break;
                    }
                    break;
                case 791293245:
                    if (titleSrn.equals("_94_key_detection_alert")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 93, (byte) i3});
                        break;
                    }
                    break;
                case 808582857:
                    if (titleSrn.equals("_94_false_lock_warning")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 82, (byte) i3});
                        break;
                    }
                    break;
                case 880229735:
                    if (titleSrn.equals("_94_parking_sensirs")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 14, (byte) i3});
                        break;
                    }
                    break;
                case 1085590749:
                    if (titleSrn.equals("_94_tsc_control")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 3, (byte) i3});
                        break;
                    }
                    break;
                case 1130159007:
                    if (titleSrn.equals("_94_reverse_brake_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    }
                    break;
                case 1154526995:
                    if (titleSrn.equals("_94_automatic_high_beam")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 34, (byte) i3});
                        break;
                    }
                    break;
                case 1229285653:
                    if (titleSrn.equals("_81_traction_control_system")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (2 - i3), 0});
                        break;
                    }
                    break;
                case 1276324186:
                    if (titleSrn.equals("_94_automatic_engine_shutdown")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 5, (byte) i3});
                        break;
                    }
                    break;
                case 1329319848:
                    if (titleSrn.equals("_94_passenger_airbag")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -96, (byte) i3});
                        break;
                    }
                    break;
                case 1391591368:
                    if (titleSrn.equals("_94_fog_light_steering_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 39, (byte) i3});
                        break;
                    }
                    break;
                case 1558717881:
                    if (titleSrn.equals("_94_remote_control_off")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 86, (byte) i3});
                        break;
                    }
                    break;
                case 1567327775:
                    if (titleSrn.equals("parking_assistance")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -83, (byte) i3, 0});
                        break;
                    }
                    break;
                case 1635225648:
                    if (titleSrn.equals("_94_activate_remote_start")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 87, (byte) i3});
                        break;
                    }
                    break;
                case 1715286958:
                    if (titleSrn.equals("ford_message_tone")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (i3 + 5), 0});
                        break;
                    }
                    break;
                case 1846162417:
                    if (titleSrn.equals("_94_rain_sensing_wiper")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 96, (byte) i3});
                        break;
                    }
                    break;
                case 2109787601:
                    if (titleSrn.equals("_218_setting_0_4")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, (byte) (20 - i3), 0});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-22$lambda-18, reason: not valid java name */
    public static final void m1069lambda22$lambda18(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1315315632:
                    if (titleSrn.equals("_94_atmosphere_lamp")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, 32, (byte) i3});
                        break;
                    }
                    break;
                case -522237854:
                    if (titleSrn.equals("_94_driver_low")) {
                        byte[] bArr = new byte[5];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = -89;
                        bArr[3] = 2;
                        int low = this$0.mMsgMgr.getDriver().getLow() - i3;
                        bArr[4] = (byte) (low <= 0 ? low < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    }
                    break;
                case -522237098:
                    if (titleSrn.equals("_94_driver_mid")) {
                        byte[] bArr2 = new byte[5];
                        bArr2[0] = 22;
                        bArr2[1] = -58;
                        bArr2[2] = -89;
                        bArr2[3] = 1;
                        int middle = this$0.mMsgMgr.getDriver().getMiddle() - i3;
                        bArr2[4] = (byte) (middle <= 0 ? middle < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr2);
                        break;
                    }
                    break;
                case 587065676:
                    if (titleSrn.equals("_94_passenger_high")) {
                        byte[] bArr3 = new byte[5];
                        bArr3[0] = 22;
                        bArr3[1] = -58;
                        bArr3[2] = -89;
                        bArr3[3] = 3;
                        int high = this$0.mMsgMgr.getPassenger().getHigh() - i3;
                        bArr3[4] = (byte) (high <= 0 ? high < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr3);
                        break;
                    }
                    break;
                case 988772970:
                    if (titleSrn.equals("_94_passenger_low")) {
                        byte[] bArr4 = new byte[5];
                        bArr4[0] = 22;
                        bArr4[1] = -58;
                        bArr4[2] = -89;
                        bArr4[3] = 5;
                        int low2 = this$0.mMsgMgr.getPassenger().getLow() - i3;
                        bArr4[4] = (byte) (low2 <= 0 ? low2 < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr4);
                        break;
                    }
                    break;
                case 988773726:
                    if (titleSrn.equals("_94_passenger_mid")) {
                        byte[] bArr5 = new byte[5];
                        bArr5[0] = 22;
                        bArr5[1] = -58;
                        bArr5[2] = -89;
                        bArr5[3] = 4;
                        int middle2 = this$0.mMsgMgr.getPassenger().getMiddle() - i3;
                        bArr5[4] = (byte) (middle2 <= 0 ? middle2 < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr5);
                        break;
                    }
                    break;
                case 990370388:
                    if (titleSrn.equals("_94_driver_high")) {
                        byte[] bArr6 = new byte[5];
                        bArr6[0] = 22;
                        bArr6[1] = -58;
                        bArr6[2] = -89;
                        bArr6[3] = 0;
                        int high2 = this$0.mMsgMgr.getDriver().getHigh() - i3;
                        bArr6[4] = (byte) (high2 <= 0 ? high2 < 0 ? 1 : 0 : 2);
                        CanbusMsgSender.sendMsg(bArr6);
                        break;
                    }
                    break;
                case 1708492484:
                    if (titleSrn.equals("_270_setting_13")) {
                        this$0.send0xC5Command(1, i3 * 5);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-22$lambda-19, reason: not valid java name */
    public static final void m1070lambda22$lambda19(SettingPageUiSet settingPageUiSet, int i, int i2, View view, MotionEvent motionEvent) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (!Intrinsics.areEqual(titleSrn, "_94_cockpit_fresh_air")) {
            if (Intrinsics.areEqual(titleSrn, "_94_tire_reset") && motionEvent.getAction() == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, 18, 0});
                return;
            }
            return;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -55, ByteCompanionObject.MIN_VALUE, 1});
        } else {
            if (action != 1) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -55, ByteCompanionObject.MIN_VALUE, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
    
        if (r1.equals("_94_driver_high") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0046, code lost:
    
        if (r1.equals("_94_passenger_mid") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
    
        if (r1.equals("_94_passenger_low") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        if (r1.equals("_94_passenger_high") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        if (r1.equals("_94_driver_mid") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
    
        if (r1.equals("_94_driver_low") == false) goto L32;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-22$lambda-20, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String m1071lambda22$lambda20(com.hzbhd.canbus.ui_set.SettingPageUiSet r1, android.content.Context r2, int r3, int r4, int r5) {
        /*
            java.lang.String r0 = "$context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.List r1 = r1.getList()
            java.lang.Object r1 = r1.get(r3)
            com.hzbhd.canbus.ui_set.SettingPageUiSet$ListBean r1 = (com.hzbhd.canbus.ui_set.SettingPageUiSet.ListBean) r1
            java.util.List r1 = r1.getItemList()
            java.lang.Object r1 = r1.get(r4)
            com.hzbhd.canbus.ui_set.SettingPageUiSet$ListBean$ItemListBean r1 = (com.hzbhd.canbus.ui_set.SettingPageUiSet.ListBean.ItemListBean) r1
            java.lang.String r1 = r1.getTitleSrn()
            if (r1 == 0) goto L7b
            int r3 = r1.hashCode()
            switch(r3) {
                case -522237854: goto L64;
                case -522237098: goto L5b;
                case 587065676: goto L52;
                case 988772970: goto L49;
                case 988773726: goto L40;
                case 990370388: goto L37;
                case 1708492484: goto L27;
                default: goto L26;
            }
        L26:
            goto L7b
        L27:
            java.lang.String r2 = "_270_setting_13"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L30
            goto L7b
        L30:
            int r5 = r5 * 5
            java.lang.String r1 = java.lang.String.valueOf(r5)
            goto L7f
        L37:
            java.lang.String r3 = "_94_driver_high"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L40:
            java.lang.String r3 = "_94_passenger_mid"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L49:
            java.lang.String r3 = "_94_passenger_low"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L52:
            java.lang.String r3 = "_94_passenger_high"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L5b:
            java.lang.String r3 = "_94_driver_mid"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L64:
            java.lang.String r3 = "_94_driver_low"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L6d
            goto L7b
        L6d:
            if (r5 != 0) goto L76
            java.lang.String r1 = "close"
            java.lang.String r1 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r2, r1)
            goto L7f
        L76:
            java.lang.String r1 = java.lang.String.valueOf(r5)
            goto L7f
        L7b:
            java.lang.String r1 = java.lang.String.valueOf(r5)
        L7f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._94.UiMgr.m1071lambda22$lambda20(com.hzbhd.canbus.ui_set.SettingPageUiSet, android.content.Context, int, int, int):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-22$lambda-21, reason: not valid java name */
    public static final void m1072lambda22$lambda21(SettingPageUiSet settingPageUiSet, int i, int i2) {
        if (Intrinsics.areEqual(settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn(), "_94_reset_all")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -61, -1, 0});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-31$lambda-30, reason: not valid java name */
    public static final void m1073lambda31$lambda30(ParkPageUiSet parkPageUiSet, UiMgr this$0, int i) {

        String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2035380262:
                    if (titleSrn.equals("_94_left_back")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -58, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(5).intValue())});
                        break;
                    }
                    break;
                case -1925482140:
                    if (titleSrn.equals("_94_front")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -62, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(1).intValue())});
                        break;
                    }
                    break;
                case -1609910059:
                    if (titleSrn.equals("_94_rear_view_split_screen")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -81, (byte) (1 - ((this$0.mMsgMgr.getMReversingValues() >> 3) & 1)), 0});
                        break;
                    }
                    break;
                case -1441128958:
                    if (titleSrn.equals("_94_front_left")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -63, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(0).intValue())});
                        break;
                    }
                    break;
                case -1309174196:
                    if (titleSrn.equals("_94_back")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -59, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(4).intValue())});
                        break;
                    }
                    break;
                case -342621887:
                    if (titleSrn.equals("_94_right_front")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -61, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(2).intValue())});
                        break;
                    }
                    break;
                case -335060217:
                    if (titleSrn.equals("_94_whole_car")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 0});
                        break;
                    }
                    break;
                case 91682403:
                    if (titleSrn.equals("_81_video_restore")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 18, 0});
                        break;
                    }
                    break;
                case 352548330:
                    if (titleSrn.equals("_94_front_and_left")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 2});
                        break;
                    }
                    break;
                case 563949675:
                    if (titleSrn.equals("_94_front_panoramic")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 1});
                        break;
                    }
                    break;
                case 613958680:
                    if (titleSrn.equals("_94_back_dip")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 4});
                        break;
                    }
                    break;
                case 1689670663:
                    if (titleSrn.equals("_81_video_enlarge")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -85, 19, 0});
                        break;
                    }
                    break;
                case 1789927119:
                    if (titleSrn.equals("_94_right_back")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -60, (byte) (1 - this$0.mMsgMgr.getListGeneralParkBigData().get(3).intValue())});
                        break;
                    }
                    break;
                case 2116569939:
                    if (titleSrn.equals("_94_back_panoramic")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -55, -64, 3});
                        break;
                    }
                    break;
            }
        }
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {

        return this.mMsgMgr.getActivePark(context).getMPanoramicView();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void sendAirCommand(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1786872896: goto Lb9;
                case -1470045433: goto Lae;
                case -1423573049: goto La3;
                case -712865050: goto L97;
                case -631663038: goto L8c;
                case -620266838: goto L80;
                case -246396018: goto L74;
                case 3106: goto L69;
                case 3005871: goto L5b;
                case 3094652: goto L4d;
                case 106858757: goto L40;
                case 341572893: goto L32;
                case 756225563: goto L25;
                case 1434490075: goto L17;
                case 1434539597: goto L9;
                default: goto L7;
            }
        L7:
            goto Lc5
        L9:
            java.lang.String r0 = "blow_head"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L13
            goto Lc5
        L13:
            r2 = 33
            goto Lc6
        L17:
            java.lang.String r0 = "blow_foot"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L21
            goto Lc5
        L21:
            r2 = 34
            goto Lc6
        L25:
            java.lang.String r0 = "in_out_cycle"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L2f
            goto Lc5
        L2f:
            r2 = 3
            goto Lc6
        L32:
            java.lang.String r0 = "blow_window"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L3c
            goto Lc5
        L3c:
            r2 = 32
            goto Lc6
        L40:
            java.lang.String r0 = "power"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4a
            goto Lc5
        L4a:
            r2 = 1
            goto Lc6
        L4d:
            java.lang.String r0 = "dual"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L57
            goto Lc5
        L57:
            r2 = 24
            goto Lc6
        L5b:
            java.lang.String r0 = "auto"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L65
            goto Lc5
        L65:
            r2 = 23
            goto Lc6
        L69:
            java.lang.String r0 = "ac"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L72
            goto Lc5
        L72:
            r2 = 2
            goto Lc6
        L74:
            java.lang.String r0 = "max_front"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L7d
            goto Lc5
        L7d:
            r2 = 25
            goto Lc6
        L80:
            java.lang.String r0 = "rear_power"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L89
            goto Lc5
        L89:
            r2 = 17
            goto Lc6
        L8c:
            java.lang.String r0 = "rear_defog"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L95
            goto Lc5
        L95:
            r2 = 6
            goto Lc6
        L97:
            java.lang.String r0 = "rear_lock"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto La0
            goto Lc5
        La0:
            r2 = 18
            goto Lc6
        La3:
            java.lang.String r0 = "ac_max"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto Lac
            goto Lc5
        Lac:
            r2 = 4
            goto Lc6
        Lae:
            java.lang.String r0 = "front_defog"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto Lb7
            goto Lc5
        Lb7:
            r2 = 5
            goto Lc6
        Lb9:
            java.lang.String r0 = "steering_wheel_heating"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto Lc2
            goto Lc5
        Lc2:
            r2 = 11
            goto Lc6
        Lc5:
            r2 = 0
        Lc6:
            r1.sendAirCommand(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._94.UiMgr.sendAirCommand(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int command) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, -84, (byte) command, 0});
    }

    private final void send0xC5Command(int dataIndex, int value) {
        Log.i(this.TAG, "send0xC5Command: " + dataIndex + "  " + value);
        byte[] m0x61DataRecord = this.mMsgMgr.getM0x61DataRecord();
        m0x61DataRecord[0] = 22;
        m0x61DataRecord[1] = -59;
        m0x61DataRecord[dataIndex + 2] = (byte) value;
        CanbusMsgSender.sendMsg(m0x61DataRecord);
    }
}
