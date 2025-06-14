package com.hzbhd.canbus.car._448;

import android.app.ActivityManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._448.air.AirData;
import com.hzbhd.canbus.car._448.dvr.DvrDataKAdapter;
import com.hzbhd.canbus.car._448.speech.SpeechAction;
import com.hzbhd.canbus.car._448.speech.SpeechReceive;
import com.hzbhd.canbus.car._448.speech.SpeechSend;
import com.hzbhd.canbus.car_cus._448.DvrController;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import com.hzbhd.canbus.car_cus._448.data.DvrMode;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr implements DvrDataInterface {
    private Context context;
    int[] mCanBusInfoInt;
    boolean bLeftFront = false;
    boolean bRightFront = false;
    boolean bLeftRear = false;
    boolean bRightRear = false;
    boolean bTrunk = false;
    boolean bHood = false;
    private int speed = 0;

    private String getSwitch(boolean z) {
        return z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : "off";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) throws RemoteException {
        super.initCommand(context);
        this.context = context;
        DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new DvrDataKAdapter.OnDvrDataReadListener() { // from class: com.hzbhd.canbus.car._448.MsgMgr.1
            @Override // com.hzbhd.canbus.car._448.dvr.DvrDataKAdapter.OnDvrDataReadListener
            public void onDataRead(byte[] bArr) throws RemoteException {
                DvrController.getInstance().setData(context, MsgMgr.this.getByteArrayToIntArray(bArr));
            }
        });
        SpeechReceive.INSTANCE.get().register(context);
        DvrObserver.getInstance().register(new DvrDataInterface() { // from class: com.hzbhd.canbus.car._448.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
            public final void dataChange(String str) throws RemoteException {
                this.f$0.dataChange(str);
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int dataType = getDataType(byteArrayToIntArray);
        if (dataType == 417) {
            setSpeedInfo(this.mCanBusInfoInt);
            return;
        }
        if (dataType == 544) {
            setEsp0x220(bArr);
            return;
        }
        if (dataType == 781) {
            setAir0x30D(this.mCanBusInfoInt);
            return;
        }
        if (dataType == 869) {
            setHood0x365(this.mCanBusInfoInt);
        } else if (dataType == 900) {
            setRadar0x384(this.mCanBusInfoInt);
        } else {
            if (dataType != 914) {
                return;
            }
            setDoor0x392(this.mCanBusInfoInt);
        }
    }

    private void setEsp0x220(byte[] bArr) {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[9], bArr[10], 0, 780, 16);
        ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_ANGLE, String.valueOf(GeneralParkData.trackAngle));
    }

    private void setRadar0x384(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[7], 3, 5);
        int intFromByteWithBit2 = ((DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 1) & 255) << 4) | (DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 4) & 255);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[10], 2, 5);
        int intFromByteWithBit4 = (DataHandleUtils.getIntFromByteWithBit(iArr[10], 7, 1) & 255) | ((DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 4) & 255) << 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 5);
        int intFromByteWithBit6 = (DataHandleUtils.getIntFromByteWithBit(iArr[11], 5, 3) & 255) | ((DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 2) & 255) << 3);
        if (intFromByteWithBit != 0) {
            intFromByteWithBit = (intFromByteWithBit / 3) + 1;
        }
        if (intFromByteWithBit > 10) {
            intFromByteWithBit = 10;
        }
        if (intFromByteWithBit2 != 0) {
            intFromByteWithBit2 = (intFromByteWithBit2 / 3) + 1;
        }
        if (intFromByteWithBit2 > 10) {
            intFromByteWithBit2 = 10;
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_RADAR_FRONT_JSON_INFO, "{\"left\"=" + intFromByteWithBit + ",\"leftMid\"=0,\"rightMid\"=0,\"right\"=" + intFromByteWithBit2 + "}");
        if (intFromByteWithBit3 != 0) {
            intFromByteWithBit3 = (intFromByteWithBit3 / 3) + 1;
        }
        if (intFromByteWithBit3 > 10) {
            intFromByteWithBit3 = 10;
        }
        if (intFromByteWithBit4 != 0) {
            intFromByteWithBit4 = (intFromByteWithBit4 / 3) + 1;
        }
        if (intFromByteWithBit4 > 10) {
            intFromByteWithBit4 = 10;
        }
        if (intFromByteWithBit5 != 0) {
            intFromByteWithBit5 = (intFromByteWithBit5 / 3) + 1;
        }
        if (intFromByteWithBit5 > 10) {
            intFromByteWithBit5 = 10;
        }
        if (intFromByteWithBit6 != 0) {
            intFromByteWithBit6 = (intFromByteWithBit6 / 3) + 1;
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_RADAR_REAR_JSON_INFO, "{\"left\"=" + intFromByteWithBit4 + ",\"leftMid\"=" + intFromByteWithBit3 + ",\"rightMid\"=" + (intFromByteWithBit6 <= 10 ? intFromByteWithBit6 : 10) + ",\"right\"=" + intFromByteWithBit5 + "}");
    }

    private void setHood0x365(int[] iArr) {
        this.bHood = DataHandleUtils.getBoolBit6(iArr[11]);
        shareDoor();
    }

    private void setDoor0x392(int[] iArr) {
        this.bLeftFront = DataHandleUtils.getBoolBit4(iArr[8]);
        this.bRightFront = DataHandleUtils.getBoolBit5(iArr[8]);
        this.bLeftRear = DataHandleUtils.getBoolBit6(iArr[8]);
        this.bRightRear = DataHandleUtils.getBoolBit7(iArr[8]);
        this.bTrunk = DataHandleUtils.getBoolBit0(iArr[9]);
        shareDoor();
        boolean boolBit0 = DataHandleUtils.getBoolBit0(iArr[7]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(iArr[8]);
        String str = (boolBit0 && boolBit3) ? "double.flash" : "none";
        if (!boolBit0 && boolBit3) {
            str = "turn.right";
        }
        if (boolBit0 && !boolBit3) {
            str = "turn.left";
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_TURN_SIGNALS, (boolBit0 || boolBit3) ? str : "none");
    }

    private void shareDoor() {
        ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_DOOR_JSON_INFO, "{\"leftFront\":\"" + getSwitch(this.bLeftFront) + "\",\"rightFront\":\"" + getSwitch(this.bRightFront) + "\",\"leftRear\":\"" + getSwitch(this.bLeftRear) + "\",\"rightRear\":\"" + getSwitch(this.bRightRear) + "\",\"trunk\":\"" + getSwitch(this.bTrunk) + "\",\"hood\":\"" + getSwitch(this.bHood) + "\"}");
    }

    private void setSpeedInfo(int[] iArr) {
        if (this.speed != DataHandleUtils.getIntFromByteWithBit(iArr[10], 4, 4)) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[10], 4, 4);
            this.speed = intFromByteWithBit;
            ShareDataServiceImpl.setString(ShareConstants.SHARE_SLD_CANBUS_CAR_SPEED, String.valueOf(intFromByteWithBit * 0.05625d));
        }
    }

    private void setAir0x30D(int[] iArr) {
        Log.d("Air", "WindLevel=" + DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 4));
        AirData.wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 4);
        SpeechSend.windChange(DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 4));
        Log.d("Air", "Temp=" + (iArr[8] + 16));
        AirData.temp_value = iArr[8] + 16;
        SpeechSend.tempChange(iArr[8] + 16);
        boolean z = !DataHandleUtils.getBoolBit2(iArr[7]);
        SpeechSend.acStatus(Boolean.valueOf(z));
        Log.d("Air", "Ac   isAcOn=" + z);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[12], 5, 3);
        if (intFromByteWithBit == 0) {
            SpeechSend.acMode(SpeechAction.WindValueEnum.foot);
            Log.d("Air", "Mode foot");
        } else if (intFromByteWithBit == 1) {
            SpeechSend.acMode(SpeechAction.WindValueEnum.face);
            Log.d("Air", "Mode face");
        } else if (intFromByteWithBit == 2) {
            SpeechSend.acMode(SpeechAction.WindValueEnum.facefoot);
            Log.d("Air", "Mode facefoot");
        } else if (intFromByteWithBit == 3) {
            SpeechSend.acMode(SpeechAction.WindValueEnum.footdefrost);
            Log.d("Air", "Mode footWindow");
        } else if (intFromByteWithBit == 4) {
            SpeechSend.acMode(SpeechAction.WindValueEnum.defrost);
            Log.d("Air", "Mode Window");
        }
        SpeechSend.frontDefrost(Boolean.valueOf(intFromByteWithBit == 4));
        Log.d("Air", "FrontDefog  isOn=" + (intFromByteWithBit == 4));
        boolean boolBit1 = DataHandleUtils.getBoolBit1(iArr[7]);
        SpeechSend.behindDefrost(Boolean.valueOf(boolBit1));
        Log.d("Air", "RearDefog=" + boolBit1);
        boolean z2 = !DataHandleUtils.getBoolBit4(iArr[12]);
        SpeechSend.acLoop(Boolean.valueOf(z2));
        Log.d("Air", "Cycle=" + z2);
    }

    private int getDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) throws RemoteException {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        DvrSender.send(new byte[]{88, 1, (byte) (i - 2000), (byte) i3, (byte) i4});
        DvrSender.send(new byte[]{89, 1, (byte) i5, (byte) i6, (byte) i7});
    }

    @Override // com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface
    public void dataChange(String str) throws RemoteException {
        if (str.equals(DvrMode.SPEECH_START_DVR) && isNotDvrActivity()) {
            startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
        }
        if (str.equals(DvrMode.SPEECH_TAKE_PICTURE)) {
            if (isNotDvrActivity()) {
                startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
            }
            if (DvrData.systemMode == 1) {
                if (DvrData.videoStateIcon == 3 || (DvrData.systemMode == 1 && DvrData.videoStateIcon == 4)) {
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{92, -1});
                } else {
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{92, -1});
                }
                DvrData.systemMode = 2;
                DvrObserver.getInstance().dataChange(DvrMode.VIDEO_STATE_MODE);
                DvrSender.send(new byte[]{64, 37});
            }
        }
        if (str.equals(DvrMode.SPEECH_RECORD_START)) {
            if (isNotDvrActivity()) {
                startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
            }
            if (DvrData.systemMode == 1) {
                if (DvrData.videoStateIcon == 3 || (DvrData.systemMode == 1 && DvrData.videoStateIcon == 4)) {
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{92, -1});
                } else {
                    DvrSender.send(new byte[]{64, 35});
                    DvrSender.send(new byte[]{92, -1});
                }
                if (DvrData.videoStateIcon != 1) {
                    DvrSender.send(new byte[]{64, 36});
                }
            }
        }
        if (str.equals(DvrMode.SPEECH_RECORD_STOP)) {
            if (isNotDvrActivity()) {
                startOtherActivity(this.context, HzbhdComponentName.SldDvrActivity);
            }
            if (DvrData.videoStateIcon == 3 || (DvrData.systemMode == 1 && DvrData.videoStateIcon == 4)) {
                DvrSender.send(new byte[]{64, 35});
                DvrSender.send(new byte[]{64, 35});
                DvrSender.send(new byte[]{92, -1});
            } else {
                DvrSender.send(new byte[]{64, 35});
                DvrSender.send(new byte[]{92, -1});
            }
            if (DvrData.videoStateIcon == 1) {
                DvrSender.send(new byte[]{64, 36});
            }
        }
    }

    public boolean isNotDvrActivity() {
        return !((ActivityManager) this.context.getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName().equals("com.hzbhd.canbus.car_cus._448.activity.DvrActivity");
    }
}
