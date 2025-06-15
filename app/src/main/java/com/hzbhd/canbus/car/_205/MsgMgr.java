package com.hzbhd.canbus.car._205;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class MsgMgr extends AbstractMsgMgr {
    private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private static final int SEND_NORMAL_MESSAGE = 2;
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private ID3[] mMusicId3s;
    private long realtime;
    private String songTitleNow = "";
    private String songAlbumNow = "";
    private String songArtistNow = "";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        initID3();
        initHandler(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            realKeyControl();
        } else {
            if (i != 207) {
                return;
            }
            setMostStatus();
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyControl() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
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
            realKeyClick(45);
            return;
        }
        if (i == 4) {
            realKeyClick(46);
            return;
        }
        if (i == 6) {
            realKeyClick(HotKeyConstant.K_VOICE_PICKUP);
            return;
        }
        if (i == 7) {
            realKeyClick(184);
            return;
        }
        switch (i) {
            case 129:
                realKeyClick(15);
                break;
            case 130:
                realKeyClick(137);
                break;
            case 131:
                realKeyClick(15);
                break;
        }
    }

    private void setMostStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? "switch_on" : "switch_off"));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
        reportID3InfoSimple(new byte[]{22, -53}, (byte) 2, (byte) 3, (byte) 4, " ", " ", " ", true, "utf-8");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, allBandTypeData, this.freqLo, this.freqHi, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        Log.i("_205_MsgMgr", "discInfoChange: mContext = " + this.mContext);
        if (this.mContext == null) {
            return;
        }
        int hour = getHour(i);
        int minute = getMinute(i);
        int second = getSecond(i);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        if (i7 != 240) {
            if (i7 == 32) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 16, (byte) i3, (byte) i5, 0, (byte) hour, (byte) minute, (byte) second});
            }
        } else {
            if (SystemClock.elapsedRealtime() - this.realtime < 1000) {
                return;
            }
            String string = Settings.System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(string, 0));
            this.realtime = SystemClock.elapsedRealtime();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.hzbhd.canbus.car._205.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(final byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 1, 0, 0, 0, 0, 0});
        new Thread() { // from class: com.hzbhd.canbus.car._205.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(100L);
                    CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, bArr));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r7v7, types: [com.hzbhd.canbus.car._205.MsgMgr$2] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(final byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 2, (byte) getMinute(i), (byte) getSecond(i), 0, 0, 0});
        new Thread() { // from class: com.hzbhd.canbus.car._205.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(100L);
                    CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, bArr));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.hzbhd.canbus.car._205.MsgMgr$3] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(final byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 3, 0, 0, 0, 0, 0});
        new Thread() { // from class: com.hzbhd.canbus.car._205.MsgMgr.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(100L);
                    CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -53, 4}, bArr));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.hzbhd.canbus.car._205.MsgMgr$4] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4, 0, 0, 0, 0, 0});
        new Thread() { // from class: com.hzbhd.canbus.car._205.MsgMgr.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -53, 4, 0});
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b != 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 19, (byte) i, b7, 0, b3, b4, b5});
        }
        this.mMusicId3s[0].info = str4;
        this.mMusicId3s[1].info = str5;
        this.mMusicId3s[2].info = str6;
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMusicId3s[0].info = " ";
        this.mMusicId3s[1].info = " ";
        this.mMusicId3s[2].info = " ";
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b != 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 8, 19, (byte) i, b6, 0, b3, b4, b5});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 18, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) ((z ? 128 : 0) + (i & 0x7F))});
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.FreqInt = Integer.parseInt(str2);
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return (byte) 16;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    /* JADX WARN: Type inference failed for: r11v0, types: [com.hzbhd.canbus.car._205.MsgMgr$5] */
    private void reportID3InfoSimple(final byte[] bArr, final byte b, final byte b2, final byte b3, final String str, final String str2, final String str3, final boolean z, final String str4) {
        new Thread() { // from class: com.hzbhd.canbus.car._205.MsgMgr.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.songTitleNow) && str2.equals(MsgMgr.this.songAlbumNow) && str3.equals(MsgMgr.this.songArtistNow)) {
                        return;
                    }
                    MsgMgr.this.songTitleNow = str;
                    MsgMgr.this.songAlbumNow = str2;
                    MsgMgr.this.songArtistNow = str3;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b, str, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b2, str2, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b3, str3, str4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoSimpleFinal(byte[] bArr, byte b, String str, String str2) {
        try {
            Util.reportCanbusInfo(Util.byteMerger(Util.addBytes(bArr, b), Util.exceptBOMHead(str.getBytes(str2))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandler(final Context context) {
        HandlerThread handlerThread = new HandlerThread("MyApplication");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._205.MsgMgr.6
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 2) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = i;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private class ID3 {
        private int command;
        private String info;
        private String record;

        private ID3(int i) {
            this.command = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            recordId3Info();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }
    }

    private void initID3() {
        this.mMusicId3s = new ID3[]{new ID3(2), new ID3(3), new ID3(4)};
    }

    private void reportID3Info(ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(id32.command, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, String str) {
        try {
            sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, (byte) i}, DataHandleUtils.exceptBOMHead(str.getBytes("utf-8"))), 35), i * 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
