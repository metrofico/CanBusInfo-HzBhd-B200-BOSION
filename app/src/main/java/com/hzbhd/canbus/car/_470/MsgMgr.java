package com.hzbhd.canbus.car._470;

import android.content.Context;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class MsgMgr extends AbstractMsgMgr {
    private String airJsonStr;
    int[] mAirData;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");

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
    public void onHandshake(Context context) {
        super.onHandshake(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, final byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._470.MsgMgr$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m888lambda$canbusInfoChange$0$comhzbhdcanbuscar_470MsgMgr(bArr);
            }
        });
    }

    /* renamed from: lambda$canbusInfoChange$0$com-hzbhd-canbus-car-_470-MsgMgr, reason: not valid java name */
    /* synthetic */ Unit m888lambda$canbusInfoChange$0$comhzbhdcanbuscar_470MsgMgr(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (LogUtil.log5()) {
            LogUtil.d("canbusInfoChange(): " + getMsDataType(byteArrayToIntArray));
        }
        int msDataType = getMsDataType(byteArrayToIntArray);
        if (msDataType == 888) {
            ShareBasicInfo(getByteArrayToIntArray(bArr));
            return null;
        }
        if (msDataType != 928) {
            return null;
        }
        set0x3A0ParkingSensorInfo(byteArrayToIntArray);
        return null;
    }

    protected void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportAllCanBusData(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void set0x3A0ParkingSensorInfo(final int[] iArr) {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._470.MsgMgr$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m889x1ec122b9(iArr);
            }
        });
    }

    /* renamed from: lambda$set0x3A0ParkingSensorInfo$1$com-hzbhd-canbus-car-_470-MsgMgr, reason: not valid java name */
    /* synthetic */ Unit m889x1ec122b9(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[7], 3, 3);
        if (LogUtil.log5()) {
            LogUtil.d("set0x3A0ParkingSensorInfo(): " + intFromByteWithBit);
        }
        PDCModeStatus(intFromByteWithBit);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 1);
        if (LogUtil.log5()) {
            LogUtil.d("set0x3A0ParkingSensorInfo(): " + intFromByteWithBit2);
        }
        PDCLed(intFromByteWithBit2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[13], 0, 1);
        if (LogUtil.log5()) {
            LogUtil.d("set0x3A0ParkingSensorInfo(): " + intFromByteWithBit3);
        }
        PDCECUFault(intFromByteWithBit3);
        PDCDistanceRl(DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 3));
        PDCDistanceRml(DataHandleUtils.getIntFromByteWithBit(iArr[10], 5, 3));
        PDCDistanceRr(DataHandleUtils.getIntFromByteWithBit(iArr[11], 5, 3));
        SensorFaultRl(DataHandleUtils.getIntFromByteWithBit(iArr[12], 4, 1));
        SensorFaultRml(DataHandleUtils.getIntFromByteWithBit(iArr[12], 5, 1));
        SensorFaultRr(DataHandleUtils.getIntFromByteWithBit(iArr[12], 7, 1));
        getUiMgr(this.mContext).refreshRadar();
        return null;
    }

    public void SensorFaultRml(int i) {
        MdRadarData.sensorFaultRml = i == 0;
    }

    public void SensorFaultRr(int i) {
        MdRadarData.sensorFaultRr = i == 0;
    }

    public void SensorFaultRl(int i) {
        MdRadarData.sensorFaultRl = i == 0;
    }

    public void PDCDistanceRr(int i) {
        switch (i) {
            case 0:
                MdRadarData.distanceRr = 0;
                break;
            case 1:
                MdRadarData.distanceRr = 1;
                break;
            case 2:
                MdRadarData.distanceRr = 2;
                break;
            case 3:
                MdRadarData.distanceRr = 3;
                break;
            case 4:
                MdRadarData.distanceRr = 4;
                break;
            case 5:
                MdRadarData.distanceRr = 5;
                break;
            case 6:
                MdRadarData.distanceRr = 6;
                break;
            case 7:
                MdRadarData.distanceRr = 7;
                break;
        }
    }

    public void PDCDistanceRml(int i) {
        switch (i) {
            case 0:
                MdRadarData.distanceRml = 0;
                break;
            case 1:
                MdRadarData.distanceRml = 1;
                break;
            case 2:
                MdRadarData.distanceRml = 2;
                break;
            case 3:
                MdRadarData.distanceRml = 3;
                break;
            case 4:
                MdRadarData.distanceRml = 4;
                break;
            case 5:
                MdRadarData.distanceRml = 5;
                break;
            case 6:
                MdRadarData.distanceRml = 6;
                break;
            case 7:
                MdRadarData.distanceRml = 7;
                break;
        }
    }

    public void PDCDistanceRl(int i) {
        switch (i) {
            case 0:
                MdRadarData.distanceRl = 0;
                break;
            case 1:
                MdRadarData.distanceRl = 1;
                break;
            case 2:
                MdRadarData.distanceRl = 2;
                break;
            case 3:
                MdRadarData.distanceRl = 3;
                break;
            case 4:
                MdRadarData.distanceRl = 4;
                break;
            case 5:
                MdRadarData.distanceRl = 5;
                break;
            case 6:
                MdRadarData.distanceRl = 6;
                break;
            case 7:
                MdRadarData.distanceRl = 7;
                break;
        }
    }

    public void PDCECUFault(int i) {
        if (i == 0) {
            MdRadarData.pdc_ecufault = true;
        } else {
            MdRadarData.pdc_ecufault = false;
        }
    }

    public void PDCLed(int i) {
        if (i == 0) {
            MdRadarData.pdc_led = false;
        } else {
            MdRadarData.pdc_led = true;
        }
    }

    public void PDCModeStatus(int i) {
        switch (i) {
            case 0:
                MdRadarData.reverse_radar_working_mode = 0;
                break;
            case 1:
                MdRadarData.reverse_radar_working_mode = 1;
                break;
            case 2:
                MdRadarData.reverse_radar_working_mode = 2;
                break;
            case 3:
                MdRadarData.reverse_radar_working_mode = 3;
                break;
            case 4:
                MdRadarData.reverse_radar_working_mode = 4;
                break;
            case 5:
                MdRadarData.reverse_radar_working_mode = 5;
                break;
            case 6:
                MdRadarData.reverse_radar_working_mode = 6;
                break;
            case 7:
                MdRadarData.reverse_radar_working_mode = 7;
                break;
        }
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

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTireInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTireInfo, iArr)) {
            return false;
        }
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mPanoramicInfo, iArr)) {
            return false;
        }
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
