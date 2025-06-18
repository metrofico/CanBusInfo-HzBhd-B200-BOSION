package com.hzbhd.canbus.car._481;

import android.content.Context;
import android.util.Log;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.midware.constant.HotKeyConstant;


public class MsgMgr extends AbstractMsgMgr {
    Context mContext;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private UiMgr mUiMgr;
    private int lastDoorStatus = -1;

    @Override
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mContext = context;
        mUiMgr = getUiMgr(this.mContext);
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 1, 0, 0, 0, 0});
    }

    @Override
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override
    public void canbusInfoChange(Context context, byte[] msgReceived) {
        super.canbusInfoChange(context, msgReceived);
        this.mCanBusInfoByte = msgReceived;
        this.mCanBusInfoInt = getByteArrayToIntArray(msgReceived);
        int id = mCanBusInfoInt[1];
        if (id == 0x30) {
            set0x30VersionInfo();
        }
        if (id == 0x20) {
            set0x20WheelKey(mContext);
        }
        int doorByte = -1;

        if (id == 0x25) {
            doorByte = mCanBusInfoInt[2];
        } else if (id == 0x7D && mCanBusInfoInt[2] == 0x05) {
            doorByte = mCanBusInfoInt[3];
        }

        if (doorByte != -1 && doorByte != lastDoorStatus) {
            lastDoorStatus = doorByte;
            updateDoorState(doorByte);
            logDoorState(doorByte);
        }

        Log.d("MsgMgrSWM", "CanbusInfoChange received");
    }

    private void updateDoorState(int status) {
        GeneralDoorData.isLeftFrontDoorOpen = (status & 0x40) != 0;
        GeneralDoorData.isRightFrontDoorOpen = (status & 0x80) != 0;
        GeneralDoorData.isLeftRearDoorOpen = (status & 0x10) != 0;
        GeneralDoorData.isRightRearDoorOpen = (status & 0x20) != 0;
        GeneralDoorData.isBackOpen = (status & 0x08) != 0;
        GeneralDoorData.isFrontOpen = (status & 0x04) != 0;
        updateDoorView(mContext);
    }

    private void logDoorState(int status) {
        Log.d("DoorStatus", String.format("Estado 0x%02X | Izq. Delantera: %b | Der. Delantera: %b | Izq. Trasera: %b | Der. Trasera: %b | Maletero: %b | Capó: %b", status, GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen, GeneralDoorData.isFrontOpen));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    private void set0x30VersionInfo() {
        Log.d("Swm", "Version car received");
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    public void _0x20DoorInfo(byte[] msgReceived) {
        if (msgReceived[1] == 0x35) {
            GeneralDoorData.isLeftFrontDoorOpen = true;
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x20WheelKey(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int keyCode = iArr[2];
        int keyState = iArr[3];

        Log.d("WheelKey", String.format("KEY_CODE: 0x%02X (%d), STATE: 0x%02X", keyCode, keyCode, keyState));

        switch (keyCode) {
            case 0x00:
                realKeyLongClick1(context, 0x00, keyState);
                break;
            case 0x01:
                realKeyLongClick1(context, 0x07, keyState);
                break;
            case 0x02:
                realKeyLongClick1(context, 0x08, keyState);
                break;
            case 0x03:
                buttonKey(0x2D);
                break;
            case 0x04:
                buttonKey(0x2E);
                break;
            case 0x05:
                realKeyClick2(context, 0x0E, keyCode, keyState);
                break;
            case 0x06:
                realKeyClick2(context, 0x03, keyCode, keyState);
                break;
            case 0x07:
                realKeyClick2(context, 0x02, keyCode, keyState);
                break;
            case 0x08:
                realKeyClick2(context, 0x0F, keyCode, keyState);
                break;
            case 0x09:
                realKeyClick2(context, HotKeyConstant.K_SLEEP, keyCode, keyState);
                break;
            case 0x10:
                realKeyClick1(context, 0x80, keyCode, keyState);
                break;
            case 0x11:
                realKeyClick1(context, 0x34, keyCode, keyState);
                break;
            case 0x12:
                realKeyClick1(context, 0x3D, keyCode, keyState);
                break;
            case 0x13:
                realKeyClick1(context, 0x3A, keyCode, keyState);
                break;
            case 0x14:
                realKeyClick1(context, 0x3B, keyCode, keyState);
                break;
            case 0x15:
                realKeyClick1(context, HotKeyConstant.K_SPEECH, keyCode, keyState);
                break;
            case 0x16:
                realKeyClick1(context, HotKeyConstant.K_1_PICKUP, keyCode, keyState);
                break;
            case 0x17:
                realKeyClick1(context, 0x32, keyCode, keyState);
                break;
        }
    }


    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
