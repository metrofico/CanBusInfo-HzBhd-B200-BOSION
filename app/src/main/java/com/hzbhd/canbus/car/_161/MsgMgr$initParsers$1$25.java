package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$25 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private ArrayList<DriverUpdateEntity> list;
    private ArrayList<SettingUpdateEntity<Object>> list1;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$25(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x52】混动信息-电量储备");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final Context context = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$25$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$25.m183setOnParseListeners$lambda6(msgMgr, context, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m183setOnParseListeners$lambda6(MsgMgr this$0, Context context, MsgMgr$initParsers$1$25 this$1) {
        String strByResId;
        String strByResId2;



        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_3_0");
        ArrayList<SettingUpdateEntity<Object>> arrayList = null;
        if (driverUpdateEntity != null) {
            if (DataHandleUtils.getBoolBit7(this$0.mCanbusInfoInt[2])) {
                strByResId2 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId2 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList2 = this$1.list;
            if (arrayList2 == null) {

                arrayList2 = null;
            }
            arrayList2.add(driverUpdateEntity.setValue(strByResId2));
        }
        DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_3_1");
        if (driverUpdateEntity2 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this$0.mCanbusInfoInt[2], 0, 2);
            if (intFromByteWithBit == 0) {
                strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_1_0");
            } else if (intFromByteWithBit == 1) {
                strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_1_1");
            } else if (intFromByteWithBit == 2) {
                strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_1_2");
            } else {
                strByResId = CommUtil.getStrByResId(context, "set_default");
            }
            ArrayList<DriverUpdateEntity> arrayList3 = this$1.list;
            if (arrayList3 == null) {

                arrayList3 = null;
            }
            arrayList3.add(driverUpdateEntity2.setValue(strByResId));
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_driveInfo_0_3_0");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[2] >> 7));
            ArrayList<SettingUpdateEntity<Object>> arrayList4 = this$1.list1;
            if (arrayList4 == null) {

                arrayList4 = null;
            }
            arrayList4.add(settingUpdateEntity.setValue(settingUpdateEntity.getValue()));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_Power_Reserve");
        if (settingUpdateEntity2 != null) {
            if ((this$0.mCanbusInfoInt[2] >> 7) != 0) {
                settingUpdateEntity2.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this$0.mCanbusInfoInt[2], 0, 2)));
            } else {
                settingUpdateEntity2.setValue("OFF");
            }
            settingUpdateEntity2.setValueStr(true);
            ArrayList<SettingUpdateEntity<Object>> arrayList5 = this$1.list1;
            if (arrayList5 == null) {

            } else {
                arrayList = arrayList5;
            }
            arrayList.add(settingUpdateEntity2.setValue(settingUpdateEntity2.getValue()));
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            this.list1 = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.list;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
            MsgMgr msgMgr2 = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList2 = this.list1;
            if (arrayList2 == null) {

                arrayList2 = null;
            }
            msgMgr2.updateGeneralSettingData(arrayList2);
            this.this$0.updateSettingActivity(null);
        }
    }
}
