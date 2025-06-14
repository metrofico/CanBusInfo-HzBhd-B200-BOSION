package com.hzbhd.canbus.car._240;

import android.content.Context;
import android.provider.Settings;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0014\u0010\u001e\u001a\u00020\u00122\n\u0010\u001f\u001a\u00020\n\"\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/hzbhd/canbus/car/_240/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mCanBusInfoByte", "", "getMCanBusInfoByte", "()[B", "setMCanBusInfoByte", "([B)V", "mCanBusInfoInt", "", "getMCanBusInfoInt", "()[I", "setMCanBusInfoInt", "([I)V", "mContext", "Landroid/content/Context;", "canbusInfoChange", "", "context", "canbusInfo", "getIndexBy2Bit", "", "bit", "", "getIndexBy6Bit", "resolveLeftAndRightTemp", "", "value", "resolveLeftAndRightTempNum", "sendMediaSource", "b0tx", "setAirData0x23", "setCornerData", "setDoorData0x28", "setPanoramaData", "setSettingData0x40", "setVersionInfo", "setWheelKeyInfo", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public byte[] mCanBusInfoByte;
    public int[] mCanBusInfoInt;
    private Context mContext;

    private final int getIndexBy2Bit(boolean bit) {
        return bit ? 1 : 0;
    }

    private final int getIndexBy6Bit(int bit) {
        int i = 0;
        for (int i2 = 0; i2 < 61; i2++) {
            if (bit == i2) {
                i = bit;
            }
        }
        return i;
    }

    public final int[] getMCanBusInfoInt() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
        return null;
    }

    public final void setMCanBusInfoInt(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mCanBusInfoInt = iArr;
    }

    public final byte[] getMCanBusInfoByte() {
        byte[] bArr = this.mCanBusInfoByte;
        if (bArr != null) {
            return bArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
        return null;
    }

    public final void setMCanBusInfoByte(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mCanBusInfoByte = bArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        setMCanBusInfoByte(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setMCanBusInfoInt(byteArrayToIntArray);
        this.mContext = context;
        int i = getMCanBusInfoInt()[1];
        if (i == 33) {
            setWheelKeyInfo();
            return;
        }
        if (i == 35) {
            if (isAirMsgRepeat(canbusInfo)) {
                return;
            }
            setAirData0x23();
            return;
        }
        if (i == 64) {
            if (getCurrentCanDifferentId() == 2 || getCurrentCanDifferentId() == 3) {
                setSettingData0x40();
                return;
            }
            return;
        }
        if (i == 80) {
            setPanoramaData();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i != 40) {
            if (i != 41) {
                return;
            }
            setCornerData();
        } else {
            if (isDoorMsgRepeat(canbusInfo)) {
                return;
            }
            setDoorData0x28();
        }
    }

    private final void setCornerData() {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(getMCanBusInfoByte()[3], getMCanBusInfoByte()[2], 7744, 2039, 16);
        updateParkUi(null, this.mContext);
    }

    private final void setPanoramaData() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 0, 4);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[3], 6, 1);
        ArrayList arrayList = new ArrayList(8);
        for (int i = 0; i < 8; i++) {
            arrayList.add(new PanoramicBtnUpdateEntity(i, false));
        }
        GeneralParkData.dataList = arrayList;
        List<PanoramicBtnUpdateEntity> list = GeneralParkData.dataList;
        if (intFromByteWithBit == 1) {
            list.set(0, new PanoramicBtnUpdateEntity(0, true));
        } else {
            list.set(1, new PanoramicBtnUpdateEntity(1, true));
        }
        if (intFromByteWithBit2 == 1) {
            list.set(2, new PanoramicBtnUpdateEntity(2, true));
        } else if (intFromByteWithBit2 == 2) {
            list.set(3, new PanoramicBtnUpdateEntity(3, true));
        } else if (intFromByteWithBit2 == 3) {
            list.set(4, new PanoramicBtnUpdateEntity(4, true));
        } else if (intFromByteWithBit2 == 4) {
            list.set(5, new PanoramicBtnUpdateEntity(5, true));
        }
        PanoramicBtnUpdateEntity panoramicBtnUpdateEntity = intFromByteWithBit3 == 1 ? list.set(6, new PanoramicBtnUpdateEntity(6, true)) : list.set(7, new PanoramicBtnUpdateEntity(7, true));
        updateParkUi(null, this.mContext);
    }

    private final void setWheelKeyInfo() {
        switch (getMCanBusInfoInt()[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, getMCanBusInfoInt()[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, getMCanBusInfoInt()[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, getMCanBusInfoInt()[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 21, getMCanBusInfoInt()[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 20, getMCanBusInfoInt()[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 3, getMCanBusInfoInt()[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_DARK_MODE, getMCanBusInfoInt()[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 14, getMCanBusInfoInt()[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 14, getMCanBusInfoInt()[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 15, getMCanBusInfoInt()[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, getMCanBusInfoInt()[3]);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 50, getMCanBusInfoInt()[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 1, getMCanBusInfoInt()[3]);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 52, getMCanBusInfoInt()[3]);
                break;
        }
    }

    private final void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(getMCanBusInfoByte()));
    }

    private final void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getMCanBusInfoInt()[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(getMCanBusInfoInt()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getMCanBusInfoInt()[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(getMCanBusInfoInt()[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(getMCanBusInfoInt()[2]);
        GeneralAirData.front_wind_level = getMCanBusInfoInt()[4];
        int i = getMCanBusInfoInt()[3];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        if (getCurrentCanDifferentId() == 2) {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(getMCanBusInfoInt()[6]);
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(getMCanBusInfoInt()[6]);
        } else {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTempNum(getMCanBusInfoInt()[5]);
            GeneralAirData.front_left_temperature = resolveLeftAndRightTempNum(getMCanBusInfoInt()[5]);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private final void setSettingData0x40() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2]));
        int indexBy6Bit = getIndexBy6Bit(DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 0, 7));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[3]));
        int i = getMCanBusInfoInt()[4];
        ArrayList arrayList = new ArrayList();
        if (getCurrentCanDifferentId() == 3) {
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
            arrayList.add(new SettingUpdateEntity(0, 1, new StringBuilder().append(indexBy6Bit).append('s').toString()));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(i)));
        } else {
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
            arrayList.add(new SettingUpdateEntity(0, 1, new StringBuilder().append(indexBy6Bit).append('s').toString()));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit2)));
        }
        Context context = this.mContext;
        Intrinsics.checkNotNull(context);
        Settings.System.putInt(context.getContentResolver(), "d0b7", indexBy2Bit);
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        Settings.System.putInt(context2.getContentResolver(), "d0b0t6", indexBy6Bit);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getMCanBusInfoInt()[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(getMCanBusInfoInt()[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(getMCanBusInfoInt()[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getMCanBusInfoInt()[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getMCanBusInfoInt()[2]);
        updateDoorView(this.mContext);
    }

    private final String resolveLeftAndRightTemp(int value) {
        if (value == 0) {
            return "LO";
        }
        if (value == 30) {
            return "HI";
        }
        if (value == 255) {
            Context context = this.mContext;
            Intrinsics.checkNotNull(context);
            String string = context.getString(R.string.no_display);
            Intrinsics.checkNotNullExpressionValue(string, "{\n                mConte…no_display)\n            }");
            return string;
        }
        boolean z = false;
        if (1 <= value && value < 30) {
            z = true;
        }
        return z ? ((value + 35) * 0.5f) + getTempUnitC(this.mContext) : "";
    }

    private final String resolveLeftAndRightTempNum(int value) {
        boolean z = false;
        if (1 <= value && value < 16) {
            z = true;
        }
        return z ? value + "" : "";
    }

    private final void sendMediaSource(int... b0tx) {
        ArrayList arrayList = new ArrayList(b0tx.length);
        for (int i : b0tx) {
            arrayList.add(Byte.valueOf((byte) i));
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -60}, CollectionsKt.toByteArray(arrayList)));
    }
}
