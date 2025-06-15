package com.hzbhd.canbus.car._247;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

import java.text.DecimalFormat;


public class MsgMgr extends AbstractMsgMgr {
    private static byte[] mCdInfo = {22, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mPreMuteStatus;

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        if (byteArrayToIntArray[1] != 255) {
            return;
        }
        setVersionInfo();
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.hzbhd.canbus.car._247.MsgMgr$1] */
    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        new Thread() { // from class: com.hzbhd.canbus.car._247.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
                    sleep(200L);
                    MsgMgr.this.sendContentStr(" WELCOME");
                    Settings.System.putString(MsgMgr.this.mContext.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(MsgMgr.mCdInfo, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        sendContentStr(" WELCOME");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendContentStr("  OFF   ");
        Settings.System.putString(this.mContext.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(mCdInfo, 0));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        mCdInfo[12] = getAllBandTypeData(str);
        mCdInfo[15] = getBandUnit(str);
        sendContentStr(" RADIO  ");
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
            case "FM1":
                return (byte) 33;
            case "FM2":
                return (byte) 34;
            case "FM3":
                return (byte) 35;
            default:
                return (byte) 0;
        }
    }

    private byte getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return (byte) 32;
            case "FM1":
            case "FM2":
            case "FM3":
                return (byte) 16;
            default:
                return (byte) 0;
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = mCdInfo;
        bArr[10] = (byte) i6;
        bArr[11] = (byte) i5;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        byte[] bArr = mCdInfo;
        bArr[12] = 0;
        bArr[15] = 0;
        sendContentStr("  DISC  ");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            mCdInfo[12] = 64;
        } else {
            mCdInfo[12] = Byte.MIN_VALUE;
        }
        mCdInfo[15] = 0;
        sendContentStr(" MUSIC  ");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            mCdInfo[12] = 64;
        } else {
            mCdInfo[12] = Byte.MIN_VALUE;
        }
        mCdInfo[15] = 0;
        sendContentStr(" VIDEO  ");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = mCdInfo;
        bArr[12] = 0;
        bArr[15] = 0;
        sendContentStr("  AUX   ");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        if (this.mPreMuteStatus == z) {
            return;
        }
        this.mPreMuteStatus = z;
        if (z) {
            sendContentStr("   MUTE   ");
            return;
        }
        try {
            String string = Settings.System.getString(this.mContext.getContentResolver(), "reportAfterHangUp");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            CanbusMsgSender.sendMsg(Base64.decode(string, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMinute(int i) {
        return new DecimalFormat("00").format((i / 60) % 60);
    }

    private String getSecond(int i) {
        return new DecimalFormat("00").format(i % 60);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendContentStr(String str) {
        setMediaInfo07(str.getBytes());
        byte[] bArr = mCdInfo;
        bArr[12] = 0;
        bArr[15] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    private void setMediaInfo07(byte[] bArr) {
        if (bArr.length == 8) {
            for (int i = 0; i < 8; i++) {
                mCdInfo[i + 2] = bArr[i];
            }
        }
    }

    public static String makeMediaInfoCenteredInBytes(int i, String str) {
        String str2 = "";
        if (str == null || str.length() > i) {
            return "";
        }
        int length = i - str.length();
        int i2 = length / 2;
        int i3 = length - i2;
        for (int i4 = 0; i4 < i2; i4++) {
            str2 = str2 + " ";
        }
        String str3 = str2 + str;
        for (int i5 = 0; i5 < i3; i5++) {
            str3 = str3 + " ";
        }
        return str3;
    }
}
