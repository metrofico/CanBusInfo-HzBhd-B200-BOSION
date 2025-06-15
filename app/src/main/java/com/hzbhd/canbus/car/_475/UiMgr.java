package com.hzbhd.canbus.car._475;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import nfore.android.bt.res.NfDef;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    private MsgMgr mMsgMgr;
    OnAirBtnClickListener topBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.5
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0021  */
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r5) {
            /*
                Method dump skipped, instructions count: 414
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._475.UiMgr.AnonymousClass5.onClickItem(int):void");
        }
    };
    OnAirBtnClickListener leftBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.6
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            String str = uiMgr.getAirUiSet(uiMgr.mContext).getFrontArea().getLineBtnAction()[1][i];
            str.hashCode();
            if (str.equals(AirBtnAction.AC_MAX)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 9});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
            }
        }
    };
    OnAirBtnClickListener rightBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.7
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        public void onClickItem(int i) {
            UiMgr uiMgr = UiMgr.this;
            String str = uiMgr.getAirUiSet(uiMgr.mContext).getFrontArea().getLineBtnAction()[2][i];
            str.hashCode();
            if (str.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 24});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
            } else if (str.equals(AirBtnAction.MAX_FRONT)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 44});
                CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
            }
        }
    };
    OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.8
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0021  */
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r5) {
            /*
                Method dump skipped, instructions count: 504
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._475.UiMgr.AnonymousClass8.onClickItem(int):void");
        }
    };
    OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.9
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.10
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 3});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 4});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.11
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 6});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 5});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    OnAirBtnClickListener rearTopBtn = new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.12
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0021  */
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onClickItem(int r5) {
            /*
                Method dump skipped, instructions count: 414
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._475.UiMgr.AnonymousClass12.onClickItem(int):void");
        }
    };
    OnAirTemperatureUpDownClickListener rearLeftTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.13
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 25});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 26});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    OnAirTemperatureUpDownClickListener rearRightTemp = new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.14
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 27});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
        public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 28});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    OnAirWindSpeedUpDownClickListener rearWindControl = new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.15
        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 30});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }

        @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
        public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 29});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
        }
    };
    int dataFrontRear = 0;
    int dataLeftRight = 0;
    int volume = 0;
    int treble = 0;
    int middle = 0;
    int bass = 0;
    int data7 = 0;
    private OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.16
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
        public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
            int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
            if (i2 == 1) {
                Log.d("AMPL", "前后=" + i);
                UiMgr.this.dataFrontRear = i;
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAmplifierData(uiMgr.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                return;
            }
            if (i2 != 2) {
                return;
            }
            Log.d("AMPL", "左右=" + i);
            UiMgr.this.dataLeftRight = i;
            UiMgr uiMgr2 = UiMgr.this;
            uiMgr2.sendAmplifierData(uiMgr2.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
        }
    };
    private OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.17
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
        public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
            int i2 = AnonymousClass19.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
            if (i2 == 1) {
                Log.d("AMPL", "音量=" + i);
                UiMgr.this.volume = i;
                UiMgr uiMgr = UiMgr.this;
                uiMgr.sendAmplifierData(uiMgr.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                return;
            }
            if (i2 == 2) {
                Log.d("AMPL", "高音=" + i);
                UiMgr.this.treble = i;
                UiMgr uiMgr2 = UiMgr.this;
                uiMgr2.sendAmplifierData(uiMgr2.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                return;
            }
            if (i2 == 3) {
                Log.d("AMPL", "中音=" + i);
                UiMgr.this.middle = i;
                UiMgr uiMgr3 = UiMgr.this;
                uiMgr3.sendAmplifierData(uiMgr3.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                return;
            }
            if (i2 != 4) {
                return;
            }
            Log.d("AMPL", "低音=" + i);
            UiMgr.this.bass = i;
            UiMgr uiMgr4 = UiMgr.this;
            uiMgr4.sendAmplifierData(uiMgr4.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
        }
    };
    private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.18
        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void create() {
        }

        @Override // com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener
        public void destroy() {
        }
    };
    private int mDifferent = getCurrentCarId();

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int i = this.mDifferent;
        if (i == 0) {
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_WINDOW);
            removeFrontAirButtonByName(context, AirBtnAction.AUTO_2);
        } else {
            if (i != 1) {
                return;
            }
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_HEAD_FOOT);
            removeFrontAirButtonByName(context, AirBtnAction.BLOW_WINDOW_FOOT);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
        amplifierPageUiSet.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
        AirPageUiSet airUiSet = getAirUiSet(context);
        airUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
        airUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
        airUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
        airUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.rearTopBtn});
        airUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.rearLeftTemp, null, this.rearRightTemp});
        airUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.rearWindControl);
        getDriverDataPageUiSet(context).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 53});
            }
        });
        final SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
            public void onStatusChange(int i) {
                CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
            }
        });
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.3
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                char c = 65535;
                switch (titleSrn.hashCode()) {
                    case -2093370173:
                        if (titleSrn.equals("_475_start_stop_system")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -2065696613:
                        if (titleSrn.equals("_118_setting_title_10")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -2065696605:
                        if (titleSrn.equals("_118_setting_title_18")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -2065696604:
                        if (titleSrn.equals("_118_setting_title_19")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -2065696578:
                        if (titleSrn.equals("_118_setting_title_24")) {
                            c = 4;
                            break;
                        }
                        break;
                    case -2065696577:
                        if (titleSrn.equals("_118_setting_title_25")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -2065696576:
                        if (titleSrn.equals("_118_setting_title_26")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -2065696575:
                        if (titleSrn.equals("_118_setting_title_27")) {
                            c = 7;
                            break;
                        }
                        break;
                    case -2065696574:
                        if (titleSrn.equals("_118_setting_title_28")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -2065696573:
                        if (titleSrn.equals("_118_setting_title_29")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -2065696396:
                        if (titleSrn.equals("_118_setting_title_80")) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -2065696395:
                        if (titleSrn.equals("_118_setting_title_81")) {
                            c = 11;
                            break;
                        }
                        break;
                    case -2055183645:
                        if (titleSrn.equals("_2_setting_12_3_2")) {
                            c = '\f';
                            break;
                        }
                        break;
                    case -2023122657:
                        if (titleSrn.equals("_370_Engine_Off_Light_delay")) {
                            c = '\r';
                            break;
                        }
                        break;
                    case -1856514051:
                        if (titleSrn.equals("_475_sound_the_horn_lock")) {
                            c = 14;
                            break;
                        }
                        break;
                    case -1836538809:
                        if (titleSrn.equals("auto_locking")) {
                            c = 15;
                            break;
                        }
                        break;
                    case -1818972390:
                        if (titleSrn.equals("_475_auto_start_headlights_when_unlocking")) {
                            c = 16;
                            break;
                        }
                        break;
                    case -1711605204:
                        if (titleSrn.equals("_123_auxiliary_1")) {
                            c = 17;
                            break;
                        }
                        break;
                    case -1590596702:
                        if (titleSrn.equals("_250_automatic_folding_mirror")) {
                            c = 18;
                            break;
                        }
                        break;
                    case -1535730024:
                        if (titleSrn.equals("_475_sound_the_horn_remote")) {
                            c = 19;
                            break;
                        }
                        break;
                    case -1357084752:
                        if (titleSrn.equals("_475_rearview_mirror_reverse_assist_adjustment")) {
                            c = 20;
                            break;
                        }
                        break;
                    case -1297749980:
                        if (titleSrn.equals("_475_p_key")) {
                            c = 21;
                            break;
                        }
                        break;
                    case -1154303868:
                        if (titleSrn.equals("_475_front_sensor_driving")) {
                            c = 22;
                            break;
                        }
                        break;
                    case -1074566328:
                        if (titleSrn.equals("_475_high_beam_assist")) {
                            c = 23;
                            break;
                        }
                        break;
                    case -1006589135:
                        if (titleSrn.equals("_94_key_free")) {
                            c = 24;
                            break;
                        }
                        break;
                    case -811343875:
                        if (titleSrn.equals("_475_capacity_unit")) {
                            c = 25;
                            break;
                        }
                        break;
                    case -599641101:
                        if (titleSrn.equals("ramp_start_assist")) {
                            c = 26;
                            break;
                        }
                        break;
                    case -585729983:
                        if (titleSrn.equals("parkingAssist")) {
                            c = 27;
                            break;
                        }
                        break;
                    case -566809151:
                        if (titleSrn.equals("_475_auto_unlocking_get_off")) {
                            c = 28;
                            break;
                        }
                        break;
                    case -560842866:
                        if (titleSrn.equals("distance_unit")) {
                            c = 29;
                            break;
                        }
                        break;
                    case -454334994:
                        if (titleSrn.equals("_123_rear_mirror")) {
                            c = 30;
                            break;
                        }
                        break;
                    case -392737071:
                        if (titleSrn.equals("hiworld_jeep_123_0xCA_0X08")) {
                            c = 31;
                            break;
                        }
                        break;
                    case -250079212:
                        if (titleSrn.equals("_1193_setting_1_7")) {
                            c = ' ';
                            break;
                        }
                        break;
                    case -229100667:
                        if (titleSrn.equals("_475_bending_mode")) {
                            c = '!';
                            break;
                        }
                        break;
                    case -26602129:
                        if (titleSrn.equals("temperature_unit")) {
                            c = Typography.quote;
                            break;
                        }
                        break;
                    case -538431:
                        if (titleSrn.equals("_475_display_fuel_efficient_mode")) {
                            c = '#';
                            break;
                        }
                        break;
                    case 102584022:
                        if (titleSrn.equals("language_setup")) {
                            c = Typography.dollar;
                            break;
                        }
                        break;
                    case 300606309:
                        if (titleSrn.equals("_373_setting_car_control9")) {
                            c = '%';
                            break;
                        }
                        break;
                    case 387914516:
                        if (titleSrn.equals("_118_setting_title_110")) {
                            c = Typography.amp;
                            break;
                        }
                        break;
                    case 548772759:
                        if (titleSrn.equals("_475_auto_activate")) {
                            c = '\'';
                            break;
                        }
                        break;
                    case 664425103:
                        if (titleSrn.equals("_475_guidance_line")) {
                            c = '(';
                            break;
                        }
                        break;
                    case 709018697:
                        if (titleSrn.equals("_475_intelligent_electric_tailgate")) {
                            c = ')';
                            break;
                        }
                        break;
                    case 741052632:
                        if (titleSrn.equals("_475_lights_flashing_when_locked")) {
                            c = '*';
                            break;
                        }
                        break;
                    case 828452275:
                        if (titleSrn.equals("_475_lanesense_warning")) {
                            c = '+';
                            break;
                        }
                        break;
                    case 909800750:
                        if (titleSrn.equals("_475_press_key_control_unlock")) {
                            c = ',';
                            break;
                        }
                        break;
                    case 1170379802:
                        if (titleSrn.equals("hiworld_jeep_123_0x43_data3_0")) {
                            c = '-';
                            break;
                        }
                        break;
                    case 1170379803:
                        if (titleSrn.equals("hiworld_jeep_123_0x43_data3_1")) {
                            c = '.';
                            break;
                        }
                        break;
                    case 1180290614:
                        if (titleSrn.equals("_118_setting_title_2")) {
                            c = '/';
                            break;
                        }
                        break;
                    case 1180290615:
                        if (titleSrn.equals("_118_setting_title_3")) {
                            c = '0';
                            break;
                        }
                        break;
                    case 1180290617:
                        if (titleSrn.equals("_118_setting_title_5")) {
                            c = '1';
                            break;
                        }
                        break;
                    case 1180290618:
                        if (titleSrn.equals("_118_setting_title_6")) {
                            c = '2';
                            break;
                        }
                        break;
                    case 1180290621:
                        if (titleSrn.equals("_118_setting_title_9")) {
                            c = '3';
                            break;
                        }
                        break;
                    case 1205441802:
                        if (titleSrn.equals("_475_lanesense_strength")) {
                            c = '4';
                            break;
                        }
                        break;
                    case 1511876815:
                        if (titleSrn.equals("_337_amplifier_info2")) {
                            c = '5';
                            break;
                        }
                        break;
                    case 1787707114:
                        if (titleSrn.equals("_475_power_unit")) {
                            c = '6';
                            break;
                        }
                        break;
                    case 1904746967:
                        if (titleSrn.equals("hiworld_jeep_123_0x60_data1_4")) {
                            c = '7';
                            break;
                        }
                        break;
                    case 1962120554:
                        if (titleSrn.equals("_475_blind_spot_alarm")) {
                            c = '8';
                            break;
                        }
                        break;
                    case 2076384769:
                        if (titleSrn.equals("_220_daytime_running_lights")) {
                            c = '9';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte) i3});
                        break;
                    case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte) i3});
                        break;
                    case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte) i3});
                        break;
                    case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte) i3});
                        break;
                    case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 47, (byte) i3});
                        break;
                    case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte) i3});
                        break;
                    case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte) i3});
                        break;
                    case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte) i3});
                        break;
                    case '\b':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte) i3});
                        break;
                    case '\t':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte) i3});
                        break;
                    case '\n':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte) i3});
                        break;
                    case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte) i3});
                        break;
                    case '\f':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte) i3});
                        break;
                    case '\r':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte) i3});
                        break;
                    case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte) i3});
                        break;
                    case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte) i3});
                        break;
                    case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte) i3});
                        break;
                    case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte) i3});
                        break;
                    case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte) i3});
                        break;
                    case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte) i3});
                        break;
                    case 20:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) i3});
                        break;
                    case 21:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte) i3});
                        break;
                    case 22:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 45, (byte) i3});
                        break;
                    case 23:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte) i3});
                        break;
                    case 24:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte) i3});
                        break;
                    case 25:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte) i3});
                        break;
                    case 26:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte) i3});
                        break;
                    case 27:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i3});
                        break;
                    case 28:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, (byte) i3});
                        break;
                    case 29:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte) i3});
                        break;
                    case 30:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte) i3});
                        break;
                    case 31:
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte) i3});
                        break;
                    case ' ':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte) i3});
                        break;
                    case '!':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte) i3});
                        break;
                    case '\"':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte) i3});
                        break;
                    case '#':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i3});
                        break;
                    case '$':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                        break;
                    case '%':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte) i3});
                        break;
                    case '&':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte) i3});
                        break;
                    case '\'':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte) i3});
                        break;
                    case '(':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte) i3});
                        break;
                    case ')':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 46, (byte) i3});
                        break;
                    case '*':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                        break;
                    case '+':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte) i3});
                        break;
                    case ',':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte) i3});
                        break;
                    case '-':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte) i3});
                        break;
                    case '.':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte) i3});
                        break;
                    case '/':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte) i3});
                        break;
                    case '0':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte) i3});
                        break;
                    case '1':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte) i3});
                        break;
                    case '2':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte) i3});
                        break;
                    case '3':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte) i3});
                        break;
                    case '4':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte) i3});
                        break;
                    case '5':
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.data7 = (i3 << 7) | (uiMgr.data7 & (-129));
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.sendAmplifierData(uiMgr2.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                        break;
                    case '6':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte) i3});
                        break;
                    case '7':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte) i3});
                        break;
                    case '8':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte) i3});
                        break;
                    case '9':
                        CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte) i3});
                        break;
                }
            }
        });
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._475.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = settingUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("_370_Speed_adjustment_volume")) {
                    UiMgr uiMgr = UiMgr.this;
                    uiMgr.data7 = (uiMgr.data7 & (-16)) | (i3 & 15);
                    UiMgr uiMgr2 = UiMgr.this;
                    uiMgr2.sendAmplifierData(uiMgr2.volume, UiMgr.this.dataFrontRear, UiMgr.this.dataLeftRight, UiMgr.this.bass, UiMgr.this.middle, UiMgr.this.treble, UiMgr.this.data7);
                }
            }
        });
    }

    /* renamed from: com.hzbhd.canbus.car._475.UiMgr$19, reason: invalid class name */
    static /* synthetic */ class AnonymousClass19 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr;
            try {
                iArr[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 1;
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
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 4;
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

    public void sendAmplifierData(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
    }

    protected int getDrivingPageIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHeadTitleSrn().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    protected int getDrivingItemIndexes(Context context, String str) {
        List<DriverDataPageUiSet.Page> list = getPageUiSet(context).getDriverDataPageUiSet().getList();
        for (int i = 0; i < list.size(); i++) {
            Iterator<DriverDataPageUiSet.Page> it = list.iterator();
            while (it.hasNext()) {
                List<DriverDataPageUiSet.Page.Item> itemList = it.next().getItemList();
                for (int i2 = 0; i2 < itemList.size(); i2++) {
                    if (itemList.get(i2).getTitleSrn().equals(str)) {
                        return i2;
                    }
                }
            }
        }
        return -1;
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

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
