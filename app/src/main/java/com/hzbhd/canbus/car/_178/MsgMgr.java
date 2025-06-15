package com.hzbhd.canbus.car._178;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mKeyStatus;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            set0x01WheelKey();
            return;
        }
        if (i == 2) {
            set0x02CarData();
        } else if (i == 5) {
            set0x05TrackData(context);
        } else {
            if (i != 113) {
                return;
            }
            set0x71VersionData();
        }
    }

    private void set0x01WheelKey() {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        switch (i2) {
            case 1:
                realKeyClick(2);
                break;
            case 2:
                realKeyClick(46);
                break;
            case 3:
                realKeyClick(45);
                break;
            case 4:
                realKeyClick(7);
                break;
            case 5:
                realKeyClick(8);
                break;
            case 6:
                realKeyClick(3);
                break;
            case 7:
                realKeyClick(14);
                break;
            case 8:
                realKeyClick(15);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x02CarData() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3] | (iArr[2] << 8);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, i + " KM"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveOutDoorTem() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        double d = iArr[5] | (i << 8);
        if (!DataHandleUtils.getBoolBit7(i)) {
            return (d / 10.0d) + getTempUnitC(this.mContext);
        }
        int[] iArr2 = this.mCanBusInfoInt;
        return "-" + ((iArr2[5] | ((iArr2[4] & 15) << 8)) / 10.0d) + getTempUnitC(this.mContext);
    }

    private void set0x05TrackData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte) this.mCanBusInfoInt[2], (byte) 0, 0, 50, 8);
            updateParkUi(null, context);
        }
    }

    private void set0x71VersionData() {
        updateVersionInfo(this.mContext, "PORSCHE-Cayenne " + String.format("20%d%d.%d%d.%d%d ", Integer.valueOf(this.mCanBusInfoInt[4] >> 4), Integer.valueOf(this.mCanBusInfoInt[4] & 15), Integer.valueOf(this.mCanBusInfoInt[5] >> 4), Integer.valueOf(this.mCanBusInfoInt[5] & 15), Integer.valueOf(this.mCanBusInfoInt[6] >> 4), Integer.valueOf(this.mCanBusInfoInt[6] & 15)) + ("V" + (this.mCanBusInfoInt[8] - 78) + (this.mCanBusInfoInt[9] - 78) + (this.mCanBusInfoInt[10] - 78)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -126, 0, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int msb;
        int lsb;
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM") || str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            int i4 = (int) (Float.parseFloat(str2) * 10.0f);
            msb = DataHandleUtils.getMsb(i4);
            lsb = DataHandleUtils.getLsb(i4);
            i3 = 1;
        } else if (str.equals("AM") || str.equals("AM1") || str.equals("AM2") || str.equals("MW")) {
            int i5 = Integer.parseInt(str2);
            msb = DataHandleUtils.getMsb(i5);
            lsb = DataHandleUtils.getLsb(i5);
            i3 = 2;
        } else {
            lsb = 255;
            msb = 255;
            i3 = 255;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i3, (byte) msb, (byte) lsb, (byte) 255});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -126, (byte) (b == 9 ? 3 : 4), b7, (byte) i, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -126, (byte) (b == 9 ? 3 : 4), b6, (byte) i, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -126, 11, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -126, 11, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 0 || i6 == 15) {
            return;
        }
        int i8 = i6 == 1 ? 6 : 7;
        int[] iArr = new int[2];
        if (i6 == 6) {
            iArr[0] = i3 >> 8;
            iArr[1] = i3 & 255;
        } else {
            iArr[0] = i4 >> 8;
            iArr[1] = i4 & 255;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -126, (byte) i8, (byte) iArr[0], (byte) iArr[1], -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -126, 9, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, -1, -1, -1});
    }
}
