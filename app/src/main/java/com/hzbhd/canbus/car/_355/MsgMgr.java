package com.hzbhd.canbus.car._355;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0016JT\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u001fH\u0016J\u0018\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020!H\u0016Jp\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;H\u0016J\b\u0010>\u001a\u00020\u0019H\u0016JÄ\u0001\u0010?\u001a\u00020\u00192\u0006\u0010@\u001a\u0002002\u0006\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\u00102\u0006\u0010D\u001a\u0002002\u0006\u0010E\u001a\u0002002\u0006\u0010F\u001a\u0002002\u0006\u0010G\u001a\u0002002\u0006\u0010H\u001a\u0002002\u0006\u0010I\u001a\u0002002\b\u0010J\u001a\u0004\u0018\u00010;2\b\u0010K\u001a\u0004\u0018\u00010;2\b\u0010L\u001a\u0004\u0018\u00010;2\u0006\u0010M\u001a\u00020N2\u0006\u0010/\u001a\u0002002\u0006\u0010O\u001a\u00020\u00102\u0006\u00108\u001a\u00020!2\u0006\u0010P\u001a\u00020N2\b\u0010Q\u001a\u0004\u0018\u00010;2\b\u0010R\u001a\u0004\u0018\u00010;2\b\u0010S\u001a\u0004\u0018\u00010;2\u0006\u0010T\u001a\u00020!H\u0016J0\u0010U\u001a\u00020\u00192\u0006\u0010V\u001a\u00020\u00102\u0006\u0010W\u001a\u00020;2\u0006\u0010X\u001a\u00020;2\u0006\u0010Y\u001a\u00020;2\u0006\u0010Z\u001a\u00020\u0010H\u0016J\u000e\u0010[\u001a\u00020\u001f2\u0006\u0010\\\u001a\u00020\u001fJ\u001e\u0010]\u001a\u00020\u00192\u0006\u0010^\u001a\u00020\u00102\u0006\u0010_\u001a\u00020\u00102\u0006\u0010`\u001a\u00020\u001fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006a"}, d2 = {"Lcom/hzbhd/canbus/car/_355/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "vol", "", "getVol", "()I", "setVol", "(I)V", "volSource", "getVolSource", "setVolSource", "atvInfoChange", "", "auxInInfoChange", "btMusicInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "", LcdInfoShare.MediaInfo.album, LcdInfoShare.MediaInfo.artist, "dtvInfoChange", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "restrict", "param", "sendFrame", "d0", "d1", "d3", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frameData;
    private int vol;
    private int volSource;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
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

    public final int getVol() {
        return this.vol;
    }

    public final void setVol(int i) {
        this.vol = i;
    }

    public final int getVolSource() {
        return this.volSource;
    }

    public final void setVolSource(int i) {
        this.volSource = i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrameData(byteArrayToIntArray);
        switch (getFrameData()[4]) {
            case 0:
                realKeyClick4(context, 0);
                break;
            case 1:
                realKeyClick4(context, 7);
                break;
            case 2:
                realKeyClick4(context, 8);
                break;
            case 3:
                realKeyClick4(context, HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 4:
                realKeyClick4(context, 3);
                break;
            case 5:
                realKeyClick4(context, 45);
                break;
            case 6:
                realKeyClick4(context, 46);
                break;
            case 7:
                realKeyClick4(context, 2);
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        super.currentVolumeInfoChange(volValue, isMute);
        this.vol = volValue;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r1, java.lang.String r2, java.lang.String r3, java.lang.String r4, int r5) {
        /*
            r0 = this;
            java.lang.String r1 = "currBand"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.lang.String r1 = "currentFreq"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
            java.lang.String r1 = "psName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            int r1 = r2.hashCode()
            switch(r1) {
                case 64901: goto L47;
                case 64902: goto L3b;
                case 69706: goto L2f;
                case 69707: goto L23;
                case 69708: goto L17;
                default: goto L16;
            }
        L16:
            goto L53
        L17:
            java.lang.String r1 = "FM3"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L20
            goto L53
        L20:
            r1 = 19
            goto L54
        L23:
            java.lang.String r1 = "FM2"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L2c
            goto L53
        L2c:
            r1 = 18
            goto L54
        L2f:
            java.lang.String r1 = "FM1"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L38
            goto L53
        L38:
            r1 = 17
            goto L54
        L3b:
            java.lang.String r1 = "AM2"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L44
            goto L53
        L44:
            r1 = 21
            goto L54
        L47:
            java.lang.String r1 = "AM1"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L50
            goto L53
        L50:
            r1 = 20
            goto L54
        L53:
            r1 = 0
        L54:
            r0.volSource = r1
            int r1 = r0.vol
            java.nio.charset.Charset r3 = kotlin.text.Charsets.US_ASCII
            byte[] r2 = r2.getBytes(r3)
            java.lang.String r3 = "this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            int r3 = r0.volSource
            r0.sendFrame(r3, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._355.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        Intrinsics.checkNotNullParameter(song, "song");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        byte[] bytes = "DISC".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        sendFrame(32, this.vol, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bytes = "AUX".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        sendFrame(83, this.vol, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (totalCount != 0) {
            byte[] bytes = (stoagePath == 9 ? "SD" : "USB").getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            sendFrame(stoagePath == 9 ? 85 : 84, this.vol, bytes);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        byte[] bytes = "PHONE".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        sendFrame(64, this.vol, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        byte[] bytes = "ATV".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        sendFrame(81, this.vol, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        byte[] bytes = "DTV".getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        sendFrame(87, this.vol, bytes);
    }

    public final byte[] restrict(byte[] param) {
        Intrinsics.checkNotNullParameter(param, "param");
        if (param.length > 8) {
            return ArraysKt.copyOfRange(param, 0, 7);
        }
        int length = 8 - param.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = 0;
        }
        return ArraysKt.plus(param, bArr);
    }

    public final void sendFrame(int d0, int d1, byte[] d3) {
        Intrinsics.checkNotNullParameter(d3, "d3");
        CanbusMsgSender.sendMsg(ArraysKt.plus(ArraysKt.plus(new byte[]{22, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) d0, (byte) d1, 8}, restrict(d3)), (byte) 0));
    }
}
