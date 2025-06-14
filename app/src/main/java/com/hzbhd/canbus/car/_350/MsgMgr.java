package com.hzbhd.canbus.car._350;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u001e\n\u0002\u0010\t\n\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u0006\u0010*\u001a\u00020)J\b\u0010+\u001a\u00020)H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J\b\u0010/\u001a\u00020)H\u0016J\u0006\u00100\u001a\u00020)J\u001c\u00101\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020)H\u0002Jv\u00105\u001a\u00020)2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020-2\b\u0010A\u001a\u0004\u0018\u00010\u00172\b\u0010B\u001a\u0004\u0018\u00010\u00172\b\u0010C\u001a\u0004\u0018\u00010\u0017H\u0016J\u0006\u0010D\u001a\u00020)J\u0010\u0010E\u001a\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0006\u0010F\u001a\u00020)JÄ\u0001\u0010G\u001a\u00020)2\u0006\u0010H\u001a\u0002072\u0006\u0010I\u001a\u0002072\u0006\u0010J\u001a\u00020-2\u0006\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u0002072\u0006\u0010M\u001a\u0002072\u0006\u0010N\u001a\u0002072\u0006\u0010O\u001a\u0002072\u0006\u0010P\u001a\u0002072\u0006\u0010Q\u001a\u0002072\b\u0010R\u001a\u0004\u0018\u00010\u00172\b\u0010S\u001a\u0004\u0018\u00010\u00172\b\u0010T\u001a\u0004\u0018\u00010\u00172\u0006\u0010U\u001a\u00020V2\u0006\u00106\u001a\u0002072\u0006\u0010W\u001a\u00020-2\u0006\u0010?\u001a\u00020\u00142\u0006\u0010X\u001a\u00020V2\b\u0010Y\u001a\u0004\u0018\u00010\u00172\b\u0010Z\u001a\u0004\u0018\u00010\u00172\b\u0010[\u001a\u0004\u0018\u00010\u00172\u0006\u0010\\\u001a\u00020\u0014H\u0016J\b\u0010]\u001a\u00020)H\u0002J\b\u0010^\u001a\u00020)H\u0002J6\u0010_\u001a\u00020)2\u0006\u0010`\u001a\u00020-2\b\u0010a\u001a\u0004\u0018\u00010\u00172\b\u0010b\u001a\u0004\u0018\u00010\u00172\b\u0010c\u001a\u0004\u0018\u00010\u00172\u0006\u0010d\u001a\u00020-H\u0016J\u0006\u0010e\u001a\u00020)J\u0006\u0010f\u001a\u00020)J\u0006\u0010g\u001a\u00020)J\b\u0010h\u001a\u00020)H\u0002J\u0006\u0010i\u001a\u00020)J\b\u0010j\u001a\u00020)H\u0002J\b\u0010k\u001a\u00020)H\u0002J\u0006\u0010l\u001a\u00020)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR>\u0010\u001e\u001a&\u0012\u0004\u0012\u00020\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f0\u0016j\u0012\u0012\u0004\u0012\u00020\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001f`\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\u001dR\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006m"}, d2 = {"Lcom/hzbhd/canbus/car/_350/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "lastListForFrontAirConditioner", "", "Ljava/io/Serializable;", "lastListForRearAirConditioner", "lastValue", "", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDriveItemIndexHashMap", "()Ljava/util/HashMap;", "setMDriveItemIndexHashMap", "(Ljava/util/HashMap;)V", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "getMSettingItemIndexHashMap", "setMSettingItemIndexHashMap", "mUiMgr", "Lcom/hzbhd/canbus/car/_350/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_350/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_350/UiMgr;)V", "afterServiceNormalSetting", "", "airConditioner", "amplifierInfo", "assignRadarProgress", "", "value", "auxInInfoChange", "basicInfo", "canbusInfoChange", "canbusInfo", "", "controlModeInfo", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", LcdInfoShare.MediaInfo.album, LcdInfoShare.MediaInfo.artist, "frontRadar", "initItemsIndexHashMap", "mediaInfo", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "past15minFuelConsumption", "pastFuelConsumption", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rearRadar", "rockerInfo", "steerAngle", "steerBtn", "systemInfo", "vehicleInfo", "vehicleSettings", "versionInfo", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frameData;
    private boolean lastValue;
    public UiMgr mUiMgr;
    private List<Serializable> lastListForFrontAirConditioner = new ArrayList();
    private List<Serializable> lastListForRearAirConditioner = new ArrayList();
    private HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverDataPageUiSet.Page.Item> mDriveItemIndexHashMap = new HashMap<>();

    private final int assignRadarProgress(int value) {
        if (value == 1) {
            return 1;
        }
        if (value == 2) {
            return 4;
        }
        if (value != 3) {
            return value != 4 ? 0 : 10;
        }
        return 7;
    }

    public final void mediaInfo() {
    }

    public final void rockerInfo() {
    }

    public final void versionInfo() {
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
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (canbusInfo == null || context == null) {
            return;
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrameData(byteArrayToIntArray);
        setContext(context);
        int i = getFrameData()[1];
        if (i == 29) {
            frontRadar();
            return;
        }
        if (i == 30) {
            rearRadar();
            return;
        }
        if (i == 32) {
            steerBtn();
            return;
        }
        if (i == 42) {
            vehicleInfo();
            return;
        }
        if (i == 53) {
            steerAngle();
            return;
        }
        if (i == 96) {
            controlModeInfo();
            return;
        }
        if (i == 98) {
            mediaInfo();
            return;
        }
        if (i == 35) {
            pastFuelConsumption();
            return;
        }
        if (i == 36) {
            basicInfo();
            return;
        }
        if (i == 49) {
            amplifierInfo();
            return;
        }
        if (i != 50) {
            switch (i) {
                case 38:
                    vehicleSettings();
                    break;
                case 39:
                    past15minFuelConsumption();
                    break;
                case 40:
                    airConditioner();
                    break;
            }
            return;
        }
        systemInfo();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0043 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9) throws java.lang.NumberFormatException {
        /*
            r4 = this;
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.List r8 = (java.util.List) r8
            if (r6 == 0) goto Lb7
            int r9 = r6.hashCode()
            r0 = 2
            r1 = 1
            r2 = 16
            switch(r9) {
                case 64901: goto L36;
                case 64902: goto L2c;
                case 69706: goto L22;
                case 69707: goto L16;
                default: goto L14;
            }
        L14:
            goto Lb7
        L16:
            java.lang.String r9 = "FM2"
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L20
            goto Lb7
        L20:
            r6 = r0
            goto L41
        L22:
            java.lang.String r9 = "FM1"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto Lb7
            r6 = r1
            goto L41
        L2c:
            java.lang.String r9 = "AM2"
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L40
            goto Lb7
        L36:
            java.lang.String r9 = "AM1"
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L40
            goto Lb7
        L40:
            r6 = r2
        L41:
            if (r6 == r1) goto L70
            if (r6 == r0) goto L70
            if (r6 == r2) goto L48
            goto L9c
        L48:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            int r7 = java.lang.Integer.parseInt(r7)
            int r9 = com.hzbhd.canbus.util.DataHandleUtils.getMsb(r7)
            int r7 = com.hzbhd.canbus.util.DataHandleUtils.getLsb(r7)
            byte r6 = (byte) r6
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
            byte r6 = (byte) r9
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
            byte r6 = (byte) r7
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
            goto L9c
        L70:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            double r0 = java.lang.Double.parseDouble(r7)
            r7 = 100
            double r2 = (double) r7
            double r0 = r0 * r2
            int r7 = (int) r0
            int r9 = com.hzbhd.canbus.util.DataHandleUtils.getMsb(r7)
            int r7 = com.hzbhd.canbus.util.DataHandleUtils.getLsb(r7)
            byte r6 = (byte) r6
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
            byte r6 = (byte) r9
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
            byte r6 = (byte) r7
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r8.add(r6)
        L9c:
            byte r5 = (byte) r5
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r8.add(r5)
            r5 = 3
            byte[] r5 = new byte[r5]
            r5 = {x00ca: FILL_ARRAY_DATA , data: [22, -64, 1} // fill-array
            java.util.Collection r8 = (java.util.Collection) r8
            byte[] r6 = kotlin.collections.CollectionsKt.toByteArray(r8)
            byte[] r5 = kotlin.collections.ArraysKt.plus(r5, r6)
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._350.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (stoagePath != 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) 4, (byte) playIndex, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._350.UiMgr");
        setMUiMgr((UiMgr) canUiMgr);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initItemsIndexHashMap(context);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isShowLittleLight = true;
    }

    private final void steerBtn() {
        int i = getFrameData()[2];
        if (i == 15) {
            realKeyLongClick1(getContext(), 128, getFrameData()[3]);
            return;
        }
        if (i == 129) {
            realKeyLongClick1(getContext(), 7, getFrameData()[3]);
            return;
        }
        if (i == 130) {
            realKeyLongClick1(getContext(), 8, getFrameData()[3]);
            return;
        }
        if (i == 135) {
            realKeyLongClick1(getContext(), HotKeyConstant.K_SLEEP, getFrameData()[3]);
            return;
        }
        if (i != 136) {
            switch (i) {
                case 1:
                    realKeyLongClick1(getContext(), 7, getFrameData()[3]);
                    break;
                case 2:
                    realKeyLongClick1(getContext(), 8, getFrameData()[3]);
                    break;
                case 3:
                    realKeyLongClick1(getContext(), 48, getFrameData()[3]);
                    break;
                case 4:
                    realKeyLongClick1(getContext(), 47, getFrameData()[3]);
                    break;
                case 5:
                    realKeyLongClick1(getContext(), 2, getFrameData()[3]);
                    break;
                case 6:
                    realKeyLongClick1(getContext(), 3, getFrameData()[3]);
                    break;
                case 7:
                    realKeyLongClick1(getContext(), HotKeyConstant.K_SPEECH, getFrameData()[3]);
                    break;
                case 8:
                    realKeyLongClick1(getContext(), 14, getFrameData()[3]);
                    break;
                case 9:
                    realKeyLongClick1(getContext(), 15, getFrameData()[3]);
                    break;
                case 10:
                    realKeyLongClick1(getContext(), 52, getFrameData()[3]);
                    break;
                default:
                    switch (i) {
                        case 17:
                            realKeyLongClick1(getContext(), 53, getFrameData()[3]);
                            break;
                        case 18:
                            realKeyLongClick1(getContext(), 151, getFrameData()[3]);
                            break;
                        case 19:
                            realKeyLongClick1(getContext(), 45, getFrameData()[3]);
                            break;
                        case 20:
                            realKeyLongClick1(getContext(), 46, getFrameData()[3]);
                            break;
                        case 21:
                            realKeyLongClick1(getContext(), 50, getFrameData()[3]);
                            break;
                        case 22:
                            realKeyLongClick1(getContext(), 49, getFrameData()[3]);
                            break;
                        case 23:
                            DataHandleUtils.knob(getContext(), 45, getFrameData()[3]);
                            break;
                        case 24:
                            DataHandleUtils.knob(getContext(), 46, getFrameData()[3]);
                            break;
                    }
            }
            return;
        }
        realKeyLongClick1(getContext(), 2, getFrameData()[3]);
    }

    public final void steerAngle() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) getFrameData()[3], (byte) getFrameData()[2], 0, 380, 12);
        updateParkUi(null, getContext());
    }

    public final void basicInfo() {
        int i = getFrameData()[2];
        int i2 = getFrameData()[3];
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(i);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(i);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(i);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(i);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(i);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(i);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(i2);
        GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(i2);
        GeneralDoorData.skyWindowOpenLevel = DataHandleUtils.getBoolBit3(i2) ? 2 : 0;
        updateDoorView(getContext());
    }

    private final void pastFuelConsumption() {
        String strByResId;
        int i = getFrameData()[2];
        int i2 = (getFrameData()[3] * 256) + getFrameData()[4];
        int i3 = (getFrameData()[5] * 256) + getFrameData()[6];
        int i4 = (getFrameData()[7] * 256) + getFrameData()[8];
        int i5 = (getFrameData()[9] * 256) + getFrameData()[10];
        int i6 = (getFrameData()[11] * 256) + getFrameData()[12];
        int i7 = (getFrameData()[13] * 256) + getFrameData()[14];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("_350_d_0_0");
        Intrinsics.checkNotNull(item);
        DriverDataPageUiSet.Page.Item item2 = item;
        if (i == 0) {
            strByResId = "MPG";
        } else if (i != 1) {
            strByResId = i != 2 ? CommUtil.getStrByResId(getContext(), "set_default") : "L/100KM";
        } else {
            strByResId = "KM/L";
        }
        item2.setValue(strByResId);
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("_350_d_0_1");
        Intrinsics.checkNotNull(item3);
        item3.setValue(String.valueOf(i2 / 10.0f));
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("_350_d_0_2");
        Intrinsics.checkNotNull(item4);
        item4.setValue(String.valueOf(i3 / 10.0f));
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("_350_d_0_3");
        Intrinsics.checkNotNull(item5);
        item5.setValue(String.valueOf(i4 / 10.0f));
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("_350_d_0_4");
        Intrinsics.checkNotNull(item6);
        item6.setValue(String.valueOf(i5 / 10.0f));
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("_350_d_0_5");
        Intrinsics.checkNotNull(item7);
        item7.setValue(String.valueOf(i6 / 10.0f));
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("_350_d_0_6");
        Intrinsics.checkNotNull(item8);
        item8.setValue(String.valueOf(i7 / 10.0f));
        updateDriveDataActivity(null);
    }

    private final void past15minFuelConsumption() {
        String strByResId;
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
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("_350_d_1_0");
        Intrinsics.checkNotNull(item);
        DriverDataPageUiSet.Page.Item item2 = item;
        if (i == 0) {
            strByResId = "MPG";
        } else if (i != 1) {
            strByResId = i != 2 ? CommUtil.getStrByResId(getContext(), "set_default") : "L/100KM";
        } else {
            strByResId = "KM/L";
        }
        item2.setValue(strByResId);
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("_350_d_1_1");
        Intrinsics.checkNotNull(item3);
        item3.setValue(String.valueOf(i2 / 10.0f));
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("_350_d_1_2");
        Intrinsics.checkNotNull(item4);
        item4.setValue(String.valueOf(i3 / 10.0f));
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("_350_d_1_3");
        Intrinsics.checkNotNull(item5);
        item5.setValue(String.valueOf(i4 / 10.0f));
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("_350_d_1_4");
        Intrinsics.checkNotNull(item6);
        item6.setValue(String.valueOf(i5 / 10.0f));
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("_350_d_1_5");
        Intrinsics.checkNotNull(item7);
        item7.setValue(String.valueOf(i6 / 10.0f));
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("_350_d_1_6");
        Intrinsics.checkNotNull(item8);
        item8.setValue(String.valueOf(i7 / 10.0f));
        DriverDataPageUiSet.Page.Item item9 = this.mDriveItemIndexHashMap.get("_350_d_1_7");
        Intrinsics.checkNotNull(item9);
        item9.setValue(String.valueOf(i8 / 10.0f));
        DriverDataPageUiSet.Page.Item item10 = this.mDriveItemIndexHashMap.get("_350_d_1_8");
        Intrinsics.checkNotNull(item10);
        item10.setValue(String.valueOf(i9 / 10.0f));
        DriverDataPageUiSet.Page.Item item11 = this.mDriveItemIndexHashMap.get("_350_d_1_9");
        Intrinsics.checkNotNull(item11);
        item11.setValue(String.valueOf(i10 / 10.0f));
        DriverDataPageUiSet.Page.Item item12 = this.mDriveItemIndexHashMap.get("_350_d_1_10");
        Intrinsics.checkNotNull(item12);
        item12.setValue(String.valueOf(i11 / 10.0f));
        DriverDataPageUiSet.Page.Item item13 = this.mDriveItemIndexHashMap.get("_350_d_1_11");
        Intrinsics.checkNotNull(item13);
        item13.setValue(String.valueOf(i12 / 10.0f));
        DriverDataPageUiSet.Page.Item item14 = this.mDriveItemIndexHashMap.get("_350_d_1_12");
        Intrinsics.checkNotNull(item14);
        item14.setValue(String.valueOf(i13 / 10.0f));
        DriverDataPageUiSet.Page.Item item15 = this.mDriveItemIndexHashMap.get("_350_d_1_13");
        Intrinsics.checkNotNull(item15);
        item15.setValue(String.valueOf(i14 / 10.0f));
        DriverDataPageUiSet.Page.Item item16 = this.mDriveItemIndexHashMap.get("_350_d_1_14");
        Intrinsics.checkNotNull(item16);
        item16.setValue(String.valueOf(i15 / 10.0f));
        DriverDataPageUiSet.Page.Item item17 = this.mDriveItemIndexHashMap.get("_350_d_1_15");
        Intrinsics.checkNotNull(item17);
        item17.setValue(String.valueOf(i16 / 10.0f));
        updateDriveDataActivity(null);
    }

    private final void vehicleInfo() {
        String strByResId;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrameData()[5], getFrameData()[6]));
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrameData()[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrameData()[2]);
        int i = getFrameData()[3];
        int i2 = getFrameData()[4];
        int i3 = getFrameData()[5];
        int i4 = getFrameData()[6];
        int i5 = getFrameData()[7];
        int i6 = getFrameData()[8];
        int i7 = getFrameData()[9];
        int i8 = getFrameData()[10];
        int i9 = getFrameData()[11];
        int i10 = getFrameData()[12];
        int i11 = getFrameData()[13];
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("_350_d_2_0_0");
        Intrinsics.checkNotNull(item);
        item.setValue(boolBit7 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("_350_d_2_0_1");
        Intrinsics.checkNotNull(item2);
        DriverDataPageUiSet.Page.Item item3 = item2;
        Context context = getContext();
        item3.setValue(boolBit6 ? CommUtil.getStrByResId(context, "_350_u_1") : CommUtil.getStrByResId(context, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("_350_d_2_0_2");
        Intrinsics.checkNotNull(item4);
        DriverDataPageUiSet.Page.Item item5 = item4;
        Context context2 = getContext();
        item5.setValue(boolBit5 ? CommUtil.getStrByResId(context2, "_350_u_1") : CommUtil.getStrByResId(context2, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("_350_d_2_0_3");
        Intrinsics.checkNotNull(item6);
        DriverDataPageUiSet.Page.Item item7 = item6;
        Context context3 = getContext();
        item7.setValue(boolBit4 ? CommUtil.getStrByResId(context3, "_350_u_1") : CommUtil.getStrByResId(context3, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("_350_d_2_0_4");
        Intrinsics.checkNotNull(item8);
        DriverDataPageUiSet.Page.Item item9 = item8;
        Context context4 = getContext();
        item9.setValue(boolBit3 ? CommUtil.getStrByResId(context4, "_350_u_1") : CommUtil.getStrByResId(context4, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item10 = this.mDriveItemIndexHashMap.get("_350_d_2_0_5");
        Intrinsics.checkNotNull(item10);
        DriverDataPageUiSet.Page.Item item11 = item10;
        Context context5 = getContext();
        item11.setValue(boolBit2 ? CommUtil.getStrByResId(context5, "_350_u_1") : CommUtil.getStrByResId(context5, "_350_u_0"));
        DriverDataPageUiSet.Page.Item item12 = this.mDriveItemIndexHashMap.get("_350_d_2_1");
        Intrinsics.checkNotNull(item12);
        item12.setValue(DataHandleUtils.getMsbLsbResult(i, i2) + " rpm");
        DriverDataPageUiSet.Page.Item item13 = this.mDriveItemIndexHashMap.get("_350_d_2_2");
        Intrinsics.checkNotNull(item13);
        item13.setValue(DataHandleUtils.getMsbLsbResult(i3, i4) + " km/h");
        DriverDataPageUiSet.Page.Item item14 = this.mDriveItemIndexHashMap.get("_350_d_2_3");
        Intrinsics.checkNotNull(item14);
        item14.setValue(String.valueOf((i5 << 16) + (i6 << 8) + i7));
        DriverDataPageUiSet.Page.Item item15 = this.mDriveItemIndexHashMap.get("_350_d_2_4");
        Intrinsics.checkNotNull(item15);
        item15.setValue(String.valueOf((i8 << 8) + i9));
        updateOutDoorTemp(getContext(), i10 == 0 ? "--" : ((int) ((byte) i10)) + " °C");
        DriverDataPageUiSet.Page.Item item16 = this.mDriveItemIndexHashMap.get("_350_d_2_6");
        Intrinsics.checkNotNull(item16);
        DriverDataPageUiSet.Page.Item item17 = item16;
        if (i11 == 1) {
            strByResId = CommUtil.getStrByResId(getContext(), "_350_d_2_6_0");
        } else if (i11 == 2) {
            strByResId = CommUtil.getStrByResId(getContext(), "_350_d_2_6_1");
        } else {
            strByResId = CommUtil.getStrByResId(getContext(), "set_default");
        }
        item17.setValue(strByResId);
        updateDriveDataActivity(null);
    }

    public final void airConditioner() {
        String str;
        String strByResId;
        String strByResId2;
        List list;
        String strByResId3;
        String strByResId4;
        MsgMgr msgMgr;
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[2]);
        boolean z = !DataHandleUtils.getBoolBit5(getFrameData()[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrameData()[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrameData()[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(getFrameData()[2]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(getFrameData()[2]);
        boolean boolBit72 = DataHandleUtils.getBoolBit7(getFrameData()[3]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(getFrameData()[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrameData()[3]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrameData()[3]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 4);
        int i = getFrameData()[4];
        int i2 = getFrameData()[5];
        boolean boolBit73 = DataHandleUtils.getBoolBit7(getFrameData()[6]);
        DataHandleUtils.getBoolBit6(getFrameData()[6]);
        boolean boolBit52 = DataHandleUtils.getBoolBit5(getFrameData()[6]);
        boolean boolBit43 = DataHandleUtils.getBoolBit4(getFrameData()[6]);
        DataHandleUtils.getBoolBit3(getFrameData()[6]);
        boolean boolBit22 = DataHandleUtils.getBoolBit2(getFrameData()[6]);
        boolean boolBit02 = DataHandleUtils.getBoolBit0(getFrameData()[6]);
        int i3 = getFrameData()[7];
        boolean boolBit74 = DataHandleUtils.getBoolBit7(getFrameData()[8]);
        boolean boolBit63 = DataHandleUtils.getBoolBit6(getFrameData()[8]);
        boolean boolBit53 = DataHandleUtils.getBoolBit5(getFrameData()[8]);
        boolean boolBit44 = DataHandleUtils.getBoolBit4(getFrameData()[8]);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[8], 0, 4);
        int i4 = getFrameData()[9];
        boolean boolBit75 = DataHandleUtils.getBoolBit7(getFrameData()[10]);
        boolean boolBit64 = DataHandleUtils.getBoolBit6(getFrameData()[10]);
        boolean boolBit54 = DataHandleUtils.getBoolBit5(getFrameData()[10]);
        DataHandleUtils.getBoolBit4(getFrameData()[10]);
        DataHandleUtils.getBoolBit3(getFrameData()[10]);
        DataHandleUtils.getBoolBit2(getFrameData()[10]);
        List listListOf = CollectionsKt.listOf((Object[]) new Serializable[]{Boolean.valueOf(boolBit7), Boolean.valueOf(boolBit6), Boolean.valueOf(z), Boolean.valueOf(boolBit4), Boolean.valueOf(boolBit3), Boolean.valueOf(boolBit2), Boolean.valueOf(boolBit1), Boolean.valueOf(boolBit0), Boolean.valueOf(boolBit72), Boolean.valueOf(boolBit62), Boolean.valueOf(boolBit5), Integer.valueOf(intFromByteWithBit), Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(boolBit73), Boolean.valueOf(boolBit52), Boolean.valueOf(boolBit43), Boolean.valueOf(boolBit75), Boolean.valueOf(boolBit64), Boolean.valueOf(boolBit54)});
        List listListOf2 = CollectionsKt.listOf((Object[]) new Serializable[]{Boolean.valueOf(boolBit22), Integer.valueOf(i3), Boolean.valueOf(boolBit74), Boolean.valueOf(boolBit63), Boolean.valueOf(boolBit63), Boolean.valueOf(boolBit53), Boolean.valueOf(boolBit44), Integer.valueOf(intFromByteWithBit2), Integer.valueOf(i4)});
        GeneralAirData.power = boolBit7;
        GeneralAirData.ac = boolBit6;
        GeneralAirData.in_out_cycle = z;
        GeneralAirData.negative_ion = boolBit4;
        GeneralAirData.auto = boolBit3;
        GeneralAirData.dual = boolBit2;
        GeneralAirData.front_window_heat = boolBit0;
        GeneralAirData.front_left_blow_window = boolBit72;
        GeneralAirData.front_left_blow_head = boolBit62;
        GeneralAirData.front_left_blow_foot = boolBit5;
        GeneralAirData.front_wind_level = intFromByteWithBit;
        if (i == 0) {
            str = "HI";
            strByResId = "LO";
        } else if (i == 31) {
            strByResId = "HI";
            str = strByResId;
        } else {
            if (1 <= i && i < 30) {
                str = "HI";
                double d = 18;
                strByResId = boolBit02 ? MsgMgrKt.transToF(d + ((i - 1) * 0.5d)) : MsgMgrKt.transToC(d + ((i - 1) * 0.5d));
            } else {
                str = "HI";
                if (33 <= i && i < 39) {
                    double d2 = 15;
                    strByResId = boolBit02 ? MsgMgrKt.transToF(d2 + ((i - 33) * 0.5d)) : MsgMgrKt.transToC(d2 + ((i - 33) * 0.5d));
                } else {
                    strByResId = CommUtil.getStrByResId(getContext(), "set_default");
                }
            }
        }
        GeneralAirData.front_left_temperature = strByResId;
        if (i2 == 0) {
            strByResId2 = "LO";
        } else if (i2 == 31) {
            strByResId2 = str;
        } else {
            if (1 <= i2 && i2 < 30) {
                double d3 = 18;
                int i5 = i2 - 1;
                strByResId2 = boolBit02 ? MsgMgrKt.transToF(d3 + (i5 * 0.5d)) : MsgMgrKt.transToC(d3 + (i5 * 0.5d));
            } else {
                if (33 <= i2 && i2 < 39) {
                    double d4 = 15;
                    strByResId2 = boolBit02 ? MsgMgrKt.transToF(d4 + ((i2 - 33) * 0.5d)) : MsgMgrKt.transToC(d4 + ((i2 - 33) * 0.5d));
                } else {
                    strByResId2 = CommUtil.getStrByResId(getContext(), "set_default");
                }
            }
        }
        GeneralAirData.front_right_temperature = strByResId2;
        GeneralAirData.pollrn_removal = boolBit73;
        GeneralAirData.aqs = boolBit52;
        GeneralAirData.clean_air = boolBit43;
        GeneralAirData.rear_lock = boolBit22;
        if (i3 == 0) {
            strByResId3 = "LO";
            list = listListOf2;
        } else if (i3 == 31) {
            list = listListOf2;
            strByResId3 = str;
        } else {
            if (!(1 <= i3 && i3 < 30)) {
                list = listListOf2;
                if (33 <= i3 && i3 < 39) {
                    strByResId3 = boolBit02 ? MsgMgrKt.transToF(15 + ((i3 - 33) * 0.5d)) : MsgMgrKt.transToC(15 + ((i3 - 33) * 0.5d));
                } else {
                    strByResId3 = CommUtil.getStrByResId(getContext(), "set_default");
                }
            } else if (boolBit02) {
                list = listListOf2;
                strByResId3 = MsgMgrKt.transToF(18 + ((i3 - 1) * 0.5d));
            } else {
                list = listListOf2;
                strByResId3 = MsgMgrKt.transToC(18 + ((i3 - 1) * 0.5d));
            }
        }
        GeneralAirData.rear_left_temperature = strByResId3;
        GeneralAirData.rear_power = boolBit74;
        GeneralAirData.rear_left_blow_head = boolBit63;
        GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
        GeneralAirData.rear_left_blow_foot = boolBit53;
        GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
        GeneralAirData.rear_auto = boolBit44;
        GeneralAirData.rear_wind_level = intFromByteWithBit2;
        if (i4 == 0) {
            strByResId4 = "LO";
        } else if (i4 == 31) {
            strByResId4 = str;
        } else {
            if (1 <= i4 && i4 < 30) {
                double d5 = 18 + ((i4 - 1) * 0.5d);
                strByResId4 = boolBit02 ? MsgMgrKt.transToF(d5) : MsgMgrKt.transToC(d5);
            } else {
                if (33 <= i4 && i4 < 39) {
                    double d6 = 15 + ((i4 - 33) * 0.5d);
                    strByResId4 = boolBit02 ? MsgMgrKt.transToF(d6) : MsgMgrKt.transToC(d6);
                } else {
                    strByResId4 = CommUtil.getStrByResId(getContext(), "set_default");
                }
            }
        }
        GeneralAirData.rear_right_temperature = strByResId4;
        GeneralAirData.front_right_blow_window = boolBit75;
        GeneralAirData.front_right_blow_head = boolBit64;
        GeneralAirData.front_right_blow_foot = boolBit54;
        if (SystemUtil.isForeground(getContext(), AirActivity.class.getName())) {
            msgMgr = this;
            if (!MsgMgrKt.isEqual(listListOf, msgMgr.lastListForFrontAirConditioner)) {
                airConditioner$updateWithNotPop(msgMgr, 1001);
            } else if (!MsgMgrKt.isEqual(list, msgMgr.lastListForRearAirConditioner)) {
                airConditioner$updateWithNotPop(msgMgr, 1002);
            }
        } else {
            msgMgr = this;
            if (!MsgMgrKt.isEqual(listListOf, msgMgr.lastListForFrontAirConditioner) && boolBit42) {
                airConditioner$updateWithPop(msgMgr, 1001);
            } else if (!MsgMgrKt.isEqual(list, msgMgr.lastListForRearAirConditioner) && boolBit42) {
                airConditioner$updateWithPop(msgMgr, 1002);
            }
        }
        if (boolBit02 != msgMgr.lastValue) {
            if (AirActivity.page == 0) {
                airConditioner$updateWithNotPop(msgMgr, 1001);
            } else if (AirActivity.page == 1) {
                airConditioner$updateWithNotPop(msgMgr, 1002);
            }
            msgMgr.lastValue = boolBit02;
        }
        msgMgr.lastListForFrontAirConditioner.clear();
        msgMgr.lastListForFrontAirConditioner.addAll(listListOf);
        msgMgr.lastListForRearAirConditioner.clear();
        msgMgr.lastListForRearAirConditioner.addAll(list);
    }

    private static final void airConditioner$updateWithNotPop(MsgMgr msgMgr, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_air_what", i);
        msgMgr.updateActivity(bundle);
    }

    private static final void airConditioner$updateWithPop(MsgMgr msgMgr, int i) {
        msgMgr.updateAirActivity(msgMgr.getContext(), i);
    }

    public final void frontRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, assignRadarProgress(getFrameData()[2]), assignRadarProgress(getFrameData()[3]), assignRadarProgress(getFrameData()[4]), assignRadarProgress(getFrameData()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    public final void rearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(10, assignRadarProgress(getFrameData()[2]), assignRadarProgress(getFrameData()[3]), assignRadarProgress(getFrameData()[4]), assignRadarProgress(getFrameData()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("_350_s_5_0");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrameData()[6]) ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndexHashMap.get("_350_s_5_1");
        Intrinsics.checkNotNull(itemListBean2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
        itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getBoolBit6(getFrameData()[6]) ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndexHashMap.get("_350_s_5_2");
        Intrinsics.checkNotNull(itemListBean4);
        itemListBean4.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(getFrameData()[6]) ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = this.mSettingItemIndexHashMap.get("_350_s_5_3");
        Intrinsics.checkNotNull(itemListBean5);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = itemListBean5;
        itemListBean6.setProgress(DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 0, 3));
        itemListBean6.setValue(String.valueOf(itemListBean6.getProgress()));
        updateSettingActivity(null);
    }

    private final void vehicleSettings() {
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
        boolean boolBit52 = DataHandleUtils.getBoolBit5(getFrameData()[4]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrameData()[4]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[4]);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 0, 3);
        boolean boolBit74 = DataHandleUtils.getBoolBit7(getFrameData()[5]);
        boolean boolBit63 = DataHandleUtils.getBoolBit6(getFrameData()[5]);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[5], 2, 2);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[5], 0, 2);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 5, 2);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 2, 3);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(getFrameData()[6], 0, 2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("_350_s_0_0");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(boolBit7 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndexHashMap.get("_350_s_0_1");
        Intrinsics.checkNotNull(itemListBean2);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = itemListBean2;
        itemListBean3.setProgress(intFromByteWithBit);
        itemListBean3.setValue(String.valueOf(intFromByteWithBit));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemIndexHashMap.get("_350_s_0_2");
        Intrinsics.checkNotNull(itemListBean4);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = itemListBean4;
        itemListBean5.setValue(itemListBean5.getValueSrnArray().get(intFromByteWithBit2));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = this.mSettingItemIndexHashMap.get("_350_s_0_3");
        Intrinsics.checkNotNull(itemListBean6);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = itemListBean6;
        itemListBean7.setValue(itemListBean7.getValueSrnArray().get(intFromByteWithBit3));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = this.mSettingItemIndexHashMap.get("_350_s_1_0");
        Intrinsics.checkNotNull(itemListBean8);
        itemListBean8.setValue(Integer.valueOf(boolBit72 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = this.mSettingItemIndexHashMap.get("_350_s_1_1");
        Intrinsics.checkNotNull(itemListBean9);
        itemListBean9.setValue(Integer.valueOf(boolBit6 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = this.mSettingItemIndexHashMap.get("_350_s_1_2");
        Intrinsics.checkNotNull(itemListBean10);
        itemListBean10.setValue(Integer.valueOf(boolBit5 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = this.mSettingItemIndexHashMap.get("_350_s_1_3");
        Intrinsics.checkNotNull(itemListBean11);
        itemListBean11.setValue(Integer.valueOf(boolBit4 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = this.mSettingItemIndexHashMap.get("_350_s_1_4");
        Intrinsics.checkNotNull(itemListBean12);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = itemListBean12;
        itemListBean13.setProgress(intFromByteWithBit4);
        itemListBean13.setValue(String.valueOf(itemListBean13.getProgress()));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = this.mSettingItemIndexHashMap.get("_350_s_2_0");
        Intrinsics.checkNotNull(itemListBean14);
        itemListBean14.setValue(Integer.valueOf(boolBit73 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = this.mSettingItemIndexHashMap.get("_350_s_2_1");
        Intrinsics.checkNotNull(itemListBean15);
        itemListBean15.setValue(Integer.valueOf(boolBit62 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = this.mSettingItemIndexHashMap.get("_350_s_2_2");
        Intrinsics.checkNotNull(itemListBean16);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = itemListBean16;
        itemListBean17.setValue(itemListBean17.getValueSrnArray().get(boolBit52 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = this.mSettingItemIndexHashMap.get("_350_s_2_3");
        Intrinsics.checkNotNull(itemListBean18);
        itemListBean18.setValue(Integer.valueOf(boolBit42 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = this.mSettingItemIndexHashMap.get("_350_s_2_4");
        Intrinsics.checkNotNull(itemListBean19);
        itemListBean19.setValue(Integer.valueOf(boolBit3 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = this.mSettingItemIndexHashMap.get("_350_s_2_5");
        Intrinsics.checkNotNull(itemListBean20);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = itemListBean20;
        itemListBean21.setValue(itemListBean21.getValueSrnArray().get(intFromByteWithBit5));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean22 = this.mSettingItemIndexHashMap.get("_350_s_3_0");
        Intrinsics.checkNotNull(itemListBean22);
        itemListBean22.setValue(Integer.valueOf(boolBit74 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean23 = this.mSettingItemIndexHashMap.get("_350_s_3_1");
        Intrinsics.checkNotNull(itemListBean23);
        itemListBean23.setValue(Integer.valueOf(boolBit63 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean24 = this.mSettingItemIndexHashMap.get("_350_s_3_2");
        Intrinsics.checkNotNull(itemListBean24);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean25 = itemListBean24;
        itemListBean25.setValue(itemListBean25.getValueSrnArray().get(intFromByteWithBit6));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean26 = this.mSettingItemIndexHashMap.get("_350_s_3_3");
        Intrinsics.checkNotNull(itemListBean26);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean27 = itemListBean26;
        itemListBean27.setValue(itemListBean27.getValueSrnArray().get(intFromByteWithBit7));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean28 = this.mSettingItemIndexHashMap.get("_350_s_4_0");
        Intrinsics.checkNotNull(itemListBean28);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean29 = itemListBean28;
        itemListBean29.setValue(itemListBean29.getValueSrnArray().get(intFromByteWithBit8));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean30 = this.mSettingItemIndexHashMap.get("_350_s_4_1");
        Intrinsics.checkNotNull(itemListBean30);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean31 = itemListBean30;
        itemListBean31.setProgress(intFromByteWithBit9);
        itemListBean31.setValue(String.valueOf(itemListBean31.getProgress()));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean32 = this.mSettingItemIndexHashMap.get("_350_s_4_2");
        Intrinsics.checkNotNull(itemListBean32);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean33 = itemListBean32;
        itemListBean33.setValue(itemListBean33.getValueSrnArray().get(intFromByteWithBit10));
        updateSettingActivity(null);
    }

    private final void amplifierInfo() {
        GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 4, 4), 0, 14), 0, 2, null) - 7;
        GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[2], 0, 4), 0, 14) - 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[3], 0, 4), 2, 12) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(getFrameData()[5], 0, 63);
        updateAmplifierActivity(null);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("_350_s_6_0");
        Intrinsics.checkNotNull(itemListBean);
        itemListBean.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrameData()[4], 0, 4) == 8 ? 1 : 0));
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemIndexHashMap.get("_350_s_6_1");
        Intrinsics.checkNotNull(itemListBean2);
        itemListBean2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrameData()[6]) ? 1 : 0));
        updateSettingActivity(null);
    }

    public final void systemInfo() {
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
        item7.setValue(boolBit6 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        DriverDataPageUiSet.Page.Item item8 = this.mDriveItemIndexHashMap.get("_350_d_3_5");
        Intrinsics.checkNotNull(item8);
        item8.setValue(boolBit7 ? CommUtil.getStrByResId(getContext(), "_350_u_1") : CommUtil.getStrByResId(getContext(), "_350_u_0"));
        updateDriveDataActivity(null);
        if (boolBit3) {
            forceReverse(getContext(), true);
        } else {
            forceReverse(getContext(), false);
        }
    }

    private final void controlModeInfo() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemIndexHashMap.get("_350_s_7_0_0");
        Intrinsics.checkNotNull(itemListBean);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = itemListBean;
        itemListBean2.setValue(itemListBean2.getValueSrnArray().get(getFrameData()[2]));
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

    public final void initItemsIndexHashMap(Context context) {
        UiMgr mUiMgr = getMUiMgr();
        List<SettingPageUiSet.ListBean> list = mUiMgr.getSettingUiSet(context).getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it.next().getItemList()) {
                int i4 = i3 + 1;
                HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> map = this.mSettingItemIndexHashMap;
                String titleSrn = itemListBean.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "itemListBean.titleSrn");
                SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = list.get(i).getItemList().get(i3);
                Intrinsics.checkNotNullExpressionValue(itemListBean2, "this[left].itemList[right]");
                map.put(titleSrn, itemListBean2);
                i3 = i4;
            }
            i = i2;
        }
        List<DriverDataPageUiSet.Page> list2 = mUiMgr.getDriverDataPageUiSet(context).getList();
        Iterator<DriverDataPageUiSet.Page> it2 = list2.iterator();
        int i5 = 0;
        while (it2.hasNext()) {
            int i6 = i5 + 1;
            int i7 = 0;
            for (DriverDataPageUiSet.Page.Item item : it2.next().getItemList()) {
                int i8 = i7 + 1;
                HashMap<String, DriverDataPageUiSet.Page.Item> map2 = this.mDriveItemIndexHashMap;
                String titleSrn2 = item.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn2, "item.titleSrn");
                DriverDataPageUiSet.Page.Item item2 = list2.get(i5).getItemList().get(i7);
                Intrinsics.checkNotNullExpressionValue(item2, "this[pageIndex].itemList[itemIndex]");
                map2.put(titleSrn2, item2);
                i7 = i8;
            }
            i5 = i6;
        }
    }
}
