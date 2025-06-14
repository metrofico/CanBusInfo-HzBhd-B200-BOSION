package com.hzbhd.canbus.car._405;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/hzbhd/canbus/car/_405/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x41Data", "set0x45Data", "set0x87Data", "set0xF0Data", "bytes", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private static final int set0x41Data$restrictValue(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._405.UiMgr");
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), 19});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNull(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo!!)");
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
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 69) {
            set0x45Data();
        } else if (i == 135) {
            set0x87Data();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Data(canbusInfo);
        }
    }

    private final void set0x45Data() {
        Integer num;
        PanoramicBtnUpdateEntity panoramicBtnUpdateEntity;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 2);
        if (intFromByteWithBit == 0) {
            num = 0;
        } else if (intFromByteWithBit == 1) {
            num = 1;
        } else {
            num = intFromByteWithBit != 2 ? null : 2;
        }
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            if (num != null && i == num.intValue()) {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i, true);
            } else {
                panoramicBtnUpdateEntity = new PanoramicBtnUpdateEntity(i, false);
            }
            arrayList.add(panoramicBtnUpdateEntity);
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x87Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S405_x87_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 3, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[5]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[6]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(getFrame()[7]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(itemListBean9.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 2, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(itemListBean10.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 0, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S405_x87_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(getFrame()[8]))));
        }
        updateSettingActivity(null);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(getFrame()[6]), set0x41Data$restrictValue(getFrame()[7]), set0x41Data$restrictValue(getFrame()[8]), set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x21Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[2];
        realKeyLongClick1(mContext, i != 1 ? i != 2 ? i != 3 ? i != 9 ? i != 51 ? i != 69 ? i != 70 ? 0 : 8 : 7 : HotKeyConstant.K_ACTION_RADIO : 3 : 20 : 21 : 1, getFrame()[3]);
    }

    private final void set0x31Data() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 1) == 0;
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 2) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        GeneralAirData.front_left_temperature = "Level " + getFrame()[8];
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[4])) == 1;
        updateDoorView(InitUtilsKt.getMContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x11Data() {
        /*
            r7 = this;
            android.content.Context r0 = com.hzbhd.canbus.util.InitUtilsKt.getMContext()
            int[] r1 = r7.getFrame()
            r2 = 4
            r1 = r1[r2]
            r2 = 8
            r3 = 3
            r4 = 2
            r5 = 0
            if (r1 == 0) goto L29
            r6 = 1
            if (r1 == r6) goto L27
            if (r1 == r4) goto L25
            if (r1 == r3) goto L2a
            switch(r1) {
                case 12: goto L23;
                case 13: goto L20;
                case 14: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L29
        L1d:
            r3 = 46
            goto L2a
        L20:
            r3 = 45
            goto L2a
        L23:
            r3 = r4
            goto L2a
        L25:
            r3 = r2
            goto L2a
        L27:
            r3 = 7
            goto L2a
        L29:
            r3 = r5
        L2a:
            int[] r1 = r7.getFrame()
            r4 = 5
            r1 = r1[r4]
            r7.realKeyLongClick1(r0, r3, r1)
            int[] r0 = r7.getFrame()
            r1 = 9
            r0 = r0[r1]
            byte r0 = (byte) r0
            int[] r1 = r7.getFrame()
            r1 = r1[r2]
            byte r1 = (byte) r1
            r2 = 540(0x21c, float:7.57E-43)
            r3 = 16
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r0, r1, r5, r2, r3)
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
            r0 = 0
            android.content.Context r1 = com.hzbhd.canbus.util.InitUtilsKt.getMContext()
            r7.updateParkUi(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._405.MsgMgr.set0x11Data():void");
    }
}
