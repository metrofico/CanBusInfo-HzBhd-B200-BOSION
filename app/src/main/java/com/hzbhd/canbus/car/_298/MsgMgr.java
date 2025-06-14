package com.hzbhd.canbus.car._298;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.Arrays;
import org.apache.log4j.helpers.DateLayout;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static int up_dn_btn_data;
    private static int voice_btn_data;
    TextView content;
    AlertDialog dialog;
    Button i_know;
    private boolean mBeltStatus;
    private byte[] mCanBusBtnPanelInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private SparseArray<int[]> mCanbusDataArray;
    int[] mCarDoorData;
    private Context mContext;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    View view;
    private final String TAG = "_298_MsgMgr";
    private final int DATA_TYPE = 1;

    private int getFrontRadarInfo(int i) {
        if (i == 2) {
            return 10;
        }
        return i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        if (getCurrentCanDifferentId() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 2, 38});
        } else if (getCurrentCanDifferentId() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 9, 38});
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
        this.mCanbusDataArray = new SparseArray<>();
        GeneralDoorData.isShowSeatBelt = true;
        RadarInfoUtil.mMinIsClose = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKeyData0x11(context);
            track0x11();
            return;
        }
        if (i == 18) {
            setDoorData0x12(context);
            return;
        }
        if (i == 24) {
            set0x18LightInfo();
            return;
        }
        if (i == 38) {
            setVehicleTypeData0x26();
            return;
        }
        if (i == 49) {
            setAirData0x31(context);
            return;
        }
        if (i == 65) {
            setRadarData0x41(context);
            return;
        }
        if (i == 240) {
            setVersionData0xF0(context);
            return;
        }
        if (i == 33) {
            panelButton0x21();
        } else if (i == 34 && !isBtnPanelMsgReturn(bArr)) {
            setOriginalPanel0x22();
        }
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [com.hzbhd.canbus.car._298.MsgMgr$2] */
    private void set0x18LightInfo() {
        String string = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? this.mContext.getString(R.string._214_lighting_1) : DateLayout.NULL_DATE_FORMAT;
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            string = this.mContext.getString(R.string._214_lighting_2);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            string = this.mContext.getString(R.string._214_lighting_3);
        }
        if (string.equals(DateLayout.NULL_DATE_FORMAT)) {
            return;
        }
        if (this.view == null) {
            this.view = LayoutInflater.from(this.mContext).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(this.mContext).setView(this.view).create();
        }
        if (this.content == null) {
            this.content = (TextView) this.view.findViewById(R.id.alert_content);
        }
        this.content.setText(string);
        if (this.i_know == null) {
            this.i_know = (Button) this.view.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._298.MsgMgr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgMgr.this.dialog.dismiss();
            }
        });
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
        new CountDownTimer(2000L, 1000L) { // from class: com.hzbhd.canbus.car._298.MsgMgr.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.dialog.dismiss();
            }
        }.start();
    }

    private void setOriginalPanel0x22() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (iArr[3] > voice_btn_data) {
                realKeyClick4(7);
                voice_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < voice_btn_data) {
                realKeyClick4(8);
                voice_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (iArr[3] > up_dn_btn_data) {
            realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
        if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
    }

    private void panelButton0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 16) {
            realKeyLongClick1(this.mContext, 49, iArr[3]);
            return;
        }
        if (i == 36) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(this.mContext, 52, iArr[3]);
            return;
        }
        if (i == 51) {
            realKeyLongClick1(this.mContext, 62, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 21, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, iArr[3]);
        } else if (i == 93) {
            realKeyLongClick1(this.mContext, 4, iArr[3]);
        } else {
            if (i != 94) {
                return;
            }
            realKeyLongClick1(this.mContext, 75, iArr[3]);
        }
    }

    private void track0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setWheelKeyData0x11(Context context) {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyLongClick1(context, 0);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 3);
            return;
        }
        if (i == 5) {
            realKeyLongClick1(context, 14);
            return;
        }
        if (i != 23) {
            switch (i) {
                case 12:
                    realKeyLongClick1(context, 2);
                    break;
                case 13:
                    realKeyLongClick1(context, 45);
                    break;
                case 14:
                    realKeyLongClick1(context, 46);
                    break;
            }
            return;
        }
        realKeyLongClick1(context, 128);
    }

    private void setDoorData0x12(Context context) {
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
            updateDoorView(context);
        }
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setAirData0x31(Context context) {
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_foot = isBlowModeMatch(this.mCanBusInfoInt[6], 3, 5, 12);
        GeneralAirData.front_left_blow_head = isBlowModeMatch(this.mCanBusInfoInt[6], 5, 6);
        GeneralAirData.front_left_blow_window = isBlowModeMatch(this.mCanBusInfoInt[6], 12);
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
        updateAirActivity(context, 1001);
    }

    private void setRadarData0x41(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            if (this.mCanBusInfoInt[13] == 0) {
                GeneralParkData.isShowDistanceNotShowLocationUi = false;
                int[] iArr = this.mCanBusInfoInt;
                RadarInfoUtil.setRearRadarLocationData(8, iArr[2], iArr[3], iArr[4], iArr[5]);
                RadarInfoUtil.setFrontRadarLocationData(10, getFrontRadarInfo(this.mCanBusInfoInt[6]), getFrontRadarInfo(this.mCanBusInfoInt[6]), getFrontRadarInfo(this.mCanBusInfoInt[9]), getFrontRadarInfo(this.mCanBusInfoInt[9]));
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.isShowDistanceNotShowLocationUi = true;
                int[] iArr2 = this.mCanBusInfoInt;
                RadarInfoUtil.setRearRadarDistanceData(iArr2[2], iArr2[3], iArr2[4], iArr2[5]);
                int[] iArr3 = this.mCanBusInfoInt;
                int i = iArr3[6];
                int i2 = iArr3[9];
                RadarInfoUtil.setFrontRadarDistanceData(i, i, i2, i2);
                GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
                updateParkUi(null, this.mContext);
            }
            updateParkUi(null, context);
        }
    }

    private void setVehicleTypeData0x26() {
        int[] iArr = this.mCanBusInfoInt;
        Log.i("_298_MsgMgr", "setVehicleTypeData0x26: Car series：" + DateLayout.NULL_DATE_FORMAT + ", Model:" + ((iArr[2] == 38 && iArr[3] == 2) ? "风光580" : DateLayout.NULL_DATE_FORMAT));
    }

    private void setVersionData0xF0(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[5]);
    }

    private boolean isBlowModeMatch(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private String resolveAirTemperature(Context context, int i) {
        return i == 254 ? "LOW" : i == 255 ? "HIGH" : (i / 2.0f) + getTempUnitC(context);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBeltStatus == GeneralDoorData.isSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
        return true;
    }

    private boolean isFirst() {
        if (isAirFirst) {
            isAirFirst = false;
            if (!GeneralAirData.power) {
                return true;
            }
        }
        return false;
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private boolean isBtnPanelMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusBtnPanelInfoCopy)) {
            return true;
        }
        this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
