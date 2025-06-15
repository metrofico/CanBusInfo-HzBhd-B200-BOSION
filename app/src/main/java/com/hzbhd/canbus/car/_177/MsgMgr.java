package com.hzbhd.canbus.car._177;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static int ONE = 1;
    static int WIND_LEVEL_HIGH = 8;
    static int WIND_LEVEL_LOW = 1;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mIsBackOpen;
    private boolean mIsBackOpenNow;
    private boolean mIsFrontLeftOpen;
    private boolean mIsFrontLeftOpenNow;
    private boolean mIsFrontOpen;
    private boolean mIsFrontOpenNow;
    private boolean mIsFrontRightOpen;
    private boolean mIsFrontRightOpenNow;
    private boolean mIsRearLeftOpen;
    private boolean mIsRearLeftOpenNow;
    private boolean mIsRearRightOpen;
    private boolean mIsRearRightOpenNow;
    private int data0x90 = 0;
    private int data0x91 = 0;
    private int data0x92 = 0;
    private int data0x93 = 0;
    private int data0xA0 = 0;
    private int data0xA1 = 0;
    private int data0xA2 = 0;
    private int data0xA3 = 0;
    private int data0xA4 = 0;
    private String SHARE_177_DATA_0X90 = "data0x90";
    private String SHARE_177_DATA_0X91 = "data0x91";
    private String SHARE_177_DATA_0X92 = "data0x92";
    private String SHARE_177_DATA_0X93 = "data0x93";
    private String SHARE_177_DATA_0XA0 = "data0xa0";
    private String SHARE_177_DATA_0XA1 = "data0xa1";
    private String SHARE_177_DATA_0XA2 = "data0xa2";
    private String SHARE_177_DATA_0XA3 = "data0xa3";
    private String SHARE_177_DATA_0XA4 = "data0xa4";
    private String SHARE_177_LANGUAGE = "176_Language";
    private int mCallStatus = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 2});
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 113, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 114, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, -127, 0});
        initAmplifierData();
    }

    /* JADX WARN: Type inference failed for: r0v21, types: [com.hzbhd.canbus.car._177.MsgMgr$1] */
    private void initAmplifierData() {
        CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) SharePreUtil.getIntValue(this.mContext, this.SHARE_177_LANGUAGE, 0)});
        this.data0x90 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X90, 0);
        this.data0x91 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X91, 0);
        this.data0x92 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X92, 0);
        this.data0x93 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X93, 0);
        this.data0xA0 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA0, 0);
        this.data0xA1 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA1, 0);
        this.data0xA2 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA2, 0);
        this.data0xA3 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA3, 0);
        this.data0xA4 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA4, 0);
        new Thread() { // from class: com.hzbhd.canbus.car._177.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte) MsgMgr.this.data0x90});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte) (MsgMgr.this.data0x91 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte) (MsgMgr.this.data0x92 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte) (MsgMgr.this.data0x93 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte) MsgMgr.this.data0xA0});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte) MsgMgr.this.data0xA1});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte) MsgMgr.this.data0xA2});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte) MsgMgr.this.data0xA3});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte) MsgMgr.this.data0xA4});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setCarMessage0x28();
            return;
        }
        if (i == 48) {
            setTrackInfo();
            return;
        }
        if (i == 97) {
            setTireData0x61();
            return;
        }
        if (i == 113) {
            setCarSetting0x71();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 129) {
            setCarData0x81();
            return;
        }
        if (i == 145) {
            setSOS0x91();
            return;
        }
        switch (i) {
            case 32:
                setAccused0x20();
                break;
            case 33:
                if (!isAirMsgRepeat(bArr)) {
                    setAirData0x21();
                    break;
                }
                break;
            case 34:
                setRadarInfo0x22();
                break;
            case 35:
                setFrontRadarInfo0x23();
                break;
            case 36:
                setLeftRadarInfo0x24();
                break;
            case 37:
                setRightRadarInfo0x25();
                break;
        }
    }

    private void setAccused0x20() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
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
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(this.mContext, 14, iArr[3]);
            return;
        }
        if (i == 10) {
            realKeyLongClick1(this.mContext, 15, iArr[3]);
            return;
        }
        if (i == 18) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
            return;
        }
        if (i == 143) {
            realKeyClick4(14);
        } else if (i == 21) {
            realKeyLongClick1(this.mContext, 50, iArr[3]);
        } else {
            if (i != 22) {
                return;
            }
            realKeyLongClick1(this.mContext, 49, iArr[3]);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    private void setSOS0x91() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            enterAuxIn2();
        } else if (i == 1) {
            exitAuxIn2();
        }
    }

    private void setCarData0x81() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getData(2) + getOilUnit()));
        arrayList.add(new DriverUpdateEntity(0, 1, getData(4) + getSpeedUnit()));
        arrayList.add(new DriverUpdateEntity(0, 2, getDataDistance(6) + getDistanceUnit()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]));
    }

    private String getOilUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 4, 3)) == 0) ? "L/100KM" : intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? intFromByteWithBit != 3 ? intFromByteWithBit != 4 ? intFromByteWithBit != 5 ? "" : "MPKWH" : "KWH/100KM" : "MPGUK" : "MPGUS" : "KM/L";
    }

    private String getSpeedUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 2, 2)) == 0) ? "KM/H" : intFromByteWithBit != 1 ? "" : "MILES/H";
    }

    private String getDistanceUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 2, 2)) == 0) ? "KM" : intFromByteWithBit != 1 ? "" : "MILES";
    }

    private String getDataDistance(int i) {
        return i == 65535 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf((((DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i + 2], 0, 1) * 256) * 256) + getIdFromCanbusInfo(i)) / 10.0f));
    }

    private String getData(int i) {
        return i == 65535 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf(getIdFromCanbusInfo(i) / 10.0f));
    }

    private int getIdFromCanbusInfo(int i) {
        int[] iArr = this.mCanBusInfoInt;
        return (iArr[i + 1] & 255) | ((iArr[i] & 255) << 8);
    }

    private void setCarSetting0x71() {
        int i;
        int i2;
        int i3;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 3);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
        int intFromByteWithBit18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
        int intFromByteWithBit19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        int[] iArr = this.mCanBusInfoInt;
        int i4 = iArr[5];
        int i5 = iArr[6];
        int i6 = iArr[7];
        int i7 = iArr[8];
        int intFromByteWithBit20 = DataHandleUtils.getIntFromByteWithBit(iArr[9], 7, 1);
        int intFromByteWithBit21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1);
        int intFromByteWithBit22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
        int intFromByteWithBit23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
        int intFromByteWithBit24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1);
        int intFromByteWithBit25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 3);
        int[] iArr2 = this.mCanBusInfoInt;
        int i8 = iArr2[10];
        int intFromByteWithBit26 = DataHandleUtils.getIntFromByteWithBit(iArr2[11], 7, 1);
        int intFromByteWithBit27 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3);
        int intFromByteWithBit28 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
        int intFromByteWithBit29 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1);
        int intFromByteWithBit30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(intFromByteWithBit21)));
        arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(intFromByteWithBit22)));
        arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(intFromByteWithBit20)));
        arrayList.add(new SettingUpdateEntity(2, 8, Integer.valueOf(intFromByteWithBit23)));
        arrayList.add(new SettingUpdateEntity(2, 9, Integer.valueOf(intFromByteWithBit24)));
        arrayList.add(new SettingUpdateEntity(2, 10, Integer.valueOf(intFromByteWithBit25 == 0 ? 0 : intFromByteWithBit25 - ONE)));
        arrayList.add(new SettingUpdateEntity(2, 11, Integer.valueOf(i8)).setProgress(i8));
        arrayList.add(new SettingUpdateEntity(2, 12, Integer.valueOf(intFromByteWithBit26)));
        arrayList.add(new SettingUpdateEntity(2, 13, Integer.valueOf(intFromByteWithBit27 == 0 ? 0 : intFromByteWithBit27 - ONE)));
        arrayList.add(new SettingUpdateEntity(2, 14, Integer.valueOf(intFromByteWithBit28 == 0 ? 0 : intFromByteWithBit28 - ONE)));
        arrayList.add(new SettingUpdateEntity(2, 15, Integer.valueOf(intFromByteWithBit29)));
        arrayList.add(new SettingUpdateEntity(2, 16, Integer.valueOf(intFromByteWithBit30)));
        arrayList.add(new SettingUpdateEntity(2, 17, Integer.valueOf(intFromByteWithBit8 == 0 ? 0 : intFromByteWithBit8 - ONE)));
        arrayList.add(new SettingUpdateEntity(2, 18, Integer.valueOf(intFromByteWithBit9)));
        arrayList.add(new SettingUpdateEntity(2, 19, Integer.valueOf(intFromByteWithBit17)));
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(intFromByteWithBit7)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(intFromByteWithBit6)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit10)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(intFromByteWithBit11)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(intFromByteWithBit12)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(intFromByteWithBit13)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(intFromByteWithBit14)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(intFromByteWithBit15)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(intFromByteWithBit16)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(intFromByteWithBit18)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(intFromByteWithBit19 - ONE)));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(i6 - ONE)));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(i7)).setProgress(i7));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(i5 == 0 ? 0 : i5 - ONE)));
        if (i4 >= 38) {
            i3 = i4 - 4;
        } else {
            if (i4 < 23) {
                i = 4;
                i2 = i4 >= 4 ? i4 - 2 : i4;
                if (i4 != i && i4 != 5 && i4 != 23 && i4 != 38) {
                    arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(i2)));
                }
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                SharePreUtil.setIntValue(this.mContext, this.SHARE_177_LANGUAGE, i4);
            }
            i3 = i4 - 3;
        }
        i2 = i3;
        i = 4;
        if (i4 != i) {
            arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(i2)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_177_LANGUAGE, i4);
    }

    private void setTireData0x61() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getTireEntity(0, getTirePressure(this.mCanBusInfoInt[3])));
        arrayList.add(getTireEntity(1, getTirePressure(this.mCanBusInfoInt[4])));
        arrayList.add(getTireEntity(2, getTirePressure(this.mCanBusInfoInt[5])));
        arrayList.add(getTireEntity(3, getTirePressure(this.mCanBusInfoInt[6])));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
        String tisWarmMsg = getTisWarmMsg(this.mCanBusInfoInt[2]);
        if (TextUtils.isEmpty(tisWarmMsg)) {
            return;
        }
        GeneralDisplayMsgData.displayMsg = tisWarmMsg;
        sendDisplayMsgView(this.mContext);
    }

    private void setCarMessage0x28() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        this.mIsFrontLeftOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        this.mIsFrontRightOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        this.mIsRearLeftOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        this.mIsRearRightOpen = boolBit4;
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = boolBit3;
        this.mIsBackOpen = boolBit3;
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = boolBit2;
        this.mIsFrontOpen = boolBit2;
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private boolean isDoorDataChange() {
        boolean z = this.mIsFrontLeftOpen;
        if (z == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
            return false;
        }
        this.mIsFrontLeftOpenNow = z;
        this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
        this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
        this.mIsRearRightOpenNow = this.mIsRearRightOpen;
        this.mIsBackOpenNow = this.mIsBackOpen;
        this.mIsFrontOpenNow = this.mIsFrontOpen;
        return true;
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[3];
        if (i == 255) {
            return "--" + this.mContext.getString(R.string.str_temp_c_unit);
        }
        return i == 254 ? "" : (this.mCanBusInfoInt[3] - 40) + this.mContext.getString(R.string.str_temp_c_unit);
    }

    private void setRightRadarInfo0x25() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setLeftRadarInfo0x24() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x23() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRadarInfo0x22() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 5400, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x21() {
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.soft = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.fast = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.normal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[4];
        GeneralAirData.front_wind_level = i;
        GeneralAirData.front_auto_wind_speed = i == 15;
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM")) {
            i3 = (int) (Float.parseFloat(str2) * 100.0f);
        } else {
            i3 = str.contains("AM") ? Integer.parseInt(str2) : 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, getAllBandTypeData(str), (byte) (i3 % 256), (byte) (i3 / 256), (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = i3 + 1;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, (byte) (i2 / 256), (byte) (i2 % 256), (byte) (i4 / 256), (byte) (i4 % 256), b3, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, -1, -1, -1, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, (byte) (i3 / 256), (byte) (i3 % 256), (byte) (i5 / 256), (byte) (i5 % 256), (byte) (i / 3600), (byte) ((i / 60) % 60), (byte) (i % 60)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        this.mCallStatus = 1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        this.mCallStatus = 2;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        int i = this.mCallStatus;
        if (i == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 1}, bArr));
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 1}, bArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte getAllBandTypeData(java.lang.String r7) {
        /*
            r6 = this;
            r7.hashCode()
            int r0 = r7.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = -1
            switch(r0) {
                case 2092: goto L5f;
                case 2247: goto L54;
                case 64901: goto L49;
                case 64902: goto L3e;
                case 64903: goto L33;
                case 69706: goto L28;
                case 69707: goto L1d;
                case 69708: goto L12;
                default: goto Lf;
            }
        Lf:
            r7 = r5
            goto L69
        L12:
            java.lang.String r0 = "FM3"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L1b
            goto Lf
        L1b:
            r7 = 7
            goto L69
        L1d:
            java.lang.String r0 = "FM2"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L26
            goto Lf
        L26:
            r7 = 6
            goto L69
        L28:
            java.lang.String r0 = "FM1"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L31
            goto Lf
        L31:
            r7 = 5
            goto L69
        L33:
            java.lang.String r0 = "AM3"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L3c
            goto Lf
        L3c:
            r7 = 4
            goto L69
        L3e:
            java.lang.String r0 = "AM2"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L47
            goto Lf
        L47:
            r7 = r1
            goto L69
        L49:
            java.lang.String r0 = "AM1"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L52
            goto Lf
        L52:
            r7 = r2
            goto L69
        L54:
            java.lang.String r0 = "FM"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L5d
            goto Lf
        L5d:
            r7 = r3
            goto L69
        L5f:
            java.lang.String r0 = "AM"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L68
            goto Lf
        L68:
            r7 = r4
        L69:
            switch(r7) {
                case 0: goto L7a;
                case 1: goto L79;
                case 2: goto L76;
                case 3: goto L73;
                case 4: goto L70;
                case 5: goto L6f;
                case 6: goto L6e;
                case 7: goto L6d;
                default: goto L6c;
            }
        L6c:
            return r5
        L6d:
            return r1
        L6e:
            return r2
        L6f:
            return r3
        L70:
            r7 = 19
            return r7
        L73:
            r7 = 18
            return r7
        L76:
            r7 = 17
            return r7
        L79:
            return r4
        L7a:
            r7 = 16
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._177.MsgMgr.getAllBandTypeData(java.lang.String):byte");
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "" : 254 == i ? "LO" : 255 == i ? "HI" : (i < 32 || i > 64) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private TireUpdateEntity getTireEntity(int i, String str) {
        return new TireUpdateEntity(i, 0, new String[]{str});
    }

    private String getTirePressure(int i) {
        return i == 255 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf(i * 0.03f)) + this.mContext.getString(R.string.pressure_unit);
    }

    private String getTisWarmMsg(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = R.string._176_tire_info_1;
                break;
            case 2:
                i2 = R.string._176_tire_info_2;
                break;
            case 3:
                i2 = R.string._176_tire_info_3;
                break;
            case 4:
                i2 = R.string._176_tire_info_4;
                break;
            case 5:
                i2 = R.string._176_tire_info_5;
                break;
            case 6:
                i2 = R.string._176_tire_info_6;
                break;
            default:
                i2 = 0;
                break;
        }
        return i2 == 0 ? "" : this.mContext.getString(i2);
    }

    private void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
    }
}
