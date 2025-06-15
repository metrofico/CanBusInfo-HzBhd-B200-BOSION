package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$6 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int doorStatus;
    private ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$6(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x24】基本信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        return new OnParseListener[]{null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m1050setOnParseListeners$lambda8(this.f$0, msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m1046setOnParseListeners$lambda16(this.f$0, msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m1047setOnParseListeners$lambda23(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m1048setOnParseListeners$lambda30(this.f$0, msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m1049setOnParseListeners$lambda35(this.f$0, msgMgr5);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m1050setOnParseListeners$lambda8(MsgMgr$initParsers$1$6 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_81_traction_control_system");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(this$1.mCanbusInfoInt[4] & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_81_turn_signals_setup");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 1) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("ford_message_tone");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 2) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("ford_alert_tone");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("parking_assistance");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_reversing_video_hold");
        if (settingUpdateEntity6 != null) {
            arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("ford_range_unit");
        if (settingUpdateEntity7 != null) {
            arrayList.add(settingUpdateEntity7.setValue(Integer.valueOf((this$1.mCanbusInfoInt[4] >> 7) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-16, reason: not valid java name */
    public static final void m1046setOnParseListeners$lambda16(MsgMgr$initParsers$1$6 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_18_vehicle_setting_item_3_210");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((this$1.mCanbusInfoInt[5] >> 6) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_intelligent_unlock_lock");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$1.mCanbusInfoInt[5] >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_welcome_lamp_duration");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((this$1.mCanbusInfoInt[5] >> 3) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("remote_window_control");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((this$1.mCanbusInfoInt[5] >> 2) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_284_setting_item_49");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf((this$1.mCanbusInfoInt[5] >> 1) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("speed_lock");
        if (settingUpdateEntity6 != null) {
            arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf(this$1.mCanbusInfoInt[5] & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-23, reason: not valid java name */
    public static final void m1047setOnParseListeners$lambda23(MsgMgr$initParsers$1$6 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_indoor_lamp_duration");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((this$1.mCanbusInfoInt[6] >> 6) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_mirror_fold_mode");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$1.mCanbusInfoInt[6] >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_rear_defog_duration");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((this$1.mCanbusInfoInt[6] >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_go_home_with_me_duration");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((this$1.mCanbusInfoInt[6] >> 2) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_instrument_direction_key");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(this$1.mCanbusInfoInt[6] & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-30, reason: not valid java name */
    public static final void m1048setOnParseListeners$lambda30(MsgMgr$initParsers$1$6 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_charging_indicator");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((this$1.mCanbusInfoInt[7] >> 5) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_81_hill_start_assist");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$1.mCanbusInfoInt[7] >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_218_setting_0_4");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((this$1.mCanbusInfoInt[7] >> 2) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_118_setting_title_49");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((this$1.mCanbusInfoInt[7] >> 1) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_81_rain_sensor");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(this$1.mCanbusInfoInt[7] & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-35, reason: not valid java name */
    public static final void m1049setOnParseListeners$lambda35(MsgMgr$initParsers$1$6 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_active_city");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((this$1.mCanbusInfoInt[8] >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_ambient_light");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((this$1.mCanbusInfoInt[8] >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_powerfold_mirrors");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((this$1.mCanbusInfoInt[8] >> 5) & 1)));
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        int i = this.this$0.mCanbusInfoInt[2];
        MsgMgr msgMgr = this.this$0;
        Context context = this.$context;
        if (this.doorStatus != i) {
            this.doorStatus = i;
            GeneralDoorData.isRightFrontDoorOpen = ((i >> 7) & 1) == 1;
            GeneralDoorData.isLeftFrontDoorOpen = ((i >> 6) & 1) == 1;
            GeneralDoorData.isRightRearDoorOpen = ((i >> 5) & 1) == 1;
            GeneralDoorData.isLeftRearDoorOpen = ((i >> 4) & 1) == 1;
            GeneralDoorData.isBackOpen = ((i >> 3) & 1) == 1;
            msgMgr.updateDoorView(context);
        }
        int i2 = (this.this$0.mCanbusInfoInt[4] & 32) | (this.this$0.mCanbusInfoInt[7] & 8);
        MsgMgr msgMgr2 = this.this$0;
        if (msgMgr2.getMReversingValues() != i2) {
            msgMgr2.mReversingValues = i2;
            int mReversingValues = (msgMgr2.getMReversingValues() >> 5) & 1;
            msgMgr2.getListGeneralParkData().add(new PanoramicBtnUpdateEntity(0, mReversingValues == 0));
            msgMgr2.getListGeneralParkData().add(new PanoramicBtnUpdateEntity(1, mReversingValues == 1));
            msgMgr2.getListGeneralParkData().add(new PanoramicBtnUpdateEntity(2, ((msgMgr2.getMReversingValues() >> 3) & 1) == 1));
        }
        msgMgr2.refreshParkUi();
        this.this$0.mCanbusInfoInt[2] = 0;
        this.this$0.mCanbusInfoInt[3] = 0;
        this.this$0.mCanbusInfoInt[4] = this.this$0.mCanbusInfoInt[4] & HotKeyConstant.K_DARK_MODE;
        if (isDataChange()) {
            this.list = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr3 = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr3.updateGeneralSettingData(arrayList);
            this.this$0.updateSettingActivity(null);
        }
    }
}
