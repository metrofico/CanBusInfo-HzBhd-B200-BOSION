package com.hzbhd.canbus.car._208;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static boolean isWarnFirst = true;
    boolean a;
    boolean b;
    boolean c;
    private byte[] m0xD2Command;
    private byte[] m0xE2Command;
    private byte[] mAirData;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusWarnInfoCopy;
    private Context mContext;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private byte[] mRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private byte[] mTrackData;
    private int mVehicleSpeed;
    private int[] mWarningDataNow;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    int memory = 0;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i != 18) {
            if (i == 19) {
                set0x13ParkingVentilation();
                return;
            }
            if (i == 66) {
                set0x42RadarData(context);
                return;
            }
            if (i != 71) {
                if (i == 240) {
                    set0xF0VersionDate();
                    return;
                } else if (i == 114) {
                    set0x72CarBaseData(context);
                    return;
                } else {
                    if (i != 115) {
                        return;
                    }
                    set0x73AirData(context);
                    return;
                }
            }
            set0x47MasterAssistData(context);
        }
        set0x12CarInfo(context);
    }

    private void set0x72CarBaseData(Context context) {
        int i = this.mVehicleSpeed;
        int i2 = this.mCanBusInfoInt[3];
        if (i != i2) {
            this.mVehicleSpeed = i2;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
        int i3 = this.mKeyStatus;
        int i4 = this.mCanBusInfoInt[4];
        if (i3 != i4) {
            this.mKeyStatus = i4;
        }
        if (i4 == 0) {
            realKeyLongClick2(context, 0);
        } else if (i4 == 1) {
            realKeyLongClick2(context, 7);
        } else if (i4 == 2) {
            realKeyLongClick2(context, 8);
        } else if (i4 == 3) {
            realKeyLongClick2(context, 3);
        } else if (i4 == 5) {
            realKeyLongClick2(context, 14);
        } else if (i4 == 6) {
            realKeyLongClick2(context, 15);
        } else if (i4 != 15) {
            switch (i4) {
                case 8:
                    realKeyLongClick2(context, 45);
                    break;
                case 9:
                    realKeyLongClick2(context, 46);
                    break;
                case 10:
                    realKeyLongClick2(context, 2);
                    break;
            }
        } else {
            realKeyLongClick2(context, HotKeyConstant.K_SPEECH);
        }
        if (isTrackDataChange()) {
            if (this.mCanBusInfoInt[6] != 0) {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[6], (byte) 0, 0, 255, 16);
            }
            if (this.mCanBusInfoInt[7] != 0) {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte) 0, 0, 255, 16);
            }
            updateParkUi(null, context);
        }
        if (isRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mDisableData2 = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(255, iArr[8], iArr[9], iArr[10], iArr[11]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(255, iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x73AirData(Context context) {
        if (isAirDataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            Log.i("cbc", "AC Date: " + GeneralAirData.ac);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
            GeneralAirData.front_left_temperature = resolverAirTemperature(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolverAirTemperature(this.mCanBusInfoInt[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
            updateOutDoorTemp(context, resolverOutdoorTemperature(context, this.mCanBusInfoInt[8]));
            updateAirActivity(context, 1001);
        }
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0xF0VersionDate() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x47MasterAssistData(Context context) {
        this.a = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
        this.b = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15]);
        this.c = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15])));
        arrayList.add(new PanoramicBtnUpdateEntity(2, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, context);
    }

    private void set0x12CarInfo(final Context context) {
        String str = this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6];
        String str2 = this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, str + " L/100KM"));
        arrayList.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[9] + " L"));
        arrayList.add(new DriverUpdateEntity(0, 3, str2 + " V"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int i = this.mCanBusInfoInt[8];
        if (i <= 0 || i == this.memory) {
            return;
        }
        this.memory = i;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new WarningEntity(getWarning(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]), 1)));
        arrayList2.add(new WarningEntity(getWarning(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]), 2)));
        arrayList2.add(new WarningEntity(getWarning(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]), 3)));
        arrayList2.add(new WarningEntity(getWarning(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]), 4)));
        if (arrayList2.size() > 0) {
            startWarningActivity(context);
        }
        GeneralWarningDataData.dataList = arrayList2;
        updateWarningActivity(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._208.MsgMgr.1
            @Override // java.lang.Runnable
            public void run() {
                if (SystemUtil.isForeground(context, WarningActivity.class.getName())) {
                    MsgMgr.this.finishActivity();
                }
            }
        }, 5000L);
    }

    private void set0x13ParkingVentilation() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = iArr[11] | (iArr[10] << 8);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 4, i + " KM"));
        arrayList.add(new DriverUpdateEntity(0, 5, i2 + " RPM"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x42RadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mDisableData2 = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRightRadarLocationData(255, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setLeftRadarLocationData(255, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005a A[LOOP:0: B:20:0x0058->B:21:0x005a, LOOP_END] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9) {
        /*
            r4 = this;
            super.radioInfoChange(r5, r6, r7, r8, r9)
            java.lang.String r5 = "FM1"
            boolean r5 = r6.contains(r5)
            java.lang.String r8 = "KHz"
            r9 = 2
            r0 = 3
            r1 = 1
            r2 = 0
            java.lang.String r3 = "MHz"
            if (r5 == 0) goto L16
            r5 = r1
        L14:
            r8 = r3
            goto L40
        L16:
            java.lang.String r5 = "FM2"
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L20
            r5 = r9
            goto L14
        L20:
            java.lang.String r5 = "FM3"
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L2a
            r5 = r0
            goto L14
        L2a:
            java.lang.String r5 = "AM1"
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L34
            r5 = 4
            goto L40
        L34:
            java.lang.String r5 = "AM2"
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto L3e
            r5 = 5
            goto L40
        L3e:
            r5 = r2
            goto L14
        L40:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r8)
            java.lang.String r6 = r6.toString()
            int r7 = r6.length()
            int r7 = 12 - r7
            r8 = r2
        L58:
            if (r8 >= r7) goto L70
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r6 = r3.append(r6)
            java.lang.String r3 = " "
            java.lang.StringBuilder r6 = r6.append(r3)
            java.lang.String r6 = r6.toString()
            int r8 = r8 + 1
            goto L58
        L70:
            byte[] r7 = new byte[r0]
            r8 = 22
            r7[r2] = r8
            r8 = -46
            r7[r1] = r8
            byte r5 = (byte) r5
            r7[r9] = r5
            byte[] r5 = r6.getBytes()
            byte[] r5 = com.hzbhd.canbus.util.DataHandleUtils.byteMerger(r7, r5)
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
            byte[] r5 = new byte[r0]
            r5 = {x009c: FILL_ARRAY_DATA , data: [22, -30, 0} // fill-array
            java.lang.String r6 = "Radio Media!"
            byte[] r6 = r6.getBytes()
            byte[] r5 = com.hzbhd.canbus.util.DataHandleUtils.byteMerger(r5, r6)
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._208.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        this.m0xE2Command = new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), this.m0xE2Command);
        this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (i6 != 6 ? i6 == 1 ? 7 : 0 : 6)}, String.format("%03d/%03d     ", Integer.valueOf(i4), Integer.valueOf(i5)).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), this.m0xD2Command);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, String.format("%03d/%03d     ", Integer.valueOf((b6 * 256) + i), Integer.valueOf(i2)).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), this.m0xD2Command);
        this.m0xE2Command = DataHandleUtils.byteMerger(new byte[]{22, -30, 0}, String.format("%02d:%02d:%02d    ", Byte.valueOf(b3), Byte.valueOf(b4), Byte.valueOf(b5)).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), this.m0xE2Command);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, String.format("%03d/%03d     ", Integer.valueOf((b7 * 256) + i), Integer.valueOf(i2)).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), this.m0xD2Command);
        this.m0xE2Command = DataHandleUtils.byteMerger(new byte[]{22, -30, 0}, String.format("%02d:%02d:%02d    ", Byte.valueOf(b3), Byte.valueOf(b4), Byte.valueOf(b5)).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), this.m0xE2Command);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -29, 0}, bArr), 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -29, 0}, bArr), 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        byte[] bArr2 = this.m0xE2Command;
        if (bArr2 != null) {
            CanbusMsgSender.sendMsg(bArr2);
        }
        byte[] bArr3 = this.m0xD2Command;
        if (bArr3 != null) {
            CanbusMsgSender.sendMsg(bArr3);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    private boolean isWarningMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusWarnInfoCopy)) {
            return true;
        }
        this.mCanBusWarnInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isWarnFirst) {
            return false;
        }
        isWarnFirst = false;
        return true;
    }

    private boolean isWarningDataChange() {
        if (Arrays.equals(this.mWarningDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mWarningDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isAirDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[9] = 0;
        if (Arrays.equals(this.mAirData, bArr)) {
            return false;
        }
        byte[] bArr2 = this.mCanBusInfoByte;
        this.mAirData = Arrays.copyOf(bArr2, bArr2.length);
        return true;
    }

    private String getWarning(boolean z, int i) {
        return z ? CommUtil.getStrByResId(this.mContext, "_208_warning_" + i) : "";
    }

    private boolean isDoorChange() {
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

    private String resolverOutdoorTemperature(Context context, int i) {
        return ((i * 0.5d) - 40.0d) + getTempUnitC(context);
    }

    private String resolverAirTemperature(int i) {
        return i == 0 ? "" : i == 1 ? "LOW" : i == 255 ? "HIGH" : (18 > i || i > 26) ? "" : i + getTempUnitC(this.mContext);
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[6], bArr[7]};
        if (Arrays.equals(this.mTrackData, bArr2)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private boolean isRadarDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 8, bArr.length);
        if (Arrays.equals(this.mRadarData, bArrCopyOfRange)) {
            return false;
        }
        this.mRadarData = Arrays.copyOf(bArrCopyOfRange, bArrCopyOfRange.length);
        return true;
    }

    public Activity getCurrentActivity() {
        return getActivity();
    }
}
