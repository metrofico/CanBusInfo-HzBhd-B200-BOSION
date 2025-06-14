package com.hzbhd.canbus.car._364;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010\u0011\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/hzbhd/canbus/car/_364/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x31Data", "set0x35Data", "set0x41Data", "set0x90Data", "set0xB1Data", "set0xF0Data", "bytes", "updateSettingActivity", "bundle", "Landroid/os/Bundle;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;

    private final void set0x90Data() {
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        if (context == null) {
            return;
        }
        setContext(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._364.UiMgr");
        SelectCanTypeUtil.enableApp(context, Constant.OnStarActivity);
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNull(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
            return;
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 53) {
            set0x35Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 144) {
            set0x90Data();
        } else if (i == 177) {
            set0xB1Data();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Data(canbusInfo);
        }
    }

    private final void set0xB1Data() {
        if (getFrame()[2] == 0) {
            startMainActivity(InitUtilsKt.getMContext());
            return;
        }
        GeneralOnStartData.mOnStarStatus = getFrame()[2] + 1;
        int i = getFrame()[3];
        GeneralOnStartData.mOnStarCallType = i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "ROADSIDE ASSISTANCE" : "EMERGENCY" : "COLLISION" : "NORMAL";
        GeneralOnStartData.mOnStarCallMuteSt = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 1);
        updateOnStarActivity(1005);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x41Data() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        RadarInfoUtil.setRearRadarDistanceData(getFrame()[2], getFrame()[3], getFrame()[4], getFrame()[5]);
        RadarInfoUtil.setFrontRadarDistanceData(getFrame()[6], getFrame()[7], getFrame()[8], getFrame()[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, getContext());
    }

    private final void set0x35Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 4, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 7, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 5, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(itemListBean9.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 2, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(itemListBean11.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 0, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 7, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("S364_AIR_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(itemListBean13.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 5, 2)));
        }
        updateSettingActivity(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void set0x31Data() {
        String str;
        String str2;
        String str3;
        int i;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        boolean z = false;
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2) == 1;
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]) ? 1 : 0;
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit3(getFrame()[3]) ? 2 : GeneralAirData.in_out_auto_cycle;
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(getFrame()[4]);
        MsgMgrKt.windDirectionInit();
        int i2 = getFrame()[6];
        if (i2 == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i2 == 2) {
            GeneralAirData.front_defog = true;
        } else if (i2 == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i2 == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i2 == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i2) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        int i3 = getFrame()[7];
        if ((i3 >= 0 && i3 < 19) == true) {
            GeneralAirData.front_wind_level = i3;
        } else if (i3 == 19) {
            GeneralAirData.front_auto_wind_speed = true;
        }
        int i4 = getFrame()[8];
        String str4 = "High";
        if (i4 == 254) {
            str = "Low";
            str2 = str;
        } else if (i4 != 255) {
            str = "Low";
            str2 = (i4 * 0.5d) + " °C";
        } else {
            str = "Low";
            str2 = "High";
        }
        GeneralAirData.front_left_temperature = str2;
        int i5 = getFrame()[9];
        if (i5 != 254) {
            str3 = i5 != 255 ? (i5 * 0.5d) + " °C" : "High";
        } else {
            str3 = str;
        }
        GeneralAirData.front_right_temperature = str3;
        int i6 = getFrame()[10];
        if (i6 == 1) {
            GeneralAirData.rear_left_auto_wind = true;
            GeneralAirData.rear_right_auto_wind = true;
        } else if (i6 == 2) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i6 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i6 == 4) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        }
        int i7 = getFrame()[11];
        if (i7 >= 0) {
            i = 5;
            if (i7 < 5) {
                z = true;
            }
        } else {
            i = 5;
        }
        if (z) {
            GeneralAirData.rear_wind_level = i7;
        } else if (i7 == i) {
            GeneralAirData.rear_auto_wind_speed = true;
        }
        int i8 = getFrame()[12];
        if (i8 == 254) {
            str4 = str;
        } else if (i8 != 255) {
            str4 = (i8 * 0.5d) + " °C";
        }
        GeneralAirData.rear_left_temperature = str4;
        updateOutDoorTemp(getContext(), ((getFrame()[13] * 0.5d) - 40) + " °C");
        updateAirActivity(getContext(), 1004);
        updateAirActivity(getContext(), 1003);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        updateDoorView(getContext());
    }

    private final void set0x11Data() {
        int i = 3;
        updateSpeedInfo(getFrame()[3]);
        Context context = getContext();
        switch (getFrame()[4]) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 7;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                break;
            case 4:
            case 7:
            default:
                return;
            case 5:
                i = 14;
                break;
            case 6:
                i = 15;
                break;
            case 8:
            case 12:
                i = 46;
                break;
            case 9:
            case 11:
                i = 45;
                break;
            case 10:
                i = 2;
                break;
            case 13:
                i = HotKeyConstant.K_SLEEP;
                break;
            case 14:
                i = 60;
                break;
        }
        realKeyLongClick1(context, i, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, getContext());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void updateSettingActivity(Bundle bundle) {
        super.updateSettingActivity(bundle);
    }
}
