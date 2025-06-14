package com.hzbhd.canbus.car._363;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b#\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J&\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014H\u0016J\"\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001d\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001e\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010\u001f\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u001c\u0010 \u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\u0019H\u0016JÄ\u0001\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00142\b\u00100\u001a\u0004\u0018\u00010\u00142\b\u00101\u001a\u0004\u0018\u00010\u00142\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u0002032\b\u00108\u001a\u0004\u0018\u00010\u00142\b\u00109\u001a\u0004\u0018\u00010\u00142\b\u0010:\u001a\u0004\u0018\u00010\u00142\u0006\u0010;\u001a\u00020\u001bH\u0016J6\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020'2\b\u0010>\u001a\u0004\u0018\u00010\u00142\b\u0010?\u001a\u0004\u0018\u00010\u00142\b\u0010@\u001a\u0004\u0018\u00010\u00142\u0006\u0010A\u001a\u00020'H\u0016J\u0010\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020'H\u0002J\u0016\u0010D\u001a\u00020\u00102\u0006\u0010C\u001a\u00020'2\u0006\u0010E\u001a\u00020\u0019J\u001a\u0010F\u001a\u00020\u00102\u0006\u0010G\u001a\u00020'2\b\u0010H\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010I\u001a\u00020\u0010H\u0002J\b\u0010J\u001a\u00020\u0010H\u0002J\b\u0010K\u001a\u00020\u0010H\u0002J\b\u0010L\u001a\u00020\u0010H\u0002J\b\u0010M\u001a\u00020\u0010H\u0002J\b\u0010N\u001a\u00020\u0010H\u0002J\u0010\u0010O\u001a\u00020\u00102\u0006\u0010P\u001a\u00020\u0019H\u0002J\u0010\u0010Q\u001a\u00020\u00102\u0006\u0010R\u001a\u00020\u001bH\u0016J\u0098\u0001\u0010S\u001a\u00020\u00102\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\b\u0010,\u001a\u0004\u0018\u00010\u00142\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\b\u0010/\u001a\u0004\u0018\u00010\u00142\b\u00100\u001a\u0004\u0018\u00010\u00142\b\u00101\u001a\u0004\u0018\u00010\u00142\u0006\u00102\u001a\u00020'2\u0006\u0010T\u001a\u00020$2\u0006\u00106\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020'H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006V"}, d2 = {"Lcom/hzbhd/canbus/car/_363/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "auxInInfoChange", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, "", LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaSourceData", "d0", "sendPhoneNumber", "d3t26", "sendTextData", "comId", "text", "set0x11Data", "set0x12Data", "set0x26Data", "set0x32Data", "set0x41Data", "set0x87Data", "set0xF0Data", "bytes", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;

    private final void set0x26Data() {
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
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._363.UiMgr");
        UiMgr uiMgr = (UiMgr) canUiMgr;
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), 0});
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
        if (i == 38) {
            set0x26Data();
            return;
        }
        if (i == 50) {
            set0x32Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
        } else if (i == 135) {
            set0x87Data();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Data(canbusInfo);
        }
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void sendMediaSourceData(int d0) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, MsgMgrKt.restrict$default(new byte[]{(byte) d0}, 13, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendMediaSourceData(0);
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
            sendMediaSourceData(i);
        }
    }

    private final void sendTextData(int comId, byte[] text) {
        if (text == null) {
            return;
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) comId}, MsgMgrKt.restrict$default(text, 32, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        byte[] bytes;
        sendMediaSourceData(stoagePath == 9 ? 14 : 13);
        byte[] bytes2 = null;
        if (songTitle != null) {
            bytes = songTitle.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        } else {
            bytes = null;
        }
        sendTextData(146, bytes);
        if (songArtist != null) {
            bytes2 = songArtist.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        }
        sendTextData(148, bytes2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        sendMediaSourceData(stoagePath == 9 ? 14 : 13);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        byte[] bytes;
        sendMediaSourceData(133);
        byte[] bytes2 = null;
        if (title != null) {
            bytes = title.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        } else {
            bytes = null;
        }
        sendTextData(146, bytes);
        if (artist != null) {
            bytes2 = artist.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        }
        sendTextData(148, bytes2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaSourceData(12);
    }

    public final void sendPhoneNumber(int d0, byte[] d3t26) {
        Intrinsics.checkNotNullParameter(d3t26, "d3t26");
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51, (byte) d0, 0, 0}, d3t26));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        if (phoneNumber == null) {
            return;
        }
        sendPhoneNumber(1, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        if (phoneNumber == null) {
            return;
        }
        sendPhoneNumber(2, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        if (phoneNumber == null) {
            return;
        }
        sendPhoneNumber(4, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        if (phoneNumber == null) {
            return;
        }
        sendPhoneNumber(0, phoneNumber);
    }

    private final void set0x87Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("_340_automatic_folding");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[4]) ? 1 : 0));
        }
        updateSettingActivity(null);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(4, DataHandleUtils.rangeNumber(getFrame()[2], 2), getFrame()[3], getFrame()[4], DataHandleUtils.rangeNumber(getFrame()[5], 2));
        RadarInfoUtil.setFrontRadarLocationData(2, getFrame()[6], 0, 0, getFrame()[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    private final void set0x32Data() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            item.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) + " rpm");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            item2.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) + " km/h");
        }
        updateDriveDataActivity(null);
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
        switch (getFrame()[4]) {
            case 0:
                realKeyLongClick1(getContext(), 0, getFrame()[5]);
                break;
            case 1:
                realKeyLongClick1(getContext(), 7, getFrame()[5]);
                break;
            case 2:
                realKeyLongClick1(getContext(), 8, getFrame()[5]);
                break;
            case 3:
                realKeyLongClick1(getContext(), 3, getFrame()[5]);
                break;
            case 4:
                realKeyLongClick1(getContext(), 3, getFrame()[5]);
                break;
            case 5:
                realKeyLongClick1(getContext(), 14, getFrame()[5]);
                break;
            case 6:
                realKeyLongClick1(getContext(), 15, getFrame()[5]);
                break;
            case 7:
                realKeyLongClick1(getContext(), 0, getFrame()[5]);
                break;
            case 8:
                realKeyLongClick1(getContext(), 45, getFrame()[5]);
                break;
            case 9:
                realKeyLongClick1(getContext(), 46, getFrame()[5]);
                break;
            case 10:
                realKeyLongClick1(getContext(), 2, getFrame()[5]);
                break;
        }
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, getContext());
    }
}
