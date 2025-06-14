package com.hzbhd.canbus.car._401;

import android.content.Context;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/hzbhd/canbus/car/_401/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x18Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0xF0Data", "bytes", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private final void set0x18Data() {
    }

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
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._401.UiMgr");
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
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
        if (i == 24) {
            set0x18Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i != 34) {
            if (i == 49) {
                set0x31Data();
                return;
            } else {
                if (i != 50) {
                    return;
                }
                set0x32Data();
                return;
            }
        }
        byte b = canbusInfo[3];
        byte lastKnobValue = MsgMgrKt.getLastKnobValue();
        int iAbs = Math.abs(b - lastKnobValue);
        int i2 = getFrame()[2];
        if (i2 != 1) {
            if (i2 == 2) {
                if (b > lastKnobValue) {
                    DataHandleUtils.knob(context, 46, iAbs);
                } else if (b < lastKnobValue) {
                    DataHandleUtils.knob(context, 45, iAbs);
                }
            }
        } else if (b > lastKnobValue) {
            DataHandleUtils.knob(context, 7, iAbs);
        } else if (b < lastKnobValue) {
            DataHandleUtils.knob(context, 8, iAbs);
        }
        MsgMgrKt.setLastKnobValue(canbusInfo[3]);
    }

    private final void set0x21Data() {
        realKeyLongClick1(InitUtilsKt.getMContext(), getFrame()[2] == 1 ? HotKeyConstant.K_SLEEP : 0, getFrame()[3]);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(4, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(4, set0x41Data$restrictValue(getFrame()[6]), 0, 0, set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x32Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
        updateDriveDataActivity(null);
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.ion = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit0(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.center_wheel = str;
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
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
            r3 = 8
            r4 = 5
            r5 = 0
            if (r1 == 0) goto L32
            r6 = 1
            if (r1 == r6) goto L30
            r6 = 2
            if (r1 == r6) goto L2e
            if (r1 == r2) goto L2b
            if (r1 == r4) goto L28
            switch(r1) {
                case 8: goto L25;
                case 9: goto L22;
                case 10: goto L1f;
                default: goto L1e;
            }
        L1e:
            goto L32
        L1f:
            r1 = 52
            goto L33
        L22:
            r1 = 20
            goto L33
        L25:
            r1 = 21
            goto L33
        L28:
            r1 = 14
            goto L33
        L2b:
            r1 = 201(0xc9, float:2.82E-43)
            goto L33
        L2e:
            r1 = r3
            goto L33
        L30:
            r1 = 7
            goto L33
        L32:
            r1 = r5
        L33:
            int[] r2 = r7.getFrame()
            r2 = r2[r4]
            r7.realKeyLongClick1(r0, r1, r2)
            int[] r0 = r7.getFrame()
            r1 = 9
            r0 = r0[r1]
            byte r0 = (byte) r0
            int[] r1 = r7.getFrame()
            r1 = r1[r3]
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
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._401.MsgMgr.set0x11Data():void");
    }
}
