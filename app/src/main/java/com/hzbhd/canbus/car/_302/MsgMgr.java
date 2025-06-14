package com.hzbhd.canbus.car._302;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int mMediaInfoData6;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mIsDoorFirst = true;
    private byte[] mMediaInfo = new byte[9];

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            setWheelKey0x01(context);
        }
        switch (i) {
            case 33:
                setAirData0x21(context);
                break;
            case 34:
                setBaseInfo0x22(context);
                break;
            case 35:
                setFrontRadarData0x23(context);
                break;
            case 36:
                setTrackData0x24(context);
                break;
            case 37:
                setRearRadarData0x25(context);
                break;
            case 38:
                setSpeedData0x26();
                break;
        }
    }

    private void setTrackData0x24(Context context) {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte) 0, 0, 50, 8);
        updateParkUi(null, context);
    }

    private void setWheelKey0x01(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick2(context, 0);
        }
        switch (i) {
            case 18:
                realKeyLongClick2(context, 46);
                break;
            case 19:
                realKeyLongClick2(context, 45);
                break;
            case 20:
                realKeyLongClick2(context, 7);
                break;
            case 21:
                realKeyLongClick2(context, 8);
                break;
            case 22:
                realKeyLongClick2(context, 3);
                break;
            case 23:
                realKeyLongClick2(context, HotKeyConstant.K_SPEECH);
                break;
            default:
                switch (i) {
                    case 80:
                        realKeyLongClick2(context, 14);
                        break;
                    case 81:
                        realKeyLongClick2(context, 15);
                        break;
                    case 82:
                        realKeyLongClick2(context, 2);
                        break;
                    case 83:
                        realKeyLongClick2(context, 52);
                        break;
                }
        }
    }

    private void setAirData0x21(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp(context, this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(context, this.mCanBusInfoInt[5]);
        GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        updateAirActivity(context, 1001);
    }

    private void setRearRadarData0x25(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void setFrontRadarData0x23(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void setBaseInfo0x22(Context context) {
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(context);
    }

    private void setSpeedData0x26() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[2] * 256) + iArr[3]).append(" km/h").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append(" rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr3[2] * 256) + iArr3[3]);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 0;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = -1;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = -1;
        bArr[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0013  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10) {
        /*
            r5 = this;
            super.radioInfoChange(r6, r7, r8, r9, r10)
            r7.hashCode()
            int r9 = r7.hashCode()
            r10 = 4
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = -1
            r4 = 0
            switch(r9) {
                case 64901: goto L41;
                case 64902: goto L36;
                case 69706: goto L2b;
                case 69707: goto L20;
                case 69708: goto L15;
                default: goto L13;
            }
        L13:
            r9 = r3
            goto L4b
        L15:
            java.lang.String r9 = "FM3"
            boolean r9 = r7.equals(r9)
            if (r9 != 0) goto L1e
            goto L13
        L1e:
            r9 = r10
            goto L4b
        L20:
            java.lang.String r9 = "FM2"
            boolean r9 = r7.equals(r9)
            if (r9 != 0) goto L29
            goto L13
        L29:
            r9 = r0
            goto L4b
        L2b:
            java.lang.String r9 = "FM1"
            boolean r9 = r7.equals(r9)
            if (r9 != 0) goto L34
            goto L13
        L34:
            r9 = r1
            goto L4b
        L36:
            java.lang.String r9 = "AM2"
            boolean r9 = r7.equals(r9)
            if (r9 != 0) goto L3f
            goto L13
        L3f:
            r9 = r2
            goto L4b
        L41:
            java.lang.String r9 = "AM1"
            boolean r9 = r7.equals(r9)
            if (r9 != 0) goto L4a
            goto L13
        L4a:
            r9 = r4
        L4b:
            switch(r9) {
                case 0: goto L5c;
                case 1: goto L59;
                case 2: goto L56;
                case 3: goto L53;
                case 4: goto L50;
                default: goto L4e;
            }
        L4e:
            r9 = r4
            goto L5e
        L50:
            r9 = 19
            goto L5e
        L53:
            r9 = 18
            goto L5e
        L56:
            r9 = 17
            goto L5e
        L59:
            r9 = 34
            goto L5e
        L5c:
            r9 = 33
        L5e:
            int[] r7 = r5.getFreqByteHiLo(r7, r8)
            byte[] r8 = r5.mMediaInfo
            r8[r4] = r2
            r8[r2] = r3
            byte r9 = (byte) r9
            r8[r1] = r9
            r9 = r7[r4]
            byte r9 = (byte) r9
            r8[r0] = r9
            r7 = r7[r2]
            byte r7 = (byte) r7
            r8[r10] = r7
            r7 = 5
            byte r6 = (byte) r6
            r8[r7] = r6
            r6 = 6
            int r7 = r5.mMediaInfoData6
            byte r7 = (byte) r7
            r8[r6] = r7
            r6 = 7
            r8[r6] = r3
            r6 = 8
            r8[r6] = r3
            byte[] r6 = new byte[r1]
            r6 = {x00b8: FILL_ARRAY_DATA , data: [22, -126} // fill-array
            byte[] r6 = com.hzbhd.canbus.util.DataHandleUtils.byteMerger(r6, r8)
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._302.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int[] time = getTime(i);
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 2;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = (byte) (i4 >> 8);
        bArr[4] = (byte) (i4 & 255);
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = (byte) time[1];
        bArr[8] = (byte) time[2];
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = b == 9 ? 3 : 4;
        byte[] bArr = this.mMediaInfo;
        bArr[0] = (byte) i4;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = b7;
        bArr[4] = (byte) i;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = b4;
        bArr[8] = b5;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        int i5 = b == 9 ? 3 : 4;
        byte[] bArr = this.mMediaInfo;
        bArr[0] = (byte) i5;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = b6;
        bArr[4] = (byte) i;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = b4;
        bArr[8] = b5;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 7;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = -1;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = -1;
        bArr[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        byte[] bArr2 = this.mMediaInfo;
        bArr2[0] = 9;
        bArr2[1] = -1;
        bArr2[2] = -1;
        bArr2[3] = -1;
        bArr2[4] = -1;
        bArr2[5] = -1;
        bArr2[6] = (byte) this.mMediaInfoData6;
        bArr2[7] = -1;
        bArr2[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr2));
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125, 28}, DataHandleUtils.makeBytesFixedLength(bArr, 12, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        byte[] bArr2 = this.mMediaInfo;
        bArr2[0] = 9;
        bArr2[1] = -1;
        bArr2[2] = -1;
        bArr2[3] = -1;
        bArr2[4] = -1;
        bArr2[5] = -1;
        bArr2[6] = (byte) this.mMediaInfoData6;
        bArr2[7] = -1;
        bArr2[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr2));
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125, 28}, DataHandleUtils.makeBytesFixedLength(bArr, 12, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 9;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = -1;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = -1;
        bArr[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 10;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = -1;
        bArr[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = this.mMediaInfo;
        bArr[0] = 10;
        bArr[1] = -1;
        bArr[2] = -1;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = -1;
        bArr[6] = (byte) this.mMediaInfoData6;
        bArr[7] = -1;
        bArr[8] = -1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.mMediaInfoData6, 2, z);
        this.mMediaInfoData6 = intByteWithBit;
        byte[] bArr2 = this.mMediaInfo;
        bArr2[6] = (byte) intByteWithBit;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.mMediaInfoData6, 3, z);
        this.mMediaInfoData6 = intByteWithBit;
        byte[] bArr = this.mMediaInfo;
        bArr[6] = (byte) intByteWithBit;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -126}, bArr));
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

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private String resolveAirTemp(Context context, int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(context);
    }
}
