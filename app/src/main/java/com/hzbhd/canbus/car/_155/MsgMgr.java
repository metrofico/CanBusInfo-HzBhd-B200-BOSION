package com.hzbhd.canbus.car._155;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
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
        if (i == 17) {
            set0x11WheelKey();
            return;
        }
        if (i == 18) {
            set0x12BtUsbData();
            return;
        }
        if (i == 20) {
            set0x14DoorData(context);
            return;
        }
        if (i == 113) {
            set0x71VersionData();
        } else if (i == 22) {
            set0x16OutDoorTemp();
        } else {
            if (i != 23) {
                return;
            }
            set0x17OrigUsbPlayTimeData();
        }
    }

    private void set0x11WheelKey() {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 0) {
            realKeyClick(0);
        }
        if (i2 == 3) {
            realKeyClick(2);
            return;
        }
        if (i2 == 4) {
            realKeyClick(7);
            return;
        }
        if (i2 == 5) {
            realKeyClick(8);
            return;
        }
        if (i2 == 6) {
            realKeyClick(3);
            return;
        }
        switch (i2) {
            case 8:
                realKeyClick(45);
                break;
            case 9:
                realKeyClick(46);
                break;
            case 10:
                realKeyClick(129);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x16OutDoorTemp() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        int[] iArr = this.mCanBusInfoInt;
        double d = iArr[2];
        if (d < 1.0d || d > 128.0d) {
            return "---";
        }
        if (iArr[3] == 1) {
            return tempCToTempF(d - 40.0d) + getTempUnitF(this.mContext);
        }
        return (d - 40.0d) + getTempUnitC(this.mContext);
    }

    private double tempCToTempF(double d) {
        LogUtil.showLog("tempCToTempF:" + d);
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    private void set0x71VersionData() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        updateVersionInfo(this.mContext, "FIAT " + (i == 0 ? "兼容 " : i == 1 ? "brave " : "punto ") + String.format("20%d%d.%d%d.%d%d ", Integer.valueOf(iArr[4] >> 4), Integer.valueOf(this.mCanBusInfoInt[4] & 15), Integer.valueOf(this.mCanBusInfoInt[5] >> 4), Integer.valueOf(this.mCanBusInfoInt[5] & 15), Integer.valueOf(this.mCanBusInfoInt[6] >> 4), Integer.valueOf(this.mCanBusInfoInt[6] & 15)) + ("V" + ((char) this.mCanBusInfoInt[8]) + "." + ((char) this.mCanBusInfoInt[9]) + "." + ((char) this.mCanBusInfoInt[10])));
    }

    private void set0x14DoorData(Context context) {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private void set0x12BtUsbData() {
        int i = this.mCanBusInfoInt[2];
        if (((i >> 3) & 1) == 1) {
            GeneralOriginalCarDeviceData.cdStatus = "VOICE";
        } else if (((i >> 2) & 1) == 1) {
            GeneralOriginalCarDeviceData.cdStatus = "PHONE";
        } else if ((i & 3) == 2) {
            GeneralOriginalCarDeviceData.cdStatus = "Media Player";
        } else {
            GeneralOriginalCarDeviceData.cdStatus = " ";
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void set0x17OrigUsbPlayTimeData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, String.format("%02d:%02d", Integer.valueOf(this.mCanBusInfoInt[2]), Integer.valueOf(this.mCanBusInfoInt[3]))));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -109, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM2")) {
            i3 = 1;
        } else if (str.contains("FM3")) {
            i3 = 2;
        } else if (str.contains("LW")) {
            i3 = 4;
        } else if (str.contains("MW")) {
            i3 = 5;
        } else {
            i3 = str.contains("AM") ? 6 : 0;
        }
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -109, 1, (byte) i3, 0, 0, (byte) freqByteHiLo[0], (byte) freqByteHiLo[1]});
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 10.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -109, 2, 0, (byte) DataHandleUtils.rangeNumber(i4, 1, 99), (byte) (z2 ? 2 : 0), 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte) (b == 9 ? 5 : 4), 0, (byte) DataHandleUtils.rangeNumber((b7 << 8) | i, 255), (byte) (z ? 2 : 0), 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte) (b == 9 ? 5 : 4), 0, (byte) DataHandleUtils.rangeNumber((b6 << 8) | i, 255), (byte) (z ? 2 : 0), 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -109, 3, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
    }
}
