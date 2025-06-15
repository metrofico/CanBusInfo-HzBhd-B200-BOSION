package com.hzbhd.canbus.car._211;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static byte[] mFrontDataLast;
    private static byte[] mFrontDataRec;
    private static byte[] mRearDataLast;
    private static byte[] mRearDataRec;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private ContentResolver mContentResolver;
    private Context mContext;
    int[] mRearRadarData;

    private int getRadarData(int i) {
        if (i == 255) {
            return 0;
        }
        return i + 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setVehicleInfo0x11();
            setTrackInfo();
        } else if (i == 49) {
            setAirData0x31();
        } else if (i == 65) {
            set0x41RadarInfo();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo();
        }
    }

    private void set0x41RadarInfo() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(8, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVehicleInfo0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getLightStatus()));
        arrayList.add(new DriverUpdateEntity(0, 1, getLightValue()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[5]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[5]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[5]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 0, iArr[5]);
            return;
        }
        if (i == 8) {
            realKeyLongClick1(this.mContext, 20, iArr[5]);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(this.mContext, 21, iArr[5]);
            return;
        }
        if (i == 11) {
            realKeyLongClick1(this.mContext, 2, iArr[5]);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 47, iArr[5]);
            return;
        }
        if (i != 23) {
            switch (i) {
                case 13:
                    realKeyLongClick1(this.mContext, 45, iArr[5]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 46, iArr[5]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 49, iArr[5]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 48, iArr[5]);
    }

    private void setAirData0x31() {
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 13);
        setOutDoorTem();
        getLastAirData();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        cleanAllBlow();
        int i = this.mCanBusInfoInt[6];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = resolveRearTemp(this.mCanBusInfoInt[12]);
        updateAirActivity(this.mContext, getAirWhat());
    }

    private void setOutDoorTem() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void getLastAirData() {
        try {
            String string = Settings.System.getString(this.mContentResolver, "211_LastFrontData");
            String string2 = Settings.System.getString(this.mContentResolver, "211_LastRearData");
            if (!TextUtils.isEmpty(string)) {
                mFrontDataLast = Base64.decode(string, 0);
            }
            if (TextUtils.isEmpty(string2)) {
                return;
            }
            mRearDataLast = Base64.decode(string2, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getAirWhat() {
        mFrontDataRec = Arrays.copyOfRange(this.mCanBusInfoByte, 2, 10);
        mRearDataRec = Arrays.copyOfRange(this.mCanBusInfoByte, 10, 13);
        if (!Arrays.equals(mFrontDataRec, mFrontDataLast)) {
            Settings.System.putString(this.mContentResolver, "211_LastFrontData", Base64.encodeToString(mFrontDataRec, 0));
            return 1001;
        }
        if (Arrays.equals(mRearDataRec, mRearDataLast)) {
            return 0;
        }
        Settings.System.putString(this.mContentResolver, "211_LastRearData", Base64.encodeToString(mRearDataRec, 0));
        return 1002;
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String resolveOutDoorTem() {
        return ((this.mCanBusInfoInt[13] / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String resolveLeftAndRightTemp(int r5) {
        /*
            r4 = this;
            boolean r0 = com.hzbhd.canbus.ui_datas.GeneralAirData.fahrenheit_celsius
            java.lang.String r1 = "HI"
            r2 = 1073741824(0x40000000, float:2.0)
            java.lang.String r3 = "LO"
            if (r0 == 0) goto L2e
            r0 = 119(0x77, float:1.67E-43)
            if (r0 != r5) goto Lf
            goto L32
        Lf:
            r0 = 171(0xab, float:2.4E-43)
            if (r0 != r5) goto L14
            goto L52
        L14:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            float r1 = (float) r5
            float r1 = r1 / r2
            java.lang.StringBuilder r0 = r0.append(r1)
            android.content.Context r1 = r4.mContext
            java.lang.String r1 = r4.getTempUnitF(r1)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r0.toString()
            goto L52
        L2e:
            r0 = 30
            if (r0 != r5) goto L34
        L32:
            r1 = r3
            goto L52
        L34:
            r0 = 60
            if (r0 != r5) goto L39
            goto L52
        L39:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            float r1 = (float) r5
            float r1 = r1 / r2
            java.lang.StringBuilder r0 = r0.append(r1)
            android.content.Context r1 = r4.mContext
            java.lang.String r1 = r4.getTempUnitC(r1)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r0.toString()
        L52:
            if (r5 != 0) goto L56
            java.lang.String r1 = " "
        L56:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._211.MsgMgr.resolveLeftAndRightTemp(int):java.lang.String");
    }

    private String resolveRearTemp(int i) {
        return 1 == i ? "LO" : 9 == i ? "HI" : (2 > i || i > 8) ? "OFF" : i + "";
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private boolean isFirst() {
        if (isAirFirst) {
            isAirFirst = false;
            if (!GeneralAirData.power) {
                return true;
            }
        }
        return false;
    }

    private String getLightStatus() {
        return CommUtil.getStrByResId(this.mContext, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "headlights_on" : "headlights_off");
    }

    private String getLightValue() {
        int i = this.mCanBusInfoInt[7];
        if (i == 100) {
            return CommUtil.getStrByResId(this.mContext, "light_max");
        }
        return Integer.toString(i);
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }
}
