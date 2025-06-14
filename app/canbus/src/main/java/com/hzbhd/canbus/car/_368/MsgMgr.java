package com.hzbhd.canbus.car._368;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016Jp\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0004H\u0016J\b\u0010)\u001a\u00020\u0014H\u0002J\b\u0010*\u001a\u00020\u0014H\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\b¨\u0006+"}, d2 = {"Lcom/hzbhd/canbus/car/_368/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "color", "", "getColor", "()Ljava/lang/Integer;", "setColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastD2", "getLastD2", "setLastD2", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "set0x72Data", "set0x73Data", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private Integer color;
    public int[] frame;
    private Integer lastD2;

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

    public final Integer getColor() {
        return this.color;
    }

    public final void setColor(Integer num) {
        this.color = num;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = (byte) bYear2Dig;
        bArr[3] = (byte) bMonth;
        bArr[4] = (byte) bDay;
        bArr[5] = (byte) bHours;
        bArr[6] = (byte) bMins;
        bArr[7] = (byte) bSecond;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        Integer num = this.color;
        if (num != null) {
            bArr[11] = (byte) num.intValue();
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._368.UiMgr");
        UiMgr uiMgr = (UiMgr) canUiMgr;
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNull(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 114) {
            set0x72Data();
        } else {
            if (i != 115) {
                return;
            }
            set0x73Data();
        }
    }

    private final void set0x73Data() {
        updateOutDoorTemp(InitUtilsKt.getMContext(), ((getFrame()[8] * 0.5d) - 40) + " °C");
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[9]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[9]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[9]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[9]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[9]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[9]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    public final Integer getLastD2() {
        return this.lastD2;
    }

    public final void setLastD2(Integer num) {
        this.lastD2 = num;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x72Data() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._368.MsgMgr.set0x72Data():void");
    }
}
