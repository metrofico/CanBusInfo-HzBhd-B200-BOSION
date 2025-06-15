package com.hzbhd.canbus.car._28;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    static final int AMPLIFIER_HALF_MAX = 10;
    private static final int AMPLIFIER_MAX = 20;
    static final int AMPLIFIER_OFFSET = 6;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private Context mContext;
    private DecimalFormat mDecimalFormat00;
    private DecimalFormat mDecimalFormat0000;
    private Handler mHandler;
    private final int DATA_TYPE = 1;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private final int SEND_NORMAL_MESSAGE = 2;
    private final int MEDIA_DATA_LENGTH = 15;
    private boolean mIsFirstAirData = true;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initData(context);
        initAmplifierData(context);
    }

    private void initData(final Context context) {
        this.mCanId = getCurrentEachCanId();
        this.mCanbusDataArray = new SparseArray<>();
        this.mDecimalFormat00 = new DecimalFormat("00");
        this.mDecimalFormat0000 = new DecimalFormat("0000");
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._28.MsgMgr.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 2) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    private void initAmplifierData(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            arrayList.add(new byte[]{22, -83, 7, (byte) SharePreUtil.getIntValue(context, "_28_amplifier_mute", 0)});
        }
        arrayList.add(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
        for (int i = 0; i < arrayList.size(); i++) {
            sendNormalMessage(arrayList.get(i), i * 80);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 49) {
            setAirData0x31(context);
        } else if (i == 166) {
            setAmplifierData0xA6(context);
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0(context);
        }
    }

    private void setAirData0x31(Context context) {
        updateOutDoorTemp(context, resolveOutDoorTemperature(context, this.mCanBusInfoInt[13]));
        byte[] bArr = this.mCanBusInfoByte;
        bArr[13] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        if (this.mIsFirstAirData) {
            this.mIsFirstAirData = false;
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = isBlowModeMatch(this.mCanBusInfoInt[6], 2, 12);
        GeneralAirData.front_left_blow_foot = isBlowModeMatch(this.mCanBusInfoInt[6], 3, 5, 12);
        GeneralAirData.front_left_blow_head = isBlowModeMatch(this.mCanBusInfoInt[6], 5, 6);
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[9]);
        updateAirActivity(context, 1001);
    }

    private void setVersionInfo0xF0(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setAmplifierData0xA6(Context context) {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.leftRight = (this.mCanBusInfoInt[3] - 6) - 10;
        GeneralAmplifierData.frontRear = (this.mCanBusInfoInt[4] - 6) - 10;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 6;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 6;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 6;
        saveAmplifierData(context, this.mCanId);
        updateAmplifierActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 0}, 15, 32));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte[] bArr = {22, -46, (byte) getBand(str)};
        if (str.contains("FM")) {
            if (str2.length() == 5) {
                str2 = " " + str2;
            }
            str2 = (str2 + "MHz") + i;
        }
        if (str.contains("AM")) {
            String str4 = " " + str2;
            if (str4.length() == 4) {
                str4 = " " + str4;
            }
            str2 = ((str4 + "KHz") + " ") + i;
        }
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, str2.getBytes()), 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        int i8;
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            i8 = 7;
            i3 = i4;
        } else {
            i8 = 6;
        }
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, (Integer.toString(DataHandleUtils.rangeNumber(getTime(i)[0], 0, 9)) + this.mDecimalFormat00.format(r2[1]) + this.mDecimalFormat00.format(r2[2]) + this.mDecimalFormat0000.format(i3) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 8}, 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 8}, 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 10}, 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 10}, 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 12}, 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, (("     " + this.mDecimalFormat0000.format((b7 * 256) + i)) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 22)}, (((((int) b3) + str3 + str4) + this.mDecimalFormat0000.format((b6 * 256) + i)) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) 21}, 15, 32));
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

    private String resolveOutDoorTemperature(Context context, int i) {
        return ((i / 2.0f) - 40.0f) + getTempUnitC(context);
    }

    private int getBand(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return 4;
            case "AM2":
                return 5;
            case "FM2":
                return 2;
            case "FM3":
                return 3;
            default:
                return 1;
        }
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = i;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
