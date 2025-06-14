package com.hzbhd.canbus.car._55;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingletonForKt.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0005J&\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010#\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010$\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010%\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010&\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010'\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006("}, d2 = {"Lcom/hzbhd/canbus/car/_55/SingletonForKt;", "", "()V", "carMap", "", "", "", "mMsgMgr", "Lcom/hzbhd/canbus/car/_55/MsgMgr;", "getMMsgMgr", "()Lcom/hzbhd/canbus/car/_55/MsgMgr;", "setMMsgMgr", "(Lcom/hzbhd/canbus/car/_55/MsgMgr;)V", "init", "", "context", "Landroid/content/Context;", "uiMgr", "Lcom/hzbhd/canbus/car/_55/UiMgr;", "sendAirBtnData", "btn", "sendSettingsData", "settingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "leftPos", "rightPos", "value", "setAmplifierData", "data", "", "setDrivingAssistance", "setElectricDoor", "setHistoryOilConsumptionData", "setLightingData", "setLockData", "setOilConsumptionData", "setOriginalCarData", "setOther2Data", "setOtherData", "setRemoteControlData", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SingletonForKt {
    public static final SingletonForKt INSTANCE = new SingletonForKt();
    private static final Map<String, Integer> carMap = MapsKt.mapOf(TuplesKt.to("S55_Car_1", 26), TuplesKt.to("S55_Car_2", 27), TuplesKt.to("S55_Car_3", 30), TuplesKt.to("S55_Car_4", 32), TuplesKt.to("S55_Car_5", 33), TuplesKt.to("S55_Car_6", 29), TuplesKt.to("S55_Car_7", 31), TuplesKt.to("S55_Car_8", 34), TuplesKt.to("S55_Car_9", 35), TuplesKt.to("S55_Car_10", 36), TuplesKt.to("S55_Car_11", 37), TuplesKt.to("S55_Car_12", 38), TuplesKt.to("S55_Car_13", 39), TuplesKt.to("S55_Car_14", 40), TuplesKt.to("S55_Car_15", 41), TuplesKt.to("S55_Car_16", 42), TuplesKt.to("S55_Car_17", 43), TuplesKt.to("S55_Car_18", 44), TuplesKt.to("S55_Car_19", 45), TuplesKt.to("S55_Car_20", 46), TuplesKt.to("S55_Car_21", 47), TuplesKt.to("S55_Car_22", 48), TuplesKt.to("S55_Car_23", 1));
    public static MsgMgr mMsgMgr;

    private static final String setOilConsumptionData$unit1(int i) {
        return i != 0 ? i != 1 ? i != 2 ? " --" : " L/100Km" : " Km/L" : " MPG";
    }

    private static final String setOilConsumptionData$unit2(boolean z) {
        return z ? " Mile" : " Km";
    }

    private SingletonForKt() {
    }

    public final MsgMgr getMMsgMgr() {
        MsgMgr msgMgr = mMsgMgr;
        if (msgMgr != null) {
            return msgMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMsgMgr");
        return null;
    }

    public final void setMMsgMgr(MsgMgr msgMgr) {
        Intrinsics.checkNotNullParameter(msgMgr, "<set-?>");
        mMsgMgr = msgMgr;
    }

    public final void init(Context context, UiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(context);
        Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
        setMMsgMgr((MsgMgr) canMsgMgr);
        UiMgr uiMgr2 = uiMgr;
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr2, null, 4, null);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr2, null, 4, null);
    }

    public final void setHistoryOilConsumptionData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(data[5], data[6]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(data[10], data[11]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_3");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(data[15], data[16]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_4");
        if (item4 != null) {
            item4.setValue(String.valueOf(MsgMgrKt.getAnotherMsbLsbResult(data[2], data[3], data[4]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_5");
        if (item5 != null) {
            item5.setValue(String.valueOf(MsgMgrKt.getAnotherMsbLsbResult(data[7], data[8], data[9]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S55_DrivingInfoHistory_6");
        if (item6 == null) {
            return;
        }
        item6.setValue(String.valueOf(MsgMgrKt.getAnotherMsbLsbResult(data[12], data[13], data[14]) / 10.0f));
    }

    public final void setOilConsumptionData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_1");
        if (item != null) {
            item.setValue("Level " + data[2]);
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_1");
        if (item2 != null) {
            item2.setValue((DataHandleUtils.getMsbLsbResult(data[3], data[4]) / 10.0f) + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(data[14], 2, 2)));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_2");
        if (item3 != null) {
            item3.setValue((DataHandleUtils.getMsbLsbResult(data[5], data[6]) / 10.0f) + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(data[14], 2, 2)));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_3");
        if (item4 != null) {
            item4.setValue((DataHandleUtils.getMsbLsbResult(data[7], data[8]) / 10.0f) + setOilConsumptionData$unit1(DataHandleUtils.getIntFromByteWithBit(data[14], 4, 2)));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_4");
        if (item5 != null) {
            item5.setValue((MsgMgrKt.getAnotherMsbLsbResult(data[9], data[10], data[11]) / 10.0f) + setOilConsumptionData$unit2(DataHandleUtils.getBoolBit6(data[14])));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_5");
        if (item6 != null) {
            item6.setValue(DataHandleUtils.getMsbLsbResult(data[12], data[13]) + setOilConsumptionData$unit2(DataHandleUtils.getBoolBit7(data[14])));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_6");
        if (item7 != null) {
            item7.setValue(data[16] + " Km/H");
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("S55_OilConsumption_7");
        if (item8 == null) {
            return;
        }
        item8.setValue(data[17] + " : " + data[18]);
    }

    public final void setRemoteControlData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[3], 3, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[3], 2, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[3], 1, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_RemoteControl_4");
        if (itemListBean4 == null) {
            return;
        }
        itemListBean4.setValue(Integer.valueOf(intFromByteWithBit4));
    }

    public final void setLightingData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 3, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 3);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[3], 4, 3);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[3], 2, 2);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_Lighting_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(intFromByteWithBit5 == 0 ? "S55_DrivingAssistance_4_0" : itemListBean5.getValueSrnArray().get(intFromByteWithBit5 - 1));
        }
    }

    public final void setOriginalCarData(int[] data) {
        int i;
        String str;
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 5, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[2], 4, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[2], 2, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 2);
        int i2 = data[3];
        int i3 = data[4];
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[5], 7, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(data[5], 2, 2);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(data[5], 0, 1);
        int i4 = data[6];
        int i5 = data[7];
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(data[8], 5, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(data[8], 4, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(data[8], 3, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(data[8], 2, 1);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(data[8], 1, 1);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(data[8], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_5");
        String str2 = "set_default";
        if (itemListBean5 == null) {
            i = 2;
        } else {
            if (i2 != 1) {
                i = 2;
                str = i2 != 2 ? i2 != 3 ? "set_default" : "S55_OriginalCarScreen_5_3" : "S55_OriginalCarScreen_5_2";
            } else {
                i = 2;
                str = "S55_OriginalCarScreen_5_1";
            }
            itemListBean5.setValue(str);
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_6");
        if (itemListBean6 != null) {
            Integer numValueOf = Integer.valueOf(i3);
            numValueOf.intValue();
            INSTANCE.getMMsgMgr().openRightCamera(i3 == 1);
            itemListBean6.setValue(numValueOf);
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S55_Camera_1");
        if (itemListBean7 != null) {
            List<String> valueSrnArray = itemListBean7.getValueSrnArray();
            if (intFromByteWithBit5 == 1) {
                INSTANCE.getMMsgMgr().mEnterOrExitAux(true);
                i = 1;
            } else {
                INSTANCE.getMMsgMgr().mEnterOrExitAux(false);
            }
            itemListBean7.setValue(valueSrnArray.get(i));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_7");
        if (itemListBean8 != null) {
            itemListBean8.setValue(itemListBean8.getValueSrnArray().get(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_8");
        if (itemListBean9 != null) {
            itemListBean9.setValue(Integer.valueOf(intFromByteWithBit7));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_9");
        if (itemListBean10 != null) {
            if (i4 == 0) {
                str2 = "_336_state3";
            } else if (i4 == 1) {
                str2 = "_336_state4";
            }
            itemListBean10.setValue(str2);
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_10");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(i5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_11");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(intFromByteWithBit8));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_12");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(intFromByteWithBit9));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_13");
        if (itemListBean14 != null) {
            itemListBean14.setValue(itemListBean14.getValueSrnArray().get(intFromByteWithBit10));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_14");
        if (itemListBean15 != null) {
            itemListBean15.setValue(Integer.valueOf(intFromByteWithBit11));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_15");
        if (itemListBean16 != null) {
            itemListBean16.setValue(Integer.valueOf(intFromByteWithBit12));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = InitUtilsKt.getMSettingItemIndex().get("S55_OriginalCarScreen_16");
        if (itemListBean17 == null) {
            return;
        }
        itemListBean17.setValue(Integer.valueOf(intFromByteWithBit13));
    }

    public final void setDrivingAssistance(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 4, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[2], 1, 3) - 1;
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[3], 6, 2);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[3], 4, 2);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(data[3], 3, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(data[3], 2, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_1");
        if (itemListBean != null) {
            itemListBean.setValue(intFromByteWithBit2 == -1 ? "S55_DrivingAssistance_1_0" : itemListBean.getValueSrnArray().get(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(intFromByteWithBit5 == 0 ? "S55_DrivingAssistance_4_0" : itemListBean4.getValueSrnArray().get(intFromByteWithBit5 - 1));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(intFromByteWithBit7));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(intFromByteWithBit8));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S55_DrivingAssistance_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(itemListBean8.getValueSrnArray().get(intFromByteWithBit));
        }
    }

    public final void setLockData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[3], 6, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[3], 4, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[3], 3, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[3], 1, 2) - 1;
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(intFromByteWithBit5 == -1 ? "S55_Lock_4_1" : itemListBean4.getValueSrnArray().get(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S55_Lock_6");
        if (itemListBean6 == null) {
            return;
        }
        itemListBean6.setValue(Integer.valueOf(intFromByteWithBit));
    }

    public final void setOtherData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int valueOfBoolean = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(data[2]));
        int valueOfBoolean2 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(data[3]));
        int valueOfBoolean3 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(data[3]));
        int valueOfBoolean4 = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(data[3]));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 5, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[2], 4, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 4);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[4], 1, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[4], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(valueOfBoolean));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(valueOfBoolean2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(valueOfBoolean3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S55_Other1_9");
        if (itemListBean9 == null) {
            return;
        }
        itemListBean9.setValue(Integer.valueOf(valueOfBoolean4));
    }

    public final void setOther2Data(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[2], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[2], 5, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[2], 4, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(data[2], 3, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(data[2], 2, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(data[2], 0, 2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(intFromByteWithBit7));
        }
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(data[3], 7, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(data[3], 5, 2);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(data[3], 3, 2);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 3);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(intFromByteWithBit8));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(itemListBean9.getValueSrnArray().get(intFromByteWithBit9));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(itemListBean10.getValueSrnArray().get(intFromByteWithBit10));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S55_Other2_11");
        if (itemListBean11 != null) {
            if (intFromByteWithBit11 != 0) {
                itemListBean11.setProgress(intFromByteWithBit11 - 1);
                itemListBean11.setValue(String.valueOf(intFromByteWithBit11 - 4));
            } else {
                itemListBean11.setValue("无效");
            }
        }
    }

    public final void setElectricDoor(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[3], 3, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[3], 2, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(data[3], 1, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(data[3], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_3");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_4");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_1");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S55_ElectricDoor_2");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(intFromByteWithBit4));
        }
    }

    public final void setAmplifierData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(data[8], 1, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(data[8], 0, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S55_Amplifier_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Amplifier_2");
        if (itemListBean2 == null) {
            return;
        }
        itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
    }

    private static final void sendSettingsData$sendOriginalCarData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendLightingCarData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendRemoteControlCarData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendLockData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendDrivingAssistanceData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendOtherData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendLanguageData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -102, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendElectricDoorData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 122, (byte) i, (byte) i2});
    }

    private static final void sendSettingsData$sendAmplifierData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) i, (byte) i2});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public final void sendSettingsData(SettingPageUiSet settingPageUiSet, int leftPos, int rightPos, int value) {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean;
        String str;
        Intrinsics.checkNotNullParameter(settingPageUiSet, "settingPageUiSet");
        String titleSrn = settingPageUiSet.getList().get(leftPos).getItemList().get(rightPos).getTitleSrn();
        if (titleSrn != null) {
            int iHashCode = titleSrn.hashCode();
            switch (iHashCode) {
                case -1862005303:
                    if (titleSrn.equals("S55_Lock_1")) {
                        sendSettingsData$sendLockData(5, value);
                        break;
                    }
                    break;
                case -1862005302:
                    if (titleSrn.equals("S55_Lock_2")) {
                        sendSettingsData$sendLockData(4, value);
                        break;
                    }
                    break;
                case -1862005301:
                    if (titleSrn.equals("S55_Lock_3")) {
                        sendSettingsData$sendLockData(3, value);
                        break;
                    }
                    break;
                case -1862005300:
                    if (titleSrn.equals("S55_Lock_4")) {
                        sendSettingsData$sendLockData(2, value + 1);
                        break;
                    }
                    break;
                case -1862005299:
                    if (titleSrn.equals("S55_Lock_5")) {
                        sendSettingsData$sendLockData(1, value);
                        break;
                    }
                    break;
                case -1862005298:
                    if (titleSrn.equals("S55_Lock_6")) {
                        sendSettingsData$sendLockData(6, value);
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case -1638072122:
                            if (titleSrn.equals("S55_Tire_1")) {
                                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S55_Tire_1");
                                if (itemListBean2 != null) {
                                    CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte) value});
                                    itemListBean2.setValue(Integer.valueOf(value));
                                }
                                MsgMgrInterface canMsgMgr = MsgMgrFactory.getCanMsgMgr(InitUtilsKt.getMContext());
                                Intrinsics.checkNotNull(canMsgMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.MsgMgr");
                                ((MsgMgr) canMsgMgr).updateSettingActivity(null);
                                break;
                            }
                            break;
                        case -1466512577:
                            if (titleSrn.equals("S55_Other1_1")) {
                                sendSettingsData$sendOtherData(16, value);
                                break;
                            }
                            break;
                        case -1466512576:
                            if (titleSrn.equals("S55_Other1_2")) {
                                sendSettingsData$sendOtherData(15, value);
                                break;
                            }
                            break;
                        case -1466512575:
                            if (titleSrn.equals("S55_Other1_3")) {
                                sendSettingsData$sendOtherData(14, value);
                                break;
                            }
                            break;
                        case -1466512574:
                            if (titleSrn.equals("S55_Other1_4")) {
                                sendSettingsData$sendOtherData(11, value);
                                break;
                            }
                            break;
                        case -1466512573:
                            if (titleSrn.equals("S55_Other1_5")) {
                                sendSettingsData$sendOtherData(12, value);
                                break;
                            }
                            break;
                        case -1466512572:
                            if (titleSrn.equals("S55_Other1_6")) {
                                sendSettingsData$sendOtherData(18, value);
                                break;
                            }
                            break;
                        case -1466512571:
                            if (titleSrn.equals("S55_Other1_7")) {
                                sendSettingsData$sendOtherData(20, value);
                                break;
                            }
                            break;
                        case -1466512570:
                            if (titleSrn.equals("S55_Other1_8")) {
                                sendSettingsData$sendOtherData(17, value);
                                break;
                            }
                            break;
                        case -1466512569:
                            if (titleSrn.equals("S55_Other1_9")) {
                                sendSettingsData$sendOtherData(19, value);
                                break;
                            }
                            break;
                        case 102584022:
                            if (titleSrn.equals("language_setup")) {
                                sendSettingsData$sendLanguageData(1, value + 1);
                                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("language_setup");
                                if (itemListBean3 != null) {
                                    itemListBean3.setValue(itemListBean3.getValueSrnArray().get(value));
                                    break;
                                }
                            }
                            break;
                        case 748741536:
                            if (titleSrn.equals("S18_Car_0") && (itemListBean = InitUtilsKt.getMSettingItemIndex().get("S18_Car_0")) != null) {
                                byte[] bArr = new byte[4];
                                bArr[0] = 22;
                                bArr[1] = 36;
                                Integer num = carMap.get(itemListBean.getValueSrnArray().get(value));
                                if (num != null) {
                                    bArr[2] = (byte) num.intValue();
                                    bArr[3] = 2;
                                    CanbusMsgSender.sendMsg(bArr);
                                    itemListBean.setValue(itemListBean.getValueSrnArray().get(value));
                                    break;
                                }
                            }
                            break;
                        case 1782780208:
                            if (titleSrn.equals("S55_Other2_10")) {
                                sendSettingsData$sendOtherData(2, value);
                                break;
                            }
                            break;
                        case 1782780209:
                            if (titleSrn.equals("S55_Other2_11")) {
                                sendSettingsData$sendOtherData(1, value + 4);
                                break;
                            }
                            break;
                        default:
                            switch (iHashCode) {
                                case -1466511616:
                                    if (titleSrn.equals("S55_Other2_1")) {
                                        sendSettingsData$sendOtherData(10, value);
                                        break;
                                    }
                                    break;
                                case -1466511615:
                                    if (titleSrn.equals("S55_Other2_2")) {
                                        sendSettingsData$sendOtherData(9, value);
                                        break;
                                    }
                                    break;
                                case -1466511614:
                                    if (titleSrn.equals("S55_Other2_3")) {
                                        sendSettingsData$sendOtherData(6, value);
                                        break;
                                    }
                                    break;
                                case -1466511613:
                                    if (titleSrn.equals("S55_Other2_4")) {
                                        sendSettingsData$sendOtherData(7, value);
                                        break;
                                    }
                                    break;
                                case -1466511612:
                                    if (titleSrn.equals("S55_Other2_5")) {
                                        sendSettingsData$sendOtherData(8, value);
                                        break;
                                    }
                                    break;
                                case -1466511611:
                                    if (titleSrn.equals("S55_Other2_6")) {
                                        sendSettingsData$sendOtherData(5, value);
                                        break;
                                    }
                                    break;
                                case -1466511610:
                                    if (titleSrn.equals("S55_Other2_7")) {
                                        sendSettingsData$sendOtherData(4, value);
                                        break;
                                    }
                                    break;
                                case -1466511609:
                                    if (titleSrn.equals("S55_Other2_8")) {
                                        sendSettingsData$sendOtherData(13, value);
                                        break;
                                    }
                                    break;
                                case -1466511608:
                                    if (titleSrn.equals("S55_Other2_9")) {
                                        sendSettingsData$sendOtherData(3, value);
                                        break;
                                    }
                                    break;
                                default:
                                    switch (iHashCode) {
                                        case -949707653:
                                            if (titleSrn.equals("S55_OriginalCarScreen_10")) {
                                                sendSettingsData$sendOriginalCarData(14, value);
                                                break;
                                            }
                                            break;
                                        case -949707652:
                                            if (titleSrn.equals("S55_OriginalCarScreen_11")) {
                                                sendSettingsData$sendOriginalCarData(17, value);
                                                break;
                                            }
                                            break;
                                        case -949707651:
                                            if (titleSrn.equals("S55_OriginalCarScreen_12")) {
                                                sendSettingsData$sendOriginalCarData(16, value);
                                                break;
                                            }
                                            break;
                                        case -949707650:
                                            if (titleSrn.equals("S55_OriginalCarScreen_13")) {
                                                sendSettingsData$sendOriginalCarData(13, value + 6);
                                                break;
                                            }
                                            break;
                                        case -949707649:
                                            if (titleSrn.equals("S55_OriginalCarScreen_14")) {
                                                sendSettingsData$sendOriginalCarData(13, value + 4);
                                                break;
                                            }
                                            break;
                                        case -949707648:
                                            if (titleSrn.equals("S55_OriginalCarScreen_15")) {
                                                sendSettingsData$sendOriginalCarData(13, value + 2);
                                                break;
                                            }
                                            break;
                                        case -949707647:
                                            if (titleSrn.equals("S55_OriginalCarScreen_16")) {
                                                sendSettingsData$sendOriginalCarData(13, value);
                                                break;
                                            }
                                            break;
                                        default:
                                            switch (iHashCode) {
                                                case -908645891:
                                                    if (titleSrn.equals("S55_RemoteControl_1")) {
                                                        sendSettingsData$sendRemoteControlCarData(4, value);
                                                        break;
                                                    }
                                                    break;
                                                case -908645890:
                                                    if (titleSrn.equals("S55_RemoteControl_2")) {
                                                        sendSettingsData$sendRemoteControlCarData(3, value);
                                                        break;
                                                    }
                                                    break;
                                                case -908645889:
                                                    if (titleSrn.equals("S55_RemoteControl_3")) {
                                                        sendSettingsData$sendRemoteControlCarData(2, value);
                                                        break;
                                                    }
                                                    break;
                                                case -908645888:
                                                    if (titleSrn.equals("S55_RemoteControl_4")) {
                                                        sendSettingsData$sendRemoteControlCarData(1, value);
                                                        break;
                                                    }
                                                    break;
                                                default:
                                                    switch (iHashCode) {
                                                        case -634715383:
                                                            if (titleSrn.equals("S55_ElectricDoor_1")) {
                                                                sendSettingsData$sendElectricDoorData(2, value);
                                                                break;
                                                            }
                                                            break;
                                                        case -634715382:
                                                            if (titleSrn.equals("S55_ElectricDoor_2")) {
                                                                sendSettingsData$sendElectricDoorData(1, value);
                                                                break;
                                                            }
                                                            break;
                                                        case -634715381:
                                                            if (titleSrn.equals("S55_ElectricDoor_3")) {
                                                                sendSettingsData$sendElectricDoorData(4, value);
                                                                break;
                                                            }
                                                            break;
                                                        case -634715380:
                                                            if (titleSrn.equals("S55_ElectricDoor_4")) {
                                                                sendSettingsData$sendElectricDoorData(3, value);
                                                                break;
                                                            }
                                                            break;
                                                        default:
                                                            switch (iHashCode) {
                                                                case -543432342:
                                                                    if (titleSrn.equals("S55_Lighting_1")) {
                                                                        sendSettingsData$sendLightingCarData(5, value);
                                                                        break;
                                                                    }
                                                                    break;
                                                                case -543432341:
                                                                    if (titleSrn.equals("S55_Lighting_2")) {
                                                                        sendSettingsData$sendLightingCarData(4, value);
                                                                        break;
                                                                    }
                                                                    break;
                                                                case -543432340:
                                                                    if (titleSrn.equals("S55_Lighting_3")) {
                                                                        sendSettingsData$sendLightingCarData(3, value);
                                                                        break;
                                                                    }
                                                                    break;
                                                                case -543432339:
                                                                    if (titleSrn.equals("S55_Lighting_4")) {
                                                                        sendSettingsData$sendLightingCarData(2, value);
                                                                        break;
                                                                    }
                                                                    break;
                                                                case -543432338:
                                                                    if (titleSrn.equals("S55_Lighting_5")) {
                                                                        sendSettingsData$sendLightingCarData(1, value + 1);
                                                                        break;
                                                                    }
                                                                    break;
                                                                default:
                                                                    switch (iHashCode) {
                                                                        case 164943287:
                                                                            if (titleSrn.equals("S55_Amplifier_1")) {
                                                                                sendSettingsData$sendAmplifierData(7, value);
                                                                                break;
                                                                            }
                                                                            break;
                                                                        case 164943288:
                                                                            if (titleSrn.equals("S55_Amplifier_2")) {
                                                                                sendSettingsData$sendAmplifierData(8, value);
                                                                                break;
                                                                            }
                                                                            break;
                                                                        default:
                                                                            switch (iHashCode) {
                                                                                case 1120198181:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_1")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(9, value + 1);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198182:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_2")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(8, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198183:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_3")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(7, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198184:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_4")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(4, value + 1);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198185:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_5")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(3, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198186:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_6")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(2, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198187:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_7")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(1, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                case 1120198188:
                                                                                    if (titleSrn.equals("S55_DrivingAssistance_8")) {
                                                                                        sendSettingsData$sendDrivingAssistanceData(10, value);
                                                                                        break;
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    switch (iHashCode) {
                                                                                        case 1354837589:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_1")) {
                                                                                                sendSettingsData$sendOriginalCarData(21, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837590:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_2")) {
                                                                                                sendSettingsData$sendOriginalCarData(20, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837591:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_3")) {
                                                                                                sendSettingsData$sendOriginalCarData(19, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837592:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_4")) {
                                                                                                sendSettingsData$sendOriginalCarData(18, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837593:
                                                                                            str = "S55_OriginalCarScreen_5";
                                                                                            break;
                                                                                        case 1354837594:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_6")) {
                                                                                                sendSettingsData$sendOriginalCarData(7, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837595:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_7")) {
                                                                                                sendSettingsData$sendOriginalCarData(12, value + 4);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837596:
                                                                                            if (titleSrn.equals("S55_OriginalCarScreen_8")) {
                                                                                                sendSettingsData$sendOriginalCarData(12, value);
                                                                                                break;
                                                                                            }
                                                                                            break;
                                                                                        case 1354837597:
                                                                                            str = "S55_OriginalCarScreen_9";
                                                                                            break;
                                                                                    }
                                                                                    titleSrn.equals(str);
                                                                                    break;
                                                                            }
                                                                    }
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
    }

    private static final void sendAirBtnData$sendAirBtnCmd(int i) {
        byte b = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, 61, b, 0});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void sendAirBtnData(String btn) {
        Intrinsics.checkNotNullParameter(btn, "btn");
        switch (btn.hashCode()) {
            case -1470045433:
                if (btn.equals("front_defog")) {
                    sendAirBtnData$sendAirBtnCmd(5);
                    break;
                }
                break;
            case -631663038:
                if (btn.equals("rear_defog")) {
                    sendAirBtnData$sendAirBtnCmd(6);
                    break;
                }
                break;
            case 3106:
                if (btn.equals("ac")) {
                    if (!GeneralAirData.ac) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
                        break;
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
                        break;
                    }
                }
                break;
            case 3005871:
                if (btn.equals("auto")) {
                    sendAirBtnData$sendAirBtnCmd(4);
                    break;
                }
                break;
            case 3545755:
                if (btn.equals("sync")) {
                    sendAirBtnData$sendAirBtnCmd(3);
                    break;
                }
                break;
            case 106858757:
                if (btn.equals("power")) {
                    sendAirBtnData$sendAirBtnCmd(1);
                    break;
                }
                break;
            case 756225563:
                if (btn.equals("in_out_cycle")) {
                    sendAirBtnData$sendAirBtnCmd(7);
                    break;
                }
                break;
        }
    }
}
