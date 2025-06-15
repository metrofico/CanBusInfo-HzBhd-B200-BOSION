package com.hzbhd.canbus.car._254;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private final int DATA_TYPE = 1;
    String songName = "";
    String artistName = "";
    String albumName = "";
    DECODE_FOMART decodeFomart = DECODE_FOMART.ASCII;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();

    public enum DECODE_FOMART {
        ASCII,
        UN_SMALL,
        UN_BIG,
        UTF_8
    }

    public enum ID3_TYPE {
        ID3_SONG,
        ID3_ARTIST,
        ID3_ALBUM
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy4Bit(int i) {
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
        }
        return 0;
    }

    private int getIndexBy5Bit(int i) {
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
            if (i == 3) {
                return 3;
            }
            if (i == 4) {
                return 4;
            }
        }
        return 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        try {
            setCanInfo(context);
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setCanInfo(Context context) throws Resources.NotFoundException {
        int i = this.mCanBusInfoInt[1];
        if (i == 1) {
            set0x01WheelKey(context);
        }
        if (i == 2) {
            setDoorData0x02();
            return;
        }
        if (i == 16) {
            setDriveData0x10();
            return;
        }
        if (i == 17) {
            setDriveData0x11();
            return;
        }
        if (i == 103) {
            setSettingData0x67();
            return;
        }
        if (i == 104) {
            setSettingData0x68();
            return;
        }
        if (i == 106) {
            setSettingData0x6a();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        switch (i) {
            case 4:
                setOriginalCarDeviceInfo0x04();
                break;
            case 5:
                setOriginalCarDeviceInfo0x05();
                break;
            case 6:
                setOriginalCarDeviceInfo0x06(context);
                break;
            case 7:
                set0x07TrackData(context);
                break;
            case 8:
                set0x08RadarData(context);
                break;
            case 9:
                setSettingData0x09();
                break;
            case 10:
                setSettingData0x0a();
                break;
            case 11:
                setDriveData0x0b();
                break;
            case 12:
                setDriveData0x0c();
                break;
            case 13:
                setSettingData0x0d();
                break;
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSettingData0x09() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        int indexBy5Bit = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3));
        int indexBy4Bit = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        int indexBy3Bit2 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2));
        int indexBy4Bit2 = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        int indexBy3Bit3 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2));
        int indexBy3Bit4 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
        int indexBy5Bit2 = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3));
        int indexBy5Bit3 = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3));
        String strValueOf = String.valueOf(this.mCanBusInfoInt[6]);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        String strValueOf2 = String.valueOf(iArr[7]);
        int[] iArr2 = this.mCanBusInfoInt;
        int i2 = iArr2[7];
        String strValueOf3 = String.valueOf(iArr2[8]);
        int[] iArr3 = this.mCanBusInfoInt;
        int i3 = iArr3[8];
        int indexBy2Bit12 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr3[9]));
        int indexBy2Bit13 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]));
        int indexBy3Bit5 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2));
        int indexBy2Bit14 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]));
        int indexBy3Bit6 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy5Bit)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy4Bit)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy3Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy4Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(indexBy2Bit10)));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(indexBy2Bit11)));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(indexBy3Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(indexBy3Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 15, Integer.valueOf(indexBy5Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 16, Integer.valueOf(indexBy5Bit3)));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(indexBy2Bit12)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(indexBy2Bit13)));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(indexBy3Bit5)));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(indexBy2Bit14)));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(indexBy3Bit6)));
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(indexBy2Bit9)));
        arrayList.add(new SettingUpdateEntity(2, 3, strValueOf).setProgress(i));
        arrayList.add(new SettingUpdateEntity(2, 4, strValueOf2).setProgress(i2));
        arrayList.add(new SettingUpdateEntity(2, 5, strValueOf3).setProgress(i3));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData0x0d() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        int indexBy5Bit = getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        int indexBy4Bit = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2));
        int indexBy3Bit2 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        int indexBy3Bit3 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2));
        int indexBy3Bit4 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(indexBy5Bit)));
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(indexBy4Bit)));
        arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(indexBy3Bit2)));
        arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(indexBy3Bit3)));
        arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(indexBy3Bit4)));
        arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(indexBy2Bit9)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData0x0a() throws Resources.NotFoundException {
        String string;
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else {
            string = i != 13 ? null : this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        }
        arrayList.add(new DriverUpdateEntity(2, 5, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x0b() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, String.valueOf(((float) ((((iArr[2] * 256) + iArr[3]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, String.valueOf(((float) ((((iArr2[4] * 256) + iArr2[5]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, String.valueOf(((float) ((((iArr3[6] * 256) + iArr3[7]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, String.valueOf(((float) ((((iArr4[8] * 256) + iArr4[9]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, String.valueOf(((float) ((((iArr5[10] * 256) + iArr5[11]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, String.valueOf(((float) ((((iArr6[12] * 256) + iArr6[13]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 6, String.valueOf(((float) ((((iArr7[14] * 256) + iArr7[15]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, String.valueOf(((float) ((((iArr8[16] * 256) + iArr8[17]) * 0.1d) * 10.0d)) / 10.0f)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x0c() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, String.valueOf(((float) ((((iArr[2] * 256) + iArr[3]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, String.valueOf(((float) ((((iArr2[4] * 256) + iArr2[5]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, String.valueOf(((float) ((((iArr3[6] * 256) + iArr3[7]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, String.valueOf(((float) ((((iArr4[8] * 256) + iArr4[9]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, String.valueOf(((float) ((((iArr5[10] * 256) + iArr5[11]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 5, String.valueOf(((float) ((((iArr6[12] * 256) + iArr6[13]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 6, String.valueOf(((float) ((((iArr7[14] * 256) + iArr7[15]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 7, String.valueOf(((float) ((((iArr8[16] * 256) + iArr8[17]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr9 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 8, String.valueOf(((float) ((((iArr9[18] * 256) + iArr9[19]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr10 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 9, String.valueOf(((float) ((((iArr10[20] * 256) + iArr10[21]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr11 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 10, String.valueOf(((float) ((((iArr11[22] * 256) + iArr11[23]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr12 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 11, String.valueOf(((float) ((((iArr12[24] * 256) + iArr12[25]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr13 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 12, String.valueOf(((float) ((((iArr13[26] * 256) + iArr13[27]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr14 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 13, String.valueOf(((float) ((((iArr14[28] * 256) + iArr14[29]) * 0.1d) * 10.0d)) / 10.0f)));
        int[] iArr15 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 14, String.valueOf(((float) ((((iArr15[30] * 256) + iArr15[31]) * 0.1d) * 10.0d)) / 10.0f)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x10() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 0, sb.append((iArr[3] * 256 * 256) + (iArr[4] * 256) + iArr[5]).append("KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 1, (this.mCanBusInfoInt[2] - 40) + "℃"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSettingData0x67() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_1);
        } else if (i == 1) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_2);
        } else if (i == 2) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_3);
        } else {
            string = i != 3 ? null : this.mContext.getResources().getString(R.string.mazda_binary_car_set77_4);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 2, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSettingData0x68() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 3, sb.append(((iArr[2] + (iArr[3] * 256)) * 10) / 10).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSettingData0x6a() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 4, sb.append(((iArr[2] + (iArr[3] * 256)) * 10) / 10).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void setDoorData0x02() {
        GeneralDoorData.isShowEsp = true;
        GeneralDoorData.isShowIstop = true;
        GeneralDoorData.isShowLittleLight = true;
        GeneralDoorData.isShowWaterTemp = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isEspOn = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isIstopOn = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isWaterTempWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void setOriginalCarDeviceInfo0x04() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr[5] * 256) + iArr[6]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.have_cd);
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1) == 1) {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.have_dvd);
        } else {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.have_not_dvd);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 0) {
            GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(R.string.cd_status_normal);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 1) {
            GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(R.string.cd_status_error);
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarDeviceInfo0x05() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append((iArr[5] * 256) + iArr[6]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb2.append((iArr2[7] * 256) + iArr2[8]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        GeneralOriginalCarDeviceData.rpt_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 0;
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 1;
        GeneralOriginalCarDeviceData.rpt_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 2;
        GeneralOriginalCarDeviceData.rdm_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 0;
        GeneralOriginalCarDeviceData.rdm_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 1;
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 2;
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_stop_close);
        } else if (i == 1) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_pause);
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.cd_play);
        } else if (i == 3) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.disc_out);
        } else if (i == 4) {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(R.string.reading);
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");
        int[] iArr3 = this.mCanBusInfoInt;
        int i2 = (iArr3[11] * 256) + iArr3[12];
        int i3 = (iArr3[9] * 256) + iArr3[10];
        if (i2 <= i3) {
            if (i2 >= 3600) {
                GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 / 60) / 60) + ":" + decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
            } else {
                GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
            }
            if (i3 >= 3600) {
                GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i3 / 60) / 60) + ":" + decimalFormat.format((i3 % 3600) / 60) + ":" + decimalFormat.format(i3 % 60);
            } else {
                GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i3 % 3600) / 60) + ":" + decimalFormat.format(i3 % 60);
            }
            if (i3 == 0) {
                GeneralOriginalCarDeviceData.progress = 0;
            } else {
                GeneralOriginalCarDeviceData.progress = (i2 * 100) / i3;
            }
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarDeviceInfo0x06(Context context) {
        String id3InfoNeedSendCode;
        try {
            id3InfoNeedSendCode = setId3InfoNeedSendCode((byte) this.mCanBusInfoInt[2], new byte[]{0, 3, 2}, new ID3_TYPE[]{ID3_TYPE.ID3_SONG, ID3_TYPE.ID3_ALBUM, ID3_TYPE.ID3_ARTIST}, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4), DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 2, (byte) 0));
        } catch (Exception e) {
            e.printStackTrace();
            id3InfoNeedSendCode = null;
        }
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        arrayList.add(new OriginalCarDeviceUpdateEntity(i != 0 ? i != 2 ? i != 3 ? 0 : 5 : 4 : 3, id3InfoNeedSendCode));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void set0x01WheelKey(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
        }
        if (i == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(context, 45);
            return;
        }
        switch (i) {
            case 8:
                realKeyLongClick1(context, 3);
                break;
            case 9:
                realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                break;
            case 10:
                realKeyLongClick1(context, 14);
                break;
            case 11:
                realKeyLongClick1(context, 15);
                break;
            default:
                switch (i) {
                    case 32:
                        realKeyLongClick1(context, 59);
                        break;
                    case 33:
                        realKeyLongClick1(context, 52);
                        break;
                    case 34:
                        realKeyLongClick1(context, 128);
                        break;
                    case 35:
                        realKeyLongClick1(context, 79);
                        break;
                    case 36:
                        realKeyLongClick1(context, 45);
                        break;
                    case 37:
                        realKeyLongClick1(context, 46);
                        break;
                    case 38:
                        realKeyLongClick1(context, 47);
                        break;
                    case 39:
                        realKeyLongClick1(context, 48);
                        break;
                    case 40:
                        realKeyLongClick1(context, 49);
                        break;
                    case 41:
                        realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                        break;
                    case 42:
                        realKeyLongClick1(context, 50);
                        break;
                    default:
                        switch (i) {
                            case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                                realKeyClick3_1(context, 7);
                                break;
                            case 241:
                                realKeyClick3_1(context, 8);
                                break;
                            case 242:
                                realKeyClick3_2(context, 48);
                                break;
                            case 243:
                                realKeyClick3_2(context, 47);
                                break;
                        }
                }
        }
    }

    private void set0x07TrackData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, context);
        }
    }

    private void set0x08RadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            int i = iArr[3];
            int i2 = iArr[4];
            RadarInfoUtil.setFrontRadarLocationData(4, i, i2, i2, iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            int i3 = iArr2[6];
            int i4 = iArr2[7];
            RadarInfoUtil.setRearRadarLocationData(4, i3, i4, i4, iArr2[8]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick3_1(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    public String setId3InfoNeedSendCode(byte b, byte[] bArr, ID3_TYPE[] id3_typeArr, int i, byte[] bArr2) throws Exception {
        String strDecodeBytes2StrNeedSendData = null;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (b == bArr[i2]) {
                if (id3_typeArr[i2] == ID3_TYPE.ID3_SONG) {
                    strDecodeBytes2StrNeedSendData = decodeBytes2StrNeedSendData(i, bArr2);
                    this.songName = strDecodeBytes2StrNeedSendData;
                } else if (id3_typeArr[i2] == ID3_TYPE.ID3_ARTIST) {
                    strDecodeBytes2StrNeedSendData = decodeBytes2StrNeedSendData(i, bArr2);
                    this.artistName = strDecodeBytes2StrNeedSendData;
                } else {
                    strDecodeBytes2StrNeedSendData = decodeBytes2StrNeedSendData(i, bArr2);
                    this.albumName = strDecodeBytes2StrNeedSendData;
                }
            }
        }
        return strDecodeBytes2StrNeedSendData;
    }

    private String decodeBytes2StrNeedSendData(int i, byte[] bArr) throws Exception {
        LogUtil.showLog("code:" + i);
        if (i == 0) {
            String str = new String(bArr, "ascii");
            LogUtil.showLog("result:" + str);
            return str;
        }
        if (i == 1) {
            return DataHandleUtils.Byte2Unicode(bArr, true);
        }
        if (i != 2) {
            return i != 3 ? "" : new String(bArr, "UTF-8");
        }
        return DataHandleUtils.Byte2Unicode(bArr, false);
    }

    public static String stringToAscii(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                stringBuffer.append((int) charArray[i]).append(",");
            } else {
                stringBuffer.append((int) charArray[i]);
            }
        }
        return stringBuffer.toString();
    }

    private int getIndexBy3Bit(int i) {
        LogUtil.showLog("getIndexBy3Bit:" + i);
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
        }
        return 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE, 0});
    }
}
