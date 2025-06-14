package com.hzbhd.canbus.car._342;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    private int eachId;
    int[] m0x60Data;
    int[] mAirData;
    private int mCameraStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr uiMgr;
    int A = 0;
    public int nowTemLevel1 = 0;
    public int nowTemLevel2 = 0;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    int g = 0;

    private boolean resolveCycle(int i) {
        return i != 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        sendCarType();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            setWheelKeyInfo0x20();
            if (this.eachId == 1) {
                return;
            }
            setPanelKeyInfo0x20();
            return;
        }
        if (i == 33) {
            if (this.eachId == 1) {
                return;
            }
            setAirInfo0x21();
            return;
        }
        if (i == 36) {
            SingletonForKt.INSTANCE.setCarBodyData(this.mCanBusInfoInt);
            updateDoorView(context);
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 96) {
            if (this.eachId != 1) {
                return;
            }
            setPanoramicData0x60(context);
        } else if (i == 97 && this.eachId == 1) {
            setAVMState0x61();
        }
    }

    private void setWheelKeyInfo0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(45);
                break;
            case 4:
                buttonKey(46);
                break;
            case 5:
                buttonKey(HotKeyConstant.K_1_PICKUP);
                break;
            case 6:
                buttonKey(HotKeyConstant.K_2_HANGUP);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 10:
                int i = this.eachId;
                if (i == 2 || i == 7) {
                    int i2 = this.A;
                    if (i2 != 0) {
                        if (i2 == 1) {
                            Log.d("lai", this.A + "进来了3");
                            forceReverse(this.mContext, false);
                            this.A = 0;
                            Log.d("lai", this.A + "进来了4");
                            break;
                        }
                    } else {
                        Log.d("lai", this.A + "进来了1");
                        forceReverse(this.mContext, true);
                        this.A = 1;
                        Log.d("lai", this.A + "进来了2");
                        break;
                    }
                }
                break;
            case 11:
                buttonKey(49);
                break;
        }
    }

    private void setPanelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
        switch (i) {
            case 18:
                buttonKey(1);
                break;
            case 19:
                buttonKey(49);
                break;
            case 20:
                buttonKey(45);
                break;
            case 21:
                buttonKey(46);
                break;
            default:
                switch (i) {
                    case 32:
                        buttonKey(7);
                        break;
                    case 33:
                        buttonKey(8);
                        break;
                    case 34:
                        buttonKey(50);
                        break;
                    case 35:
                        buttonKey(151);
                        break;
                    case 36:
                        buttonKey(HotKeyConstant.K_DISP);
                        break;
                    case 37:
                        buttonKey(59);
                        break;
                    case 38:
                        buttonKey(52);
                        break;
                    case 39:
                        buttonKey(128);
                        break;
                    case 40:
                        buttonKey(3);
                        break;
                    case 41:
                        buttonKey(58);
                        break;
                    case 42:
                        buttonKey(30);
                        break;
                    case 43:
                        buttonKey(129);
                        break;
                    case 44:
                        buttonKey(4);
                        break;
                }
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirInfo0x21() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = resolveCycle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_defog = false;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_defog = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_defog = true;
        } else if (i == 5) {
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = true;
        }
        if (this.mCanBusInfoInt[4] == 0) {
            GeneralAirData.power = false;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        if (this.eachId != 7) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[5] + "Level";
            Log.e("lai3", "用了这个方法");
        } else {
            GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 0.0d, "C");
            Log.e("lai4", "用了这个方法");
        }
        int[] iArr = this.mCanBusInfoInt;
        this.nowTemLevel1 = iArr[5];
        int i2 = iArr[6];
        this.nowTemLevel2 = i2;
        if (this.eachId != 7) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[6] + "level";
            Log.e("lai5", "用了这个方法");
        } else {
            GeneralAirData.front_right_temperature = getTemperature(i2, 0.5d, 0.0d, "C");
            Log.e("lai6", "用了这个方法");
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setPanoramicData0x60(Context context) {
        if (this.eachId != 1) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, resolveVideoStatus(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(0, 1, resolveVideoStatus2(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1 && iArr[3] != 0) {
            FutureUtil.instance.detectParkPanoramic(true, this.mCanBusInfoInt[2]);
        } else {
            FutureUtil.instance.detectParkPanoramic(false, this.mCanBusInfoInt[2]);
        }
        ArrayList arrayList2 = new ArrayList();
        int i = this.mCanBusInfoInt[3];
        if (i == 3) {
            arrayList2.add(new PanoramicBtnUpdateEntity(0, true));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
        } else if (i == 4) {
            arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, true));
            arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
        } else if (i == 5) {
            arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(2, true));
            arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
        } else if (i == 6) {
            arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(3, true));
            arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
        }
        GeneralParkData.dataList = arrayList2;
        updateParkUi(null, this.mContext);
    }

    private String resolveVideoStatus2(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_1);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_8);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._342_setting_1_2);
        }
        if (i == 4) {
            return this.mContext.getResources().getString(R.string._342_setting_1_3);
        }
        if (i == 5) {
            return this.mContext.getResources().getString(R.string._342_setting_1_4);
        }
        if (i == 6) {
            return this.mContext.getResources().getString(R.string._342_setting_1_5);
        }
        if (i == 7) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_2);
        }
        if (i == 8) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_3);
        }
        if (i == 9) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_4);
        }
        if (i == 10) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_5);
        }
        if (i == 11) {
            return this.mContext.getResources().getString(R.string._342_setting_1_1_6);
        }
        return i == 12 ? this.mContext.getResources().getString(R.string._342_setting_1_1_7) : "";
    }

    private String resolveVideoStatus(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._342_setting_1_6_0_1);
        }
        return this.mContext.getResources().getString(R.string._342_setting_1_6_0_2);
    }

    private void setAVMState0x61() {
        this.a = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        this.b = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
        this.c = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
        this.d = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
        this.e = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
        this.f = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
        this.g = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    private String getTemperature(int i, double d, double d2, String str) {
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        sendCarType();
    }

    private void sendCarType() {
        if (this.eachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(0)});
        }
        if (this.eachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(1)});
        }
        if (this.eachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(2)});
        }
        if (this.eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(3)});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(4)});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte) getUiMgr(this.mContext).getCarModelData(5)});
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean is0x60DataChange() {
        if (Arrays.equals(this.m0x60Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x60Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
