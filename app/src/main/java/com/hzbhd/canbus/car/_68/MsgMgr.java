package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.midware.constant.HotKeyConstant;

import org.apache.log4j.helpers.DateLayout;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x25Data;
    private int[] m0x26Data;
    private int[] m0x30Data;
    private int[] m0x32Data;
    private int[] m0x33DataIndexOne;
    private int[] m0x33DataIndexTwo;
    private int[] m0xD1Data;
    private int[] m0xD2Data;
    private SparseArray<ActiveParkItem> mActiveParkItemArray;
    private ActiveParkView mActiveParkView;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mIsActiveViewOpen;
    private WindowManager.LayoutParams mLayoutParams;
    private MyPanoramicView mPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private UiMgr mUiMgr;
    private WindowManager mWindowManager;
    private String ParkingType = "";
    private String ParkingProgress = "";

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    public int calculateSum(int i, int i2) {
        return (i * 256) + i2;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        getUiMgr(this.mContext).send0xE2CarModelInfo(getCurrentEachCanId());
        initData(context);
        initActivePark(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OnStarActivity);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 19) {
            setCarWind0x13();
        }
        if (i == 38) {
            setTrackData0x26();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        switch (i) {
            case 1:
                setWheelKey0x01();
                break;
            case 2:
                setPanelKey0x02();
                break;
            case 3:
                setAirData0x03();
                break;
            case 4:
                setPanelKey0x02();
                break;
            case 5:
                setSettingData0x05();
                break;
            case 6:
                setSettingData0x06();
                break;
            case 7:
                setRadar0x07();
                break;
            case 8:
                setOnStartData0x08();
                break;
            case 9:
                setOnStartData0x09();
                break;
            case 10:
                setControl0x0A();
                break;
            case 11:
                setCarSpeed0x0B();
                break;
            case 12:
                setCarLanguage0x0C();
                break;
            case 13:
                setCarVolume0x0D();
                break;
            default:
                switch (i) {
                    case 34:
                        setRearRadar0x22();
                        break;
                    case 35:
                        setFrontRadarData0x23();
                        break;
                    case 36:
                        if (!isDoorMsgRepeat(bArr)) {
                            setDoorData0x24();
                            break;
                        }
                        break;
                    default:
                        switch (i) {
                            case 65:
                                setOnStartData0x41();
                                break;
                            case 66:
                                setOnStartData0x42();
                                break;
                            case 67:
                                setCarSettings0x43();
                                break;
                            case 68:
                                setAirData0x44();
                                break;
                            case 69:
                                setAirData0x45();
                                break;
                            case 70:
                                setAirData0x46();
                                break;
                            case 71:
                                setHybirdData0x47(context);
                                break;
                            case 72:
                                setPanoramic0x48();
                                break;
                            case 73:
                                setPanoramic0x49();
                                break;
                            case 74:
                                StringBuilder sbAppend = new StringBuilder().append("0x4A ");
                                int[] iArr = this.mCanBusInfoInt;
                                Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
                                int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
                                if (intFromByteWithBit != 1) {
                                    if (intFromByteWithBit == 2) {
                                        setFuelData0x4A();
                                        break;
                                    }
                                } else {
                                    setTireData0x4A();
                                    break;
                                }
                                break;
                        }
                }
        }
    }

    private void setPanoramic0x49() {
        forceReverse(this.mContext, this.mCanBusInfoInt[2] == 1);
    }

    private void setFuelData0x4A() {
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        String string = sb.append(calculateSum(iArr[3], iArr[4])).append("km").toString();
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        String string2 = sb2.append(calculateSum(iArr2[5], iArr2[6]) * 0.1d).append("L/100km").toString();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string2));
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTireData0x4A() {
        StringBuilder sbAppend = new StringBuilder().append("0x4A ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("setTireData0x4A", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(getTireEntity(0, "", getTirePressure(this.mCanBusInfoInt[4]), ""));
        arrayList.add(getTireEntity(1, "", getTirePressure(this.mCanBusInfoInt[5]), ""));
        arrayList.add(getTireEntity(2, "", getTirePressure(this.mCanBusInfoInt[6]), ""));
        arrayList.add(getTireEntity(3, "", getTirePressure(this.mCanBusInfoInt[7]), ""));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3) {
        String[] strArr;
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            strArr = new String[]{"", str2, str3};
        } else {
            strArr = new String[]{str, str2, str3};
            i2 = 1;
        }
        return new TireUpdateEntity(i, i2, strArr);
    }

    private String getTirePressure(int i) {
        return String.valueOf(i * 4);
    }

    private void setAirData0x46() {
        boolean z = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) == 1;
        if (GeneralAirData.climate != z) {
            GeneralAirData.climate = z;
            if (!GeneralAirData.climate) {
                if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
                    finishActivity();
                }
            } else {
                AirActivity.mIsClickOpen = true;
                Intent intent = new Intent(this.mContext, (Class<?>) AirActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.mContext.startActivity(intent);
            }
        }
    }

    private void setAirData0x44() {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 2));
        } else if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 1));
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_heat"), 0));
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_seat_cold"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_air"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "remote_start_rear_air"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSettings0x43() {
        StringBuilder sbAppend = new StringBuilder().append("setCarSettings0x43 ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("cbc", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        getMyPanoramicView().mRearCarStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 2);
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._68.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() {
                m926lambda$setCarSettings0x43$0$comhzbhdcanbuscar_68MsgMgr();
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 16, Integer.valueOf(this.mCanBusInfoInt[3])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* renamed from: lambda$setCarSettings0x43$0$com-hzbhd-canbus-car-_68-MsgMgr, reason: not valid java name */
    /* synthetic */ void m926lambda$setCarSettings0x43$0$comhzbhdcanbuscar_68MsgMgr() {
        this.mPanoramicView.updatePanoramicView();
    }

    private MyPanoramicView getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        return this.mPanoramicView;
    }

    private void setTrackData0x26() {
        if (is0x26DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadar0x22() {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setCarWind0x13() {
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        updateAirActivity(this.mContext, 1001);
    }

    private void setCarVolume0x0D() {
        StringBuilder sbAppend = new StringBuilder().append("setCarVolume0x0D ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarLanguage0x0C() {
        StringBuilder sbAppend = new StringBuilder().append("setCarLanguage0x0C ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSpeed0x0B() {
        StringBuilder sbAppend = new StringBuilder().append("setCarSpeed0x0B ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(3, 0, sb.append(((iArr2[2] * 256) + iArr2[3]) / 16).append("km/h").toString()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(((iArr3[2] * 256) + iArr3[3]) / 16);
    }

    private void setControl0x0A() {
        StringBuilder sbAppend = new StringBuilder().append("setControl0x0A ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
        arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(2, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(2, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        arrayList.add(new SettingUpdateEntity(2, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(2, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(2, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(2, 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
        arrayList.add(new SettingUpdateEntity(2, 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(2, 18, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        arrayList.add(new SettingUpdateEntity(2, 19, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(2, 20, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 21, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        arrayList.add(new SettingUpdateEntity(2, 22, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, 23, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
        arrayList.add(new SettingUpdateEntity(2, 24, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_body_center_control_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_body_center_control_2", "_68_Automatically_unlock_near_car"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2))));
        arrayList.add(new SettingUpdateEntity(2, 26, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 27, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
        arrayList.add(new SettingUpdateEntity(2, 28, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, 29, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
        arrayList.add(new SettingUpdateEntity(2, 30, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRadar0x07() {
        StringBuilder sbAppend = new StringBuilder().append("setWheelKey0x01 ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(intFromByteWithBit)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setPanelKey0x02() {
        StringBuilder sbAppend = new StringBuilder().append("setWheelKey0x01 ");
        int[] iArr = this.mCanBusInfoInt;
        HzbhdLog.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        if (getCurrentEachCanId() == 24 || getCurrentEachCanId() == 23) {
            if (ConstantHashMap.panelKeyMaps_Gl8.containsKey(Integer.valueOf(this.mCanBusInfoInt[2]))) {
                realKeyClick2(ConstantHashMap.panelKeyMaps_Gl8.get(Integer.valueOf(this.mCanBusInfoInt[2])).intValue());
            }
        } else if (getCurrentEachCanId() == 17 || getCurrentEachCanId() == 99) {
            realKeyClick2(ConstantHashMap.panelKeyMaps_Encore_.get(Integer.valueOf(this.mCanBusInfoInt[2])).intValue());
        } else if (ConstantHashMap.panelKeyMaps.containsKey(Integer.valueOf(this.mCanBusInfoInt[2]))) {
            realKeyClick2(ConstantHashMap.panelKeyMaps.get(Integer.valueOf(this.mCanBusInfoInt[2])).intValue());
        }
    }

    private void setWheelKey0x01() {
        StringBuilder sbAppend = new StringBuilder().append("setWheelKey0x01 ");
        int[] iArr = this.mCanBusInfoInt;
        Log.d("mww", sbAppend.append(FgeString.bytes2HexString(iArr, iArr.length)).toString());
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                realKeyLongClick1(7);
                break;
            case 2:
                realKeyLongClick1(8);
                break;
            case 3:
                realKeyLongClick1(45);
                break;
            case 4:
                realKeyLongClick1(46);
                break;
            case 5:
                realKeyLongClick1(2);
                break;
            case 6:
                realKeyLongClick1(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 7:
                realKeyLongClick1(184);
                break;
            case 8:
                realKeyLongClick1(48);
                break;
            case 9:
                realKeyLongClick1(47);
                break;
        }
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyLongClick1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, CommUtil.subArrayToString(this.mCanBusInfoByte, 0, 16));
    }

    private void setHybirdData0x47(Context context) {
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralHybirdData.title = context.getString(R.string.energy_info);
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            arrayList.add(context.getString(R.string.energy_close));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
            arrayList.add(context.getString(R.string.battery_energy));
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
            arrayList.add(context.getString(R.string.engin_energy));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
            arrayList.add(context.getString(R.string.regeneration_energy_recovery));
        }
        if (arrayList.size() != 0) {
            GeneralHybirdData.valueList = arrayList;
        }
        updateHybirdActivity(null);
    }

    private void setAirData0x45() {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = "LO";
        } else if (129 == i) {
            string = "HI";
        } else if (255 == i) {
            string = this.mContext.getString(R.string.no_display);
        } else {
            string = (1 > i || 128 < i) ? "" : (((i - 1) * 0.5d) + 0.5d) + getTempUnitC(this.mContext);
        }
        GeneralAirData.rear_temperature = string;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        if (intFromByteWithBit == 0 || intFromByteWithBit == 1 || intFromByteWithBit == 2 || intFromByteWithBit == 3 || intFromByteWithBit == 4 || intFromByteWithBit == 5 || intFromByteWithBit == 6) {
            GeneralAirData.rear_wind_level = intFromByteWithBit;
            GeneralAirData.rear_auto_wind_speed = false;
        } else if (intFromByteWithBit == 15) {
            GeneralAirData.rear_auto_wind_speed = true;
        } else {
            GeneralAirData.rear_auto_wind_speed = false;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_auto_wind_model = false;
        if (intFromByteWithBit2 == 1) {
            GeneralAirData.rear_left_auto_wind = true;
            GeneralAirData.rear_right_auto_wind = true;
        } else if (intFromByteWithBit2 == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (intFromByteWithBit2 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 4) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        updateAirActivity(this.mContext, 1002);
    }

    private void setOnStartData0x41() {
        GeneralOnStartData.mOnStarWirelessPoint = getSimpleGmOnstarWirelessInfo(this.mCanBusInfoInt, this.mCanBusInfoByte, 65);
        updateOnStarActivity(1002);
    }

    private void setOnStartData0x42() {
        GeneralOnStartData.mOnStarWirelessPassword = getSimpleGmOnstarWirelessPassWord(this.mCanBusInfoInt, this.mCanBusInfoByte, 66);
        updateOnStarActivity(1002);
    }

    private void setOnStartData0x08() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralOnStartData.mOnStarPhoneNum = DataHandleUtils.bcd2Str(Arrays.copyOfRange(bArr, 2, bArr.length));
        updateOnStarActivity(1001);
    }

    private void setOnStartData0x09() {
        GeneralOnStartData.mOnStarStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        updateOnStarActivity(1001);
    }

    private void setAirData0x03() {
        updateOutDoorTemp(this.mContext, ((int) this.mCanBusInfoByte[7]) + getTempUnitC(this.mContext));
        this.mCanBusInfoByte[7] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
        GeneralAirData.sync_temperature = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_defog = false;
        switch (intFromByteWithBit) {
            case 1:
                GeneralAirData.front_auto_wind_model = true;
                break;
            case 2:
                GeneralAirData.front_defog = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 8:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 9:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        GeneralAirData.climate = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralAirData.ac_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
        GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setSettingData0x06() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
        boolean z = intFromByteWithBit3 == 1;
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(intFromByteWithBit6)).setEnable(z));
        arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(intFromByteWithBit7)));
        arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(intFromByteWithBit8)));
        arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(intFromByteWithBit9)));
        if (intFromByteWithBit10 == 1) {
            arrayList.add(new SettingUpdateEntity(1, 9, this.mContext.getString(R.string.can_operate)).setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(1, 9, this.mContext.getString(R.string.can_not_operate)).setValueStr(true));
        }
        arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(intFromByteWithBit11)));
        arrayList.add(new SettingUpdateEntity(1, 11, Integer.valueOf(intFromByteWithBit12)));
        arrayList.add(new SettingUpdateEntity(1, 12, Integer.valueOf(intFromByteWithBit13)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData0x05() {
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2));
        int indexBy3Bit2 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        int indexBy3Bit3 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[2], 1));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[2], 0));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 5));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 4));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[3], 3));
        int indexBy3Bit4 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy3Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy3Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy3Bit4)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "_68_Pollution_control"), Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_air_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_air_control", "_68_lianaidexiniu"), "恋爱的犀牛").setEnable(indexBy3Bit == 1 && indexBy3Bit2 == 1 && indexBy3Bit3 == 1 && indexBy2Bit == 0 && indexBy2Bit2 == 0 && indexBy2Bit3 == 0 && indexBy2Bit4 == 1 && indexBy2Bit5 == 1 && indexBy3Bit4 == 1).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -119}, new byte[]{(byte) i8, (byte) i6, (byte) (!z ? 1 : 0)}));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM")) {
            str4 = "MHz";
            i3 = 1;
        } else {
            str4 = "KHz";
            i3 = 2;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) i3}, (str + " P" + i + " " + str2 + str4).getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 6}, ("Music Progress:" + str + ":" + str2 + ":" + str3).getBytes()));
        } else {
            String str7 = "Music Progress:" + str + ":" + str2 + ":" + str3;
            getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 4}, str7.getBytes()));
            MyLog.temporaryTracking(str7);
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 6}, ("Video Progress:" + str2 + ":" + str3 + ":" + str4).getBytes()));
        } else {
            getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 4}, ("Video Progress:" + str2 + ":" + str3 + ":" + str4).getBytes()));
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 5}, "Bluetooth music".getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xC0Info(SplicingByte(new byte[]{22, -64, 5}, "Aux playing".getBytes()));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void openOnStarPhoneMoreInfoFragment() {
        if (SystemUtil.isForeground(this.mContext, Constant.OnStarActivity.getClassName())) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(Constant.OnStarActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(OnStarActivity.BUNDLE_OPEN_FRAGMENT, OnStartPhoneMoreInfoFragment.class);
        this.mContext.startActivity(intent);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (30 == i) {
            return "HI";
        }
        if (255 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        if (29 == i) {
            return "16" + getTempUnitC(this.mContext);
        }
        if (31 == i) {
            return "16.5" + getTempUnitC(this.mContext);
        }
        if (32 == i) {
            return "15" + getTempUnitC(this.mContext);
        }
        if (33 == i) {
            return "15.5" + getTempUnitC(this.mContext);
        }
        if (34 == i) {
            return "31" + getTempUnitC(this.mContext);
        }
        return (1 > i || 28 < i) ? "" : (((i - 1) * 0.5d) + 17.0d) + getTempUnitC(this.mContext);
    }

    private int getIndexBy3Bit(int i) {
        LogUtil.showLog("getIndexBy3Bit:" + i);
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
        }
        return 0;
    }

    private String getSimpleGmOnstarWirelessInfo(int[] iArr, byte[] bArr, int i) {
        if (iArr[1] != i) {
            return "";
        }
        try {
            return new String(Arrays.copyOfRange(bArr, 2, bArr.length), "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getSimpleGmOnstarWirelessPassWord(int[] iArr, byte[] bArr, int i) {
        if (iArr[1] != i) {
            return "";
        }
        try {
            return new String(Arrays.copyOfRange(bArr, 2, bArr.length), "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void setPanoramic0x48() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._68.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (DataHandleUtils.getBoolBit5(MsgMgr.this.mCanBusInfoInt[2])) {
                    MsgMgr.this.openActiveParView();
                    MsgMgr.this.mParkPageUiSet.setShowCusPanoramicView(true);
                    MsgMgr.this.mActiveParkView.hideAlert();
                    ActiveParkItem activeParkItem = (ActiveParkItem) MsgMgr.this.mActiveParkItemArray.get(MsgMgr.this.mCanBusInfoInt[4]);
                    MsgMgr.this.mActiveParkView.setLeftSideImage(activeParkItem.getLeftImageResId());
                    MsgMgr.this.mActiveParkView.setRightSideImage(activeParkItem.getRightImageResId());
                    MsgMgr.this.mActiveParkView.setViewText(getTextViewContent(activeParkItem.getMessageResId()));
                    return;
                }
                MsgMgr.this.closeActiveParkView();
                MsgMgr.this.mParkPageUiSet.setShowCusPanoramicView(false);
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.updateParkUi(null, msgMgr.mContext);
            }

            private String getTextViewContent(int i) {
                return MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_type) + (!DataHandleUtils.getBoolBit7(MsgMgr.this.mCanBusInfoInt[2]) ? MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_type0) : MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_type1)) + "\n" + MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_LR) + (!DataHandleUtils.getBoolBit6(MsgMgr.this.mCanBusInfoInt[2]) ? MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_L) : MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_R)) + "\n" + MsgMgr.this.mContext.getString(R.string._68_Automatic_parking_progress) + (MsgMgr.this.mCanBusInfoInt[3] + "%") + "\n\n" + MsgMgr.this.mContext.getString(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeActiveParkView() {
        ActiveParkView activeParkView;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (activeParkView = this.mActiveParkView) == null || !this.mIsActiveViewOpen) {
            return;
        }
        windowManager.removeView(activeParkView);
        this.mIsActiveViewOpen = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openActiveParView() {
        if (this.mIsActiveViewOpen) {
            return;
        }
        this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
        this.mIsActiveViewOpen = true;
    }

    private void initData(Context context) {
        this.mParkPageUiSet = getUiMgr(context).getParkPageUiSet(context);
    }

    private void initActivePark(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        this.mLayoutParams.gravity = Gravity.CENTER;
        this.mLayoutParams.width = -1;
        this.mLayoutParams.height = -1;
        this.mActiveParkView = new ActiveParkView(context);
        SparseArray<ActiveParkItem> sparseArray = new SparseArray<>();
        this.mActiveParkItemArray = sparseArray;
        sparseArray.put(0, new ActiveParkItem(R.string._68_Automatic_parking0));
        this.mActiveParkItemArray.append(1, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x04, R.string._68_Automatic_parking1, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(2, new ActiveParkItem(R.drawable.ford_pa_0x05, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking2, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(3, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x04, R.string._68_Automatic_parking3, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(4, new ActiveParkItem(R.drawable.ford_pa_0x05, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking4, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.put(5, new ActiveParkItem(R.string._68_Automatic_parking5));
        this.mActiveParkItemArray.put(6, new ActiveParkItem(R.string._68_Automatic_parking6));
        this.mActiveParkItemArray.put(7, new ActiveParkItem(R.string._68_Automatic_parking7));
        this.mActiveParkItemArray.put(8, new ActiveParkItem(R.string._68_Automatic_parking8));
        this.mActiveParkItemArray.append(9, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x10, R.string._68_Automatic_parking9, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.put(10, new ActiveParkItem(R.string._68_Automatic_parking0A));
        this.mActiveParkItemArray.put(11, new ActiveParkItem(R.string._68_Automatic_parking0B));
        this.mActiveParkItemArray.put(12, new ActiveParkItem(R.string._68_Automatic_parking0C));
        this.mActiveParkItemArray.append(13, new ActiveParkItem(R.drawable.ford_pa_0x0f, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking0D, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.put(14, new ActiveParkItem(R.string._68_Automatic_parking0E));
        this.mActiveParkItemArray.put(15, new ActiveParkItem(R.string._68_Automatic_parking0F));
        this.mActiveParkItemArray.put(16, new ActiveParkItem(R.string._68_Automatic_parking10));
        this.mActiveParkItemArray.append(17, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x04, R.string._68_Automatic_parking11, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(18, new ActiveParkItem(R.drawable.ford_pa_0x05, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking12, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(19, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x12, R.string._68_Automatic_parking13, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.put(20, new ActiveParkItem(R.string._68_Automatic_parking14));
        this.mActiveParkItemArray.put(21, new ActiveParkItem(R.string._68_Automatic_parking15));
        this.mActiveParkItemArray.append(22, new ActiveParkItem(R.drawable.ford_pa_0x11, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking16, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.put(23, new ActiveParkItem(R.string._68_Automatic_parking17));
        this.mActiveParkItemArray.put(24, new ActiveParkItem(R.string._68_Automatic_parking18));
        this.mActiveParkItemArray.put(25, new ActiveParkItem(R.string._68_Automatic_parking19));
        this.mActiveParkItemArray.put(26, new ActiveParkItem(R.string._68_Automatic_parking1A));
        this.mActiveParkItemArray.append(27, new ActiveParkItem(R.drawable.ford_pa_0x15, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking1B, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(28, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x16, R.string._68_Automatic_parking1C, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(29, new ActiveParkItem(R.string._68_Automatic_parking1D));
        this.mActiveParkItemArray.append(30, new ActiveParkItem(R.string._68_Automatic_parking1E));
        this.mActiveParkItemArray.append(31, new ActiveParkItem(R.string._68_Automatic_parking1F));
        this.mActiveParkItemArray.append(32, new ActiveParkItem(R.string._68_Automatic_parking20));
        this.mActiveParkItemArray.append(33, new ActiveParkItem(R.string._68_Automatic_parking21));
        this.mActiveParkItemArray.append(34, new ActiveParkItem(R.string._68_Automatic_parking22));
        this.mActiveParkItemArray.append(35, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x08, R.string._68_Automatic_parking23, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(36, new ActiveParkItem(R.drawable.ford_pa_0x07_bak, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking24, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(37, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x08, R.string._68_Automatic_parking25, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(38, new ActiveParkItem(R.drawable.ford_pa_0x07_bak, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking26, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(39, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x0a, R.string._68_Automatic_parking27, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(40, new ActiveParkItem(R.drawable.ford_pa_0x09, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking28, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(41, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x0a, R.string._68_Automatic_parking29, DateLayout.NULL_DATE_FORMAT));
        this.mActiveParkItemArray.append(42, new ActiveParkItem(R.drawable.ford_pa_0x09, R.drawable.ford_sync_icon_null, R.string._68_Automatic_parking2A, DateLayout.NULL_DATE_FORMAT));
    }

    private class ActiveParkView extends LinearLayout {
        private ImageView mIvLeftSide;
        private ImageView mIvRightSide;
        private LinearLayout mLlAlert;
        private TextView mTvAlertMessage;
        private TextView mTvMessage;

        private void initView() {
        }

        public ActiveParkView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.layout_active_park_81_view, this);
            findeView();
            initView();
        }

        private void findeView() {
            this.mIvLeftSide = (ImageView) findViewById(R.id.iv_left_side);
            this.mIvRightSide = (ImageView) findViewById(R.id.iv_right_side);
            this.mTvMessage = (TextView) findViewById(R.id.tv_message);
            this.mLlAlert = (LinearLayout) findViewById(R.id.ll_alert);
            this.mTvAlertMessage = (TextView) findViewById(R.id.tv_alert_message);
        }

        public void showAlert() {
            this.mLlAlert.setVisibility(View.VISIBLE);
        }

        public void hideAlert() {
            this.mLlAlert.setVisibility(View.GONE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLeftSideImage(int i) {
            this.mIvLeftSide.setImageResource(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRightSideImage(int i) {
            this.mIvRightSide.setImageResource(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setViewText(String str) {
            this.mTvMessage.setText(str);
        }

        public void setAlertText(int i) {
            this.mTvAlertMessage.setText(i);
        }

        public void setAlertText(String str) {
            this.mTvAlertMessage.setText(str);
        }
    }

    private class ActiveParkItem {
        private int leftImageResId;
        private int messageResId;
        private String reverseIcon;
        private int rightImageResId;

        public ActiveParkItem(int i) {
            this.messageResId = i;
        }

        public ActiveParkItem(int i, int i2, int i3, String str) {
            this.leftImageResId = i;
            this.rightImageResId = i2;
            this.messageResId = i3;
            this.reverseIcon = str;
        }

        public int getLeftImageResId() {
            return this.leftImageResId;
        }

        public int getRightImageResId() {
            return this.rightImageResId;
        }

        public int getMessageResId() {
            return this.messageResId;
        }

        public String getReverseIcon() {
            return this.reverseIcon;
        }
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x26Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isNotAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }
}
