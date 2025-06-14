package com.hzbhd.canbus.car._163;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\t\n\u0002\b+\u0018\u0000 \u008e\u00012\u00020\u0001:\u0002\u008e\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\"\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010$\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010%\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016JP\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\n2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-H\u0016J\"\u0010.\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u0018\u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\nH\u0016J\u0018\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\"H\u0016Jp\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u00020\u0013H\u0016J\b\u0010B\u001a\u00020\u001cH\u0002J\b\u0010C\u001a\u00020\u001cH\u0002J8\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u001a2\u0006\u0010H\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020\u001aH\u0002J\u000e\u0010L\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\"H\u0002J\u000e\u0010O\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010P\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fH\u0016J\b\u0010Q\u001a\u00020\"H\u0002J\u0010\u0010R\u001a\u00020\"2\u0006\u00100\u001a\u00020\nH\u0002J\u0010\u0010S\u001a\u00020\"2\u0006\u00100\u001a\u00020\nH\u0002J\u0006\u0010T\u001a\u00020\u001cJ¸\u0001\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u001a2\u0006\u0010[\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020\u001a2\u0006\u0010]\u001a\u00020\u001a2\u0006\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\u001a2\u0006\u0010`\u001a\u00020F2\u0006\u0010a\u001a\u00020F2\u0006\u0010b\u001a\u00020F2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u001a2\u0006\u0010f\u001a\u00020\u00132\u0006\u0010g\u001a\u00020\"2\u0006\u0010h\u001a\u00020d2\u0006\u0010i\u001a\u00020F2\u0006\u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020F2\u0006\u0010l\u001a\u00020\"H\u0016J\b\u0010m\u001a\u00020\u001cH\u0002J\u0010\u0010n\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\nH\u0002J0\u0010o\u001a\u00020\u001c2\u0006\u0010p\u001a\u00020\u00132\u0006\u0010E\u001a\u00020F2\u0006\u0010q\u001a\u00020F2\u0006\u0010r\u001a\u00020F2\u0006\u0010s\u001a\u00020\u0013H\u0016J\u0010\u0010t\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010u\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010v\u001a\u00020F2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010w\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010y\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u00132\b\b\u0002\u0010z\u001a\u00020\nH\u0002J\u0010\u0010{\u001a\u00020\u001c2\u0006\u0010|\u001a\u00020\nH\u0002J\b\u0010}\u001a\u00020\u001cH\u0002J\b\u0010~\u001a\u00020\u001cH\u0002J\b\u0010\u007f\u001a\u00020\u001cH\u0002J\t\u0010\u0080\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0081\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0082\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0083\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0084\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0085\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0086\u0001\u001a\u00020\u001cH\u0002J\t\u0010\u0087\u0001\u001a\u00020\u001cH\u0002J\u0012\u0010\u0088\u0001\u001a\u00020\u001c2\u0007\u0010\u0089\u0001\u001a\u00020\"H\u0016J\u0007\u0010\u008a\u0001\u001a\u00020\u001cJ\u0093\u0001\u0010\u008b\u0001\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u00132\u0006\u0010Y\u001a\u00020\u00132\u0006\u0010Z\u001a\u00020\u001a2\u0006\u0010[\u001a\u00020\u001a2\u0006\u0010\\\u001a\u00020\u001a2\u0006\u0010]\u001a\u00020F2\u0006\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\u001a2\u0006\u0010`\u001a\u00020F2\u0006\u0010a\u001a\u00020F2\u0006\u0010b\u001a\u00020F2\u0006\u0010c\u001a\u00020\u00132\u0007\u0010\u008c\u0001\u001a\u00020\u001a2\u0006\u0010g\u001a\u00020\"2\u0007\u0010\u008d\u0001\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008f\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_163/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAirData", "", "getMAirData", "()[I", "setMAirData", "([I)V", "mCanBusInfoByte", "", "mCanBusInfoInt", "mCanbusAirInfoCopy", "mCanbusDoorInfoCopy", "mContext", "Landroid/content/Context;", "mUiMgr", "Lcom/hzbhd/canbus/car/_163/UiMgr;", "value", "", "mVolume", "getMVolume", "()I", "setMVolume", "(I)V", "x22D1", "", "afterServiceNormalSetting", "", "context", "btMusicInfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "driveData0", "driveData1", "getAllBandTypeData", "currBand", "", OriginalBtnAction.FM1, OriginalBtnAction.FM2, "fm3", "am1", "am2", "getBits", "getBoolResult", "data", "getTens", "initCommand", "isAirDataNoChange", "isAirMsgRepeatRenault", "isDoorMsgRepeatRenault", "languageSet", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "originalStatus", "panelKnob", "radioInfoChange", "currClickPresetIndex", "currentFreq", "psName", "isStereo", "realKeyClick", "realKeyClick2", "resolveLeftAndRightTemp", "sendBtPhoneData", "d0", "sendMediaSource", "d1t12", "sendTextData", "byteArray", "set0x23Data", "setAirData0x31", "setCarData", "setCarInfo", "setCarInfoData2", "setDoorData0x12", "setPanelBtnKey", "setRadar", "setSwc", "setTrack", "setVersionInfo", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettingData", "videoInfoChange", "playMode", "duation", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private UiMgr mUiMgr;
    private byte x22D1;
    private int[] mAirData = new int[0];
    private int mVolume = getVolume();

    private final int getBoolResult(boolean data) {
        return data ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf((byte) DataHandleUtils.setIntByteWithBit(0, 7, !isGpsTime)));
        if (isFormat24H) {
            bHours = bHours24H;
        }
        arrayList.add(Byte.valueOf((byte) bHours));
        arrayList.add(Byte.valueOf((byte) bMins));
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf(isFormat24H ? (byte) 1 : (byte) 0));
        arrayList.add(Byte.valueOf((byte) bYear2Dig));
        arrayList.add(Byte.valueOf((byte) bMonth));
        arrayList.add(Byte.valueOf((byte) bDay));
        arrayList.add((byte) 1);
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -53}, CollectionsKt.toByteArray(arrayList)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.initCommand(context);
        this.mContext = context;
        languageSet();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._163.UiMgr");
        UiMgr uiMgr = (UiMgr) canUiMgr;
        this.mUiMgr = uiMgr;
        if (uiMgr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
            uiMgr = null;
        }
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        this.mCanBusInfoByte = canbusInfo;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        if (byteArrayToIntArray == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            byteArrayToIntArray = null;
        }
        int i = byteArrayToIntArray[1];
        if (i == 38) {
            setCarData();
            return;
        }
        if (i == 49) {
            setAirData0x31();
            return;
        }
        if (i == 65) {
            setRadar();
            return;
        }
        if (i == 232) {
            originalStatus();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 97) {
            setCarInfo();
            return;
        }
        if (i != 98) {
            switch (i) {
                case 17:
                    setSwc();
                    setTrack();
                    break;
                case 18:
                    setDoorData0x12();
                    break;
                case 19:
                    driveData0();
                    break;
                case 20:
                    driveData1();
                    break;
                default:
                    switch (i) {
                        case 33:
                            setPanelBtnKey();
                            break;
                        case 34:
                            panelKnob(canbusInfo);
                            break;
                        case 35:
                            set0x23Data();
                            break;
                    }
            }
            return;
        }
        setCarInfoData2();
    }

    private final void set0x23Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S163_x2A_1");
        if (itemListBean != null) {
            List<String> valueSrnArray = itemListBean.getValueSrnArray();
            int[] iArr = this.mCanBusInfoInt;
            if (iArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr = null;
            }
            itemListBean.setValue(valueSrnArray.get(iArr[2]));
        }
        updateSettingData();
    }

    private final void setCarInfoData2() {
        char c;
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1);
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr2[2], 6, 1);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr3 = null;
        }
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr3[2], 5, 1);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(iArr4[2], 4, 1);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr5 = null;
        }
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr5[2], 3, 1);
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr6 = null;
        }
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(iArr6[2], 2, 1);
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr7 = null;
        }
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(iArr7[2], 1, 1);
        int[] iArr8 = this.mCanBusInfoInt;
        if (iArr8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr8 = null;
        }
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(iArr8[2], 0, 1);
        int[] iArr9 = this.mCanBusInfoInt;
        if (iArr9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr9 = null;
        }
        int i = iArr9[3];
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr10 = null;
        }
        int i2 = iArr10[4];
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr11 = null;
        }
        int i3 = iArr11[5];
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr12 = null;
        }
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(iArr12[6], 7, 1);
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            c = 6;
            iArr13 = null;
        } else {
            c = 6;
        }
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(iArr13[c], 4, 3);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(intFromByteWithBit7));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(intFromByteWithBit8));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(Integer.valueOf(i));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(i2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(i3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(Integer.valueOf(intFromByteWithBit9));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("S163_CarInfo2_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(itemListBean13.getValueSrnArray().get(intFromByteWithBit10));
        }
        updateSettingActivity(null);
    }

    private final void setCarData() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S18_Car_0");
        if (itemListBean != null) {
            int[] iArr = this.mCanBusInfoInt;
            if (iArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr = null;
            }
            int i = iArr[3];
            boolean z = false;
            if (17 <= i && i < 30) {
                z = true;
            }
            if (z) {
                List<String> valueSrnArray = itemListBean.getValueSrnArray();
                int[] iArr2 = this.mCanBusInfoInt;
                if (iArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                    iArr2 = null;
                }
                itemListBean.setValue(valueSrnArray.get(iArr2[3] - 16));
            }
        }
        updateSettingActivity(null);
    }

    private final void panelKnob(byte[] canbusInfo) {
        byte b = canbusInfo[3];
        byte b2 = this.x22D1;
        int iAbs = Math.abs(b - b2);
        int[] iArr = this.mCanBusInfoInt;
        Context context = null;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int i = iArr[2];
        if (i != 1) {
            if (i == 2) {
                if (b > b2) {
                    Context context2 = this.mContext;
                    if (context2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context = context2;
                    }
                    DataHandleUtils.knob(context, 46, iAbs);
                } else if (b < b2) {
                    Context context3 = this.mContext;
                    if (context3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mContext");
                    } else {
                        context = context3;
                    }
                    DataHandleUtils.knob(context, 45, iAbs);
                }
            }
        } else if (b > b2) {
            Context context4 = this.mContext;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context = context4;
            }
            DataHandleUtils.knob(context, 7, iAbs);
        } else if (b < b2) {
            Context context5 = this.mContext;
            if (context5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
            } else {
                context = context5;
            }
            DataHandleUtils.knob(context, 8, iAbs);
        }
        this.x22D1 = canbusInfo[3];
    }

    private final void setVersionInfo() {
        Context context = this.mContext;
        byte[] bArr = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        byte[] bArr2 = this.mCanBusInfoByte;
        if (bArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
        } else {
            bArr = bArr2;
        }
        updateVersionInfo(context, getVersionStr(bArr));
    }

    private final void setAirData0x31() {
        if (isAirDataNoChange()) {
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        Context context = null;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(iArr[2]);
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        GeneralAirData.rear = DataHandleUtils.getBoolBit4(iArr2[2]);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr3 = null;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr3[2]);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(iArr4[2]);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr5 = null;
        }
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(iArr5[2], 0, 2) == 1;
        int[] iArr6 = this.mCanBusInfoInt;
        if (iArr6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr6 = null;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(iArr6[3]);
        int[] iArr7 = this.mCanBusInfoInt;
        if (iArr7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr7 = null;
        }
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(iArr7[3]);
        int[] iArr8 = this.mCanBusInfoInt;
        if (iArr8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr8 = null;
        }
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(iArr8[4]);
        int[] iArr9 = this.mCanBusInfoInt;
        if (iArr9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr9 = null;
        }
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr9[4]);
        int[] iArr10 = this.mCanBusInfoInt;
        if (iArr10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr10 = null;
        }
        GeneralAirData.fast = DataHandleUtils.getBoolBit1(iArr10[5]);
        int[] iArr11 = this.mCanBusInfoInt;
        if (iArr11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr11 = null;
        }
        GeneralAirData.soft = DataHandleUtils.getBoolBit0(iArr11[5]);
        int[] iArr12 = this.mCanBusInfoInt;
        if (iArr12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr12 = null;
        }
        GeneralAirData.front_wind_level = iArr12[7];
        int[] iArr13 = this.mCanBusInfoInt;
        if (iArr13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr13 = null;
        }
        int i = iArr13[6];
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
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
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
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
        int[] iArr14 = this.mCanBusInfoInt;
        if (iArr14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr14 = null;
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(iArr14[8]);
        int[] iArr15 = this.mCanBusInfoInt;
        if (iArr15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr15 = null;
        }
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(iArr15[9]);
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context2 = null;
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr16 = this.mCanBusInfoInt;
        if (iArr16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr16 = null;
        }
        updateOutDoorTemp(context2, sb.append((float) ((iArr16[13] * 0.5d) - 40)).append(" °C").toString());
        Context context3 = this.mContext;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context3;
        }
        updateAirActivity(context, 1004);
    }

    public final int[] getMAirData() {
        return this.mAirData;
    }

    public final void setMAirData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData = iArr;
    }

    private final boolean isAirDataNoChange() {
        int[] iArr = this.mAirData;
        int[] iArr2 = this.mCanBusInfoInt;
        int[] iArr3 = null;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        if (Arrays.equals(iArr, iArr2)) {
            return true;
        }
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
        } else {
            iArr3 = iArr4;
        }
        this.mAirData = iArr3;
        return false;
    }

    private final void setSwc() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int i = iArr[4];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick(HotKeyConstant.K_MUTE_PHONE_ON_OUT);
            return;
        }
        if (i == 4) {
            realKeyClick(128);
            return;
        }
        if (i == 7) {
            realKeyClick(95);
            return;
        }
        if (i == 8) {
            realKeyClick(48);
            return;
        }
        if (i == 9) {
            realKeyClick(47);
            return;
        }
        if (i == 13) {
            realKeyClick(45);
            return;
        }
        if (i == 14) {
            realKeyClick(46);
            return;
        }
        if (i == 16) {
            realKeyClick(HotKeyConstant.K_BAND_PICKUP);
        } else if (i == 17) {
            realKeyClick(2);
        } else {
            if (i != 24) {
                return;
            }
            realKeyClick(HotKeyConstant.K_SPEECH);
        }
    }

    private final void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
            bArr = null;
        }
        byte b = bArr[9];
        byte[] bArr2 = this.mCanBusInfoByte;
        if (bArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
            bArr2 = null;
        }
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(b, bArr2[8], 0, 5400, 16);
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        updateParkUi(null, context);
    }

    private final void setDoorData0x12() {
        int[] iArr = this.mCanBusInfoInt;
        Context context = null;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[4]);
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr2[4]);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr3 = null;
        }
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(iArr3[4]);
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr4[4]);
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr5 = null;
        }
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr5[4]);
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context = context2;
        }
        updateDoorView(context);
    }

    private final void driveData0() throws Resources.NotFoundException {
        String string;
        String string2;
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int i = iArr[10];
        if (128 <= i && i < 159) {
            Context context = this.mContext;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context = null;
            }
            String string3 = context.getResources().getString(R.string._163_drive_data7);
            Intrinsics.checkNotNullExpressionValue(string3, "mContext.resources.getSt….string._163_drive_data7)");
            StringBuilder sbAppend = new StringBuilder().append(string3);
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr2 = null;
            }
            string = sbAppend.append(iArr2[10] - 128).append(Typography.degree).toString();
        } else {
            StringBuilder sb = new StringBuilder();
            Context context2 = this.mContext;
            if (context2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context2 = null;
            }
            StringBuilder sbAppend2 = sb.append(context2.getResources().getString(R.string._163_drive_data6));
            int[] iArr3 = this.mCanBusInfoInt;
            if (iArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr3 = null;
            }
            string = sbAppend2.append(iArr3[10]).append(Typography.degree).toString();
        }
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        int i2 = iArr4[11];
        if (128 <= i2 && i2 < 159) {
            Context context3 = this.mContext;
            if (context3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context3 = null;
            }
            String string4 = context3.getResources().getString(R.string._163_drive_data8);
            Intrinsics.checkNotNullExpressionValue(string4, "mContext.resources.getSt….string._163_drive_data8)");
            StringBuilder sbAppend3 = new StringBuilder().append(string4);
            int[] iArr5 = this.mCanBusInfoInt;
            if (iArr5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr5 = null;
            }
            string2 = sbAppend3.append(iArr5[11] - 128).append(Typography.degree).toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            Context context4 = this.mContext;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context4 = null;
            }
            StringBuilder sbAppend4 = sb2.append(context4.getResources().getString(R.string._163_drive_data9));
            int[] iArr6 = this.mCanBusInfoInt;
            if (iArr6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
                iArr6 = null;
            }
            string2 = sbAppend4.append(iArr6[11]).append(Typography.degree).toString();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        arrayList.add(new DriverUpdateEntity(0, 1, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x017e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void driveData1() {
        /*
            Method dump skipped, instructions count: 578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._163.MsgMgr.driveData1():void");
    }

    private final void setPanelBtnKey() {
        int[] iArr = this.mCanBusInfoInt;
        Context context = null;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int i = iArr[2];
        if (i == 0) {
            realKeyClick2(0);
            return;
        }
        if (i == 4) {
            realKeyClick2(151);
            return;
        }
        if (i != 6) {
            if (i == 32) {
                realKeyClick2(128);
                return;
            }
            if (i == 33) {
                realKeyClick2(128);
                return;
            }
            if (i == 69) {
                realKeyClick2(7);
                return;
            }
            if (i == 70) {
                realKeyClick2(8);
                return;
            }
            if (i == 91) {
                realKeyClick2(59);
                return;
            }
            if (i != 92) {
                switch (i) {
                    case 6:
                        break;
                    case 9:
                        realKeyClick2(3);
                        break;
                    case 18:
                        Context context2 = this.mContext;
                        if (context2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mContext");
                        } else {
                            context = context2;
                        }
                        startMainActivity(context);
                        break;
                    case 37:
                        realKeyClick2(27);
                        break;
                    case 43:
                        realKeyClick2(52);
                        break;
                    case 49:
                        realKeyClick2(57);
                        break;
                    case 55:
                        realKeyClick2(58);
                        break;
                    case 57:
                        realKeyClick2(57);
                        break;
                    case 84:
                        realKeyClick2(128);
                        break;
                    default:
                        switch (i) {
                            case 22:
                                realKeyClick2(49);
                                break;
                            case 23:
                                realKeyClick2(45);
                                break;
                            case 24:
                                realKeyClick2(46);
                                break;
                            case 25:
                                realKeyClick2(47);
                                break;
                            case 26:
                                realKeyClick2(48);
                                break;
                        }
                }
                return;
            }
            realKeyClick2(2);
            return;
        }
        realKeyClick2(50);
    }

    private final void setRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        int i = iArr[2];
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        int i2 = iArr2[3];
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr3 = null;
        }
        int i3 = iArr3[4];
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        RadarInfoUtil.setRearRadarLocationData(4, i, i2, i3, iArr4[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        updateParkUi(null, context);
    }

    private final void originalStatus() {
        ArrayList arrayList = new ArrayList();
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        forceReverse(context, iArr[5] == 1);
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr2 = null;
        }
        arrayList.add(new PanoramicBtnUpdateEntity(0, iArr2[3] == 5));
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr3 = null;
        }
        arrayList.add(new PanoramicBtnUpdateEntity(1, iArr3[3] == 6));
        int[] iArr4 = this.mCanBusInfoInt;
        if (iArr4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr4 = null;
        }
        arrayList.add(new PanoramicBtnUpdateEntity(2, iArr4[3] == 7));
        int[] iArr5 = this.mCanBusInfoInt;
        if (iArr5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr5 = null;
        }
        arrayList.add(new PanoramicBtnUpdateEntity(3, iArr5[3] == 8));
        GeneralParkData.dataList = arrayList;
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context2 = null;
        }
        updateParkUi(null, context2);
    }

    private final void setCarInfo() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
            iArr = null;
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("_163_setting_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[2], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_2");
        if (itemListBean2 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 5);
            itemListBean2.setProgress(intFromByteWithBit);
            itemListBean2.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(iArr[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit5(iArr[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit4(iArr[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[4], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(itemListBean8.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[4], 3, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(itemListBean9.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(itemListBean10.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[5], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_11");
        if (itemListBean11 != null) {
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[5], 2, 3);
            itemListBean11.setValue(itemListBean11.getValueSrnArray().get(intFromByteWithBit2 != 0 ? intFromByteWithBit2 - 2 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_13");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_14");
        if (itemListBean13 != null) {
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[6], 3, 3);
            itemListBean13.setProgress(intFromByteWithBit3 - 1);
            itemListBean13.setValue(String.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_15");
        if (itemListBean14 != null) {
            int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 3);
            itemListBean14.setProgress(intFromByteWithBit4 - 1);
            itemListBean14.setValue(String.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_12");
        if (itemListBean15 != null) {
            itemListBean15.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[7]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_16");
        if (itemListBean16 != null) {
            itemListBean16.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_17");
        if (itemListBean17 != null) {
            itemListBean17.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_18");
        if (itemListBean18 != null) {
            itemListBean18.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit5(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_19");
        if (itemListBean19 != null) {
            itemListBean19.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit4(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_20");
        if (itemListBean20 != null) {
            itemListBean20.setValue(itemListBean20.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[8], 2, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_21");
        if (itemListBean21 != null) {
            itemListBean21.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit1(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean22 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_22");
        if (itemListBean22 != null) {
            itemListBean22.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit0(iArr[8]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean23 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_23");
        if (itemListBean23 != null) {
            itemListBean23.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[9]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean24 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_24");
        if (itemListBean24 != null) {
            int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 5);
            itemListBean24.setProgress(intFromByteWithBit5);
            itemListBean24.setValue(String.valueOf(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean25 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_26");
        if (itemListBean25 != null) {
            itemListBean25.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[10]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean26 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_27");
        if (itemListBean26 != null) {
            itemListBean26.setValue(itemListBean26.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[10], 5, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean27 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_28");
        if (itemListBean27 != null) {
            int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 5);
            itemListBean27.setProgress(intFromByteWithBit6);
            itemListBean27.setValue(String.valueOf(intFromByteWithBit6));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean28 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_29");
        if (itemListBean28 != null) {
            itemListBean28.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit7(iArr[11]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean29 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_30");
        if (itemListBean29 != null) {
            itemListBean29.setValue(Integer.valueOf(MsgMgrKt.getOneOrZero(DataHandleUtils.getBoolBit6(iArr[11]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean30 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_31");
        if (itemListBean30 != null) {
            itemListBean30.setValue(itemListBean30.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[11], 4, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean31 = InitUtilsKt.getMSettingItemIndex().get("_163_setting_32");
        if (itemListBean31 != null) {
            itemListBean31.setValue(itemListBean31.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(iArr[11], 2, 2)));
        }
        updateSettingActivity(null);
    }

    public final void languageSet() {
        ArrayList arrayList = new ArrayList();
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(SharePreUtil.getIntValue(context, "_163_language_item", 0))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final void realKeyClick(int value) {
        Context context = this.mContext;
        int[] iArr = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
        } else {
            iArr = iArr2;
        }
        realKeyLongClick1(context, value, iArr[5]);
    }

    private final void realKeyClick2(int value) {
        Context context = this.mContext;
        int[] iArr = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
        } else {
            iArr = iArr2;
        }
        realKeyLongClick1(context, value, iArr[3]);
    }

    private final String resolveLeftAndRightTemp(int value) {
        if (value == 254) {
            return "LO";
        }
        if (value == 255) {
            return "HI";
        }
        StringBuilder sbAppend = new StringBuilder().append(value * 0.5f);
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        return sbAppend.append(getTempUnitC(context)).toString();
    }

    private final boolean isAirMsgRepeatRenault(byte[] canbusInfo) {
        byte[] bArr = this.mCanbusAirInfoCopy;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanbusAirInfoCopy");
            bArr = null;
        }
        if (!Arrays.equals(canbusInfo, bArr)) {
            byte[] bArrCopyOf = Arrays.copyOf(canbusInfo, canbusInfo.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
            this.mCanbusAirInfoCopy = bArrCopyOf;
            if (!isAirFirst) {
                return false;
            }
            isAirFirst = false;
        }
        return true;
    }

    private final boolean isDoorMsgRepeatRenault(byte[] canbusInfo) {
        byte[] bArr = this.mCanbusDoorInfoCopy;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCanbusDoorInfoCopy");
            bArr = null;
        }
        if (!Arrays.equals(canbusInfo, bArr)) {
            byte[] bArrCopyOf = Arrays.copyOf(canbusInfo, canbusInfo.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
            this.mCanbusDoorInfoCopy = bArrCopyOf;
            if (!isDoorFirst) {
                return false;
            }
            isDoorFirst = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0054 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final byte getAllBandTypeData(java.lang.String r2, byte r3, byte r4, byte r5, byte r6, byte r7) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case 2443: goto L4b;
                case 2474: goto L41;
                case 64901: goto L38;
                case 64902: goto L2f;
                case 69706: goto L25;
                case 69707: goto L1b;
                case 69708: goto L11;
                case 2426268: goto L8;
                default: goto L7;
            }
        L7:
            goto L55
        L8:
            java.lang.String r3 = "OIRT"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L1a
            goto L55
        L11:
            java.lang.String r3 = "FM3"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L1a
            goto L55
        L1a:
            return r5
        L1b:
            java.lang.String r3 = "FM2"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L24
            goto L55
        L24:
            return r4
        L25:
            java.lang.String r4 = "FM1"
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L2e
            goto L55
        L2e:
            return r3
        L2f:
            java.lang.String r3 = "AM2"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L4a
            goto L55
        L38:
            java.lang.String r3 = "AM1"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L54
            goto L55
        L41:
            java.lang.String r3 = "MW"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L4a
            goto L55
        L4a:
            return r7
        L4b:
            java.lang.String r3 = "LW"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L54
            goto L55
        L54:
            return r6
        L55:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._163.MsgMgr.getAllBandTypeData(java.lang.String, byte, byte, byte, byte, byte):byte");
    }

    static /* synthetic */ void sendMediaSource$default(MsgMgr msgMgr, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        msgMgr.sendMediaSource(i, bArr);
    }

    private final void sendMediaSource(int d0, byte[] d1t12) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -31}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict(ArraysKt.plus(new byte[]{(byte) d0}, d1t12), 13, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendMediaSource$default(this, 0, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        Intrinsics.checkNotNullParameter(currBand, "currBand");
        Intrinsics.checkNotNullParameter(currentFreq, "currentFreq");
        Intrinsics.checkNotNullParameter(psName, "psName");
        switch (currBand.hashCode()) {
            case 64901:
                if (currBand.equals("AM1")) {
                    i = 4;
                    break;
                } else {
                    return;
                }
            case 64902:
                if (currBand.equals("AM2")) {
                    i = 5;
                    break;
                } else {
                    return;
                }
            case 69706:
                if (currBand.equals("FM1")) {
                    i = 1;
                    break;
                } else {
                    return;
                }
            case 69707:
                if (currBand.equals("FM2")) {
                    i = 2;
                    break;
                } else {
                    return;
                }
            case 69708:
                if (currBand.equals("FM3")) {
                    i = 3;
                    break;
                } else {
                    return;
                }
            default:
                return;
        }
        sendMediaSource(i, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(currClickPresetIndex, currBand, 8, currentFreq));
    }

    private final void sendBtPhoneData(int d0, byte[] phoneNumber) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf((byte) d0));
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        byte[] byteArray = CollectionsKt.toByteArray(arrayList);
        if (phoneNumber == null) {
            return;
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(byteArray, phoneNumber), 27, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendBtPhoneData(1, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendBtPhoneData(2, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendBtPhoneData(4, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendBtPhoneData(5, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Intrinsics.checkNotNullParameter(currentHourStr, "currentHourStr");
        Intrinsics.checkNotNullParameter(currentMinuteStr, "currentMinuteStr");
        Intrinsics.checkNotNullParameter(currentSecondStr, "currentSecondStr");
        Intrinsics.checkNotNullParameter(songTitle, "songTitle");
        Intrinsics.checkNotNullParameter(songAlbum, "songAlbum");
        Intrinsics.checkNotNullParameter(songArtist, "songArtist");
        if (stoagePath != 9) {
            byte[] bytes = songTitle.getBytes(Charsets.UTF_16BE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            sendTextData(bytes);
            sendMediaSource$default(this, 13, null, 2, null);
        }
    }

    private final void sendTextData(byte[] byteArray) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -28}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(byteArray, 32, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duation) {
        Intrinsics.checkNotNullParameter(currentAllMinuteStr, "currentAllMinuteStr");
        Intrinsics.checkNotNullParameter(currentHourStr, "currentHourStr");
        Intrinsics.checkNotNullParameter(currentMinuteStr, "currentMinuteStr");
        Intrinsics.checkNotNullParameter(currentSecondStr, "currentSecondStr");
        if (stoagePath != 9) {
            sendMediaSource$default(this, 13, null, 2, null);
        }
    }

    public final int getMVolume() {
        return this.mVolume;
    }

    public final void setMVolume(int i) {
        if (i != this.mVolume) {
            sendMediaSource(32, new byte[]{86, 79, 76, 32, (byte) getTens(i), (byte) getBits(i)});
        }
        this.mVolume = i;
    }

    public final int getTens(int value) {
        return (value / 10) % 10;
    }

    public final int getBits(int value) {
        return (value / 1) % 10;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        setMVolume(volValue);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        sendMediaSource$default(this, 10, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        sendMediaSource$default(this, 133, null, 2, null);
    }

    public final void updateSettingData() {
        updateSettingActivity(null);
    }
}
