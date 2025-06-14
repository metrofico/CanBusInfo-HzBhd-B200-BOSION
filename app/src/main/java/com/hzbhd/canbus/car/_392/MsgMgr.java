package com.hzbhd.canbus.car._392;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private TextView content;
    private CountDownTimer countDownTimer;
    private AlertDialog dialog;
    int differentId;
    int eachId;
    private Button i_know;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private View view;
    DecimalFormat timeFormat = new DecimalFormat("00");
    private int nowLightLever = 5;
    private int data5 = -1;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
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
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC8BtInfo(1);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC8BtInfo(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i != 17) {
            if (i == 49) {
                setOutDoorTem0x31();
                return;
            } else {
                if (i != 240) {
                    return;
                }
                updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
                return;
            }
        }
        setMedia0x11();
        setSWC0x11();
        setLight0x11();
        setAlert0x11();
        setTrack0x11();
        setTime0x11();
    }

    private void setOutDoorTem0x31() {
        if (this.mCanBusInfoInt[13] == 255) {
            updateOutDoorTemp(this.mContext, "");
        } else {
            updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        }
    }

    private void setLight0x11() {
        int i = this.nowLightLever;
        int i2 = this.mCanBusInfoInt[6];
        if (i == i2) {
            return;
        }
        this.nowLightLever = i2;
        if (i2 < 17) {
            return;
        }
        setBacklightLevel(((i2 - 17) / 36) + 1);
    }

    private void setTime0x11() {
        String str = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]) ? "        (Invalid)" : "        (Support)";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_392_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_392_drive2"), this.timeFormat.format(this.mCanBusInfoInt[11]) + ":" + this.timeFormat.format(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7)) + str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack0x11() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        int[] iArr = this.mCanBusInfoInt;
        iArr[8] = blockBit(iArr[8], 7);
        if (boolBit7) {
            int[] iArr2 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = (getMsbLsbResult(iArr2[8], iArr2[9]) / 10) / 21;
        } else {
            int[] iArr3 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = ((-getMsbLsbResult(iArr3[8], iArr3[9])) / 10) / 21;
        }
        updateParkUi(null, this.mContext);
    }

    private void setAlert0x11() {
        int i = this.data5;
        if (i == -1) {
            this.data5 = this.mCanBusInfoInt[7];
            return;
        }
        int i2 = this.mCanBusInfoInt[7];
        if (i == i2) {
            return;
        }
        this.data5 = i2;
        if (i2 == 0) {
            return;
        }
        String str = DataHandleUtils.getBoolBit7(i2) ? "【" + this.mContext.getString(R.string._392_car1) + ":" + this.mContext.getString(R.string._392_state2) + "】" : "";
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
            str = str + "【" + this.mContext.getString(R.string._392_car2) + ":" + this.mContext.getString(R.string._392_state2) + "】";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
            str = str + "【" + this.mContext.getString(R.string._392_car3) + ":" + this.mContext.getString(R.string._392_state3) + "】";
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
        if (this.i_know == null) {
            this.i_know = (Button) this.view.findViewById(R.id.i_know);
        }
        this.content.setText(str);
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._392.MsgMgr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgMgr.this.dialog.dismiss();
            }
        });
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.car._392.MsgMgr.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.dialog.dismiss();
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
    }

    private void setSWC0x11() {
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 5)) {
            case 0:
                realKeyLongClick1(this.mContext, 0, this.mCanBusInfoInt[5]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, this.mCanBusInfoInt[5]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, this.mCanBusInfoInt[5]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_MUTE_PHONE_ON_OUT, this.mCanBusInfoInt[5]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 39, this.mCanBusInfoInt[5]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 14, this.mCanBusInfoInt[5]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 15, this.mCanBusInfoInt[5]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, this.mCanBusInfoInt[5]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 48, this.mCanBusInfoInt[5]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 47, this.mCanBusInfoInt[5]);
                break;
            case 10:
                if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
                    realKeyLongClick1(this.mContext, 151, this.mCanBusInfoInt[5]);
                    break;
                } else {
                    realKeyLongClick1(this.mContext, 2, this.mCanBusInfoInt[5]);
                    break;
                }
            case 13:
                realKeyLongClick1(this.mContext, 45, this.mCanBusInfoInt[5]);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 46, this.mCanBusInfoInt[5]);
                break;
            case 15:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, this.mCanBusInfoInt[5]);
                break;
            case 16:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_RADIO, this.mCanBusInfoInt[5]);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 59, this.mCanBusInfoInt[5]);
                break;
        }
    }

    private void setMedia0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_392_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_392_drive1"), getMediaType()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getMediaType() {
        int i = this.mCanBusInfoInt[3];
        return i != 1 ? i != 2 ? i != 3 ? "NO MEDIA" : "AUX" : "CD" : "Radio";
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
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

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }

    public void toast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._392.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.getActivity(), str, 0).show();
            }
        });
    }
}
