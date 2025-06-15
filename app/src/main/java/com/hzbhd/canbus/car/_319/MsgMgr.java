package com.hzbhd.canbus.car._319;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;


public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanbusInfoInt = getByteArrayToIntArray(bArr);
        this.mCanbusInfoByte = bArr;
        byte b = bArr[1];
        if (b == 32) {
            set0x20WheelKeyInfo(context);
            return;
        }
        if (b == 36) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            set0x24BaseData(context);
        } else if (b == 41) {
            set0x29TrackData(context);
        } else {
            if (b != 48) {
                return;
            }
            set0x30VersionData();
        }
    }

    private void set0x20WheelKeyInfo(Context context) {
        int[] iArr = this.mCanbusInfoInt;
        int i = 2;
        switch (iArr[2]) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                i = 46;
                break;
            case 4:
                i = 45;
                break;
            case 5:
            case 6:
            default:
                i = 0;
                break;
            case 7:
                break;
            case 8:
                i = HotKeyConstant.K_SPEECH;
                break;
            case 9:
                i = 14;
                break;
            case 10:
                i = 15;
                break;
        }
        realKeyLongClick1(context, i, iArr[3]);
    }

    private void set0x24BaseData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private void set0x30VersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanbusInfoByte));
    }

    private void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 11776, 16);
            Log.i("cbc", "set0x29TackAngle =" + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
    }
}
