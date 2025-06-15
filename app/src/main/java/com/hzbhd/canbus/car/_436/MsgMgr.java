package com.hzbhd.canbus.car._436;

import android.content.Context;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car._436.DvrDataKAdapter;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int[] dvrInt;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mPanoramicInfo;
    int[] mRadarData;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    DecimalFormat df_0Decimal = new DecimalFormat("###00");
    boolean nowRightLampState = false;
    boolean nowLeftLampState = false;
    private int nowRightLight = 0;
    byte noneData = 0;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(final Context context) throws RemoteException {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new DvrDataKAdapter.OnDvrDataReadListener() { // from class: com.hzbhd.canbus.car._436.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car._436.DvrDataKAdapter.OnDvrDataReadListener
            public final void onDataRead(byte[] bArr) {
                this.f$0.m858xb70a1f48(context, bArr);
            }
        });
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
        int msbLsbResult = getMsbLsbResult(byteArrayToIntArray[4], byteArrayToIntArray[5]);
        if (msbLsbResult == 768) {
            setData0x300();
            return;
        }
        if (msbLsbResult == 848) {
            setFCamera0x350();
            setLeftLamp(this.mCanBusInfoInt);
            setRightLamp(this.mCanBusInfoInt);
            return;
        }
        if (msbLsbResult == 856) {
            setData0x358();
            return;
        }
        if (msbLsbResult == 882) {
            setData0x372();
            return;
        }
        if (msbLsbResult == 886) {
            setData0x376();
        } else if (msbLsbResult == 888) {
            setAlr0x378();
        } else {
            if (msbLsbResult != 928) {
                return;
            }
            setRadar0x3A0();
        }
    }

    private void setRightLamp(int[] iArr) {
        if (this.nowRightLampState == DataHandleUtils.getBoolBit2(iArr[8])) {
            return;
        }
        boolean boolBit2 = DataHandleUtils.getBoolBit2(iArr[8]);
        this.nowRightLampState = boolBit2;
        turnRightLamp(boolBit2);
    }

    private void setLeftLamp(int[] iArr) {
        if (this.nowLeftLampState == DataHandleUtils.getBoolBit0(iArr[8])) {
            return;
        }
        boolean boolBit0 = DataHandleUtils.getBoolBit0(iArr[8]);
        this.nowLeftLampState = boolBit0;
        turnLeftLamp(boolBit0);
    }

    private void setData0x300() {
        if (isTrackInfoChange()) {
            return;
        }
        Log.d("EPS", "mCanBusInfoByte[11]--------------->" + ((int) this.mCanBusInfoByte[11]));
        Log.d("EPS", "mCanBusInfoByte[12]--------------->" + ((int) this.mCanBusInfoByte[12]));
        StringBuilder sbAppend = new StringBuilder().append("MSB LSB[11 12]--------------->");
        byte[] bArr = this.mCanBusInfoByte;
        Log.d("EPS", sbAppend.append(getMsbLsbResult(bArr[11], bArr[12])).toString());
        StringBuilder sbAppend2 = new StringBuilder().append("MSB LSB[12 11]--------------->");
        byte[] bArr2 = this.mCanBusInfoByte;
        Log.d("EPS", sbAppend2.append(getMsbLsbResult(bArr2[12], bArr2[11])).toString());
        DecimalFormat decimalFormat = this.df_0Decimal;
        byte[] bArr3 = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = Integer.parseInt(decimalFormat.format((((getMsbLsbResult(bArr3[11], bArr3[12]) / 10.0f) - 780.0f) / 30.0f) + 1.0f));
        updateParkUi(null, this.mContext);
        Log.d("EPS", "实际转角--------------->" + GeneralParkData.trackAngle);
    }

    private void setData0x358() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
        if (intFromByteWithBit == 0 || intFromByteWithBit == 1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_6"), 0));
        } else if (intFromByteWithBit == 2 || intFromByteWithBit == 3 || intFromByteWithBit == 4) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_6"), 1));
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setFCamera0x350() {
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) != this.nowRightLight) {
            switchFCamera(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) == 1);
            this.nowRightLight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
        }
    }

    private void setRadar0x3A0() {
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3), 0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3));
            RadarInfoUtil.setRearRadarLocationData(7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 3));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setAlr0x378() {
        if (DataHandleUtils.getBoolBit(0, this.mCanBusInfoInt[8])) {
            updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + getTempUnitC(this.mContext));
        }
        int[] iArr = this.mCanBusInfoInt;
        iArr[7] = 0;
        iArr[8] = 0;
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7) == 34) {
            GeneralAirData.center_wheel = "LO";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7) == 64) {
            GeneralAirData.center_wheel = "HI";
        } else {
            GeneralAirData.center_wheel = (this.mCanBusInfoInt[10] / 2.0f) + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_window = false;
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 2) {
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 3) {
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_window = true;
        }
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12]);
        GeneralAirData.power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setData0x372() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2) - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setData0x376() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        new Message().what = 9090;
        str.hashCode();
        switch (str) {
            case "front.right.seat.heat.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -98, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "skylight.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 24, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "skylight.stop":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 56, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "skylight.tiltup":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 72, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "ac.open":
                getUiMgr(this.mContext).sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(3, 0, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                break;
            case "front.left.seat.heat.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -80, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "tailgate.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 5, 9, ByteCompanionObject.MIN_VALUE});
                break;
            case "sun.visor.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 104, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "front.right.seat.heat.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -122, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "front.left.seat.heat.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, -77, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "skylight.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 40, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "ac.close":
                getUiMgr(this.mContext).sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(1, 128, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                break;
            case "all.windows.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -1, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "sun.visor.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 88, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "sun.visor.stop":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, ByteCompanionObject.MAX_VALUE, 120, 0, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
            case "tailgate.close":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, 8, 0, 69, 9, ByteCompanionObject.MIN_VALUE});
                break;
            case "all.windows.open":
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, ByteCompanionObject.MAX_VALUE, -120, -86, -74, 8, ByteCompanionObject.MIN_VALUE});
                break;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) throws UnsupportedEncodingException {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        try {
            getUiMgr(this.mContext).sendMediaInfo(8, 0, 17, 1, str4.getBytes("UTF-8"));
            getUiMgr(this.mContext).sendMediaInfo(8, 0, 18, 1, str6.getBytes("UTF-8"));
            getUiMgr(this.mContext).sendMediaInfo(8, 0, 19, 1, str5.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) throws UnsupportedEncodingException {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        try {
            getUiMgr(this.mContext).sendMediaInfo(8, 0, 20, 1, (str2 + ":" + str3 + ":" + str4).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws UnsupportedEncodingException {
        super.radioInfoChange(i, str, str2, str3, i2);
        try {
            getUiMgr(this.mContext).sendMediaInfo(1, 0, 1, 1, (str + " " + str2).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) throws RemoteException {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        DvrSender.send(new byte[]{35, 0});
        DvrSender.send(new byte[]{35, 8});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: DvrModular, reason: merged with bridge method [inline-methods] */
    public void m858xb70a1f48(Context context, byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(SplicingByte(new byte[]{0}, bArr));
        if (byteArrayToIntArray[1] == 170 && byteArrayToIntArray[2] == 77 && byteArrayToIntArray[3] == 68) {
            int i = byteArrayToIntArray[5];
            if (i == 95) {
                set0x5F(byteArrayToIntArray);
                return;
            }
            if (i == 112) {
                set0x70(byteArrayToIntArray);
                return;
            }
            if (i != 255) {
                switch (i) {
                    case 32:
                        set0x20(byteArrayToIntArray);
                        break;
                    case 33:
                        set0x21(byteArrayToIntArray);
                        break;
                    case 34:
                        set0x22();
                        break;
                    default:
                        switch (i) {
                            case 80:
                                set0x50(byteArrayToIntArray[6]);
                                break;
                            case 81:
                                set0x51(byteArrayToIntArray[6]);
                                break;
                            case 82:
                                set0x52(byteArrayToIntArray[6]);
                                break;
                            case 83:
                                set0x53(byteArrayToIntArray[6]);
                                break;
                            case 84:
                                set0x54(byteArrayToIntArray[6]);
                                break;
                            case 85:
                                set0x55(byteArrayToIntArray[6]);
                                break;
                            case 86:
                                set0x56(byteArrayToIntArray[6]);
                                break;
                            case 87:
                                set0x57(byteArrayToIntArray[6]);
                                break;
                            case 88:
                                set0x58(byteArrayToIntArray[6], byteArrayToIntArray[7], byteArrayToIntArray[8], byteArrayToIntArray[9]);
                                break;
                            case 89:
                                set0x59(byteArrayToIntArray[6], byteArrayToIntArray[7], byteArrayToIntArray[8], byteArrayToIntArray[9]);
                                break;
                            case 90:
                                set0x5A(byteArrayToIntArray);
                                break;
                            case 91:
                                set0x5B();
                                break;
                            case 92:
                                set0x5C(byteArrayToIntArray);
                                break;
                        }
                }
                return;
            }
            set0xFF();
        }
    }

    private void set0x70(int[] iArr) {
        int i = 0;
        switch (iArr[6]) {
            case 1:
                GeneralDvrFile.getInstance().item1.clear();
                int length = iArr.length;
                while (i < length) {
                    GeneralDvrFile.getInstance().item1.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 2:
                GeneralDvrFile.getInstance().item2.clear();
                int length2 = iArr.length;
                while (i < length2) {
                    GeneralDvrFile.getInstance().item2.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 3:
                GeneralDvrFile.getInstance().item3.clear();
                int length3 = iArr.length;
                while (i < length3) {
                    GeneralDvrFile.getInstance().item3.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 4:
                GeneralDvrFile.getInstance().item4.clear();
                int length4 = iArr.length;
                while (i < length4) {
                    GeneralDvrFile.getInstance().item4.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 5:
                GeneralDvrFile.getInstance().item5.clear();
                int length5 = iArr.length;
                while (i < length5) {
                    GeneralDvrFile.getInstance().item5.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
            case 6:
                GeneralDvrFile.getInstance().item6.clear();
                int length6 = iArr.length;
                while (i < length6) {
                    GeneralDvrFile.getInstance().item6.add(Integer.valueOf(iArr[i]));
                    i++;
                }
                NotifyBuilding.getInstance().updateUi();
                break;
        }
    }

    private void set0xFF() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._436.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_finish), 0).show();
            }
        });
    }

    private void set0x22() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._436.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.dvrInt[6] == 1) {
                    Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_connect1), 0).show();
                }
            }
        });
    }

    private void set0x5B() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._436.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                switch (MsgMgr.this.dvrInt[6]) {
                    case 128:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format0), 0).show();
                        GeneralDvrState.formatSdFail = true;
                        break;
                    case 129:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format1), 0).show();
                        GeneralDvrState.formatSdFail = false;
                        break;
                    case 130:
                        Toast.makeText(MsgMgr.this.mContext, MsgMgr.this.mContext.getString(R.string._436_DVR_State_sd_format2), 0).show();
                        GeneralDvrState.formatSdFail = false;
                        break;
                }
            }
        });
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x5A(int[] iArr) {
        Log.d("fxHou0x5A", "收到数据");
        try {
            GeneralDvrState.version = (iArr[6] + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + this.df_2Integer.format(iArr[7]) + "-" + this.df_2Integer.format(iArr[8]) + " V" + iArr[9];
        } catch (Exception e) {
            Log.d("fxHou0x5A", e.toString());
        }
        Log.d("fxHou0x5A", GeneralDvrState.version);
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x59(int i, int i2, int i3, int i4) {
        if (i == 2) {
            GeneralDvrState.time = this.df_2Integer.format(i2) + ":" + this.df_2Integer.format(i3) + ":" + this.df_2Integer.format(i4);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x58(int i, int i2, int i3, int i4) {
        if (i == 2) {
            GeneralDvrState.date = (i2 + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + this.df_2Decimal.format(i3) + "-" + this.df_2Decimal.format(i4);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x5F(int[] iArr) {
        set0x50(iArr[6]);
        set0x51(iArr[7]);
        set0x52(iArr[8]);
        set0x53(iArr[9]);
        set0x54(iArr[10]);
        set0x55(iArr[11]);
        set0x56(iArr[12]);
        set0x57(iArr[13]);
        set0x58(2, iArr[14], iArr[15], iArr[16]);
        set0x59(2, iArr[17], iArr[18], iArr[19]);
    }

    private void set0x5C(int[] iArr) {
        if (iArr[6] == 128) {
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._436.MsgMgr.4
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    Toast.makeText(MsgMgr.this.mContext, "Reset Fail!", 0).show();
                }
            });
        }
    }

    private void set0x57(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.timeFormat = 0;
                break;
            case 129:
                GeneralDvrSetting.timeFormat = 1;
                break;
            case 130:
                GeneralDvrSetting.timeFormat = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x56(int i) {
        if (i == 128) {
            GeneralDvrSetting.opticalFrequency = 0;
        } else if (i == 129) {
            GeneralDvrSetting.opticalFrequency = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x55(int i) {
        if (i == 128) {
            GeneralDvrSetting.gravitySensor = 0;
        } else if (i == 129) {
            GeneralDvrSetting.gravitySensor = 1;
        } else if (i == 131) {
            GeneralDvrSetting.gravitySensor = 2;
        } else if (i == 133) {
            GeneralDvrSetting.gravitySensor = 3;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x54(int i) {
        if (i == 128) {
            GeneralDvrSetting.dvrLanguage = 0;
        } else if (i == 129) {
            GeneralDvrSetting.dvrLanguage = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x53(int i) {
        if (i == 128) {
            GeneralDvrSetting.VideoRecordingVoice = 0;
        } else if (i == 129) {
            GeneralDvrSetting.VideoRecordingVoice = 1;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x52(int i) {
        if (i == 128) {
            GeneralDvrSetting.VideoRecordingSyncTime = 0;
        } else if (i == 129) {
            GeneralDvrSetting.VideoRecordingSyncTime = 1;
        } else if (i == 131) {
            GeneralDvrSetting.VideoRecordingSyncTime = 2;
        } else if (i == 133) {
            GeneralDvrSetting.VideoRecordingSyncTime = 3;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x51(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.timeTag = 0;
                break;
            case 129:
                GeneralDvrSetting.timeTag = 1;
                break;
            case 130:
                GeneralDvrSetting.timeTag = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x50(int i) {
        switch (i) {
            case 128:
                GeneralDvrSetting.resolvingPower = 0;
                break;
            case 129:
                GeneralDvrSetting.resolvingPower = 1;
                break;
            case 130:
                GeneralDvrSetting.resolvingPower = 2;
                break;
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x21(int[] iArr) {
        int i = iArr[6];
        if (i == 209) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_6);
        } else if (i == 210) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_7);
        } else if (i == 212) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_8);
        } else if (i == 216) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_9);
        } else if (i == 228) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_4);
        } else if (i == 232) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_5);
        } else if (i == 225) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_2);
        } else if (i == 226) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_3);
        } else if (i == 240) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_0);
        } else if (i == 241) {
            GeneralDvrPlayPage.controlState = this.mContext.getString(R.string._436_DVR_play_page_fun_3_1);
        }
        NotifyBuilding.getInstance().updateUi();
    }

    private void set0x20(int[] iArr) {
        GeneralDvrState.lock = DataHandleUtils.getBoolBit7(iArr[6]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[6], 5, 2);
        if (intFromByteWithBit == 0) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag0);
        } else if (intFromByteWithBit == 1) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag1);
        } else if (intFromByteWithBit == 2) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag2);
        } else if (intFromByteWithBit == 3) {
            GeneralDvrState.tag = this.mContext.getString(R.string._436_DVR_State_tag3);
        }
        GeneralDvrPlayPage.pageState = DataHandleUtils.getIntFromByteWithBit(iArr[6], 3, 2);
        GeneralDvrState.sd = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 2);
        int i = iArr[7];
        if (i == 0) {
            GeneralDvrPlayPage.time = this.mContext.getString(R.string._436_DVR_play_page_fun_2_1) + this.df_2Integer.format(iArr[8]) + ":" + this.df_2Integer.format(iArr[9]) + ":" + this.df_2Integer.format(iArr[10]);
        } else if (i == 1) {
            GeneralDvrPlayPage.time = this.mContext.getString(R.string._436_DVR_play_page_fun_2_0) + this.df_2Integer.format(iArr[8]) + ":" + this.df_2Integer.format(iArr[9]) + ":" + this.df_2Integer.format(iArr[10]);
        } else if (i == 255) {
            GeneralDvrPlayPage.time = " ";
        }
        NotifyBuilding.getInstance().updateUi();
    }
}
