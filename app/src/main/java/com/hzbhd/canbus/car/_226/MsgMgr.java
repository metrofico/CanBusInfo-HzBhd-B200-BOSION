package com.hzbhd.canbus.car._226;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int mAmb;
    private static int mOutDoorTemp;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    private int pinJie2ByteToInt(int i, int i2) {
        return (i << 8) | i2;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            set0x01WheelKeyInfo();
            return;
        }
        if (i == 2) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            updateAirInfoPanel();
        } else if (i == 125) {
            setVehicleInfo0x7d();
        } else {
            if (i != 127) {
                return;
            }
            updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void set0x01WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 21, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        switch (i) {
            case 19:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 20:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 15, iArr[3]);
                break;
        }
    }

    private void setVehicleInfo0x7d() throws Resources.NotFoundException {
        String str;
        String str2;
        String string;
        String string2;
        ArrayList arrayList = new ArrayList();
        String string3 = this.mContext.getString(R.string.unaccessible);
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[2] == 255 ? string3 : this.mCanBusInfoInt[2] + "KM/H"));
        int[] iArr = this.mCanBusInfoInt;
        int iPinJie2ByteToInt = pinJie2ByteToInt(iArr[3], iArr[4]);
        if (iPinJie2ByteToInt == 65535) {
            str = string3;
        } else {
            str = iPinJie2ByteToInt == 65534 ? "----" : new DecimalFormat("0.0").format(iPinJie2ByteToInt * 0.1d) + "L/100KM";
        }
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        int[] iArr2 = this.mCanBusInfoInt;
        int iPinJie2ByteToInt2 = pinJie2ByteToInt(iArr2[5], iArr2[6]);
        if (iPinJie2ByteToInt2 == 65535) {
            str2 = string3;
        } else {
            str2 = iPinJie2ByteToInt2 != 65534 ? new DecimalFormat("0.0").format(iPinJie2ByteToInt2 * 0.1d) + "L/100KM" : "----";
        }
        arrayList.add(new DriverUpdateEntity(0, 2, str2));
        int[] iArr3 = this.mCanBusInfoInt;
        int iPinJie2ByteToInt3 = pinJie2ByteToInt(iArr3[7], iArr3[8]);
        arrayList.add(new DriverUpdateEntity(0, 3, iPinJie2ByteToInt3 == 65535 ? string3 : iPinJie2ByteToInt3 + "KM"));
        arrayList.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[9] == 255 ? string3 : this.mCanBusInfoInt[9] + "KM/H"));
        int[] iArr4 = this.mCanBusInfoInt;
        if ((iArr4[10] * 256) + iArr4[11] == 65535) {
            string = string3;
        } else {
            StringBuilder sb = new StringBuilder();
            int[] iArr5 = this.mCanBusInfoInt;
            string = sb.append((iArr5[10] * 256) + iArr5[11]).append("rpm").toString();
        }
        arrayList.add(new DriverUpdateEntity(0, 5, string));
        arrayList.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[12] == 255 ? string3 : ((float) (this.mCanBusInfoInt[12] * 0.1d)) + "V"));
        if (this.mCanBusInfoInt[13] != 255) {
            string3 = (this.mCanBusInfoInt[13] - 40) + "℃";
        }
        arrayList.add(new DriverUpdateEntity(0, 7, string3));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
            string2 = String.format(this.mContext.getResources().getString(R.string._226_drivie_data_3), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 7)));
        } else {
            string2 = this.mContext.getResources().getString(R.string._226_drivie_data_4);
        }
        arrayList.add(new DriverUpdateEntity(0, 8, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void updateAirInfoPanel() {
        if (getCurrentCanDifferentId() == 0) {
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.eco = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_temperature = resolveLeftTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolveRightTemp(this.mCanBusInfoInt[5]);
        } else {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        }
        mAmb = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? 1 : 0;
        if (this.mContext.getResources().getConfiguration().orientation != 2 || DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            resolveOutDoorTem(this.mCanBusInfoByte);
        } else {
            updateOutDoorTemp(this.mContext, "  ");
        }
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "_226_out_door_temp", mOutDoorTemp);
            SharePreUtil.setIntValue(this.mContext, "_226_amb", mAmb);
            Log.d("cwh", "室外温度");
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
                resolveOutDoorTem(this.mCanBusInfoByte);
                return;
            } else {
                updateOutDoorTemp(this.mContext, "  ");
                return;
            }
        }
        Log.d("cwh", "空调");
        updateAirActivity(this.mContext, 1001);
    }

    private boolean isOnlyOutDoorDataChange() {
        return (SharePreUtil.getFloatValue(this.mContext, "_226_out_door_temp", 0.0f) == ((float) mOutDoorTemp) && SharePreUtil.getIntValue(this.mContext, "_226_amb", 0) == mAmb) ? false : true;
    }

    private String resolveLeftTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (127 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        return (31 > i || 63 < i) ? "" : (((i - 31) * 0.5d) + 15.5d) + getTempUnitC(this.mContext);
    }

    private String resolveRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (127 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        return (31 > i || 63 < i) ? "" : (((i - 31) * 0.5d) + 15.5d) + getTempUnitC(this.mContext);
    }

    private void resolveOutDoorTem(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        mOutDoorTemp = DataHandleUtils.getIntFromByteWithBit(byteArrayToIntArray[6], 0, 7);
        String str = DataHandleUtils.getIntFromByteWithBit(byteArrayToIntArray[6], 0, 7) + getTempUnitC(this.mContext);
        if (DataHandleUtils.getBoolBit7(byteArrayToIntArray[6])) {
            str = "-" + str;
        }
        updateOutDoorTemp(this.mContext, str);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "AUX".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, "DTV".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -125}, ("Video  " + ((int) b4) + ":" + new DecimalFormat("00").format(b5)).getBytes()));
        Log.d("cwh", "video");
    }
}
