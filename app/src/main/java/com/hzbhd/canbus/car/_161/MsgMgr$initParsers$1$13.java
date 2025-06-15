package com.hzbhd.canbus.car._161;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;




public final class MsgMgr$initParsers$1$13 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int displayPage;
    private boolean isBackOpen;
    private boolean isLeftFrontDoorOpen;
    private boolean isLeftRearDoorOpen;
    private boolean isRightFrontDoorOpen;
    private boolean isRightRearDoorOpen;
    private final ArrayList<SettingUpdateEntity<Object>> list;
    private int[] settingsData;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$13(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x38】汽车状态");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final MsgMgr msgMgr8 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m144setOnParseListeners$lambda1(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m145setOnParseListeners$lambda11(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m146setOnParseListeners$lambda21(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m147setOnParseListeners$lambda28(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m148setOnParseListeners$lambda37(msgMgr5, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m149setOnParseListeners$lambda46(msgMgr6, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m150setOnParseListeners$lambda54(msgMgr7, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m151setOnParseListeners$lambda59(msgMgr8, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m144setOnParseListeners$lambda1(MsgMgr this$0) {

        int i = this$0.mCanbusInfoInt[2];
        GeneralDoorData.isLeftFrontDoorOpen = ((i >> 7) & 1) == 1;
        GeneralDoorData.isRightFrontDoorOpen = ((i >> 6) & 1) == 1;
        GeneralDoorData.isLeftRearDoorOpen = ((i >> 5) & 1) == 1;
        GeneralDoorData.isRightRearDoorOpen = ((i >> 4) & 1) == 1;
        GeneralDoorData.isBackOpen = ((i >> 3) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m145setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[3];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_161_rear_wiper");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_220_driving_assistance");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("temperature_unit");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("_161_door_auto_lock");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(((i >> 4) & 1) == 1 ? "enable" : "disable"));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) map.get("parkingAssist");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf((i >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) map.get("_161_central_control_door_lock");
        if (settingUpdateEntity6 != null) {
            int i2 = (i >> 2) & 1;
            Log.i("_1161_MsgMgr", "setOnParseListeners: " + i2);
            arrayList.add(settingUpdateEntity6.setValue(i2 == 1 ? "_161_lock" : "_161_unlock"));
            Log.i("_1161_MsgMgr", "setOnParseListeners: " + settingUpdateEntity6.getValue());
            Log.i("_1161_MsgMgr", "setOnParseListeners: " + settingUpdateEntity6.getLeftListIndex() + "  " + settingUpdateEntity6.getRightListIndex());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-21, reason: not valid java name */
    public static final void m146setOnParseListeners$lambda21(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[4];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_283_car_setting_light_4");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_143_setting_20");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("_143_setting_21");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("fuel_unit");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 3) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) map.get("_161_emergency_braking");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf((i >> 1) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) map.get("_118_setting_title_13");
        if (settingUpdateEntity6 != null) {
            arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf(i & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-28, reason: not valid java name */
    public static final void m147setOnParseListeners$lambda28(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[5];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("on_off_btn_txt_7");
        if (settingUpdateEntity != null) {
            SettingUpdateEntity value = settingUpdateEntity.setValue(Integer.valueOf((i >> 5) & 7));
            Object value2 = settingUpdateEntity.getValue();

            arrayList.add(value.setProgress(((Integer) value2).intValue()));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_161_only_unlock_trunk");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("_161_reversing_radar");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 3) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-37, reason: not valid java name */
    public static final void m148setOnParseListeners$lambda37(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[6];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_143_0x76_d1_b01");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 6) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_143_0x76_d0_b45");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 4) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("_161_rearview_mirror_adaptive");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("outlander_simple_car_set_17");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 1) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) map.get("_161_disable_rear_mirror");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(i & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-46, reason: not valid java name */
    public static final void m149setOnParseListeners$lambda46(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[8];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_143_setting_7");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_81_traction_control_system");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("geely_theme_colors");
        if (settingUpdateEntity3 != null) {
            int mDifferent = this$0.getMDifferent();
            if (mDifferent == 4) {
                int i2 = (i >> 2) & 15;
                if (i2 == 6) {
                    i = 1;
                } else if (i2 == 7) {
                    i = 2;
                }
                arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf(i)));
            } else if (mDifferent == 24 || mDifferent == 18) {
                arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf(((i >> 2) & 15) == 8 ? 1 : 0)));
            } else if (mDifferent == 19) {
                int i3 = (i >> 2) & 15;
                if (i3 == 1) {
                    i = 1;
                } else if (i3 == 2) {
                    i = 2;
                }
                arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf(i)));
            }
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("_143_setting_8");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 1) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) map.get("_283_car_setting_pa_5");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(i & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-54, reason: not valid java name */
    public static final void m150setOnParseListeners$lambda54(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[7];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_94_blind_spot_monitoring");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_161_start_stop_disabled");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("_143_setting_5");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("_161_door_opening_options");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 4) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-59, reason: not valid java name */
    public static final void m151setOnParseListeners$lambda59(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        int i = this$0.mCanbusInfoInt[10];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_start_stop_status");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 6) & 1)));
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            super.parse(dataLength);
            if (isDoorStatusChange()) {
                this.this$0.updateDoorView(this.$context);
            }
            int i = this.this$0.mCanbusInfoInt[3] & 3;
            MsgMgr msgMgr = this.this$0;
            Context context = this.$context;
            if (this.displayPage != i) {
                this.displayPage = i;
                msgMgr.startDrivingDataActivity(context, i);
            }
            if (isSettingsDataChange()) {
                this.this$0.updateGeneralSettingData(this.list);
                this.this$0.updateSettingActivity(null);
            }
        }
    }

    private final boolean isDoorStatusChange() {
        if (this.isLeftFrontDoorOpen == GeneralDoorData.isLeftFrontDoorOpen && this.isRightFrontDoorOpen == GeneralDoorData.isRightFrontDoorOpen && this.isLeftRearDoorOpen == GeneralDoorData.isLeftRearDoorOpen && this.isRightRearDoorOpen == GeneralDoorData.isRightRearDoorOpen && this.isBackOpen == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
        this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
        this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
        this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
        this.isBackOpen = GeneralDoorData.isBackOpen;
        return true;
    }

    private final boolean isSettingsDataChange() {
        int[] iArr = this.this$0.mCanbusInfoInt;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);

        iArrCopyOf[2] = 0;
        iArrCopyOf[3] = iArrCopyOf[3] & 252;
        iArrCopyOf[5] = iArrCopyOf[5] & 248;
        iArrCopyOf[7] = iArrCopyOf[7] & NfDef.STATE_3WAY_M_HOLD;
        if (Arrays.equals(this.settingsData, iArrCopyOf)) {
            return false;
        }
        int[] iArrCopyOf2 = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length);

        this.settingsData = iArrCopyOf2;
        return true;
    }
}
