package com.hzbhd.canbus.car._404;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016Jp\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\nH\u0016JÄ\u0001\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020%2\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u0002042\b\u00109\u001a\u0004\u0018\u0001002\b\u0010:\u001a\u0004\u0018\u0001002\b\u0010;\u001a\u0004\u0018\u0001002\u0006\u0010<\u001a\u00020\u001fH\u0016J \u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u0002002\u0006\u0010@\u001a\u000200H\u0002J6\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\n2\b\u0010C\u001a\u0004\u0018\u0001002\b\u0010D\u001a\u0004\u0018\u0001002\b\u0010E\u001a\u0004\u0018\u0001002\u0006\u0010F\u001a\u00020\nH\u0016J&\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\n2\b\b\u0002\u0010I\u001a\u00020\n2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010K\u001a\u00020\u000eH\u0002J\b\u0010L\u001a\u00020\u000eH\u0002J\b\u0010M\u001a\u00020\u000eH\u0002J\b\u0010N\u001a\u00020\u000eH\u0002J\b\u0010O\u001a\u00020\u000eH\u0002J\b\u0010P\u001a\u00020\u000eH\u0002J\b\u0010Q\u001a\u00020\u000eH\u0002J\b\u0010R\u001a\u00020\u000eH\u0002J\u0010\u0010S\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\u0013H\u0002J\u0010\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u001fH\u0016J\u0098\u0001\u0010W\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\b\u0010,\u001a\u0004\u0018\u0001002\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\u0006\u00103\u001a\u00020\n2\u0006\u0010X\u001a\u00020%2\u0006\u00107\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006Z"}, d2 = {"Lcom/hzbhd/canbus/car/_404/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastD0", "", "Ljava/lang/Integer;", "lastD1", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioAscii", "index", "band", LcdInfoShare.RadioInfo.freq, "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSource", "d0", "d1", "d1t12", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x32Data", "set0x41Data", "set0x48Data", "set0x61Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;
    private Integer lastD0;
    private Integer lastD1;

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
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._404.UiMgr");
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        UiMgrInterface canUiMgr2 = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr2, "null cannot be cast to non-null type com.hzbhd.canbus.car._404.UiMgr");
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr2, null, 4, null);
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
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 72) {
            set0x48Data();
            return;
        }
        if (i == 97) {
            set0x61Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
        } else if (i == 49) {
            set0x31Data();
        } else {
            if (i != 50) {
                return;
            }
            set0x32Data();
        }
    }

    static /* synthetic */ void sendMediaSource$default(MsgMgr msgMgr, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            bArr = null;
        }
        msgMgr.sendMediaSource(i, i2, bArr);
    }

    private final void sendMediaSource(int d0, int d1, byte[] d1t12) {
        Integer num;
        Integer num2 = this.lastD0;
        if (num2 == null || num2.intValue() != d0 || (num = this.lastD1) == null || num.intValue() != d1) {
            if (d1t12 == null) {
                CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{(byte) d0, (byte) d1}, 13, 0, 4, null)));
            } else {
                CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(new byte[]{(byte) d0}, d1t12), 13, 0, 4, null)));
            }
        }
        this.lastD0 = Integer.valueOf(d0);
        this.lastD1 = Integer.valueOf(d1);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendMediaSource$default(this, 0, 0, null, 6, null);
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
            Intrinsics.checkNotNull(currentFreq);
            sendMediaSource$default(this, i, 0, radioAscii(currClickPresetIndex, currBand, currentFreq), 2, null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        sendMediaSource$default(this, stoagePath == 9 ? 14 : 13, !isPlaying ? 1 : 0, null, 4, null);
        Intrinsics.checkNotNull(songTitle);
        MsgMgrKt.sendTextInfo(146, songTitle, Charsets.UTF_16LE, 32, 10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        sendMediaSource$default(this, stoagePath == 9 ? 14 : 13, !isPlaying ? 1 : 0, null, 4, null);
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
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        bArr[11] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x61Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[2]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[2]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(getFrame()[2]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d0_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(getFrame()[2]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_1");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_2");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d1_3");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 3, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d2_1");
        if (itemListBean8 != null) {
            itemListBean8.setValue(itemListBean8.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S404_0x61_d2_2");
        if (itemListBean9 != null) {
            itemListBean9.setValue(itemListBean9.getValueSrnArray().get(com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(getFrame()[4]))));
        }
        updateSettingActivity(null);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(getFrame()[6]), 0, 0, set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x32Data() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_1");
        if (item != null) {
            item.setValue(getFrame()[3] == 0 ? "电量充足" : "电量不足");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_3");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_4");
        if (item4 != null) {
            item4.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9])));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_5");
        if (item5 != null) {
            item5.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) * 0.1d, 10) + " L/100Km");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_6");
        if (item6 != null) {
            item6.setValue(String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(com.hzbhd.canbus.car._361.MsgMgrKt.getAnotherMsbLsbResult(getFrame()[12], getFrame()[13], getFrame()[14]) * 0.1d, 10)));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S404_0x32_7");
        if (item7 != null) {
            item7.setValue(String.valueOf(getFrame()[15]));
        }
        updateDriveDataActivity(null);
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x48Data() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            arrayList.add(new TireUpdateEntity(i, 0, new String[]{getFrame()[i + 2] + " °C", com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(getFrame()[i + 4 + 2] * 0.1d, 10) + " Bar"}));
        }
        GeneralTireData.dataList = arrayList;
        GeneralTireData.isHaveSpareTire = false;
        updateTirePressureActivity(null);
    }

    private final void set0x21Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = 2;
        int i2 = getFrame()[2];
        if (i2 == 1) {
            i = 1;
        } else if (i2 == 2) {
            i = 21;
        } else if (i2 == 3) {
            i = 20;
        } else if (i2 == 9) {
            i = 3;
        } else if (i2 == 32) {
            i = 128;
        } else if (i2 == 43) {
            i = 52;
        } else if (i2 != 51) {
            i = i2 != 62 ? i2 != 69 ? i2 != 70 ? 0 : 8 : 7 : 49;
        }
        realKeyLongClick1(mContext, i, getFrame()[3]);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x11Data() {
        /*
            r9 = this;
            android.content.Context r0 = com.hzbhd.canbus.util.InitUtilsKt.getMContext()
            int[] r1 = r9.getFrame()
            r2 = 4
            r1 = r1[r2]
            r3 = 9
            r4 = 5
            r5 = 2
            r6 = 0
            r7 = 8
            if (r1 == 0) goto L3b
            r8 = 1
            if (r1 == r8) goto L39
            if (r1 == r5) goto L37
            if (r1 == r2) goto L35
            if (r1 == r4) goto L32
            r2 = 6
            if (r1 == r2) goto L2f
            if (r1 == r7) goto L2c
            if (r1 == r3) goto L29
            r2 = 11
            if (r1 == r2) goto L3c
            goto L3b
        L29:
            r5 = 20
            goto L3c
        L2c:
            r5 = 21
            goto L3c
        L2f:
            r5 = 15
            goto L3c
        L32:
            r5 = 14
            goto L3c
        L35:
            r5 = 3
            goto L3c
        L37:
            r5 = r7
            goto L3c
        L39:
            r5 = 7
            goto L3c
        L3b:
            r5 = r6
        L3c:
            int[] r1 = r9.getFrame()
            r1 = r1[r4]
            r9.realKeyLongClick1(r0, r5, r1)
            int[] r0 = r9.getFrame()
            r0 = r0[r3]
            byte r0 = (byte) r0
            int[] r1 = r9.getFrame()
            r1 = r1[r7]
            byte r1 = (byte) r1
            r2 = 540(0x21c, float:7.57E-43)
            r3 = 16
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r0, r1, r6, r2, r3)
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
            r0 = 0
            android.content.Context r1 = com.hzbhd.canbus.util.InitUtilsKt.getMContext()
            r9.updateParkUi(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._404.MsgMgr.set0x11Data():void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
    
        if (r9.equals("FM1") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
    
        r9 = kotlin.text.StringsKt.toDoubleOrNull(r10);
        kotlin.jvm.internal.Intrinsics.checkNotNull(r9);
        r9 = java.lang.String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(r9.doubleValue(), 10)).getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).getBytes(charset)");
        r10 = new byte[]{0, (byte) r8};
        r4 = 10 - r9.length;
        r8 = new byte[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0056, code lost:
    
        if (r6 >= r4) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0058, code lost:
    
        r8[r6] = 32;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x006c, code lost:
    
        if (r9.equals("AM2") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0075, code lost:
    
        if (r9.equals("AM1") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0078, code lost:
    
        r9 = r10.getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, "this as java.lang.String).getBytes(charset)");
        r10 = new byte[]{0, (byte) r8};
        r4 = 10 - r9.length;
        r8 = new byte[r4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008c, code lost:
    
        if (r6 >= r4) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008e, code lost:
    
        r8[r6] = 32;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return kotlin.collections.ArraysKt.plus(kotlin.collections.ArraysKt.plus(r10, r8), r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return kotlin.collections.ArraysKt.plus(kotlin.collections.ArraysKt.plus(r10, r8), r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
    
        if (r9.equals("FM3") != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
    
        if (r9.equals("FM2") == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final byte[] radioAscii(int r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            int r0 = r9.hashCode()
            r1 = 32
            r2 = 2
            java.lang.String r3 = "this as java.lang.String).getBytes(charset)"
            r4 = 10
            r5 = 1
            r6 = 0
            switch(r0) {
                case 64901: goto L6f;
                case 64902: goto L66;
                case 69706: goto L25;
                case 69707: goto L1b;
                case 69708: goto L12;
                default: goto L10;
            }
        L10:
            goto L9c
        L12:
            java.lang.String r0 = "FM3"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L9c
            goto L2f
        L1b:
            java.lang.String r0 = "FM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L2f
            goto L9c
        L25:
            java.lang.String r0 = "FM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L2f
            goto L9c
        L2f:
            java.lang.Double r9 = kotlin.text.StringsKt.toDoubleOrNull(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            double r9 = r9.doubleValue()
            double r9 = com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(r9, r4)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.nio.charset.Charset r10 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r9.getBytes(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r3)
            byte[] r10 = new byte[r2]
            r10[r6] = r6
            byte r8 = (byte) r8
            r10[r5] = r8
            int r8 = r9.length
            int r4 = r4 - r8
            byte[] r8 = new byte[r4]
        L56:
            if (r6 >= r4) goto L5d
            r8[r6] = r1
            int r6 = r6 + 1
            goto L56
        L5d:
            byte[] r8 = kotlin.collections.ArraysKt.plus(r10, r8)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r8, r9)
            goto L9e
        L66:
            java.lang.String r0 = "AM2"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L78
            goto L9c
        L6f:
            java.lang.String r0 = "AM1"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L78
            goto L9c
        L78:
            java.nio.charset.Charset r9 = kotlin.text.Charsets.US_ASCII
            byte[] r9 = r10.getBytes(r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r3)
            byte[] r10 = new byte[r2]
            r10[r6] = r6
            byte r8 = (byte) r8
            r10[r5] = r8
            int r8 = r9.length
            int r4 = r4 - r8
            byte[] r8 = new byte[r4]
        L8c:
            if (r6 >= r4) goto L93
            r8[r6] = r1
            int r6 = r6 + 1
            goto L8c
        L93:
            byte[] r8 = kotlin.collections.ArraysKt.plus(r10, r8)
            byte[] r8 = kotlin.collections.ArraysKt.plus(r8, r9)
            goto L9e
        L9c:
            byte[] r8 = new byte[r6]
        L9e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._404.MsgMgr.radioAscii(int, java.lang.String, java.lang.String):byte[]");
    }
}
