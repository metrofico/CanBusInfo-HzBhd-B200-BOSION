package com.hzbhd.canbus.car._361;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u001d\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\"\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0016J\b\u0010&\u001a\u00020#H\u0002J\b\u0010'\u001a\u00020#H\u0002JT\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020*2\u0006\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u001c\u00105\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u00106\u001a\u0004\u0018\u00010,H\u0016J\b\u00107\u001a\u00020#H\u0002J\b\u00108\u001a\u00020#H\u0002J\b\u00109\u001a\u00020#H\u0002J\b\u0010:\u001a\u00020#H\u0002J\b\u0010;\u001a\u00020#H\u0002J\b\u0010<\u001a\u00020#H\u0002J\u0010\u0010=\u001a\u00020#2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\b\u0010>\u001a\u00020#H\u0002JÄ\u0001\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020A2\u0006\u0010F\u001a\u00020A2\u0006\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020A2\u0006\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020A2\b\u0010K\u001a\u0004\u0018\u00010\u00112\b\u0010L\u001a\u0004\u0018\u00010\u00112\b\u0010M\u001a\u0004\u0018\u00010\u00112\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020A2\u0006\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020\u001d2\u0006\u0010S\u001a\u00020O2\b\u0010T\u001a\u0004\u0018\u00010\u00112\b\u0010U\u001a\u0004\u0018\u00010\u00112\b\u0010V\u001a\u0004\u0018\u00010\u00112\u0006\u0010W\u001a\u00020\u001dH\u0016J\b\u0010X\u001a\u00020#H\u0002J\b\u0010Y\u001a\u00020#H\u0002J\b\u0010Z\u001a\u00020#H\u0002J6\u0010[\u001a\u00020#2\u0006\u0010\\\u001a\u00020*2\b\u0010]\u001a\u0004\u0018\u00010\u00112\b\u0010^\u001a\u0004\u0018\u00010\u00112\b\u0010_\u001a\u0004\u0018\u00010\u00112\u0006\u0010`\u001a\u00020*H\u0016J\b\u0010a\u001a\u00020#H\u0002J\b\u0010b\u001a\u00020#H\u0002J\b\u0010c\u001a\u00020#H\u0002J\u0006\u0010d\u001a\u00020#J\"\u0010e\u001a\u00020#2\u0006\u0010f\u001a\u00020*2\u0006\u0010g\u001a\u00020*2\b\u0010h\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010i\u001a\u00020#H\u0002J\b\u0010j\u001a\u00020#H\u0002J\b\u0010k\u001a\u00020#H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR6\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R>\u0010\u0018\u001a&\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u0010j\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019`\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006l"}, d2 = {"Lcom/hzbhd/canbus/car/_361/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDriveItemIndexHashMap", "()Ljava/util/HashMap;", "setMDriveItemIndexHashMap", "(Ljava/util/HashMap;)V", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndexHashMap", "setMSettingItemIndexHashMap", "powerSwitcher", "", "getPowerSwitcher", "()Z", "setPowerSwitcher", "(Z)V", "afterServiceNormalSetting", "", "airConditionerInfo", "auxInInfoChange", "basicInfoOfJourney", "bodyState", "btPhoneStatusInfoChange", "callStatus", "", "phoneNumber", "", "isHfpConnected", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "centralControlEnableBit", "centralCtrlInfo", "drivingInfo", "essentialInfo", "frontRadarInfo", "fuelConsumptionInPast15Min", "initItemsIndexHashMap", "instantaneousFuelConsumptionInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "oilAndElectricHybridInfo", "powerAmplifierIndicationInfo", "powerAmplifierInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rangeFuelConsumptionInfo", "rearRadarInfo", "rearSeatAirConditioner", "returnClick", "sendTextInfo", "dataType", "date0", "data2_25", "steeringWheelAngle", "steeringWheelKeys", "tirePressureInfo", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frameData;
    private boolean powerSwitcher;
    private HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverDataPageUiSet.Page.Item> mDriveItemIndexHashMap = new HashMap<>();

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

    public final int[] getFrameData() {
        int[] iArr = this.frameData;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("frameData");
        return null;
    }

    public final void setFrameData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.frameData = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null || canbusInfo == null) {
            return;
        }
        setContext(context);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrameData(byteArrayToIntArray);
        int i = getFrameData()[1];
        if (i == 26) {
            centralControlEnableBit();
        }
        if (i == 58) {
            rearSeatAirConditioner();
            return;
        }
        if (i == 49) {
            powerAmplifierInfo();
            return;
        }
        if (i == 50) {
            powerAmplifierIndicationInfo();
            return;
        }
        if (i == 253) {
            bodyState();
            return;
        }
        if (i == 254) {
            drivingInfo();
            return;
        }
        switch (i) {
            case 29:
                frontRadarInfo();
                break;
            case 30:
                rearRadarInfo();
                break;
            case 31:
                oilAndElectricHybridInfo();
                break;
            case 32:
                steeringWheelKeys();
                break;
            case 33:
                basicInfoOfJourney();
                break;
            case 34:
                instantaneousFuelConsumptionInfo();
                break;
            case 35:
                rangeFuelConsumptionInfo();
                break;
            case 36:
                essentialInfo();
                break;
            case 37:
                tirePressureInfo();
                break;
            case 38:
                centralCtrlInfo();
                break;
            case 39:
                fuelConsumptionInPast15Min();
                break;
            case 40:
                airConditionerInfo();
                break;
            case 41:
                steeringWheelAngle();
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        initItemsIndexHashMap(context);
        new Thread() { // from class: com.hzbhd.canbus.car._361.MsgMgr$afterServiceNormalSetting$thread$1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws InterruptedException {
                while (true) {
                    Thread.sleep(1000L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, this.this$0.getPowerSwitcher() ? (byte) 1 : (byte) 0});
                }
            }
        }.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009b  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, int r14) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._361.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        int i = stoagePath != 9 ? 8 : 9;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf((byte) currentPlayingIndexLow));
        arrayList.add(Byte.valueOf(currentPlayingIndexHigh));
        arrayList.add(Byte.valueOf((byte) DataHandleUtils.getLsb(totalCount)));
        arrayList.add(Byte.valueOf((byte) DataHandleUtils.getMsb(totalCount)));
        arrayList.add(Byte.valueOf(currentMinute));
        arrayList.add(Byte.valueOf(currentSecond));
        MsgMgrKt.sendMediaInfo(i, 2, CollectionsKt.toByteArray(arrayList));
        sendTextInfo(135, 1, songTitle);
        sendTextInfo(135, 2, songArtist);
        sendTextInfo(135, 3, songAlbum);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        int i = 2;
        if (!isHfpConnected) {
            i = 0;
        } else if (!isCallingFlag && isHfpConnected) {
            i = 1;
        } else if (callStatus != 1) {
            i = callStatus == 2 ? 3 : callStatus == 4 ? 5 : 255;
        }
        byte[] bArr = new byte[6];
        for (int i2 = 0; i2 < 6; i2++) {
            bArr[i2] = 0;
        }
        bArr[0] = (byte) i;
        MsgMgrKt.sendMediaInfo(5, 3, bArr);
        String string = Arrays.toString(phoneNumber);
        Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
        sendTextInfo(137, 3, string);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        MsgMgrKt.sendMediaInfo$default(7, 0, null, 4, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void steeringWheelKeys() {
        /*
            r6 = this;
            int[] r0 = r6.getFrameData()
            r1 = 3
            r0 = r0[r1]
            int[] r2 = r6.getFrameData()
            r3 = 2
            r2 = r2[r3]
            r4 = 0
            if (r2 == 0) goto Lea
            r5 = 1
            if (r2 == r5) goto Le1
            if (r2 == r3) goto Ld7
            if (r2 == r1) goto Lcd
            r1 = 4
            if (r2 == r1) goto Lc3
            switch(r2) {
                case 7: goto Lbb;
                case 8: goto Lb1;
                case 9: goto La7;
                case 10: goto L9d;
                default: goto L1e;
            }
        L1e:
            switch(r2) {
                case 19: goto L93;
                case 20: goto L89;
                case 21: goto L7f;
                case 22: goto L74;
                default: goto L21;
            }
        L21:
            switch(r2) {
                case 32: goto L69;
                case 33: goto L5e;
                case 34: goto L53;
                case 35: goto L48;
                case 36: goto L3d;
                case 37: goto L34;
                default: goto L24;
            }
        L24:
            switch(r2) {
                case 129: goto Le1;
                case 130: goto Ld7;
                case 131: goto L93;
                case 132: goto L89;
                case 133: goto Lcd;
                case 134: goto Lc3;
                case 135: goto L29;
                case 136: goto Lbb;
                default: goto L27;
            }
        L27:
            goto Lf1
        L29:
            android.content.Context r1 = r6.getContext()
            r2 = 134(0x86, float:1.88E-43)
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L34:
            android.content.Context r1 = r6.getContext()
            r6.realKeyLongClick1(r1, r4, r0)
            goto Lf1
        L3d:
            android.content.Context r1 = r6.getContext()
            r2 = 141(0x8d, float:1.98E-43)
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L48:
            android.content.Context r1 = r6.getContext()
            r2 = 140(0x8c, float:1.96E-43)
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L53:
            android.content.Context r1 = r6.getContext()
            r2 = 139(0x8b, float:1.95E-43)
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L5e:
            android.content.Context r1 = r6.getContext()
            r2 = 76
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L69:
            android.content.Context r1 = r6.getContext()
            r2 = 77
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L74:
            android.content.Context r1 = r6.getContext()
            r2 = 49
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L7f:
            android.content.Context r1 = r6.getContext()
            r2 = 50
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L89:
            android.content.Context r1 = r6.getContext()
            r2 = 46
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L93:
            android.content.Context r1 = r6.getContext()
            r2 = 45
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        L9d:
            android.content.Context r1 = r6.getContext()
            r2 = 15
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        La7:
            android.content.Context r1 = r6.getContext()
            r2 = 14
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Lb1:
            android.content.Context r1 = r6.getContext()
            r2 = 187(0xbb, float:2.62E-43)
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Lbb:
            android.content.Context r1 = r6.getContext()
            r6.realKeyLongClick1(r1, r3, r0)
            goto Lf1
        Lc3:
            android.content.Context r1 = r6.getContext()
            r2 = 47
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Lcd:
            android.content.Context r1 = r6.getContext()
            r2 = 48
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Ld7:
            android.content.Context r1 = r6.getContext()
            r2 = 8
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Le1:
            android.content.Context r1 = r6.getContext()
            r2 = 7
            r6.realKeyLongClick1(r1, r2, r0)
            goto Lf1
        Lea:
            android.content.Context r1 = r6.getContext()
            r6.realKeyLongClick1(r1, r4, r0)
        Lf1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._361.MsgMgr.steeringWheelKeys():void");
    }

    private final void basicInfoOfJourney() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrameData()[2], getFrameData()[3]));
        int i = (getFrameData()[2] * 256) + getFrameData()[3];
        int i2 = (getFrameData()[4] * 256) + getFrameData()[5];
        int i3 = (getFrameData()[6] * 256) + getFrameData()[7];
        int i4 = getFrameData()[6];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("D361_d0t1");
        Intrinsics.checkNotNull(item);
        item.setValue((i / 10.0f) + " Km/Mile");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("D361_d2t3");
        Intrinsics.checkNotNull(item2);
        item2.setValue(i2 + " Min");
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("D361_d4t5");
        Intrinsics.checkNotNull(item3);
        item3.setValue(i3 + " Km/Mile");
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("D361_d6");
        Intrinsics.checkNotNull(item4);
        item4.setValue(i4 != 1 ? i4 != 2 ? "----" : "公制 KM" : "英制 MILE");
        updateDriveDataActivity(null);
    }

    private final void instantaneousFuelConsumptionInfo() {
        int i = getFrameData()[2];
        int i2 = (getFrameData()[3] * 256) + getFrameData()[4];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("D361_1_d0");
        Intrinsics.checkNotNull(item);
        item.setValue(i != 0 ? i != 1 ? i != 2 ? "----" : "L/100Km" : "Km/L" : "MPG");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("D361_1_d1t2");
        Intrinsics.checkNotNull(item2);
        item2.setValue(String.valueOf(i2 / 10.0f));
        updateDriveDataActivity(null);
    }

    private final void rangeFuelConsumptionInfo() {
        int i = getFrameData()[2];
        int i2 = (getFrameData()[3] * 256) + getFrameData()[4];
        int i3 = (getFrameData()[5] * 256) + getFrameData()[6];
        int i4 = (getFrameData()[7] * 256) + getFrameData()[8];
        int i5 = (getFrameData()[9] * 256) + getFrameData()[10];
        int i6 = (getFrameData()[11] * 256) + getFrameData()[12];
        int i7 = (getFrameData()[13] * 256) + getFrameData()[14];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("D361_2_d0");
        Intrinsics.checkNotNull(item);
        item.setValue(i != 0 ? i != 1 ? i != 2 ? "----" : "L/100Km" : "Km/L" : "MPG");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("D361_2_d1t2");
        Intrinsics.checkNotNull(item2);
        item2.setValue(String.valueOf(i2 / 10.0f));
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("D361_2_d3t4");
        Intrinsics.checkNotNull(item3);
        item3.setValue(String.valueOf(i3 / 10.0f));
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("D361_2_d5t6");
        Intrinsics.checkNotNull(item4);
        item4.setValue(String.valueOf(i4 / 10.0f));
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("D361_2_d7t8");
        Intrinsics.checkNotNull(item5);
        item5.setValue(String.valueOf(i5 / 10.0f));
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("D361_2_d9t10");
        Intrinsics.checkNotNull(item6);
        item6.setValue(String.valueOf(i6 / 10.0f));
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("D361_2_d11t12");
        Intrinsics.checkNotNull(item7);
        item7.setValue(String.valueOf(i7 / 10.0f));
        updateDriveDataActivity(null);
    }

    private final void essentialInfo() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(getFrameData()[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        GeneralDoorData.skyWindowOpenLevel = DataHandleUtils.getBoolBit1(getFrameData()[2]) ? 2 : 0;
        updateDoorView(getContext());
    }

    private final void tirePressureInfo() {
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 2);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "无效" : "2.5 KPA" : "PSI" : "0.1 BAR";
        GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(getFrameData()[2]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrameData()[3]), str}));
        arrayList.add(new TireUpdateEntity(1, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrameData()[4]), str}));
        arrayList.add(new TireUpdateEntity(2, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrameData()[5]), str}));
        arrayList.add(new TireUpdateEntity(3, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrameData()[6]), str}));
        if (GeneralTireData.isHaveSpareTire) {
            arrayList.add(new TireUpdateEntity(4, boolBit6 ? 1 : 0, new String[]{String.valueOf(getFrameData()[7]), str}));
        }
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private final void centralCtrlInfo() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 4, 3);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 2, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 2);
        boolean boolBit72 = DataHandleUtils.getBoolBit7(getFrameData()[3]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrameData()[3]);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 3);
        boolean boolBit73 = DataHandleUtils.getBoolBit7(getFrameData()[4]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(getFrameData()[4]);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 5, 1);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrameData()[4]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[4]);
        boolean boolBit74 = DataHandleUtils.getBoolBit7(getFrameData()[5]);
        boolean boolBit63 = DataHandleUtils.getBoolBit6(getFrameData()[5]);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[5], 0, 2);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 4, 2);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 0, 4);
        if (!UiMgrKt.isRemove2()) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("S361_d0b7");
            Intrinsics.checkNotNull(itemListBean);
            itemListBean.setValue(Integer.valueOf(boolBit7 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndexHashMap.get("S361_d1b0t2");
            Intrinsics.checkNotNull(itemListBean2);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
            itemListBean3.setProgress(intFromByteWithBit4);
            itemListBean3.setValue(String.valueOf(itemListBean3.getProgress()));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndexHashMap.get("S361_d0b0t1");
            Intrinsics.checkNotNull(itemListBean4);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = itemListBean4;
            itemListBean5.setValue(itemListBean5.getValueSrnArray().get(intFromByteWithBit3));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = this.mSettingItemIndexHashMap.get("S361_d2b7");
            Intrinsics.checkNotNull(itemListBean6);
            itemListBean6.setValue(Integer.valueOf(boolBit73 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = this.mSettingItemIndexHashMap.get("S361_d2b5");
            Intrinsics.checkNotNull(itemListBean7);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = itemListBean7;
            itemListBean8.setValue(itemListBean8.getValueSrnArray().get(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = this.mSettingItemIndexHashMap.get("S361_d0b6t4");
        Intrinsics.checkNotNull(itemListBean9);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = itemListBean9;
        itemListBean10.setProgress(intFromByteWithBit);
        itemListBean10.setValue(String.valueOf(itemListBean10.getProgress()));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = this.mSettingItemIndexHashMap.get("S361_d0b2t3");
        Intrinsics.checkNotNull(itemListBean11);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = itemListBean11;
        itemListBean12.setValue(itemListBean12.getValueSrnArray().get(intFromByteWithBit2));
        if (!UiMgrKt.isRemove1()) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = this.mSettingItemIndexHashMap.get("S361_d1b7");
            Intrinsics.checkNotNull(itemListBean13);
            itemListBean13.setValue(Integer.valueOf(boolBit72 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = this.mSettingItemIndexHashMap.get("S361_d1b6");
            Intrinsics.checkNotNull(itemListBean14);
            itemListBean14.setValue(Integer.valueOf(boolBit6 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = this.mSettingItemIndexHashMap.get("S361_d1b5");
            Intrinsics.checkNotNull(itemListBean15);
            itemListBean15.setValue(Integer.valueOf(boolBit5 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = this.mSettingItemIndexHashMap.get("S361_d1b4");
            Intrinsics.checkNotNull(itemListBean16);
            itemListBean16.setValue(Integer.valueOf(boolBit4 ? 1 : 0));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = this.mSettingItemIndexHashMap.get("S361_d2b6");
            Intrinsics.checkNotNull(itemListBean17);
            itemListBean17.setValue(Integer.valueOf(boolBit62 ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = this.mSettingItemIndexHashMap.get("S361_d2b4");
        Intrinsics.checkNotNull(itemListBean18);
        itemListBean18.setValue(Integer.valueOf(boolBit42 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = this.mSettingItemIndexHashMap.get("S361_d2b3");
        Intrinsics.checkNotNull(itemListBean19);
        itemListBean19.setValue(Integer.valueOf(boolBit3 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = this.mSettingItemIndexHashMap.get("S361_d3b7");
        Intrinsics.checkNotNull(itemListBean20);
        itemListBean20.setValue(Integer.valueOf(boolBit74 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = this.mSettingItemIndexHashMap.get("S361_d3b6");
        Intrinsics.checkNotNull(itemListBean21);
        itemListBean21.setValue(Integer.valueOf(boolBit63 ? 1 : 0));
        if (!UiMgrKt.isRemove0()) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean22 = this.mSettingItemIndexHashMap.get("S361_d30t1");
            Intrinsics.checkNotNull(itemListBean22);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean23 = itemListBean22;
            itemListBean23.setValue(itemListBean23.getValueSrnArray().get(intFromByteWithBit6));
        }
        if (!UiMgrKt.isRemove4()) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean24 = this.mSettingItemIndexHashMap.get("S361_d4b4t5");
            Intrinsics.checkNotNull(itemListBean24);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean25 = itemListBean24;
            itemListBean25.setValue(itemListBean25.getValueSrnArray().get(intFromByteWithBit7));
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean26 = this.mSettingItemIndexHashMap.get("S361_d4b0t3");
            Intrinsics.checkNotNull(itemListBean26);
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean27 = itemListBean26;
            itemListBean27.setValue(itemListBean27.getValueSrnArray().get(intFromByteWithBit8));
        }
        updateSettingActivity(null);
    }

    private final void fuelConsumptionInPast15Min() {
        int i = getFrameData()[2];
        int i2 = (getFrameData()[3] * 256) + getFrameData()[4];
        int i3 = (getFrameData()[5] * 256) + getFrameData()[6];
        int i4 = (getFrameData()[7] * 256) + getFrameData()[8];
        int i5 = (getFrameData()[9] * 256) + getFrameData()[10];
        int i6 = (getFrameData()[11] * 256) + getFrameData()[12];
        int i7 = (getFrameData()[13] * 256) + getFrameData()[14];
        int i8 = (getFrameData()[15] * 256) + getFrameData()[16];
        int i9 = (getFrameData()[17] * 256) + getFrameData()[18];
        int i10 = (getFrameData()[19] * 256) + getFrameData()[20];
        int i11 = (getFrameData()[21] * 256) + getFrameData()[22];
        int i12 = (getFrameData()[23] * 256) + getFrameData()[24];
        int i13 = (getFrameData()[25] * 256) + getFrameData()[26];
        int i14 = (getFrameData()[27] * 256) + getFrameData()[28];
        int i15 = (getFrameData()[29] * 256) + getFrameData()[30];
        int i16 = (getFrameData()[31] * 256) + getFrameData()[32];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("D361_3_d0");
        Intrinsics.checkNotNull(item);
        item.setValue(i != 0 ? i != 1 ? i != 2 ? "无效" : "L/100Km" : "Km/L" : "MPG");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("D361_3_d1t2");
        Intrinsics.checkNotNull(item2);
        item2.setValue(String.valueOf(i2 / 10.0f));
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("D361_3_d3t4");
        Intrinsics.checkNotNull(item3);
        item3.setValue(String.valueOf(i3 / 10.0f));
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("D361_3_d5t6");
        Intrinsics.checkNotNull(item4);
        item4.setValue(String.valueOf(i4 / 10.0f));
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("D361_3_d7t8");
        Intrinsics.checkNotNull(item5);
        item5.setValue(String.valueOf(i5 / 10.0f));
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("D361_3_d9t10");
        Intrinsics.checkNotNull(item6);
        item6.setValue(String.valueOf(i6 / 10.0f));
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("D361_3_d11t12");
        Intrinsics.checkNotNull(item7);
        item7.setValue(String.valueOf(i7 / 10.0f));
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("D361_3_d13t14");
        Intrinsics.checkNotNull(item8);
        item8.setValue(String.valueOf(i8 / 10.0f));
        DriverDataPageUiSet.Page.Item item9 = this.mDriveItemIndexHashMap.get("D361_3_d15t16");
        Intrinsics.checkNotNull(item9);
        item9.setValue(String.valueOf(i9 / 10.0f));
        DriverDataPageUiSet.Page.Item item10 = this.mDriveItemIndexHashMap.get("D361_3_d17t18");
        Intrinsics.checkNotNull(item10);
        item10.setValue(String.valueOf(i10 / 10.0f));
        DriverDataPageUiSet.Page.Item item11 = this.mDriveItemIndexHashMap.get("D361_3_d19t20");
        Intrinsics.checkNotNull(item11);
        item11.setValue(String.valueOf(i11 / 10.0f));
        DriverDataPageUiSet.Page.Item item12 = this.mDriveItemIndexHashMap.get("D361_3_d21t22");
        Intrinsics.checkNotNull(item12);
        item12.setValue(String.valueOf(i12 / 10.0f));
        DriverDataPageUiSet.Page.Item item13 = this.mDriveItemIndexHashMap.get("D361_3_d23t24");
        Intrinsics.checkNotNull(item13);
        item13.setValue(String.valueOf(i13 / 10.0f));
        DriverDataPageUiSet.Page.Item item14 = this.mDriveItemIndexHashMap.get("D361_3_d25t26");
        Intrinsics.checkNotNull(item14);
        item14.setValue(String.valueOf(i14 / 10.0f));
        DriverDataPageUiSet.Page.Item item15 = this.mDriveItemIndexHashMap.get("D361_3_d27t28");
        Intrinsics.checkNotNull(item15);
        item15.setValue(String.valueOf(i15 / 10.0f));
        DriverDataPageUiSet.Page.Item item16 = this.mDriveItemIndexHashMap.get("D361_3_d29t30");
        Intrinsics.checkNotNull(item16);
        item16.setValue(String.valueOf(i16 / 10.0f));
        updateDriveDataActivity(null);
    }

    private final void airConditionerInfo() {
        String str;
        String str2;
        String str3;
        String str4;
        GeneralAirData.power = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(getFrameData()[2]);
        GeneralAirData.small_wind_light = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrameData()[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(getFrameData()[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(getFrameData()[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(getFrameData()[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(getFrameData()[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 4);
        int i = getFrameData()[4];
        if (i == 0) {
            str = "--";
            str2 = "HI";
            str3 = "LO";
        } else if (i == 31) {
            str = "--";
            str3 = "HI";
            str2 = str3;
        } else {
            if (32 <= i && i < 36) {
                str2 = "HI";
                str = "--";
                str3 = (16 + ((getFrameData()[4] - 32) * 0.5d)) + " °C";
            } else {
                str = "--";
                str2 = "HI";
                str3 = 1 <= i && i < 30 ? (18 + ((getFrameData()[4] - 1) * 0.5d)) + " °C" : str;
            }
        }
        GeneralAirData.front_left_temperature = str3;
        int i2 = getFrameData()[5];
        if (i2 == 0) {
            str4 = "LO";
        } else if (i2 == 31) {
            str4 = str2;
        } else {
            if (32 <= i2 && i2 < 36) {
                str4 = (16 + ((getFrameData()[5] - 32) * 0.5d)) + " °C";
            } else {
                str4 = 1 <= i2 && i2 < 30 ? (18 + ((getFrameData()[5] - 1) * 0.5d)) + " °C" : str;
            }
        }
        GeneralAirData.front_right_temperature = str4;
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(getFrameData()[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 2, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 0, 2);
        updateAirActivity(getContext(), 1001);
    }

    private final void steeringWheelAngle() {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrameData()[3], (byte) getFrameData()[4], 0, 352, 12);
        updateParkUi(null, getContext());
    }

    private final void oilAndElectricHybridInfo() {
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 3);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(getFrameData()[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(getFrameData()[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(getFrameData()[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(getFrameData()[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(getFrameData()[3]);
        GeneralHybirdData.title = "动力回收值";
        GeneralHybirdData.valueList = CollectionsKt.listOf(getFrameData()[4] + " wh");
        updateHybirdActivity(null);
    }

    private final void centralControlEnableBit() {
        boolean boolBit0 = DataHandleUtils.getBoolBit0(getFrameData()[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(getFrameData()[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrameData()[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrameData()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        boolean boolBit02 = DataHandleUtils.getBoolBit0(getFrameData()[3]);
        boolean boolBit12 = DataHandleUtils.getBoolBit1(getFrameData()[3]);
        boolean boolBit22 = DataHandleUtils.getBoolBit2(getFrameData()[3]);
        boolean boolBit32 = DataHandleUtils.getBoolBit3(getFrameData()[3]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrameData()[3]);
        boolean boolBit52 = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(getFrameData()[3]);
        boolean boolBit72 = DataHandleUtils.getBoolBit7(getFrameData()[3]);
        boolean boolBit03 = DataHandleUtils.getBoolBit0(getFrameData()[4]);
        boolean boolBit13 = DataHandleUtils.getBoolBit1(getFrameData()[4]);
        String strByResId = CommUtil.getStrByResId(getContext(), "_341_setting_0_0_0");
        Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context, \"_341_setting_0_0_0\")");
        String strByResId2 = CommUtil.getStrByResId(getContext(), "_341_setting_0_0_1");
        Intrinsics.checkNotNullExpressionValue(strByResId2, "getStrByResId(context, \"_341_setting_0_0_1\")");
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("S361_1_d0b0");
        Intrinsics.checkNotNull(item);
        item.setValue(boolBit0 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("S361_1_d0b1");
        Intrinsics.checkNotNull(item2);
        item2.setValue(boolBit1 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("S361_1_d0b2");
        Intrinsics.checkNotNull(item3);
        item3.setValue(boolBit2 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("S361_1_d0b3");
        Intrinsics.checkNotNull(item4);
        item4.setValue(boolBit3 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("S361_1_d0b4");
        Intrinsics.checkNotNull(item5);
        item5.setValue(boolBit4 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("S361_1_d0b5");
        Intrinsics.checkNotNull(item6);
        item6.setValue(boolBit5 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("S361_1_d0b6");
        Intrinsics.checkNotNull(item7);
        item7.setValue(boolBit6 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("S361_1_d0b7");
        Intrinsics.checkNotNull(item8);
        item8.setValue(boolBit7 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item9 = this.mDriveItemIndexHashMap.get("S361_1_d1b0");
        Intrinsics.checkNotNull(item9);
        item9.setValue(boolBit02 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item10 = this.mDriveItemIndexHashMap.get("S361_1_d1b1");
        Intrinsics.checkNotNull(item10);
        item10.setValue(boolBit12 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item11 = this.mDriveItemIndexHashMap.get("S361_1_d1b2");
        Intrinsics.checkNotNull(item11);
        item11.setValue(boolBit22 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item12 = this.mDriveItemIndexHashMap.get("S361_1_d1b3");
        Intrinsics.checkNotNull(item12);
        item12.setValue(boolBit32 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item13 = this.mDriveItemIndexHashMap.get("S361_1_d1b4");
        Intrinsics.checkNotNull(item13);
        item13.setValue(boolBit42 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item14 = this.mDriveItemIndexHashMap.get("S361_1_d1b5");
        Intrinsics.checkNotNull(item14);
        item14.setValue(boolBit52 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item15 = this.mDriveItemIndexHashMap.get("S361_1_d1b6");
        Intrinsics.checkNotNull(item15);
        item15.setValue(boolBit62 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item16 = this.mDriveItemIndexHashMap.get("S361_1_d1b7");
        Intrinsics.checkNotNull(item16);
        item16.setValue(boolBit72 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item17 = this.mDriveItemIndexHashMap.get("S361_1_d2b0");
        Intrinsics.checkNotNull(item17);
        item17.setValue(boolBit03 ? strByResId2 : strByResId);
        DriverDataPageUiSet.Page.Item item18 = this.mDriveItemIndexHashMap.get("S361_1_d2b1");
        Intrinsics.checkNotNull(item18);
        item18.setValue(boolBit13 ? strByResId2 : strByResId);
        updateDriveDataActivity(null);
    }

    private final void powerAmplifierInfo() {
        GeneralAmplifierData.frontRear = com.hzbhd.canbus.car._350.MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 4, 4), 0, 14), 0, 2, null) - 7;
        GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 4), 0, 14) - 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 4), 2, 12) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(getFrameData()[5], 0, 63);
        updateAmplifierActivity(null);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 0, 4);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(getFrameData()[6]);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("S361_2_d2b0t3");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(intFromByteWithBit == 8 ? 1 : 0));
        if (!UiMgrKt.isRemove0()) {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndexHashMap.get("S361_2_d4b0");
            Intrinsics.checkNotNull(itemListBean2);
            itemListBean2.setValue(Integer.valueOf(boolBit0 ? 1 : 0));
        }
        updateSettingActivity(null);
    }

    public final boolean getPowerSwitcher() {
        return this.powerSwitcher;
    }

    public final void setPowerSwitcher(boolean z) {
        this.powerSwitcher = z;
    }

    private final void powerAmplifierIndicationInfo() {
        boolean boolBit0 = DataHandleUtils.getBoolBit0(getFrameData()[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(getFrameData()[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrameData()[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("_350_d_3_0");
        Intrinsics.checkNotNull(item);
        item.setValue(boolBit0 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("_350_d_3_1");
        Intrinsics.checkNotNull(item2);
        item2.setValue(boolBit1 ? CommUtil.getStrByResId(getContext(), "_350_u_2") : CommUtil.getStrByResId(getContext(), "_350_u_3"));
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("_350_d_3_2");
        Intrinsics.checkNotNull(item3);
        DriverDataPageUiSet.Page.Item item4 = item3;
        Context context = getContext();
        item4.setValue(boolBit2 ? CommUtil.getStrByResId(context, "_350_u_2") : CommUtil.getStrByResId(context, "_350_u_3"));
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("_350_d_3_3");
        Intrinsics.checkNotNull(item5);
        DriverDataPageUiSet.Page.Item item6 = item5;
        Context context2 = getContext();
        item6.setValue(boolBit3 ? CommUtil.getStrByResId(context2, "_350_u_2") : CommUtil.getStrByResId(context2, "_350_u_3"));
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("_350_d_3_4");
        Intrinsics.checkNotNull(item7);
        DriverDataPageUiSet.Page.Item item8 = item7;
        Context context3 = getContext();
        item8.setValue(boolBit6 ? CommUtil.getStrByResId(context3, "_350_u_1") : CommUtil.getStrByResId(context3, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item9 = this.mDriveItemIndexHashMap.get("_350_d_3_5");
        Intrinsics.checkNotNull(item9);
        item9.setValue(boolBit7 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        updateDriveDataActivity(null);
        this.powerSwitcher = boolBit6;
    }

    private final void bodyState() {
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D361_State_1");
        if (item != null) {
            item.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 4, 1)));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_2");
        if (item2 != null) {
            item2.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 3, 1)));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_3");
        if (item3 != null) {
            item3.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 2, 1)));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_4");
        if (item4 != null) {
            item4.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 1, 1)));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_5");
        if (item5 != null) {
            item5.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 1)));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_6");
        if (item6 != null) {
            item6.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 7, 1)));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_7");
        if (item7 != null) {
            item7.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 6, 1)));
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_8");
        if (item8 != null) {
            item8.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 5, 1)));
        }
        DriverDataPageUiSet.Page.Item item9 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_9");
        if (item9 != null) {
            item9.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 4, 1)));
        }
        DriverDataPageUiSet.Page.Item item10 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_10");
        if (item10 != null) {
            item10.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 3, 1)));
        }
        DriverDataPageUiSet.Page.Item item11 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_11");
        if (item11 != null) {
            item11.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 7, 1)));
        }
        DriverDataPageUiSet.Page.Item item12 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_12");
        if (item12 != null) {
            item12.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 6, 1)));
        }
        DriverDataPageUiSet.Page.Item item13 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_13");
        if (item13 != null) {
            item13.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 5, 1)));
        }
        DriverDataPageUiSet.Page.Item item14 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_14");
        if (item14 != null) {
            item14.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 4, 1)));
        }
        DriverDataPageUiSet.Page.Item item15 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_15");
        if (item15 != null) {
            item15.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 3, 1)));
        }
        DriverDataPageUiSet.Page.Item item16 = InitUtilsKt.getMDrivingItemIndex().get("D361_State_16");
        if (item16 != null) {
            item16.setValue(bodyState$getString(this, DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 2, 1)));
        }
        updateDriveDataActivity(null);
        updateDoorView(getContext());
    }

    private static final String bodyState$getString(MsgMgr msgMgr, int i) {
        String string;
        String str;
        Context context = msgMgr.getContext();
        if (i == 1) {
            string = context.getString(R.string._341_setting_0_0_0);
            str = "context.getString(R.string._341_setting_0_0_0)";
        } else {
            string = context.getString(R.string._341_setting_0_0_1);
            str = "context.getString(R.string._341_setting_0_0_1)";
        }
        Intrinsics.checkNotNullExpressionValue(string, str);
        return string;
    }

    private final void drivingInfo() {
        int i = getFrameData()[2];
        int msbLsbResult = DataHandleUtils.getMsbLsbResult(getFrameData()[3], getFrameData()[4]);
        int msbLsbResult2 = DataHandleUtils.getMsbLsbResult(getFrameData()[5], getFrameData()[6]);
        int anotherMsbLsbResult = MsgMgrKt.getAnotherMsbLsbResult(getFrameData()[5], getFrameData()[6], getFrameData()[7]);
        int msbLsbResult3 = DataHandleUtils.getMsbLsbResult(getFrameData()[10], getFrameData()[11]);
        byte b = (byte) getFrameData()[12];
        int anotherMsbLsbResult2 = MsgMgrKt.getAnotherMsbLsbResult(getFrameData()[13], getFrameData()[14], getFrameData()[15]);
        int anotherMsbLsbResult3 = MsgMgrKt.getAnotherMsbLsbResult(getFrameData()[14], getFrameData()[15], getFrameData()[16]);
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("d0");
        Intrinsics.checkNotNull(item);
        item.setValue((i / 10) + " V");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("d1t2");
        Intrinsics.checkNotNull(item2);
        item2.setValue((msbLsbResult / 10) + " L");
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("d3t4");
        Intrinsics.checkNotNull(item3);
        item3.setValue(msbLsbResult2 + " r/min");
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("d5t7");
        Intrinsics.checkNotNull(item4);
        item4.setValue(anotherMsbLsbResult + " km");
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("d8t9");
        Intrinsics.checkNotNull(item5);
        item5.setValue(msbLsbResult3 + " km/h");
        updateOutDoorTemp(getContext(), ((int) b) + " °C");
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("d11t13");
        Intrinsics.checkNotNull(item6);
        item6.setValue((anotherMsbLsbResult2 / 10) + " km");
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("d14t16");
        Intrinsics.checkNotNull(item7);
        item7.setValue((anotherMsbLsbResult3 / 10) + " km");
        updateDriveDataActivity(null);
    }

    private final void rearRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(4, getFrameData()[2], getFrameData()[3], getFrameData()[4], getFrameData()[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    private final void frontRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(4, getFrameData()[2], getFrameData()[3], getFrameData()[4], getFrameData()[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    private final void rearSeatAirConditioner() {
        String str;
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(getFrameData()[3]);
        GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(getFrameData()[3]);
        GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
        boolean z = false;
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 4);
        int i = getFrameData()[5];
        if (i == 0) {
            str = "LO";
        } else if (i == 31) {
            str = "HI";
        } else {
            if (32 <= i && i < 36) {
                str = (16 + ((getFrameData()[5] - 32) * 0.5d)) + " °C";
            } else {
                if (1 <= i && i < 30) {
                    z = true;
                }
                str = z ? (18 + ((getFrameData()[5] - 1) * 0.5d)) + " °C" : "--";
            }
        }
        GeneralAirData.rear_temperature = str;
        updateAirActivity(getContext(), 1002);
    }

    public final HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> getMSettingItemIndexHashMap() {
        return this.mSettingItemIndexHashMap;
    }

    public final void setMSettingItemIndexHashMap(HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.mSettingItemIndexHashMap = map;
    }

    public final HashMap<String, DriverDataPageUiSet.Page.Item> getMDriveItemIndexHashMap() {
        return this.mDriveItemIndexHashMap;
    }

    public final void setMDriveItemIndexHashMap(HashMap<String, DriverDataPageUiSet.Page.Item> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.mDriveItemIndexHashMap = map;
    }

    public final void initItemsIndexHashMap(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._361.UiMgr");
        UiMgr uiMgr = (UiMgr) canUiMgr;
        List<DriverDataPageUiSet.Page> list = uiMgr.getDriverDataPageUiSet(context).getList();
        Iterator<DriverDataPageUiSet.Page> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (DriverDataPageUiSet.Page.Item item : it.next().getItemList()) {
                int i4 = i3 + 1;
                HashMap<String, DriverDataPageUiSet.Page.Item> map = this.mDriveItemIndexHashMap;
                String titleSrn = item.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "item.titleSrn");
                DriverDataPageUiSet.Page.Item item2 = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(item2, "this[pageIndex].itemList[itemIndex]");
                map.put(titleSrn, item2);
                i3 = i4;
            }
            i = i2;
        }
        List<SettingPageUiSet.ListBean> list2 = uiMgr.getSettingUiSet(context).getList();
        Iterator<SettingPageUiSet.ListBean> it2 = list2.iterator();
        int i5 = 0;
        while (it2.hasNext()) {
            int i6 = i5 + 1;
            int i7 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it2.next().getItemList()) {
                int i8 = i7 + 1;
                HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map2 = this.mSettingItemIndexHashMap;
                String titleSrn2 = itemListBean.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn2, "itemListBean.titleSrn");
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = list2.get(i5).getItemList().get(i7);
                Intrinsics.checkNotNullExpressionValue(itemListBean2, "this[left].itemList[right]");
                map2.put(titleSrn2, itemListBean2);
                i7 = i8;
            }
            i5 = i6;
        }
    }

    public final void returnClick() {
        startMainActivity(getContext());
    }

    private final void sendTextInfo(int dataType, int date0, String data2_25) {
        byte[] bytes;
        if (data2_25 == null) {
            data2_25 = "Unknown";
        }
        if (data2_25.length() > 10) {
            bytes = StringsKt.substring(data2_25, new IntRange(0, 10)).getBytes(Charsets.UTF_16LE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        } else {
            bytes = data2_25.getBytes(Charsets.UTF_16LE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) dataType, (byte) date0, 3}, MsgMgrKt.restrict$default(bytes, 24, 0, 4, null)));
    }
}
