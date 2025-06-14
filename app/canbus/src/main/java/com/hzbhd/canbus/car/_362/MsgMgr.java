package com.hzbhd.canbus.car._362;

import android.content.Context;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\n\n\u0002\b\u0012\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010 \u001a\u00020\u001fH\u0002J\u001c\u0010!\u001a\u00020\u001f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020'H\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\b\u0010,\u001a\u00020\u001fH\u0002J\b\u0010-\u001a\u00020\u001fH\u0002J\b\u0010.\u001a\u00020\u001fH\u0002J\b\u0010/\u001a\u00020\u001fH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001fH\u0002J\b\u00102\u001a\u00020\u001fH\u0002J\b\u00103\u001a\u00020\u001fH\u0002J\u0006\u00104\u001a\u00020\u001fJ\b\u00105\u001a\u00020\u001fH\u0002J\b\u00106\u001a\u00020\u001fH\u0002J\b\u00107\u001a\u00020\u001fH\u0002J\b\u00108\u001a\u00020\u001fH\u0002J\b\u00109\u001a\u00020\u001fH\u0002J\b\u0010:\u001a\u00020\u001fH\u0002J\u0013\u0010;\u001a\u0004\u0018\u00010<*\u00020\u0013H\u0002¢\u0006\u0002\u0010=R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010\u0016\u001a&\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0012j\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006>"}, d2 = {"Lcom/hzbhd/canbus/car/_362/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastDeviceStatus", "", "mDrivingItemIndex", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "mSettingItemIndex", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "mUiMgr", "Lcom/hzbhd/canbus/car/_362/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_362/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_362/UiMgr;)V", "afterServiceNormalSetting", "", "airConditioningInfo", "canbusInfoChange", "canbusInfo", "", "carSpeedInfo", "corneringLampInfo", "culTrackAngle", "", "track", "", "max", "drivingModeAndAssistanceInfo", "electricPSInfo", "essentialInfo", "frontRadarInfo", "fuelConsumptionPerMin", "historicalFuelConsumption", "instantaneousFuelConsumption", "powerAmplifierInfo", "rearRadarInfo", "returnClick", "seatHeatingStatus", "steeringWheelKeys", "systemInfo", "tirePMSInfo", "vehicleSettings", "vehicleSpeedInfo", "toDoubleOrStringSelf", "", "(Ljava/lang/String;)Ljava/lang/Double;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;
    private boolean lastDeviceStatus;
    private HashMap<String, DriverDataPageUiSet.Page.Item> mDrivingItemIndex = new HashMap<>();
    private HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndex = new HashMap<>();
    public UiMgr mUiMgr;

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("frame");
        return null;
    }

    public final void setFrame(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.frame = iArr;
    }

    public final UiMgr getMUiMgr() {
        UiMgr uiMgr = this.mUiMgr;
        if (uiMgr != null) {
            return uiMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
        return null;
    }

    public final void setMUiMgr(UiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(uiMgr, "<set-?>");
        this.mUiMgr = uiMgr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._362.UiMgr");
        setMUiMgr((UiMgr) canUiMgr);
        GeneralDoorData.isSubShowSeatBelt = true;
        InitUtilsKt.initDrivingItemsIndexHashMap(context, getMUiMgr(), this.mDrivingItemIndex);
        InitUtilsKt.initSettingItemsIndexHashMap(context, getMUiMgr(), this.mSettingItemIndex);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null || canbusInfo == null) {
            return;
        }
        setContext(context);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 22) {
            vehicleSpeedInfo();
            return;
        }
        if (i == 53) {
            seatHeatingStatus();
            return;
        }
        if (i == 80) {
            carSpeedInfo();
            return;
        }
        if (i == 29) {
            frontRadarInfo();
            return;
        }
        if (i == 30) {
            rearRadarInfo();
            return;
        }
        if (i == 40) {
            airConditioningInfo();
            return;
        }
        if (i == 41) {
            electricPSInfo();
            return;
        }
        if (i == 49) {
            powerAmplifierInfo();
            return;
        }
        if (i == 50) {
            systemInfo();
            return;
        }
        if (i == 82) {
            corneringLampInfo();
            return;
        }
        if (i != 83) {
            switch (i) {
                case 32:
                    steeringWheelKeys();
                    break;
                case 33:
                    fuelConsumptionPerMin();
                    break;
                case 34:
                    instantaneousFuelConsumption();
                    break;
                case 35:
                    historicalFuelConsumption();
                    break;
                case 36:
                    essentialInfo();
                    break;
                case 37:
                    tirePMSInfo();
                    break;
                case 38:
                    vehicleSettings();
                    break;
            }
            return;
        }
        drivingModeAndAssistanceInfo();
    }

    private final void drivingModeAndAssistanceInfo() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2);
        boolean boolBit72 = DataHandleUtils.getBoolBit7(getFrame()[3]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrame()[3]);
        boolean boolBit73 = DataHandleUtils.getBoolBit7(getFrame()[4]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(getFrame()[4]);
        if (boolBit7) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
            Intrinsics.checkNotNull(itemListBean);
            if (itemListBean.getSelectIndex() == 2) {
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
                Intrinsics.checkNotNull(itemListBean2);
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
                itemListBean3.setValue(itemListBean3.getValueSrnArray().get(0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
            Intrinsics.checkNotNull(itemListBean4);
            itemListBean4.getValueSrnArray().remove("S362_drivingModeAndAssistanceInfo_2_2");
        } else {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
            Intrinsics.checkNotNull(itemListBean5);
            if (!itemListBean5.getValueSrnArray().contains("S362_drivingModeAndAssistanceInfo_2_2")) {
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
                Intrinsics.checkNotNull(itemListBean6);
                itemListBean6.getValueSrnArray().add("S362_drivingModeAndAssistanceInfo_2_2");
            }
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_1");
        Intrinsics.checkNotNull(itemListBean7);
        itemListBean7.setValue(Integer.valueOf(boolBit7 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_2");
        Intrinsics.checkNotNull(itemListBean8);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = itemListBean8;
        itemListBean9.setValue(itemListBean9.getValueSrnArray().get(intFromByteWithBit >= 0 && intFromByteWithBit < 3 ? intFromByteWithBit : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_3");
        Intrinsics.checkNotNull(itemListBean10);
        itemListBean10.setValue(Integer.valueOf(boolBit72 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_4");
        Intrinsics.checkNotNull(itemListBean11);
        itemListBean11.setValue(Integer.valueOf(boolBit6 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_5");
        Intrinsics.checkNotNull(itemListBean12);
        itemListBean12.setValue(Integer.valueOf(boolBit5 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_6");
        Intrinsics.checkNotNull(itemListBean13);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = itemListBean13;
        itemListBean14.setValue(itemListBean14.getValueSrnArray().get(boolBit73 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = this.mSettingItemIndex.get("S362_drivingModeAndAssistanceInfo_7");
        Intrinsics.checkNotNull(itemListBean15);
        itemListBean15.setValue(Integer.valueOf(boolBit62 ? 1 : 0));
        updateSettingActivity(null);
    }

    private final void corneringLampInfo() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[2]);
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D362_corneringLampInfo_2");
        Intrinsics.checkNotNull(item);
        item.setValue(boolBit7 ? "ON" : "OFF");
        DriverDataPageUiSet.Page.Item item2 = this.mDrivingItemIndex.get("D362_corneringLampInfo_1");
        Intrinsics.checkNotNull(item2);
        item2.setValue(boolBit6 ? "ON" : "OFF");
        updateDriveDataActivity(null);
    }

    private final void carSpeedInfo() {
        int msbLsbResult = DataHandleUtils.getMsbLsbResult(getFrame()[3], getFrame()[2]);
        int i = getFrame()[4];
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D362_carSpeedInfo_1");
        Intrinsics.checkNotNull(item);
        item.setValue(msbLsbResult + " rp");
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S362_carSpeedInfo_1");
        Intrinsics.checkNotNull(itemListBean);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = itemListBean;
        itemListBean2.setProgress(i / 10);
        itemListBean2.setValue(itemListBean2.getProgress() == 0 ? "OFF" : itemListBean2.getProgress() + " s");
        updateDriveDataActivity(null);
        updateSettingActivity(null);
    }

    private final void seatHeatingStatus() {
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 3);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 3);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 3);
        updateAirActivity(getContext(), 1001);
    }

    private final void systemInfo() {
        boolean boolBit0 = DataHandleUtils.getBoolBit0(getFrame()[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(getFrame()[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrame()[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrame()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[2]);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[2]);
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("_350_d_3_0");
        Intrinsics.checkNotNull(item);
        item.setValue(boolBit0 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item2 = this.mDrivingItemIndex.get("_350_d_3_1");
        Intrinsics.checkNotNull(item2);
        item2.setValue(boolBit1 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item3 = this.mDrivingItemIndex.get("_350_d_3_2");
        Intrinsics.checkNotNull(item3);
        item3.setValue(boolBit2 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item4 = this.mDrivingItemIndex.get("_350_d_3_3");
        Intrinsics.checkNotNull(item4);
        item4.setValue(boolBit3 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item5 = this.mDrivingItemIndex.get("_350_d_3_4");
        Intrinsics.checkNotNull(item5);
        item5.setValue(boolBit6 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        DriverDataPageUiSet.Page.Item item6 = this.mDrivingItemIndex.get("_350_d_3_5");
        Intrinsics.checkNotNull(item6);
        item6.setValue(boolBit7 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        updateDriveDataActivity(null);
        if (boolBit3) {
            forceReverse(getContext(), true);
        } else {
            forceReverse(getContext(), false);
        }
    }

    private final void powerAmplifierInfo() {
        GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 4), 0, 14), 0, 2, null) - 7;
        GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 4), 0, 14) - 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 4), 2, 12) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(getFrame()[5], 0, 63);
        updateAmplifierActivity(null);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S362_powerAmplifierInfo_1");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 4) == 8 ? 1 : 0));
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D362_powerAmplifierInfo_1");
        Intrinsics.checkNotNull(item);
        item.setValue(DataHandleUtils.getBoolBit0(getFrame()[6]) ? "有" : "无");
        DriverDataPageUiSet.Page.Item item2 = this.mDrivingItemIndex.get("D362_powerAmplifierInfo_2");
        Intrinsics.checkNotNull(item2);
        item2.setValue(DataHandleUtils.getBoolBit1(getFrame()[6]) ? "有" : "无");
        updateSettingActivity(null);
        updateDriveDataActivity(null);
    }

    private final void electricPSInfo() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) getFrame()[2], (byte) getFrame()[3], 0, 380, 12);
        updateParkUi(null, getContext());
    }

    private final void airConditioningInfo() {
        String str;
        String strValueOf;
        GeneralAirData.power = DataHandleUtils.getBoolBit7(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.negative_ion = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit0(getFrame()[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 4);
        int i = getFrame()[4];
        String strValueOf2 = "--";
        if (i == 0) {
            str = "HI";
            strValueOf = "LO";
        } else if (i == 31) {
            strValueOf = "HI";
            str = strValueOf;
        } else {
            if (1 <= i && i < 30) {
                str = "HI";
                strValueOf = String.valueOf(18 + ((getFrame()[4] - 1) * 0.5d));
            } else {
                str = "HI";
                strValueOf = 33 <= i && i < 39 ? String.valueOf(15 + ((getFrame()[4] - 33) * 0.5d)) : "--";
            }
        }
        GeneralAirData.front_left_temperature = strValueOf;
        int i2 = getFrame()[5];
        if (i2 == 0) {
            strValueOf2 = "LO";
        } else if (i2 == 31) {
            strValueOf2 = str;
        } else {
            if (1 <= i2 && i2 < 30) {
                strValueOf2 = String.valueOf(18 + ((getFrame()[5] - 1) * 0.5d));
            } else {
                if (33 <= i2 && i2 < 39) {
                    strValueOf2 = String.valueOf(15 + ((getFrame()[5] - 33) * 0.5d));
                }
            }
        }
        GeneralAirData.front_right_temperature = strValueOf2;
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(getFrame()[6]);
        if (DataHandleUtils.getBoolBit0(getFrame()[6])) {
            String front_left_temperature = GeneralAirData.front_left_temperature;
            Intrinsics.checkNotNullExpressionValue(front_left_temperature, "front_left_temperature");
            Double doubleOrNull = StringsKt.toDoubleOrNull(front_left_temperature);
            if (doubleOrNull != null) {
                GeneralAirData.front_left_temperature = MsgMgrKt.transToF(doubleOrNull.doubleValue());
            }
            String front_right_temperature = GeneralAirData.front_right_temperature;
            Intrinsics.checkNotNullExpressionValue(front_right_temperature, "front_right_temperature");
            Double doubleOrNull2 = StringsKt.toDoubleOrNull(front_right_temperature);
            if (doubleOrNull2 != null) {
                GeneralAirData.front_right_temperature = MsgMgrKt.transToF(doubleOrNull2.doubleValue());
            }
        } else {
            String front_left_temperature2 = GeneralAirData.front_left_temperature;
            Intrinsics.checkNotNullExpressionValue(front_left_temperature2, "front_left_temperature");
            Double doubleOrNull3 = StringsKt.toDoubleOrNull(front_left_temperature2);
            if (doubleOrNull3 != null) {
                GeneralAirData.front_left_temperature = MsgMgrKt.transToC(doubleOrNull3.doubleValue());
            }
            String front_right_temperature2 = GeneralAirData.front_right_temperature;
            Intrinsics.checkNotNullExpressionValue(front_right_temperature2, "front_right_temperature");
            Double doubleOrNull4 = StringsKt.toDoubleOrNull(front_right_temperature2);
            if (doubleOrNull4 != null) {
                GeneralAirData.front_right_temperature = MsgMgrKt.transToC(doubleOrNull4.doubleValue());
            }
        }
        updateAirActivity(getContext(), 1001);
    }

    private final void vehicleSettings() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 3);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 2, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S361_d0b7");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(boolBit7 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndex.get("S361_d0b6t4");
        Intrinsics.checkNotNull(itemListBean2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
        itemListBean3.setProgress(intFromByteWithBit);
        itemListBean3.setValue(String.valueOf(itemListBean3.getProgress()));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndex.get("S361_d0b2t3");
        Intrinsics.checkNotNull(itemListBean4);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = itemListBean4;
        itemListBean5.setValue(itemListBean5.getValueSrnArray().get(intFromByteWithBit2));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = this.mSettingItemIndex.get("S361_d0b0t1");
        Intrinsics.checkNotNull(itemListBean6);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = itemListBean6;
        itemListBean7.setValue(itemListBean7.getValueSrnArray().get(intFromByteWithBit3));
        boolean boolBit72 = DataHandleUtils.getBoolBit7(getFrame()[3]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrame()[3]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrame()[3]);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 3);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = this.mSettingItemIndex.get("S361_d1b7");
        Intrinsics.checkNotNull(itemListBean8);
        itemListBean8.setValue(Integer.valueOf(boolBit72 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = this.mSettingItemIndex.get("S361_d1b6");
        Intrinsics.checkNotNull(itemListBean9);
        itemListBean9.setValue(Integer.valueOf(boolBit6 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = this.mSettingItemIndex.get("S361_d1b5");
        Intrinsics.checkNotNull(itemListBean10);
        itemListBean10.setValue(Integer.valueOf(boolBit5 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = this.mSettingItemIndex.get("S361_d1b4");
        Intrinsics.checkNotNull(itemListBean11);
        itemListBean11.setValue(Integer.valueOf(boolBit4 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = this.mSettingItemIndex.get("S361_d1b0t2");
        Intrinsics.checkNotNull(itemListBean12);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = itemListBean12;
        itemListBean13.setProgress(intFromByteWithBit4);
        itemListBean13.setValue(String.valueOf(itemListBean13.getProgress()));
        boolean boolBit73 = DataHandleUtils.getBoolBit7(getFrame()[4]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(getFrame()[4]);
        boolean boolBit52 = DataHandleUtils.getBoolBit5(getFrame()[4]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrame()[4]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrame()[4]);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = this.mSettingItemIndex.get("S361_d2b7");
        Intrinsics.checkNotNull(itemListBean14);
        itemListBean14.setValue(Integer.valueOf(boolBit73 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = this.mSettingItemIndex.get("S361_d2b6");
        Intrinsics.checkNotNull(itemListBean15);
        itemListBean15.setValue(Integer.valueOf(boolBit62 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = this.mSettingItemIndex.get("S361_d2b5");
        Intrinsics.checkNotNull(itemListBean16);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = itemListBean16;
        itemListBean17.setValue(itemListBean17.getValueSrnArray().get(boolBit52 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = this.mSettingItemIndex.get("S362_vehicleSettings_1");
        Intrinsics.checkNotNull(itemListBean18);
        itemListBean18.setValue(Integer.valueOf(boolBit42 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = this.mSettingItemIndex.get("S362_vehicleSettings_2");
        Intrinsics.checkNotNull(itemListBean19);
        itemListBean19.setValue(Integer.valueOf(boolBit3 ? 1 : 0));
        boolean boolBit74 = DataHandleUtils.getBoolBit7(getFrame()[5]);
        boolean boolBit63 = DataHandleUtils.getBoolBit6(getFrame()[5]);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = this.mSettingItemIndex.get("S362_vehicleSettings_3");
        Intrinsics.checkNotNull(itemListBean20);
        itemListBean20.setValue(Integer.valueOf(boolBit74 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = this.mSettingItemIndex.get("S362_vehicleSettings_4");
        Intrinsics.checkNotNull(itemListBean21);
        itemListBean21.setValue(Integer.valueOf(boolBit63 ? 1 : 0));
        updateSettingActivity(null);
    }

    private final void tirePMSInfo() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[2]);
        if (this.lastDeviceStatus != boolBit7) {
            this.lastDeviceStatus = boolBit7;
            if (!boolBit7) {
                getMUiMgr().getMainUiSet(getContext()).getBtnAction().remove(MainAction.TIRE_INFO);
            } else if (!getMUiMgr().getMainUiSet(getContext()).getBtnAction().contains(MainAction.TIRE_INFO)) {
                getMUiMgr().getMainUiSet(getContext()).getBtnAction().add(MainAction.TIRE_INFO);
            }
        }
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[2]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrame()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "None" : "2.5 KPA" : "PSI" : "0.1 BAR";
        GeneralTireData.isHaveSpareTire = boolBit5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrame()[3]), str}));
        arrayList.add(new TireUpdateEntity(1, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrame()[4]), str}));
        arrayList.add(new TireUpdateEntity(2, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrame()[5]), str}));
        arrayList.add(new TireUpdateEntity(3, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrame()[6]), str}));
        if (GeneralTireData.isHaveSpareTire) {
            arrayList.add(new TireUpdateEntity(4, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrame()[7]), str}));
        }
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private final void essentialInfo() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(getFrame()[3]);
        updateDoorView(getContext());
    }

    private final void historicalFuelConsumption() {
        int i = getFrame()[2];
        int i2 = (getFrame()[3] * 256) + getFrame()[4];
        int i3 = (getFrame()[5] * 256) + getFrame()[6];
        int i4 = (getFrame()[7] * 256) + getFrame()[8];
        int i5 = (getFrame()[9] * 256) + getFrame()[10];
        int i6 = (getFrame()[11] * 256) + getFrame()[12];
        int i7 = (getFrame()[13] * 256) + getFrame()[14];
        String str = i != 0 ? i != 1 ? i != 2 ? "None" : "L/100Km" : "Km/L" : "MPG";
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D361_2_d1t2");
        Intrinsics.checkNotNull(item);
        item.setValue((i2 / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item2 = this.mDrivingItemIndex.get("D361_2_d3t4");
        Intrinsics.checkNotNull(item2);
        item2.setValue((i3 / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item3 = this.mDrivingItemIndex.get("D361_2_d5t6");
        Intrinsics.checkNotNull(item3);
        item3.setValue((i4 / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item4 = this.mDrivingItemIndex.get("D361_2_d7t8");
        Intrinsics.checkNotNull(item4);
        item4.setValue((i5 / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item5 = this.mDrivingItemIndex.get("D361_2_d9t10");
        Intrinsics.checkNotNull(item5);
        item5.setValue((i6 / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item6 = this.mDrivingItemIndex.get("D361_2_d11t12");
        Intrinsics.checkNotNull(item6);
        item6.setValue((i7 / 10) + ' ' + str);
        updateDriveDataActivity(null);
    }

    private final void instantaneousFuelConsumption() {
        int i = getFrame()[2];
        int i2 = (getFrame()[3] * 256) + getFrame()[4];
        String str = i != 0 ? i != 1 ? i != 2 ? "None" : "L/100Km" : "Km/L" : "MPG";
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D361_1_d1t2");
        Intrinsics.checkNotNull(item);
        item.setValue((i2 / 10) + ' ' + str);
        updateDriveDataActivity(null);
    }

    private final void fuelConsumptionPerMin() {
        int i = (getFrame()[2] * 256) + getFrame()[3];
        int i2 = (getFrame()[4] * 256) + getFrame()[5];
        int i3 = (getFrame()[6] * 256) + getFrame()[7];
        int i4 = getFrame()[8];
        String str = i4 != 1 ? i4 != 2 ? "None" : "Km" : "Mile";
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_1");
        Intrinsics.checkNotNull(item);
        item.setValue(Intrinsics.areEqual(str, "None") ? "----" : (i / 10) + ' ' + str);
        DriverDataPageUiSet.Page.Item item2 = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_2");
        Intrinsics.checkNotNull(item2);
        item2.setValue((i2 / 60) + " : " + (i2 % 60));
        DriverDataPageUiSet.Page.Item item3 = this.mDrivingItemIndex.get("D362_fuelConsumptionPerMin_3");
        Intrinsics.checkNotNull(item3);
        item3.setValue(Intrinsics.areEqual(str, "None") ? "----" : (i3 / 10) + ' ' + str);
        updateDriveDataActivity(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void steeringWheelKeys() {
        /*
            r5 = this;
            int[] r0 = r5.getFrame()
            r1 = 3
            r0 = r0[r1]
            int[] r2 = r5.getFrame()
            r3 = 2
            r2 = r2[r3]
            if (r2 == 0) goto Laf
            r4 = 1
            if (r2 == r4) goto La6
            if (r2 == r3) goto L9c
            if (r2 == r1) goto L92
            r1 = 4
            if (r2 == r1) goto L88
            r1 = 129(0x81, float:1.81E-43)
            if (r2 == r1) goto La6
            r1 = 130(0x82, float:1.82E-43)
            if (r2 == r1) goto L9c
            switch(r2) {
                case 7: goto L80;
                case 8: goto L76;
                case 9: goto L6c;
                case 10: goto L62;
                default: goto L25;
            }
        L25:
            switch(r2) {
                case 19: goto L58;
                case 20: goto L4e;
                case 21: goto L43;
                case 22: goto L38;
                default: goto L28;
            }
        L28:
            switch(r2) {
                case 133: goto L58;
                case 134: goto L4e;
                case 135: goto L2d;
                case 136: goto L80;
                default: goto L2b;
            }
        L2b:
            goto Lb7
        L2d:
            android.content.Context r1 = r5.getContext()
            r2 = 134(0x86, float:1.88E-43)
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L38:
            android.content.Context r1 = r5.getContext()
            r2 = 49
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L43:
            android.content.Context r1 = r5.getContext()
            r2 = 50
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L4e:
            android.content.Context r1 = r5.getContext()
            r2 = 46
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L58:
            android.content.Context r1 = r5.getContext()
            r2 = 45
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L62:
            android.content.Context r1 = r5.getContext()
            r2 = 15
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L6c:
            android.content.Context r1 = r5.getContext()
            r2 = 14
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L76:
            android.content.Context r1 = r5.getContext()
            r2 = 187(0xbb, float:2.62E-43)
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L80:
            android.content.Context r1 = r5.getContext()
            r5.realKeyLongClick1(r1, r3, r0)
            goto Lb7
        L88:
            android.content.Context r1 = r5.getContext()
            r2 = 47
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L92:
            android.content.Context r1 = r5.getContext()
            r2 = 48
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        L9c:
            android.content.Context r1 = r5.getContext()
            r2 = 8
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        La6:
            android.content.Context r1 = r5.getContext()
            r2 = 7
            r5.realKeyLongClick1(r1, r2, r0)
            goto Lb7
        Laf:
            android.content.Context r1 = r5.getContext()
            r2 = 0
            r5.realKeyLongClick1(r1, r2, r0)
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._362.MsgMgr.steeringWheelKeys():void");
    }

    private final void rearRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(4, getFrame()[2], getFrame()[3], getFrame()[4], getFrame()[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrame()[6]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrame()[6]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrame()[6]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrame()[6]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 3);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S362_rearRadarInfo_1");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(boolBit7 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndex.get("S362_rearRadarInfo_2");
        Intrinsics.checkNotNull(itemListBean2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
        itemListBean3.setValue(itemListBean3.getValueSrnArray().get(boolBit6 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndex.get("S362_rearRadarInfo_3");
        Intrinsics.checkNotNull(itemListBean4);
        itemListBean4.setValue(Integer.valueOf(boolBit5 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = this.mSettingItemIndex.get("S362_rearRadarInfo_4");
        Intrinsics.checkNotNull(itemListBean5);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = itemListBean5;
        itemListBean6.setValue(itemListBean6.getValueSrnArray().get(boolBit4 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = this.mSettingItemIndex.get("S362_rearRadarInfo_5");
        Intrinsics.checkNotNull(itemListBean7);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = itemListBean7;
        itemListBean8.setProgress(intFromByteWithBit);
        itemListBean8.setValue(String.valueOf(itemListBean8.getProgress()));
        updateSettingActivity(null);
    }

    private final void frontRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(4, getFrame()[2], getFrame()[3], getFrame()[4], getFrame()[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    private final void vehicleSpeedInfo() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[3], getFrame()[2]) / 16);
        int msbLsbResult = DataHandleUtils.getMsbLsbResult(getFrame()[3], getFrame()[2]) / 16;
        int i = getFrame()[4];
        DriverDataPageUiSet.Page.Item item = this.mDrivingItemIndex.get("D362_vehicleSpeedInfo_1");
        Intrinsics.checkNotNull(item);
        item.setValue(msbLsbResult + " Km/H");
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndex.get("S362_vehicleSpeedInfo_1");
        Intrinsics.checkNotNull(itemListBean);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = itemListBean;
        itemListBean2.setProgress(i / 10);
        itemListBean2.setValue(itemListBean2.getProgress() == 0 ? "OFF" : itemListBean2.getProgress() + " s");
        updateDriveDataActivity(null);
        updateSettingActivity(null);
    }

    public final void returnClick() {
        startMainActivity(getContext());
    }

    private final Double toDoubleOrStringSelf(String str) {
        return StringsKt.toDoubleOrNull(str);
    }

    private final int culTrackAngle(short track, int max) {
        return DataHandleUtils.rangeNumber((int) (track / (max / 25)), -25, 25);
    }
}
