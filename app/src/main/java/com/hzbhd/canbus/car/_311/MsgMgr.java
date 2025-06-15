package com.hzbhd.canbus.car._311;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat00;
    private String mMediaId;
    private byte[] mMediaInfo;
    private TimerUtil mMeidaInfoTimer;
    private TimerUtil mPhoneInfoTimer;
    private int mWheelKey;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(final Context context) {
        super.afterServiceNormalSetting(context);
        String strName = SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name();
        this.mMediaId = strName;
        byte[] bArr = new byte[14];
        this.mMediaInfo = bArr;
        bArr[0] = 22;
        bArr[1] = 0;
        sendMediaMsg(context, strName, bArr);
        TimerUtil timerUtil = new TimerUtil();
        this.mMeidaInfoTimer = timerUtil;
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._311.MsgMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.sendMediaMsg(context, msgMgr.mMediaId, MsgMgr.this.mMediaInfo);
            }
        }, 300L, 300L);
        this.mPhoneInfoTimer = new TimerUtil();
        this.mDecimalFormat00 = new DecimalFormat("00");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        if (byteArrayToIntArray[1] == 0) {
            set0x01Wheelkey(context);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        Log.d("cwh", "isPowerOff   " + (z ? "关机" : "开机"));
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, 4, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 4, 0});
        }
    }

    private void set0x01Wheelkey(Context context) {
        int i = this.mWheelKey;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
        }
        this.mWheelKey = i2;
        switch (i2) {
            case 0:
                realKeyLongClick2(context, 0);
                break;
            case 1:
                realKeyLongClick2(context, 7);
                break;
            case 2:
                realKeyLongClick2(context, 8);
                break;
            case 3:
                realKeyLongClick2(context, 14);
                break;
            case 4:
                realKeyLongClick2(context, 3);
                break;
            case 5:
                realKeyLongClick2(context, 45);
                break;
            case 6:
                realKeyLongClick2(context, 46);
                break;
            case 7:
                realKeyLongClick2(context, 2);
                break;
            case 8:
                realKeyLongClick2(context, 48);
                break;
            case 9:
                realKeyLongClick2(context, 47);
                break;
            case 10:
                realKeyLongClick2(context, 49);
                break;
        }
    }

    private void setDriveData() throws Resources.NotFoundException {
        String string;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
        if (intFromByteWithBit == 0) {
            string = this.mContext.getResources().getString(R.string.minimum);
        } else if (intFromByteWithBit == 255) {
            string = this.mContext.getResources().getString(R.string.max);
        } else {
            string = intFromByteWithBit + "";
        }
        Log.d("cwh", "str = " + string);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), "NO SOURCE");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        super.atvDestdroy();
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.ATV.name(), "ATV");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.AUX1.name(), "AUX");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), "BT MUSIC");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        startPhoneInfoTimer(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        startPhoneInfoTimer(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        stopPhoneInfoTimer();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int[] time = getTime(i);
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.MPEG.name(), "DISC " + this.mDecimalFormat00.format(time[1]) + ":" + this.mDecimalFormat00.format(time[2]));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.DTV.name(), "DTV");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.MUSIC.name(), "MUSIC " + str2 + ":" + str3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.FM.name(), str + " " + str2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaInfo(SourceConstantsDef.SOURCE_ID.VIDEO.name(), "VIDEO " + str3 + ":" + str4);
    }

    private void sendMediaInfo(String str, String str2) {
        this.mMediaId = str;
        byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(str2.getBytes(), 12, 32);
        System.arraycopy(bArrMakeBytesFixedLength, 0, this.mMediaInfo, 2, bArrMakeBytesFixedLength.length);
    }

    private void startPhoneInfoTimer(byte[] bArr) {
        final byte[] bArr2 = new byte[14];
        bArr2[0] = 22;
        bArr2[1] = 0;
        byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(bArr, 12, 32);
        System.arraycopy(bArrMakeBytesFixedLength, 0, bArr2, 2, bArrMakeBytesFixedLength.length);
        this.mPhoneInfoTimer.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._311.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(bArr2);
            }
        }, 0L, 300L);
    }

    private void stopPhoneInfoTimer() {
        this.mPhoneInfoTimer.stopTimer();
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }
}
