package com.hzbhd.canbus.car._455;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    Context mContext;
    int[] mTireErrorInfo;
    int[] mTireInfo;
    int[] mTrackData;
    DecimalFormat df = new DecimalFormat("###0.0");
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    private String[] arr0 = new String[2];
    private String[] arr1 = new String[2];
    private String[] arr2 = new String[2];
    private String[] arr3 = new String[2];
    private int leftFrontError = 0;
    private int rightFrontError = 0;
    private int leftRearError = 0;
    private int rightRearError = 0;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        GeneralTireData.isHaveSpareTire = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setAir0x11(byteArrayToIntArray);
            return;
        }
        if (i == 33) {
            setSwc0x21(byteArrayToIntArray);
            return;
        }
        if (i == 40) {
            setDoor0x28(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            setTrack0x30(byteArrayToIntArray);
            return;
        }
        if (i == 127) {
            updateVersionInfo(this.mContext, getVersionStr(bArr));
        } else if (i == 56) {
            setTireInfo0x38(byteArrayToIntArray);
        } else {
            if (i != 57) {
                return;
            }
            setTireErrorInfo0x39(byteArrayToIntArray);
        }
    }

    private void updateDateTire() {
        List<TireUpdateEntity> list = this.tyreInfoList;
        if (list != null) {
            list.clear();
        }
        this.tyreInfoList.add(new TireUpdateEntity(0, this.leftFrontError, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, this.rightFrontError, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, this.leftRearError, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, this.rightRearError, this.arr3));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void setTireErrorInfo0x39(int[] iArr) {
        if (isTireInfoErrorChange(iArr)) {
            this.leftFrontError = 0;
            this.rightFrontError = 0;
            this.leftRearError = 0;
            this.rightRearError = 0;
            if (iArr[2] != 0) {
                this.leftFrontError = 1;
            }
            if (iArr[3] != 0) {
                this.rightFrontError = 1;
            }
            if (iArr[4] != 0) {
                this.leftRearError = 1;
            }
            if (iArr[5] != 0) {
                this.rightRearError = 1;
            }
            updateDateTire();
        }
    }

    private void setTireInfo0x38(int[] iArr) {
        if (isTireInfoChange(iArr)) {
            this.arr0[0] = this.mContext.getString(R.string._418_tire2) + ":" + (iArr[2] - 40) + getTempUnitC(this.mContext);
            this.arr0[1] = this.mContext.getString(R.string._418_tire1) + ":" + this.df.format(iArr[6] * 0.02745d) + "bar";
            this.arr1[0] = this.mContext.getString(R.string._418_tire2) + ":" + (iArr[3] - 40) + getTempUnitC(this.mContext);
            this.arr1[1] = this.mContext.getString(R.string._418_tire1) + ":" + this.df.format(iArr[7] * 0.02745d) + "bar";
            this.arr2[0] = this.mContext.getString(R.string._418_tire2) + ":" + (iArr[4] - 40) + getTempUnitC(this.mContext);
            this.arr2[1] = this.mContext.getString(R.string._418_tire1) + ":" + this.df.format(iArr[8] * 0.02745d) + "bar";
            this.arr3[0] = this.mContext.getString(R.string._418_tire2) + ":" + (iArr[5] - 40) + getTempUnitC(this.mContext);
            this.arr3[1] = this.mContext.getString(R.string._418_tire1) + ":" + this.df.format(iArr[9] * 0.02745d) + "bar";
            updateDateTire();
        }
    }

    private void setTrack0x30(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            int i = iArr[3];
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 7);
            if (DataHandleUtils.getBoolBit7(iArr[2])) {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) i, (byte) intFromByteWithBit, 0, 12000, 16);
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) i, (byte) intFromByteWithBit, 0, 12000, 16);
                updateParkUi(null, this.mContext);
            }
        }
    }

    private void setDoor0x28(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setSwc0x21(int[] iArr) {
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i != 18) {
            switch (i) {
                case 9:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
    }

    private void setAir0x11(int[] iArr) {
        iArr[7] = 0;
        if (isAirDataChange(iArr)) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(iArr[2]);
            int i = iArr[3];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
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
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            }
            GeneralAirData.front_wind_level = iArr[4];
            int i2 = iArr[5];
            if (i2 == 0) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i2 == 30) {
                GeneralAirData.front_left_temperature = "HI";
            } else {
                GeneralAirData.front_left_temperature = ((iArr[5] / 2.0f) + 17.0f) + getTempUnitC(this.mContext);
            }
            int i3 = iArr[6];
            if (i3 == 0) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i3 == 30) {
                GeneralAirData.front_right_temperature = "HI";
            } else {
                GeneralAirData.front_right_temperature = ((iArr[6] / 2.0f) + 17.0f) + getTempUnitC(this.mContext);
            }
            updateAirActivity(this.mContext, 1001);
        }
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = iArr;
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

    private boolean isTireInfoErrorChange(int[] iArr) {
        if (Arrays.equals(this.mTireErrorInfo, iArr)) {
            return false;
        }
        this.mTireErrorInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
