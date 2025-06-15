package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import org.apache.log4j.net.SyslogAppender;

import kotlin.ranges.RangesKt;
import nfore.android.bt.res.NfDef;


public final class UiMgr extends AbstractUiMgr {
    private static final String TAG = "_3_UiMgr";
    private final Handler mHandler;
    private final MsgMgr mMsgMgr;
    private final MyPanoramicView mPanoramicView;

    /* JADX INFO: Access modifiers changed from: private */
    public final int cycle(int i, int i2) {
        if (i >= i2) {
            return 0;
        }
        return i + 1;
    }

    /* renamed from: switch, reason: not valid java name */
    private final int m400switch(boolean z) {
        return !z ? 1 : 0;
    }

    public UiMgr(final Context context) {

        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);

        this.mMsgMgr = (MsgMgr) canMsgMgr;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mPanoramicView = new MyPanoramicView(context);
        AirPageUiSet airUiSet = getAirUiSet(context);
        final FrontArea frontArea = airUiSet.getFrontArea();
        OnAirBtnClickListener[] onAirBtnClickListenerArr = new OnAirBtnClickListener[4];
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            onAirBtnClickListenerArr[i] = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
                public final void onClickItem(int i2) {
                    String str = frontArea.getLineBtnAction()[finalI][i2];
                    sendAirCommand(str);
                }
            };
        }
        frontArea.setOnAirBtnClickListeners(onAirBtnClickListenerArr);
        frontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                sendAirCommand(184, 1);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                sendAirCommand(184, 0);
            }
        }, null, new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                sendAirCommand(HotKeyConstant.K_CAN_CONFIG, 1);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                sendAirCommand(HotKeyConstant.K_CAN_CONFIG, 0);
            }
        }});
        frontArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                sendAirCommand(HotKeyConstant.K_HOUR, RangesKt.coerceAtLeast(GeneralAirData.front_wind_level - 1, 0));
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                sendAirCommand(HotKeyConstant.K_HOUR, RangesKt.coerceAtMost(GeneralAirData.front_wind_level + 1, frontArea.getWindMaxLevel()));
            }
        });
        frontArea.setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                sendAirCommand(173, cycle(GeneralAirData.front_left_seat_heat_level, frontArea.getSeatHeatSrnArray().length - 1));
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                sendAirCommand(174, cycle(GeneralAirData.front_right_seat_heat_level, frontArea.getSeatHeatSrnArray().length - 1));
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                sendAirCommand(191, cycle(GeneralAirData.front_left_seat_cold_level, frontArea.getSeatColdSrnArray().length - 1));
            }
        }, new OnAirSeatHeatColdMinPlusClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$1$8
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickMin() {
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener
            public void onClickPlus() {
                sendAirCommand(192, cycle(GeneralAirData.front_right_seat_cold_level, frontArea.getSeatColdSrnArray().length - 1));
            }
        }});
        final RearArea rearArea = airUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i2) {
                String str = rearArea.getLineBtnAction()[0][i2];
                sendAirCommand(str);
            }
        }, null, null, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i2) {
                String str = rearArea.getLineBtnAction()[3][i2];
                sendAirCommand(str);
            }
        }});

        rearArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener() {
            @Override
            // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                sendAirCommand(186, 1);
            }

            @Override
            // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                sendAirCommand(186, 0);
            }
        }});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                sendAirCommand(40, RangesKt.coerceAtLeast(GeneralAirData.rear_wind_level - 1, 0));
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                sendAirCommand(40, RangesKt.coerceAtMost(GeneralAirData.rear_wind_level + 1, rearArea.getWindMaxLevel()));
            }
        });
        getSettingUiSet(context).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public final void onClickItem(int i2, int i3, int i4) {
                UiMgr.m393lambda12$lambda7(getSettingUiSet(context), i2, i3, i4);
            }
        });
        getSettingUiSet(context).setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                String titleSrn = getSettingUiSet(context).getList().get(i).getItemList().get(i2).getTitleSrn();
                if (titleSrn != null) {
                    switch (titleSrn.hashCode()) {
                        case -2087556778:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mirror_wipers_auto_wiping")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 98, (byte) i3});
                                break;
                            }
                            break;
                        case -1955990015:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_travelling_time")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -124, (byte) i3});
                                break;
                            }
                            break;
                        case -1835574973:
                            if (titleSrn.equals("_2_settings_lower_while_reversing")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte) i3});
                                break;
                            }
                            break;
                        case -1792009119:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_eco_tips")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -125, (byte) i3});
                                break;
                            }
                            break;
                        case -1685396382:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_distance_travelled")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -123, (byte) i3});
                                break;
                            }
                            break;
                        case -1626662470:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_current_consumption")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, Byte.MIN_VALUE, (byte) i3});
                                break;
                            }
                            break;
                        case -1552511302:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_driver_assistance_lane_assisit")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) i3});
                                break;
                            }
                            break;
                        case -1454223666:
                            if (titleSrn.equals("inductive_trunk_lid")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 116, (byte) i3});
                                break;
                            }
                            break;
                        case -1417441837:
                            if (titleSrn.equals("parking_brake_function")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte) i3});
                                break;
                            }
                            break;
                        case -1369742382:
                            if (titleSrn.equals("driving_out_of_the_parking_space")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte) i3});
                                break;
                            }
                            break;
                        case -1274837915:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_con_consumers")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -126, (byte) i3});
                                break;
                            }
                            break;
                        case -1248570349:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_avg_consumption")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -127, (byte) i3});
                                break;
                            }
                            break;
                        case -1062929027:
                            if (titleSrn.equals("_303_setting_content_11")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 102, (byte) i3});
                                break;
                            }
                            break;
                        case -1062929026:
                            if (titleSrn.equals("_303_setting_content_12")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 103, (byte) i3});
                                break;
                            }
                            break;
                        case -1062929025:
                            if (titleSrn.equals("_303_setting_content_13")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 104, (byte) i3});
                                break;
                            }
                            break;
                        case -897027326:
                            if (titleSrn.equals("hillDescentAssist")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -27, (byte) i3});
                                break;
                            }
                            break;
                        case -834987329:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_avg_speed")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -122, (byte) i3});
                                break;
                            }
                            break;
                        case -725720196:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_open_close_auto_locking")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 114, (byte) i3});
                                break;
                            }
                            break;
                        case -710443082:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_background_lighting")) {
                                if (i3 == 0) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 76, 0});
                                    break;
                                } else if (i3 == 1) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -58, 76, (byte) mMsgMgr.getMColour()});
                                    break;
                                }
                            }
                            break;
                        case -585729983:
                            if (titleSrn.equals("parkingAssist")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -25, (byte) i3});
                                break;
                            }
                            break;
                        case -578117088:
                            if (titleSrn.equals("_283_car_setting_light_4")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -56, (byte) i3});
                                break;
                            }
                            break;
                        case -576263338:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 99, (byte) i3});
                                break;
                            }
                            break;
                        case -424337997:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_driver_assistance_last_distance_selected")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i3});
                                break;
                            }
                            break;
                        case -184768695:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_speed_warning")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -120, (byte) i3});
                                break;
                            }
                            break;
                        case -140298758:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_parking_active_auto")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte) i3});
                                break;
                            }
                            break;
                        case -46292327:
                            if (titleSrn.equals("individual")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -96, (byte) i3});
                                break;
                            }
                            break;
                        case 115233015:
                            if (titleSrn.equals("_3_40h_31h_p3_b0")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte) i3});
                                break;
                            }
                            break;
                        case 178210912:
                            if (titleSrn.equals("_2_settings_syncchronous_adjustment")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte) i3});
                                break;
                            }
                            break;
                        case 179817561:
                            if (titleSrn.equals("_3_40h_20h_p1_b0")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                                break;
                            }
                            break;
                        case 278137725:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_light_auto_control")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 81, (byte) i3});
                                break;
                            }
                            break;
                        case 362469612:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_oil_temperature")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -119, (byte) i3});
                                break;
                            }
                            break;
                        case 455816650:
                            if (titleSrn.equals("two_color_sync")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 79, (byte) (1 - i3)});
                                break;
                            }
                            break;
                        case 620816228:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_light_lane_change_flash")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 82, (byte) i3});
                                break;
                            }
                            break;
                        case 678740831:
                            if (titleSrn.equals("_3_21h_d8_b7")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) i3});
                                break;
                            }
                            break;
                        case 887148936:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_digital_speed_display")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -121, (byte) i3});
                                break;
                            }
                            break;
                        case 982307432:
                            if (titleSrn.equals("_2_setting_2_0")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte) i3});
                                break;
                            }
                            break;
                        case 982314164:
                            if (titleSrn.equals("_2_setting_9_5")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 115, (byte) i3});
                                break;
                            }
                            break;
                        case 982314165:
                            if (titleSrn.equals("_2_setting_9_6")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 117, (byte) i3});
                                break;
                            }
                            break;
                        case 1192434642:
                            if (titleSrn.equals("air_conditioning_is_powered_by_battery")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -13, (byte) i3});
                                break;
                            }
                            break;
                        case 1253361596:
                            if (titleSrn.equals("driver_seat")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -55, (byte) i3});
                                break;
                            }
                            break;
                        case 1329380962:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_light_dynamic_light_assist")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 90, (byte) i3});
                                break;
                            }
                            break;
                        case 1468626858:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_light_dynamic_light_follow")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 89, (byte) i3});
                                break;
                            }
                            break;
                        case 1539964092:
                            if (titleSrn.equals("_3_40h_60h_p2_b0")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 100, (byte) i3});
                                break;
                            }
                            break;
                        case 1582870156:
                            if (titleSrn.equals("hillHoidAssist")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -26, (byte) i3});
                                break;
                            }
                            break;
                        case 1849560298:
                            if (titleSrn.equals("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -116, (byte) i3});
                                break;
                            }
                            break;
                        case 1987301822:
                            if (titleSrn.equals("_3_40h_10h_p2_b7")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117358:
                            if (titleSrn.equals("_283_car_setting_pa_1")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117359:
                            if (titleSrn.equals("_283_car_setting_pa_2")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117360:
                            if (titleSrn.equals("_283_car_setting_pa_3")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117363:
                            if (titleSrn.equals("_283_car_setting_pa_6")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117364:
                            if (titleSrn.equals("_283_car_setting_pa_7")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 61, (byte) i3});
                                break;
                            }
                            break;
                        case 2105117365:
                            if (titleSrn.equals("_283_car_setting_pa_8")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 62, (byte) i3});
                                break;
                            }
                            break;
                        case 2136472732:
                            if (titleSrn.equals("seat_remote_key_memory_matching")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -53, (byte) i3});
                                break;
                            }
                            break;
                    }
                }
            }
        });
        getSettingUiSet(context).setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public final void onClickItem(int i2, int i3, int i4) {
                UiMgr.m395lambda12$lambda9(getSettingUiSet(context), i2, i3, i4);
            }
        });
        getSettingUiSet(context).setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public final void onConfirmClick(int i2, int i3) {
                UiMgr.m391lambda12$lambda10(getSettingUiSet(context), i2, i3);
            }
        });
        getSettingUiSet(context).setOnSettingItemSeekbarSetTextListener(new OnSettingItemSeekbarSetTextListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener
            public final String onSetText(int i2, int i3, int i4) {
                return UiMgr.m392lambda12$lambda11(getSettingUiSet(context), context, i2, i3, i4);
            }
        });
        final ParkPageUiSet parkPageUiSet = getParkPageUiSet(context);
        parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public final void onClickItem(int i2) {
                String titleSrn = parkPageUiSet.getPanoramicBtnList().get(i2).getTitleSrn();
                if (titleSrn != null) {
                    int iHashCode = titleSrn.hashCode();
                    switch (iHashCode) {
                        case -1932462487:
                            if (titleSrn.equals("_253_front_view")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 0});
                                break;
                            }
                            break;
                        case -1705162449:
                            if (titleSrn.equals("_250_exit")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 105, 0});
                                break;
                            }
                            break;
                        case -1483394203:
                            if (titleSrn.equals("_94_parallel_parking")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 1});
                                break;
                            }
                            break;
                        case -1219351889:
                            if (titleSrn.equals("_253_left_view")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 1});
                                break;
                            }
                            break;
                        case -404582956:
                            if (titleSrn.equals("_94_vertical_parking")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 0});
                                break;
                            }
                            break;
                        case 621013810:
                            if (titleSrn.equals("_253_rear_view")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 2});
                                break;
                            }
                            break;
                        case 1298895446:
                            if (titleSrn.equals("_253_right_view")) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, 69, 3});
                                break;
                            }
                            break;
                        case 1981264262:
                            if (titleSrn.equals("color_set")) {
                                getCusPanoramicView(context).showHideWindow();
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -1907444892:
                                    if (titleSrn.equals("_3_c6h_46h_2")) {
                                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 2});
                                        break;
                                    }
                                    break;
                                case -1907444891:
                                    if (titleSrn.equals("_3_c6h_46h_3")) {
                                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 3});
                                        break;
                                    }
                                    break;
                                case -1907444890:
                                    if (titleSrn.equals("_3_c6h_46h_4")) {
                                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 4});
                                        break;
                                    }
                                    break;
                                case -1907444889:
                                    if (titleSrn.equals("_3_c6h_46h_5")) {
                                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 5});
                                        break;
                                    }
                                    break;
                                case -1907444888:
                                    if (titleSrn.equals("_3_c6h_46h_6")) {
                                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 70, 6});
                                        break;
                                    }
                                    break;
                            }
                    }
                }
            }
        });
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$4$1


            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress) {

                if (AmplifierActivity.AmplifierBand.VOLUME == amplifierBand) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 0, (byte) progress});
                    return;
                }
                if (AmplifierActivity.AmplifierBand.TREBLE == amplifierBand) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 1, (byte) progress});
                } else if (AmplifierActivity.AmplifierBand.MIDDLE == amplifierBand) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 2, (byte) progress});
                } else {
                    if (AmplifierActivity.AmplifierBand.BASS != amplifierBand) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 3, (byte) progress});
                }
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._3.UiMgr$4$2


            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value) {
                if (AmplifierActivity.AmplifierPosition.FRONT_REAR == amplifierPosition) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 4, (byte) (value + 9)});
                } else {
                    if (amplifierPosition != AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -88, 5, (byte) (value + 9)});
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: lambda-6$lambda-1$lambda-0, reason: not valid java name */
    public static final void m397lambda6$lambda1$lambda0(UiMgr this$0, FrontArea frontArea, int i, int i2) {


    }


    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-12$lambda-7, reason: not valid java name */
    public static final void m393lambda12$lambda7(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            int i4 = 4;
            switch (titleSrn.hashCode()) {
                case -2043604067:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_temperature")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -110, (byte) i3});
                        break;
                    }
                    break;
                case -2014552680:
                    if (titleSrn.equals("_2_settings_individual_engine")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -46, (byte) i3});
                        break;
                    }
                    break;
                case -1912662420:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_distance")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -112, (byte) i3});
                        break;
                    }
                    break;
                case -1879314128:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_environment_color")) {
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = 76;
                        if (i3 == 0) {
                            i4 = 1;
                        } else if (i3 != 1) {
                            i4 = i3 != 2 ? 0 : 5;
                        }
                        bArr[3] = (byte) i4;
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    }
                    break;
                case -1836094980:
                    if (titleSrn.equals("_2_settings_offroad_air_conditioning")) {
                        byte[] bArr2 = new byte[4];
                        bArr2[0] = 22;
                        bArr2[1] = -58;
                        bArr2[2] = -28;
                        bArr2[3] = (byte) (i3 == 1 ? 2 : 0);
                        CanbusMsgSender.sendMsg(bArr2);
                        break;
                    }
                    break;
                case -1500272463:
                    if (titleSrn.equals("_2_settings_offroad_steering")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -31, (byte) i3});
                        break;
                    }
                    break;
                case -969556744:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_travel_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 86, (byte) i3});
                        break;
                    }
                    break;
                case -722743688:
                    if (titleSrn.equals("_3_40h_31h_p3_b32")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte) i3});
                        break;
                    }
                    break;
                case -717170286:
                    if (titleSrn.equals("_2_settings_individual_air_conditioning")) {
                        byte[] bArr3 = new byte[4];
                        bArr3[0] = 22;
                        bArr3[1] = -58;
                        bArr3[2] = -45;
                        bArr3[3] = (byte) (i3 == 1 ? 2 : 0);
                        CanbusMsgSender.sendMsg(bArr3);
                        break;
                    }
                    break;
                case -433870697:
                    if (titleSrn.equals("_3_21h_d8_b65")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 42, (byte) i3});
                        break;
                    }
                    break;
                case -374590123:
                    if (titleSrn.equals("on_off_btn_txt_7")) {
                        byte[] bArr4 = new byte[4];
                        bArr4[0] = 22;
                        bArr4[1] = -58;
                        bArr4[2] = 101;
                        bArr4[3] = (byte) (i3 != 0 ? i3 + 2 : 1);
                        CanbusMsgSender.sendMsg(bArr4);
                        break;
                    }
                    break;
                case -264532014:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_switch_on_time")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte) i3});
                        break;
                    }
                    break;
                case -240318347:
                    if (titleSrn.equals("_2_setting_0")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte) i3});
                        break;
                    }
                    break;
                case -189627282:
                    if (titleSrn.equals("dashboard_mode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -23, (byte) (i3 + 2)});
                        break;
                    }
                    break;
                case -158538558:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_open_close_door_unlocking")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 113, (byte) i3});
                        break;
                    }
                    break;
                case 10536655:
                    if (titleSrn.equals("key_assign")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) i3});
                        break;
                    }
                    break;
                case 64386538:
                    if (titleSrn.equals("maximum_charging_current")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -15, (byte) i3});
                        break;
                    }
                    break;
                case 194708627:
                    if (titleSrn.equals("_29_right_side")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -21, (byte) i3});
                        break;
                    }
                    break;
                case 296809711:
                    if (titleSrn.equals("_3_40h_70h_p1_b30")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 112, (byte) i3});
                        break;
                    }
                    break;
                case 348332473:
                    if (titleSrn.equals("user_account")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) (i3 + 1)});
                        break;
                    }
                    break;
                case 554554329:
                    if (titleSrn.equals("vm_power_consumption")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -106, (byte) i3});
                        break;
                    }
                    break;
                case 606404938:
                    if (titleSrn.equals("_2_settings_offroad_drive")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -30, (byte) i3});
                        break;
                    }
                    break;
                case 633766017:
                    if (titleSrn.equals("_2_settings_acc_drive_program")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) i3});
                        break;
                    }
                    break;
                case 643894855:
                    if (titleSrn.equals("_2_settings_individual_steering")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -47, (byte) i3});
                        break;
                    }
                    break;
                case 669958842:
                    if (titleSrn.equals("_283_title_2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -48, (byte) i3});
                        break;
                    }
                    break;
                case 669958843:
                    if (titleSrn.equals("_283_title_3")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    }
                    break;
                case 817602748:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_pressure")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -107, (byte) i3});
                        break;
                    }
                    break;
                case 961699958:
                    if (titleSrn.equals("_29_left_side")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -22, (byte) i3});
                        break;
                    }
                    break;
                case 1081491908:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_consumption")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -108, (byte) i3});
                        break;
                    }
                    break;
                case 1302419692:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_hybrid_emode")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -16, (byte) i3});
                        break;
                    }
                    break;
                case 1349952337:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_volume")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -109, (byte) i3});
                        break;
                    }
                    break;
                case 1371742195:
                    if (titleSrn.equals("_3_40h_52h_p3_b50")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 94, (byte) i3});
                        break;
                    }
                    break;
                case 1371742263:
                    if (titleSrn.equals("_3_40h_52h_p3_b76")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 74, (byte) i3});
                        break;
                    }
                    break;
                case 1806178504:
                    if (titleSrn.equals("vm_golf7_language_setup")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) MsgMgr.INSTANCE.getLanguageList().get(i3).intValue()});
                        break;
                    }
                    break;
                case 1835219203:
                    if (titleSrn.equals("_2_settings_acc_distance")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i3});
                        break;
                    }
                    break;
                case 1841914160:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_units_speed")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -111, (byte) i3});
                        break;
                    }
                    break;
                case 2037033549:
                    if (titleSrn.equals("_2_settings_offroad_four_wheel_drive")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -29, (byte) i3});
                        break;
                    }
                    break;
                case 2129434926:
                    if (titleSrn.equals("_2_settings_individual_cornering_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -43, (byte) i3});
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* renamed from: lambda-12$lambda-8, reason: not valid java name */
    public static final void m394lambda12$lambda8(SettingPageUiSet settingPageUiSet, UiMgr this$0, int i, int i2, int i3) {


    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-12$lambda-9, reason: not valid java name */
    public static final void m395lambda12$lambda9(SettingPageUiSet settingPageUiSet, int i, int i2, int i3) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2038625043:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_coming_home")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 84, (byte) (i3 * 5)});
                        break;
                    }
                    break;
                case -1595882384:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_parking_rear_tone_setting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte) i3});
                        break;
                    }
                    break;
                case -1435138704:
                    if (titleSrn.equals("headlight_illumination_distance_adjustment")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 95, (byte) i3});
                        break;
                    }
                    break;
                case -1158124569:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_parking_front_tone_setting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte) i3});
                        break;
                    }
                    break;
                case -1087223577:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_ins_swi_lighting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 83, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case -965356345:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_parking_rear_volume")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte) i3});
                        break;
                    }
                    break;
                case -705322732:
                    if (titleSrn.equals("first_color")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 78, (byte) i3});
                        break;
                    }
                    break;
                case -702173029:
                    if (titleSrn.equals("vehicle_tem_in")) {
                        byte[] bArr = new byte[4];
                        bArr[0] = 22;
                        bArr[1] = -58;
                        bArr[2] = -14;
                        bArr[3] = (byte) (i3 != 0 ? i3 == settingPageUiSet.getList().get(i).getItemList().get(i2).getMax() ? 255 : ((i3 + 31) * 5) - 100 : 0);
                        CanbusMsgSender.sendMsg(bArr);
                        break;
                    }
                    break;
                case -458021931:
                    if (titleSrn.equals("_3_40h_20h_p2")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) (i3 * MsgMgr.SpeedAlertHelper.INSTANCE.getSpeedUnit().getStep())});
                        break;
                    }
                    break;
                case -376524840:
                    if (titleSrn.equals("second_color")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 78, (byte) ((i3 & 0x7F) | 128)});
                        break;
                    }
                    break;
                case 53943538:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_all_area_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 93, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 155644670:
                    if (titleSrn.equals("lane_assist_system_brightness")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 63, (byte) (i3 - 1)});
                        break;
                    }
                    break;
                case 205955940:
                    if (titleSrn.equals("battery_charge_lower_limit")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -12, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 332079758:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_door_ambient_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 87, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 392307070:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_parking_front_volume")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte) i3});
                        break;
                    }
                    break;
                case 433034755:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_footwell_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 88, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 878703845:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_car_front_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 92, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 1222207462:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_car_roof_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 91, (byte) (i3 * 10)});
                        break;
                    }
                    break;
                case 1233601850:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_leaving_home")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 85, (byte) (i3 * 5)});
                        break;
                    }
                    break;
                case 1285394220:
                    if (titleSrn.equals("_143_0xAD_setting6")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -88, 6, (byte) i3});
                        break;
                    }
                    break;
                case 1415745787:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_handle_box_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 77, (byte) (i3 * 10)});
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
    /* renamed from: lambda-12$lambda-10, reason: not valid java name */
    public static final void m391lambda12$lambda10(SettingPageUiSet settingPageUiSet, int i, int i2) {
        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2039180641:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_opening_and_closing")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -59, 1});
                        break;
                    }
                    break;
                case -1905665511:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_multifuction_display")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -58, 1});
                        break;
                    }
                    break;
                case -1220578990:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_reset_since_start")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -118, 1});
                        break;
                    }
                    break;
                case -348745653:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_parking_and_manoeuvring")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -62, 1});
                        break;
                    }
                    break;
                case -247586406:
                    if (titleSrn.equals("electric_drive_charging_settings")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -54, 1});
                        break;
                    }
                    break;
                case -146540603:
                    if (titleSrn.equals("tpms_set")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, 1});
                        break;
                    }
                    break;
                case -85456911:
                    if (titleSrn.equals("vm_golf7_Conv_consumers_prompt_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -86, 1});
                        break;
                    }
                    break;
                case 45028093:
                    if (titleSrn.equals("background_lighting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -49, 1});
                        break;
                    }
                    break;
                case 171533376:
                    if (titleSrn.equals("off_road_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -24, 1});
                        break;
                    }
                    break;
                case 574486206:
                    if (titleSrn.equals("drive_assist")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -63, 1});
                        break;
                    }
                    break;
                case 587462358:
                    if (titleSrn.equals("_307_value_26")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -92, 1});
                        break;
                    }
                    break;
                case 1025851151:
                    if (titleSrn.equals("assign_key")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -93, 1});
                        break;
                    }
                    break;
                case 1140065918:
                    if (titleSrn.equals("_2_setting_14")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -50, 1});
                        break;
                    }
                    break;
                case 1334218665:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_mirror_and_wipers")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -60, 1});
                        break;
                    }
                    break;
                case 1395133481:
                    if (titleSrn.equals("individual_reset")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -44, 1});
                        break;
                    }
                    break;
                case 1543436994:
                    if (titleSrn.equals("reset_all_setting")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -57, 1});
                        break;
                    }
                    break;
                case 1657608132:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_mfd_reset_long_term")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -117, 1});
                        break;
                    }
                    break;
                case 1837145441:
                    if (titleSrn.equals("reset_maintenance_interval")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -115, 1});
                        break;
                    }
                    break;
                case 2001824143:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, -61, 1});
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
    /* renamed from: lambda-12$lambda-11, reason: not valid java name */
    public static final String m392lambda12$lambda11(SettingPageUiSet settingPageUiSet, Context context, int i, int i2, int i3) {

        String titleSrn = settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -2038625043:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_coming_home")) {
                        return String.valueOf(i3 * 5);
                    }
                    break;
                case -1087223577:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_ins_swi_lighting")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case -705322732:
                    if (titleSrn.equals("first_color")) {
                        return CommUtil.getStrByResId(context, "_270_setting_12") + ' ' + i3;
                    }
                    break;
                case -702173029:
                    if (titleSrn.equals("vehicle_tem_in")) {
                        return i3 == 0 ? "LO" : i3 == settingPageUiSet.getList().get(i).getItemList().get(i2).getMax() ? "HI" : new StringBuilder().append((i3 + 31) / 2.0f).append((char) 8451).toString();
                    }
                    break;
                case -458021931:
                    if (titleSrn.equals("_3_40h_20h_p2")) {
                        return String.valueOf(i3 * MsgMgr.SpeedAlertHelper.INSTANCE.getSpeedUnit().getStep());
                    }
                    break;
                case -376524840:
                    if (titleSrn.equals("second_color")) {
                        return CommUtil.getStrByResId(context, "_270_setting_12") + ' ' + i3;
                    }
                    break;
                case 53943538:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_all_area_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 205955940:
                    if (titleSrn.equals("battery_charge_lower_limit")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 332079758:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_door_ambient_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 433034755:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_footwell_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 878703845:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_car_front_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 1222207462:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_car_roof_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
                case 1233601850:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_leaving_home")) {
                        return String.valueOf(i3 * 5);
                    }
                    break;
                case 1415745787:
                    if (titleSrn.equals("vm_golf7_vehicle_setup_light_handle_box_light")) {
                        return String.valueOf(i3 * 10);
                    }
                    break;
            }
        }
        return String.valueOf(i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: lambda-14$lambda-13, reason: not valid java name */
    public static final void m396lambda14$lambda13(ParkPageUiSet parkPageUiSet, UiMgr this$0, Context context, int i) {


    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public MyPanoramicView getCusPanoramicView(Context context) {

        return this.mPanoramicView;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    private final void sendAirCommand(String title) {
        switch (title.hashCode()) {
            case -1786872896:
                if (title.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                    sendAirCommand(MpegConstantsDef.GotoTimeCFM, m400switch(GeneralAirData.steering_wheel_heating));
                    break;
                }
                break;
            case -1470045433:
                if (title.equals("front_defog")) {
                    sendAirCommand(205, m400switch(GeneralAirData.front_defog));
                    break;
                }
                break;
            case -1428679594:
                if (title.equals(AirBtnAction.AUTO_MANUAL)) {
                    sendAirCommand(37, m400switch(GeneralAirData.auto_manual));
                    break;
                }
                break;
            case -1423573049:
                if (title.equals(AirBtnAction.AC_MAX)) {
                    sendAirCommand(HotKeyConstant.K_SPEECH, 2);
                    break;
                }
                break;
            case -1274277292:
                if (title.equals(AirBtnAction.CLEAN_AIR)) {
                    sendAirCommand(175, m400switch(GeneralAirData.clean_air));
                    break;
                }
                break;
            case -1081415738:
                if (title.equals(AirBtnAction.MANUAL)) {
                    sendAirCommand(HotKeyConstant.K_SPEECH, 0);
                    break;
                }
                break;
            case -825767279:
                if (title.equals(AirBtnAction.AUTO_WIND_LV)) {
                    sendAirCommand(MpegConstantsDef.MPEG_VIDEO_DISP_INFO_IND, GeneralAirData.auto_wind_lv);
                    break;
                }
                break;
            case -620266838:
                if (title.equals(AirBtnAction.REAR_POWER)) {
                    sendAirCommand(36, m400switch(GeneralAirData.rear_power));
                    break;
                }
                break;
            case -480367616:
                if (title.equals(AirBtnAction.REAR_BLOW_FOOT)) {
                    sendAirCommand(39, m400switch(GeneralAirData.rear_left_blow_foot));
                    break;
                }
                break;
            case -480318094:
                if (title.equals(AirBtnAction.REAR_BLOW_HEAD)) {
                    sendAirCommand(38, m400switch(GeneralAirData.rear_left_blow_head));
                    break;
                }
                break;
            case -246396018:
                if (title.equals(AirBtnAction.MAX_FRONT)) {
                    sendAirCommand(HotKeyConstant.K_SPEECH, 3);
                    break;
                }
                break;
            case 3106:
                if (title.equals("ac")) {
                    sendAirCommand(HotKeyConstant.K_PHONE_OFF_RETURN, m400switch(GeneralAirData.ac));
                    break;
                }
                break;
            case 96835:
                if (title.equals(AirBtnAction.AQS)) {
                    sendAirCommand(SyslogAppender.LOG_LOCAL6, m400switch(GeneralAirData.aqs));
                    break;
                }
                break;
            case 3094652:
                if (title.equals("dual")) {
                    sendAirCommand(MpegConstantsDef.MPEG_TEST_NUM_IND, m400switch(GeneralAirData.dual));
                    break;
                }
                break;
            case 3496356:
                if (title.equals(AirBtnAction.REAR)) {
                    sendAirCommand(HotKeyConstant.K_PHONE_ON_OFF, m400switch(GeneralAirData.rear));
                    break;
                }
                break;
            case 106858757:
                if (title.equals("power")) {
                    sendAirCommand(178, m400switch(GeneralAirData.power));
                    break;
                }
                break;
            case 341572893:
                if (title.equals(AirBtnAction.BLOW_WINDOW)) {
                    sendAirCommand(HotKeyConstant.K_DISP, m400switch(GeneralAirData.front_left_blow_window));
                    break;
                }
                break;
            case 756225563:
                if (title.equals("in_out_cycle")) {
                    sendAirCommand(NfDef.STATE_3WAY_OUTGOING_CALL_S_HOLD, m400switch(!GeneralAirData.in_out_cycle));
                    break;
                }
                break;
            case 1434490075:
                if (title.equals(AirBtnAction.BLOW_FOOT)) {
                    sendAirCommand(181, m400switch(GeneralAirData.front_left_blow_foot));
                    break;
                }
                break;
            case 1434539597:
                if (title.equals(AirBtnAction.BLOW_HEAD)) {
                    sendAirCommand(NfDef.STATE_CALL_IS_ACTIVE, m400switch(GeneralAirData.front_left_blow_head));
                    break;
                }
                break;
            case 1438998804:
                if (title.equals(AirBtnAction.AUTO_1_2)) {
                    sendAirCommand(HotKeyConstant.K_SPEECH, 1);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendAirCommand(int command, int param) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) command, (byte) param});
    }
}
