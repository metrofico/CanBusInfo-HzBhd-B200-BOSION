package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car._18.P360ButtonWindow;
import com.hzbhd.canbus.car._350.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\t\n\u0002\b0\u0018\u0000 ²\u00012\u00020\u0001:\u0002²\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010B\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010D\u001a\u00020CH\u0002J\b\u0010E\u001a\u00020CH\u0016J&\u0010F\u001a\u00020C2\b\u00102\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\"\u0010G\u001a\u00020C2\b\u0010H\u001a\u0004\u0018\u00010 2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\rH\u0016JT\u0010K\u001a\u00020C2\u0006\u0010L\u001a\u00020:2\b\u0010H\u001a\u0004\u0018\u00010 2\u0006\u0010M\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\r2\u0006\u0010O\u001a\u00020:2\u0006\u0010P\u001a\u00020:2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\u0010\u0010S\u001a\u00020C2\u0006\u0010T\u001a\u00020:H\u0002J\u001c\u0010U\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010V\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010W\u001a\u00020C2\u0006\u0010X\u001a\u00020:2\u0006\u0010Y\u001a\u00020\rH\u0016Jp\u0010Z\u001a\u00020C2\u0006\u0010[\u001a\u00020:2\u0006\u0010\\\u001a\u00020:2\u0006\u0010]\u001a\u00020:2\u0006\u0010^\u001a\u00020:2\u0006\u0010_\u001a\u00020:2\u0006\u0010`\u001a\u00020:2\u0006\u0010a\u001a\u00020:2\u0006\u0010b\u001a\u00020:2\u0006\u0010c\u001a\u00020:2\u0006\u0010d\u001a\u00020\r2\u0006\u0010e\u001a\u00020\r2\u0006\u0010f\u001a\u00020\r2\u0006\u0010g\u001a\u00020:H\u0016J\u0010\u0010h\u001a\u00020C2\u0006\u0010i\u001a\u00020:H\u0002J\b\u0010j\u001a\u00020CH\u0002J\b\u0010k\u001a\u00020CH\u0002J\u0012\u0010l\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010m\u001a\u00020C2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010n\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\u0010\u0010p\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\u0010\u0010q\u001a\u00020\r2\u0006\u0010o\u001a\u00020\u0011H\u0002J\b\u0010r\u001a\u00020CH\u0002J\b\u0010s\u001a\u00020CH\u0002JÑ\u0001\u0010t\u001a\u00020C2\u0006\u0010u\u001a\u00020\u001d2\u0006\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020:2\u0006\u0010x\u001a\u00020:2\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010z\u001a\u00020\u001d2\u0006\u0010{\u001a\u00020\u001d2\u0006\u0010|\u001a\u00020\u001d2\u0006\u0010}\u001a\u00020\u001d2\u0006\u0010~\u001a\u00020\u001d2\b\u0010\u007f\u001a\u0004\u0018\u00010\u00042\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00042\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\u001d2\u0007\u0010\u0085\u0001\u001a\u00020:2\u0007\u0010\u0086\u0001\u001a\u00020\r2\b\u0010\u0087\u0001\u001a\u00030\u0083\u00012\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u008b\u0001\u001a\u00020\rH\u0016J\t\u0010\u008c\u0001\u001a\u00020CH\u0002J\t\u0010\u008d\u0001\u001a\u00020CH\u0002J\t\u0010\u008e\u0001\u001a\u00020CH\u0002J<\u0010\u008f\u0001\u001a\u00020C2\u0007\u0010\u0090\u0001\u001a\u00020:2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u0094\u0001\u001a\u00020:H\u0016J\u001b\u0010\u0095\u0001\u001a\u00020 2\u0007\u0010\u0096\u0001\u001a\u00020 2\u0007\u0010\u0097\u0001\u001a\u00020:H\u0002J\u0012\u0010\u0098\u0001\u001a\u00020C2\u0007\u0010\u0099\u0001\u001a\u00020:H\u0002J\u001c\u0010\u009a\u0001\u001a\u00020C2\u0007\u0010\u009b\u0001\u001a\u00020:2\b\u0010H\u001a\u0004\u0018\u00010 H\u0002J\t\u0010\u009c\u0001\u001a\u00020CH\u0002J\t\u0010\u009d\u0001\u001a\u00020CH\u0002J\t\u0010\u009e\u0001\u001a\u00020CH\u0002J\t\u0010\u009f\u0001\u001a\u00020CH\u0002J\t\u0010 \u0001\u001a\u00020CH\u0002J\t\u0010¡\u0001\u001a\u00020CH\u0002J\t\u0010¢\u0001\u001a\u00020CH\u0002J\t\u0010£\u0001\u001a\u00020CH\u0002J\u0011\u0010¤\u0001\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010¥\u0001\u001a\u00020C2\u0007\u0010\u009b\u0001\u001a\u00020:H\u0002J\u0012\u0010¦\u0001\u001a\u00020C2\u0007\u0010§\u0001\u001a\u00020\rH\u0016J\t\u0010¨\u0001\u001a\u00020CH\u0002J\"\u0010©\u0001\u001a\u00020C2\u0007\u0010ª\u0001\u001a\u00020:2\u0007\u0010«\u0001\u001a\u00020:2\u0007\u0010¬\u0001\u001a\u00020:J\t\u0010\u00ad\u0001\u001a\u00020CH\u0002J\u0011\u0010®\u0001\u001a\u00020C2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001b\u0010¯\u0001\u001a\u00020C2\u0007\u0010°\u0001\u001a\u00020:2\u0007\u0010±\u0001\u001a\u00020:H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R\u001a\u0010$\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u001a\u0010'\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R2\u0010.\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u0003000/j\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u000300`1X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0012\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010<\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010=\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010>\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010?\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0012\u0010@\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0004\n\u0002\u0010;R\u0010\u0010A\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006³\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_18/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", LcdInfoShare.MediaInfo.album, "", LcdInfoShare.MediaInfo.artist, "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "first0x31Data", "", "first0x37Data", "first0x82Data", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "isInit", "()Z", "setInit", "(Z)V", "lastAlbum", "lastArtist", "lastKnobValue", "", "lastTitle", "lastX91", "", "mAirData0x31", "getMAirData0x31", "setMAirData0x31", "mAirData0x37", "getMAirData0x37", "setMAirData0x37", "mAirData0x82", "getMAirData0x82", "setMAirData0x82", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getMOriginalCarDevicePageUiSet", "()Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "mSettingItemsIndex", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet$ListBean$ItemListBean;", "Lkotlin/collections/HashMap;", LcdInfoShare.MediaInfo.title, "uiMgr", "Lcom/hzbhd/canbus/car/_18/UiMgr;", "getUiMgr", "()Lcom/hzbhd/canbus/car/_18/UiMgr;", "setUiMgr", "(Lcom/hzbhd/canbus/car/_18/UiMgr;)V", "x11D0", "", "Ljava/lang/Integer;", "x11D2", "x11D4", "x11D6", "x11D7", "x21D0", "x91", "afterServiceNormalSetting", "", MainAction.AMPLIFIER, "auxInInfoChange", "btMusicId3InfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "isAudioTransferTowardsAG", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "button", "d2", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "door", "d4", "horizontalAir", "hybrid", "initCommand", "initRestValue", "isAirNoDataChange0x31", "mCanBusInfoInt", "isAirNoDataChange0x37", "isAirNoDataChange0x82", "language", "media", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panel", "panorama", "radar", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "restrict", "param", "max", "selectCarMode", "currentCanDifferentId", "sendPhoneNumber", "d0", "sendSrcInfo", "sendText", "set0x17Data", "set0x86Data", "setCarInfoData0", "setCarInfoData1", "setCarInfoData2", "setCarModelData", "settings", "signal", "sourceSwitchNoMediaInfoChange", "isPowerOff", "tyre", "updateSettings", "leftListIndex", "rightListIndex", "value", "verticalAir", "verticalAirSupplement", "wheel", "d6", "d7", "CONST", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_RANGE_MAX = 7;
    private String album;
    private String artist;
    public Context context;
    private boolean first0x31Data;
    private boolean first0x37Data;
    private boolean first0x82Data;
    public int[] frame;
    private boolean isInit;
    private String lastAlbum;
    private String lastArtist;
    private byte lastKnobValue;
    private String lastTitle;
    private byte[] lastX91;
    private int[] mAirData0x31;
    private int[] mAirData0x37;
    private int[] mAirData0x82;
    private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private final HashMap<String, SettingPageUiSet.ListBean.ItemListBean<?>> mSettingItemsIndex = new HashMap<>();
    private String title;
    public UiMgr uiMgr;
    private Integer x11D0;
    private Integer x11D2;
    private Integer x11D4;
    private Integer x11D6;
    private Integer x11D7;
    private Integer x21D0;
    private byte[] x91;

    public MsgMgr() {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(originalCarDevicePageUiSet, "getCanUiMgr(BaseUtil.con…geUiSet(BaseUtil.context)");
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
        this.first0x37Data = true;
        this.first0x82Data = true;
        this.first0x31Data = true;
        this.mAirData0x31 = new int[30];
        this.mAirData0x82 = new int[30];
        this.mAirData0x37 = new int[30];
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

    public final UiMgr getUiMgr() {
        UiMgr uiMgr = this.uiMgr;
        if (uiMgr != null) {
            return uiMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("uiMgr");
        return null;
    }

    public final void setUiMgr(UiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(uiMgr, "<set-?>");
        this.uiMgr = uiMgr;
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
        bArr[11] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        byte[] bArr = new byte[13];
        for (int i = 0; i < 13; i++) {
            bArr[i] = 0;
        }
        this.x91 = bArr;
        sendSrcInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        byte[] bArr = {NfDef.AVRCP_EVENT_ID_UIDS_CHANGED};
        byte[] bArr2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        byte[] bArr = {32, (byte) ((volValue / 10) + 48), (byte) ((volValue % 10) + 48)};
        byte[] bArr2 = new byte[10];
        for (int i = 0; i < 10; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001e, code lost:
    
        if (r17.equals("FM2") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0028, code lost:
    
        if (r17.equals("FM1") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        r0 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0034, code lost:
    
        if (r17.equals("AM2") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
    
        if (r17.equals("AM1") == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
    
        r0 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        switch(r16) {
            case 1: goto L32;
            case 2: goto L31;
            case 3: goto L30;
            case 4: goto L29;
            case 5: goto L28;
            case 6: goto L27;
            default: goto L26;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0048, code lost:
    
        r4 = 48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004a, code lost:
    
        r4 = 54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004d, code lost:
    
        r4 = 53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0050, code lost:
    
        r4 = 52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0053, code lost:
    
        r4 = 51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        r4 = 50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
    
        r4 = 49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0065, code lost:
    
        if (r0 == 1) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0067, code lost:
    
        if (r0 == 2) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
    
        if (r0 == 3) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
    
        if (r0 == 4) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006e, code lost:
    
        if (r0 == 5) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0071, code lost:
    
        r2 = java.lang.String.valueOf(r18).getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "this as java.lang.String).getBytes(charset)");
        r10 = 10 - r2.length;
        r6 = new byte[r10];
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0083, code lost:
    
        if (r11 >= r10) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0085, code lost:
    
        r6[r11] = 32;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008a, code lost:
    
        r2 = kotlin.collections.ArraysKt.plus(r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008f, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNull(r18);
        r2 = kotlin.text.StringsKt.toDoubleOrNull(r18);
        kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
        r13 = 10;
        r2 = java.lang.String.valueOf(java.lang.Math.rint(r2.doubleValue() * r13) / r13).getBytes(kotlin.text.Charsets.US_ASCII);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "this as java.lang.String).getBytes(charset)");
        r10 = 10 - r2.length;
        r6 = new byte[r10];
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b6, code lost:
    
        if (r11 >= r10) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b8, code lost:
    
        r6[r11] = 32;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00bd, code lost:
    
        r2 = kotlin.collections.ArraysKt.plus(r6, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c1, code lost:
    
        r15.x91 = kotlin.collections.ArraysKt.plus(new byte[]{r0, 48, (byte) r4}, r2);
        sendSrcInfo();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        if (r17.equals("FM3") != false) goto L16;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, int r20) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._18.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        byte[] bArr = {10};
        byte[] bArr2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
        sendPhoneNumber(callStatus != 1 ? callStatus != 2 ? callStatus != 4 ? isHfpConnected ? 7 : 6 : 3 : 2 : 1, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        byte[] bArr = {10};
        byte[] bArr2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
        sendPhoneNumber(0, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        byte[] bArr = new byte[1];
        bArr[0] = stoagePath == 8 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        byte[] bArr2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
        this.title = songTitle;
        this.album = songAlbum;
        this.artist = songArtist;
        sendText();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        byte[] bArr = {-123};
        byte[] bArr2 = new byte[12];
        for (int i = 0; i < 12; i++) {
            bArr2[i] = 0;
        }
        this.x91 = ArraysKt.plus(bArr, bArr2);
        sendSrcInfo();
        this.title = title;
        this.artist = artist;
        this.album = album;
        sendText();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initRestValue(context);
        if (getCurrentCanDifferentId() == 28 || getCurrentCanDifferentId() == 29 || getCurrentCanDifferentId() == 30) {
            new P360ButtonWindow(context, new P360ButtonWindow.PanoramaListener() { // from class: com.hzbhd.canbus.car._18.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._18.P360ButtonWindow.PanoramaListener
                public final void clickEvent() {
                    MsgMgr.m278initCommand$lambda2(this.f$0);
                }
            }).show();
        }
        selectCarMode(getCurrentCanDifferentId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initCommand$lambda-2, reason: not valid java name */
    public static final void m278initCommand$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getUiMgr().sendPanoramaCmd(6);
    }

    private final void selectCarMode(int currentCanDifferentId) {
        if (currentCanDifferentId != 32) {
            switch (currentCanDifferentId) {
                case 1:
                    getUiMgr().sendCarModelData(3);
                    break;
                case 2:
                    getUiMgr().sendCarModelData(4);
                    break;
                case 3:
                    getUiMgr().sendCarModelData(27);
                    break;
                case 4:
                    getUiMgr().sendCarModelData(112);
                    break;
                case 5:
                    getUiMgr().sendCarModelData(2);
                    break;
                case 6:
                    getUiMgr().sendCarModelData(24);
                    break;
                case 7:
                    getUiMgr().sendCarModelData(12);
                    break;
                case 8:
                    getUiMgr().sendCarModelData(20);
                    break;
                case 9:
                    getUiMgr().sendCarModelData(17);
                    break;
                case 10:
                    getUiMgr().sendCarModelData(112);
                    break;
                case 11:
                    getUiMgr().sendCarModelData(112);
                    break;
                case 12:
                    getUiMgr().sendCarModelData(112);
                    break;
                case 13:
                    getUiMgr().sendCarModelData(25);
                    break;
                case 14:
                    getUiMgr().sendCarModelData(26);
                    break;
                case 15:
                    getUiMgr().sendCarModelData(18);
                    break;
                default:
                    getUiMgr().sendCarModelData((currentCanDifferentId + 131) - 128);
                    break;
            }
        }
        getUiMgr().sendCarModelData(112);
    }

    /* renamed from: isInit, reason: from getter */
    public final boolean getIsInit() {
        return this.isInit;
    }

    public final void setInit(boolean z) {
        this.isInit = z;
    }

    private final void initRestValue(Context context) {
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        ArrayList arrayList = new ArrayList();
        SettingUpdateEntity valueStr = new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_1"), context != null ? context.getString(R.string._18_reset_6) : null).setValueStr(true);
        Intrinsics.checkNotNullExpressionValue(valueStr, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
        arrayList.add(valueStr);
        SettingUpdateEntity valueStr2 = new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_2"), context != null ? context.getString(R.string._18_reset_6) : null).setValueStr(true);
        Intrinsics.checkNotNullExpressionValue(valueStr2, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
        arrayList.add(valueStr2);
        SettingUpdateEntity valueStr3 = new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_3"), context != null ? context.getString(R.string._18_reset_6) : null).setValueStr(true);
        Intrinsics.checkNotNullExpressionValue(valueStr3, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
        arrayList.add(valueStr3);
        SettingUpdateEntity valueStr4 = new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_4"), context != null ? context.getString(R.string._18_reset_6) : null).setValueStr(true);
        Intrinsics.checkNotNullExpressionValue(valueStr4, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
        arrayList.add(valueStr4);
        SettingUpdateEntity valueStr5 = new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_reset"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_reset", "_18_reset_5"), context != null ? context.getString(R.string._18_reset_6) : null).setValueStr(true);
        Intrinsics.checkNotNullExpressionValue(valueStr5, "SettingUpdateEntity<Any>…set_6)).setValueStr(true)");
        arrayList.add(valueStr5);
        updateGeneralSettingData(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._18.UiMgr");
        setUiMgr((UiMgr) canUiMgr);
        GeneralDoorData.isShowHandBrake = true;
        InitUtilsKt.initSettingItemsIndexHashMap(context, getUiMgr(), this.mSettingItemsIndex);
        InitUtilsKt.initDrivingItemsIndexHashMap(context, getUiMgr(), InitUtilsKt.getMDrivingItemIndex());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null || canbusInfo == null) {
            return;
        }
        try {
            setContext(context);
            int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
            Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
            setFrame(byteArrayToIntArray);
            switch (getFrame()[1]) {
                case 17:
                    signal(getFrame()[2]);
                    button(getFrame()[4]);
                    door(getFrame()[6]);
                    wheel(getFrame()[8], getFrame()[9]);
                    break;
                case 19:
                    setCarInfoData0();
                    break;
                case 22:
                    setCarInfoData1();
                    break;
                case 23:
                    set0x17Data();
                    break;
                case 31:
                    hybrid();
                    break;
                case 33:
                    panel();
                    break;
                case 34:
                    byte b = canbusInfo[3];
                    byte b2 = this.lastKnobValue;
                    int iAbs = Math.abs(b - b2);
                    int i = getFrame()[2];
                    if (i != 1) {
                        if (i == 2) {
                            if (b > b2) {
                                DataHandleUtils.knob(context, 46, iAbs);
                            } else if (b < b2) {
                                DataHandleUtils.knob(context, 45, iAbs);
                            }
                        }
                    } else if (b > b2) {
                        DataHandleUtils.knob(context, 7, iAbs);
                    } else if (b < b2) {
                        DataHandleUtils.knob(context, 8, iAbs);
                    }
                    this.lastKnobValue = canbusInfo[3];
                    break;
                case 38:
                    setCarModelData();
                    break;
                case 49:
                    verticalAir();
                    break;
                case 50:
                    setCarInfoData2();
                    break;
                case 55:
                    verticalAirSupplement(getFrame());
                    break;
                case 65:
                    radar();
                    break;
                case 72:
                    tyre();
                    break;
                case 98:
                    settings(getFrame());
                    break;
                case 130:
                    horizontalAir();
                    break;
                case HotKeyConstant.K_SLEEP /* 134 */:
                    set0x86Data();
                    break;
                case MpegConstantsDef.MPEG_DIVX_REG_STATUS_INT /* 154 */:
                    language();
                    break;
                case MpegConstantsDef.MPEG_PASSWORD_CFM /* 166 */:
                    amplifier();
                    break;
                case 224:
                    media();
                    break;
                case 232:
                    panorama();
                    break;
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private final void set0x17Data() {
        String str;
        if (getFrame().length < 63) {
            return;
        }
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_3");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_4");
        if (item4 != null) {
            item4.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_5");
        if (item5 != null) {
            item5.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_6");
        if (item6 != null) {
            item6.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[13]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_7");
        if (item7 != null) {
            item7.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[14], getFrame()[15]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_8");
        if (item8 != null) {
            item8.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[16], getFrame()[17]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item9 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_9");
        if (item9 != null) {
            item9.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[18], getFrame()[19]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item10 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_10");
        if (item10 != null) {
            item10.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[20], getFrame()[21]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item11 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_11");
        if (item11 != null) {
            item11.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[22], getFrame()[23]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item12 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_12");
        if (item12 != null) {
            item12.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[24], getFrame()[25]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item13 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_13");
        if (item13 != null) {
            item13.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[26], getFrame()[27]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item14 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_14");
        if (item14 != null) {
            item14.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[28], getFrame()[29]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item15 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_15");
        if (item15 != null) {
            item15.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[30], getFrame()[31]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item16 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_16");
        if (item16 != null) {
            item16.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[32], getFrame()[33]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item17 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_17");
        if (item17 != null) {
            item17.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[34], getFrame()[35]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item18 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_18");
        if (item18 != null) {
            item18.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[36], getFrame()[37]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item19 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_19");
        if (item19 != null) {
            item19.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[38], getFrame()[39]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item20 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_20");
        if (item20 != null) {
            item20.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[40], getFrame()[41]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item21 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_21");
        if (item21 != null) {
            item21.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[42], getFrame()[43]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item22 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_22");
        if (item22 != null) {
            item22.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[44], getFrame()[45]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item23 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_23");
        if (item23 != null) {
            item23.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[46], getFrame()[47]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item24 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_24");
        if (item24 != null) {
            item24.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[48], getFrame()[49]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item25 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_25");
        if (item25 != null) {
            item25.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[50], getFrame()[51]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item26 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_26");
        if (item26 != null) {
            item26.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[52], getFrame()[53]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item27 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_27");
        if (item27 != null) {
            item27.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[54], getFrame()[55]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item28 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_28");
        if (item28 != null) {
            item28.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[56], getFrame()[57]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item29 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_29");
        if (item29 != null) {
            item29.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[58], getFrame()[59]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item30 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_30");
        if (item30 != null) {
            item30.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[60], getFrame()[61]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item31 = InitUtilsKt.getMDrivingItemIndex().get("S55_x17_31");
        if (item31 != null) {
            int i = getFrame()[62];
            if (i == 0) {
                str = "MPG";
            } else if (i == 1) {
                str = "Km/L";
            } else if (i != 2) {
                return;
            } else {
                str = "L/100Km";
            }
            item31.setValue(str);
        }
        updateDriveDataActivity(null);
    }

    public final OriginalCarDevicePageUiSet getMOriginalCarDevicePageUiSet() {
        return this.mOriginalCarDevicePageUiSet;
    }

    private final void set0x86Data() {
        String str;
        int i = getFrame()[8];
        GeneralOriginalCarDeviceData.cdStatus = i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? "More" : "Scan" : "Disc Scan" : "Repeat" : "Disc Repeat" : "Random" : "Disc Random";
        switch (getFrame()[9]) {
            case 2:
                str = "Play";
                break;
            case 3:
                str = "Fast";
                break;
            case 4:
                str = "User search";
                break;
            case 5:
                str = "Internal search";
                break;
            case 6:
                str = "Stop";
                break;
            case 7:
                str = "Rom read";
                break;
            case 8:
                str = "Rom search";
                break;
            case 9:
                str = "Retrieving";
                break;
            case 10:
                str = "Disc changing(User)";
                break;
            case 11:
                str = "Disc changing(Internal)";
                break;
            case 12:
                str = "Eject";
                break;
            default:
                str = "Other";
                break;
        }
        GeneralOriginalCarDeviceData.runningState = str;
        List<OriginalCarDevicePageUiSet.Item> items = this.mOriginalCarDevicePageUiSet.getItems();
        items.clear();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 4);
        boolean z = false;
        if (1 <= intFromByteWithBit && intFromByteWithBit < 7) {
            z = true;
        }
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_disc", z ? "Disk " + intFromByteWithBit : "Single"));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_track", String.valueOf(getFrame()[5])));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_play_time", getFrame()[6] + " : " + getFrame()[7]));
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void setCarModelData() {
        try {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemsIndex.get("S18_Car_0");
            if (itemListBean != null) {
                itemListBean.setValue(itemListBean.getValueSrnArray().get(getFrame()[3] - 128));
            }
            updateSettingActivity(null);
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private final void setCarInfoData2() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateDriveDataActivity(null);
    }

    private final void setCarInfoData1() {
        String str;
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_3");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_4");
        if (item4 != null) {
            item4.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_5");
        if (item5 != null) {
            item5.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_6");
        if (item6 != null) {
            item6.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[13]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S18_OilConsumption_7");
        if (item7 != null) {
            int i = getFrame()[14];
            if (i == 0) {
                str = "MPG";
            } else if (i == 1) {
                str = "Km/L";
            } else if (i != 2) {
                return;
            } else {
                str = "L/100Km";
            }
            item7.setValue(str);
        }
        updateDriveDataActivity(null);
    }

    private final void setCarInfoData0() {
        String str;
        String str2;
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_6");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_7");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_8");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) / 10.0f));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_9");
        if (item4 != null) {
            item4.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9]) + " Min");
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_10");
        if (item5 != null) {
            item5.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) + " Km/H");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_11");
        if (item6 != null) {
            int i = getFrame()[12];
            if (i == 0) {
                str2 = "MPG";
            } else if (i == 1) {
                str2 = "Km/L";
            } else if (i != 2) {
                return;
            } else {
                str2 = "L/100Km";
            }
            item6.setValue(str2);
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_12");
        if (item7 != null) {
            int i2 = getFrame()[13];
            if (i2 == 0) {
                str = "Km";
            } else if (i2 != 1) {
                return;
            } else {
                str = "Mile";
            }
            item7.setValue(str);
        }
        updateDriveDataActivity(null);
    }

    private final void panel() {
        Integer num = this.x21D0;
        int i = getFrame()[2];
        if ((num == null || num.intValue() != i) && getFrame()[3] != 0) {
            int i2 = getFrame()[2];
            if (i2 == 1) {
                realKeyClick4(getContext(), HotKeyConstant.K_SLEEP);
            } else if (i2 == 2) {
                realKeyClick4(getContext(), 21);
            } else if (i2 == 3) {
                realKeyClick4(getContext(), 20);
            } else if (i2 == 6) {
                realKeyClick4(getContext(), 50);
            } else if (i2 == 16) {
                realKeyClick4(getContext(), 95);
            } else if (i2 == 18) {
                realKeyClick4(getContext(), 58);
            } else if (i2 == 36) {
                realKeyClick4(getContext(), 2);
            } else if (i2 == 40) {
                realKeyClick4(getContext(), HotKeyConstant.K_PHONE_ON_OFF);
            } else if (i2 == 57) {
                realKeyClick4(getContext(), 57);
            } else if (i2 == 59) {
                realKeyClick4(getContext(), 2);
            } else if (i2 == 75) {
                realKeyClick4(getContext(), 62);
            } else if (i2 == 97) {
                updateAirActivity(getContext(), 1001);
            } else if (i2 == 8) {
                realKeyClick4(getContext(), 141);
            } else if (i2 == 9) {
                realKeyClick4(getContext(), 3);
            } else if (i2 == 32) {
                realKeyClick4(getContext(), 128);
            } else if (i2 == 33) {
                realKeyClick4(getContext(), 39);
            } else if (i2 == 47) {
                realKeyClick4(getContext(), 151);
            } else if (i2 == 48) {
                realKeyClick4(getContext(), 68);
            } else if (i2 == 69) {
                realKeyClick4(getContext(), 7);
            } else if (i2 != 70) {
                switch (i2) {
                    case 42:
                        realKeyClick4(getContext(), 49);
                        break;
                    case 43:
                        realKeyClick4(getContext(), 52);
                        break;
                    case 44:
                        realKeyClick4(getContext(), 2);
                        break;
                    default:
                        switch (i2) {
                            case 51:
                                realKeyClick4(getContext(), 76);
                                break;
                            case 52:
                                realKeyClick4(getContext(), 14);
                                break;
                            case 53:
                                realKeyClick4(getContext(), 15);
                                break;
                        }
                }
            } else {
                realKeyClick4(getContext(), 8);
            }
        }
        this.x21D0 = Integer.valueOf(getFrame()[2]);
    }

    private final void language() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemsIndex.get("language-settings");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(getFrame()[3] - 1));
        }
        updateSettingActivity(null);
    }

    private final void media() {
        int i = getFrame()[2];
        if (i == 0) {
            enterNoSource();
            return;
        }
        if (i == 38) {
            realKeyClick4(getContext(), 130);
            return;
        }
        if (i != 39) {
            switch (i) {
                case 32:
                    realKeyClick4(getContext(), 77);
                    break;
                case 33:
                    realKeyClick4(getContext(), 76);
                    break;
                case 34:
                    realKeyClick4(getContext(), 59);
                    break;
                case 35:
                    realKeyClick4(getContext(), 140);
                    break;
            }
            return;
        }
        realKeyClick4(getContext(), 141);
    }

    private final void tyre() {
        int intFromByteWithBit = DataHandleUtils.getBoolBit7(getFrame()[2]) ? DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 6, 1) : 0;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new TireUpdateEntity(i, intFromByteWithBit, new String[]{(getFrame()[i + 4] + getFrame()[i + 9]) + "kPa"}));
        }
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private final void panorama() {
        forceReverse(getContext(), getFrame()[5] == 1);
    }

    private final void amplifier() {
        GeneralAmplifierData.volume = getFrame()[2];
        GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(getFrame()[3], 0, 14) - 7;
        GeneralAmplifierData.frontRear = MsgMgrKt.reverse$default(DataHandleUtils.rangeNumber(getFrame()[4], 0, 14), 0, 2, null) - 7;
        GeneralAmplifierData.bandBass = getFrame()[5];
        GeneralAmplifierData.bandMiddle = getFrame()[6];
        GeneralAmplifierData.bandTreble = getFrame()[7];
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemsIndex.get("S18xA61");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(getFrame()[8]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemsIndex.get("S18xA62");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[8]) ? 1 : 0));
        }
        updateAmplifierActivity(null);
        updateSettingActivity(null);
    }

    private final void radar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.setRearRadarLocationData(4, getFrame()[2], getFrame()[3], getFrame()[4], getFrame()[5]);
        RadarInfoUtil.setFrontRadarLocationData(4, getFrame()[6], getFrame()[7], getFrame()[7], getFrame()[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
    }

    private final void verticalAirSupplement(int[] frame) {
        String str;
        if (this.first0x37Data) {
            this.first0x37Data = false;
            this.mAirData0x37 = frame;
            return;
        }
        if (isAirNoDataChange0x37(frame)) {
            return;
        }
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = frame[2];
        if (i == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else {
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        }
        int i2 = frame[3];
        if (i2 == 0) {
            str = "--";
        } else if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "HIGH";
        } else {
            str = "LOW";
        }
        GeneralAirData.rear_right_temperature = str;
        GeneralAirData.rear_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 6, 2);
        GeneralAirData.rear_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 4, 2);
        GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 2, 2);
        GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(frame[4], 0, 2);
        updateAirActivity(getContext(), 1004);
        updateAirActivity(getContext(), 1003);
    }

    private final void horizontalAir() {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.first0x82Data) {
            this.first0x82Data = false;
            this.mAirData0x82 = getFrame();
            return;
        }
        if (isAirNoDataChange0x82(getFrame())) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit3(getFrame()[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit1(getFrame()[3]);
        if (DataHandleUtils.getBoolBit0(getFrame()[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
        } else if (GeneralAirData.in_out_cycle) {
            GeneralAirData.in_out_auto_cycle = 0;
        } else {
            GeneralAirData.in_out_auto_cycle = 1;
        }
        int i = getFrame()[4];
        String str5 = "HIGH";
        if (i == 0) {
            str = "--";
        } else if (i != 1) {
            str = i != 255 ? ((i * 0.5d) - 40) + " °C" : "HIGH";
        } else {
            str = "LOW";
        }
        GeneralAirData.front_left_temperature = str;
        int i2 = getFrame()[5];
        if (i2 == 0) {
            str2 = "--";
        } else if (i2 != 1) {
            str2 = i2 != 255 ? ((i2 * 0.5d) - 40) + " °C" : "HIGH";
        } else {
            str2 = "LOW";
        }
        GeneralAirData.front_right_temperature = str2;
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(getFrame()[6]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(getFrame()[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(getFrame()[6]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(getFrame()[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(getFrame()[6]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(getFrame()[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 4);
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_foot = false;
        int i3 = getFrame()[7];
        if (i3 == 1) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i3 == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (i3 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        GeneralAirData.rear_wind_level = getFrame()[8];
        int i4 = getFrame()[9];
        if (i4 == 0) {
            str3 = "--";
        } else if (i4 != 1) {
            str3 = i4 != 255 ? ((i4 * 0.5d) - 40) + " °C" : "HIGH";
        } else {
            str3 = "LOW";
        }
        GeneralAirData.rear_temperature = str3;
        int i5 = getFrame()[9];
        if (i5 == 0) {
            str4 = "--";
        } else if (i5 != 1) {
            str4 = i5 != 255 ? ((i5 * 0.5d) - 40) + " °C" : "HIGH";
        } else {
            str4 = "LOW";
        }
        GeneralAirData.rear_right_temperature = str4;
        int i6 = getFrame()[9];
        if (i6 == 0) {
            str5 = "--";
        } else if (i6 == 1) {
            str5 = "LOW";
        } else if (i6 != 255) {
            str5 = ((i6 * 0.5d) - 40) + " °C";
        }
        GeneralAirData.rear_left_temperature = str5;
        updateAirActivity(getContext(), 1004);
        updateAirActivity(getContext(), 1003);
    }

    private final void verticalAir() {
        String str;
        String str2;
        updateOutDoorTemp(getContext(), ((getFrame()[13] * 0.5d) - 40) + " °C");
        getFrame()[13] = 0;
        if (this.first0x31Data) {
            this.mAirData0x31 = getFrame();
            this.first0x31Data = false;
            return;
        }
        if (isAirNoDataChange0x31(getFrame())) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(getFrame()[3]);
        if (DataHandleUtils.getBoolBit3(getFrame()[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
        } else if (GeneralAirData.in_out_cycle) {
            GeneralAirData.in_out_auto_cycle = 0;
        } else {
            GeneralAirData.in_out_auto_cycle = 1;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit1(getFrame()[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(getFrame()[3]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 2);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_auto_wind = false;
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
        } else {
            switch (i) {
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_right_auto_wind = false;
        int i2 = getFrame()[6];
        if (i2 == 1) {
            GeneralAirData.front_right_auto_wind = true;
        } else if (i2 == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (i2 == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i2 == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i2) {
                case 12:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i3 = getFrame()[8];
        String str3 = "HIGH";
        if (i3 == 0) {
            str = "--";
        } else if (i3 != 254) {
            str = i3 != 255 ? (i3 * 0.5d) + " °C" : "HIGH";
        } else {
            str = "LOW";
        }
        GeneralAirData.front_left_temperature = str;
        int i4 = getFrame()[9];
        if (i4 == 0) {
            str2 = "--";
        } else if (i4 != 254) {
            str2 = i4 != 255 ? (i4 * 0.5d) + " °C" : "HIGH";
        } else {
            str2 = "LOW";
        }
        GeneralAirData.front_right_temperature = str2;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        int i5 = getFrame()[10];
        if (i5 == 1) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i5 == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (i5 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        GeneralAirData.rear_wind_level = getFrame()[11];
        int i6 = getFrame()[12];
        if (i6 == 0) {
            str3 = "--";
        } else if (i6 == 254) {
            str3 = "LOW";
        } else if (i6 != 255) {
            str3 = (i6 * 0.5d) + " °C";
        }
        GeneralAirData.rear_left_temperature = str3;
        updateAirActivity(getContext(), 1004);
        updateAirActivity(getContext(), 1003);
    }

    private final void hybrid() {
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 4);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(getFrame()[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(getFrame()[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(getFrame()[3]);
        updateHybirdActivity(null);
    }

    private final void signal(int d0) {
        Integer num = this.x11D0;
        if (num == null || num.intValue() != d0) {
            if (DataHandleUtils.getBoolBit7(d0)) {
                if (getVolume() != 0) {
                    realKeyLongClick2(getContext(), 3);
                }
            } else if (getVolume() == 0) {
                realKeyLongClick2(getContext(), 3);
            }
            getUiMgr().getParkPageUiSet(getContext()).setShowRadar(DataHandleUtils.getBoolBit5(d0));
            GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(d0);
        }
        this.x11D0 = Integer.valueOf(d0);
    }

    private final void button(int d2) {
        Integer num = this.x11D2;
        if ((num == null || num.intValue() != d2) && getFrame()[5] != 0) {
            switch (d2) {
                case 0:
                    realKeyClick4(getContext(), 0);
                    break;
                case 1:
                    realKeyClick4(getContext(), 7);
                    break;
                case 2:
                    realKeyClick4(getContext(), 8);
                    break;
                case 4:
                    realKeyClick4(getContext(), HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick4(getContext(), getCurrentCanDifferentId() == 6 ? HotKeyConstant.K_PHONE_ON_OFF : 14);
                    break;
                case 6:
                    realKeyClick4(getContext(), 15);
                    break;
                case 8:
                    realKeyClick4(getContext(), 21);
                    break;
                case 9:
                    realKeyClick4(getContext(), 20);
                    break;
                case 12:
                    realKeyClick4(getContext(), 2);
                    break;
                case 13:
                    realKeyClick4(getContext(), 45);
                    break;
                case 14:
                    realKeyClick4(getContext(), 46);
                    break;
                case 15:
                    realKeyClick4(getContext(), 49);
                    break;
                case 16:
                    realKeyClick4(getContext(), 50);
                    break;
            }
        }
        this.x11D2 = Integer.valueOf(d2);
    }

    private final void door(int d4) {
        Integer num = this.x11D4;
        if (num == null || num.intValue() != d4) {
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(d4);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(d4);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(d4);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(d4);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(d4);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(d4);
            updateDoorView(getContext());
        }
        this.x11D4 = Integer.valueOf(d4);
    }

    private final void wheel(int d6, int d7) {
        Integer num;
        Integer num2 = this.x11D6;
        if (num2 == null || num2.intValue() != d6 || (num = this.x11D7) == null || num.intValue() != d7) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) d7, (byte) d6, 0, 500, 16);
            updateParkUi(null, getContext());
        }
        this.x11D6 = Integer.valueOf(d6);
        this.x11D7 = Integer.valueOf(d7);
    }

    private final void settings(int[] frame) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "S18Title1"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "S18Title1", "_18_park_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "S18Title1"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "S18Title1", "_18_park_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[8], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[8], 4, 2))));
        int i = 1;
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[8], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(InitUtilsKt.getMContext(), "_18_other_settings"), getUiMgr().getSettingRightIndex(InitUtilsKt.getMContext(), "_18_other_settings", "_18_other_settings_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(frame[8], 2, 1))));
        updateGeneralSettingData(arrayList);
        try {
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = this.mSettingItemsIndex.get("S18_park_1");
            if (itemListBean != null) {
                itemListBean.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(frame[2]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = this.mSettingItemsIndex.get("S18_park_2");
            if (itemListBean2 != null) {
                itemListBean2.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[2], 4, 3) - 1);
                itemListBean2.setValue(String.valueOf(itemListBean2.getProgress() + 1));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = this.mSettingItemsIndex.get("S18_park_3");
            if (itemListBean3 != null) {
                itemListBean3.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[2], 2, 2));
                itemListBean3.setValue(String.valueOf(itemListBean3.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = this.mSettingItemsIndex.get("S18_park_4");
            if (itemListBean4 != null) {
                itemListBean4.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[2], 0, 2));
                itemListBean4.setValue(String.valueOf(itemListBean4.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = this.mSettingItemsIndex.get("S18_lock_1");
            if (itemListBean5 != null) {
                itemListBean5.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = this.mSettingItemsIndex.get("S18_lock_2");
            if (itemListBean6 != null) {
                itemListBean6.setValue(itemListBean6.getValueSrnArray().get(DataHandleUtils.getBoolBit5(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = this.mSettingItemsIndex.get("S18_lock_3");
            if (itemListBean7 != null) {
                itemListBean7.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = this.mSettingItemsIndex.get("S18_lock_4");
            if (itemListBean8 != null) {
                itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getBoolBit3(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = this.mSettingItemsIndex.get("S18_lock_5");
            if (itemListBean9 != null) {
                itemListBean9.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = this.mSettingItemsIndex.get("S18_lock_6");
            if (itemListBean10 != null) {
                itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = this.mSettingItemsIndex.get("S18_lock_7");
            if (itemListBean11 != null) {
                itemListBean11.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(frame[3]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = this.mSettingItemsIndex.get("S18_remote_1");
            if (itemListBean12 != null) {
                itemListBean12.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(frame[4]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = this.mSettingItemsIndex.get("S18_remote_2");
            if (itemListBean13 != null) {
                itemListBean13.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(frame[4]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = this.mSettingItemsIndex.get("S18_remote_3");
            if (itemListBean14 != null) {
                itemListBean14.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(frame[4]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = this.mSettingItemsIndex.get("S18_remote_4");
            if (itemListBean15 != null) {
                itemListBean15.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(frame[4]) ? 1 : 0));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = this.mSettingItemsIndex.get("S18_remote_5");
            if (itemListBean16 != null) {
                itemListBean16.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[4], 1, 3));
                itemListBean16.setValue(String.valueOf(itemListBean16.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = this.mSettingItemsIndex.get("S18_light_1");
            if (itemListBean17 != null) {
                if (!DataHandleUtils.getBoolBit7(frame[5])) {
                    i = 0;
                }
                itemListBean17.setValue(Integer.valueOf(i));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = this.mSettingItemsIndex.get("S18_light_2");
            if (itemListBean18 != null) {
                itemListBean18.setValue(itemListBean18.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(frame[5], 5, 2)));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = this.mSettingItemsIndex.get("S18_light_3");
            if (itemListBean19 != null) {
                itemListBean19.setValue(itemListBean19.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(frame[5], 3, 2)));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = this.mSettingItemsIndex.get("S18_light_4");
            if (itemListBean20 != null) {
                itemListBean20.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[5], 0, 3));
                itemListBean20.setValue(String.valueOf(itemListBean20.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = this.mSettingItemsIndex.get("S18_air_1");
            if (itemListBean21 != null) {
                itemListBean21.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[6], 5, 3));
                itemListBean21.setValue(String.valueOf(itemListBean21.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean22 = this.mSettingItemsIndex.get("S18_air_2");
            if (itemListBean22 != null) {
                itemListBean22.setProgress(DataHandleUtils.getIntFromByteWithBit(frame[6], 2, 3));
                itemListBean22.setValue(String.valueOf(itemListBean22.getProgress()));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean23 = this.mSettingItemsIndex.get("S18_air_3");
            if (itemListBean23 != null) {
                itemListBean23.setValue(itemListBean23.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(frame[6], 0, 2)));
            }
            SettingPageUiSet.ListBean.ItemListBean<?> itemListBean24 = this.mSettingItemsIndex.get("S18_air_4");
            if (itemListBean24 != null) {
                itemListBean24.setProgress(frame[7]);
                itemListBean24.setValue(String.valueOf(itemListBean24.getProgress()));
            }
        } catch (Exception e) {
            Log.e("18Msg", Log.getStackTraceString(e));
        }
        updateSettingActivity(null);
    }

    private final byte[] restrict(byte[] param, int max) {
        if (param.length > max) {
            return ArraysKt.copyOfRange(param, 0, max);
        }
        int length = max - param.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = 0;
        }
        return ArraysKt.plus(param, bArr);
    }

    private final void sendSrcInfo() {
        if (!Arrays.equals(this.x91, this.lastX91)) {
            byte[] bArr = {22, -111};
            byte[] bArr2 = this.x91;
            if (bArr2 == null) {
                return;
            } else {
                CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, bArr2));
            }
        }
        this.lastX91 = this.x91;
    }

    private final void sendText() {
        if (!Intrinsics.areEqual(this.lastTitle, this.title)) {
            byte[] bArr = {22, -110};
            String str = this.title;
            if (str == null) {
                str = "Unknown";
            }
            byte[] bytes = str.getBytes(Charsets.UTF_16LE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, restrict(bytes, 32)));
        }
        if (!Intrinsics.areEqual(this.lastAlbum, this.album)) {
            byte[] bArr2 = {22, -109};
            String str2 = this.album;
            if (str2 == null) {
                str2 = "Unknown";
            }
            byte[] bytes2 = str2.getBytes(Charsets.UTF_16LE);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            CanbusMsgSender.sendMsg(ArraysKt.plus(bArr2, restrict(bytes2, 32)));
        }
        if (!Intrinsics.areEqual(this.lastArtist, this.artist)) {
            byte[] bArr3 = {22, -108};
            String str3 = this.artist;
            byte[] bytes3 = (str3 != null ? str3 : "Unknown").getBytes(Charsets.UTF_16LE);
            Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
            CanbusMsgSender.sendMsg(ArraysKt.plus(bArr3, restrict(bytes3, 32)));
        }
        this.lastTitle = this.title;
        this.lastAlbum = this.album;
        this.lastArtist = this.artist;
    }

    private final void sendPhoneNumber(int d0, byte[] phoneNumber) {
        Intrinsics.checkNotNull(phoneNumber);
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -51, (byte) d0, 0, 0}, restrict(phoneNumber, 24)));
    }

    public final void updateSettings(int leftListIndex, int rightListIndex, int value) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(leftListIndex, rightListIndex, Integer.valueOf(value)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public final int[] getMAirData0x31() {
        return this.mAirData0x31;
    }

    public final void setMAirData0x31(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData0x31 = iArr;
    }

    private final boolean isAirNoDataChange0x31(int[] mCanBusInfoInt) {
        if (Arrays.equals(this.mAirData0x31, mCanBusInfoInt)) {
            return true;
        }
        int[] iArrCopyOf = Arrays.copyOf(mCanBusInfoInt, mCanBusInfoInt.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mAirData0x31 = iArrCopyOf;
        return false;
    }

    public final int[] getMAirData0x82() {
        return this.mAirData0x82;
    }

    public final void setMAirData0x82(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData0x82 = iArr;
    }

    private final boolean isAirNoDataChange0x82(int[] mCanBusInfoInt) {
        if (Arrays.equals(this.mAirData0x82, mCanBusInfoInt)) {
            return true;
        }
        int[] iArrCopyOf = Arrays.copyOf(mCanBusInfoInt, mCanBusInfoInt.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mAirData0x82 = iArrCopyOf;
        return false;
    }

    public final int[] getMAirData0x37() {
        return this.mAirData0x37;
    }

    public final void setMAirData0x37(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData0x37 = iArr;
    }

    private final boolean isAirNoDataChange0x37(int[] mCanBusInfoInt) {
        if (Arrays.equals(this.mAirData0x37, mCanBusInfoInt)) {
            return true;
        }
        int[] iArrCopyOf = Arrays.copyOf(mCanBusInfoInt, mCanBusInfoInt.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(mCanBusInfoInt, mCanBusInfoInt.size)");
        this.mAirData0x37 = iArrCopyOf;
        return false;
    }
}
