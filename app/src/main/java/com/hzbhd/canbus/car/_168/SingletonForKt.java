package com.hzbhd.canbus.car._168;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingletonForKt.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/hzbhd/canbus/car/_168/SingletonForKt;", "", "()V", "lastKnobValue", "", "init", "", "context", "Landroid/content/Context;", "uiMgr", "Lcom/hzbhd/canbus/car/_168/UiMgr;", "sendCarData", "d0", "", "set0x11Data", "frame", "", "mgr", "Lcom/hzbhd/canbus/car/_168/MsgMgr;", "set0x12Data", "set0x21Data", "set0x22Data", "canbusInfo", "", "set0x31Data", "set0x35Data", "set0x56Data", "set0x67Data", "setAirListener", "airPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "setSettingsListener", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SingletonForKt {
    public static final SingletonForKt INSTANCE = new SingletonForKt();
    private static byte lastKnobValue;

    private static final String set0x12Data$setOnOff(boolean z) {
        return z ? "On" : "Off";
    }

    private SingletonForKt() {
    }

    public final void sendCarData(int d0) {
        CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte) d0, 0});
    }

    public final void init(Context context, UiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uiMgr, "uiMgr");
        UiMgr uiMgr2 = uiMgr;
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr2, null, 4, null);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr2, null, 4, null);
    }

    public final void set0x11Data(int[] frame, MsgMgr mgr) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(mgr, "mgr");
        int i = frame[7];
        if (!(i >= 0 && i < 21)) {
            if (21 <= i && i < 41) {
                i = 2;
            } else {
                if (41 <= i && i < 61) {
                    i = 3;
                } else {
                    if (61 <= i && i < 81) {
                        i = 4;
                    } else {
                        if (((81 > i || i >= 101) ? 0 : 1) == 0) {
                            return;
                        } else {
                            i = 5;
                        }
                    }
                }
            }
        }
        mgr.setBacklightLevel(i);
    }

    public final void set0x22Data(byte[] canbusInfo, Context context) {
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        Intrinsics.checkNotNullParameter(context, "context");
        byte b = canbusInfo[3];
        byte b2 = lastKnobValue;
        int iAbs = Math.abs(b - b2);
        byte b3 = canbusInfo[2];
        if (b3 == 1) {
            if (b > b2) {
                DataHandleUtils.knob(context, 7, iAbs);
            } else if (b < b2) {
                DataHandleUtils.knob(context, 8, iAbs);
            }
        } else if (b3 == 2) {
            if (b > b2) {
                DataHandleUtils.knob(context, 46, iAbs);
            } else if (b < b2) {
                DataHandleUtils.knob(context, 45, iAbs);
            }
        }
        lastKnobValue = canbusInfo[3];
    }

    public final void setSettingsListener(final SettingPageUiSet mSettingPageUiSet) {
        Intrinsics.checkNotNullParameter(mSettingPageUiSet, "mSettingPageUiSet");
        mSettingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener
            public final void onSwitch(int i, int i2, int i3) {
                SingletonForKt.m275setSettingsListener$lambda1(mSettingPageUiSet, i, i2, i3);
            }
        });
    }

    /* renamed from: setSettingsListener$lambda-1$send, reason: not valid java name */
    private static final void m277setSettingsListener$lambda1$send(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 90, (byte) i2, (byte) i});
    }

    /* renamed from: setSettingsListener$lambda-1$anotherSend, reason: not valid java name */
    private static final void m276setSettingsListener$lambda1$anotherSend(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte) i2, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* renamed from: setSettingsListener$lambda-1, reason: not valid java name */
    public static final void m275setSettingsListener$lambda1(SettingPageUiSet mSettingPageUiSet, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(mSettingPageUiSet, "$mSettingPageUiSet");
        String titleSrn = mSettingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
        if (titleSrn != null) {
            switch (titleSrn.hashCode()) {
                case -1181433203:
                    if (titleSrn.equals("S168_x35_1")) {
                        m276setSettingsListener$lambda1$anotherSend(i3, 15);
                        break;
                    }
                    break;
                case -1181372660:
                    if (titleSrn.equals("S168_x56_1")) {
                        m277setSettingsListener$lambda1$send(i3, 8);
                        break;
                    }
                    break;
                case -1181372659:
                    if (titleSrn.equals("S168_x56_2")) {
                        m277setSettingsListener$lambda1$send(i3, 9);
                        break;
                    }
                    break;
            }
        }
    }

    public final void set0x56Data(int[] frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S168_x56_2");
        if (itemListBean == null) {
            return;
        }
        itemListBean.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(frame[3]))));
    }

    public final void set0x31Data(int[] frame) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(frame, "frame");
        GeneralAirData.power = DataHandleUtils.getBoolBit6(frame[2]);
        GeneralAirData.ac_auto = DataHandleUtils.getBoolBit4(frame[2]);
        GeneralAirData.sync = DataHandleUtils.getIntFromByteWithBit(frame[2], 2, 2) == 1;
        GeneralAirData.hybrid_ac = DataHandleUtils.getBoolBit0(frame[2]);
        if (DataHandleUtils.getBoolBit7(frame[3])) {
            GeneralAirData.right_set_seat_cold = true;
            GeneralAirData.right_set_seat_heat = false;
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 2, 2);
            GeneralAirData.front_right_seat_heat_level = 0;
        } else {
            GeneralAirData.right_set_seat_heat = true;
            GeneralAirData.right_set_seat_cold = false;
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 2, 2);
            GeneralAirData.front_right_seat_cold_level = 0;
        }
        if (DataHandleUtils.getBoolBit6(frame[3])) {
            GeneralAirData.left_set_seat_cold = true;
            GeneralAirData.left_set_seat_heat = false;
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 0, 2);
            GeneralAirData.front_left_seat_heat_level = 0;
        } else {
            GeneralAirData.left_set_seat_heat = true;
            GeneralAirData.left_set_seat_cold = false;
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 0, 2);
            GeneralAirData.front_left_seat_cold_level = 0;
        }
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(frame[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(frame[3]);
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(frame[3]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(frame[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(frame[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(frame[4]);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = frame[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        int i2 = frame[7];
        if (i2 >= 0 && i2 < 19) {
            GeneralAirData.front_wind_level = i2;
            GeneralAirData.front_auto_wind_speed = false;
        } else {
            GeneralAirData.front_auto_wind_speed = true;
        }
        int i3 = frame[8];
        String str3 = "High";
        if (i3 != 254) {
            str = i3 != 255 ? (frame[8] * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i4 = frame[9];
        if (i4 == 254) {
            str3 = "Low";
        } else if (i4 != 255) {
            str3 = (frame[9] * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str3;
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit5(frame[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(frame[3]);
        GeneralAirData.rear_sync = DataHandleUtils.getBoolBit0(frame[3]);
        int i5 = frame[10];
        if (i5 == 1) {
            GeneralAirData.rear_left_auto_wind = true;
            GeneralAirData.rear_right_auto_wind = true;
        } else if (i5 == 2) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i5 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i5 == 4) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        }
        GeneralAirData.rear_wind_level = frame[11];
        GeneralAirData.rear_auto_wind_speed = frame[11] == 32;
        int i6 = frame[12];
        if (i6 != 254) {
            str2 = i6 != 255 ? (i6 * 0.5d) + " °C" : "HIGH";
        } else {
            str2 = "LOW";
        }
        GeneralAirData.rear_left_temperature = str2;
    }

    public final void set0x35Data(int[] frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S168_x35_1");
        if (itemListBean == null) {
            return;
        }
        itemListBean.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(frame[6]))));
    }

    public final void set0x67Data(int[] frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S168_x67_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(frame[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S168_x67_2");
        if (itemListBean2 == null) {
            return;
        }
        itemListBean2.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(frame[3]))));
    }

    /* renamed from: setAirListener$send-4$default, reason: not valid java name */
    static /* synthetic */ void m274setAirListener$send4$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 255;
        }
        m273setAirListener$send4(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$send-4, reason: not valid java name */
    public static final void m273setAirListener$send4(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte) i, (byte) i2});
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
    private static final void setAirListener$select(String str) {
        switch (str.hashCode()) {
            case -1181429844:
                if (str.equals(AirBtnAction.AC_AUTO)) {
                    m274setAirListener$send4$default(4, 0, 2, null);
                    break;
                }
                break;
            case -712865050:
                if (str.equals(AirBtnAction.REAR_LOCK)) {
                    m274setAirListener$send4$default(24, 0, 2, null);
                    break;
                }
                break;
            case -712646570:
                if (str.equals(AirBtnAction.REAR_SYNC)) {
                    m274setAirListener$send4$default(25, 0, 2, null);
                    break;
                }
                break;
            case -620266838:
                if (str.equals(AirBtnAction.REAR_POWER)) {
                    m274setAirListener$send4$default(23, 0, 2, null);
                    break;
                }
                break;
            case -54617514:
                if (str.equals(AirBtnAction.AUTO_CYCLE)) {
                    m273setAirListener$send4(7, 2);
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    m273setAirListener$send4(1, !GeneralAirData.power ? 1 : 0);
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    m273setAirListener$send4(7, !GeneralAirData.in_out_cycle ? 1 : 0);
                    break;
                }
                break;
            case 1574763845:
                if (str.equals(AirBtnAction.HYBRID_AC)) {
                    m274setAirListener$send4$default(2, 0, 2, null);
                    break;
                }
                break;
        }
    }

    public final void setAirListener(AirPageUiSet airPageUiSet) {
        Intrinsics.checkNotNullParameter(airPageUiSet, "airPageUiSet");
        final FrontArea frontArea = airPageUiSet.getFrontArea();
        frontArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m269setAirListener$lambda15$lambda9$lambda5(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m270setAirListener$lambda15$lambda9$lambda6(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m271setAirListener$lambda15$lambda9$lambda7(frontArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m272setAirListener$lambda15$lambda9$lambda8(frontArea, i);
            }
        }});
        final RearArea rearArea = airPageUiSet.getRearArea();
        rearArea.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m265setAirListener$lambda15$lambda14$lambda10(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m266setAirListener$lambda15$lambda14$lambda11(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m267setAirListener$lambda15$lambda14$lambda12(rearArea, i);
            }
        }, new OnAirBtnClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener
            public final void onClickItem(int i) {
                SingletonForKt.m268setAirListener$lambda15$lambda14$lambda13(rearArea, i);
            }
        }});
        rearArea.setTempSetViewOnUpDownClickListeners(new SingletonForKt$setAirListener$1$2$5[]{new OnAirTemperatureUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$setAirListener$1$2$5
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickUp() {
                SingletonForKt.m273setAirListener$send4(22, 1);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener
            public void onClickDown() {
                SingletonForKt.m273setAirListener$send4(22, 2);
            }
        }, null, null});
        rearArea.setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener() { // from class: com.hzbhd.canbus.car._168.SingletonForKt$setAirListener$1$2$6
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickLeft() {
                SingletonForKt.m273setAirListener$send4(21, 2);
            }

            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener
            public void onClickRight() {
                SingletonForKt.m273setAirListener$send4(21, 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-9$lambda-5, reason: not valid java name */
    public static final void m269setAirListener$lambda15$lambda9$lambda5(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-9$lambda-6, reason: not valid java name */
    public static final void m270setAirListener$lambda15$lambda9$lambda6(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-9$lambda-7, reason: not valid java name */
    public static final void m271setAirListener$lambda15$lambda9$lambda7(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-9$lambda-8, reason: not valid java name */
    public static final void m272setAirListener$lambda15$lambda9$lambda8(FrontArea frontArea, int i) {
        String str = frontArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-14$lambda-10, reason: not valid java name */
    public static final void m265setAirListener$lambda15$lambda14$lambda10(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[0][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[0][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-14$lambda-11, reason: not valid java name */
    public static final void m266setAirListener$lambda15$lambda14$lambda11(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[1][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[1][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-14$lambda-12, reason: not valid java name */
    public static final void m267setAirListener$lambda15$lambda14$lambda12(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[2][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[2][it]");
        setAirListener$select(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setAirListener$lambda-15$lambda-14$lambda-13, reason: not valid java name */
    public static final void m268setAirListener$lambda15$lambda14$lambda13(RearArea rearArea, int i) {
        String str = rearArea.getLineBtnAction()[3][i];
        Intrinsics.checkNotNullExpressionValue(str, "this.lineBtnAction[3][it]");
        setAirListener$select(str);
    }

    public final int set0x21Data(int[] frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        switch (frame[2]) {
            case 1:
                return 1;
            case 2:
            case 27:
                return 21;
            case 3:
            case 30:
                return 20;
            case 4:
                return HotKeyConstant.K_CAN_CONFIG;
            case 5:
                return HotKeyConstant.K_SPEECH;
            case 6:
                return 50;
            case 7:
                return 62;
            case 8:
            case 31:
                return 141;
            case 9:
                return 3;
            case 10:
                return 33;
            case 11:
                return 34;
            case 12:
                return 35;
            case 13:
                return 36;
            case 14:
                return 37;
            case 15:
                return 38;
            case 16:
                return 95;
            case 17:
                return 31;
            case 18:
                return HotKeyConstant.K_DISP;
            case 19:
                return HotKeyConstant.K_TIME;
            case 20:
                return 5;
            case 21:
                return 75;
            case 22:
            case 42:
                return 49;
            case 23:
                return 45;
            case 24:
                return 46;
            case 25:
                return 47;
            case 26:
                return 48;
            case 28:
                return 66;
            case 29:
                return 65;
            case 32:
                return 128;
            case 33:
                return 130;
            case 34:
                return 150;
            case 35:
                return 58;
            case 36:
                return HotKeyConstant.K_ACTION_MEDIA;
            case 37:
                return 27;
            case 38:
                return 53;
            case 39:
                return 148;
            case 40:
                return HotKeyConstant.K_PHONE_ON_OFF;
            case 41:
                return 94;
            case 43:
                return 52;
            case 44:
                return 2;
            case 45:
                return 151;
            default:
                return 0;
        }
    }

    public final void set0x12Data(int[] frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("instaneous_fuel_consumption");
        if (item != null) {
            item.setValue(new StringBuilder().append(frame[5]).append('.').append(frame[6]).toString());
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("right_rear_window");
        if (item2 != null) {
            item2.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit3(frame[8])));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("left_rear_window");
        if (item3 != null) {
            item3.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit2(frame[8])));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("left_front_window");
        if (item4 != null) {
            item4.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit1(frame[8])));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("right_front_window");
        if (item5 != null) {
            item5.setValue(set0x12Data$setOnOff(DataHandleUtils.getBoolBit0(frame[8])));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("battery_voltage");
        if (item6 == null) {
            return;
        }
        item6.setValue(MsgMgrKt.accurateTo(frame[9] * 0.1d, 10) + " V");
    }
}
