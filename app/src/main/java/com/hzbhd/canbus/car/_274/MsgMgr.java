package com.hzbhd.canbus.car._274;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_274_LEFT_CAMERA_INPUT = "share_274_left_camera_input";
    static final String SHARE_274_RIGHT_CAMERA_INPUT = "share_274_right_camera_input";
    private static boolean is0xE8First = true;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private byte[] mCanBus0xE8InfoCopy;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private CameraHelper mLeftCameraHelper;
    private int mLeftCameraStatus;
    private CameraHelper mRightCameraHelper;
    private int mRightCameraStatus;
    private final String _274_IS_LEFT_FRONT_DOOR_OPEN = "_274_is_left_front_door_open";
    private final String _274_IS_RIGHT_FRONT_DOOR_OPEN = "_274_is_right_front_door_open";
    private final String _274_IS_RIGHT_REAR_DOOR_OPEN = "_274_is_right_rear_door_open";
    private final String _274_IS_LEFT_REAR_DOOR_OPEN = "_274_is_left_rear_door_open";
    private final String _274_IS_BACK_OPEN = "_274_is_back_open";

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        senMsg(17);
        senMsg(18);
        senMsg(49);
        senMsg(50);
        senMsg(65);
        senMsg(NfDef.STATE_3WAY_M_HOLD);
        senMsg(97);
        initFrontCamera(context);
    }

    private void senMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            keyControl0x11();
            return;
        }
        if (i == 26) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData();
            return;
        }
        if (i == 30) {
            driveData0x1e();
            return;
        }
        if (i == 33) {
            setPanelButton();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
        } else if (i == 120) {
            setCarSetData();
        } else if (i == 232) {
            setOriginalCameraStatusInfo();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo();
        }
    }

    private void keyControl0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick1(0);
        } else if (i == 1) {
            realKeyClick1(7);
        } else if (i == 2) {
            realKeyClick1(8);
        } else if (i == 11) {
            realKeyClick1(2);
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[4], iArr[5]);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorData() {
        GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        if (GeneralDoorData.isShowCarDoor && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "_274_is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_274_is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_274_is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_274_is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_274_is_back_open", GeneralDoorData.isBackOpen);
            updateDoorView(this.mContext);
        }
        String[] strArr = {!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? "close" : "open", !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ? "close" : "open", !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]) ? "close" : "open", DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]) ? "open" : "close"};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            arrayList.add(new DriverUpdateEntity(1, i, strArr[i]));
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb.append((iArr[11] * 256) + iArr[12]).append(" rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "_274_is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_274_is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_274_is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_274_is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_274_is_back_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private void setPanelButton() {
        int i = this.mCanBusInfoInt[2];
        if (i == 2) {
            realKeyClick4(21);
            return;
        }
        if (i == 3) {
            realKeyClick4(20);
            return;
        }
        if (i == 6) {
            realKeyClick4(50);
            return;
        }
        if (i == 9) {
            realKeyClick4(3);
            return;
        }
        if (i == 47) {
            realKeyClick4(151);
            return;
        }
        if (i == 43) {
            realKeyClick4(52);
            return;
        }
        if (i == 44) {
            realKeyClick4(2);
        } else if (i == 69) {
            realKeyClick4(7);
        } else {
            if (i != 70) {
                return;
            }
            realKeyClick4(8);
        }
    }

    private void realKeyClick4(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarSetData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(0, this.mCanBusInfoInt[9])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOriginalCameraStatusInfo() {
        int i = this.mRightCameraStatus;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.mRightCameraStatus = i2;
            if (i2 == 1) {
                this.mRightCameraHelper.open();
            } else if (i2 == 0) {
                this.mRightCameraHelper.close();
            }
        }
        int i3 = this.mLeftCameraStatus;
        int i4 = this.mCanBusInfoInt[6];
        if (i3 != i4) {
            this.mLeftCameraStatus = i4;
            if (i4 == 1) {
                this.mLeftCameraHelper.open();
            } else if (i4 == 0) {
                this.mLeftCameraHelper.close();
            }
        }
    }

    private void driveData0x1e() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[5] * 256 * 256) + (iArr[6] * 256) + iArr[7]).append("Km").toString()));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, (((iArr2[8] * 256) + iArr2[9]) % Priority.DEBUG_INT) + "Km"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb2.append((iArr3[10] * 256) + iArr3[11]).append("Km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusAirInfoCopy)) {
            return true;
        }
        this.mCanBusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusDoorInfoCopy)) {
            return true;
        }
        this.mCanBusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean is0xE8MsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBus0xE8InfoCopy)) {
            return true;
        }
        this.mCanBus0xE8InfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!is0xE8First) {
            return false;
        }
        is0xE8First = false;
        return true;
    }

    private void initFrontCamera(Context context) {
        int intValue = SharePreUtil.getIntValue(context, SHARE_274_LEFT_CAMERA_INPUT, 0);
        int intValue2 = SharePreUtil.getIntValue(context, SHARE_274_RIGHT_CAMERA_INPUT, 0);
        CameraHelper cameraHelper = new CameraHelper(context);
        this.mLeftCameraHelper = cameraHelper;
        cameraHelper.setInput(getCameraInput(intValue));
        CameraHelper cameraHelper2 = new CameraHelper(context);
        this.mRightCameraHelper = cameraHelper2;
        cameraHelper2.setInput(getCameraInput(intValue2));
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void setCameraInput(String str, int i) {
        str.hashCode();
        if (str.equals(SHARE_274_RIGHT_CAMERA_INPUT)) {
            this.mRightCameraHelper.setInput(getCameraInput(i));
        } else if (str.equals(SHARE_274_LEFT_CAMERA_INPUT)) {
            this.mLeftCameraHelper.setInput(getCameraInput(i));
        }
        SharePreUtil.setIntValue(this.mContext, str, i);
    }

    private ComponentName getCameraInput(int i) {
        if (i == 0) {
            return Constant.AuxInActivity;
        }
        if (i != 1) {
            return null;
        }
        return Constant.FCameraActivity;
    }

    private class CameraHelper {
        private static final String TAG = "CameraHelper";
        private ComponentName mComponentName;
        private Context mContext;
        private Intent mIntent = new Intent();

        public CameraHelper(Context context) {
            this.mContext = context;
        }

        public void open() {
            if (isReverse()) {
                Log.w(TAG, "open: isReverse");
                return;
            }
            ComponentName componentName = this.mComponentName;
            if (componentName == null) {
                Log.w(TAG, "open: mComponentName is null");
                return;
            }
            this.mIntent.setComponent(componentName);
            this.mIntent.setFlags(268435456);
            this.mContext.startActivity(this.mIntent);
        }

        public void close() {
            if (isForeground(this.mContext, this.mComponentName.getClassName())) {
                MsgMgr.this.realKeyClick(this.mContext, 50);
            }
        }

        public void setInput(ComponentName componentName) {
            this.mComponentName = componentName;
        }

        private boolean isReverse() {
            return CommUtil.isMiscReverse();
        }

        private boolean isForeground(Context context, String str) throws SecurityException {
            if (context != null && !TextUtils.isEmpty(str)) {
                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
                if (!runningTasks.isEmpty() && str.equals(runningTasks.get(0).topActivity.getClassName())) {
                    return true;
                }
            }
            return false;
        }
    }
}
