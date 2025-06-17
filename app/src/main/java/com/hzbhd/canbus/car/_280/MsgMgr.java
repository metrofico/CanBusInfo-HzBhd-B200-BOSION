package com.hzbhd.canbus.car._280;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private final String _280_ISLEFT_FRONT_DOOR_OPEN = "_280_isleft_front_door_open";
    private final String _280_ISRIGHT_FRONT_DOOR_OPEN = "_280_isright_front_door_open";
    private final String _280_ISRIGHT_REAR_DOOR_OPEN = "_280_isright_rear_door_open";
    private final String _280_ISLEFT_REAR_DOOR_OPEN = "_280_isleft_rear_door_open";
    private final String _280_ISBACK_OPEN = "_280_isback_open";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte) SharePreUtil.getIntValue(context, "frontRear", 0), (byte) SharePreUtil.getIntValue(context, "leftRight", 0), (byte) SharePreUtil.getIntValue(context, "bandBass", 0), (byte) SharePreUtil.getIntValue(context, "bandMiddle", 0), (byte) SharePreUtil.getIntValue(context, "bandTreble", 0)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 36) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x24();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 104) {
            setEngine();
            return;
        }
        if (i == 106) {
            setCarSpeed();
            return;
        }
        if (i == 38) {
            setTrack();
            return;
        }
        if (i != 39) {
            switch (i) {
                case 32:
                    setSwc();
                    break;
                case 33:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x21();
                        break;
                    }
                    break;
                case 34:
                    setRearRadar();
                    break;
            }
            return;
        }
        setAirData0x27();
    }

    private void realKeyClick(int i) {
        realKeyClick2(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private void realKeyClick2(int i) {
        realKeyClick4(this.mContext, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setSwc() {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._280.MsgMgr.setSwc():void");
    }

    private void setWheel(int i, int i2) {
        for (int i3 = 0; i3 < i; i3++) {
            realKeyClick2(i2);
        }
    }

    private void setRearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "_280_isleft_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_280_isright_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_280_isright_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_280_isleft_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_280_isback_open", GeneralDoorData.isBackOpen);
            updateDoorView(this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        arrayList.add(new DriverUpdateEntity(0, 1, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "Not P" : "P"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte) 0, 0, 100, 8);
        updateParkUi(null, this.mContext);
    }

    private void setEngine() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, sb.append(iArr[2] + (iArr[3] * 256)).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb.append(iArr[2] + (iArr[3] * 256)).append("Km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "_280_isleft_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_280_isright_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_280_isright_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_280_isleft_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_280_isback_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private String resolveOutDoorTem() {
        byte b = this.mCanBusInfoByte[2];
        if (b < 0 || b > 87) {
            return b < 0 ? ((int) this.mCanBusInfoByte[2]) + getTempUnitC(this.mContext) : "";
        }
        return ((int) this.mCanBusInfoByte[2]) + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 30 == i ? "HI" : (1 > i || i > 29) ? "" : ((i * 0.5f) + 17.0f) + getTempUnitC(this.mContext);
    }

    void initAmplifierData(Context context) {
        GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, "_280_amplifier_fade", 0) - 10;
        GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, "_280_amplifier_balance", 0) - 10;
        GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, "_280_amplifier_bass", 0) - 10;
        GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, "_280_amplifier_middle", 0) - 10;
        GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, "_280_amplifier_treble", 0) - 10;
        updateAmplifierActivity(null);
        SharePreUtil.setIntValue(context, "frontRear", SharePreUtil.getIntValue(context, "_280_amplifier_fade", 0));
        SharePreUtil.setIntValue(context, "leftRight", SharePreUtil.getIntValue(context, "_280_amplifier_balance", 0));
        SharePreUtil.setIntValue(context, "bandBass", SharePreUtil.getIntValue(context, "_280_amplifier_bass", 0));
        SharePreUtil.setIntValue(context, "bandMiddle", SharePreUtil.getIntValue(context, "_280_amplifier_middle", 0));
        SharePreUtil.setIntValue(context, "bandTreble", SharePreUtil.getIntValue(context, "_280_amplifier_treble", 0));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }
}
