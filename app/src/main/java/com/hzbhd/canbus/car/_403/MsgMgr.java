package com.hzbhd.canbus.car._403;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0016JT\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u001c\u0010&\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010'\u001a\u0004\u0018\u00010\u001dH\u0016Jp\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u0004H\u0016JÄ\u0001\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u0002082\u0006\u0010=\u001a\u0002082\u0006\u0010>\u001a\u0002082\u0006\u0010?\u001a\u0002082\u0006\u0010@\u001a\u0002082\u0006\u0010A\u001a\u0002082\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010C2\b\u0010E\u001a\u0004\u0018\u00010C2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u0002082\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020G2\b\u0010L\u001a\u0004\u0018\u00010C2\b\u0010M\u001a\u0004\u0018\u00010C2\b\u0010N\u001a\u0004\u0018\u00010C2\u0006\u0010O\u001a\u00020\u0011H\u0016J6\u0010P\u001a\u00020\u00162\u0006\u0010Q\u001a\u00020\u00042\b\u0010R\u001a\u0004\u0018\u00010C2\b\u0010S\u001a\u0004\u0018\u00010C2\b\u0010T\u001a\u0004\u0018\u00010C2\u0006\u0010U\u001a\u00020\u0004H\u0016J\u0010\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\u0004H\u0002J\b\u0010X\u001a\u00020\u0016H\u0002J\b\u0010Y\u001a\u00020\u0016H\u0002J\b\u0010Z\u001a\u00020\u0016H\u0002J\b\u0010[\u001a\u00020\u0016H\u0002J\b\u0010\\\u001a\u00020\u0016H\u0002J\b\u0010]\u001a\u00020\u0016H\u0002J\b\u0010^\u001a\u00020\u0016H\u0002J\b\u0010_\u001a\u00020\u0016H\u0002J\b\u0010`\u001a\u00020\u0016H\u0002J\b\u0010a\u001a\u00020\u0016H\u0002J\u0010\u0010b\u001a\u00020\u00162\u0006\u0010c\u001a\u00020\u001dH\u0002J\u0010\u0010d\u001a\u00020\u00162\u0006\u0010e\u001a\u00020\u0011H\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006f"}, d2 = {"Lcom/hzbhd/canbus/car/_403/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "dateFormat", "", "getDateFormat", "()Ljava/lang/Integer;", "setDateFormat", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "isReverse", "", "()Z", "setReverse", "(Z)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "", "isHfpConnected", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSource", "d0", "set0x11Data", "set0x12Data", "set0x26Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0x66Data", "set0x67Data", "set0x68Data", "set0xE8Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private Integer dateFormat;
    public int[] frame;
    private boolean isReverse;

    private final void set0x26Data() {
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
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._403.UiMgr");
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        UiMgrInterface canUiMgr2 = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr2, "null cannot be cast to non-null type com.hzbhd.canbus.car._403.UiMgr");
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr2, null, 4, null);
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
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 38) {
            set0x26Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 232) {
            set0xE8Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 50) {
            set0x32Data();
            return;
        }
        switch (i) {
            case 102:
                set0x66Data();
                break;
            case 103:
                set0x67Data();
                break;
            case 104:
                set0x68Data();
                break;
        }
    }

    private final void sendMediaSource(int d0) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{(byte) d0}, 13, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendMediaSource(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaSource(12);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        sendMediaSource(10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        if (currBand != null) {
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
            sendMediaSource(i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        sendMediaSource(stoagePath == 9 ? 14 : 13);
        Intrinsics.checkNotNull(songTitle);
        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, songTitle, Charsets.UTF_16BE, 32, 10);
        Intrinsics.checkNotNull(songArtist);
        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, songArtist, Charsets.UTF_16BE, 32, 10);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0xE8Data() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2);
        if (intFromByteWithBit == 1) {
            new PanoramicBtnUpdateEntity(5, true);
            new PanoramicBtnUpdateEntity(6, false);
        } else if (intFromByteWithBit == 2) {
            new PanoramicBtnUpdateEntity(5, true);
            new PanoramicBtnUpdateEntity(6, true);
        } else {
            new PanoramicBtnUpdateEntity(5, false);
            new PanoramicBtnUpdateEntity(6, false);
        }
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    public final Integer getDateFormat() {
        return this.dateFormat;
    }

    public final void setDateFormat(Integer num) {
        this.dateFormat = num;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = 0;
        if (isFormat24H) {
            bHours = bHours24H;
        }
        bArr[3] = (byte) bHours;
        bArr[4] = (byte) bMins;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = isFormat24H ? (byte) 1 : (byte) 0;
        bArr[8] = (byte) bYear2Dig;
        bArr[9] = (byte) bMonth;
        bArr[10] = (byte) bDay;
        Integer num = this.dateFormat;
        if (num != null) {
            bArr[11] = (byte) num.intValue();
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    private final void set0x68Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x68_1");
        if (itemListBean != null) {
            itemListBean.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit5(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit4(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit3(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit2(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(itemListBean5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 2)));
        }
        updateSettingActivity(null);
    }

    private final void set0x67Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x67_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 7, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 5, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_9");
        if (itemListBean9 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 2, 2);
            itemListBean9.setProgress(intFromByteWithBit);
            itemListBean9.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 0, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 2, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_14");
        if (itemListBean14 != null) {
            itemListBean14.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 1)));
        }
        updateSettingActivity(null);
    }

    private final void set0x66Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x66_1");
        if (itemListBean != null) {
            itemListBean.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit7(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_2");
        if (itemListBean2 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 3);
            itemListBean2.setProgress(intFromByteWithBit);
            itemListBean2.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit2(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit1(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit0(getFrame()[3])));
        }
        updateSettingActivity(null);
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

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(getFrame()[6]), set0x41Data$restrictValue(getFrame()[7]), set0x41Data$restrictValue(getFrame()[8]), set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
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
        String str2 = "High";
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i3 = getFrame()[9];
        if (i3 == 254) {
            str2 = "Low";
        } else if (i3 != 255) {
            str2 = (i3 * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        updateOutDoorTemp(InitUtilsKt.getMContext(), getFrame()[13] + " °C");
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(getFrame()[4]);
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    /* renamed from: isReverse, reason: from getter */
    public final boolean getIsReverse() {
        return this.isReverse;
    }

    public final void setReverse(boolean z) {
        this.isReverse = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void set0x11Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[4];
        int i2 = 3;
        if (i != 101) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    i2 = 7;
                    break;
                case 2:
                    i2 = 8;
                    break;
                case 3:
                case 4:
                    break;
                case 5:
                    i2 = 14;
                    break;
                case 6:
                    i2 = 15;
                    break;
                default:
                    switch (i) {
                        case 8:
                            i2 = 21;
                            break;
                        case 9:
                            i2 = 20;
                            break;
                        case 10:
                            i2 = 52;
                            break;
                        case 11:
                            i2 = 2;
                            break;
                        default:
                            switch (i) {
                                case 13:
                                    i2 = 45;
                                    break;
                                case 14:
                                    i2 = 46;
                                    break;
                                case 15:
                                    i2 = 49;
                                    break;
                                case 16:
                                    i2 = 50;
                                    break;
                            }
                    }
            }
            realKeyLongClick1(mContext, i2, getFrame()[5]);
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
            updateParkUi(null, InitUtilsKt.getMContext());
        }
        forceReverse(InitUtilsKt.getMContext(), !this.isReverse);
        this.isReverse = !this.isReverse;
        i2 = 0;
        realKeyLongClick1(mContext, i2, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
