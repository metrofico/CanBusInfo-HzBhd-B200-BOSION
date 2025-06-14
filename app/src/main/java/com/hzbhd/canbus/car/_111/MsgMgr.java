package com.hzbhd.canbus.car._111;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int _1111_AMPLIFIER_BAND_MAX = 1;
    static final int _1111_AMPLIFIER_HALF_MAX = 10;
    private int[] mAmplifierDataNow;
    private int mAmplifierSwitch;
    private boolean mBackStatus;
    private int mCanId;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private TimerUtil mTimerUtil = new TimerUtil();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, 1});
        Log.d("fxHouAcc", "初始化");
        initAmplifier(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        Context context = this.mContext;
        if (context != null) {
            initAmplifier(context);
        }
    }

    void updateAmplifierSwitch(int i) {
        this.mAmplifierSwitch = i;
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, getUiMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -124, 2, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 3, (byte) (10 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.leftRight + 10)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandBass + 1)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandTreble + 1)}, new byte[]{22, -124, 7, (byte) (GeneralAmplifierData.bandMiddle + 1)}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._111.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 100L, 133L);
        this.mAmplifierSwitch = 1;
        this.mTimerUtil.stopTimer();
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._111.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) MsgMgr.this.mAmplifierSwitch});
            }
        }, 100L, 1000L);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanbusInfoInt = getByteArrayToIntArray(bArr);
        this.mCanbusInfoByte = bArr;
        byte b = bArr[1];
        if (b == 23) {
            set0x17Amplifier();
            return;
        }
        if (b == 32) {
            set0x20WheelKeyData(context);
            return;
        }
        if (b == 36) {
            set0x24DoorData(context);
        } else if (b == 41) {
            set0x29TrackData(context);
        } else {
            if (b != 48) {
                return;
            }
            set0x30VersionInfo();
        }
    }

    private void set0x20WheelKeyData(Context context) {
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
                i = 20;
                break;
            case 4:
                i = 21;
                break;
            case 5:
                i = 3;
                break;
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
        }
        realKeyLongClick1(context, i, iArr[3]);
    }

    private void set0x24DoorData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
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

    private void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 1000, 16);
            Log.i("cbc", "set0x29TrackData: GeneralParkData.trackAngle <--> " + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
    }

    private void set0x17Amplifier() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1);
        int[] iArr = this.mCanbusInfoInt;
        int i = iArr[3];
        int i2 = iArr[4];
        int i3 = iArr[5];
        int i4 = iArr[6];
        int i5 = iArr[7];
        int i6 = iArr[8];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(intFromByteWithBit)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.volume = i;
        GeneralAmplifierData.frontRear = 10 - i2;
        GeneralAmplifierData.leftRight = i3 - 10;
        GeneralAmplifierData.bandBass = i4 - 1;
        GeneralAmplifierData.bandTreble = i5 - 1;
        GeneralAmplifierData.bandMiddle = i6 - 1;
        if (isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
            updateAmplifierActivity(null);
            saveAmplifierData(this.mContext, this.mCanId);
        }
    }

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        Log.d("fxHouAcc", "" + z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM1")) {
            i3 = 1;
        } else if (str.contains("FM2")) {
            i3 = 2;
        } else if (str.contains("FM3")) {
            i3 = 3;
        } else if (str.contains("AM1")) {
            i3 = 17;
        } else if (str.contains("AM2")) {
            i3 = 18;
        } else {
            i3 = str.contains("AM3") ? 19 : 0;
        }
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 0, (byte) i3, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0});
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("AM") || str.equals("AM3") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            i3 = i4;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, 0, 1, (byte) DataHandleUtils.rangeNumber(i3, 255), 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanbusInfoByte));
    }
}
