package com.hzbhd.canbus.car._22;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_22_LANGUAGE = "share_22_language";
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int mEachId;
    private int[] mFrontRadarData;
    private boolean mFrontStatus;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsMute;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private MyPanoramicView mPanoramicView;
    private int[] mRearRadarData;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private SparseIntArray mTipsArray;
    private int mWheelKeyStatus;

    private int resolveSpeedData(int i, int i2) {
        return (i2 * 256) + i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        int i = this.mEachId;
        if (i != 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte) (i - 1)});
        }
        initSparseArray();
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        int i;
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        try {
            i = byteArrayToIntArray[1];
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
            return;
        }
        if (i == 40) {
            setBaseInfo0x28();
            return;
        }
        if (i != 41) {
            if (i == 47) {
                setWheelCommand0x2F();
            } else if (i != 48) {
                if (i == 80) {
                    setSpeedData0x50();
                    return;
                }
                if (i == 203) {
                    setVehicleTypeFeedback0xCB();
                    return;
                }
                if (i == 208) {
                    setSetupStatusFeedback0xD0();
                    return;
                }
                if (i == 148) {
                    setPanoramicUiData0x94();
                    return;
                }
                if (i != 149) {
                    switch (i) {
                        case 32:
                            setWheelKey0x20();
                            break;
                        case 33:
                            setAirData0x21();
                            break;
                        case 34:
                            setRearRadarData0x22();
                            break;
                        case 35:
                            setFrontRadarData0x23();
                            break;
                    }
                    return;
                }
                setGlonassData0x95();
                return;
            }
            setVersionInfo0x30();
            return;
        }
        setTrackData0x29();
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick(45);
            return;
        }
        if (i == 4) {
            realKeyClick(46);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 135) {
            realKeyClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 9) {
            realKeyClick(14);
            return;
        }
        if (i == 10) {
            realKeyClick(15);
        } else if (i == 21) {
            realKeyClick(50);
        } else {
            if (i != 22) {
                return;
            }
            realKeyClick(49);
        }
    }

    private void setWheelCommand0x2F() {
        try {
            switch (this.mCanBusInfoInt[2]) {
                case 1:
                    realKeyClick4(77);
                    break;
                case 2:
                    changeBandFm1();
                    break;
                case 3:
                    changeBandFm2();
                    break;
                case 4:
                    realKeyClick4(130);
                    break;
                case 5:
                    realKeyClick4(59);
                    break;
                case 6:
                    realKeyClick4(61);
                    break;
                case 7:
                    realKeyClick4(140);
                    break;
                case 8:
                    realKeyClick4(141);
                    break;
                case 9:
                    realKeyClick4(76);
                    break;
                case 10:
                    realKeyClick4(139);
                    break;
                case 11:
                    realKeyClick4(33);
                    break;
                case 12:
                    realKeyClick4(34);
                    break;
                case 13:
                    realKeyClick4(35);
                    break;
                case 14:
                    realKeyClick4(142);
                    break;
                case 15:
                    realKeyClick4(36);
                    break;
                case 16:
                    startMainActivity(this.mContext);
                    break;
                case 17:
                    realKeyClick4(14);
                    break;
                case 18:
                    realKeyClick4(15);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setRearRadarData0x22() {
        if (isRearRadarDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (isFrontRadarDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setBaseInfo0x28() {
        Log.w("ljq", "setBaseInfo0x28: " + Arrays.toString(this.mCanBusInfoInt));
        try {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mLeftFrontRec = boolBit7;
            GeneralDoorData.isRightFrontDoorOpen = boolBit7;
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.mRightFrontRec = boolBit6;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
            boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            this.mLeftRearRec = boolBit5;
            GeneralDoorData.isRightRearDoorOpen = boolBit5;
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mRightRearRec = boolBit4;
            GeneralDoorData.isLeftRearDoorOpen = boolBit4;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (isDoorDataChange()) {
                updateDoorView(this.mContext);
            }
            Log.w("ljq", "setBaseInfo0x28: " + (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ? "倒挡" : "非倒挡"));
            Log.w("ljq", "setBaseInfo0x28: " + (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? "非P挡" : "P挡"));
            Log.w("ljq", "setBaseInfo0x28: " + (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? "小灯开" : "小灯关"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setSpeedData0x50() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(resolveSpeedData(iArr[2], iArr[3])).append(" Km/h").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append(resolveSpeedData(iArr2[4], iArr2[5])).append(" RPM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(resolveSpeedData(iArr3[2], iArr3[3]));
    }

    private void setVehicleTypeFeedback0xCB() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            Log.w("ljq", "setVehicleTypeFeedback: 15款奇骏自动空调");
        } else {
            if (i != 1) {
                return;
            }
            Log.w("ljq", "setVehicleTypeFeedback: 15途乐低配");
        }
    }

    private void setPanoramicUiData0x94() {
        getMyPanoramicView();
        int i = this.mCanBusInfoInt[2];
        if (i == 255) {
            i = 6;
        }
        this.mPanoramicView.mPageNumber = i;
        this.mPanoramicView.mIbUpStatus = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbDownStatus = !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftStatus = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightStatus = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightDownStatus = !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftDownStatus = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mTvTips = this.mContext.getString(this.mTipsArray.get(this.mCanBusInfoInt[4]));
        this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._22.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mPanoramicView.updatePanoramicView();
            }
        });
    }

    private void setGlonassData0x95() {
        boolean z = getVolume() == 0;
        if (this.mCanBusInfoInt[2] == 1) {
            if (z) {
                return;
            }
            realKeyClick4(3);
        } else if (z) {
            realKeyClick4(3);
        }
    }

    private void setSetupStatusFeedback0xD0() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            Log.w("ljq", "setSetupStatusFeedback0xD0: 时钟设置 " + (this.mCanBusInfoInt[3] == 0 ? "成功" : "失败"));
        } else {
            if (i != 2) {
                return;
            }
            Log.w("ljq", "setSetupStatusFeedback0xD0: DISP 按键" + (this.mCanBusInfoInt[3] == 0 ? "释放" : "按下"));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        if (!z && i5 == 0) {
            i5 = 12;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i5, (byte) i6, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, z2), 6, !z)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.mIsMute = z;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
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

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
    }

    private String resolveAirTemp(int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
    }

    private void initSparseArray() {
        SparseIntArray sparseIntArray = new SparseIntArray(39);
        this.mTipsArray = sparseIntArray;
        sparseIntArray.put(0, R.string.null_value);
        this.mTipsArray.append(1, R.string.nissan_infor_eiisbm);
        this.mTipsArray.append(2, R.string.nissan_infor_pana);
        this.mTipsArray.append(3, R.string.nissan_infor_utstcs);
        this.mTipsArray.append(4, R.string.nissan_infor_sfg);
        this.mTipsArray.append(5, R.string.nissan_infor_sfdf);
        this.mTipsArray.append(6, R.string.nissan_infor_snteps);
        this.mTipsArray.append(7, R.string.nissan_infor_pudtspp);
        this.mTipsArray.append(8, R.string.nissan_infor_app);
        this.mTipsArray.append(9, R.string.nissan_infor_pac);
        this.mTipsArray.append(10, R.string.nissan_infor_db);
        this.mTipsArray.append(11, R.string.nissan_infor_sasrg);
        this.mTipsArray.append(12, R.string.nissan_infor_df);
        this.mTipsArray.append(13, R.string.nissan_infor_sasfg);
        this.mTipsArray.append(14, R.string.nissan_infor_pafd);
        this.mTipsArray.append(18, R.string.nissan_infor_csw);
        this.mTipsArray.append(19, R.string.nissan_infor_rt);
        this.mTipsArray.append(20, R.string.nissan_infor_vmbo);
        this.mTipsArray.append(21, R.string.nissan_infor_cdts);
        this.mTipsArray.append(22, R.string.nissan_infor_sf);
        this.mTipsArray.append(23, R.string.nissan_infor_paft);
        this.mTipsArray.append(24, R.string.nissan_infor_os);
        this.mTipsArray.append(25, R.string.nissan_infor_si);
        this.mTipsArray.append(26, R.string.nissan_infor_ton);
        this.mTipsArray.append(27, R.string.nissan_infor_va);
        this.mTipsArray.append(28, R.string.nissan_infor_dobo);
        this.mTipsArray.append(29, R.string.nissan_infor_tot);
        this.mTipsArray.append(34, R.string.nissan_infor_engna);
        this.mTipsArray.append(35, R.string.nissan_infor_espna);
        this.mTipsArray.append(36, R.string.nissan_infor_fgoops);
        this.mTipsArray.append(37, R.string.nissan_infor_rnp);
        this.mTipsArray.append(39, R.string.nissan_infor_s);
        this.mTipsArray.append(40, R.string.nissan_infor_srg);
        this.mTipsArray.append(66, R.string.nissan_infor_cs);
        this.mTipsArray.append(67, R.string.nissan_infor_sbpstspa);
        this.mTipsArray.append(69, R.string.nissan_infor_rs);
        this.mTipsArray.append(76, R.string.nissan_infor_sr);
        this.mTipsArray.append(77, R.string.nissan_infor_dfwc);
        this.mTipsArray.append(78, R.string.nissan_infor_dbwc);
        this.mTipsArray.append(79, R.string.nissan_infor_co);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        if (i == 1 && i2 == 0) {
            arrayList.add(new SettingUpdateEntity(1, 1, null).setEnable(i3 == 1));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 2 && i != 49) {
            return false;
        }
        turnCamera();
        return true;
    }

    private void turnCamera() {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int i, int i2, int i3, Bundle bundle) {
        if (i == 182) {
            turnCamera();
        }
    }
}
