package com.hzbhd.canbus.car._420;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;
    private String TirePressure1000 = "NO INFO";
    private String TirePressure0100 = "NO INFO";
    private String TirePressure0010 = "NO INFO";
    private String TirePressure0001 = "NO INFO";
    private String TireTempture1000 = "NO INFO";
    private String TireTempture0100 = "NO INFO";
    private String TireTempture0010 = "NO INFO";
    private String TireTempture0001 = "NO INFO";
    private Boolean SenseStatus1000 = false;
    private Boolean SenseStatus0100 = false;
    private Boolean SenseStatus0010 = false;
    private Boolean SenseStatus0001 = false;
    private Boolean LowPressure1000 = false;
    private Boolean LowPressure0100 = false;
    private Boolean LowPressure0010 = false;
    private Boolean LowPressure0001 = false;
    private Boolean HighPressure1000 = false;
    private Boolean HighPressure0100 = false;
    private Boolean HighPressure0010 = false;
    private Boolean HighPressure0001 = false;
    List<TireUpdateEntity> MTire0 = new ArrayList();
    List<TireUpdateEntity> MTire1 = new ArrayList();
    List<TireUpdateEntity> MTire2 = new ArrayList();
    List<TireUpdateEntity> MTire3 = new ArrayList();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferentID = currentCanDifferentId;
        CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, (byte) currentCanDifferentId});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mDifferentID = getCurrentCanDifferentId();
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setWheelKey0x11();
            setTrackDate0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 56) {
            setTireInfo0x38();
        } else if (i == 57) {
            setTireInfo0x39();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setTireInfo0x39() {
        this.SenseStatus1000 = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        this.SenseStatus0100 = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        this.SenseStatus0010 = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        this.SenseStatus0001 = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        this.LowPressure1000 = Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        this.LowPressure0100 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        this.LowPressure0010 = Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        this.LowPressure0001 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
        this.HighPressure1000 = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        this.HighPressure0100 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        this.HighPressure0010 = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        this.HighPressure0001 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
        setTireInfo();
    }

    private void setTireInfo0x38() {
        this.TirePressure1000 = this.mCanBusInfoInt[6] + "kpa";
        this.TirePressure0100 = this.mCanBusInfoInt[7] + "kpa";
        this.TirePressure0010 = this.mCanBusInfoInt[8] + "kpa";
        this.TirePressure0001 = this.mCanBusInfoInt[9] + "kpa";
        this.TireTempture1000 = this.mCanBusInfoInt[2] + getTempUnitC(this.mContext);
        this.TireTempture0100 = this.mCanBusInfoInt[3] + getTempUnitC(this.mContext);
        this.TireTempture0010 = this.mCanBusInfoInt[4] + getTempUnitC(this.mContext);
        this.TireTempture0001 = this.mCanBusInfoInt[5] + getTempUnitC(this.mContext);
        setTireInfo();
    }

    private void setTireInfo() {
        GeneralTireData.isHaveSpareTire = false;
        this.MTire0.add(getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.SenseStatus1000.booleanValue(), this.LowPressure1000.booleanValue(), this.HighPressure1000.booleanValue()));
        this.MTire1.add(getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.SenseStatus0100.booleanValue(), this.LowPressure0100.booleanValue(), this.HighPressure0100.booleanValue()));
        this.MTire2.add(getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.SenseStatus0010.booleanValue(), this.LowPressure0010.booleanValue(), this.HighPressure0010.booleanValue()));
        this.MTire3.add(getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.SenseStatus0001.booleanValue(), this.LowPressure0001.booleanValue(), this.HighPressure0001.booleanValue()));
        GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
        updateTirePressureActivity(null);
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, boolean z, boolean z2, boolean z3) {
        String strByResId;
        int i2;
        String strByResId2;
        if (z) {
            strByResId = CommUtil.getStrByResId(this.mContext, "_420_item_2");
            i2 = 1;
        } else {
            strByResId = CommUtil.getStrByResId(this.mContext, "_420_item_3");
            i2 = 0;
        }
        if (z2) {
            strByResId2 = CommUtil.getStrByResId(this.mContext, "_420_item_4");
        } else if (z3) {
            strByResId2 = CommUtil.getStrByResId(this.mContext, "_420_item_5");
        } else {
            strByResId2 = CommUtil.getStrByResId(this.mContext, "no_warning_msg");
            return new TireUpdateEntity(i, i2, new String[]{str, str2, strByResId, strByResId2});
        }
        i2 = 1;
        return new TireUpdateEntity(i, i2, new String[]{str, str2, strByResId, strByResId2});
    }

    protected static <T> List<T> mergeList(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
            return;
        }
        if (i == 1) {
            realKeyClick0x11(7);
            return;
        }
        if (i == 2) {
            realKeyClick0x11(8);
            return;
        }
        if (i == 8) {
            realKeyClick0x11(45);
        } else if (i == 9) {
            realKeyClick0x11(46);
        } else {
            if (i != 12) {
                return;
            }
            realKeyClick0x11(2);
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setDoorInfo0x12() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
            updateDoorView(this.mContext);
        }
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }
}
